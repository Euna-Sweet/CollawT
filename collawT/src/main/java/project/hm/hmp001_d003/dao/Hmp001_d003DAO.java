package project.hm.hmp001_d003.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import project.hm.hmp001_d003.vo.Hmp001_d003VO;

public interface Hmp001_d003DAO {
	public List<Hmp001_d003VO> searchList(Map<String, Object> searchMap) throws DataAccessException;
	public List<Hmp001_d003VO> searchMod(Map<String, Object> searchMap) throws DataAccessException;
	public List<Hmp001_d003VO> searchAdd() throws DataAccessException;
	
	public void updateProduct(Map<String, Object> datahMap) throws DataAccessException;
	public void insertProduct(Map<String, Object> datahMap) throws DataAccessException;
	public void deleteProduct(Map<String, Object> datahMap) throws DataAccessException;
}
