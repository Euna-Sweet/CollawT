package project.euna.whole_search.service;


import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import project.euna.whole_search.vo.issueCriteria;
import project.euna.whole_search.vo.replyCriteria;
import project.euna.whole_search.vo.voteCriteria;
import project.euna.whole_search.vo.SearchVO;
import project.euna.whole_search.vo.fileCriteria;



public interface Whole_searchService {

	public List<Map> searchIssue(issueCriteria cri) throws DataAccessException;
	public int issueCount(issueCriteria cri) throws Exception;
	public List<Map> searchFile(fileCriteria cri) throws DataAccessException;
	public int fileCount(fileCriteria cri) throws Exception;
	public List<Map> searchVote(voteCriteria cri) throws DataAccessException;
	public int voteCount(voteCriteria cri) throws Exception;
	public List<Map> searchReply(replyCriteria cri) throws DataAccessException;
	public int replyCount(replyCriteria cri) throws Exception;
	public List<Map> comemRead(issueCriteria cri);
	public List<Map> issueWriter(issueCriteria cri) throws Exception;
	public List<Map> fileWriter(fileCriteria cri) throws Exception;
	public List<Map> voteWriter(voteCriteria cri) throws Exception;
	public List<Map> replyWriter(replyCriteria cri) throws Exception;
	public List<Map> fileEx(fileCriteria cri) throws Exception;
}
