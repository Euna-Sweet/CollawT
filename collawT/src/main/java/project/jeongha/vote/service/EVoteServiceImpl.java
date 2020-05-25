package project.jeongha.vote.service;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.jeongha.vote.dao.EVoteDAO;
import project.jeongha.vote.dao.EVoteDAOImpl;
import project.jeongha.vote.vo.Criteria;
import project.jeongha.vote.vo.VoteVO;
import project.notify.dao.NotifyDAOImpl;


@Service
public class EVoteServiceImpl implements EVoteService {
	
	@Autowired
	EVoteDAOImpl evoteDAO;
	
	@Inject
	NotifyDAOImpl notifyDAO;
	
	//
	
	//투표 제목 및 
	@Override
	public void  voteInsert(Map map) {
		evoteDAO.voteInsert(map);
		//System.out.println("!!!!!!!!!!!!!!!!!11cmap service"+map);
		
	}
	//항목 
	@Override
	public void votedInsert(Map map) {
		evoteDAO.votedInsert(map);
		
	}
	
	
	//목록 조회 페이징
	@Override
	public List<Map> searchList(Criteria cri) throws Exception{
		List<Map> list = evoteDAO.searchList(cri);
		return list;
	}
	
	//게시물 총 갯수
	@Override
	public int listCount(String c_Id) throws Exception{
		return evoteDAO.listCount(c_Id);
	}
	
	//투표 조회
	@Override
	public Map<String, Object> voteRead(Map<String, Object> v_Num) {
		return evoteDAO.voteRead(v_Num);
		
	}
	//투표내용 조회
	@Override
	public List<Map> votedRead(String v_Num) {
		
		return evoteDAO.votedRead(v_Num);
		
	}
	
	//투표 카운팅
	@Override
	public List<Map> voteCount(Map<String, Object> voteCount) throws Exception {
		List<Map>result = evoteDAO.voteCount(voteCount);
		return result;
	}
	
	//투표 토탈
	@Override
	public List<Map> voteTotal (Map<String, Object> voteCount) throws Exception {
		List<Map>result = evoteDAO.voteTotal(voteCount);
		
		return result;
				
	}
		
		

	//글 삭제
	@Override
	public void voteDelete(String v_Num) throws Exception {
		evoteDAO.voteDelete(v_Num);
	}
	
	//글 삭제 시 투표 보기 삭제
	@Override
	public void votedDelete(String v_Num) throws Exception {
		evoteDAO.voteDelete(v_Num);
	}
	
	//글 삭제 시 댓글 삭제
	@Override
	public void votereplyDelete(String v_Num) throws Exception {
		evoteDAO.votereplyDelete(v_Num);
	}

	//글 수정
	@Override
	public void voteUpdate(Map<String, Object> cmap) throws Exception {
		evoteDAO.voteUpdate(cmap);
	}
	
	//이슈그룹 조회
	@Override
	public List<Map> igRead() {
		return evoteDAO.igRead();
		
	}
	
	
	//협업공간 내 멤버 조회
	@Override
	public List<Map> comemRead(String c_id) {
		return evoteDAO.comemRead(c_id);
		
	}
	
	//이슈 담당자 삽입
	@Override
	public void comemInsert(Map<String, Object> dataMap) throws Exception {
		// TODO Auto-generated method stub
		evoteDAO.comemInsert(dataMap);
	}
	
	//협업공간 내 멤버 조회
	@Override
	public List<Map> chargerRead(String v_Num) {
		return evoteDAO.chargerRead(v_Num);
		
	}
	
	//이슈 담당자 수정(삭제 후 다시 삽입)
	@Override
	public void chargerDelete(String v_Num) throws Exception {
		evoteDAO.chargerDelete(v_Num);
	}
	@Override
	public Map<String, Object> voteInfo(Map<String, Object> voteVO) throws Exception {
		Map<String, Object> voteInfo = evoteDAO.voteInfo(voteVO);
		return voteInfo;
	}
	//투표자 입력
	@Override
	public void voterInsert(Map map,HttpServletResponse response,VoteVO voteVO) throws Exception{
		response.setContentType("text/html;charset=utf-8");
			// 아이디 중복을확인하여 1이면 투표를 못하게 막는다.
			if (evoteDAO.checkId(map) == 1) {
				System.out.println("동일한 아이디");
				PrintWriter out = response.getWriter();
				response.setCharacterEncoding("utf-8");
				out.println("<script type=\"text/javascript\">");
				out.println("alert('이미 투표를 하셨습니다..');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.close();
		}else {
			
			 	evoteDAO.voterInsert(map);
		}
		
		
	}
	@Override
	public Map<String, Object> countCowork(Map<String, Object> searchC_Id) throws Exception {
		 
		return evoteDAO.countCowork(searchC_Id);
	}
	
	
	@Override
	public void votedUpdate(Map<String, Object> cmap) throws Exception {
		evoteDAO.votedUpdate(cmap);
		
	}
	//업데이트vs_Num
	@Override
	public void updateVs_Num(Map<String,Object> searchMap) {
		evoteDAO.updateVs_Num(searchMap);
	}
	//c_Id
	@Override
	public Map<String, Object> voteReadList(Map<String, Object> searchMap) {
		return evoteDAO.voteReadList(searchMap);
	}
	

	
	



	
	
	//협업공간 조회
	//@Override
//	public List<Map> coRead(String mem_id) {
//		return evoteDAO.coRead(mem_id);
//		
//	}
	

}
