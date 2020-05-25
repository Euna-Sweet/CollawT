package project.euna.personal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import project.euna.personal.vo.Criteria;
import project.euna.personal.vo.PersonalVO;

public interface PersonalService {
//
	public void  personalInsert(Map map);
	public List<Map> searchList(Criteria cri) throws Exception;
	public Map<String, Object> personalRead(String i_Num);	
	public void personalDelete(String i_Num) throws Exception;
	public void personalUpdate(PersonalVO personalVO) throws Exception;
	public int listCount(String c_Id) throws Exception;
	public List<Map> igRead();
	public List<Map> comemRead(String c_id);
	public void comemInsert(Map<String, Object> dataMap) throws Exception;
	public List<Map> chargerRead(String i_Num);
	public void chargerDelete(String i_Num) throws Exception;
	public List<Map> coRead(String mem_id);
	public void  personalCopy(Map map);
	public List<Map> chargerList(String c_Id);
	public List<Map> newspeed(String mem_Id);
	public List<Map> voteAvailable(String mem_Id);
	public List<Map> myissue(String mem_Id);
	public void personalmemoDelete(String p_Num) throws Exception;

}
