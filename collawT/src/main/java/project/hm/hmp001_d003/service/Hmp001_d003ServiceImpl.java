package project.hm.hmp001_d003.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import project.hm.hmp001_d003.dao.Hmp001_d003DAO;
import project.hm.hmp001_d003.vo.Hmp001_d003VO;

@Service("hmp001_d003Service")
@Transactional(propagation = Propagation.REQUIRED)
public class Hmp001_d003ServiceImpl implements Hmp001_d003Service {
	@Autowired
	private Hmp001_d003DAO hmp001_d003DAO;

	@Override
	public List<Hmp001_d003VO> searchList(Map<String, Object> searchMap) throws DataAccessException {
		List<Hmp001_d003VO> list =  hmp001_d003DAO.searchList(searchMap); 
		return list;
	}

	@Override
	public List<Hmp001_d003VO> searchMod(Map<String, Object> searchMap) throws DataAccessException {
		List<Hmp001_d003VO> list =  hmp001_d003DAO.searchMod(searchMap);
		return list;
	}

	@Override
	public List<Hmp001_d003VO> searchAdd() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProduct(Map<String, Object> datahMap) throws Exception {
		hmp001_d003DAO.updateProduct(datahMap);
	}

	@Override
	public void insertProduct(Map<String, Object> datahMap) throws Exception {
		hmp001_d003DAO.insertProduct(datahMap);
	}

	@Override
	public void deleteProduct(Map<String, Object> datahMap) throws Exception {
		hmp001_d003DAO.deleteProduct(datahMap);
	}
}
