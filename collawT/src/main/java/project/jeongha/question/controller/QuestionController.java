package project.jeongha.question.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.jeongha.member.vo.MemberVO;
import project.jeongha.question.vo.QuestionVO;


public interface QuestionController {
	public String qustion(QuestionVO qVo, HttpServletRequest reqest, HttpServletResponse response, HttpSession session) ;
	public String sendEmail(QuestionVO qVo,HttpServletRequest reqest, HttpServletResponse response, HttpSession session) throws Exception ;
	public void sendEmail(QuestionVO qVo) throws Exception;
}