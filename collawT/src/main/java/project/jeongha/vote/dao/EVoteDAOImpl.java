package project.jeongha.vote.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.jeongha.vote.vo.Criteria;

@Repository
public class EVoteDAOImpl implements EVoteDAO {
//
	@Autowired
	private SqlSession sqlSession;

	
	// 아이디 중복 검사
		public int checkId(Map<String, Object> mem_Id) throws Exception {
			int result = sqlSession.selectOne("voteResult.checkId", mem_Id);
			return result;
		}
	// 투표 입력
	@Override
	public void voteInsert(Map map) {
		sqlSession.update("voteResult.voteInsert", map);
		sqlSession.update("notify.voteInsertNotify", map);
	}

	// 항목들 입력
	@Override
	public int votedInsert(Map map) {
		int result = sqlSession.insert("voteResult.votedInsert", map);
		return result;
	}

	// 목록 페이징
	@Override
	public List<Map> searchList(Criteria cri) throws DataAccessException {

		List<Map> list = sqlSession.selectList("voteResult.searchList", cri);
		return list;

	}

	// 게시물 총 갯수
	@Override
	public int listCount(String c_Id) throws Exception {
		return sqlSession.selectOne("voteResult.listCount", c_Id);
	}

	// 투표 조회
	@Override
	public Map<String, Object> voteRead(Map<String, Object> v_Num) {
		return sqlSession.selectOne("voteResult.voteRead", v_Num);
	}

	// 투표 내용 조회
	@Override
	public List<Map> votedRead(String v_Num) {
		return sqlSession.selectList("voteResult.votedRead", v_Num);
	}
	
	//카운트
	@Override
	public  List<Map> voteCount(Map<String, Object> voteCount)throws Exception {
		List<Map>result  = sqlSession.selectList("voteResult.voteCount", voteCount );
		return result;
	}
	
	//카운트
	@Override
	public  List<Map> voteTotal(Map<String, Object> searchMap)throws Exception {
		List<Map>result  = sqlSession.selectList("voteResult.voteTotal", searchMap );
		return result;
	}
	

	// 글 삭제
	@Override
	public void voteDelete(String i_Num) throws Exception {
		sqlSession.delete("voteResult.voteDelete", i_Num);
		sqlSession.update("notify.voteUpdate", i_Num);
	}
	
	// 글 삭제 시 투표 보기 삭제
	@Override
	public void votedDelete(String i_Num) throws Exception {
		sqlSession.delete("voteResult.votedDelete", i_Num);

	}
	
	// 글 삭제 시 댓글 삭제
	@Override
	public void votereplyDelete(String i_Num) throws Exception {
		sqlSession.delete("voteResult.votereplyDelete", i_Num);

	}
	
	//투표 카운트
	
	// 글 수정
	@Override
	public void voteUpdate(Map<String, Object> cmap) throws Exception {
		sqlSession.update("voteResult.voteUpdate", cmap);
	}

	// 이슈그룹 조회
	@Override
	public List<Map> igRead() {
		return sqlSession.selectList("voteResult.igRead");

	}

	// 협업공간 내 멤버 조회
	@Override
	public List<Map> comemRead(String c_id) {
		return sqlSession.selectList("voteResult.comemRead", c_id);

	}

	// 이슈 담당자 삽입
	@Override
	public void comemInsert(Map<String, Object> dataMap) throws DataAccessException {

		sqlSession.update("voteResult.comemInsert", dataMap);

		// TODO Auto-generated method stub

	}

	// 이슈 담당자 조회
	@Override
	public List<Map> chargerRead(String i_Num) {
		return sqlSession.selectList("issue.chargerRead", i_Num);
	}

	// 이슈 담당자 수정(삭제 후 다시 삽입)
	@Override
	public void chargerDelete(String i_Num) throws Exception {
		sqlSession.delete("issue.chargerDelete", i_Num);

	}

	// v_num가져오기
	@Override
	public Map<String, Object> voteInfo(Map<String, Object> voteInfo) throws Exception {
		Map<String, Object> result = sqlSession.selectOne("voteResult.v_num", voteInfo);
		return result;
	}
	
	//투표자 넣기
	@Override
	public int voterInsert(Map map) {
		int result;
		result = sqlSession.update("voteResult.voterInsert", map);
		
		return result;
	}
	@Override
	public Map<String, Object> countCowork(Map<String, Object> searchC_Id) throws Exception {
		
		return sqlSession.selectOne("voteResult.countCowork", searchC_Id);
	}
	//수정
	@Override
	public void votedUpdate(Map<String, Object> cmap) throws Exception {
		sqlSession.update("voteResult.votedUpdate", cmap);
		
	}
	
	//업데이트 vs_Num
	@Override
	public void updateVs_Num(Map<String, Object> searchMap) {
		
		sqlSession.update("voteResult.updateVs_Num",searchMap);
	}
	
	@Override
	public Map<String, Object> voteReadList(Map<String, Object> searchMap) {
		return sqlSession.selectOne("voteResult.voteReadList", searchMap);
	}
	
	

	// 협업공간 조회
//	@Override
//	public List<Map> coRead(String mem_id) {
//		return sqlSession.selectList("issue.coRead", mem_id);
//
//	}

	// 글쓰기 화면 전환 시 글번호 가져오기
//	@Override
//	public Map<String, Object> get_i_Num() {
//		return sqlSession.selectOne("issue.get_i_Num");
//	}

	// 이미지 불러오기
//	public Map<String, Object> getByteImage(String mem_Id) { return
//	sqlSession.selectOne("member.getByteImage", mem_Id); }

}
