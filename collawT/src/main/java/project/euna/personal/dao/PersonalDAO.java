package project.euna.personal.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import project.euna.personal.vo.Criteria;
import project.euna.personal.vo.PersonalVO;

public interface PersonalDAO {
//
	public int personalInsert(Map map);
	public List<Map> searchList(Criteria cri) throws DataAccessException;
	public Map<String, Object> personalRead(String p_Num);
	public void personalDelete(String i_Num) throws Exception;
	public void personalUpdate(PersonalVO personalVO) throws Exception;
	public int listCount(String c_Id) throws Exception;
	public List<Map> igRead();
	public List<Map> comemRead(String c_id);
	public void comemInsert(Map<String, Object> dataMap) throws DataAccessException;
	public List<Map> chargerRead(String i_Num);
	public void chargerDelete(String i_Num) throws Exception;
	public List<Map> coRead(String mem_id);
	public Map<String, Object> get_p_Num();
	public int personalCopy(Map map) ;
	public List<Map> chargerList(String c_Id);
	public List<Map> newspeed(String mem_Id);
	public List<Map> voteAvailable(String mem_Id);
	public List<Map> myissue(String mem_Id);
	public void personalmemoDelete(String p_Num) throws Exception;

}
