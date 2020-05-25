package project.sungho.calendar.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

@Repository
public class CalendarDAOImpl implements CalendarDAO{

	@Autowired
	private SqlSession sqlSession;
		
	@Override
	public List<Map> searchList(Map<String, Object> searchMap)throws Exception {
		List<Map> list = sqlSession.selectList("calendar.searchList", searchMap); 
		
		return list;
	}

	@Override
	public List<Map> colorList(Map<String, Object> searchMap) throws Exception {
		List<Map> list = sqlSession.selectList("calendar.colorList", searchMap); 
		
		return list;
	}
	

}
