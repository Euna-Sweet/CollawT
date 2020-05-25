package project.euna.personal_search.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.euna.personal_search.vo.Criteria;


@Repository
public class Personal_searchDAOImpl implements Personal_searchDAO {

	@Inject
	private SqlSession sqlSession;


	// 내가쓴 글 목록 페이징
	@Override
	public List<Map> myBoardlist(Criteria cri) throws DataAccessException {

		List<Map> list = sqlSession.selectList("personal_search.myBoardlist", cri);
		return list;

	}

	// 내가쓴 글 게시물 총 갯수
	@Override
	public int myBoardlistCount(String mem_Id) throws Exception {
		return sqlSession.selectOne("personal_search.listCount", mem_Id);
	}
	
	// 내가쓴 댓글 목록 페이징
	@Override
	public List<Map> myReplylist(Criteria cri) throws DataAccessException {

		List<Map> list = sqlSession.selectList("personal_search.myreplylist", cri);
		return list;

	}

	// 내가쓴 댓글 게시물 총 갯수
	@Override
	public int myReplylistCount(String mem_Id) throws Exception {
		return sqlSession.selectOne("personal_search.replylistCount", mem_Id);
	}


	
	
	//전체 파일
	@Override
	public List<Map> myFile(String mem_Id) throws DataAccessException {

		List<Map> list = sqlSession.selectList("personal_search.myFile", mem_Id);
		return list;

	}
	
	
	// 내 담당 이슈 목록 페이징
	@Override
	public List<Map> myissueList(Criteria cri) throws DataAccessException {

		List<Map> list = sqlSession.selectList("personal_search.myissueList", cri);
		return list;

	}

	// 내 담당 이슈 총 갯수
	@Override
	public int myissueListCount(String mem_Id) throws Exception {
		return sqlSession.selectOne("personal_search.myissueListCount", mem_Id);
	}


	
}
