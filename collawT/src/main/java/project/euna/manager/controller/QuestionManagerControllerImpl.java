package project.euna.manager.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import project.euna.issue.vo.IssueVO;
import project.euna.manager.service.QuestionManagerService;
import project.jeongha.question.vo.QuestionVO;




@Controller
@RequestMapping("/manager/question/*")
public class QuestionManagerControllerImpl implements QuestionManagerController {
	@Autowired 
	QuestionManagerService managerService;
	
	@Override
	@RequestMapping(value = "/main", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView searchInit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView("/manager/questioninfo");
		return mav;
	} 
	
	@Override
	@RequestMapping(value = "/searchList", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map searchList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String, Object> searchMap = new HashMap<String, Object>(); // 검색조건
		Map<String, Object> resultMap = new HashMap<String, Object>(); // 조회결과
		
		
		searchMap.put("q_Email", request.getParameter("q_Email"));
		searchMap.put("q_Subject", request.getParameter("q_Subject"));
		searchMap.put("q_Content", request.getParameter("q_Content"));

		// 검색조건설정
		//데이터 조회
		List<Map> data = managerService.searchList(searchMap);
        resultMap.put("Data", data);
        
        for (int i = 0; i < data.size(); i++) {
            data.get(i).put("link", "바로가기");
         }
        
        return resultMap;
	}
	
	
	@Override
	@RequestMapping(value = "/saveData", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map saveData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String, String[]> dataMap = new HashMap<String, String[]>(); // 저장할Daa
		Map<String, Object> resultMap = new HashMap<String, Object>(); // 처리결과
		
		// 저장 Data 추출하기
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String[] values = request.getParameterValues(name);
			dataMap.put(name, values);
		}
		
		Map<String, String> result = new HashMap<String, String>();
		try {
			managerService.saveData(dataMap);	
			result.put("Code","0");
			result.put("Message","저장되었습니다");
		}catch(Exception e) {
			result.put("Code","-1");
			result.put("Message","저장에 실패하였습니다");
			e.printStackTrace();
		}
		
		resultMap.put("Result", result);         
        return resultMap;
	}
	
	//문의 글 조회
	@Override
	@GetMapping("/read")
	public ModelAndView questionRead(String q_Num, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Map<String, Object> member = new HashMap<String,Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String mem_Id = (String) member.get("mem_Id");
		
		
		Map<String, Object> questionRead = managerService.questionRead(q_Num);
		List<Map> beforeAnswerList = managerService.beforeAnswerList(q_Num);
		
		ModelAndView mav = new ModelAndView("manager/questionRead");

		mav.addObject("questionRead", questionRead);
		mav.addObject("beforeAnswerList", beforeAnswerList);
		return mav;
	}
	
	//답변 쓰기 DB에 넣기
	@Override
	@PostMapping("/insert")
	public String answerInsert(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		String q_Num = request.getParameter("q_Num");
		String as_Subject = request.getParameter("as_Subject");
		String as_Content = request.getParameter("as_Content");
		String q_Email = request.getParameter("q_Email");
		
		Map<String, Object> insertmap = new HashMap<String,Object>();
		insertmap.put("q_Num", q_Num);
		insertmap.put("as_Subject", as_Subject);
		insertmap.put("as_Content", as_Content);

		managerService.answerInsert(insertmap);
		
		Map<String, Object> updatemap = new HashMap<String,Object>();
		updatemap.put("q_Num", q_Num);
		updatemap.put("q_Answer", "확인중");
		
		managerService.questionStatusUpdate(updatemap);
		
		
		Map<String, Object> mailmap = new HashMap<String,Object>();
		mailmap.put("q_Num", q_Num);
		mailmap.put("as_Subject", as_Subject);
		mailmap.put("as_Content", as_Content);
		mailmap.put("q_Email", q_Email);
		
		sendEmail(mailmap);
		
	
		return "redirect:/manager/question/read?q_Num="+q_Num;
	
	}
	
	@Override
	public void sendEmail(Map<String, Object> mailmap) throws Exception {
		String q_Num = (String) mailmap.get("q_Num");
		String as_Subject = (String) mailmap.get("as_Subject");
		String as_Content = (String) mailmap.get("as_Content");
		String q_Email = (String) mailmap.get("q_Email");
		
		// Mail Server 설정
				String charSet = "utf-8";
				String hostSMTP = "smtp.naver.com";

				// google 아이디, 비밀번호
				String hostSMTPid = "collawt";
				String hostSMTPpwd = "zhffkdnxl%!00";

				// 보내는 사람 EMail,이름, 제목, 내용
				String fromEmail = "collawt@naver.com";
				String fromName = "CollawT 관리자";
				String subject = "";
				String msg = "";

				//회원가입시 인증메일
					subject = "안녕하세요 :) CollawT 문의에 대한 답변 메일입니다.";
					msg += "<html>";
					msg += "<head>";
					msg += "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css\"/>";
					msg += "</head>";
					msg += "<body>";
					msg += "<div style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 540px; height: 600px; border-top: 4px solid #02b875; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">\n" + 
							"    <h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">\n" + 
							"        <span style=\"font-size: 15px; margin: 0 0 10px 3px;\">콜라우티</span><br />\n" + 
							"        <span style=\"color: #02b875;\">문의</span> 메일입니다.\n" + 
							"    </h1>\n";
					
					msg += 			""+
							"    <p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">\n"+
							"안녕하세요 :) CollawT입니다."+
							"고객님께서 문의 주신 내용에 대한 답변입니다.<br><br>";
					msg += 	as_Content;
					msg+=	"    </p>\n" + 
							"    <div style=\"border-top: 1px solid #DDD; padding: 5px;\">\n" + 
							"        <p style=\"font-size: 13px; line-height: 21px; color: #555;\">\n" + 
							"        </p>\n" + 
							"    </div>\n" + 
							"</div>";
					msg += "</body>";
					msg += "</html>";
		String mail = q_Email;
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setHostName(hostSMTP);
			// google smtp 포트번호
			email.setTLS(true);
			email.setSmtpPort(587);

			// 메일 아이디 비밀번호
			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			// 받는사람
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
	}
	
	//문의상태 변경
	@Override
	@GetMapping("/update")
	public String questionStatusUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String q_Num = request.getParameter("q_Num");
		String q_Answer = request.getParameter("q_Answer");


		Map<String, Object> updatemap = new HashMap<String,Object>();
		updatemap.put("q_Num", q_Num);
		updatemap.put("q_Answer", q_Answer);
		
		managerService.questionStatusUpdate(updatemap);

		
		//수정한 게시물로 리턴
		return "redirect:/manager/question/read?q_Num="+q_Num;
	}
	
}
