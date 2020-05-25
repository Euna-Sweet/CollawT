package project.jeongha.question.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionDaoImpl implements QuestionDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertQuestion(Map<String, Object> insertThing) {
		
		return sqlSession.insert("question.questionInsert", insertThing);
		
	}

}
