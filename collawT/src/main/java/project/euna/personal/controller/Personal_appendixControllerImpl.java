package project.euna.personal.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import project.euna.personal.dao.Personal_appendixDAO;
import project.euna.personal.service.Personal_appendixService;
import project.euna.personal.vo.PersonalVO;
import project.euna.personal.vo.Personal_appendixVO;




@Controller

@RequestMapping("/personal/appendix/*")
public class Personal_appendixControllerImpl implements Personal_appendixController {
	
	
	@Inject
	Personal_appendixDAO personal_appendixDAO;
	
	@Inject
	Personal_appendixService personal_appendixService;
	

	

	
	//파일첨부
	@Override
	@PostMapping("/fileUpload")
	@ResponseBody
	public String saveFile(PersonalVO personalVO, MultipartHttpServletRequest mtfRequest, Personal_appendixVO personal_appendixVO) throws Exception{
		List<MultipartFile> fileList = mtfRequest.getFiles("p_a_File");
		
		for (MultipartFile mf : fileList) {
			String p_a_RealName = mf.getOriginalFilename(); // 원본 파일 명
			String p_a_Size = Long.toString((mf.getSize())); // 파일 사이즈
			String p_a_NameEx = p_a_RealName.substring(p_a_RealName.lastIndexOf(".")+1);
			String p_Num=personalVO.getP_Num();
		
		
			
			try {
				Map<String, Object> file = new HashMap<String, Object>();
				file.put("p_a_RealName", p_a_RealName);
				file.put("p_a_File", personal_appendixVO.getP_a_File().getBytes());
				file.put("p_a_NameEx", p_a_NameEx);
				file.put("p_a_Size", p_a_Size);
				file.put("p_Num", p_Num);
				
				personal_appendixDAO.uploadFile(file);
				
				
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return "redirect:/issue/list";

	}
	
	
	//해당글의 첨부파일 목록 확인하기
	@Override
	@GetMapping("/fileread")
	@ResponseBody
	public List<Map> fileRead(@RequestParam ("p_Num")String p_Num, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Map> list = personal_appendixService.fileList(p_Num);
		
		return list;
	}

	
	//파일 다운로드
	@Override
	@GetMapping("/download")
	public ResponseEntity<byte[]> download(Personal_appendixVO personal_appendixVO, HttpServletResponse response) {
		String p_a_Num = personal_appendixVO.getP_a_Num();
	

		Map<String, Object> map = personal_appendixDAO.download(p_a_Num);
		String p_a_RealName = (String) map.get("p_a_RealName");
		
		byte[] file = (byte[]) map.get("p_a_File");
		
		
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
			try {
				headers.add("Content-Disposition","attachment; filename="+new String(p_a_RealName.getBytes("UTF-8"),"8859_1"));
				headers.add("Content-Type", "text/html; charset=utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		return new ResponseEntity<byte[]>(file, headers, HttpStatus.OK);

	}
	
	//첨부파일 수정
	@Override
	@PostMapping("/fileUpdate")
	@ResponseBody
	public void updateFile(Personal_appendixVO personal_appendixVO) throws Exception{
		
	
		personal_appendixService.updateFile(personal_appendixVO);
		
		
	}
	
	
	//작성 취소 시 파일 없애기
	@Override
	@PostMapping("/fileDelete")
	@ResponseBody
	public String fileDelete(String p_Num) throws Exception{
		personal_appendixService.fileDelete(p_Num);	
		
		
		return "redirect:/project/issue/list?c_Id=";
	}
	
	
	//수정 취소 시 파일 없애고 다시 입히기
	@Override
	@PostMapping("/fileCancel")
	@ResponseBody
	public String fileCancel(String p_Num, HttpServletRequest request) throws Exception{
		
		//세션에서 파일리스트 가져오기
		HttpSession session = request.getSession();
		List<Map> personalfileList = null;
		personalfileList = (List<Map>) session.getAttribute("personalfileList");
		System.out.println("~~~~~~~~~~~~~~~~personalfileList"+personalfileList);
		
		
		
	
		//DB에서 파일 지우기
		personal_appendixService.fileDelete(p_Num);	
		

		for(int i=0; i<personalfileList.size(); i++) {
			try {
				Map<String, Object> file = new HashMap<String, Object>();
				file.put("p_a_RealName", (String) personalfileList.get(i).get("p_a_RealName"));
				file.put("p_a_File", (personalfileList.get(i).get("p_a_File")));
				file.put("p_a_NameEx", (String) personalfileList.get(i).get("p_a_NameEx"));
				file.put("p_a_Size", (String) personalfileList.get(i).get("p_a_Size"));
				file.put("p_Num", p_Num);
				
				personal_appendixService.uploadFile(file);
				
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 session.removeAttribute("personalfileList");
		
		return "redirect:/project/issue/list?c_Id=";
	}
	

	
}




