package project.sungho.apply.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface ApplyController {

	public String searchList(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public String updateApply(Model model) throws Exception;

	public String insertApply(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public String deleteApply(HttpServletRequest request, HttpServletResponse responsel) throws Exception;

	public int memberCheck(@RequestParam("mem_Id") String mem_Id) throws Exception;
	
	public int acceptApply(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public int rejectApply(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
