package project.sungho.interceptor;

import java.util.Map;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class LoginCheck implements HttpSessionListener{
	
	  @Autowired
	  private SqlSession sqlSession;


	  private SqlSession getSqlSession(HttpSessionEvent se) {
	    WebApplicationContext context = 
	      WebApplicationContextUtils.getWebApplicationContext(
	        se.getSession().getServletContext());
	    return (SqlSession) context.getBean("sqlSession");
	  } 

	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
	
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		sqlSession = getSqlSession(se);
		if (se.getSession().getAttribute("member") != null) {
			Map<String, Object> logout = (Map<String, Object>) se.getSession().getAttribute("member");
			System.out.println("로그아웃 세션파기"+logout.get("mem_Id"));
			sqlSession.update("logincheck.updatelogoutcheck", logout);
		} 
		
		
		
	}


}