package project.euna.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.hm.hmp002_d001.vo.Hmp002_d001VO;


@Repository
public class EManagerMainDAOImpl implements EManagerMainDAO {
	@Autowired
	private SqlSession sqlSession;

	
	public List<Map> main() throws DataAccessException {
		return sqlSession.selectList("questionmanager.main");
	}

}
