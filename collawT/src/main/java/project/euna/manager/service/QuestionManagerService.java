package project.euna.manager.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import project.hm.hmp002_d001.vo.Hmp002_d001VO;

public interface QuestionManagerService {
	 public List<Map> searchList(Map<String, Object> searchMap) throws DataAccessException;

	public void saveData(Map<String, String[]> dataMap)  throws DataAccessException ;
	public Map<String, Object> questionRead(String q_Num) throws DataAccessException;
	public void  answerInsert(Map map);
	public void questionStatusUpdate(Map map) throws Exception;
	public List<Map> beforeAnswerList(String q_Num) throws DataAccessException;
}
