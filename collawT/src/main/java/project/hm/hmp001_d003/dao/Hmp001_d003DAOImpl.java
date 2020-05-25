package project.hm.hmp001_d003.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.hm.hmp001_d003.vo.Hmp001_d003VO;

@Repository("hmp001_d003DAO")
public class Hmp001_d003DAOImpl implements Hmp001_d003DAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Hmp001_d003VO> searchList(Map<String, Object> searchMap) throws DataAccessException {
		List<Hmp001_d003VO> list = sqlSession.selectList("hm.hmp001_d003.searchList", searchMap);
		return list;
	}
	
	@Override
	public List<Hmp001_d003VO> searchMod(Map<String, Object> searchMap) throws DataAccessException {
		List<Hmp001_d003VO> list = sqlSession.selectList("hm.hmp001_d003.searchMod", searchMap);
		return list;
	}

	@Override
	public List<Hmp001_d003VO> searchAdd() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProduct(Map<String, Object> datahMap) throws DataAccessException {
		sqlSession.update("hm.hmp001_d003.updateMember", datahMap);
	}

	@Override
	public void insertProduct(Map<String, Object> datahMap) throws DataAccessException {
		sqlSession.update("hm.hmp001_d003.insertMember", datahMap);
	}

	@Override
	public void deleteProduct(Map<String, Object> datahMap) throws DataAccessException {
		sqlSession.update("hm.hmp001_d003.deleteMember", datahMap);
	}
}
