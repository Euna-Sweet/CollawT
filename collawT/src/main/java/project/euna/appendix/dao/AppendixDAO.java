package project.euna.appendix.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import project.euna.appendix.vo.AppendixVO;


public interface AppendixDAO {


	public void uploadFile(Map<String, Object> hmap) throws Exception;
	public List<Map> fileList(String i_Num) throws DataAccessException;
	public Map<String, Object> download(String a_Num);
	public Map<String, Object> get_i_Num();
	public void updateFile(AppendixVO appendixVO) throws Exception;
	public void copyFile(Map<String, Object> hmap) throws Exception;
	public void fileDelete(String i_Num) throws Exception;
	
	}
