package project.euna.whole_search.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.euna.whole_search.vo.fileCriteria;
import project.euna.whole_search.vo.issueCriteria;
import project.euna.whole_search.vo.replyCriteria;
import project.euna.whole_search.vo.voteCriteria;



@Repository
public class Whole_searchDAOImpl implements Whole_searchDAO {

	@Inject
	private SqlSession sqlSession;


	
	//전체 이슈 검색결과 페이징
	@Override
	public List<Map> searchIssue(issueCriteria cri) throws DataAccessException {

		List<Map> list = sqlSession.selectList("whole_search.searchIssue", cri);
		return list;

	}
	
	// 이슈 검색결과 총 갯수
	@Override
	public int issueCount(issueCriteria cri) throws Exception {
		return sqlSession.selectOne("whole_search.issueCount", cri);
	}
	
	//이슈 검색결과 작성자 필터링
	@Override
	public List<Map> issueWriter(issueCriteria cri) throws DataAccessException {

		List<Map> list = sqlSession.selectList("whole_search.issueWriter", cri);
		return list;

	}
	
	
	//전체 파일 검색결과 페이징
	@Override
	public List<Map> searchFile(fileCriteria cri) throws DataAccessException {

		List<Map> list = sqlSession.selectList("whole_search.searchFile", cri);
		return list;

	}
	
	//파일 검색결과 총 갯수
	@Override
	public int fileCount(fileCriteria cri) throws Exception {
		return sqlSession.selectOne("whole_search.fileCount", cri);
	}
	
	//파일 검색결과 작성자 필터링
	@Override
	public List<Map> fileWriter(fileCriteria cri) throws DataAccessException {

		List<Map> list = sqlSession.selectList("whole_search.fileWriter", cri);
		return list;

	}
	
	
	//파일 검색결과 확장자 필터링
	@Override
	public List<Map> fileEx(fileCriteria cri) throws DataAccessException {

		List<Map> list = sqlSession.selectList("whole_search.fileEx", cri);
		return list;

	}
	
	
	//전체 투표 검색결과 페이징
	@Override
	public List<Map> searchVote(voteCriteria cri) throws DataAccessException {

		List<Map> list = sqlSession.selectList("whole_search.searchVote", cri);
		return list;

	}
	
	//투표 검색결과 총 갯수
	@Override
	public int voteCount(voteCriteria cri) throws Exception {
		return sqlSession.selectOne("whole_search.voteCount", cri);
	}
	
	//투표 검색결과 작성자 필터링
	@Override
	public List<Map> voteWriter(voteCriteria cri) throws DataAccessException {

		List<Map> list = sqlSession.selectList("whole_search.voteWriter", cri);
		return list;

	}
	
	
	//전체 댓글 검색결과 페이징
	@Override
	public List<Map> searchReply(replyCriteria cri) throws DataAccessException {

		List<Map> list = sqlSession.selectList("whole_search.searchReply", cri);
		return list;

	}
	
	//댓글 검색결과 총 갯수
	@Override
	public int replyCount(replyCriteria cri) throws Exception {
		return sqlSession.selectOne("whole_search.replyCount", cri);
	}
	
	//댓글 검색결과 작성자 필터링
	@Override
	public List<Map> replyWriter(replyCriteria cri) throws DataAccessException {

		List<Map> list = sqlSession.selectList("whole_search.replyWriter", cri);
		return list;

	}
	
	
	//협업공간 내 멤버 조회
	@Override
	public List<Map> comemRead(issueCriteria cri) {
		return sqlSession.selectList("whole_search.comemRead", cri);

	}

	
}
