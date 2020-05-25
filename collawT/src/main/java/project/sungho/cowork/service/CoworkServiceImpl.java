package project.sungho.cowork.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import project.sungho.apply.dao.ApplyDAO;
import project.sungho.comember.dao.ComemberDAO;
import project.sungho.cowork.dao.CoworkDAO;
import project.sungho.cowork.vo.CoworkVO;


@Service //서비스는 서비스 어노테이션을 작성해준다 
public class CoworkServiceImpl implements CoworkService {
	
	@Autowired //DAO객체를 메모리에 올려하는 어노테이션 new랑 같음
	private CoworkDAO coworkDAO; 
	
	@Autowired
	private ComemberDAO comemberDAO;
	
	@Autowired
	private ApplyDAO applyDAO;
	
	
	
	@Override
	public List<CoworkVO> searchList(Map<String, Object> searchMap) throws DataAccessException {
		
		List<CoworkVO> list =  coworkDAO.searchList(searchMap);  //DAO에 searchList함수 실행
		return list;
	}
	
	@Override
	public Map<String, Object> searchMain(Map<String, Object> searchMap) throws DataAccessException {
		
		Map<String, Object> pjt = coworkDAO.searchMain(searchMap);  //DAO에 searchList함수 실행
		return pjt;
	}

	@Override
	public void updateCowork(Map<String, Object> dataMap) throws Exception {
		coworkDAO.updateCowork(dataMap);
	}

	@Override
	public void insertCowork(Map<String, Object> dataMap) throws Exception {
		String c_Id = coworkDAO.selectSqc();
		dataMap.put("c_Id",c_Id);
		System.out.println(dataMap.toString());
		coworkDAO.insertCowork(dataMap);//DAO에 insertCowork함수 실행
		comemberDAO.insertComember(dataMap);
		
	}

	@Override
	public int deleteCowork(Map<String, Object> dataMap) throws Exception {
		comemberDAO.deleteComemberAll(dataMap);
		applyDAO.deleteApply(dataMap);
		int result = coworkDAO.deleteCowork(dataMap);
		if(result == 0 ) {
			System.out.println("삭제 실패!!");
		} else {
			System.out.println("삭제 성공!!");
		}
		
		return result;
	}

	@Override
	public List<Map> calendarlist(Map<String, Object> searchMap) throws DataAccessException {
		List<Map>list = coworkDAO.calendarlist(searchMap);
		return list;
	}
	
	@Override
	public List<Map> calendarvotelist(Map<String, Object> searchMap) throws DataAccessException {
		List<Map>list = coworkDAO.calendarvotelist(searchMap);
		return list;
	}
	
	@Override
	public List<Map> kanbanlist(Map<String, Object> searchMap) throws DataAccessException {
		List<Map>list = coworkDAO.kanbanlist(searchMap);
		return list;
	}
	
	@Override
	public int kanbanUpdate(Map<String, Object> searchMap) throws DataAccessException {
		int result = coworkDAO.kanbanUpdate(searchMap);
		return result;
	}
	
	
	

}
