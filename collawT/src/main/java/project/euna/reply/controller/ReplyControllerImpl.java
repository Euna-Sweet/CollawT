package project.euna.reply.controller;


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

import project.euna.reply.service.ReplyService;
import project.euna.reply.vo.ReplyVO;
import project.jeongha.member.dao.MemberDao;

@Controller

@RequestMapping("/reply/*")
public class ReplyControllerImpl implements ReplyController {
	
	@Inject
	ReplyService replyService;
	
	@Inject
	MemberDao memberDAO;
	
	//댓글 쓰기 DB에 넣기
	@Override
	@PostMapping("/insert")
	@ResponseBody
	public String replyInsert(@RequestParam ("i_Num")String i_Num, @RequestParam ("c_Id")String c_Id, ReplyVO replyVO, HttpSession session) throws Exception {
		Map<String, Object> member = new HashMap<String,Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		String mem_Id = (String) member.get("mem_Id");
		
		replyVO.setMem_Id(mem_Id);
		replyVO.setI_Num(i_Num);
		replyVO.setC_Id(c_Id);
		
		
		replyService.replyInsert(replyVO);
	
		return "redirect:/reply/list";
	
	}
	
	//댓글 목록
	@Override
	@GetMapping("/list")
	@ResponseBody
	public List<Map> searchList(@RequestParam ("i_Num")String i_Num, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		List<Map> list=replyService.searchList(i_Num);
		return list;
		
	}


	
	//댓글 삭제
	@Override
	@PostMapping("/delete")
	@ResponseBody
	public void replyDelete(ReplyVO replyVO) throws Exception{
		String r_Num = replyVO.getR_Num();
	
		replyService.replyDelete(r_Num);
		
	}
	

	
	//게시글 수정 db에 넣기
	@Override
	@PostMapping("/update")
	@ResponseBody
	public void replyUpdate(ReplyVO replyVO) throws Exception {
		replyService.replyUpdate(replyVO);
		
	}
	

	


}
