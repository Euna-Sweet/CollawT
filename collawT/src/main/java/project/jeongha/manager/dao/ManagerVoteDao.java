package project.jeongha.manager.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface ManagerVoteDao {
	
	 	public List<Map> searchList(Map<String, Object> searchMap) throws DataAccessException;
		public void insertData(Map<String, String> row) throws DataAccessException;
		public void updateData(Map<String, String> row) throws DataAccessException;
		public void deleteData(Map<String, String> row) throws DataAccessException;
		
}
