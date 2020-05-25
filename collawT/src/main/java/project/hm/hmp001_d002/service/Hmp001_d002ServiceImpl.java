package project.hm.hmp001_d002.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import project.hm.hmp001_d002.dao.Hmp001_d002DAO;
import project.hm.hmp001_d002.vo.Hmp001_d002VO;

@Service("hmp001_d002Service")
@Transactional(propagation = Propagation.REQUIRED)
public class Hmp001_d002ServiceImpl implements Hmp001_d002Service {
	@Autowired
	private Hmp001_d002DAO hmp001_d002DAO;

	@Override
	public List<Hmp001_d002VO> searchMember(Map<String, Object> searchMap) throws DataAccessException {
		List<Hmp001_d002VO> list =  hmp001_d002DAO.searchMember(searchMap); 
		return list;
	}

	@Override
	public void updateMember(Map<String, Object> datahMap) throws Exception {
		hmp001_d002DAO.updateMember(datahMap);
	}

	@Override
	public void insertMember(Map<String, Object> datahMap) throws Exception {
		hmp001_d002DAO.insertMember(datahMap);
	}

	@Override
	public void deleteMember(Map<String, Object> datahMap) throws Exception {
		hmp001_d002DAO.deleteMember(datahMap);
	}

}
