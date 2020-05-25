package project.euna.issue.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import project.euna.issue.vo.Criteria;
import project.euna.issue.vo.IssueVO;

public interface IssueService {
//
	public void  issueInsert(Map map);
	public List<Map> searchList(Criteria cri) throws Exception;
	public Map<String, Object> issueRead(String i_Num);	
	public void issueDelete(String i_Num) throws Exception;
	public void issueUpdate(IssueVO issueVO) throws Exception;
	public int listCount(String c_Id) throws Exception;
	public List<Map> igRead();
	public List<Map> comemRead(String c_id);
	public void comemInsert(Map<String, Object> dataMap) throws Exception;
	public List<Map> chargerRead(String i_Num);
	public void chargerDelete(String i_Num) throws Exception;
	public List<Map> coRead(String mem_id);
	public void  issueCopy(Map map);
	public List<Map> chargerList(String c_Id);
	public List<Map> igCount(String c_Id);
	public List<Map> recentBoard(String c_Id);
	public List<Map> voteOn(String c_Id);
	public String votePercent(String c_Id);
	public void replyDelete(String i_Num) throws Exception;
}
