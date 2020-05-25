package project.jeongha.manager.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface ManagerVoteService {
	 public List<Map> searchList(Map<String, Object> searchMap) throws DataAccessException;

		public void saveData(Map<String, String[]> dataMap)  throws DataAccessException ;
}
