package project.euna.personal.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.euna.appendix.service.AppendixService;
import project.euna.issue.service.IssueService;
import project.euna.issue.vo.IssueVO;
import project.euna.personal.dao.PersonalDAO;
import project.euna.personal.service.PersonalService;
import project.euna.personal.service.Personal_appendixService;
import project.euna.personal.vo.Criteria;
import project.euna.personal.vo.PersonalVO;
import project.euna.personal.vo.PageMaker;
import project.euna.reply.service.ReplyService;
import project.notify.service.NotifyService;
import project.sungho.apply.service.ApplyService;
import project.sungho.comember.service.ComemberService;
import project.sungho.cowork.service.CoworkService;

@Controller

@RequestMapping("/personal/*")
public class PersonalControllerImpl implements PersonalController {
	
	//
	@Inject
	PersonalService personalService;
	

	
	@Inject
	PersonalDAO personalDAO;
	
	
	@Inject
	Personal_appendixService personal_appendixService;
	
	@Inject
	IssueService issueService;
	
	@Inject
	ApplyService applyService;
	
	@Inject
	NotifyService notifyService;
	
	@Inject
	AppendixService appendixService;



	//홈 화면
	@Override
	@GetMapping("/main")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Map<String, Object> member = new HashMap<String,Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String mem_Id = (String) member.get("mem_Id");
		
		List<Map> newspeed = personalService.newspeed(mem_Id);
		List<Map> voteAvailable = personalService.voteAvailable(mem_Id);

		
		ModelAndView mav = new ModelAndView("personal/myHome");
		mav.addObject("newspeed", newspeed);
		mav.addObject("voteAvailable", voteAvailable);

		
		return mav;
		
	}	
	
	
	
	
	
	
	//글 목록 조회 페이징
	@Override
	@GetMapping("/list")
	public ModelAndView searchList(PersonalVO personalVO, Criteria cri, String mem_Id, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			List<Map> list = personalService.searchList(cri);
			PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(personalService.listCount(mem_Id));
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		
	
		ModelAndView mav = new ModelAndView("personal/personalList");
		mav.addObject("personalList", list);
		mav.addObject("pageMaker", pageMaker);
	
		return mav;
		
	}

	
	//글 쓰기 DB에 넣기
	@Override
	@PostMapping("/insert")
	public String personalInsert(PersonalVO personalVO, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		Map<String, Object> member = new HashMap<String,Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String p_Num = personalVO.getP_Num();
		String mem_Id = (String) member.get("mem_Id");
		String p_i_Name = personalVO.getP_i_Name();
		String p_i_Content = personalVO.getP_i_Content();
		String p_i_Date = personalVO.getP_i_Date();
		String p_i_Start = personalVO.getP_i_Start();
		String p_i_End = personalVO.getP_i_End();
		
		
		Map<String, Object> cmap = new HashMap<String,Object>();
		cmap.put("p_Num", p_Num);
		cmap.put("mem_Id", mem_Id);
		cmap.put("p_i_Name", p_i_Name);
		cmap.put("p_i_Content",p_i_Content);
		cmap.put("p_i_Date", p_i_Date);
		cmap.put("p_i_Start", p_i_Start);
		cmap.put("p_i_End", p_i_End);

		
		personalService.personalInsert(cmap);
		
		

		
		return "redirect:/personal/read?mem_Id="+mem_Id+"&p_Num="+p_Num;
	
	}
	
	//글 쓰기 화면
	@Override
	@GetMapping("/insert")
	public ModelAndView personalInsert(String mem_Id, HttpSession session) {
		Map<String, Object> p_Num = personalDAO.get_p_Num();
			
		ModelAndView mav = new ModelAndView("/personal/personalInsert");
		mav.addObject("mem_Id", mem_Id);
		mav.addObject("p_Num", p_Num);
		return mav;
		
	}
//	
//
//	
//	
	//개별 글 조회
	@Override
	@GetMapping("/read")
	public ModelAndView personalRead(String p_Num, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Map<String, Object> member = new HashMap<String,Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String mem_Id = (String) member.get("mem_Id");
		
		List<Map> coworkList = issueService.coRead(mem_Id);
		Map<String, Object> board = personalService.personalRead(p_Num);
		List<Map> list = personal_appendixService.fileList(p_Num);		
		
		ModelAndView mav = new ModelAndView("personal/personalRead");
		mav.addObject("personalRead", board);
		mav.addObject("file", list);
		mav.addObject("coworkList", coworkList);

		return mav;
	}
//
//	
//	//게시글 삭제
	@Override
	@GetMapping("/delete")
	public String personalDelete(String mem_Id, String p_Num, PersonalVO personalVO) throws Exception{
		personal_appendixService.fileDelete(p_Num);
		personalService.personalmemoDelete(p_Num);
		personalService.personalDelete(personalVO.getP_Num());

		
		
		return "redirect:/personal/list?mem_Id="+mem_Id;
	}
	
	//게시글 수정 페이지로 이동
	@Override
	@GetMapping("/update")
	public String personalUpdate(String mem_Id, String p_Num, PersonalVO personalVO, Model model, HttpSession session) throws Exception {
		
		
		
		model.addAttribute("personalUpdate",personalService.personalRead(personalVO.getP_Num()) );
		model.addAttribute("mem_Id", mem_Id);
		
		//세션에 수정 전 파일 던지기
		List<Map> list = personal_appendixService.fileList(p_Num);
		session.setAttribute("personalfileList", list);

		
		return "/personal/personalUpdate";
	}

//	
//	
	//게시글 수정 db에 넣기
	@Override
	@PostMapping("/update")
	public String personalUpdate(PersonalVO personalVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		personalService.personalUpdate(personalVO);
		
		String p_Num = personalVO.getP_Num();
		String mem_Id = personalVO.getMem_Id();
		

		
		//수정한 게시물로 리턴
		return "redirect:/personal/read?mem_Id="+mem_Id+"&p_Num="+p_Num;
	}
//	
//	
	//다른 협업공간으로 복제 글 쓰기 DB에 넣기
	@Override
	@PostMapping("/copy")
	public String issueCopy(IssueVO issueVO, HttpSession session, HttpServletRequest request, HttpServletResponse response, RedirectAttributes rttr) throws Exception {
		Map<String, Object> member = new HashMap<String,Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String mem_Id = (String) member.get("mem_Id");
		String p_Num = request.getParameter("p_Num");

		
		String coworkSelect = request.getParameter("coworkSelect");
	
			String c_Id = request.getParameter("c_Id");
			String i_Name = request.getParameter("p_i_Name");
			String i_Content = request.getParameter("p_i_Content");
			String i_Start = request.getParameter("p_i_Start");
			String i_End = request.getParameter("p_i_End");
			String ig_Num = "1";
			
			Map<String, Object> cmap = new HashMap<String,Object>();
			cmap.put("mem_Id", mem_Id);
			cmap.put("c_Id", c_Id);
			cmap.put("i_Name", i_Name);
			cmap.put("i_Content",i_Content);
			cmap.put("i_Start", i_Start);
			cmap.put("i_End", i_End);
			cmap.put("ig_Num", ig_Num);
			
			issueService.issueCopy(cmap);
			String redirecti_Num = (String) cmap.get("i_Num");
			
			Map<String, Object> hmap = new HashMap<String,Object>();
			hmap.put("p_Num", p_Num);
			hmap.put("redirecti_Num", redirecti_Num);

			personal_appendixService.copyFiletoCowork(hmap);
			rttr.addFlashAttribute("msg", "success");

			return "redirect:/project/issue/read?c_Id="+c_Id+"&i_Num="+redirecti_Num;
		
	
	}


}





