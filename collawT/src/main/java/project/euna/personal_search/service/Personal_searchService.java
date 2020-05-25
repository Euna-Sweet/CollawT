package project.euna.personal_search.service;


import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import project.euna.personal_search.vo.Criteria;


public interface Personal_searchService {

	public List<Map> myBoardlist(Criteria cri) throws Exception;
	public int myBoardlistCount(String mem_Id) throws Exception;
	public List<Map> myFile(String mem_Id) throws DataAccessException;
	public List<Map> myReplylist(Criteria cri) throws Exception;
	public int myReplylistCount(String mem_Id) throws Exception;
	public List<Map> myissueList(Criteria cri) throws Exception;
	public int myissueListCount(String mem_Id) throws Exception;

}
