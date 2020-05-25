package project.sungho.apply.vo;

import org.springframework.stereotype.Component;

@Component
public class ApplyVO {

	private String c_Id;
	private String mem_Id;
	private String ap_Date;
	private String ap_Yn;
	private String ap_Status;
	
	
	public String getC_Id() {
		return c_Id;
	}
	public void setC_Id(String c_Id) {
		this.c_Id = c_Id;
	}
	public String getMem_Id() {
		return mem_Id;
	}
	public void setMem_Id(String mem_Id) {
		this.mem_Id = mem_Id;
	}
	public String getAp_Date() {
		return ap_Date;
	}
	public void setAp_Date(String ap_Date) {
		this.ap_Date = ap_Date;
	}
	public String getAp_Yn() {
		return ap_Yn;
	}
	public void setAp_Yn(String ap_Yn) {
		this.ap_Yn = ap_Yn;
	}
	public String getAp_Status() {
		return ap_Status;
	}
	public void setAp_Status(String ap_Status) {
		this.ap_Status = ap_Status;
	}
	
	
	
}
