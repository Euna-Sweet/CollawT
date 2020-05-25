package project.sungho.chat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import project.sungho.chat.dao.ChatDAO;

public class ChatWebSocketHandler extends TextWebSocketHandler {

	private Map<String, WebSocketSession> usersMap = new HashMap<String,WebSocketSession>();
	
	@Autowired
	ChatDAO chatDAO;
	

	@Override //연결이 성사 되고 나서 해야할 일들.
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//로그인시 저장했던 맴버세션을 가져온다
		Map<String,Object> loginMember = (Map<String, Object>) session.getAttributes().get("member");
		System.out.println(loginMember + " 연결 됨");
		//로그인한 아이디와 상대방의 이름으로 웹소켓세션을 저장한다. (1:1 채팅을 위해)
		String loginUser = (String) loginMember.get("mem_Id");
		String target_Id = (String) loginMember.get("target_Id");
		System.out.println("==================================연결타켓 =========" +target_Id);
		usersMap.put(loginUser+target_Id,session);
		
	}

	@Override //소켓 연결이 종료되고 나서 서버단에서 실행해야할 일들을 정의해주는 메소드
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//로그인시 저장했던 맴버세션을 가져온다
		Map<String,Object> loginMember = (Map<String, Object>) session.getAttributes().get("member");
		System.out.println(loginMember + " 연결 종료됨");
		//로그인한 아이디의 이름으로 저장된 웹소켓세션을 지운다.
		String loginUser = (String) loginMember.get("mem_Id");
		String target_Id = (String) loginMember.get("target_Id");
		System.out.println("==================================종료타켓 =========" +target_Id);
		usersMap.remove(loginUser+target_Id);
	}

	@Override //웹소켓 서버단으로 메세지가 도착했을때 해주어야할 일들을 정의하는 메소드
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log(session.getId() + "로부터 메시지 수신: " + message.getPayload());
		
		//메세지 작성자,대상자,내용을 다 가져오기위해 제이슨 타입으로 메세지를 보냈고 그걸 다시 스트링타입으로 변환을 해준다.
		JSONParser jsonParser =  new JSONParser();
		JSONObject obj = (JSONObject) jsonParser.parse(message.getPayload());
		//작성자 아이디
		String my_Id = (String) obj.get("inId");
		//대상자 아이디
		String target_Id = (String) obj.get("target");
		//나의 웹소켓  내아이디+상대아이디(1:1 채팅을 위해)
		WebSocketSession myws = (WebSocketSession) usersMap.get(my_Id+target_Id);
		//상대의 웹소켓  상대아이디+내아이디(1:1 채팅을 위해)
		WebSocketSession ws = (WebSocketSession) usersMap.get(target_Id+my_Id);
		
		//메세지 보내는 사람 이름과 내용
		String msgName = (String) obj.get("name");
		String msg = (String) obj.get("message");
		
		//DB저장을 위한 데이터맵
		Map<String, Object>dataMap = new HashMap<String, Object>();
		dataMap.put("mem_Id",my_Id);
		dataMap.put("target_Id",target_Id);
		dataMap.put("message",msg);
		
		//나 자신에게 메세지를 보냄
		myws.sendMessage(new TextMessage(msgName+" : "+msg));
		
		//대상이 접속해 있을 경우 대상에게 메세지를 보냄
		if (ws != null) {
			chatDAO.insertChat(dataMap);
			List<Map>countNum = chatDAO.msgCount(dataMap);
			ws.sendMessage(new TextMessage(msgName+" : "+msg));
		}else {
			chatDAO.logoutinsertChat(dataMap);
		}
	}

	@Override
	public void handleTransportError(
		WebSocketSession session, Throwable exception) throws Exception {
		log(session.getId() + " 익셉션 발생: " + exception.getMessage());
	}

	private void log(String logmsg) {
		System.out.println(new Date() + " : " + logmsg);
	}

}
