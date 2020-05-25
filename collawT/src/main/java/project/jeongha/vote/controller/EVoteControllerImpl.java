package project.jeongha.vote.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;

import project.jeongha.vote.dao.EVoteDAO;
import project.jeongha.vote.service.EVoteService;
import project.jeongha.vote.vo.Criteria;
import project.jeongha.vote.vo.PageMaker;
import project.jeongha.vote.vo.VoteVO;
import project.sungho.comember.service.ComemberService;
import project.sungho.cowork.service.CoworkService;

@Controller

@RequestMapping("/project/vote/*")
public class EVoteControllerImpl implements EVoteController {
	Date time = new Date();
	SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMdd");
	//
	@Inject
	EVoteService evoteService;

	@Inject
	EVoteDAO evoteDAO;

	@Inject
	CoworkService coworkService;

	@Inject
	ComemberService comemberService;

	// 글 목록 조회 페이징
	@Override
	@GetMapping("/list")
	public ModelAndView searchList(Criteria cri, String c_Id, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		List<Map> list = evoteService.searchList(cri);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(evoteService.listCount(c_Id));

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("c_Id", c_Id);
		Map<String, Object> pjt = coworkService.searchMain(searchMap);
		System.out.println("list:"+list);
		
		
		
		//Map<String, Object> vote = evoteService.voteReadList(searchMap);
		
		//날짜가 지나면 완료하기
		String today = format1.format(time);
		int todayInt = Integer.parseInt(today);
		System.out.println("today: "+todayInt);
		for (int i = 0; i < list.size(); i++) {
			String re = (String)list.get(i).get("v_End");
			String v_Num = (String)list.get(i).get("v_Num");
			System.out.println("sdfasdfa"+v_Num);
			if(re!=null) {
			String re_a = re.replace("-", "");
			int v_End = Integer.parseInt(re_a);
			if (todayInt > v_End) {
				Map<String, Object> searchMap1 = new HashMap<String, Object>();
				searchMap1.put("v_Num", v_Num);
				evoteService.updateVs_Num(searchMap1);
			}
			}
		}
		List<Map> list1 = evoteService.searchList(cri);
		ModelAndView mav = new ModelAndView("vote/voteList");
		mav.addObject("voteList", list1);
		mav.addObject("pageMaker", pageMaker);
		mav.addObject("pjt", pjt);

		return mav;
	}

	// 글 쓰기 DB에 넣기
	@Override
	@PostMapping("/insert")
	public String issueInsert(VoteVO voteVO, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String, Object> member = new HashMap<String, Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String mem_Name =(String)member.get("mem_Name");
		String mem_Id = (String) member.get("mem_Id");
		String c_Id = request.getParameter("c_Id");
		String v_Name = voteVO.getV_Name();
		String v_Content = voteVO.getV_Content();
		String v_Start = voteVO.getV_Start();
		String v_End = voteVO.getV_End();
		String vs_Num = voteVO.getVs_Num();
		String v_Subject = voteVO.getV_Subject();
		String v_Count = voteVO.getV_Count();
		String v_Show = request.getParameter("v_Show");
		
		
		Map<String, Object> cmap = new HashMap<String, Object>();
		cmap.put("mem_Id", mem_Id);
		cmap.put("c_Id", c_Id);
		cmap.put("v_Name", v_Name);
		cmap.put("v_Content", v_Content);
		cmap.put("v_Start", v_Start);
		cmap.put("v_End", v_End);
		cmap.put("vs_Num", vs_Num);
		cmap.put("v_Subject", v_Subject);
		cmap.put("v_Count", v_Count);
		cmap.put("mem_Name", mem_Name);
		cmap.put("v_Show",v_Show);
		evoteService.voteInsert(cmap);
		
		
		
		// v_Num가져오기
		String v_Num = (String) cmap.get("v_Num");
		// 보기항목 넣기
		String vd_Num[] = request.getParameterValues("vd_Num");
		String vd_Content[] = request.getParameterValues("vd_Content");
		// voted 테이블에 들어갈 정보
		
		Map<String, Object> vd_Info = new HashMap<String, Object>();
		for (int i = 0; i < vd_Num.length; i++) {
			vd_Info.put("v_Num", v_Num);
			vd_Info.put("vd_Num", vd_Num[i]);
			vd_Info.put("vd_Content", vd_Content[i]);
			vd_Info.put("c_Id", c_Id);
			evoteService.votedInsert(vd_Info);

		}


		return "redirect:/project/vote/read?c_Id="+c_Id+"&v_Num=" + v_Num;

	}

	// 글 쓰기 화면
	@Override
	@GetMapping("/insert")
	public ModelAndView voteInsert(String c_Id, HttpSession session) {


		ModelAndView mav = new ModelAndView("/vote/voteInsert");

		mav.addObject("c_Id", c_Id);
		return mav;

	}

	// 개별 글 조회
	@Override
	@GetMapping("/read")
	public ModelAndView voteRead(String c_Id,String v_Num, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//관리자 아이디 가져오기
		Map<String, Object> managerMap = new HashMap<String, Object>();
		managerMap.put("c_Id", c_Id);
		Map<String, Object> pjt = coworkService.searchMain(managerMap);
		
		//오늘날짜
		String today = format1.format(time);
		int todayInt = Integer.parseInt(today);
		
		//System.out.println("read c_Id: "+c_Id);
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("v_Num", v_Num);
		searchMap.put("c_Id", c_Id);
		
		Map<String, Object> vote = evoteService.voteRead(searchMap);
		List<Map> voted = evoteService.votedRead(v_Num);
		//디비에서 마감일 가져오기
		String  a = (String) vote.get("v_End");
		if(a!=null) {
		String re_a = a.replace("-", "");
		System.out.println("1231231"+re_a);
		int v_End = Integer.parseInt(re_a);
		
		//오늘 날짜 비교해서 지나면 완료하기...
		if(todayInt > v_End) {
			
			evoteService.updateVs_Num(searchMap);
		}
		}
		String vs_Num = (String)vote.get("vs_Num");
		System.out.println("vs_Num: "+vs_Num);
		System.out.println("vote: "+vote);
		Map<String, Object> vote2 = evoteService.voteRead(searchMap);
		ModelAndView mav = new ModelAndView("/vote/voteRead");
		
		//투표자들 명단
		List<Map>voterlist = evoteService.voteCount(searchMap);
		//투표 count
		List<Map> voteTotal = evoteService.voteTotal(searchMap);
		
		//투표 리스트(항목) , 투표 총계 
		for (int i = 0; i < voted.size(); i++) {
			for (int j = 0; j < voteTotal.size() ; j++) {
				
				//vd_Num이 같다면 ex 항목 1 == 토탈리스트 항목
				if(voted.get(i).get("vd_Num").equals(voteTotal.get(j).get("vd_Num"))) {
					//List v_Num, c_Id, vd_Content, vd_Num
					//Total vd_Num, v_Count
					voted.get(i).put("v_Count", voteTotal.get(j).get("v_Count"));
				}
				
			}
			
		}
		
//		for (int i = 0; i < voterlist.size(); i++) {
//			for (int j = 0; j < voteTotal.size(); j++) {
//				//Total vd_Num, v_Count
//				//voterlist v_Num, c_Id , vd_Num , mem_Id, mem_Name
//				if(voterlist.get(i).get("vd_Num").equals(voteTotal.get(j).get("vd_Num"))) {
//						
//						voteTotal.get(i).put("mem_Id", voterlist.get(j).get("mem_Id"));
//						voteTotal.get(i).put("mem_Name", voterlist.get(j).get("mem_Name"));
//					
//					
//					
//				}
//				
//			}
//			
//		}
		System.out.println("@@@@@@@@@@@"+vote2);
		
		
		
		
		mav.addObject("voteRead", vote2);
		mav.addObject("votedRead", voted);
		mav.addObject("voterList",voterlist);
		mav.addObject("voteTotal",voteTotal);
		mav.addObject("pjt", pjt);

		// mav.addObject("file", list);
		// mav.addObject("chargerList", chargerList);
		return mav;
	}


	// 게시글 삭제
	@Override
	@GetMapping("/delete")
	public String voteDelete(String c_Id, String v_Num, VoteVO voteVO) throws Exception {

		evoteService.votereplyDelete(voteVO.getV_Num());
		evoteService.votedDelete(voteVO.getV_Num());
		evoteService.voteDelete(voteVO.getV_Num());

		return "redirect:/project/vote/list?c_Id=" + c_Id;
	}


	// 게시글 수정 페이지로 이동 GET
	@Override
	@GetMapping("/update")
	public String voteUpdate(String c_Id, String v_Num, VoteVO voteVO, Model model) throws Exception {
		System.out.println("================UpdateController===============");
		System.out.println("c_Id: " + c_Id);
		System.out.println("c" + v_Num);
		
		System.out.println("===============================================");
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("v_Num", v_Num);
		searchMap.put("c_Id", c_Id);
		
		Map<String, Object> vote = evoteService.voteRead(searchMap);
		
		List<Map> voted = evoteService.votedRead(v_Num);

		//model.addAttribute("voteUpdate", evoteService.voteRead(voteVO.getV_Num()));
		model.addAttribute("voteUpdate", evoteService.voteRead(searchMap));
		model.addAttribute("c_Id", c_Id);
		model.addAttribute("voteRead", vote);
		model.addAttribute("votedRead", voted);
		

		return "/vote/voteUpdate";
	}


	// 이슈 담당자 목록 가져오기
	@GetMapping("/chargerList")
	@ResponseBody
	public List<Map> chargerList(@RequestParam("i_Num") String i_Num) throws Exception {

		List<Map> chargerList = evoteService.chargerRead(i_Num);

		return chargerList;
	}


	// 투표 수정 
	@Override
	@PostMapping("/update")
	public String voteUpdate(VoteVO voteVO, HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
		// evoteService.voteUpdate(voteVO);

		Map<String, Object> member = new HashMap<String, Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String mem_Name =(String)member.get("mem_Name");
		String mem_Id = (String) member.get("mem_Id");
		String c_Id = request.getParameter("c_Id");
		String v_Name = voteVO.getV_Name();
		String v_Content = voteVO.getV_Content();
		String v_Start = voteVO.getV_Start();
		String v_End = voteVO.getV_End();
		String vs_Num = voteVO.getVs_Num();
		String v_Subject = voteVO.getV_Subject();
		String v_Count = voteVO.getV_Count();
		// v_Num가져오기
		String v_Num = (String)request.getParameter("v_Num");

		System.out.println("@@@@@@@@@@@@@@@@CCCCCCCCCCCCC"+v_Num);
		
		Map<String, Object> cmap = new HashMap<String, Object>();
		//cmap.put("mem_Id", mem_Id);
		cmap.put("v_Num", v_Num);
		cmap.put("v_Name", v_Name);
		cmap.put("v_Content", v_Content);
		cmap.put("v_Subject", v_Subject);
		cmap.put("v_Start", v_Start);
		cmap.put("v_End", v_End);
		cmap.put("vs_Num", vs_Num);
		//cmap.put("v_Count", v_Count);
		//cmap.put("mem_Name", mem_Name);
		evoteService.voteUpdate(cmap);
		
		
		// 보기항목 넣기
		String vd_Num[] = request.getParameterValues("vd_Num");
		String vd_Content[] = request.getParameterValues("vd_Content");
		// voted 테이블에 들어갈 정보
			
		Map<String, Object> vd_Info = new HashMap<String, Object>();
		for (int i = 0; i < vd_Num.length; i++) {
			vd_Info.put("v_Num", v_Num);
			vd_Info.put("vd_Num", vd_Num[i]);
			vd_Info.put("vd_Content", vd_Content[i]);
			vd_Info.put("c_Id", c_Id);
			evoteService.votedUpdate(vd_Info);

				}
		
		
		
		return	"redirect:/project/vote/read?c_Id="+c_Id+"&v_Num=" + v_Num;
		
	}


	// 투표하기
	@Override
	@GetMapping("/insertVoter")
	public ModelAndView voter(String v_Num, String c_Id, String vd_Num, VoteVO voteVO, HttpSession session, Model model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("v_Num", v_Num);
		searchMap.put("c_Id", c_Id);
		
		Map<String, Object> vote = evoteService.voteRead(searchMap);
		List<Map> voted = evoteService.votedRead(v_Num);

		Map<String, Object> member = new HashMap<String, Object>();
		member = (Map<String, Object>) session.getAttribute("member");

		String mem_Id = (String) member.get("mem_Id");

		Map<String, Object> cmap = new HashMap<String, Object>();
		cmap.put("mem_Id", mem_Id);///
		cmap.put("c_Id", c_Id);///
		cmap.put("v_Num", v_Num);///
		cmap.put("vd_Num", vd_Num);///
		 
		ModelAndView mav = new ModelAndView("redirect:/project/vote/read?c_Id="+c_Id+"&v_Num=" + v_Num);

		// 투표자 중복인이 아닌지 체크..아니면  넣음 서비스에서 반
		evoteService.voterInsert(cmap, response, voteVO);

		// 보트카운트
		String vd_arr[] = request.getParameterValues("getVsNum");
		Map<String, Object> voteCount = new HashMap<String, Object>();
		Map<String, Object> voteCount0 = new HashMap<String, Object>();
		ArrayList<String> arrayList = new ArrayList<>();
		
		Map<String,Object> searchC_Id = new HashMap<String, Object>();
		searchC_Id.put("c_Id", c_Id);
		Map<String,Object> coworker= evoteService.countCowork(searchC_Id);

		
		
		for (int i = 0; i < vd_arr.length; i++) {
			voteCount.put("v_Num", v_Num);
			voteCount.put("vd_Num", vd_arr[i]);
	
		}
		
		mav.addObject("voteRead", vote);
		mav.addObject("votedRead", voted);
		
		return mav;
	}

}
