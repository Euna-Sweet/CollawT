package project.euna.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.hm.hmp002_d001.vo.Hmp002_d001VO;


@Repository
public class IssueManagerDAOImpl implements IssueManagerDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Map> searchList(Map<String, Object> searchMap) throws DataAccessException {
		List<Map> list = sqlSession.selectList("issuemanager.searchList", searchMap);
		return list;
	}

	@Override
	public void insertData(Map<String, String> row) throws DataAccessException {
		sqlSession.update("issuemanager.insertData", row);
	}

	@Override
	public void updateData(Map<String, String> row) throws DataAccessException {
		sqlSession.update("issuemanager.updateData", row);
	}

	@Override
	public void deleteData(Map<String, String> row) throws DataAccessException {
		sqlSession.update("issuemanager.deleteData", row);
	}

}
