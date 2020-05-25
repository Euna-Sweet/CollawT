package project.euna.manager.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import project.hm.hmp002_d001.vo.Hmp002_d001VO;

public interface QuestionManagerDAO {
	 public List<Map> searchList(Map<String, Object> searchMap) throws DataAccessException;

	public void insertData(Map<String, String> row) throws DataAccessException;

	public void updateData(Map<String, String> row) throws DataAccessException;

	public void deleteData(Map<String, String> row) throws DataAccessException;
	public Map<String, Object> questionRead(String q_Num) throws DataAccessException; 
	public int answerInsert(Map map);
	public void questionStatusUpdate(Map map) throws Exception;
	public List<Map> beforeAnswerList(String q_Num) throws DataAccessException;
}
