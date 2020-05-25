package project.sungho.cowork.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import project.sungho.cowork.vo.CoworkVO;

public interface CoworkDAO {
	 public List<CoworkVO> searchList(Map<String, Object> searchMap) throws DataAccessException;
	 
	 public void updateCowork(Map<String, Object> dataMap) throws DataAccessException;
	 public void insertCowork(Map<String, Object> dataMap) throws DataAccessException;
	 public int deleteCowork(Map<String, Object> dataMap) throws DataAccessException;
	 
	 public String selectSqc() throws DataAccessException;

	 Map<String, Object> searchMain(Map<String, Object> searchMap) throws DataAccessException;;
	 
	 public List<Map> calendarlist(Map<String, Object> searchMap)throws DataAccessException;
	 
	 public List<Map> calendarvotelist(Map<String, Object> searchMap)throws DataAccessException;
	 
	 public List<Map> kanbanlist(Map<String, Object> searchMap)throws DataAccessException;
	 
	 public int kanbanUpdate(Map<String, Object> searchMap)throws DataAccessException;

}
