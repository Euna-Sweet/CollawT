package project.sungho.comember.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.sungho.apply.service.ApplyService;
import project.sungho.comember.service.ComemberService;
import project.sungho.cowork.vo.CoworkVO;


@Controller
@RequestMapping("/pjtmember/*")
public class ComemberControllerImpl implements ComemberController {
	
	@Autowired
	ComemberService comemberService;
	
	@Autowired
	ApplyService applyService;
	
	//회원별 가입한 공간리스트를 불러오기위한 컨트롤러
	@Override
	public String searchList(Model model ,HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> searchMap = new HashMap<String, Object>();
		
		HttpSession session = request.getSession();
		
		String mem_Id = (String)session.getAttribute("mem_Id");
		
		searchMap.put("mem_Id", mem_Id);	
		
		List<Map> list = comemberService.searchList(searchMap); 
		
		System.out.println(list);
		
		model.addAttribute("coworklist", list); 
		
		return "cowork/list"; 
		
	}

	@Override
	public String updateComember(Model model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insertComember(CoworkVO coworkVO, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GetMapping("delete")
	public String deleteComember(Model model ,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String c_Id = request.getParameter("c_Id");
		String mem_Id = request.getParameter("mem_Id");
		System.out.println("=====================================");
		System.out.println(c_Id+","+ mem_Id);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("c_Id", c_Id);
		dataMap.put("mem_Id", mem_Id);
		comemberService.deleteComember(dataMap);
		applyService.deleteApply(dataMap);
		
		return "redirect:/main";
	}
	
	@Override
	@GetMapping("deleteMember")
	public String deleteMember(Model model ,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String c_Id = request.getParameter("c_Id");
		String mem_Id = request.getParameter("mem_Id");
		
		System.out.println("--------------------강퇴--------"+c_Id+mem_Id);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("c_Id", c_Id);
		dataMap.put("mem_Id", mem_Id);
		comemberService.deleteMember(dataMap);
		applyService.deleteApply(dataMap);
		
		return "redirect:/project/main?c_Id=" + c_Id;
	}

}
