package project.euna.whole_search.controller;

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

import project.euna.issue.service.IssueServiceImpl;
import project.euna.whole_search.service.Whole_searchService;
import project.euna.whole_search.vo.fileCriteria;
import project.euna.whole_search.vo.filePageMaker;
import project.euna.whole_search.vo.issueCriteria;
import project.euna.whole_search.vo.issuePageMaker;
import project.euna.whole_search.vo.replyCriteria;
import project.euna.whole_search.vo.replyPageMaker;
import project.euna.whole_search.vo.voteCriteria;
import project.euna.whole_search.vo.votePageMaker;



@Controller

@RequestMapping("/search/*")
public class Whole_searchControllerImpl implements Whole_searchController {
	
	//
	@Inject
	Whole_searchService whole_searchService;
	
	@Inject
	IssueServiceImpl issueService;
	
	
	//이슈 목록 조회 페이징
	@Override
	@GetMapping("/issueresult")
	public ModelAndView issueResult(
			@RequestParam(required=false) String keyword,
			@RequestParam(required=false) String c_Id,
			@RequestParam(required=false) String order,
			@RequestParam(required=false) String ig_Num,
			@RequestParam(required=false) String wr_mem_Id,
			issueCriteria issuecri, HttpSession session, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		Map<String, Object> member = new HashMap<String,Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String mem_Id = (String) member.get("mem_Id");
		
		
		issuecri.setKeyword(keyword);
		issuecri.setMem_Id(mem_Id);
		
		issuePageMaker issuepageMaker = new issuePageMaker();
		issuepageMaker.setCri(issuecri);
		issuepageMaker.setTotalCount(whole_searchService.issueCount(issuecri));
		
		List<Map> issueList = whole_searchService.searchIssue(issuecri);
		
		int issueCount = whole_searchService.issueCount(issuecri);
		
		List<Map> coworkList = issueService.coRead(mem_Id);
		
		List<Map> issueWriter = whole_searchService.issueWriter(issuecri);

	
		ModelAndView mav = new ModelAndView("wholesearch/issueResult");
		mav.addObject("issuepageMaker", issuepageMaker);
		mav.addObject("issueList", issueList);
		mav.addObject("issueCount", issueCount);
		mav.addObject("coworkList", coworkList);
		mav.addObject("issueWriter", issueWriter);
		mav.addObject("keyword", keyword);
		mav.addObject("c_Id", c_Id);
		mav.addObject("order", order);
		mav.addObject("wr_mem_Id", wr_mem_Id);
		mav.addObject("ig_Num", ig_Num);
		return mav;
		
	}

	
	//파일 목록 조회 페이징
	@Override
	@GetMapping("/fileresult")
	public ModelAndView fileResult(
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
	
		ModelAndView mav = new ModelAndView("wholesearch/fileResult");
		mav.addObject("filepageMaker", filepageMaker);
		mav.addObject("fileList", fileList);
		mav.addObject("fileCount", fileCount);
		mav.addObject("coworkList", coworkList);
		mav.addObject("fileWriter", fileWriter);
		mav.addObject("fileEx", fileEx);
		mav.addObject("keyword", keyword);
		mav.addObject("c_Id", c_Id);
		mav.addObject("order", order);
		mav.addObject("wr_mem_Id", wr_mem_Id);
		mav.addObject("a_NameEx", a_NameEx);
		return mav;
		
	}
	
	
	//투표 목록 조회 페이징
	@Override
	@GetMapping("/voteresult")
	public ModelAndView voteResult(
			@RequestParam(required=false) String keyword,
			@RequestParam(required=false) String c_Id,
			@RequestParam(required=false) String order,
			@RequestParam(required=false) String vs_Num,
			@RequestParam(required=false) String wr_mem_Id,
			voteCriteria votecri, HttpSession session, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		Map<String, Object> member = new HashMap<String,Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String mem_Id = (String) member.get("mem_Id");
		
		
		votecri.setKeyword(keyword);
		votecri.setMem_Id(mem_Id);
		
		votePageMaker votepageMaker = new votePageMaker();
		votepageMaker.setCri(votecri);
		votepageMaker.setTotalCount(whole_searchService.voteCount(votecri));
		
		List<Map> voteList = whole_searchService.searchVote(votecri);
		
		int voteCount = whole_searchService.voteCount(votecri);
		
		List<Map> coworkList = issueService.coRead(mem_Id);
		
		List<Map> voteWriter = whole_searchService.voteWriter(votecri);
	
		ModelAndView mav = new ModelAndView("wholesearch/voteResult");
		mav.addObject("votepageMaker", votepageMaker);
		mav.addObject("voteList", voteList);
		mav.addObject("voteCount", voteCount);
		mav.addObject("coworkList", coworkList);
		mav.addObject("voteWriter", voteWriter);
		mav.addObject("keyword", keyword);
		mav.addObject("c_Id", c_Id);
		mav.addObject("order", order);
		mav.addObject("wr_mem_Id", wr_mem_Id);
		mav.addObject("vs_Num", vs_Num);
		return mav;
		
	}
	
	//댓글 목록 조회 페이징
	@Override
	@GetMapping("/replyresult")
	public ModelAndView replyResult(
			@RequestParam(required=false) String keyword,
			@RequestParam(required=false) String c_Id,
			@RequestParam(required=false) String order,
			@RequestParam(required=false) String wr_mem_Id,
			replyCriteria replycri, HttpSession session, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		Map<String, Object> member = new HashMap<String,Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String mem_Id = (String) member.get("mem_Id");
		
		
		replycri.setKeyword(keyword);
		replycri.setMem_Id(mem_Id);
		
		replyPageMaker replypageMaker = new replyPageMaker();
		replypageMaker.setCri(replycri);
		replypageMaker.setTotalCount(whole_searchService.replyCount(replycri));
		
		List<Map> replyList = whole_searchService.searchReply(replycri);
		
		int replyCount = whole_searchService.replyCount(replycri);
		
		List<Map> coworkList = issueService.coRead(mem_Id);
		
		List<Map> replyWriter = whole_searchService.replyWriter(replycri);
	
		ModelAndView mav = new ModelAndView("wholesearch/replyResult");
		mav.addObject("replypageMaker", replypageMaker);
		mav.addObject("replyList", replyList);
		mav.addObject("replyCount", replyCount);
		mav.addObject("coworkList", coworkList);
		mav.addObject("replyWriter", replyWriter);
		mav.addObject("keyword", keyword);
		mav.addObject("c_Id", c_Id);
		mav.addObject("order", order);
		mav.addObject("wr_mem_Id", wr_mem_Id);
		return mav;
		
	}
	
	//통합검색 목록 조회 페이징
	@Override
	@GetMapping("/wholeresult")
	public ModelAndView wholeresult(
			@RequestParam(required=false) String keyword,
			issueCriteria issuecri, voteCriteria votecri,
			fileCriteria filecri, replyCriteria replycri,
			HttpSession session, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		Map<String, Object> member = new HashMap<String,Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String mem_Id = (String) member.get("mem_Id");
		
		
		issuecri.setKeyword(keyword);
		votecri.setKeyword(keyword);
		filecri.setKeyword(keyword);
		replycri.setKeyword(keyword);
		issuecri.setMem_Id(mem_Id);
		votecri.setMem_Id(mem_Id);
		filecri.setMem_Id(mem_Id);
		replycri.setMem_Id(mem_Id);
		issuecri.setPerPageNum(5);
		votecri.setPerPageNum(5);
		filecri.setPerPageNum(5);
		replycri.setPerPageNum(5);
		
		issuePageMaker issuepageMaker = new issuePageMaker();
		issuepageMaker.setCri(issuecri);
		issuepageMaker.setTotalCount(whole_searchService.issueCount(issuecri));
		
		votePageMaker votepageMaker = new votePageMaker();
		votepageMaker.setCri(votecri);
		votepageMaker.setTotalCount(whole_searchService.voteCount(votecri));
//		
		filePageMaker filepageMaker = new filePageMaker();
		filepageMaker.setCri(filecri);
		filepageMaker.setTotalCount(whole_searchService.fileCount(filecri));
//		
		replyPageMaker replypageMaker = new replyPageMaker();
		replypageMaker.setCri(replycri);
		replypageMaker.setTotalCount(whole_searchService.replyCount(replycri));
		
		
		List<Map> issueList = whole_searchService.searchIssue(issuecri);
		int issueCount = whole_searchService.issueCount(issuecri);
		List<Map> fileList = whole_searchService.searchFile(filecri);
		int fileCount = whole_searchService.fileCount(filecri);
		List<Map> voteList = whole_searchService.searchVote(votecri);
		int voteCount = whole_searchService.voteCount(votecri);
		List<Map> replyList = whole_searchService.searchReply(replycri);
		int replyCount = whole_searchService.replyCount(replycri);

	
		ModelAndView mav = new ModelAndView("wholesearch/wholeResult");
		mav.addObject("issuepageMaker", issuepageMaker);
		mav.addObject("votepageMaker", votepageMaker);
		mav.addObject("filepageMaker", filepageMaker);
		mav.addObject("replypageMaker", replypageMaker);
		
		mav.addObject("issueList", issueList);
		mav.addObject("issueCount", issueCount);
		mav.addObject("fileList", fileList);
		mav.addObject("fileCount", fileCount);
		mav.addObject("voteList", voteList);
		mav.addObject("voteCount", voteCount);
		mav.addObject("replyList", replyList);
		mav.addObject("replyCount", replyCount);
		
		mav.addObject("keyword", keyword);
		return mav;
		
	}




}





