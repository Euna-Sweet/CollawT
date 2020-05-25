package project.euna.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import project.euna.issue.vo.IssueVO;
import project.euna.manager.dao.QuestionManagerDAO;


@Service
@Transactional(propagation = Propagation.REQUIRED)
public class QuestionManagerServiceImpl implements QuestionManagerService {
	@Autowired
	private QuestionManagerDAO managerDAO;

	@Override
	public List<Map> searchList(Map<String, Object> searchMap) throws DataAccessException {
		List<Map> list =  managerDAO.searchList(searchMap); 
		return list;
	}

	@Override
	public void saveData(Map<String, String[]> dataMap)  throws DataAccessException  {
		String[] status = dataMap.get("STATUS");
		int length = status.length; // row��
		int i = 0;
		
		for(String str : status) {
			Map<String, String> row = getRow(dataMap, length, i); // ���� Index�� Row Map
			if("I".equals(str)) { // �߰�
				managerDAO.insertData(row);
			}else if("U".equals(str)) { // ����
				managerDAO.updateData(row);
			}else if("D".equals(str)) { // ����
				managerDAO.deleteData(row);
			}
			i++;
		}
	}
	
	private Map getRow(Map<String, String[]> dataMap, int length, int index) {
		Map<String, String> row = new HashMap<String, String>();
		for(String name : dataMap.keySet()) {
			String[] data = dataMap.get(name);
			if(length == data.length) {
				row.put(name, data[index]);
			}
		}		
		return row;
	}
	
	@Override
	public Map<String, Object> questionRead(String q_Num) throws DataAccessException {
		Map<String, Object> list =  managerDAO.questionRead(q_Num); 
		return list;
	}
	
	//답변 등록
	@Override
	public void  answerInsert(Map map) {
		int result = managerDAO.answerInsert(map);
		
	}
	
	//상태 수정
	@Override
	public void questionStatusUpdate(Map map) throws Exception {
		managerDAO.questionStatusUpdate(map);
	}
	
	//이전 답변 내역
	@Override
	public List<Map> beforeAnswerList(String q_Num) throws DataAccessException {
		List<Map> list =  managerDAO.beforeAnswerList(q_Num); 
		return list;
	}
}
