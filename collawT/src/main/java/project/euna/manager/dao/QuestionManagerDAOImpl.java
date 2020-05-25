package project.euna.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.euna.issue.vo.IssueVO;
import project.hm.hmp002_d001.vo.Hmp002_d001VO;


@Repository
public class QuestionManagerDAOImpl implements QuestionManagerDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Map> searchList(Map<String, Object> searchMap) throws DataAccessException {
		List<Map> list = sqlSession.selectList("questionmanager.searchList", searchMap);
		return list;
	}

	@Override
	public void insertData(Map<String, String> row) throws DataAccessException {
		sqlSession.update("questionmanager.insertData", row);
	}

	@Override
	public void updateData(Map<String, String> row) throws DataAccessException {
		sqlSession.update("questionmanager.updateData", row);
	}

	@Override
	public void deleteData(Map<String, String> row) throws DataAccessException {
		sqlSession.update("questionmanager.deleteData", row);
	}
	
	@Override
	public Map<String, Object> questionRead(String q_Num) throws DataAccessException {
		Map<String, Object> list = sqlSession.selectOne("questionmanager.questionRead", q_Num);
		return list;
	}
	
	// 문의 답변 입력
	@Override
	public int answerInsert(Map map) {
		int result;
		result = sqlSession.update("questionmanager.answerInsert", map);
		return result;
	}
	
	//문의 상태 수정
	@Override
	public void questionStatusUpdate(Map map) throws Exception {
		sqlSession.update("questionmanager.questionStatusUpdate", map);
	}
	
	//이전 답변내역 조회
	@Override
	public List<Map> beforeAnswerList(String q_Num) throws DataAccessException {
		List<Map> list = sqlSession.selectList("questionmanager.beforeAnswerList", q_Num);
		return list;
	}


}
