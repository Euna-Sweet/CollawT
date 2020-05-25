package project.euna.personal_search.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import project.euna.personal_search.dao.Personal_searchDAOImpl;
import project.euna.personal_search.vo.Criteria;
import project.notify.dao.NotifyDAOImpl;


@Service
public class Personal_searchServiceImpl implements Personal_searchService {
	
	@Inject
	Personal_searchDAOImpl personal_searchDAO;
	
	@Inject
	NotifyDAOImpl notifyDAO;
	

	
	
	//목록 조회 페이징
	@Override
	public List<Map> myBoardlist(Criteria cri) throws Exception{
		List<Map> list = personal_searchDAO.myBoardlist(cri);
		return list;
	}
	
	//게시물 총 갯수
	@Override
	public int myBoardlistCount(String mem_Id) throws Exception{
		return personal_searchDAO.myBoardlistCount(mem_Id);
	}
	
	//댓글 목록 조회 페이징
	@Override
	public List<Map> myReplylist(Criteria cri) throws Exception{
		List<Map> list = personal_searchDAO.myReplylist(cri);
		return list;
	}
	
	//댓글  총 갯수
	@Override
	public int myReplylistCount(String mem_Id) throws Exception{
		return personal_searchDAO.myReplylistCount(mem_Id);
	}


	//전체 파일
	@Override
	public List<Map> myFile(String mem_Id) throws DataAccessException {
		return personal_searchDAO.myFile(mem_Id);

}
	
	//내 담당 리스트 페이징
	@Override
	public List<Map> myissueList(Criteria cri) throws Exception{
		List<Map> list = personal_searchDAO.myissueList(cri);
		return list;
	}
	
	//내 담당 리스트 총 갯수
	@Override
	public int myissueListCount(String mem_Id) throws Exception{
		return personal_searchDAO.myissueListCount(mem_Id);
	}
	
}
