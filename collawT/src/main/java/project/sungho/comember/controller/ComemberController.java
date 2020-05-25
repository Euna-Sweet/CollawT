package project.sungho.comember.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import project.sungho.cowork.vo.CoworkVO;

public interface ComemberController {

public String searchList(Model model ,HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public String updateComember(Model model) throws Exception;
	public String insertComember(CoworkVO coworkVO, HttpSession session) throws Exception;
	public String deleteComember(Model model ,HttpServletRequest request, HttpServletResponse responsel) throws Exception;
	public String deleteMember(Model model ,HttpServletRequest request, HttpServletResponse responsel) throws Exception;
	
}
