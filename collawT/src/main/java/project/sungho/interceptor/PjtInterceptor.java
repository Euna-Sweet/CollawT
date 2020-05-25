package project.sungho.interceptor;

import java.util.ArrayList;
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

public class PjtInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	ComemberService comemberService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//로그인하여 세션에 저장되어있는 회원정보를 불러옴
		HttpSession session = request.getSession();
		Map<String, Object> member = new HashMap<String,Object>();
		List<Map>checkId = new ArrayList<Map>();
		
		member = (Map<String, Object>) session.getAttribute("member");
		String loginId = (String) member.get("mem_Id");
		String checkmember = (String)request.getParameter("c_Id");
		checkId = comemberService.searchList(member);
		String memKind = (String) member.get("mem_Kind");
		if (memKind.equals("03")) {
			return true;
		}	
		for (int i = 0; i < checkId.size(); i++) {
			if (checkId.get(i).get("c_Id").equals(checkmember)) {
				return true;
			}
		}
		response.sendRedirect("/main");
		return false;
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