package project.sungho.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.hm.hmp002_d001.vo.Hmp002_d001VO;


@Repository
public class ManagerMainDAOImpl implements ManagerMainDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Map> searchList() throws DataAccessException {
		List<Map> list = sqlSession.selectList("chart.searchList");
		return list;
	}

	@Override
	public int memCount() throws DataAccessException {
		int result = sqlSession.selectOne("chart.memCount");
		return result;
	}

	public List<Map> donutList() throws DataAccessException {
		List<Map> list = sqlSession.selectList("chart.donutList");
		return list;
	}

	@Override
	public List<Map> lineList() throws DataAccessException {
		List<Map> list = sqlSession.selectList("chart.lineList");
		return list;
	}


}
