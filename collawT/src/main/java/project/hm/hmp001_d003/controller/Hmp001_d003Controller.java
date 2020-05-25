package project.hm.hmp001_d003.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

public interface Hmp001_d003Controller {
	public ModelAndView searchList(String p_prod_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	//public ModelAndView searchMod(String p_prod_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView searchInit(HttpServletRequest request, HttpServletResponse response) throws Exception;
	//public ModelAndView searchInsert(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//public ResponseEntity updateProduct(HttpServletRequest request, HttpServletResponse response) throws Exception;
	//public ResponseEntity insertProduct(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
