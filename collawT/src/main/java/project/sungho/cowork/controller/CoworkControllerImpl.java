package project.sungho.cowork.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import project.euna.issue.service.IssueService;
import project.sungho.comember.service.ComemberService;
import project.sungho.cowork.service.CoworkService;
import project.sungho.cowork.vo.CoworkVO;

@Controller
@RequestMapping("/project/*")
public class CoworkControllerImpl implements CoworkController {

	@Autowired
	CoworkService coworkService;
	@Autowired
	ComemberService comemberService;

	@Autowired
	IssueService issueService;

	@Override
	@GetMapping("/main")
	public String searchMain(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		String c_Id = (String) request.getParameter("c_Id");

		searchMap.put("c_Id", c_Id);

		Map<String, Object> pjt = coworkService.searchMain(searchMap); // vo타입에 list를 생성하고 서비스에서 가져온 데이터를 list에 넣습니다
		List<Map> memberList = comemberService.memberList(searchMap);
		List<Map> igCount = issueService.igCount(c_Id);
		List<Map> recentBoard = issueService.recentBoard(c_Id);
		List<Map> voteOn = issueService.voteOn(c_Id);
		String votePercent = issueService.votePercent(c_Id);

		model.addAttribute("pjt", pjt);
		model.addAttribute("memberList", memberList);
		model.addAttribute("igCount", igCount);
		model.addAttribute("recentBoard", recentBoard);
		model.addAttribute("voteOn", voteOn);
		model.addAttribute("votePercent", votePercent);
		return "/cowork/main"; // 뷰url지정해주세요

	}

	@Override
	@PostMapping("/update")
	public String updateCowork(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String c_Id = request.getParameter("c_Id");
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			dataMap.put(name, value);
		}
		coworkService.updateCowork(dataMap);

		return "redirect:/project/main?c_Id=" + c_Id;

	}

	@Override
	@PostMapping("/insert")
	public String insertCowork(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> dataMap = new HashMap<String, Object>();

		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			dataMap.put(name, value);
		}
		coworkService.insertCowork(dataMap);

		return "redirect:/main";

	}

	@Override
	@PostMapping("/delete")
	public String deleteCowork(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> dataMap = new HashMap<String, Object>();

		String c_Id = request.getParameter("c_Id");

		dataMap.put("c_Id", c_Id);

		coworkService.deleteCowork(dataMap);

		return "redirect:/main";
	}

	@RequestMapping("/create")
	public String project(Model model) {

		return "/cowork/coworkinsert";
	}

	@Override
	@GetMapping("/calendarlist")
	@ResponseBody
	public List<Map> calendarlist(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		String c_Id = (String) request.getParameter("c_Id");
		searchMap.put("c_Id", c_Id);
		System.out.println(c_Id);
		List<Map> eventlist = coworkService.calendarlist(searchMap);

		String itemurl = "/project/issue/read?c_Id=" + c_Id +"&i_Num=";
		for (int i = 0; i < eventlist.size(); i++) {
			eventlist.get(i).put("url", itemurl + eventlist.get(i).get("url"));
			eventlist.get(i).put("color", "#28a745");
			eventlist.get(i).put("textColor", "#FFF");
		}
		List<Map> votelist = coworkService.calendarvotelist(searchMap);

		String voteurl = "/project/vote/read?c_Id=" + c_Id + "&v_Num=";
		for (int i = 0; i < votelist.size(); i++) {
			votelist.get(i).put("url", voteurl + votelist.get(i).get("url"));
			votelist.get(i).put("color", "#ffc107");
			votelist.get(i).put("textColor", "#FFF");
		}
		eventlist.addAll(votelist);

		return eventlist;
	}

	@Override
	@GetMapping("/calendar")
	public String calendar(Model model, HttpServletRequest request, HttpServletResponse responsen) throws Exception {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		String c_Id = (String) request.getParameter("c_Id");
		searchMap.put("c_Id", c_Id);
		Map<String, Object> pjt = coworkService.searchMain(searchMap);
		model.addAttribute("pjt", pjt);
		return "/cowork/calendar";
	}
	
	@Override
	@GetMapping("/kanban")
	public String kanban(Model model, HttpServletRequest request, HttpServletResponse responsen) throws Exception {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		String c_Id = (String) request.getParameter("c_Id");
		searchMap.put("c_Id", c_Id);
		List<Map> kanbanlist = coworkService.kanbanlist(searchMap);
		Map<String, Object> pjt = coworkService.searchMain(searchMap);
		model.addAttribute("pjt", pjt);
		
		model.addAttribute("kanbanlist", kanbanlist);
		return "/cowork/kanban";
	}
	
	
	@Override
	@GetMapping("/kanbanUpdate")
	@ResponseBody
	public int kanbanUpdate(Model model, HttpServletRequest request, HttpServletResponse responsen) throws Exception {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		String i_Num = (String) request.getParameter("i_Num");
		String ig_Stat = (String) request.getParameter("ig_Num");
		System.out.println("칸반번호================="+ig_Stat);
		String ig_Num = ig_Stat.substring(9,10);
		searchMap.put("i_Num", i_Num);
		searchMap.put("ig_Num", ig_Num);
		int result = coworkService.kanbanUpdate(searchMap);
		return result;
	}
	
	
}
