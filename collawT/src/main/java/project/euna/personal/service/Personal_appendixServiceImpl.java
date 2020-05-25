package project.euna.personal.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.euna.personal.dao.Personal_appendixDAO;
import project.euna.personal.vo.Personal_appendixVO;




@Service
public class Personal_appendixServiceImpl implements Personal_appendixService {
	
	@Autowired
	Personal_appendixDAO personal_appendixDAO;

	//파일 목록 조회
	@Override
		public List<Map> fileList(String i_Num) throws Exception{
			List<Map> list = personal_appendixDAO.fileList(i_Num);

			return list;
		}
		
	//파일 첨부
	@Override
	public void uploadFile(Map<String, Object> hmap) throws Exception {
		personal_appendixDAO.uploadFile(hmap);
	}
	
	//첨부파일 수정
		@Override
		public void updateFile(Personal_appendixVO personal_appendixVO) throws Exception {
			personal_appendixDAO.updateFile(personal_appendixVO);
			
//			List<Map<String, Object>> list = appendixVO.parseUpdateFileInfo(issueVO, files, fileNames, mpRequest);
//			Map<String, Object> tempMap = null;
//			int size = list.size();
//			for(int i = 0; i<size; i++) {
//				tempMap = list.get(i);
//				if(tempMap.get("IS_NEW").equals("Y")) {
//					appendixDAO.uploadFile(tempMap);
//				}else {
//					appendixDAO.uploadFile(tempMap);
//				}
//			}
		}
		
		//협업공간 첨부파일 복사
		@Override
		public void copyFile(Map<String, Object> hmap) throws Exception {
			personal_appendixDAO.copyFile(hmap);
		}
		
		//다른 협업공간으로 첨부파일 복사
		@Override
		public void copyFiletoCowork(Map<String, Object> hmap) throws Exception {
			personal_appendixDAO.copyFiletoCowork(hmap);
		}
		
		//작성 취소 시 파일 없애기
		@Override
		public void fileDelete(String p_Num) throws Exception {
			personal_appendixDAO.fileDelete(p_Num);

		}


}

