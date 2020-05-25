package project.euna.personal.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.euna.appendix.vo.AppendixVO;
import project.euna.issue.vo.IssueVO;
import project.euna.personal.vo.Criteria;
import project.euna.personal.vo.PersonalVO;

public interface PersonalController {
//
	public ModelAndView searchList(PersonalVO personalVO, Criteria cri, String c_Id, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String personalInsert(PersonalVO personalVO, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView personalInsert(String mem_Id, HttpSession session);
//	
	public ModelAndView personalRead(String p_Num, HttpServletRequest request, HttpServletResponse response) throws Exception;//	
	public String personalDelete(String mem_Id, String p_Num, PersonalVO personalVO) throws Exception;//	
	public String personalUpdate(String mem_Id, String p_Num, PersonalVO personalVO, Model model, HttpSession session) throws Exception;
	public String personalUpdate(PersonalVO personalVO, HttpServletRequest request, HttpServletResponse response) throws Exception ;
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String issueCopy(IssueVO issueVO, HttpSession session, HttpServletRequest request, HttpServletResponse response, RedirectAttributes rttr) throws Exception;
//	public void communityImageUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload);
//	public String personalCopy(personalVO personalVO, HttpSession session, HttpServletRequest request, HttpServletResponse response, RedirectAttributes rttr) throws Exception;
}

