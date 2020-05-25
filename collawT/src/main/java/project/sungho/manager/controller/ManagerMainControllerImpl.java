package project.sungho.manager.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import project.euna.manager.dao.EManagerMainDAO;
import project.hm.hmp002_d001.service.Hmp002_d001Service;
import project.hm.hmp002_d001.vo.Hmp002_d001VO;
import project.sungho.comember.service.ComemberService;
import project.sungho.manager.service.ManagerMainService;
import project.sungho.manager.service.ManagerService;

@Controller
@RequestMapping("/manager/*")
public class ManagerMainControllerImpl implements ManagerMainController {
	@Autowired 
	ManagerMainService managerMainService;
	
	@Autowired 
	EManagerMainDAO eManagerMainDAO;
	
	@Override
	@RequestMapping(value = "/main", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView searchInit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView("/manager/managermain");
		List<Map> chartList = managerMainService.searchList();
		int member_Count = managerMainService.memCount();
		List<Map> donutList = managerMainService.donutList();
		List<Map> lineList = managerMainService.lineList();
		List<Map> list = eManagerMainDAO.main();
		
		System.out.println("회원가입 숫자"+chartList.get(0).get("mem_Joindate"));
		mav.addObject("chartList",chartList);
		mav.addObject("member_Count",member_Count);
		mav.addObject("donutList",donutList);
		mav.addObject("lineList",lineList);
		mav.addObject("recentBoard", list);
		
		return mav;
	} 
	
}
