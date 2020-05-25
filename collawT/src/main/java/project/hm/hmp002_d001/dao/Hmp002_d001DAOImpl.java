package project.hm.hmp002_d001.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.hm.hmp002_d001.vo.Hmp002_d001VO;


@Repository("hmp002_d001DAO")
public class Hmp002_d001DAOImpl implements Hmp002_d001DAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Hmp002_d001VO> searchList(Map<String, Object> searchMap) throws DataAccessException {
		List<Hmp002_d001VO> list = sqlSession.selectList("hm.hmp002_d001.searchList", searchMap);
		return list;
	}

	@Override
	public void insertData(Map<String, String> row) throws DataAccessException {
		sqlSession.update("hm.hmp002_d001.insertData", row);
	}

	@Override
	public void updateData(Map<String, String> row) throws DataAccessException {
		sqlSession.update("hm.hmp002_d001.updateData", row);
	}

	@Override
	public void deleteData(Map<String, String> row) throws DataAccessException {
		sqlSession.update("hm.hmp002_d001.deleteData", row);
	}

}
