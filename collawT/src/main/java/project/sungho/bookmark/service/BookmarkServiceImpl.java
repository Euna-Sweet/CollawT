package project.sungho.bookmark.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import project.sungho.bookmark.dao.BookmarkDAO;



@Service
public class BookmarkServiceImpl implements BookmarkService{
	
	@Autowired
	BookmarkDAO bookmarkDAO;

	@Override
	public List<Map> searchList(Map<String, Object> searchMap) throws DataAccessException {

		List<Map> list =  bookmarkDAO.searchList(searchMap);  //DAO에 searchList함수 실행
			return list;
		}

	@Override
	public void updateBookmark(Map<String, Object> dataMap) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int insertBookmark(Map<String, Object> dataMap) throws Exception {
		// TODO Auto-generated method stub
		return bookmarkDAO.insertBookmark(dataMap);
	}

	@Override
	public int deleteBookmark(Map<String, Object> dataMap) throws Exception {
		// TODO Auto-generated method stub
		return	bookmarkDAO.deleteBookmark(dataMap);
		
	}

	
	@Override
	public int bookmarkCheck(Map<String, Object> dataMap) throws Exception {
		// TODO Auto-generated method stub
		return bookmarkDAO.bookmarkCheck(dataMap);
		 
	}
	

}
