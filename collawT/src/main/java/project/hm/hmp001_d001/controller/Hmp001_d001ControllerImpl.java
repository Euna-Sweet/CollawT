package project.hm.hmp001_d001.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import project.hm.hmp001_d001.service.Hmp001_d001Service;
import project.hm.hmp001_d001.vo.Hmp001_d001VO;

@Controller("hmp001_d001Controller")

@RequestMapping("/hm/hmp001_d001/*")
public class Hmp001_d001ControllerImpl implements Hmp001_d001Controller {
	private static final Logger logger = LoggerFactory.getLogger(Hmp001_d001ControllerImpl.class);
	@Autowired
	Hmp001_d001Service hmp001_d001Service;
	@Autowired
	Hmp001_d001VO hmp001_d001VO;
	
	@Override 
	@GetMapping("searchInit.do")
	public ModelAndView searchInit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("hm/hmp001_d001_init");
		return mav;
	}
	
	@Override
	@RequestMapping(value = "hm/hmp001_d001/searchList.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView searchList(@RequestParam(value="p_id", required=false) String p_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("p_id", p_id);	 
		
		List<Hmp001_d001VO> list = hmp001_d001Service.searchList(searchMap);
		
		ModelAndView mav = new ModelAndView("hm/hmp001_d001_search");
		mav.addObject("searchList", list);
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/hm/hmp001_d001/searchMod.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView searchMod(@RequestParam(value="p_mod_id", required=false) String p_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("p_id", p_id);
		
		List<Hmp001_d001VO> list = hmp001_d001Service.searchList(searchMap);
		if(!list.isEmpty()) {
			hmp001_d001VO = (Hmp001_d001VO)list.get(0);
		}
		
		ModelAndView mav = new ModelAndView("hm/hmp001_d001_mod");
		mav.addObject("hmp001_d001VO", hmp001_d001VO);
		mav.addObject("command", "modSearch");
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/hm/hmp001_d001/searchInsert.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView searchInsert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		ModelAndView mav = new ModelAndView("hm/hmp001_d001_mod");
		mav.addObject("command", "addSearch");
		return mav;
	}

	@Override
	@RequestMapping(value = "/hm/hmp001_d001/updateMember.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResponseEntity updateMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			dataMap.put(name, value);
		}

		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders(); // �뿤�뜑蹂�寃� �떆 �궗�슜
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");		
		try {
			hmp001_d001Service.updateMember(dataMap);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/hm/hmp001_d001/searchList.do");
			dispatch.forward(request, response);
		} catch (Exception e) {
			message = " <script>";
			message += " alert('�삤瑜섍� 諛쒖깮�뻽�뒿�땲�떎. �떎�떆 �떆�룄�빐 二쇱꽭�슂');";
			message += " location.href='" + request.getContextPath() + "/hm/hmp001_d001/searchInit.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}		
		return resEnt;
	}
	
	@Override
	@RequestMapping(value = "/hm/hmp001_d001/insertMember.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResponseEntity insertMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			dataMap.put(name, value);
		}

		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders(); // �뿤�뜑蹂�寃� �떆 �궗�슜
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");		
		try {
			hmp001_d001Service.insertMember(dataMap);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/hm/hmp001_d001/searchList.do");
			dispatch.forward(request, response);
		} catch (Exception e) {
			message = " <script>";
			message += " alert('�삤瑜섍� 諛쒖깮�뻽�뒿�땲�떎. �떎�떆 �떆�룄�빐 二쇱꽭�슂');";
			message += " location.href='" + request.getContextPath() + "/hm/hmp001_d001/searchInit.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}		
		return resEnt;
	}	
	
	@RequestMapping(value = "/common/ajaxTest", produces="application/json", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> ajaxTest() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "hong");
		map.put("name", "�솉湲몃룞");
		return map;
	}
}
