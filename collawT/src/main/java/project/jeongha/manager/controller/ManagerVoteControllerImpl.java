package project.jeongha.manager.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import project.jeongha.manager.service.ManagerVoteService;

@Controller
@RequestMapping("/manager/vote/*")
public class ManagerVoteControllerImpl implements ManagerVoteController {

	@Autowired
	ManagerVoteService managerVoteService;
	
	@Override
	@RequestMapping(value = "/main", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView searchInit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView("/manager/voteInfo");
		return mav;
	}

	@Override
	@RequestMapping(value = "/searchList", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map searchList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String, Object> searchMap = new HashMap<String, Object>(); // 검색조건
		Map<String, Object> resultMap = new HashMap<String, Object>(); // 조회결과
		
		//http://localhost:8090/project/vote/read?c_Id=281&v_Num=1081
		
		// 검색조건설정
		searchMap.put("mem_Id", request.getParameter("mem_Id"));
		searchMap.put("mem_Name", request.getParameter("mem_Name"));
		searchMap.put("v_Content", request.getParameter("v_Content"));
		//데이터 조회
		List<Map> data = managerVoteService.searchList(searchMap);
        
        for (int i = 0; i < data.size(); i++) {
              data.get(i).put("link", "바로가기");
		}
        System.out.println("조회결과"+resultMap);
        
        resultMap.put("Data", data);
        return resultMap;
	}

	@Override
	@RequestMapping(value = "/saveData", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map saveData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String, String[]> dataMap = new HashMap<String, String[]>(); // 저장할Daa
		Map<String, Object> resultMap = new HashMap<String, Object>(); // 처리결과
		
		// 저장 Data 추출하기
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String[] values = request.getParameterValues(name);
			dataMap.put(name, values);
		}
		
		System.out.println(" wwwwwww"+dataMap);
		Map<String, String> result = new HashMap<String, String>();
		try {
			managerVoteService.saveData(dataMap);	
			result.put("Code","0");
			result.put("Message","저장되었습니다");
		}catch(Exception e) {
			result.put("Code","-1");
			result.put("Message","저장에 실패하였습니다");
			e.printStackTrace();
		}
		
		resultMap.put("Result", result);         
        return resultMap;
	}

}
