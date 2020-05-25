package project.hm.hmp001_d001.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import project.hm.hmp001_d001.dao.Hmp001_d001DAO;
import project.hm.hmp001_d001.vo.Hmp001_d001VO;

@Service("hmp001_d001Service")
@Transactional(propagation = Propagation.REQUIRED)
public class Hmp001_d001ServiceImpl implements Hmp001_d001Service {
	@Autowired
	private Hmp001_d001DAO hmp001_d001DAO;

	@Override
	public List<Hmp001_d001VO> searchList(Map<String, Object> searchMap) throws DataAccessException {
		List<Hmp001_d001VO> list =  hmp001_d001DAO.searchList(searchMap); 
		return list;
	}

	@Override
	public List<Hmp001_d001VO> searchMod(Map<String, Object> searchMap) throws DataAccessException {
		List<Hmp001_d001VO> list =  hmp001_d001DAO.searchMod(searchMap);
		return list;
	}

	@Override
	public List<Hmp001_d001VO> searchAdd() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateMember(Map<String, Object> datahMap) throws Exception {
		hmp001_d001DAO.updateMember(datahMap);
	}

	@Override
	public void insertMember(Map<String, Object> datahMap) throws Exception {
		hmp001_d001DAO.insertMember(datahMap);
	}

	@Override
	public void deleteMember(Map<String, Object> datahMap) throws Exception {
		hmp001_d001DAO.deleteMember(datahMap);
	}

}
