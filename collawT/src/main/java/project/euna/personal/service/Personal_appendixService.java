package project.euna.personal.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import project.euna.appendix.vo.AppendixVO;
import project.euna.issue.vo.IssueVO;
import project.euna.personal.vo.Personal_appendixVO;


public interface Personal_appendixService {

	
	public List<Map> fileList(String i_Num) throws Exception;
	public void updateFile(Personal_appendixVO personal_appendixVO) throws Exception;
	public void uploadFile(Map<String, Object> hmap) throws Exception;
	public void copyFile(Map<String, Object> hmap) throws Exception;
	public void fileDelete(String p_Num) throws Exception;
	public void copyFiletoCowork(Map<String, Object> hmap) throws Exception;
}
