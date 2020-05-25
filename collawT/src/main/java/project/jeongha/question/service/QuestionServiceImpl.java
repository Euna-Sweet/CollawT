package project.jeongha.question.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.jeongha.question.dao.QuestionDao;

@Service
public class QuestionServiceImpl implements QuesttionService{

	
	@Autowired
	QuestionDao qDao;
	
	@Override
	public int insertQuestion(Map<String, Object> insertThing) {
		return qDao.insertQuestion(insertThing);
		
		
		
		
		
	}

}
