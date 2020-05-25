package project.sungho.cowork.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;



public interface CoworkController {
	public String searchMain(Model model ,HttpServletRequest request, HttpServletResponse responsen) throws Exception;
	
	public String updateCowork(HttpServletRequest request, HttpServletResponse responsen) throws Exception;
	public String insertCowork(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String deleteCowork(Model model, HttpServletRequest request, HttpServletResponse responsen) throws Exception;
	
	public List<Map> calendarlist(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public String calendar(Model model ,HttpServletRequest request, HttpServletResponse responsen) throws Exception;
	
	public String kanban(Model model ,HttpServletRequest request, HttpServletResponse responsen) throws Exception;
	public int kanbanUpdate(Model model ,HttpServletRequest request, HttpServletResponse responsen) throws Exception;
	

}
