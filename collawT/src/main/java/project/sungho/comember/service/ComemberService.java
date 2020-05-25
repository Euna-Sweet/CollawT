package project.sungho.comember.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import project.sungho.comember.vo.ComemberVO;



public interface ComemberService {
	public List<Map> searchList(Map<String, Object> searchMap) throws DataAccessException;
	public List<Map> memberList(Map<String, Object> searchMap) throws DataAccessException;
	 
	 public void updateComember(Map<String, Object> dataMap) throws Exception;
	 public void insertComember(Map<String, Object> dataMap) throws Exception;
	 public void deleteComember(Map<String, Object> dataMap) throws Exception;
	 public void deleteMember(Map<String, Object> dataMap) throws Exception;
}
