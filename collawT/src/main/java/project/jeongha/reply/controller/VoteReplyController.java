package project.jeongha.reply.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;

import project.euna.reply.vo.ReplyVO;
import project.jeongha.reply.vo.VoteReplyVO;

public interface VoteReplyController {

	
	public String voteReplyInsert(@RequestParam ("v_Num")String v_Num, @RequestParam ("c_Id")String c_Id, VoteReplyVO voteReplyVO, HttpSession session) throws Exception;
	public List<Map> voteSearchList(@RequestParam ("v_Num")String v_Num, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public void voteReplyDelete(String vr_Num) throws Exception;
	public void voteReplyUpdate(VoteReplyVO voteReplyVO) throws Exception;
	
}
