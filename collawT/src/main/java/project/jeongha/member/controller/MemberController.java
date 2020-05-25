package project.jeongha.member.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.jeongha.member.vo.MemberVO;

public interface MemberController {
//	public String getServerTime(Locale locale);

public String signup() ;
public String loginFrorm();
public String mypage(HttpServletRequest request )throws Exception;
public String memJoin(MemberVO memberVO,HttpServletRequest request, HttpServletResponse response, RedirectAttributes rttr)throws Exception;
//public ModelAndView memLogin(MemberVO memberVO,RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response)throws Exception;
public ModelAndView memLogin(MemberVO memberVO,RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response)throws Exception ;
public String update_mypage(MemberVO memberVO, HttpSession session, RedirectAttributes rttr, HttpServletRequest request) throws Exception;
public String update_pw(MemberVO memberVO, String old_pw, HttpSession session, HttpServletResponse response, RedirectAttributes rttr) throws Exception;
public int check_id(@RequestParam("mem_Id") String mem_Id) throws Exception;
public String findPw() ;
public void find_pw(Map<String,Object> member,MemberVO memberVO, HttpServletResponse response) throws Exception;
public String outMember() ;
public String memberDelete(MemberVO memberVO, HttpServletResponse response, HttpSession session, RedirectAttributes rttr)throws Exception;
public String saveImage(MemberVO memberVO,HttpServletResponse request,HttpServletResponse response, HttpSession session) throws Exception;
public ResponseEntity<byte[]> getByteImage(String mem_Id) ;
public String naverLogin(Model model, HttpSession session);
public String naverSignup(Model model, HttpSession session);
public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session, HttpServletResponse response) throws IOException,ParseException,Exception ;
public String logout(HttpSession session)throws IOException ;
public String googleCallback(HttpServletRequest request, HttpServletResponse response, HttpSession session,
        Model model) throws IOException, Exception;
public String getHttpConnection(String uri, String param) throws ServletException, IOException;
public String getHttpConnection(String uri) throws ServletException, IOException;
public void approvalMember( MemberVO memberVO, HttpServletResponse response) throws Exception;
}
