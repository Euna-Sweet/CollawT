package project.sungho.bookmark.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RequestParam;

import project.sungho.apply.vo.ApplyVO;




public interface BookmarkService {
	public List<Map> searchList(Map<String, Object> searchMap) throws DataAccessException;
	 
	 public void updateBookmark(Map<String, Object> dataMap) throws Exception;
	 public int insertBookmark(Map<String, Object> dataMap) throws Exception;
	 public int deleteBookmark(Map<String, Object> dataMap) throws Exception;
	 
	 public int bookmarkCheck(Map<String, Object> dataMap) throws Exception;
	 
	 
}
