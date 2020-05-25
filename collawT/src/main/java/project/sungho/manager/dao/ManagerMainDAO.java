package project.sungho.manager.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import project.hm.hmp002_d001.vo.Hmp002_d001VO;

public interface ManagerMainDAO {
	public List<Map> searchList() throws DataAccessException;
	 
	 public int memCount() throws DataAccessException;

	 public List<Map> donutList() throws DataAccessException;
	 
	 public List<Map> lineList() throws DataAccessException;
	 
	 
}
