package project.notify.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.euna.issue.vo.IssueVO;
import project.notify.vo.NotifyVO;

@Repository
public class NotifyDAOImpl implements NotifyDAO {
	IssueVO issueVO;

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int notifyInsert(Map map) {
		int result;
		result = sqlSession.update("notify.notifyInsert", map);
		return result;
	}

	@Override
	public List<Map> searchNotify(Map<String, Object> searchMap) {
		// TODO Auto-generated method stub
		System.out.println("searchNotify에 들어가는데 i_Num 있는지 없는지 확인==:"+searchMap.toString());
		List<Map> list = sqlSession.selectList("notify.searchNotify", searchMap);
		System.out.println("서치타고 나와서 i_Content 어떻게 돼있는지=="+list.toString());
		return list;
	}
	
	@Override
	public List<Map> replyList(Map<String,Object> searchMap){
		List<Map> list = sqlSession.selectList("notify.replyList", searchMap);
		return list;
	}

	@Override
	public void notifyUpdate(Map<String,Object>searchMap) {
		 sqlSession.update("notify.notifyUpdate",searchMap);
	}
	
	@Override
	public void replyUpdate(Map<String,Object> searchMap) {
		sqlSession.update("notify.replyUpdate", searchMap);
	}
	@Override
	public void voteUpdate(Map<String,Object> searchMap) {
		sqlSession.update("notify.voteUpdate",searchMap);
	}
	@Override
	public void votereplyUpdate(Map<String,Object> searchMap) {
		sqlSession.update("notify.votereplyUpdate", searchMap);
	}

	@Override
	public List<Map> viewNotify(Map<String, Object> searchMap) throws DataAccessException {
		List<Map> list = sqlSession.selectList("notify.viewNotify",searchMap);
		return list;
	}

	@Override
	public List<Map> viewReply(Map<String, Object> searchMap) throws DataAccessException{
		List<Map> list = sqlSession.selectList("notify.viewReply",searchMap);
		//System.out.println("viewReply타냐~~~~~~~~~~~~~~~~~"+list.toString());
		return list;
	}
	
	@Override
	public List<Map> viewVote(Map<String,Object> searchMap) throws DataAccessException{
		List<Map> list = sqlSession.selectList("notify.viewVote", searchMap);
		return list;
	}


}
