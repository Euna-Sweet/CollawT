package project.euna.personal.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import project.euna.issue.vo.IssueVO;

public class Personal_appendixVO {

	private String p_a_RealName;
	private MultipartFile p_a_File;
	private String a_NameEx;
	private String p_Num;
	private String p_a_Num;
	private String p_a_Size;
	private String p_a_Del;
	public String getP_a_RealName() {
		return p_a_RealName;
	}
	public void setP_a_RealName(String p_a_RealName) {
		this.p_a_RealName = p_a_RealName;
	}
	public MultipartFile getP_a_File() {
		return p_a_File;
	}
	public void setP_a_File(MultipartFile p_a_File) {
		this.p_a_File = p_a_File;
	}
	public String getA_NameEx() {
		return a_NameEx;
	}
	public void setA_NameEx(String a_NameEx) {
		this.a_NameEx = a_NameEx;
	}
	public String getP_Num() {
		return p_Num;
	}
	public void setP_Num(String p_Num) {
		this.p_Num = p_Num;
	}
	public String getP_a_Num() {
		return p_a_Num;
	}
	public void setP_a_Num(String p_a_Num) {
		this.p_a_Num = p_a_Num;
	}
	public String getP_a_Size() {
		return p_a_Size;
	}
	public void setP_a_Size(String p_a_Size) {
		this.p_a_Size = p_a_Size;
	}
	public String getP_a_Del() {
		return p_a_Del;
	}
	public void setP_a_Del(String p_a_Del) {
		this.p_a_Del = p_a_Del;
	}
	
	


}
