package project.jeongha.reply.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import project.jeongha.reply.vo.VoteReplyVO;


public interface VoteReplyDao {

	public List<Map> searchList(String v_Num) throws DataAccessException;
	public void replyInsert(VoteReplyVO voteReplyVO);
	public void replyDelete(String vr_Num) throws Exception;
	public void replyUpdate(VoteReplyVO voteReplyVO) throws Exception;
	
	
}
