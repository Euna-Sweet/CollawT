package project.jeongha.member.vo;

import org.springframework.web.multipart.MultipartFile;

public class MemberVO {
	//VO는 파라미터와 이름을 맞춰줘야 자동으로 get set 가능합니다. 가능하면 이름 통일해주세요
	private String mem_Id;
	private String mem_Pwd;
	private String mem_Name;
	private String mem_Imagine;
	private MultipartFile mem_File;
	private String mem_Key;
	private String mem_Status;
	
	public String getMem_Status() {
		return mem_Status;
	}
	public void setMem_Status(String mem_Status) {
		this.mem_Status = mem_Status;
	}
	public String getMem_Key() {
		return mem_Key;
	}
	public void setMem_Key(String mem_Key) {
		this.mem_Key = mem_Key;
	}
	public String getMem_Id() {
		return mem_Id;
	}
	public void setMem_Id(String mem_Id) {
		this.mem_Id = mem_Id;
	}
	public String getMem_Pwd() {
		return mem_Pwd;
	}
	public void setMem_Pwd(String mem_Pwd) {
		this.mem_Pwd = mem_Pwd;
	}
	public String getMem_Name() {
		return mem_Name;
	}
	public void setMem_Name(String mem_Name) {
		this.mem_Name = mem_Name;
	}
	public String getMem_Imagine() {
		return mem_Imagine;
	}
	public void setMem_Imagine(String mem_Imagine) {
		this.mem_Imagine = mem_Imagine;
	}
	public MultipartFile getMem_File() {
		return mem_File;
	}
	public void setMem_File(MultipartFile mem_File) {
		this.mem_File = mem_File;
	}
	
}