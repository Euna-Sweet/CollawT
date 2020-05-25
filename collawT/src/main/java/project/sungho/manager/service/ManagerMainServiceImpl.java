package project.sungho.manager.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import project.sungho.manager.dao.ManagerMainDAO;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ManagerMainServiceImpl implements ManagerMainService {
	@Autowired
	private ManagerMainDAO managerMainDAO;

	@Override
	public List<Map> searchList() throws DataAccessException {
		List<Map> list =  managerMainDAO.searchList(); 
		return list;
	}

	@Override
	public int memCount() throws DataAccessException {
		int result = managerMainDAO.memCount();
		return result;
	}

	@Override
	public List<Map> donutList() throws DataAccessException {
		List<Map> list =  managerMainDAO.donutList(); 
		return list;
	}

	@Override
	public List<Map> lineList() throws DataAccessException {
		List<Map> list =  managerMainDAO.lineList(); 
		return list;
	}


}
