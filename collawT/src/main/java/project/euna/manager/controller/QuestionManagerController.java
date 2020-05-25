package project.euna.manager.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.jeongha.question.vo.QuestionVO;

public interface QuestionManagerController {
	public ModelAndView searchInit(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public Map searchList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public Map saveData(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView questionRead(String q_Num, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String answerInsert(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public void sendEmail(Map<String, Object> mailmap) throws Exception;
	public String questionStatusUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception;

}