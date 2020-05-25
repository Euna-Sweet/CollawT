package project.euna.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.hm.hmp002_d001.vo.Hmp002_d001VO;


@Repository
public class PersonalManagerDAOImpl implements PersonalManagerDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Map> searchList(Map<String, Object> searchMap) throws DataAccessException {
		List<Map> list = sqlSession.selectList("personalmanager.searchList", searchMap);
		return list;
	}

	@Override
	public void insertData(Map<String, String> row) throws DataAccessException {
		sqlSession.update("personalmanager.insertData", row);
	}

	@Override
	public void updateData(Map<String, String> row) throws DataAccessException {
		sqlSession.update("personalmanager.updateData", row);
	}

	@Override
	public void deleteData(Map<String, String> row) throws DataAccessException {
		sqlSession.update("personalmanager.deleteData", row);
	}

}
