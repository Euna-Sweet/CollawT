package project.hm.hmp001_d003.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import project.hm.hmp001_d001.controller.Hmp001_d001ControllerImpl;
import project.hm.hmp001_d003.service.Hmp001_d003Service;
import project.hm.hmp001_d003.vo.Hmp001_d003VO;

@Controller("hmp001_d003Controller")
public class Hmp001_d003ControllerImpl implements Hmp001_d003Controller {
	private static final Logger logger = LoggerFactory.getLogger(Hmp001_d001ControllerImpl.class);
	@Autowired
	Hmp001_d003Service hmp001_d003Service;
	@Autowired
	Hmp001_d003VO hmp001_d003VO;
	
	@Override 
	@RequestMapping(value = "/hm/hmp001_d003/searchInit.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView searchInit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("hm/hmp001_d003_init");
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/hm/hmp001_d003/searchList.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView searchList(@RequestParam(value="p_prod_id", required=false) String p_prod_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("p_prod_id", p_prod_id);	 
		
		List<Hmp001_d003VO> list = hmp001_d003Service.searchList(searchMap);
		
		ModelAndView mav = new ModelAndView("hm/hmp001_d003_search");
		mav.addObject("searchList", list);
		return mav;
	}
//	
//	@Override
//	@RequestMapping(value = "/hm/hmp001_d003/searchMod.do", method = { RequestMethod.GET, RequestMethod.POST })
//	public ModelAndView searchMod(@RequestParam(value="p_mod_prod_id", required=false) String p_prod_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		request.setCharacterEncoding("utf-8");
//		Map<String, Object> searchMap = new HashMap<String, Object>();
//		searchMap.put("p_prod_id", p_prod_id);
//		
//		List<Hmp001_d003VO> list = hmp001_d003Service.searchList(searchMap);
//		if(!list.isEmpty()) {
//			hmp001_d003VO = (Hmp001_d003VO)list.get(0);
//		}
//		
//		ModelAndView mav = new ModelAndView("hm/hmp001_d003_mod");
//		mav.addObject("hmp001_d003VO", hmp001_d003VO);
//		mav.addObject("command", "modSearch");
//		return mav;
//	}
//	
//	@Override
//	@RequestMapping(value = "/hm/hmp001_d003/searchInsert.do", method = { RequestMethod.GET, RequestMethod.POST })
//	public ModelAndView searchInsert(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		request.setCharacterEncoding("utf-8");
//		
//		ModelAndView mav = new ModelAndView("hm/hmp001_d001_mod");
//		mav.addObject("command", "addSearch");
//		return mav;
//	}
//
//	@Override
//	@RequestMapping(value = "/hm/hmp001_d003/updateMember.do", method = { RequestMethod.GET, RequestMethod.POST })
//	@ResponseBody
//	public ResponseEntity updateProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		request.setCharacterEncoding("utf-8");
//		Map<String, Object> dataMap = new HashMap<String, Object>();
//		Enumeration enu = request.getParameterNames();
//		while (enu.hasMoreElements()) {
//			String name = (String) enu.nextElement();
//			String value = request.getParameter(name);
//			dataMap.put(name, value);
//		}
//
//		String message;
//		ResponseEntity resEnt = null;
//		HttpHeaders responseHeaders = new HttpHeaders(); // 헤더변경 시 사용
//		responseHeaders.add("Content-Type", "text/html; charset=utf-8");		
//		try {
//			hmp001_d003Service.updateProduct(dataMap);
//			
//			RequestDispatcher dispatch = request.getRequestDispatcher("/hm/hmp001_d003/searchList.do");
//			dispatch.forward(request, response);
//		} catch (Exception e) {
//			message = " <script>";
//			message += " alert('오류가 발생했습니다. 다시 시도해 주세요');";
//			message += " location.href='" + request.getContextPath() + "/hm/hmp001_d003/searchInit.do'; ";
//			message += " </script>";
//			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
//			e.printStackTrace();
//		}		
//		return resEnt;
//	}
//	
//	@Override
//	@RequestMapping(value = "/hm/hmp001_d003/insertMember.do", method = { RequestMethod.GET, RequestMethod.POST })
//	@ResponseBody
//	public ResponseEntity insertProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		request.setCharacterEncoding("utf-8");
//		Map<String, Object> dataMap = new HashMap<String, Object>();
//		Enumeration enu = request.getParameterNames();
//		while (enu.hasMoreElements()) {
//			String name = (String) enu.nextElement();
//			String value = request.getParameter(name);
//			dataMap.put(name, value);
//		}
//
//		String message;
//		ResponseEntity resEnt = null;
//		HttpHeaders responseHeaders = new HttpHeaders(); // 헤더변경 시 사용
//		responseHeaders.add("Content-Type", "text/html; charset=utf-8");		
//		try {
//			hmp001_d003Service.insertProduct(dataMap);
//			
//			RequestDispatcher dispatch = request.getRequestDispatcher("/hm/hmp001_d003/searchList.do");
//			dispatch.forward(request, response);
//		} catch (Exception e) {
//			message = " <script>";
//			message += " alert('오류가 발생했습니다. 다시 시도해 주세요');";
//			message += " location.href='" + request.getContextPath() + "/hm/hmp001_d003/searchInit.do'; ";
//			message += " </script>";
//			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
//			e.printStackTrace();
//		}		
//		return resEnt;
//	}	
//	
//	@RequestMapping(value = "/common/ajaxTest", produces="application/json", method = { RequestMethod.GET, RequestMethod.POST })
//	@ResponseBody
//	public Map<String, Object> ajaxTest() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("id", "hong");
//		map.put("name", "홍길동");
//		return map;
//	}
}
