package project.euna.personal.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.euna.personal.service.Personal_memoService;
import project.euna.personal.vo.Personal_memoVO;
import project.jeongha.member.dao.MemberDao;

@Controller

@RequestMapping("/personal/memo/*")
public class Personal_memoControllerImpl implements Personal_memoController {
	
	@Inject
	Personal_memoService personal_memoService;
	
	@Inject
	MemberDao memberDAO;
	
	//댓글 쓰기 DB에 넣기
	@Override
	@PostMapping("/insert")
	@ResponseBody
	public String personal_memoInsert(Personal_memoVO personal_memoVO, HttpSession session) throws Exception {
		Map<String, Object> member = new HashMap<String,Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String mem_Id = (String) member.get("mem_Id");
		
		personal_memoVO.setMem_Id(mem_Id);

		
		personal_memoService.personal_memoInsert(personal_memoVO);
				
		return "redirect:/personal/memo/list";
	
	}
	
	//댓글 목록
	@Override
	@GetMapping("/list")
	@ResponseBody
	public List<Map> searchList(@RequestParam ("p_Num")String p_Num, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		List<Map> list=personal_memoService.searchList(p_Num);
		
		return list;
		
	}


	
	//댓글 삭제
	@Override
	@PostMapping("/delete")
	@ResponseBody
	public void personal_memoDelete(String p_m_Num) throws Exception{
		personal_memoService.personal_memoDelete(p_m_Num);
		
	}
	

	
	//게시글 수정 db에 넣기
	@Override
	@PostMapping("/update")
	@ResponseBody
	public void personal_memoUpdate(Personal_memoVO personal_memoVO) throws Exception {
		personal_memoService.personal_memoUpdate(personal_memoVO);
		
	}
	

	


}
