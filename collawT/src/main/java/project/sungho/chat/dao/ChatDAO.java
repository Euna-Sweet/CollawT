package project.sungho.chat.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface ChatDAO {

	public List<Map> searchList(Map<String, Object> searchMap) throws DataAccessException;

	public void readingMsg(Map<String, Object> searchMap) throws DataAccessException;

	public void insertChat(Map<String, Object> dataMap) throws DataAccessException;

	public void logoutinsertChat(Map<String, Object> dataMap) throws DataAccessException;

	public int deleteChat(Map<String, Object> dataMap) throws DataAccessException;
	
	public List<Map> msgCount(Map<String, Object> searchMap) throws DataAccessException;
	
	public int totalCount(Map<String, Object> searchMap) throws DataAccessException;
} 
