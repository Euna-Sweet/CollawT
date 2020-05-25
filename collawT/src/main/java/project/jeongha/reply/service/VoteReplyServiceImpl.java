package project.jeongha.reply.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.jeongha.reply.dao.VoteReplyDao;
import project.jeongha.reply.vo.VoteReplyVO;
@Service
public class VoteReplyServiceImpl implements VoteReplyService{

	@Autowired
	private VoteReplyDao voteReplyDao;
	
	@Override
	public List<Map> voteSearchList(String v_Num) throws Exception {
		List<Map> list = voteReplyDao.searchList(v_Num);
		System.out.println("service:"+list);
		return list;
	}

	@Override
	public void voteReplyInsert(VoteReplyVO voteReplyVO) {
		//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!service :"+voteReplyVO.getC_Id());
		//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!service :"+voteReplyVO.getVr_Content());
		voteReplyDao.replyInsert(voteReplyVO);
		
	}

	@Override
	public void voteReplyDelete(String vr_Num) throws Exception {
		voteReplyDao.replyDelete(vr_Num);
		
	}

	@Override
	public void voteReplyUpdate(VoteReplyVO voteReplyVO) throws Exception {
		voteReplyDao.replyUpdate(voteReplyVO);
		
	}

	

}
