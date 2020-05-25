package project.hm.hmp001_d002.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface Hmp001_d002Controller {
	public ModelAndView searchInit(HttpServletRequest request, HttpServletResponse response) throws Exception;	
	public Map<String, Object> searchMember(String p_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView searchInsert(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public Map<String, Object> updateMember(HttpServletRequest request, HttpServletResponse response) throws Exception;
	//public Map<String, Object> updateMember(Map paramMap) throws Exception;
	public Map<String, Object> jsonTest(Map paramMap) throws Exception;
}