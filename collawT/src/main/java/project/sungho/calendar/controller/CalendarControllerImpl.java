package project.sungho.calendar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import project.sungho.calendar.service.CalendarService;

@Controller
@RequestMapping("/calendar/*")
public class CalendarControllerImpl implements CalendarController {
	
	@Autowired
	CalendarService calendarService;

	@Override
	@GetMapping("/main")
	public String main(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return "/calendar/allcalendar";
	}
	
	@Override
	@GetMapping("/list")
	@ResponseBody
	public List<Map> searchList(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		searchMap = (Map<String, Object>) session.getAttribute("member");
		
		//캘린더에 뿌려지기 위한 이슈데이터
		List<Map> eventlist = calendarService.searchList(searchMap);
		
		//color 지정을 위한 내가 가입한 공간리스트
		List<Map> colorlist = calendarService.colorList(searchMap);
		//list url 추가
		String itemurl = "/project/issue/read?i_Num=";
		//이슈리스트와 협업공간크기만큼 돌리는 2중 for문
		for (int i = 0; i < eventlist.size(); i++) {
			//이슈리스트데이터에 해당url을 변경해서 바로 읽을수 있게 변경
			eventlist.get(i).put("url", itemurl + eventlist.get(i).get("url") +"&c_Id="+eventlist.get(i).get("c_Id"));
			for (int j = 0; j < colorlist.size(); j++) {
				//이슈리스트와 가입한 협업공간 이름을 비교 후 같으면 해당 번호에 맞는 color를 입힘
				if (colorlist.get(j).get("c_Id").equals(eventlist.get(i).get("c_Id"))) {
					switch (j) {
					case 0: eventlist.get(i).put("color", "#28a745");
					eventlist.get(i).put("textColor", "#FFF");
					break;
					case 1: eventlist.get(i).put("color", "#ffc107");
					eventlist.get(i).put("textColor", "#FFF");
					break;
					case 2: eventlist.get(i).put("color", "#6f42c1");
					eventlist.get(i).put("textColor", "#FFF");
					break;
					case 3: eventlist.get(i).put("color", "#dc3545");
					eventlist.get(i).put("textColor", "#FFF");
					break;
					case 4: eventlist.get(i).put("color", "#007bff");
					eventlist.get(i).put("textColor", "#FFF");
					break;
					default:
					break;
					}
				}
			}
		}
		
		return eventlist;
	}





}
