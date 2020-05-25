package project.jeongha.member.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import project.jeongha.member.vo.MemberVO;

public interface MemberService {
	
	public int memberJoin(Map<String,Object> member,MemberVO memberVO,HttpServletResponse response)throws Exception;
	public void memberSearch(MemberVO memberVO);
	public Map<String, Object> login(Map<String, Object> memLogin, HttpServletResponse response)throws Exception;
	public MemberVO updateMypage(Map<String, Object> memLogin)throws Exception;
	public MemberVO update_pw(Map<String, Object> memberVO, HttpServletResponse response) throws Exception;
	public int check_id(String mem_id) throws Exception ;
	public String create_key() throws Exception ;
	public void sendEmail(MemberVO memberVO, String div) throws Exception;
	public void find_pw(HttpServletResponse response, MemberVO memberVO,Map<String,Object> member) throws Exception ;
	public Map<String, Object> memberDelete(Map<String, Object> memberVO, HttpServletResponse response) throws Exception ;
	public List<Map> searchList(Map<String, Object> memLogin)throws Exception;
	public int memberJoin(Map<String,Object> member) throws Exception;
	public int memberJoinApi(Map<String, Object> member) throws Exception;
	public int memberJoinApiGoogle(Map<String, Object> member) throws Exception;
	public MemberVO memberLoginNaver(Map<String, Object> memberVO) throws Exception;
	public void approvalMember(MemberVO memberVO, HttpServletResponse response) throws Exception ;
	
	
}
