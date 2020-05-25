package project.jeongha.member.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.google.gson.Gson;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import project.jeongha.member.dao.MemberDaoImpl;
import project.jeongha.member.service.MemberServiceImpl;
import project.jeongha.member.vo.MemberVO;
import project.jeongha.member.vo.NaverLoginBO;
import project.jeongha.member.vo.Token;
import project.sungho.comember.service.ComemberService;
import project.sungho.cowork.service.CoworkService;
import project.sungho.interceptor.LoginCheck;

@Controller // 컨트롤러 어노테이션 컨트롤 마다 작성해주세요
@RequestMapping("/member/*") // 컨트롤러 맵핑 어노테이션 /패키지명/* 로 지정해주세요
public class MemberControllerImpl implements MemberController {

	@Autowired // 메모리할당 어노테이션 기존 자바 new로 선언하는 것들에 작성해주세요
	MemberServiceImpl service;

	@Autowired
	ComemberService comemberService;

	@Autowired
	MemberDaoImpl dao;

	@Autowired
	CoworkService coworkService;

	@Autowired
	BCryptPasswordEncoder passEncoder;
	
	@Autowired
	SqlSession sqlSession;

	// Naver LoginBO
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;

	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	// @Override
//	@ModelAttribute("serverTime") // 모델어트리뷰트 어노테이션은 이컨트롤러가 매핑어뎁터로 메소드를 실행하기전에 먼저 실행하는 메소드 입니다.
//	public String getServerTime(Locale locale) { // 이경우 jsp에 ${serverTime} 으로 현재시간을 리턴하는데
//													// 다른방법으로도 사용가능하니 사용하실려면 사용하세요
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		return dateFormat.format(date);
//	}

	// 회원가입페이지
	@Override
	// @GetMapping("/signup")
	public String signup() {
		System.out.println("회원가입페이지");
		return "/member/signup";
	}

	// 로그인 페이지
	@Override
	@GetMapping("/loginPage1")
	public String loginFrorm() {
		System.out.println("로그인페이지");
		return "/member/loginPage";
	}

	// 비밀번호 찾기 페이지
	@Override
	@GetMapping("/forgotPwd")
	public String findPw() {
		System.out.println("비밀번호 찾기 페이지");
		return "/member/forgotPwd";
	}

	//테스트중!
	@Override
	@GetMapping("/signupTest1")
	public String outMember() {
		System.out.println("회원탈퇴 찾기 페이지");
		return "/member/signupTest1";
	}

	// 마이페이지
	@Override
	@RequestMapping(value = "/mypage")
	public String mypage(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		Map<String, Object> member = new HashMap<String, Object>();

		member = (Map<String, Object>) session.getAttribute("member");

		List<Map> list = comemberService.searchList(member);

		request.setAttribute("coworklist", list);

		System.out.println("마이페이지");
		return "/member/mypage";
	}

	// ModelAndView
	// @PostMapping("memJoin") //위아래중 편한걸로 사용하세요 URL 대소문자 구분하니 주의해주세요
	// 회원가입
	@Override
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String memJoin(MemberVO memberVO, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes rttr) throws Exception { // 매게변수로 vo가 들어갔을경우 자동으로 변수이름에 맞는걸 set get 해줍니다.
		request.setCharacterEncoding("utf-8"); // 다른이름으로 지정하고 싶을 경우 ex : @ModelAttribute(변수이름) MeberVo memberVO
		// 복호화
		String inputPass = memberVO.getMem_Pwd();
		String mem_Pwd = passEncoder.encode(inputPass);

		Map<String, Object> joinMem = new HashMap<String, Object>();
		joinMem.put("mem_Id", memberVO.getMem_Id());
		joinMem.put("mem_Pwd", mem_Pwd);
		joinMem.put("mem_Name", memberVO.getMem_Name());

		// 회원가입
		rttr.addFlashAttribute("msg", "success");
		rttr.addFlashAttribute("mem_Name", joinMem.get("mem_Name"));
		service.memberJoin(joinMem, memberVO, response); // service에 memberRegister를 실행하는 부분
		return "redirect:/member/loginPage"; // 리턴타입엔 패키지명/jsp파일 로 작성하여주세요 view에서도 패키지/jsp로 관리해주세요
	}

	@Override
	// 회원 인증
	@RequestMapping(value = "/approvalMember", method = RequestMethod.POST)
	public void approvalMember(@ModelAttribute MemberVO member, HttpServletResponse response) throws Exception {
		service.approvalMember(member, response);
	}

	// naver Login
	@Override
	@RequestMapping(value = "/loginPage") // member/loginPage
	public String naverLogin(Model model, HttpSession session) {
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);

		// https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		// redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		// System.out.println("네이버:" + naverAuthUrl);

		// 네이버
		model.addAttribute("naverLoginUrl", naverAuthUrl);

		/* 생성한 인증 URL을 View로 전달 */
		return "/member/loginPage";
	}

	@Override
	@RequestMapping(value = "/signup") // member/loginPage
	public String naverSignup(Model model, HttpSession session) {
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);

		// https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		// redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		// System.out.println("네이버:" + naverAuthUrl);

		// 네이버
		model.addAttribute("naverLoginUrl", naverAuthUrl);

		/* 생성한 인증 URL을 View로 전달 */
		return "/member/signup";
	}

	// 로그인 테스트 컨트롤러->회원정보수정
	@Override
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView memLogin(MemberVO member, RedirectAttributes rAttr, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();

		Map<String, Object> memLogin = new HashMap<String, Object>();
		memLogin.put("mem_Id", member.getMem_Id());
		memLogin.put("mem_Pwd", member.getMem_Pwd());
		// 로그인로직
		// 담아서
		Map<String, Object> memberVO = service.login(memLogin, response);
		String kind = "03";
		// db에 복호화된 비밀번호를 매치시킴
		boolean passMatch = passEncoder.matches(member.getMem_Pwd(), (String) memberVO.get("mem_Pwd"));

		// 로그인시 세션에.. 로그인성공
		if (memberVO != null && passMatch) {
			
			System.out.println("로그인 성공(객체): " + memberVO);
			System.out.println("로그인 권환 ================================"+memberVO.get("mem_Kind"));
			
			sqlSession.update("logincheck.updatelogincheck", memberVO);
			if (memberVO.get("mem_Kind").equals(kind)) {
				HttpSession session = request.getSession();
				session.setAttribute("member", memberVO);
				mav.setViewName("redirect:/manager/main");
			}else {
			HttpSession session = request.getSession();
			session.setAttribute("member", memberVO);
			// jsp페이지에서 ${member.mem_Id}---->이런식으로 접근해야됨
			mav.setViewName("redirect:/main");
			}
			// 실패했을경우
		} else {

			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 틀렸습니다. 다시 입력해 주세요');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			// System.out.println("로그인 실패");
			// rAttr.addAttribute("result", "loginFailed");
			mav.setViewName("redirect:/member/loginPage");

		} // end if

		return mav;
	}

	// mypage 수정
	@RequestMapping(value = "/update_mypage", method = RequestMethod.POST)
	public String update_mypage(@ModelAttribute MemberVO memberVO, HttpSession session, RedirectAttributes rttr,
			HttpServletRequest request) throws Exception {
		// System.out.println("수정");
		// System.out.println(memberVO.getMem_Id());
		// System.out.println(memberVO.getMem_Name());
		Map<String, Object> member00 = new HashMap<String, Object>();
		member00 = (Map<String, Object>) session.getAttribute("member");
		System.out.println("세션에서 가져온거member00 :" + member00);
		member00.put("mem_Id", memberVO.getMem_Id());
		member00.put("mem_Name", memberVO.getMem_Name());
		service.updateMypage(member00);
		// 일회성이라 리프레시할 경우 데이터가 소멸한다.
//		List<Map> list = comemberService.searchList(member00);
//
//		request.setAttribute("coworklist", list);

		rttr.addFlashAttribute("msg", "success");
		session.setAttribute("member", member00);
		return "redirect:/member/mypage";
	}

	// 비밀번호 변경
	@RequestMapping(value = "/update_pw", method = RequestMethod.POST)
	public String update_pw(@ModelAttribute MemberVO memberVO, @RequestParam("old_pw") String old_pw,
			HttpSession session, HttpServletResponse response, RedirectAttributes rttr) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 기존비밀번호
		String inputPass = old_pw;
		String oldPwd = passEncoder.encode(inputPass);
		// 새로운 비밀번호
		String mem_Pwd = passEncoder.encode(memberVO.getMem_Pwd());
		System.out.println("old_pw: " + old_pw);
		System.out.println("oldPwd: " + oldPwd);

		Map<String, Object> memLogin = new HashMap<String, Object>();
		// 기존비밀번호 및 아이디
		memLogin.put("mem_Id", memberVO.getMem_Id());
		memLogin.put("mem_Pwd", oldPwd);
		System.out.println("memLogin: " + memLogin);

		Map<String, Object> member = service.login(memLogin, response);
		System.out.println("member.get(mem_Pwd): " + member.get("mem_pwd"));
		// 기존비밀번호, 디비 비밀번호 비교
		boolean passMatch = passEncoder.matches(old_pw, (String) member.get("mem_Pwd"));
		// 비교
		if (member != null && passMatch) {
			System.out.println("비번 업뎃");
			// 비번 업뎃
			memLogin.put("mem_Id", memberVO.getMem_Id());
			memLogin.put("mem_Pwd", mem_Pwd);
			service.update_pw(memLogin, response);

		} else {
			out.println("<script>");
			out.println("alert('기존 비밀번호가 다릅니다. 다시입력해 주세요');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			System.out.println("else");
		}
		return "redirect:/member/mypage";
	}

	// 아이디 중복 검사(AJAX)
	@RequestMapping(value = "/check_id", method = RequestMethod.GET)
	@ResponseBody // 리스폰스바디는 매소드가 스트링이거나 타입이 있을때 ajax로통신할 경우 꼭써야함그렇게 하지 않으면 뷰 리졸버가 반응을함.
	public int check_id(@RequestParam("mem_Id") String mem_Id) throws Exception {
		System.out.println("아작스");
		System.out.println(mem_Id);
		// 아이디가 있는지 없는지 있으면 1 없으면 0
		int result = service.check_id(mem_Id);

		System.out.println("controller result:" + result);
		return result;
	}

	// 비밀번호 찾기
	@Override
	@RequestMapping(value = "/find_pw", method = RequestMethod.POST)
	public void find_pw(Map<String, Object> member, MemberVO memberVO, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// 이메일 보냄.
		service.find_pw(response, memberVO, member);
	}

	// 로그아웃
	@RequestMapping(value = "/logout1", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) throws IOException {
		System.out.println("여기는 logout");
		session.invalidate();
		return "redirect:/member/index1";
	}

	// 회원 탈퇴
	@Override
	@RequestMapping(value = "delete_Member")
	public String memberDelete(@ModelAttribute MemberVO memberVO, HttpServletResponse response, HttpSession session,
			RedirectAttributes rttr) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> memLogin = new HashMap<String, Object>();
		memLogin.put("mem_Id", memberVO.getMem_Id());
		memLogin.put("mem_Pwd", memberVO.getMem_Pwd());
		// 로그인로직
		Map<String, Object> member = service.login(memLogin, response);

		// db에 복호화된 비밀번호를 매치시킴
		boolean passMatch = passEncoder.matches(memberVO.getMem_Pwd(), (String) member.get("mem_Pwd"));
		if (member != null && passMatch) {
			System.out.println("Id: " + memberVO.getMem_Id());
			service.memberDelete(member, response);
			session.invalidate();
			// 탈퇴
			rttr.addFlashAttribute("msg", "success");

			// 세션초기화
		} else {
			out.println("<script>");
			out.println("alert('기존 비밀번호가 다릅니다. 다시입력해 주세요');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			return "/member/mypage";
		}

		return "redirect:/";
	}

	// 이미지 저장
	@Override
	@RequestMapping(value = "/saveImage")
	public String saveImage(MemberVO memberVO, HttpServletResponse request, HttpServletResponse response,
			HttpSession session) throws Exception {
		// 이미지 저장 컨트롤러
		System.out.println(memberVO.getMem_Id());
		System.out.println(memberVO.getMem_File().getBytes());
		Map<String, Object> member00 = new HashMap<String, Object>();
		member00 = (Map<String, Object>) session.getAttribute("member");
		System.out.println("세션에서 가져온거member00 :" + member00);
		try {

			// Map<String, Object> member = new HashMap<String, Object>();
			member00.put("mem_File", memberVO.getMem_File().getBytes());

			System.out.println("ImgSave Controller: " + member00);
			dao.saveImage(member00);
			// 세션 문제 추가...변경 후 사진 파일 추가 해서 세션에,,,
			session.setAttribute("member", member00);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/member/mypage";
	}

	// 프로필 사진가져옴 /getByteImage?mem_Id=
	@RequestMapping(value = "/getByteImage", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getByteImage(@RequestParam("mem_Id") String mem_Id) {
		byte[]imageContent=null;
		final HttpHeaders headers = new HttpHeaders();
		
		try {
			// System.out.println("그림파일 가져오기");
			Map<String, Object> img = dao.getByteImage(mem_Id);
			// blob컬럼명 img.get("mem_File")
			imageContent = (byte[]) img.get("mem_File");
			// System.out.println("getImg: "+img.get("mem_File"));
			
			headers.setContentType(MediaType.IMAGE_PNG);
			

		}catch(NullPointerException e) {
			
		}
		return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	}

	// 네이버 로그인 성공시 callback호출 메소드
	@Override
	@RequestMapping(value = "/naverCallback", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, String code, String state, HttpSession session, HttpServletResponse response)
			throws IOException, ParseException, Exception {
		System.out.println("여기는 callback");
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		// 1. 로그인 사용자 정보를 읽어온다.
		apiResult = naverLoginBO.getUserProfile(oauthToken); // String형식의 json데이터
		System.out.println(apiResult);
		/**
		 * apiResult json 구조 response: {"resultcode":"00","message":"success",
		 * "response":{ "id":"45166773", "nickname":"Jeongha An", "profile_image":
		 * "https:\/\/phinf.pstatic.net\/contact\/20190213_294\/15499850679839Oki2_JPEG\/image.jpg",
		 * "age":"30-39", "gender":"M", "email": "asd_7088@naver.com",
		 * "name":"\uc548\uc815\ud558", "birthday":"03-26"}}
		 **/
		// 2. String형식인 apiResult를 json형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		// 3. 데이터 파싱
		// Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject) jsonObj.get("response");
		// response의 nickname값 파싱
		String mem_Name = (String) response_obj.get("nickname");
		String mem_Id = (String) response_obj.get("email");
		String mem_ImgName = (String) response_obj.get("profile_image");
		System.out.println(mem_Id);
		// 4.파싱 닉네임 세션으로 저장

		Map<String, Object> member = new HashMap<String, Object>();
		member.put("mem_Id", mem_Id);
		member.put("mem_Name", mem_Name);
		// member.put("mem_ImgName",mem_ImgName);
		member.put("mem_LoginApi", "naver");
		// 아이디가 없다면.
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@" + member);
		int result = service.check_id(mem_Id);
		if (result != 1) {
			// System.out.println("아이디 없음");
			service.memberJoinApi(member);
			session.setAttribute("member", member);
			sqlSession.update("logincheck.updatelogincheck", member);
			return "redirect:/main";

		} else {

//				System.out.println("아이디가 있음");
			// 사진, 업데이트 정보가져오기 -> 협업공간에서는 필요없을것 같음 새로운 사진 쓸수있게 하기
			Map<String, Object> memberVO = service.login(member, response);
			// 로그인 정보 가져오기.
			System.out.println(member);
			session.setAttribute("member", memberVO);
			// model.addAttribute("result", apiResult);
			sqlSession.update("logincheck.updatelogincheck", member);
			return "redirect:/main";
		}
	}

	// 구글 Callback호출 메소드 http://localhost:9092/member/googleLogin -> google api등록 필요
	@RequestMapping(value = "/googleLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public String googleCallback(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws IOException, Exception {
		System.out.println("여기는 googleCallback");

		String code = request.getParameter("code");
		String query = "code=" + code;
		// 구글 로그인 인증키
		query += "&client_id=" + "332997436138-3g0cj5k952gddaro03grkth547udnh41.apps.googleusercontent.com";
		// 구글 로그인 인증키 비밀번호
		query += "&client_secret=" + "D930_U4ICILL756vxBavV1W8";
		// 리다렉트 할 주소
		query += "&redirect_uri=" + "http://collawt.site/member/googleLogin";
		query += "&grant_type=authorization_code";

		// 토큰권한 google에 요청
		String tokenJson = getHttpConnection("https://accounts.google.com/o/oauth2/token", query);
		System.out.println("google에 요청한 데이터: " + tokenJson.toString());
		// 토큰 제이슨 형태로 바꾸기
		Gson gson = new Gson();
		Token token = gson.fromJson(tokenJson, Token.class);

		String ret = getHttpConnection(
				"https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + token.getAccess_token());
		System.out.println("ret: " + ret);
		/*
		 * 구글에서 보내준 개인정보.. 이름없음 ret: { "id": "108033630427505881754", "email":
		 * "ajh7893@gmail.com", "verified_email": true, "picture":
		 * "https://lh3.googleusercontent.com/a-/AOh14Gimu7yC6xpwV-dtxLDrfJeGJwdwUt8EeBIiCDS6P2g"
		 */
		// json -> obj
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(ret);
		JSONObject jsonObj = (JSONObject) obj;
		System.out.println("obj: " + obj);

		String mem_Id = (String) jsonObj.get("email");
		String mem_ImgName = (String) jsonObj.get("picture");

		System.out.println("추출된ID: " + mem_Id);
		System.out.println(mem_ImgName);

		Map<String, Object> member = new HashMap<String, Object>();
		member.put("mem_Id", mem_Id);
		// 구글이 이름을 제공하지 않아 아이디로 이름 대체
		member.put("mem_Name", mem_Id);
		member.put("mem_ImgName", mem_ImgName);
		member.put("mem_LoginApi", "google");
		System.out.println("member: " + member);

		int result = service.check_id(mem_Id);
		if (result != 1) {
			System.out.println("아이디 없음");
			service.memberJoinApiGoogle(member);
			session.setAttribute("member", member);
			

			sqlSession.update("logincheck.updatelogincheck", member);
			return "redirect:/main";
		} else {

			System.out.println("아이디가 있음");
			Map<String, Object> memberVO = service.login(member, response);
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + memberVO);
			session.setAttribute("member", memberVO);
			sqlSession.update("logincheck.updatelogincheck", member);

		}
		return "redirect:/main";
	}

	// google OAuth인증함수
	@Override
	public String getHttpConnection(String uri, String param) throws ServletException, IOException {
		URL url = new URL(uri);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		try (OutputStream stream = conn.getOutputStream()) {
			try (BufferedWriter wd = new BufferedWriter(new OutputStreamWriter(stream))) {
				wd.write(param);
			}
		}
		int responseCode = conn.getResponseCode();
		System.out.println("response code: " + responseCode);

		String line;
		StringBuffer buffer = new StringBuffer();
		try (InputStream stream = conn.getInputStream()) {
			try (BufferedReader rd = new BufferedReader(new InputStreamReader(stream))) {
				while ((line = rd.readLine()) != null) {
					buffer.append(line);
					buffer.append('\r');
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	// google OAuth인증함수
	@Override
	public String getHttpConnection(String uri) throws ServletException, IOException {
		URL url = new URL(uri);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		int responseCode = conn.getResponseCode();
		System.out.println("response: " + responseCode);

		String line;
		StringBuffer buffer = new StringBuffer();
		try (InputStream stream = conn.getInputStream()) {
			try (BufferedReader rd = new BufferedReader(new InputStreamReader(stream))) {
				while ((line = rd.readLine()) != null) {
					buffer.append(line);
					buffer.append('\r');
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

}