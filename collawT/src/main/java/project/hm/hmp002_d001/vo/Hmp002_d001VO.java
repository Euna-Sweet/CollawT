package project.hm.hmp002_d001.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("hmp002_d001VO")
public class Hmp002_d001VO { 
	private String id = "";
	private String pwd = "";
	private String name = "";
	private String email = "";
	private Date joinDate;
	private int num;
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Hmp002_d001VO() {
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

	
}
