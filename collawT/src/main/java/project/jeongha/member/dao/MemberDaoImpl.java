package project.jeongha.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.jeongha.member.vo.MemberVO;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private SqlSession sqlSession; // 맵퍼에 접근하기위한 SQL세션

	// 회원가입
	@Override
	public int memberJoin(Map<String, Object> memberVO) {
		// TODO Auto-generated method stub
		int stat = sqlSession.update("member.memberJoin", memberVO); // 맵퍼실행문 sqlSession.update
		// 을 통해 데이터 변경 및 삽입 삭제은 update를 사용 sample.insertMember는
		// 맵퍼 키값입니다 해당하는 sql문 실행 밑 데이터를 가져옵니다
		// sqlSession.selectList("맵퍼키값",데이터)는 해당하는
		// 데이터를 가져올떄 사용합니다.

		return stat;
	}

	//가입 인증키
	public int approvalMember(MemberVO member) throws Exception{
		int result =  sqlSession.update("member.approvalMember", member);
		return result;
	}
	
	//가입상태검사
	@Override
	public MemberVO memberStatus(Map<String, Object> mem_id) throws Exception {
		 MemberVO result = sqlSession.selectOne("member.checkMemStatus", mem_id);
		return result;
	}
	
	//naver 로그인 유저 데이터 저장
	@Override
	public int memberJoinApi(Map<String, Object> memberVO) {
		// TODO Auto-generated method stub
		int stat = sqlSession.update("member.memberJoinApiNaver", memberVO);
		return stat;
	}

	// 로그인.
	@Override
	public Map<String, Object> memberLogin(Map<String, Object> memLogin) {
		// TODO Auto-generated method stub
		Map<String, Object> memberVo = sqlSession.selectOne("member.login", memLogin);
		return memberVo;
	}

	// 개인정보 업데이트
	@Override
	public int memberUpdate(Map<String, Object> memberVO) {
		// TODO Auto-generated method stub
		int stat = sqlSession.update("member.updateMypage", memberVO);
		return stat;
	}

	// 로그인
	@Override
	public MemberVO login(Map<String, Object> mem_id) throws Exception {
		return sqlSession.selectOne("member.logina", mem_id);
	}

	@Override
	public MemberVO loginC(String mem_id) throws Exception {
		return sqlSession.selectOne("member.logina", mem_id);
	}

	@Override
	public MemberVO loginCh(Map<String, Object> member) throws Exception {
		return sqlSession.selectOne("member.loginb", member);
	}
	
	// 아이디 중복 검사
	public int checkId(String mem_id) throws Exception {
		int result = sqlSession.selectOne("member.checkId", mem_id);
		return result;
	}
	//
	public int checkIdMap(Map<String, Object> mem_id) throws Exception {
		int result = sqlSession.selectOne("member.checkIdMap", mem_id);
		return result;
	}

	// 비밀번호변경
	@Override
	public int update_pw(Map<String, Object> memberVO) throws Exception {
		return sqlSession.update("member.update_pw", memberVO);
	}
	
	@Override
	public int update_pw(MemberVO memberVO) throws Exception {
		return sqlSession.update("member.update_pw", memberVO);
	}

	// 회원탈퇴
	@Override
	public int memberDelete(Map<String, Object> memberVO) throws Exception {
		return sqlSession.delete("member.delete_member", memberVO);
	}

	// 프로필사진 저장
	@Override
	public void saveImage(Map<String, Object> hmap) throws Exception {
		sqlSession.update("member.saveImage", hmap);
	}

	@Override
	public Map<String, Object> getByteImage(String mem_Id) {
		return sqlSession.selectOne("member.getByteImage", mem_Id);
	}

	@Override
	public List<Map> searchList(Map<String, Object> searchMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("member.searchList", searchMap);
	}

	//google 로그인 유저 데이터 저장
	@Override
	public int memberJoinApiGoogle(Map<String, Object> memberVO) {
		int stat = sqlSession.update("member.memberJoinApiGoogle", memberVO);
		return stat;
	}

	@Override
	public int memberLoginNaver(Map<String, Object> memberVO) throws Exception {
		int result = sqlSession.update("member. ", memberVO);
		return result;
	}

	

}
