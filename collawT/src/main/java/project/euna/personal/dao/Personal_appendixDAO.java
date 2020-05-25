package project.euna.personal.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import project.euna.appendix.vo.AppendixVO;
import project.euna.personal.vo.Personal_appendixVO;


public interface Personal_appendixDAO {

	public void uploadFile(Map<String, Object> hmap) throws Exception;
	public void updateFile(Personal_appendixVO personal_appendixVO) throws Exception;
	public List<Map> fileList(String i_Num) throws DataAccessException;
	public Map<String, Object> download(String a_Num);
	public Map<String, Object> get_p_Num();
	public void copyFile(Map<String, Object> hmap) throws Exception;
	public void fileDelete(String p_Num) throws Exception;
	public void copyFiletoCowork(Map<String, Object> hmap) throws Exception;
	}
