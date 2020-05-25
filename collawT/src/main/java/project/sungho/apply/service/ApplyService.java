package project.sungho.apply.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RequestParam;

import project.sungho.apply.vo.ApplyVO;




public interface ApplyService {
	public List<Map> searchList(Map<String, Object> searchMap) throws DataAccessException;
	 public List<Map> viewCount(Map<String,Object>searchMap) throws DataAccessException;
	 public void updateApply(Map<String, Object> dataMap) throws Exception;
	 public void insertApply(Map<String, Object> dataMap) throws Exception;
	 public void deleteApply(Map<String, Object> dataMap) throws Exception;
	 
	 public int memberCheck(String mem_Id) throws Exception;
	 
	 public int acceptApply(Map<String, Object> dataMap) throws Exception;

	 public int rejectApply(Map<String, Object> dataMap) throws Exception;
	 
}
