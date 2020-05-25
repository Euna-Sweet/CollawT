package project.jeongha.reply.service;

import java.util.List;
import java.util.Map;

import project.euna.reply.vo.ReplyVO;
import project.jeongha.reply.vo.VoteReplyVO;

public interface VoteReplyService {


	public List<Map> voteSearchList(String v_Num) throws Exception;
	public void voteReplyInsert(VoteReplyVO voteReplyVO);
	public void voteReplyDelete(String vr_Num) throws Exception;
	public void voteReplyUpdate(VoteReplyVO voteReplyVO) throws Exception;

}
