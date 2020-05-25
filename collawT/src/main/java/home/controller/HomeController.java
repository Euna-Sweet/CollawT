package home.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import project.euna.personal.service.PersonalService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Inject
	PersonalService personalService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response, Object handler) {
		
		
		HttpSession session = request.getSession();
		Map<String, Object> member = new HashMap<String,Object>();
		
		member = (Map<String, Object>) session.getAttribute("member");
		
		String uri = (String) request.getRequestURI();
		if (member != null) {
			if (member.get("mem_Kind").equals("03")) {
				return "redirect:/manager/member/main";
			}else {return "redirect:/main";}
			}else {
		
		return "/index";
	}}

	@RequestMapping("/join")
	public String join(Model model) {
		
		return "/sample/joinForm";
	}
	
	
	
	@RequestMapping("/issueadd")
	public String projectadd(Model model) {

		return "/issue/issueadd";
	}
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, HttpServletResponse response) throws Exception{
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/main")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Map<String, Object> member = new HashMap<String,Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String mem_Id = (String) member.get("mem_Id");
		
		List<Map> newspeed = personalService.newspeed(mem_Id);
		List<Map> voteAvailable = personalService.voteAvailable(mem_Id);
		List<Map> myissue = personalService.myissue(mem_Id);
		
		//System.out.println("!!!!!!!!!!!!!!!!!!!"+newspeed);
		
		ModelAndView mav = new ModelAndView("personal/myHome");
		mav.addObject("newspeed", newspeed);
		mav.addObject("voteAvailable", voteAvailable);
		mav.addObject("myissue", myissue);
		
		return mav;
		
	}	
	
	
	@RequestMapping("/chat")
	public String chat(Model model) {
		return "/chat/chat-ws";
	}
	
	
}
