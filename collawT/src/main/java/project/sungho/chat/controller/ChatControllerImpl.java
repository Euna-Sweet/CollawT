package project.sungho.chat.controller;

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

import project.sungho.chat.dao.ChatDAO;

@Controller
@RequestMapping("/chatmsg/*")
public class ChatControllerImpl implements ChatController{
	
	@Autowired
	ChatDAO chatDAO;
	
	//채팅내역을 가져오기위한 컨트롤러
	@Override
	@GetMapping("/list")
	@ResponseBody
	public List<Map> searchList(Model model, HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		//나의 정보를 세션에서 가져와서 데이터맵에 넣는다
		HttpSession session = request.getSession();
		Map<String, Object> searchMap = new HashMap<String,Object>();
		searchMap = (Map<String, Object>) session.getAttribute("member");
		
		//상대방 아이디를 가져와서 데이터맵에 넣는다
		String target_Id = (String)request.getParameter("target_Id");
		searchMap.put("target_Id", target_Id);
		
		//채팅내역을 가져오는 DAO
		List<Map>chatlist = chatDAO.searchList(searchMap);
		//채팅내역확인 상태를 업데이트 하는 DAO
		chatDAO.readingMsg(searchMap);
		
		return chatlist;
	}

}
