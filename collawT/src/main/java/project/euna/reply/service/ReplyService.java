package project.euna.reply.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import project.euna.issue.vo.IssueVO;
import project.euna.reply.vo.ReplyVO;

public interface ReplyService {

	
	public List<Map> searchList(String i_Num) throws Exception;
	public void  replyInsert(ReplyVO replyVO);
	public void replyDelete(String r_Num) throws Exception;
	public void replyUpdate(ReplyVO replyVO) throws Exception;


}
