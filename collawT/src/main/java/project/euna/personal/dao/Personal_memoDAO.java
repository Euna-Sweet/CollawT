package project.euna.personal.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import project.euna.issue.vo.IssueVO;
import project.euna.personal.vo.Personal_memoVO;
import project.euna.reply.vo.ReplyVO;

public interface Personal_memoDAO {
	
	public List<Map> searchList(String p_Num) throws DataAccessException;
	public int personal_memoInsert(Personal_memoVO personal_memoVO);
	public void personal_memoDelete(String p_m_Num) throws Exception;
	public void personal_memoUpdate(Personal_memoVO personal_memoVO) throws Exception;
}
