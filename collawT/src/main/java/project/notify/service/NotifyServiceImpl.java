package project.notify.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import project.euna.issue.vo.IssueVO;
import project.notify.dao.NotifyDAOImpl;
import project.notify.vo.NotifyVO;

@Service
public class NotifyServiceImpl implements NotifyService{
	
	@Autowired
	NotifyDAOImpl notifyDAO;

	@Override
	public void notifyInsert(Map map) {
		notifyDAO.notifyInsert(map);
	}

	@Override
	public List<Map> searchNotify(Map<String, Object> searchMap) {
		// TODO Auto-generated method stub
		List<Map> list = notifyDAO.searchNotify(searchMap);
		return list;
	}
	@Override
	public void notifyUpdate(Map<String,Object> searchMap) {
		// TODO Auto-generated method stub
		 notifyDAO.notifyUpdate(searchMap);		
	}
	
	@Override
	public void voteUpdate(Map<String,Object> searchMap) {
		notifyDAO.voteUpdate(searchMap);
	}
	@Override
	public void replyUpdate(Map<String,Object> searchMap) {
		notifyDAO.replyUpdate(searchMap);
	}
	@Override
	public void votereplyUpdate(Map<String,Object> searchMap) {
		notifyDAO.votereplyUpdate(searchMap);
	}

	@Override
	public List<Map> viewNotify(Map<String, Object> searchMap) throws DataAccessException {
		List<Map> list = notifyDAO.viewNotify(searchMap);
		return list;
	}
	
	@Override
	public List<Map> viewReply(Map<String,Object> searchMap) throws DataAccessException{
		List<Map> list = notifyDAO.viewReply(searchMap);
		return list;
	}
	
	@Override
	public List<Map> replyList(Map<String,Object> searchMap) throws DataAccessException{
		List<Map> list = notifyDAO.replyList(searchMap);
		return list;
	}

	@Override
	public List<Map> viewVote(Map<String,Object> searchMap) throws DataAccessException{
		List<Map> list = notifyDAO.viewVote(searchMap);
		return list;
	}
	
	
}
