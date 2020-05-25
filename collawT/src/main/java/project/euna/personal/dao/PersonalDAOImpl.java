package project.euna.personal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.euna.personal.vo.Criteria;
import project.euna.personal.vo.PersonalVO;

@Repository
public class PersonalDAOImpl implements PersonalDAO {
//
	@Autowired
	private SqlSession sqlSession;

	// 글 입력
	@Override
	public int personalInsert(Map map) {
		int result;
		result = sqlSession.update("personal.personalInsert", map);
		return result;
	}
	
	//글쓰기 화면 전환 시 글번호 가져오기
	@Override
	public Map<String, Object> get_p_Num() {
		return sqlSession.selectOne("personal.get_p_Num");
	}

	// 글 목록 페이징
	@Override
	public List<Map> searchList(Criteria cri) throws DataAccessException {

		List<Map> list = sqlSession.selectList("personal.searchList", cri);
		return list;

	}

	// 게시물 총 갯수
	@Override
	public int listCount(String mem_Id) throws Exception {
		return sqlSession.selectOne("personal.listCount", mem_Id);
	}

	// 게시글 조회
	@Override
	public Map<String, Object> personalRead(String p_Num) {
		return sqlSession.selectOne("personal.personalRead", p_Num);

	}

	// 글 삭제
	@Override
	public void personalDelete(String p_Num) throws Exception {
		sqlSession.delete("personal.personalDelete", p_Num);

	}
	
	// 글 삭제 시 메모 삭제
	@Override
	public void personalmemoDelete(String p_Num) throws Exception {
		sqlSession.delete("personal.personalmemoDelete", p_Num);

	}

	// 글 수정
	@Override
	public void personalUpdate(PersonalVO personalVO) throws Exception {
		sqlSession.update("personal.personalUpdate", personalVO);
	}
	
	// 이슈그룹 조회
	@Override
	public List<Map> igRead() {
		return sqlSession.selectList("personal.igRead");

	}
	
	//협업공간 내 멤버 조회
	@Override
	public List<Map> comemRead(String mem_Id) {
		return sqlSession.selectList("personal.comemRead", mem_Id);

	}
	
	//이슈 담당자 삽입
	@Override
	public void comemInsert(Map<String, Object> dataMap) throws DataAccessException {
		
		sqlSession.update("personal.comemInsert",dataMap);
		
		// TODO Auto-generated method stub
		
	}
	
	//이슈 담당자 조회
	@Override
	public List<Map> chargerRead(String p_Num) {
		return sqlSession.selectList("personal.chargerRead", p_Num);
	}
	
	//이슈 담당자 수정(삭제 후 다시 삽입)
	@Override
	public void chargerDelete(String p_Num) throws Exception {
		sqlSession.delete("personal.chargerDelete", p_Num);

	}
	
	


	


	//이미지 불러오기
//	public Map<String, Object> getByteImage(String mem_Id) { return
//	sqlSession.selectOne("member.getByteImage", mem_Id); }
	 
	// 협업공간 조회
	@Override
	public List<Map> coRead(String mem_id) {
		return sqlSession.selectList("personal.coRead", mem_id);

	}
	
	// 다른 협업공간으로 복사
	@Override
	public int personalCopy(Map map) {
		int result;
		result = sqlSession.update("personal.personalCopy", map);
		return result;
	}
	
	//글 목록 조회 시  이슈 담당자 화면에 뿌리기 용
	@Override
	public List<Map> chargerList(String mem_Id) {
		return sqlSession.selectList("personal.chargerList", mem_Id);
	}
	
	
	//내공간 알림
	@Override
	public List<Map> newspeed(String mem_Id) {
		return sqlSession.selectList("personal.newspeed", mem_Id);
	}
	
	//참여 가능한 투표
	@Override
	public List<Map> voteAvailable(String mem_Id) {
		return sqlSession.selectList("personal.voteAvailable", mem_Id);
	}
	
	//내가 담당한 이슈 현황
	@Override
	public List<Map> myissue(String mem_Id) {
		return sqlSession.selectList("personal.myissue", mem_Id);
	}

	
}
