package project.euna.issue.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import project.euna.issue.vo.Criteria;
import project.euna.issue.vo.IssueVO;

public interface IssueDAO {
//
	public int issueInsert(Map map);
	public List<Map> searchList(Criteria cri) throws DataAccessException;
	public Map<String, Object> issueRead(String i_Num);
	public void issueDelete(String i_Num) throws Exception;
	public void issueUpdate(IssueVO issueVO) throws Exception;
	public int listCount(String c_Id) throws Exception;
	public List<Map> igRead();
	public List<Map> comemRead(String c_id);
	public void comemInsert(Map<String, Object> dataMap) throws DataAccessException;
	public List<Map> chargerRead(String i_Num);
	public void chargerDelete(String i_Num) throws Exception;
	public List<Map> coRead(String mem_id);
	public Map<String, Object> get_i_Num();
	public int issueCopy(Map map) ;
	public List<Map> chargerList(String c_Id);
	public List<Map> igCount(String c_Id);
	public List<Map> recentBoard(String c_Id);
	public List<Map> voteOn(String c_Id);
	public String votePercent(String c_Id);
	public void replyDelete(String i_Num) throws Exception;

}
