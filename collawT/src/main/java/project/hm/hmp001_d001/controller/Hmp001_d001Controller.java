package project.hm.hmp001_d001.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest; 
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface Hmp001_d001Controller {
	public ModelAndView searchList(String p_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView searchMod(String p_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView searchInit(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView searchInsert(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ResponseEntity updateMember(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity insertMember(HttpServletRequest request, HttpServletResponse response) throws Exception;
}