package project.hm.hmp001_d001.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import project.hm.hmp001_d001.vo.Hmp001_d001VO;

public interface Hmp001_d001Service {
	 public List<Hmp001_d001VO> searchList(Map<String, Object> searchMap) throws DataAccessException;
	 public List<Hmp001_d001VO> searchMod(Map<String, Object> searchMap) throws DataAccessException;
	 public List<Hmp001_d001VO> searchAdd() throws DataAccessException;
	 
	 public void updateMember(Map<String, Object> datahMap) throws Exception;
	 public void insertMember(Map<String, Object> datahMap) throws Exception;
	 public void deleteMember(Map<String, Object> datahMap) throws Exception;
}
