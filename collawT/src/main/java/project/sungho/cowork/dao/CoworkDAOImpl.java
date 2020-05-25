package project.sungho.cowork.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.sungho.cowork.vo.CoworkVO;

@Repository //DAO는 @Repsoitory 혹은 @Component로 빈객체를 자동 등록한다
public class CoworkDAOImpl implements CoworkDAO {

	@Autowired //DB접속을 자동주입을 받고 맵퍼를 연결한다.
	private SqlSession sqlSession;
	
	@Override
	public List<CoworkVO> searchList(Map<String, Object> searchMap) throws DataAccessException {
		
		List<CoworkVO> list = sqlSession.selectList("cowork.searchList", searchMap); 
		//맵퍼에 cowork.searchList 를 찾아 실행 

		return list;
	}
	
	@Override
	public Map<String, Object> searchMain(Map<String, Object> searchMap) throws DataAccessException {
		
		return sqlSession.selectOne("cowork.searchMain", searchMap); 
		
	}


	@Override
	public void updateCowork(Map<String, Object> dataMap) throws DataAccessException {
		// TODO Auto-generated method stub
		sqlSession.update("cowork.updateCowork", dataMap);
		
	}

	@Override
	public void insertCowork(Map<String, Object> dataMap) throws DataAccessException {
		sqlSession.update("cowork.insertCowork", dataMap);
		
		//맵퍼에 cowork.insertCowork 를 찾아 실행 
	}

	@Override
	public int deleteCowork(Map<String, Object> dataMap) throws DataAccessException {
		// TODO Auto-generated method stub
		
		int result = sqlSession.delete("cowork.deleteCowork", dataMap);
		
		return result;
	}


	@Override
	public String selectSqc() throws DataAccessException {
		// TODO Auto-generated method stub
		String c_Id = sqlSession.selectOne("cowork.selectSqc");
		System.out.println(c_Id);
		return c_Id;
		
	}

	@Override
	public List<Map> calendarlist(Map<String, Object> searchMap) throws DataAccessException {
		List<Map> list = sqlSession.selectList("cowork.calendarlist", searchMap); 
		return list;
		
	}
	public List<Map> calendarvotelist(Map<String, Object> searchMap) throws DataAccessException {
		List<Map> list = sqlSession.selectList("cowork.calendarvotelist", searchMap); 
		return list;
		
	}
	
	@Override
	public List<Map> kanbanlist(Map<String, Object> searchMap) throws DataAccessException {
		List<Map> list = sqlSession.selectList("cowork.kanbanlist", searchMap); 
		return list;
		
	}
	
	@Override
	public int kanbanUpdate(Map<String, Object> searchMap) throws DataAccessException {
		int result = sqlSession.update("cowork.kanbanUpdate", searchMap); 
		return result;
		
	}

}
