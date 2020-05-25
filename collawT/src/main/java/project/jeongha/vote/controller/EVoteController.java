package project.jeongha.vote.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import project.jeongha.vote.vo.Criteria;
import project.jeongha.vote.vo.VoteVO;



public interface EVoteController {
//
	public ModelAndView searchList(Criteria cri, String c_Id, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String issueInsert(VoteVO voteVO, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView voteInsert(String c_Id, HttpSession session);
	public ModelAndView voteRead(String c_Id, String i_Num, HttpServletRequest request, HttpServletResponse response) throws Exception ;
	public String voteDelete(String c_Id, String v_Num, VoteVO voteVO) throws Exception;
	public String voteUpdate(String c_Id, String v_Num, VoteVO voteVO, Model model)  throws Exception ;
	public String voteUpdate(VoteVO voteVO, HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception;
	public ModelAndView voter(String v_Num, String c_Id, String vd_Num, VoteVO voteVO, HttpSession session,Model model,HttpServletRequest request,HttpServletResponse response ) throws Exception;
	
}

