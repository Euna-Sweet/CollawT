package project.sungho.bookmark.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.sungho.apply.vo.ApplyVO;



@Repository 
public class BookmarkDAOImpl implements BookmarkDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Map> searchList(Map<String, Object> searchMap) throws DataAccessException {
		// TODO Auto-generated method stub
		List<Map> list = sqlSession.selectList("bookmark.searchList", searchMap); 
		return list;
	}

	@Override
	public void updateBookmark(Map<String, Object> dataMap) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int insertBookmark(Map<String, Object> dataMap) throws DataAccessException {
		
		return sqlSession.update("bookmark.insertBookmark",dataMap);
		// TODO Auto-generated method stub
		
	}

	@Override
	public int deleteBookmark(Map<String, Object> dataMap) throws DataAccessException {
		return sqlSession.delete("bookmark.deleteBookmark",dataMap);
	}
	

	@Override
	public int bookmarkCheck(Map<String, Object> dataMap) throws Exception {
		// TODO Auto-generated method stub
		int result;
		result = sqlSession.selectOne("bookmark.bookmarkCheck",dataMap);
		return result;
	}

	
	
}
