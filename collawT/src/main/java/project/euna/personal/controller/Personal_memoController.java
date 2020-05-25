package project.euna.personal.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import project.euna.personal.vo.Personal_memoVO;
import project.euna.reply.vo.ReplyVO;

public interface Personal_memoController {
	public String personal_memoInsert(Personal_memoVO personal_memoVO, HttpSession session) throws Exception;
	public List<Map> searchList(@RequestParam ("p_Num")String p_Num, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public void personal_memoDelete(String p_m_Num) throws Exception;

	public void personal_memoUpdate(Personal_memoVO personal_memoVO) throws Exception;
	}
