package project.notify.controller;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import project.notify.service.NotifyService;
import project.sungho.apply.service.ApplyService;

@Controller

@RequestMapping("/notify/*")
public class NotifyControllerImpl implements NotifyController {
	
	
	@Autowired
	NotifyService notifyService;
	
	@Autowired
	ApplyService applyService;
	
	
	
	/*
	 * @PostMapping("/insert") public String notifyInsert(NotifyVO notifyVO) throws
	 * Exception {
	 * 
	 * notifyService.notifyInsert(notifyVO);
	 * 
	 * return "redirect:/issue/list";
	 * 
	 * }
	 */
	 
	@RequestMapping("/update")	
	public String notifyUpdate(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
		//System.out.println("이거 데이터 상태값변경----------------");
		//System.out.println("update에 넘겨주는 값+++++++++++++++++++++:"+request.getParameter("i_Num"));
		String mem_Id = request.getParameter("id");
		HttpSession session = request.getSession();
		String i_Num = request.getParameter("i_Num");
		String c_Id = request.getParameter("c_Id");
		String status = request.getParameter("status");
		
		
		Map<String,Object> searchMap = new HashMap<String,Object>();
		searchMap = (Map<String,Object>) session.getAttribute("member");
		searchMap.put("i_Num", i_Num);
		notifyService.notifyUpdate(searchMap);

		
		if(status.equals("main")) {
			return "redirect:/main";

		}else {
			return "redirect:/project/issue/read?c_Id="+c_Id+"&i_Num="+i_Num;
		}
	}
	
	@RequestMapping("/voteUpdate")
	public String voteUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String c_Id = request.getParameter("c_Id");
		String v_Num = request.getParameter("v_Num");
		String mem_Id = request.getParameter("mem_Id");
		String status = request.getParameter("status");

		HttpSession session = request.getSession();
		Map<String,Object> searchMap = new HashMap<String,Object>();
		searchMap = (Map<String,Object>)session.getAttribute("member");
		searchMap.put("v_Num",v_Num);
		notifyService.voteUpdate(searchMap);
		
		if(status.equals("main")) {
			return "redirect:/main";

		}else {
			return "redirect:/project/vote/read?c_Id="+c_Id+"&v_Num="+v_Num;
		}
	}
	@RequestMapping("/replyupdate")
	public String replyUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String i_Num = request.getParameter("i_Num");
		String r_Num = request.getParameter("r_Num");
		String c_Id = request.getParameter("c_Id");
		String mem_Id = request.getParameter("mem_Id");
		String status = request.getParameter("status");

		HttpSession session = request.getSession();
		Map<String,Object> searchMap = new HashMap<String,Object>();
		searchMap = (Map<String,Object>)session.getAttribute("member");
		searchMap.put("r_Num", r_Num);
		notifyService.replyUpdate(searchMap);
		if(status.equals("main")) {
			return "redirect:/main";

		}else {
			return "redirect:/project/issue/read?c_Id="+c_Id+"&i_Num="+i_Num;
		}
	}
	@RequestMapping("/votereplyupdate")
	public String votereplyupdate(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String v_Num = request.getParameter("v_Num");
		String vr_Num = request.getParameter("vr_Num");
		String c_Id = request.getParameter("c_Id");
		String mem_Id = request.getParameter("mem_Id");
		String status = request.getParameter("status");

		HttpSession session = request.getSession();
		Map<String,Object> searchMap = new HashMap<String,Object>();
		searchMap = (Map<String,Object>)session.getAttribute("member");
		searchMap.put("vr_Num", vr_Num);
		notifyService.votereplyUpdate(searchMap);
		if(status.equals("main")) {
			return "redirect:/main";

		}else {
			return "redirect:/project/vote/read?c_Id="+c_Id+"&v_Num="+v_Num;
		}
	}
	
	@GetMapping("/insert")
	public String test() {
		return "/notify/notifyadd";
	}
	@RequestMapping("/view")
	@ResponseBody
	public List<Map> notifyView(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		
		Map<String,Object> searchMap = new HashMap<String,Object>();
		searchMap = (Map<String,Object>) session.getAttribute("member");
		
		List<Map> notifyView = notifyService.viewNotify(searchMap);
		//System.out.println("view 타나??==:"+notifyView.toString());
		return notifyView;
	}
	
	@RequestMapping("/viewReply")
	@ResponseBody
	public List<Map> viewReply(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		Map<String,Object> searchMap = new HashMap<String,Object>();
		searchMap = (Map<String,Object>)session.getAttribute("member");
		List<Map> viewReply = notifyService.viewReply(searchMap);
		//System.out.println("viewReply~~~~~~~@@@@@@@@@@@@@@@@@@@@@@@@@@@@@:"+viewReply.toString());
		return viewReply;
	}
	
	@RequestMapping("/viewVote")
	@ResponseBody
	public List<Map> viewVote(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		Map<String,Object> searchMap = new HashMap<String,Object>();
		searchMap = (Map<String,Object>)session.getAttribute("member");
		List<Map> viewVote = notifyService.viewVote(searchMap);
		return viewVote;
	}

	
	@GetMapping("/list")
	@ResponseBody
	public ModelAndView searchList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		//searchMap.put("p_id", p_id);	 
		HttpSession session = request.getSession();
		searchMap = (Map<String,Object>)session.getAttribute("member");
		List<Map> notifyList = notifyService.searchNotify(searchMap);
		List<Map> list = applyService.searchList(searchMap);
		ModelAndView mav = new ModelAndView("/personal/myHome");
		mav.addObject("notifyList", notifyList);
		mav.addObject("applylist", list);
		//System.out.println("notifyList값=="+notifyList);
		return mav;
		
	}
	@GetMapping("/replyList")
	@ResponseBody
	public ModelAndView replyList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		//searchMap.put("p_id", p_id);	 
		HttpSession session = request.getSession();
		searchMap = (Map<String,Object>)session.getAttribute("member");
		List<Map> replyList = notifyService.replyList(searchMap);
		ModelAndView mav = new ModelAndView("/personal/myHome");
		mav.addObject("replyList", replyList);
		System.out.println("뚜루뚜루");
		//System.out.println("replyList=="+replyList);
		return mav;
		
	}
	@RequestMapping("/voteList")
	public ModelAndView voteList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		//searchMap.put("p_id", p_id);	 
		HttpSession session = request.getSession();
		searchMap = (Map<String,Object>)session.getAttribute("member");
		List<Map> voteList = notifyService.viewVote(searchMap);
		ModelAndView mav = new ModelAndView("/personal/myHome");
		mav.addObject("voteList", voteList);
		return mav;
	}
	}

