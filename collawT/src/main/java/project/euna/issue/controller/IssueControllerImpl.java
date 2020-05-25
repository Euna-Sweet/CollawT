package project.euna.issue.controller;


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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonObject;

import project.euna.appendix.service.AppendixService;
import project.euna.appendix.vo.AppendixVO;
import project.euna.issue.dao.IssueDAO;
import project.euna.issue.service.IssueService;
import project.euna.issue.vo.Criteria;
import project.euna.issue.vo.IssueVO;
import project.euna.issue.vo.PageMaker;
import project.euna.personal.service.PersonalService;
import project.euna.personal.service.Personal_appendixService;
import project.euna.personal.vo.PersonalVO;
import project.euna.reply.service.ReplyService;
import project.sungho.comember.service.ComemberService;
import project.sungho.cowork.service.CoworkService;

@Controller

@RequestMapping("/project/issue/*")
public class IssueControllerImpl implements IssueController {
	
	//
	@Inject
	IssueService issueService;
	
	@Inject
	AppendixService appendixService;
	
	@Inject
	ReplyService replyService;
	
	@Inject
	IssueDAO issueDAO;
	
	@Inject
	CoworkService coworkService;
	
	@Inject
	ComemberService comemberService;
	
	@Inject
	PersonalService personalService;

	@Inject
	Personal_appendixService personal_appendixService;


	
	//글 목록 조회 페이징
	@Override
	@GetMapping("/list")
	public ModelAndView searchList(IssueVO issueVO, Criteria cri, String c_Id, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String i_Num = issueVO.getI_Num();
			List<Map> list = issueService.searchList(cri);
			PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(issueService.listCount(c_Id));
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("c_Id", c_Id);
		Map<String, Object>pjt = coworkService.searchMain(searchMap);
		
	
		ModelAndView mav = new ModelAndView("issue/issueList");
		mav.addObject("issueList", list);
		mav.addObject("pageMaker", pageMaker);
		
		 mav.addObject("pjt", pjt); 
		
		
		return mav;
		
	}

	
	//글 쓰기 DB에 넣기
	@Override
	@PostMapping("/insert")
	public String issueInsert(IssueVO issueVO, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		Map<String, Object> member = new HashMap<String,Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String mem_Id = (String) member.get("mem_Id");
		String c_Id = issueVO.getC_Id();
		String i_Name = issueVO.getI_Name();
		String i_Content = issueVO.getI_Content();
		String i_Start = issueVO.getI_Start();
		String i_End = issueVO.getI_End();
		String ig_Num = issueVO.getig_Num();
		String i_Num = issueVO.getI_Num();
		
		
		Map<String, Object> cmap = new HashMap<String,Object>();
		cmap.put("mem_Id", mem_Id);
		cmap.put("c_Id", c_Id);
		cmap.put("i_Name", i_Name);
		cmap.put("i_Content",i_Content);
		cmap.put("i_Start", i_Start);
		cmap.put("i_End", i_End);
		cmap.put("ig_Num", ig_Num);
		cmap.put("i_Num", i_Num);

		
		issueService.issueInsert(cmap);
		
		
		try {
			Map<String, Object> coMap = new HashMap<String, Object>();
			String comem_Id[] = request.getParameterValues("comem_Id");
			
			
			for (int i = 0; i < comem_Id.length; i++) {
				
				coMap.put("i_Num", i_Num);
				coMap.put("c_Id", c_Id);
				coMap.put("mem_Id", comem_Id[i]);
				coMap.put("cr_Status", "");
				
				issueService.comemInsert(coMap);
				
				
			}
		}catch(NullPointerException e) {
		}

		
		
		
		
		return "redirect:/project/issue/read?c_Id="+c_Id+"&i_Num="+i_Num;
	
	}
	
	//글 쓰기 화면
	@Override
	@GetMapping("/insert")
	public ModelAndView issueInsert(String c_Id, HttpSession session) {
		Map<String, Object> i_Num = issueDAO.get_i_Num();

		
		List<Map> comemList = issueService.comemRead(c_Id);
		
				
		ModelAndView mav = new ModelAndView("/issue/issueInsert");
		mav.addObject("c_Id", c_Id);
		mav.addObject("comemList", comemList);
		mav.addObject("i_Num", i_Num);
		return mav;
		
	}
	

	
	
	//개별 글 조회
	@Override
	@GetMapping("/read")
	public ModelAndView issueRead(String i_Num, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Map<String, Object> member = new HashMap<String,Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String mem_Id = (String) member.get("mem_Id");
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		String c_Id = (String) request.getParameter("c_Id");
		searchMap.put("c_Id", c_Id);
		
		List<Map> coworkList = issueService.coRead(mem_Id);
		Map<String, Object> board = issueService.issueRead(i_Num);
		List<Map> list = appendixService.fileList(i_Num);
		List<Map> chargerList = issueService.chargerRead(i_Num);
		Map<String, Object> pjt = coworkService.searchMain(searchMap);
		
		ModelAndView mav = new ModelAndView("issue/issueRead");
		mav.addObject("issueRead", board);
		mav.addObject("pjt", pjt);
		mav.addObject("file", list);
		mav.addObject("chargerList", chargerList);
		mav.addObject("coworkList", coworkList);
		return mav;
	}

	
	//게시글 삭제
	@Override
	@GetMapping("/delete")
	public String issueDelete(String c_Id, String i_Num, IssueVO issueVO) throws Exception{
		
		appendixService.fileDelete(i_Num);
		issueService.chargerDelete(i_Num);
		issueService.replyDelete(i_Num);
		issueService.issueDelete(i_Num);
		
		
		return "redirect:/project/issue/list?c_Id="+c_Id;
	}
	
	//게시글 수정 페이지로 이동
	@Override
	@GetMapping("/update")
	public String issueUpdate(String c_Id, String i_Num, IssueVO issueVO, Model model, HttpSession session) throws Exception {
		
		
		
		List<Map> igRead = issueService.igRead();
		List<Map> comemList = issueService.comemRead(c_Id);
		List<Map> chargerList = issueService.chargerRead(i_Num);
		
		model.addAttribute("issueUpdate",issueService.issueRead(issueVO.getI_Num()) );
		model.addAttribute("igRead",igRead);
		model.addAttribute("c_Id", c_Id);
		model.addAttribute("comemList", comemList);
		model.addAttribute("chargerList", chargerList);
		
		//세션에 수정 전 파일 던지기
		List<Map> list = appendixService.fileList(i_Num);
		session.setAttribute("fileList", list);

		
		return "/issue/issueUpdate";
	}
	
	//이슈 담당자 목록 가져오기
	@GetMapping("/chargerList")
	@ResponseBody
	public List<Map> chargerList(@RequestParam ("i_Num")String i_Num) throws Exception {
		
		List<Map> chargerList = issueService.chargerRead(i_Num);
		
		return chargerList;
	}
	
	
	//게시글 수정 db에 넣기
	@Override
	@PostMapping("/update")
	public String issueUpdate(IssueVO issueVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		issueService.issueUpdate(issueVO);
		
		String i_Num = issueVO.getI_Num();
		String c_Id = issueVO.getC_Id();
		
		
		//이슈담당자 삭제 후 다시 입력
		issueService.chargerDelete(i_Num);
		
		try {
			Map<String, Object> coMap = new HashMap<String, Object>();
			String comem_Id[] = request.getParameterValues("comem_Id");
			for (int i = 0; i < comem_Id.length; i++) {
					
					coMap.put("i_Num", i_Num);
					coMap.put("c_Id", c_Id);
					coMap.put("mem_Id", comem_Id[i]);
					coMap.put("cr_Status", "");
					
					issueService.comemInsert(coMap);
			}
		
		}catch(NullPointerException e) {
		}
		
		
		//appedixService.updateFile(issueVO, appendixVO, files, fileNames, mpRequest);
		
		
		//수정한 게시물로 리턴
		return "redirect:/project/issue/read?c_Id="+c_Id+"&i_Num="+i_Num;
	}
	
	
	//다른 협업공간으로 복제 글 쓰기 DB에 넣기
	@Override
	@PostMapping("/copy")
	public String issueCopy(PersonalVO personalVO, IssueVO issueVO, HttpSession session, HttpServletRequest request, HttpServletResponse response, RedirectAttributes rttr) throws Exception {
		Map<String, Object> member = new HashMap<String,Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String mem_Id = (String) member.get("mem_Id");
		String i_Num = issueVO.getI_Num();
		String p_Num = personalVO.getP_Num();
		
		
		String coworkSelect = request.getParameter("coworkSelect");
		if(coworkSelect.equals("myspace") ) {

			String p_i_Name = issueVO.getI_Name();
			String p_i_Content = issueVO.getI_Content();
			String p_i_Start = issueVO.getI_Start();
			String p_i_End = issueVO.getI_End();
			
			Map<String, Object> cmap = new HashMap<String,Object>();
			cmap.put("mem_Id", mem_Id);
			cmap.put("p_i_Name", p_i_Name);
			cmap.put("p_i_Content", p_i_Content);
			cmap.put("p_i_Start",p_i_Start);
			cmap.put("p_i_End", p_i_End);
		
			personalService.personalCopy(cmap);
			String redirectp_Num = (String) cmap.get("p_Num");
			
			Map<String, Object> hmap = new HashMap<String,Object>();
			hmap.put("i_Num", i_Num);

			hmap.put("redirectp_Num", redirectp_Num);

			personal_appendixService.copyFile(hmap);
			
			rttr.addFlashAttribute("msg", "success");

			return "redirect:/personal/read?p_Num="+redirectp_Num;
			
						
		}else {

			String c_Id = issueVO.getC_Id();
			String i_Name = issueVO.getI_Name();
			String i_Content = issueVO.getI_Content();
			String i_Start = issueVO.getI_Start();
			String i_End = issueVO.getI_End();
			String ig_Num = issueVO.getig_Num();
			
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
			hmap.put("i_Num", i_Num);
			hmap.put("redirecti_Num", redirecti_Num);

			appendixService.copyFile(hmap);
			rttr.addFlashAttribute("msg", "success");

			return "redirect:/project/issue/read?c_Id="+c_Id+"&i_Num="+redirecti_Num;
		}
	
	}




}





