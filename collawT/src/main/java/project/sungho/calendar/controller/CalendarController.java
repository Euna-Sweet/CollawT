package project.sungho.calendar.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface CalendarController {

	public String main(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public List<Map> searchList(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
