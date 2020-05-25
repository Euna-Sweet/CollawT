package project.euna.issue.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.euna.issue.vo.Criteria;
import project.euna.issue.vo.IssueVO;

@Repository
public class IssueDAOImpl implements IssueDAO {
//
	@Autowired
	private SqlSession sqlSession;

	// 글 입력
	@Override
	public int issueInsert(Map map) {
		int result;
		result = sqlSession.update("issue.issueInsert", map);
		sqlSession.update("notify.notifyInsert", map);
		return result;
	}

	// 글 목록 페이징
	@Override
	public List<Map> searchList(Criteria cri) throws DataAccessException {

		List<Map> list = sqlSession.selectList("issue.searchList", cri);
		return list;

	}

	// 게시물 총 갯수
	@Override
	public int listCount(String c_Id) throws Exception {
		return sqlSession.selectOne("issue.listCount", c_Id);
	}

	// 게시글 조회
	@Override
	public Map<String, Object> issueRead(String i_Num) {
		return sqlSession.selectOne("issue.issueRead", i_Num);

	}

	// 글 삭제
	@Override
	public void issueDelete(String i_Num) throws Exception {
		sqlSession.delete("issue.issueDelete", i_Num);
		sqlSession.update("notify.notifyUpdate", i_Num);

	}
	
	// 글 삭제 시 댓글 삭제
	@Override
	public void replyDelete(String i_Num) throws Exception {
		sqlSession.delete("issue.replyDelete", i_Num);

	}

	// 글 수정
	@Override
	public void issueUpdate(IssueVO issueVO) throws Exception {
		sqlSession.update("issue.issueUpdate", issueVO);
	}
	
	// 이슈그룹 조회
	@Override
	public List<Map> igRead() {
		return sqlSession.selectList("issue.igRead");

	}
	
	//협업공간 내 멤버 조회
	@Override
	public List<Map> comemRead(String c_id) {
		return sqlSession.selectList("issue.comemRead", c_id);

	}
	
	//이슈 담당자 삽입
	@Override
	public void comemInsert(Map<String, Object> dataMap) throws DataAccessException {
		
		sqlSession.update("issue.comemInsert",dataMap);
		
		// TODO Auto-generated method stub
		
	}
	
	//이슈 담당자 조회
	@Override
	public List<Map> chargerRead(String i_Num) {
		return sqlSession.selectList("issue.chargerRead", i_Num);
	}
	
	//이슈 담당자 수정(삭제 후 다시 삽입)
	@Override
	public void chargerDelete(String i_Num) throws Exception {
		sqlSession.delete("issue.chargerDelete", i_Num);

	}
	
	
	//글쓰기 화면 전환 시 글번호 가져오기
	@Override
	public Map<String, Object> get_i_Num() {
		return sqlSession.selectOne("issue.get_i_Num");
	}

	


	//이미지 불러오기
//	public Map<String, Object> getByteImage(String mem_Id) { return
//	sqlSession.selectOne("member.getByteImage", mem_Id); }
	 
	// 협업공간 조회
	@Override
	public List<Map> coRead(String mem_id) {
		return sqlSession.selectList("issue.coRead", mem_id);

	}
	
	// 다른 협업공간으로 복사
	@Override
	public int issueCopy(Map map) {
		int result;
		result = sqlSession.update("issue.issueCopy", map);
		return result;
	}
	
	//글 목록 조회 시  이슈 담당자 화면에 뿌리기 용
	@Override
	public List<Map> chargerList(String c_Id) {
		return sqlSession.selectList("issue.chargerList", c_Id);
	}
	
	// 상태 카운트
	@Override
	public List<Map> igCount(String c_Id) {
		return sqlSession.selectList("issue.igCount", c_Id);

	}
	
	// 홈 화면 이슈/투표 최근글
	@Override
	public List<Map> recentBoard(String c_Id) {
		return sqlSession.selectList("issue.recentBoard", c_Id);

	}
	
	// 홈 화면 현재 진행중인 투표
	@Override
	public List<Map> voteOn(String c_Id) {
		return sqlSession.selectList("issue.voteOn", c_Id);

	}
	
	// 투표 참여율
	@Override
	public String votePercent(String c_Id) {
		return sqlSession.selectOne("issue.votePercent", c_Id);

	}
	
}
