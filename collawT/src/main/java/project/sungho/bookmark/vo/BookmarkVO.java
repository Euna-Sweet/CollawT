package project.sungho.bookmark.vo;

import org.springframework.stereotype.Component;

@Component
public class BookmarkVO {

	private String mem_Id;
	private String i_Num;
	
	public String getMem_Id() {
		return mem_Id;
	}
	public void setMem_Id(String mem_Id) {
		this.mem_Id = mem_Id;
	}
	public String getI_Num() {
		return i_Num;
	}
	public void setI_Num(String i_Num) {
		this.i_Num = i_Num;
	}
	
}
