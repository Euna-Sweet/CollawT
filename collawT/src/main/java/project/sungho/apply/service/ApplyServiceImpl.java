package project.sungho.apply.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import project.sungho.apply.dao.ApplyDAO;
import project.sungho.apply.vo.ApplyVO;



@Service
public class ApplyServiceImpl implements ApplyService{
	
	@Autowired
	ApplyDAO applyDAO;


	@Override
	public List<Map> viewCount(Map<String, Object> searchMap) throws DataAccessException {
		List<Map> list = applyDAO.viewCount(searchMap);
		return list;
	}
	
	@Override
	public List<Map> searchList(Map<String, Object> searchMap) throws DataAccessException {

		List<Map> list =  applyDAO.searchList(searchMap);  //DAO에 searchList함수 실행
			return list;
		}

	@Override
	public void updateApply(Map<String, Object> dataMap) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertApply(Map<String, Object> dataMap) throws Exception {
		// TODO Auto-generated method stub
		applyDAO.insertApply(dataMap);
	}

	@Override
	public void deleteApply(Map<String, Object> dataMap) throws Exception {
		// TODO Auto-generated method stub
		applyDAO.deleteApply(dataMap);
		
	}

	
	@Override
	public int memberCheck(String mem_Id) throws Exception {
		// TODO Auto-generated method stub
		return applyDAO.memberCheck(mem_Id);
		 
	}
	
	//초대 수락 서비스
	@Override
	public int acceptApply(Map<String, Object> dataMap) throws Exception {
		return applyDAO.acceptApply(dataMap);
	}
	
	//초대 거절 서비스 
	@Override
	public int rejectApply(Map<String, Object> dataMap) throws Exception {
		return applyDAO.rejectApply(dataMap);
	}

}
