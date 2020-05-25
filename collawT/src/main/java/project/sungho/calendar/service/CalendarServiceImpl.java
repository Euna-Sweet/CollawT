package project.sungho.calendar.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import project.sungho.calendar.dao.CalendarDAO;


@Service
public class CalendarServiceImpl implements CalendarService{
	
	@Autowired
	CalendarDAO calendarDAO;

	@Override
	public List<Map> searchList(Map<String, Object> searchMap)throws Exception {
		List<Map>list = calendarDAO.searchList(searchMap);
		
		return list;
	}

	@Override
	public List<Map> colorList(Map<String, Object> searchMap) throws Exception {
		List<Map>list = calendarDAO.colorList(searchMap);		
		
		return list;
	}

}
