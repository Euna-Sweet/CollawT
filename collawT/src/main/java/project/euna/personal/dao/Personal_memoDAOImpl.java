package project.euna.personal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.euna.personal.vo.Personal_memoVO;


@Repository
public class Personal_memoDAOImpl implements Personal_memoDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//댓글 목록
		@Override
		public List<Map> searchList(String p_Num) throws DataAccessException{
			
			List<Map> list = sqlSession.selectList("personal_memo.searchList",p_Num);
			return list;
		
		}
		
		
	//댓글 입력
	@Override
	public int personal_memoInsert(Personal_memoVO personal_memoVO) {
		int result;
		result = sqlSession.update("personal_memo.personal_memoInsert", personal_memoVO);
		return result;
	}


	//댓글 삭제
	@Override
	public void personal_memoDelete(String p_m_Num) throws Exception {
		sqlSession.delete("personal_memo.personal_memoDelete", p_m_Num);
		
	}
	
	//댓글 수정
	@Override
	public void personal_memoUpdate(Personal_memoVO personal_memoVO) throws Exception {
		sqlSession.update("personal_memo.personal_memoUpdate", personal_memoVO);
	}
	
}
