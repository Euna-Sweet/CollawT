package project.jeongha.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import project.jeongha.manager.dao.ManagerVoteDao;

@Service
public class ManagerVoteServiceImpl implements ManagerVoteService {

	@Autowired
	private ManagerVoteDao managerVoteDao;
	
	
	@Override
	public List<Map> searchList(Map<String, Object> searchMap) throws DataAccessException {
		List<Map> list =  managerVoteDao.searchList(searchMap); 
		return list;
	}

	@Override
	public void saveData(Map<String, String[]> dataMap) throws DataAccessException {
		String[] status = dataMap.get("STATUS");
		System.out.println("ssssssssss"+dataMap);
		int length = status.length; // row��
		int i = 0;
		
		for(String str : status) {
			Map<String, String> row = getRow(dataMap, length, i); // ���� Index�� Row Map
			if("I".equals(str)) { // �߰�
				managerVoteDao.insertData(row);
			}else if("U".equals(str)) { // ����
				managerVoteDao.updateData(row);
			}else if("D".equals(str)) { // ����
				managerVoteDao.deleteData(row);
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
}


