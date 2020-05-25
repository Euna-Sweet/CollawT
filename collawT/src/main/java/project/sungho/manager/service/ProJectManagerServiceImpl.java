package project.sungho.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import project.sungho.manager.dao.ProJectManagerDAO;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ProJectManagerServiceImpl implements ProJectManagerService {
	@Autowired
	private ProJectManagerDAO pjtmanagerDAO;

	@Override
	public List<Map> searchList(Map<String, Object> searchMap) throws DataAccessException {
		List<Map> list =  pjtmanagerDAO.searchList(searchMap); 
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
				pjtmanagerDAO.insertData(row);
			}else if("U".equals(str)) { // ����
				pjtmanagerDAO.updateData(row);
			}else if("D".equals(str)) { // ����
				pjtmanagerDAO.deleteData(row);
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
