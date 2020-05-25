package project.euna.whole_search.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import project.euna.whole_search.dao.Whole_searchDAOImpl;
import project.euna.whole_search.vo.issueCriteria;
import project.euna.whole_search.vo.replyCriteria;
import project.euna.whole_search.vo.voteCriteria;
import project.euna.whole_search.vo.SearchVO;
import project.euna.whole_search.vo.fileCriteria;



@Service
public class Whole_searchServiceImpl implements Whole_searchService {
	
	@Inject
	Whole_searchDAOImpl whole_searchDAO;
	


	// 이슈 검색결과  페이징
	@Override
	public List<Map> searchIssue(issueCriteria cri) throws DataAccessException  {
		return whole_searchDAO.searchIssue(cri);
	
	}
	
	//이슈 검색결과 게시물 총 갯수
	@Override
	public int issueCount(issueCriteria cri) throws Exception{
		return whole_searchDAO.issueCount(cri);
	}
	
	
	//이슈 검색결과 작성자 필터링
	@Override
	public List<Map> issueWriter(issueCriteria cri) throws Exception{
		return whole_searchDAO.issueWriter(cri);
	}
	
	
	
	// 파일 검색결과 페이징
	@Override
	public List<Map> searchFile(fileCriteria cri) throws DataAccessException  {
		return whole_searchDAO.searchFile(cri);
	
	}
	
	//파일 검색결과 게시물 총 갯수
	@Override
	public int fileCount(fileCriteria cri) throws Exception{
		return whole_searchDAO.fileCount(cri);
	}
	
	//파일 검색결과 작성자 필터링
	@Override
	public List<Map> fileWriter(fileCriteria cri) throws Exception{
		return whole_searchDAO.fileWriter(cri);
	}
	
	//파일 검색결과 확장자 필터링
	@Override
	public List<Map> fileEx(fileCriteria cri) throws Exception{
		return whole_searchDAO.fileEx(cri);
	}
	
	
	// 투표 검색결과 페이징
	@Override
	public List<Map> searchVote(voteCriteria cri) throws DataAccessException  {
		return whole_searchDAO.searchVote(cri);
	
	}
	
	//투표 검색결과 게시물 총 갯수
	@Override
	public int voteCount(voteCriteria cri) throws Exception{
		return whole_searchDAO.voteCount(cri);
	}
	
	//투표 검색결과 작성자 필터링
	@Override
	public List<Map> voteWriter(voteCriteria cri) throws Exception{
		return whole_searchDAO.voteWriter(cri);
	}
	
	// 댓글 검색결과 페이징
	@Override
	public List<Map> searchReply(replyCriteria cri) throws DataAccessException  {
		return whole_searchDAO.searchReply(cri);
	
	}
	
	//댓글 검색결과 게시물 총 갯수
	@Override
	public int replyCount(replyCriteria cri) throws Exception{
		return whole_searchDAO.replyCount(cri);
	}
	
	//투표 검색결과 작성자 필터링
	@Override
	public List<Map> replyWriter(replyCriteria cri) throws Exception{
		return whole_searchDAO.replyWriter(cri);
	}
	
	//협업공간 내 멤버 조회
	@Override
	public List<Map> comemRead(issueCriteria cri) {
		return whole_searchDAO.comemRead(cri);

	}
	
}
