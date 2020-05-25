package project.euna.personal_search.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import project.euna.personal_search.vo.Criteria;
import project.euna.whole_search.vo.fileCriteria;



public interface Personal_searchController {
	public ModelAndView myBoardlist(Criteria cri, String mem_Id, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView myFile(
			@RequestParam(required=false) String keyword,
			@RequestParam(required=false) String c_Id,
			@RequestParam(required=false) String order,
			@RequestParam(required=false) String a_NameEx,
			@RequestParam(required=false) String wr_mem_Id,
			fileCriteria filecri, HttpSession session, HttpServletRequest request, 
			HttpServletResponse response) throws Exception;
	public String issueDelete(String i_Num, HttpSession session) throws Exception;
	public String voteDelete(String v_Num, HttpSession session) throws Exception;
	public String personaldelete(String p_Num, HttpSession session) throws Exception;
	public ModelAndView myReplylist(Criteria cri, String mem_Id, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String issuereplyDelete(String r_Num, HttpSession session) throws Exception;
	public String votereplyDelete(String v_Num, HttpSession session) throws Exception;
	public String personalmemoDelete(String p_Num, HttpSession session) throws Exception;
	public ModelAndView myissueList(Criteria cri, String mem_Id, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception;
}

