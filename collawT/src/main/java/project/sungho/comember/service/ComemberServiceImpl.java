package project.sungho.comember.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import project.sungho.comember.dao.ComemberDAO;
import project.sungho.comember.vo.ComemberVO;



@Service
public class ComemberServiceImpl implements ComemberService{
	
	@Autowired
	ComemberDAO comemberDAO;

	//회원별 가입한 공간리스트를 불러오기위한 서비스
	@Override
	public List<Map> searchList(Map<String, Object> searchMap) throws DataAccessException {
		List<Map> list =  comemberDAO.searchList(searchMap); 
		return list;
	}
	
	//공간에 가입한 회원리스트를  불러오기위한 서비스
	@Override
	public List<Map> memberList(Map<String, Object> searchMap) throws DataAccessException {
		List<Map> list =  comemberDAO.memberList(searchMap); 
		return list;
	}

	@Override
	public void updateComember(Map<String, Object> dataMap) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertComember(Map<String, Object> dataMap) throws Exception {
		comemberDAO.insertComember(dataMap);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComember(Map<String, Object> dataMap) throws Exception {
		comemberDAO.deleteComember(dataMap);
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void deleteMember(Map<String, Object> dataMap) throws Exception {
		comemberDAO.deleteMember(dataMap);
		// TODO Auto-generated method stub
		
	}



}
