package project.sungho.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.socket.WebSocketSession;

import project.sungho.chat.dao.ChatDAO;
import project.sungho.comember.service.ComemberService;
import project.sungho.comember.vo.ComemberVO;

public class Interceptor extends HandlerInterceptorAdapter {
	@Autowired
	ComemberService comemberService;
	@Autowired
	ChatDAO chatDAO;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//로그인하여 세션에 저장되어있는 회원정보를 불러옴
		HttpSession session = request.getSession();
		Map<String, Object> member = new HashMap<String,Object>();
		member = (Map<String, Object>) session.getAttribute("member");
		
		String uri = (String) request.getRequestURI();
	
		//로그인 정보가 없을경우 로그인 페이지로 이동
		if (member == null) {
			response.sendRedirect("/member/loginPage");
			//리턴이 false으로 되어있으면 해당 url로는 이동이 안된다
			return false;
			
			//로그인 정보가 있을경우
		}else if (member != null){
			System.out.println(uri);
			//로그인한 회원의 협업공간 리스트를 불러옴
			List<Map> list = comemberService.searchList(member);
			//로그인한 회원에게 온 메세지를 맴버별로 카운트
			List<Map> msgCount = chatDAO.msgCount(member);
			//모든 카운트
			int totalCount = chatDAO.totalCount(member);
			for (int i = 0; i < list.size(); i++) {
				//로그인한 회원의 협업공간의 맴버들을 불러와 협업공간 정보에 저장함 , 협업공간별 칼라도 저정(채팅방구분)
				Map<String, Object> memberSearch = list.get(i);
				List<Map>memberList = comemberService.memberList(memberSearch);
				list.get(i).put("memberList", memberList);
				switch (i) {
				case 0: list.get(i).put("color", "#28a745");
				break;
				case 1: list.get(i).put("color", "#ffc107");
				break;
				case 2: list.get(i).put("color", "#6f42c1");
				break;
				case 3: list.get(i).put("color", "#dc3545");
				break;
				case 4: list.get(i).put("color", "#007bff");
				break;
				default:
				break;
				}
			}
			
			request.setAttribute("coworklist", list);
			request.setAttribute("msgCount", msgCount);
			request.setAttribute("totalCount", totalCount);
			return true;
		}
		

//		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

}