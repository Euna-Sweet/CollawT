package project.sungho.calendar.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface CalendarDAO {
	
	public List<Map> searchList(Map<String, Object> searchMap) throws Exception;
	public List<Map> colorList(Map<String, Object> searchMap) throws Exception;
	
}
