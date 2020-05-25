package project.euna.appendix.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.euna.appendix.vo.AppendixVO;


@Repository
public class AppendixDAOImpl implements AppendixDAO {

	@Autowired
	private SqlSession sqlSession;


	// 파일 첨부
	@Override
	public void uploadFile(Map<String, Object> hmap) throws Exception {
		sqlSession.insert("appendix.saveFile", hmap);
	}
	
	//파일 조회 리스트
	@Override
	public List<Map> fileList(String i_Num) throws DataAccessException {

		List<Map> list = sqlSession.selectList("appendix.fileList", i_Num);
		return list;

	}
	
	//파일 다운로드
	@Override
	public Map<String, Object> download(String a_Num) {
		return sqlSession.selectOne("appendix.download", a_Num);
	}
	
	//글쓰기 화면 전환 시 글번호 가져오기
	@Override
	public Map<String, Object> get_i_Num() {
		return sqlSession.selectOne("appendix.get_i_Num");
	}
	
	//첨부파일 수정
		@Override
		public void updateFile(AppendixVO appendixVO) throws Exception {
			sqlSession.update("appendix.fileUpdate", appendixVO);
		}
		
	//협업공간 첨부파일 복사
	@Override
	public void copyFile(Map<String, Object> hmap) throws Exception {
		sqlSession.insert("appendix.copyFile", hmap);
	}
	
	//작성 취소 시 파일 없애기
	@Override
	public void fileDelete(String i_Num) throws Exception {
		sqlSession.delete("appendix.fileDelete", i_Num);

	}
		

}
