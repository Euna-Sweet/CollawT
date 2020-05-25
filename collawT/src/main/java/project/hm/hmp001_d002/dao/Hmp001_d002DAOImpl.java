package project.hm.hmp001_d002.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.hm.hmp001_d002.vo.Hmp001_d002VO;


@Repository("hmp001_d002DAO") 
public class Hmp001_d002DAOImpl implements Hmp001_d002DAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Hmp001_d002VO> searchMember(Map<String, Object> searchMap) throws DataAccessException {
		List<Hmp001_d002VO> list = sqlSession.selectList("hm.hmp001_d002.searchMember", searchMap);
		return list;
	}

	@Override
	public void updateMember(Map<String, Object> datahMap) throws DataAccessException {
		sqlSession.update("hm.hmp001_d002.updateMember", datahMap);
	}

	@Override
	public void insertMember(Map<String, Object> datahMap) throws DataAccessException {
		sqlSession.update("hm.hmp001_d002.insertMember", datahMap);
	}

	@Override
	public void deleteMember(Map<String, Object> datahMap) throws DataAccessException {
		sqlSession.update("hm.hmp001_d002.deleteMember", datahMap);
	}

}
