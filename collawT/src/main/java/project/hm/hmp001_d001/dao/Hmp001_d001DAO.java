package project.hm.hmp001_d001.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import project.hm.hmp001_d001.vo.Hmp001_d001VO;

public interface Hmp001_d001DAO {
	public List<Hmp001_d001VO> searchList(Map<String, Object> searchMap) throws DataAccessException;
	 public List<Hmp001_d001VO> searchMod(Map<String, Object> searchMap) throws DataAccessException;
	 public List<Hmp001_d001VO> searchAdd() throws DataAccessException;
	 
	 public void updateMember(Map<String, Object> datahMap) throws DataAccessException;
	 public void insertMember(Map<String, Object> datahMap) throws DataAccessException;
	 public void deleteMember(Map<String, Object> datahMap) throws DataAccessException;
	 
}
