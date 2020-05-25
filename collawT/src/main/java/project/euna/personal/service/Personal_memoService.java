package project.euna.personal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import project.euna.issue.vo.IssueVO;
import project.euna.personal.vo.Personal_memoVO;

public interface Personal_memoService {

	
	public List<Map> searchList(String p_Num) throws Exception;
	public void  personal_memoInsert(Personal_memoVO personal_memoVO);
	public void personal_memoDelete(String p_m_Num) throws Exception;
	public void personal_memoUpdate(Personal_memoVO personal_memoVO) throws Exception;


}
