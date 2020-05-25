package project.sungho.comember.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.sungho.comember.vo.ComemberVO;



@Repository 
public class ComemberDAOImpl implements ComemberDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	//회원별 가입한 공간리스트를 불러오기위한 DAO
	@Override
	public List<Map> searchList(Map<String, Object> searchMap) throws DataAccessException {
		List<Map> list = sqlSession.selectList("comember.searchList", searchMap); 

		return list;
	}
	
	//공간에 가입한 회원리스트를  불러오기위한 DAO
	@Override
	public List<Map> memberList(Map<String, Object> searchMap) throws DataAccessException {
		List<Map> list = sqlSession.selectList("comember.memberList", searchMap); 
		
		return list;
	}
	
	

	@Override
	public void updateComember(Map<String, Object> dataMap) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertComember(Map<String, Object> dataMap) throws DataAccessException {
		
		sqlSession.update("comember.insertComember",dataMap);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComember(Map<String, Object> dataMap) throws DataAccessException {
		// TODO Auto-generated method stub
		sqlSession.delete("comember.deleteComember",dataMap);
	}
	
	@Override
	public void deleteMember(Map<String, Object> dataMap) throws DataAccessException {
		// TODO Auto-generated method stub
		sqlSession.delete("comember.deleteMember",dataMap);
	}
	
	public void deleteComemberAll(Map<String, Object> dataMap) throws DataAccessException {
		// TODO Auto-generated method stub
		sqlSession.delete("comember.deleteComemberAll",dataMap);
		
	}




	
	
}
