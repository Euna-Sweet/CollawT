package project.euna.appendix.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import project.euna.appendix.vo.AppendixVO;
import project.euna.issue.vo.IssueVO;

public interface AppendixController {

	public String saveFile(IssueVO issueVO, MultipartHttpServletRequest mtfRequest, AppendixVO appendixVO) throws Exception;
	public ResponseEntity<byte[]> download(AppendixVO appendixVO, HttpServletResponse response);
	public List<Map> fileRead(@RequestParam ("i_Num")String i_Num, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public void updateFile(AppendixVO appendixVO) throws Exception;
	public String fileDelete(@RequestParam ("i_Num")String i_Num, @RequestParam ("c_Id")String c_Id) throws Exception;
	public String fileCancel(String c_Id, String i_Num, HttpServletRequest request) throws Exception;
}

