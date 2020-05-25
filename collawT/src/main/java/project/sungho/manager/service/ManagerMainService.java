package project.sungho.manager.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import project.hm.hmp002_d001.vo.Hmp002_d001VO;

public interface ManagerMainService {
	public List<Map> searchList() throws DataAccessException;
	 
	 public int memCount() throws DataAccessException;

	 public List<Map> donutList() throws DataAccessException;
	 
	 public List<Map> lineList() throws DataAccessException;
}
