package project.jeongha.reply.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.jeongha.reply.vo.VoteReplyVO;

@Repository
public class VoteReplyDaoImpl  implements VoteReplyDao{

	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public List<Map> searchList(String v_Num) throws DataAccessException {
		
		List<Map> list = sqlSession.selectList("voteReply.searchList",v_Num);
		System.out.println("dao:"+list);
		return list;
	}

	@Override
	public void replyInsert(VoteReplyVO voteReplyVO) {
		sqlSession.update("voteReply.replyInsert", voteReplyVO);
		String vr_Num = voteReplyVO.getVr_Num();
		voteReplyVO.setVr_Num(vr_Num);
		sqlSession.update("notify.votereplyInsertNotify", voteReplyVO);


	}

	@Override
	public void replyDelete(String vr_Num) throws Exception {
		sqlSession.delete("voteReply.replyDelete", vr_Num);
		sqlSession.update("notify.votereplyUpdate", vr_Num);

		
	}

	@Override
	public void replyUpdate(VoteReplyVO voteReplyVO) throws Exception {
		sqlSession.update("voteReply.replyUpdate", voteReplyVO);
		
	}

}
