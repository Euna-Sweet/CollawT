package project.euna.personal.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.euna.personal.dao.Personal_memoDAOImpl;
import project.euna.personal.vo.Personal_memoVO;




@Service
public class Personal_memoServiceImpl implements Personal_memoService {
	
	@Autowired
	Personal_memoDAOImpl personal_memoDAOImpl;
	
	//목록 조회
		@Override
		public List<Map> searchList(String p_Num) throws Exception{
			List<Map> list = personal_memoDAOImpl.searchList(p_Num);
			return list;
		}
		
		
		
	//댓글 등록
	@Override
	public void  personal_memoInsert(Personal_memoVO personal_memoVO) {
	int result = personal_memoDAOImpl.personal_memoInsert(personal_memoVO);

	}


	//댓글 삭제
	@Override
	public void personal_memoDelete(String p_m_Num) throws Exception {
		personal_memoDAOImpl.personal_memoDelete(p_m_Num);
	}

	//댓글 수정
	@Override
	public void personal_memoUpdate(Personal_memoVO personal_memoVO) throws Exception {
		personal_memoDAOImpl.personal_memoUpdate(personal_memoVO);
	}

	

}
