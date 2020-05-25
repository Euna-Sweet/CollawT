package project.euna.personal.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.euna.personal.dao.PersonalDAOImpl;
import project.euna.personal.vo.Criteria;
import project.euna.personal.vo.PersonalVO;
import project.euna.reply.vo.ReplyVO;
import project.notify.dao.NotifyDAOImpl;


@Service
public class PersonalServiceImpl implements PersonalService {
	
	@Autowired
	PersonalDAOImpl personalDAO;
	
	@Inject
	NotifyDAOImpl notifyDAO;
	
	//
	
	//글 등록
	@Override
	public void  personalInsert(Map map) {
		int result = personalDAO.personalInsert(map);
		//notifyDAO.notifyInsert(map);
		
	}
	
	
	
	//목록 조회 페이징
	@Override
	public List<Map> searchList(Criteria cri) throws Exception{
		List<Map> list = personalDAO.searchList(cri);
		return list;
	}
	
	//게시물 총 갯수
	@Override
	public int listCount(String mem_Id) throws Exception{
		return personalDAO.listCount(mem_Id);
	}
	
	//게시글 조회
	@Override
	public Map<String, Object> personalRead(String p_Num) {
		return personalDAO.personalRead(p_Num);
		
	}

	//글 삭제
	@Override
	public void personalDelete(String p_Num) throws Exception {
		personalDAO.personalDelete(p_Num);
	}
	
	//글 삭제 시 메모 삭제
	@Override
	public void personalmemoDelete(String p_Num) throws Exception {
		personalDAO.personalmemoDelete(p_Num);
	}

	//글 수정
	@Override
	public void personalUpdate(PersonalVO personalVO) throws Exception {
		personalDAO.personalUpdate(personalVO);
	}
	
	//이슈그룹 조회
	@Override
	public List<Map> igRead() {
		return personalDAO.igRead();
		
	}
	
	
	//협업공간 내 멤버 조회
	@Override
	public List<Map> comemRead(String mem_Id) {
		return personalDAO.comemRead(mem_Id);
		
	}
	
	//이슈 담당자 삽입
	@Override
	public void comemInsert(Map<String, Object> dataMap) throws Exception {
		// TODO Auto-generated method stub
		personalDAO.comemInsert(dataMap);
	}
	
	//협업공간 내 멤버 조회
	@Override
	public List<Map> chargerRead(String p_Num) {
		return personalDAO.chargerRead(p_Num);
		
	}
	
	//이슈 담당자 수정(삭제 후 다시 삽입)
	@Override
	public void chargerDelete(String p_Num) throws Exception {
		personalDAO.chargerDelete(p_Num);
	}
	
	
	//협업공간 조회
	//@Override
	public List<Map> coRead(String mem_id) {
		return personalDAO.coRead(mem_id);
		
	}
	
	//다른 협업공간에서 복사
	@Override
	public void  personalCopy(Map map) {
		int result = personalDAO.personalCopy(map);
		
	}
	
	//글 목록 조회 시  이슈 담당자 화면에 뿌리기 용
	@Override
	public List<Map> chargerList(String mem_Id) {
		return personalDAO.chargerList(mem_Id);
	}
	
	//내공간 알림
	@Override
	public List<Map> newspeed(String mem_Id) {
		return personalDAO.newspeed(mem_Id);
	}
	
	//참여 가능한 투표
	@Override
	public List<Map> voteAvailable(String mem_Id) {
		return personalDAO.voteAvailable(mem_Id);
	}
	
	//내가 담당한 이슈 현황
	@Override
	public List<Map> myissue(String mem_Id) {
		return personalDAO.myissue(mem_Id);
	}
	

}
