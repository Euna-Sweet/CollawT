package project.euna.appendix.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import project.euna.issue.vo.IssueVO;

public class AppendixVO {

	private String a_RealName;
	private MultipartFile a_File;
	private String a_NameEx;
	private String i_Num;
	private String a_Num;
	private String a_Size;
	private String a_Del;
	
	
	public String getA_RealName() {
		return a_RealName;
	}
	public void setA_RealName(String a_RealName) {
		this.a_RealName = a_RealName;
	}
	public MultipartFile getA_File() {
		return a_File;
	}
	public void setA_File(MultipartFile a_File) {
		this.a_File = a_File;
	}
	public String getA_NameEx() {
		return a_NameEx;
	}
	public void setA_NameEx(String a_NameEx) {
		this.a_NameEx = a_NameEx;
	}
	public String getI_Num() {
		return i_Num;
	}
	public void setI_Num(String i_Num) {
		this.i_Num = i_Num;
	}
	public String getA_Num() {
		return a_Num;
	}
	public void setA_Num(String a_Num) {
		this.a_Num = a_Num;
	}
	
	public String getA_Size() {
		return a_Size;
	}
	public void setA_Size(String a_Size) {
		this.a_Size = a_Size;
	}
	public String getA_Del() {
		return a_Del;
	}
	public void setA_Del(String a_Del) {
		this.a_Del = a_Del;
	}
	
	public List<Map<String, Object>> parseUpdateFileInfo(IssueVO issueVO, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest) throws Exception{ 
		Iterator<String> iterator = mpRequest.getFileNames();
		MultipartFile multipartFile = null; 
		String a_RealName = null; 
		String a_Size = null; 
		String a_NameEx = null; 
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> listMap = null; 
		String i_Num=issueVO.getI_Num();
		
		while(iterator.hasNext()){ 
			multipartFile = mpRequest.getFile(iterator.next()); 
			if(multipartFile.isEmpty() == false){ 
				a_RealName = multipartFile.getOriginalFilename(); // 원본 파일 명
				a_Size = Long.toString((multipartFile.getSize())); // 파일 사이즈
				a_NameEx = a_RealName.substring(a_RealName.lastIndexOf(".")+1); 
				
				listMap = new HashMap<String, Object>();
				listMap.put("IS_NEW", "Y");
				listMap.put("i_Num", i_Num);
				listMap.put("a_File", getA_File().getBytes());
				listMap.put("a_RealName", a_RealName);
				listMap.put("a_Size", a_Size);
				listMap.put("a_NameEx", a_NameEx);
				list.add(listMap); 
			} 
		}
		if(files != null && fileNames != null){ 
			for(int i = 0; i<fileNames.length; i++) {
					listMap = new HashMap<String,Object>();
                    listMap.put("IS_NEW", "N");
					listMap.put("FILE_NO", files[i]); 
					list.add(listMap); 
			}
		}
		return list; 
	}

	

}
