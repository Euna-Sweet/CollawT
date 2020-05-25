package project.euna.personal.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import project.euna.appendix.vo.AppendixVO;
import project.euna.issue.vo.IssueVO;
import project.euna.personal.vo.PersonalVO;
import project.euna.personal.vo.Personal_appendixVO;

public interface Personal_appendixController {

	public String saveFile(PersonalVO personalVO, MultipartHttpServletRequest mtfRequest, Personal_appendixVO personal_appendixVO) throws Exception;
	public ResponseEntity<byte[]> download(Personal_appendixVO personal_appendixVO, HttpServletResponse response);
	public List<Map> fileRead(@RequestParam ("p_Num")String p_Num, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public void updateFile(Personal_appendixVO personal_appendixVO) throws Exception;	
	public String fileDelete(String p_Num) throws Exception;
	public String fileCancel(String p_Num, HttpServletRequest request) throws Exception;

}

