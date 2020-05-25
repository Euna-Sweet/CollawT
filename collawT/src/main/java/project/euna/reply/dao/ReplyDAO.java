package project.euna.reply.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import project.euna.issue.vo.IssueVO;
import project.euna.reply.vo.ReplyVO;

public interface ReplyDAO {

	public List<Map> searchList(String i_Num) throws DataAccessException;
	public void replyInsert(ReplyVO replyVO);
	public void replyDelete(String r_Num) throws Exception;
	public void replyUpdate(ReplyVO replyVO) throws Exception;

}
