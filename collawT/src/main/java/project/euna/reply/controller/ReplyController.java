package project.euna.reply.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import project.euna.reply.vo.ReplyVO;

public interface ReplyController {
	public String replyInsert(@RequestParam ("i_Num")String i_Num, @RequestParam ("c_Id")String c_Id, ReplyVO replyVO, HttpSession session) throws Exception;
	public List<Map> searchList(@RequestParam ("i_Num")String i_Num, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public void replyDelete(ReplyVO replyVO) throws Exception;
	public void replyUpdate(ReplyVO replyVO) throws Exception;
}
