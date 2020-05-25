package project.sungho.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;



@Repository
public class ProJectManagerDAOImpl implements ProJectManagerDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Map> searchList(Map<String, Object> searchMap) throws DataAccessException {
		System.out.println("프로젝트매니저 dao-----------------------------------");
		List<Map> list = sqlSession.selectList("pjtmanager.searchList", searchMap);
		return list;
	}

	@Override
	public void insertData(Map<String, String> row) throws DataAccessException {
		sqlSession.update("pjtmanager.insertData", row);
	}

	@Override
	public void updateData(Map<String, String> row) throws DataAccessException {
		sqlSession.update("pjtmanager.updateData", row);
	}

	@Override
	public void deleteData(Map<String, String> row) throws DataAccessException {
		sqlSession.update("pjtmanager.deleteData", row);
	}

}
