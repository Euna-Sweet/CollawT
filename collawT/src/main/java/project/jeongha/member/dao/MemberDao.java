package project.jeongha.member.dao;

import java.util.List;
import java.util.Map;

import project.jeongha.member.vo.MemberVO;

public interface MemberDao {
	
	public MemberVO login(Map<String, Object> mem_id) throws Exception;
	public MemberVO memberStatus(Map<String, Object> mem_id) throws Exception;
	public int memberJoin(Map<String,Object> memberVO);
	public Map<String, Object> memberLogin(Map<String, Object> memLogin);
	public int memberUpdate(Map<String, Object> memberVO);
	public int checkId(String mem_id) throws Exception;
	public int update_pw(Map<String, Object> memberVO) throws Exception;
	public int memberDelete(Map<String, Object> memberVO) throws Exception;
	public void saveImage(Map<String, Object> hmap) throws Exception;
	public Map<String, Object> getByteImage(String mem_Id) ;
	public MemberVO loginC(String mem_id) throws Exception ;
	public List<Map> searchList(Map<String, Object> searchMap);
	public int memberJoinApi(Map<String,Object> memberVO);
	public int memberJoinApiGoogle(Map<String, Object> memberVO);
	public int checkIdMap(Map<String, Object> mem_id) throws Exception;
	public MemberVO loginCh(Map<String, Object> mem_id) throws Exception ;
	public int update_pw(MemberVO memberVO) throws Exception;
	public int memberLoginNaver(Map<String, Object> memberVO) throws Exception;
	public int approvalMember(MemberVO member) throws Exception;
}