package project.sungho.cowork.vo;

import org.springframework.stereotype.Component;

@Component 
public class CoworkVO {
	
	private String c_Id;
	private String c_Name;
	private String c_Comment;
	private String mem_Id;
	private String c_Category;
	private String invite;
	
	public String getC_Id() {
		return c_Id;
	}
	public void setC_Id(String c_Id) {
		this.c_Id = c_Id;
	}
	public String getC_Name() {
		return c_Name;
	}
	public void setC_Name(String c_Name) {
		this.c_Name = c_Name;
	}
	public String getC_Comment() {
		return c_Comment;
	}
	public void setC_Comment(String c_Comment) {
		this.c_Comment = c_Comment;
	}
	public String getMem_Id() {
		return mem_Id;
	}
	public void setMem_Id(String mem_Id) {
		this.mem_Id = mem_Id;
	}
	public String getC_Category() {
		return c_Category;
	}
	public void setC_Category(String c_Category) {
		this.c_Category = c_Category;
	}
	public String getInvite() {
		return invite;
	}
	public void setInvite(String invite) {
		this.invite = invite;
	}

	
}
