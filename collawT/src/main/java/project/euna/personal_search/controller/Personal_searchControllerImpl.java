package project.euna.personal_search.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import project.euna.appendix.service.AppendixService;
import project.euna.issue.service.IssueServiceImpl;
import project.euna.personal.service.PersonalService;
import project.euna.personal.service.Personal_appendixService;
import project.euna.personal.service.Personal_memoService;
import project.euna.personal_search.service.Personal_searchService;
import project.euna.personal_search.vo.Criteria;
import project.euna.personal_search.vo.PageMaker;
import project.euna.reply.service.ReplyService;
import project.euna.whole_search.service.Whole_searchService;
import project.euna.whole_search.vo.fileCriteria;
import project.euna.whole_search.vo.filePageMaker;
import project.jeongha.reply.service.VoteReplyService;
import project.jeongha.vote.service.EVoteService;

@Controller

@RequestMapping("/personal/search/*")
public class Personal_searchControllerImpl implements Personal_searchController {
	
	//
	@Inject
	Personal_searchService personal_searchService;
	
	@Inject
	Whole_searchService whole_searchService;
	
	@Inject
	IssueServiceImpl issueService;
	
	@Inject
	AppendixService appendixService;
	
	@Inject
	EVoteService evoteService;
	
	@Inject
	Personal_appendixService personal_appendixService;
	
	@Inject
	PersonalService personalService;
	
	@Inject
	ReplyService replyService;
	
	@Inject
	VoteReplyService voteReplyService;
	
	@Inject
	Personal_memoService personal_memoService;

	
	//글 목록 조회 페이징
	@Override
	@GetMapping("/myBoardlist")
	public ModelAndView myBoardlist(Criteria cri, String mem_Id, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		Map<String, Object> member = new HashMap<String,Object>();
//		member = (Map<String, Object>) session.getAttribute("member");
//		mem_Id = (String) member.get("mem_Id");
				
		List<Map> list = personal_searchService.myBoardlist(cri);
		PageMaker pageMaker = new PageMaker();
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(personal_searchService.myBoardlistCount(mem_Id));
		
	
		ModelAndView mav = new ModelAndView("personal/myBoardlist");
		mav.addObject("myBoardlist", list);
		mav.addObject("pageMaker", pageMaker);		
		
		return mav;
		
	}

	

	//이슈 게시글 삭제
	@Override
	@GetMapping("/issuedelete")
	public String issueDelete(String i_Num, HttpSession session) throws Exception{
		Map<String, Object> member = new HashMap<String,Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String mem_Id = (String) member.get("mem_Id");
		
		appendixService.fileDelete(i_Num);
		issueService.chargerDelete(i_Num);
		issueService.replyDelete(i_Num);
		issueService.issueDelete(i_Num);
	
		
		
		return "redirect:/personal/search/myBoardlist?mem_Id="+mem_Id;
	}
	
	//투표 게시글 삭제
	@Override
	@GetMapping("/votedelete")
	public String voteDelete(String v_Num, HttpSession session) throws Exception{
		Map<String, Object> member = new HashMap<String,Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String mem_Id = (String) member.get("mem_Id");
		
		evoteService.votereplyDelete(v_Num);
		evoteService.votedDelete(v_Num);
		evoteService.voteDelete(v_Num);
	
		
		
		return "redirect:/personal/search/myBoardlist?mem_Id="+mem_Id;
	}
	
	//내 이슈 게시글 삭제
	@Override
	@GetMapping("/personaldelete")
	public String personaldelete(String p_Num, HttpSession session) throws Exception{
		Map<String, Object> member = new HashMap<String,Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String mem_Id = (String) member.get("mem_Id");

		personal_appendixService.fileDelete(p_Num);
		personalService.personalmemoDelete(p_Num);
		personalService.personalDelete(p_Num);

	
		return "redirect:/personal/search/myBoardlist?mem_Id="+mem_Id;
	}
	
	
	//댓글 목록 조회 페이징
	@Override
	@GetMapping("/myReplylist")
	public ModelAndView myReplylist(Criteria cri, String mem_Id, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		Map<String, Object> member = new HashMap<String,Object>();
//		member = (Map<String, Object>) session.getAttribute("member");
//		mem_Id = (String) member.get("mem_Id");
				
		List<Map> list = personal_searchService.myReplylist(cri);
		PageMaker pageMaker = new PageMaker();
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(personal_searchService.myReplylistCount(mem_Id));
		
	
		ModelAndView mav = new ModelAndView("personal/myReplylist");
		mav.addObject("myReplylist", list);
		mav.addObject("pageMaker", pageMaker);		
		
		return mav;
		
	}
	
	//이슈댓글 삭제
	@Override
	@GetMapping("/issuereplydelete")
	public String issuereplyDelete(String r_Num, HttpSession session) throws Exception{
		Map<String, Object> member = new HashMap<String,Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String mem_Id = (String) member.get("mem_Id");
		
		replyService.replyDelete(r_Num);
	
		
		
		return "redirect:/personal/search/myReplylist?mem_Id="+mem_Id;
	}
	
	//투표 댓글 삭제
	@Override
	@GetMapping("/votereplydelete")
	public String votereplyDelete(String vr_Num, HttpSession session) throws Exception{
		Map<String, Object> member = new HashMap<String,Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String mem_Id = (String) member.get("mem_Id");
		
		voteReplyService.voteReplyDelete(vr_Num);
	
		
		return "redirect:/personal/search/myReplylist?mem_Id="+mem_Id;
	}
	
	//내 이슈 댓글 삭제
	@Override
	@GetMapping("/personalmemodelete")
	public String personalmemoDelete(String p_m_Num, HttpSession session) throws Exception{
		Map<String, Object> member = new HashMap<String,Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String mem_Id = (String) member.get("mem_Id");
		
		personal_memoService.personal_memoDelete(p_m_Num);


		
		return "redirect:/personal/search/myReplylist?mem_Id="+mem_Id;
	}
	
	
	//파일함
	@Override
	@GetMapping("/myFile")
	public ModelAndView myFile(
			@RequestParam(required=false) String keyword,
			@RequestParam(required=false) String c_Id,
			@RequestParam(required=false) String order,
			@RequestParam(required=false) String a_NameEx,
			@RequestParam(required=false) String wr_mem_Id,
			fileCriteria filecri, HttpSession session, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		Map<String, Object> member = new HashMap<String,Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String mem_Id = (String) member.get("mem_Id");
		
		
		filecri.setKeyword(keyword);
		filecri.setMem_Id(mem_Id);
		
		filePageMaker filepageMaker = new filePageMaker();
		filepageMaker.setCri(filecri);
		filepageMaker.setTotalCount(whole_searchService.fileCount(filecri));
		
		List<Map> fileList = whole_searchService.searchFile(filecri);
		
		int fileCount = whole_searchService.fileCount(filecri);
		
		List<Map> coworkList = issueService.coRead(mem_Id);
		
		List<Map> fileWriter = whole_searchService.fileWriter(filecri);
		
		List<Map> fileEx = whole_searchService.fileEx(filecri);
	
		ModelAndView mav = new ModelAndView("personal/myFile");
		mav.addObject("filepageMaker", filepageMaker);
		mav.addObject("fileList", fileList);
		mav.addObject("fileCount", fileCount);
		mav.addObject("coworkList", coworkList);
		mav.addObject("fileWriter", fileWriter);
		mav.addObject("fileEx", fileEx);
		mav.addObject("fileSearchkeyword", keyword);
		mav.addObject("c_Id", c_Id);
		mav.addObject("order", order);
		mav.addObject("wr_mem_Id", wr_mem_Id);
		mav.addObject("a_NameEx", a_NameEx);
		return mav;
		
	}
	
	
	//내 담당 리스트 목록 조회 페이징
	@Override
	@GetMapping("/myChargerlist")
	public ModelAndView myissueList(Criteria cri, String mem_Id, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		Map<String, Object> member = new HashMap<String,Object>();
//		member = (Map<String, Object>) session.getAttribute("member");
//		mem_Id = (String) member.get("mem_Id");
				
		List<Map> list = personal_searchService.myissueList(cri);
		System.out.println("!!!!!!!!!!!!!!!!!!!11"+list);
		PageMaker pageMaker = new PageMaker();
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(personal_searchService.myissueListCount(mem_Id));
		
	
		ModelAndView mav = new ModelAndView("personal/myChargerlist");
		mav.addObject("myChargerlist", list);
		mav.addObject("pageMaker", pageMaker);		
		
		return mav;
		
	}


}





