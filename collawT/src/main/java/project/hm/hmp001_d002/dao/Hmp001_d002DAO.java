package project.hm.hmp001_d002.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import project.hm.hmp001_d002.vo.Hmp001_d002VO;

public interface Hmp001_d002DAO {
	 public List<Hmp001_d002VO> searchMember(Map<String, Object> searchMap) throws DataAccessException;
	 
	 public void updateMember(Map<String, Object> datahMap) throws DataAccessException;
	 public void insertMember(Map<String, Object> datahMap) throws DataAccessException;
	 public void deleteMember(Map<String, Object> datahMap) throws DataAccessException;
	 
}
