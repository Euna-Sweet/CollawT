package project.sungho.cowork.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import project.sungho.cowork.vo.CoworkVO;


public interface CoworkService {
	 public List<CoworkVO> searchList(Map<String, Object> searchMap) throws DataAccessException;
	 public Map<String, Object> searchMain(Map<String, Object> searchMap) throws DataAccessException;
	 
	 public void updateCowork(Map<String, Object> dataMap) throws Exception;
	 public void insertCowork(Map<String, Object> dataMap) throws Exception;
	 public int deleteCowork(Map<String, Object> dataMap) throws Exception;
	 
	 public List<Map> calendarlist(Map<String, Object> searchMap)throws DataAccessException;
	 public List<Map> calendarvotelist(Map<String, Object> searchMap)throws DataAccessException;
	 
	 public List<Map> kanbanlist(Map<String, Object> searchMap)throws DataAccessException;
	 public int kanbanUpdate(Map<String, Object> searchMap)throws DataAccessException;
}
