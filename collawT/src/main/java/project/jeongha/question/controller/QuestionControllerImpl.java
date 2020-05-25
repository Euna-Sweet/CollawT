package project.jeongha.question.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.jeongha.member.vo.MemberVO;
import project.jeongha.question.service.QuesttionService;
import project.jeongha.question.vo.QuestionVO;

@Controller
public class QuestionControllerImpl implements QuestionController {

	
	
	@Autowired
	QuesttionService qService;
	
	
	@Override
	@GetMapping("/question")
	public String qustion(QuestionVO qVo,HttpServletRequest reqest, HttpServletResponse response, HttpSession session) {
		
		return "/question";
	}

	@Override
	@RequestMapping(value = "/question/sendEmail", method = RequestMethod.POST)
	public String sendEmail(QuestionVO qVo,HttpServletRequest reqest, HttpServletResponse response, HttpSession session)throws Exception {
		String q_Content = qVo.getQ_Content();
		String q_Subject = qVo.getQ_Subject();
		String q_Email = qVo.getQ_Email();
		String q_Kind = qVo.getQ_Kind();
		System.out.println("q_Content: "+q_Content);
		System.out.println("q_Subject: "+q_Subject);
		System.out.println("q_Email: "+q_Email);
		System.out.println("q_Kind: "+q_Kind);
		
		Map<String, Object> insertThing = new HashMap<String, Object>();
		insertThing.put("q_Content",q_Content);
		insertThing.put("q_Subject",q_Subject);
		insertThing.put("q_Email",q_Email);
		insertThing.put("q_Kind",q_Kind);
		System.out.println("sendEmal.:"+insertThing);
		qService.insertQuestion(insertThing);
			
			sendEmail(qVo);
			session.setAttribute("question", "question");
			
		return "redirect:/";
	}

	@Override
	public void sendEmail(QuestionVO qVo) throws Exception {
		String q_Content = qVo.getQ_Content();
		String q_Subject = qVo.getQ_Subject();
		String q_Email = qVo.getQ_Email();
		String q_Kind = qVo.getQ_Kind();
		// Mail Server 설정
				String charSet = "utf-8";
				String hostSMTP = "smtp.gmail.com";

				// google 아이디, 비밀번호
				String hostSMTPid = "collawt@gmail.com";
				String hostSMTPpwd = "zhffkdnxl%!00";

				// 보내는 사람 EMail,이름, 제목, 내용
				String fromEmail = q_Email;
				String fromName = "CollawT 문의 메일";
				String subject = "";
				String msg = "";

				//회원가입시 인증메일
					subject = q_Subject +" [보낸사람: "+q_Email+" ]";
					msg += "<html>";
					msg += "<head>";
					msg += "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css\"/>";
					msg += "</head>";
					msg += "<body>";
					msg += "<div style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 540px; height: 600px; border-top: 4px solid #02b875; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">\n" + 
							"    <h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">\n" + 
							"        <span style=\"font-size: 15px; margin: 0 0 10px 3px;\">콜라우티</span><br />\n" + 
							"        <span style=\"color: #02b875;\">문의</span> 메일입니다.\n" + 
							"    </h1>\n";
					
					msg += 			""+
							"    <p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">\n" ;
					msg += "분류: "+ q_Kind +"<br>";
					msg += "문의 내용: "+q_Content;
					msg+=	"    </p>\n" + 
							"    <div style=\"border-top: 1px solid #DDD; padding: 5px;\">\n" + 
							"        <p style=\"font-size: 13px; line-height: 21px; color: #555;\">\n" + 
							"        </p>\n" + 
							"    </div>\n" + 
							"</div>";
					msg += "</body>";
					msg += "</html>";
		String mail = "collawt@gmail.com";
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			// google smtp 포트번호
			email.setSmtpPort(587);

			// 메일 아이디 비밀번호
			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			// 받는사람
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
	}
}
