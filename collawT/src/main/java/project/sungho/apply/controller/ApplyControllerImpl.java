package project.sungho.apply.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.jeongha.member.service.MemberService;
import project.sungho.apply.service.ApplyService;
import project.sungho.apply.vo.ApplyVO;
import project.sungho.comember.service.ComemberService;
import project.sungho.cowork.vo.CoworkVO;

@Controller
@RequestMapping("/news/*")
public class ApplyControllerImpl implements ApplyController {

	@Autowired
	ApplyService applyService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	ComemberService comemberService;
	//알림마크에서 초대요청 개수를 파악하기 위한 서블릿
	@RequestMapping("/view")
	@ResponseBody
	public List<Map> viewCount(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		Map<String,Object> searchMap = new HashMap<String,Object>();
		searchMap = (Map<String,Object>) session.getAttribute("member");
		List<Map> viewCount = applyService.viewCount(searchMap);
		return viewCount;
	}

	@Override
	@RequestMapping("/list")
	public String searchList(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap = (Map<String, Object>) session.getAttribute("member");

		
		List<Map> list = applyService.searchList(searchMap);
		model.addAttribute("applylist", list);
		//System.out.println("applyList에 얼마나 들어있나~~~~:");
		return "newspeed/newspeedList3";
	}

	@Override
	public String updateApply(Model model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@PostMapping("insert")
	public String insertApply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		Map<String, Object> inviteVO = (Map<String, Object>) session.getAttribute("member");
		
		inviteVO.get("mem_Name");
		String c_Id = request.getParameter("c_Id");
		String mem_Id[] = request.getParameterValues("mem_Id");
		String invite =  (String) inviteVO.get("mem_Id");;
		for (int i = 0; i < mem_Id.length; i++) {
			dataMap.put("c_Id", c_Id);
			dataMap.put("mem_Id", mem_Id[i]);
			dataMap.put("invite", invite);
			applyService.insertApply(dataMap);
		}

		return "redirect:/project/main?c_Id="+c_Id;
	}
	
	

	@Override
	public String deleteApply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			dataMap.put(name, value);
		}
		applyService.deleteApply(dataMap);
		return null;
	}

	@Override
	@GetMapping("memberCheck") //ajax 회원체크를 위한 맴버체크
	@ResponseBody
	public int memberCheck(@RequestParam("mem_Id") String mem_Id) throws Exception {
		// TODO Auto-generated method stub
		int result= applyService.memberCheck(mem_Id);
		
		System.out.println("controller result:"+result);
		return result;
	}

	
	//초대 수락을 위한 ajax 컨트롤러
	@Override
	@GetMapping("accept")
	@ResponseBody
	public int acceptApply(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			dataMap.put(name, value);
		}
		comemberService.insertComember(dataMap);
		return applyService.acceptApply(dataMap);
	}
	
	
	
	//초대 거절을 위한 ajax 컨트롤러 
	@Override
	@GetMapping("reject")
	@ResponseBody
	public int rejectApply(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			dataMap.put(name, value);
		}
		return applyService.rejectApply(dataMap);
	}
	

}
