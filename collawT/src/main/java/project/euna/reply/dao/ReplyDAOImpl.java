package project.euna.reply.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.euna.reply.vo.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//댓글 목록
		@Override
		public List<Map> searchList(String i_Num) throws DataAccessException{
			
			List<Map> list = sqlSession.selectList("reply.searchList",i_Num);
			System.out.println("dao:"+list);
			return list;
		
		}
		
		
	//댓글 입력
	@Override
	public void replyInsert(ReplyVO replyVO) {
		sqlSession.update("reply.replyInsert", replyVO);
		String r_Num  = replyVO.getR_Num();
		replyVO.setR_Num(r_Num);
		sqlSession.update("notify.replyInsertNotify",replyVO);
	}


	//댓글 삭제
	@Override
	public void replyDelete(String r_Num) throws Exception {
		sqlSession.delete("reply.replyDelete", r_Num);
		sqlSession.update("notify.replyUpdate", r_Num);
	}
	
	//댓글 수정
	@Override
	public void replyUpdate(ReplyVO replyVO) throws Exception {
		sqlSession.update("reply.replyUpdate", replyVO);
	}
	
}
