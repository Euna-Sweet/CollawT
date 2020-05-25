package project.hm.hmp001_d002.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("hmp001_d002VO")
public class Hmp001_d002VO { 
	private String id = "";
	private String before_id = "";
	private String after_id = "";
	private String pwd = "";
	private String name = "";
	private String email = "";
	private Date joinDate;
	
	public Hmp001_d002VO() {
		System.out.println("MemberVO 생성자");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getBefore_id() {
		return before_id;
	}

	public void setBefore_id(String before_id) {
		this.before_id = before_id;
	}

	public String getAfter_id() {
		return after_id;
	}

	public void setAfter_id(String after_id) {
		this.after_id = after_id;
	}

	
}
