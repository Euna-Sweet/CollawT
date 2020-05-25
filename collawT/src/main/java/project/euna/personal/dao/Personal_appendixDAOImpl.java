package project.euna.personal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.euna.personal.vo.Personal_appendixVO;



@Repository
public class Personal_appendixDAOImpl implements Personal_appendixDAO {

	@Autowired
	private SqlSession sqlSession;


	// 파일 첨부
	@Override
	public void uploadFile(Map<String, Object> hmap) throws Exception {
		sqlSession.insert("personal_appendix.saveFile", hmap);
	}
	
	//파일 조회 리스트
	@Override
	public List<Map> fileList(String p_Num) throws DataAccessException {

		List<Map> list = sqlSession.selectList("personal_appendix.fileList", p_Num);
		return list;

	}
	
	//파일 다운로드
	@Override
	public Map<String, Object> download(String p_a_Num) {
		return sqlSession.selectOne("personal_appendix.download", p_a_Num);
	}
	
	//글쓰기 화면 전환 시 글번호 가져오기
	@Override
	public Map<String, Object> get_p_Num() {
		return sqlSession.selectOne("personal_appendix.get_p_Num");
	}
	
	//첨부파일 수정
		@Override
		public void updateFile(Personal_appendixVO personal_appendixVO) throws Exception {
			sqlSession.update("personal_appendix.fileUpdate", personal_appendixVO);
		}
		
	//협업공간 첨부파일 복사
	@Override
	public void copyFile(Map<String, Object> hmap) throws Exception {
		sqlSession.insert("personal_appendix.copyFile", hmap);
	}
	
	//협업공간으로 첨부파일 복사
	@Override
	public void copyFiletoCowork(Map<String, Object> hmap) throws Exception {
		sqlSession.insert("personal_appendix.copyFiletoCowork", hmap);
	}
	
	//작성 취소 시 파일 없애기
	@Override
	public void fileDelete(String p_Num) throws Exception {
		sqlSession.delete("personal_appendix.fileDelete", p_Num);

	}
		

}
