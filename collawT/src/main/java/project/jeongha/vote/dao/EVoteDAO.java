package project.jeongha.vote.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import project.jeongha.vote.vo.Criteria;


public interface EVoteDAO {
//
	public void voteInsert(Map map);
	public int votedInsert(Map map);
	public int voterInsert(Map map);
	public List<Map> searchList(Criteria cri) throws DataAccessException;
	public Map<String, Object> voteRead(Map<String, Object> v_Num);
	public Map<String, Object> voteReadList(Map<String, Object> searchMap);
	public void voteDelete(String i_Num) throws Exception;
	public void voteUpdate(Map<String, Object> cmap) throws Exception;
	public void votedUpdate(Map<String, Object> cmap) throws Exception;
	public int listCount(String c_Id) throws Exception;
	public List<Map> igRead();
	public List<Map> comemRead(String c_id);
	public void comemInsert(Map<String, Object> dataMap) throws DataAccessException;
	public List<Map> chargerRead(String i_Num);
	public void chargerDelete(String i_Num) throws Exception;
	//public List<Map> coRead(String mem_id);
	//public Map<String, Object> get_i_Num();
	public Map<String, Object> voteInfo(Map<String, Object> voteInfo) throws Exception;
	public List<Map> votedRead(String v_Num) ;
	public  List<Map>  voteCount(Map<String, Object> voteCount)throws Exception;
	public  List<Map>  voteTotal(Map<String, Object> voteCount)throws Exception;
	public int checkId(Map<String, Object> mem_Id) throws Exception ;
	public Map<String, Object> countCowork(Map<String, Object> searchC_Id) throws Exception; 
	public void updateVs_Num(Map<String, Object> searchMap);
	public void votedDelete(String i_Num) throws Exception;
	public void votereplyDelete(String i_Num) throws Exception;
}
