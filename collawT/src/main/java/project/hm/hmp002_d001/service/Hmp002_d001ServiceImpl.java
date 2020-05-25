package project.hm.hmp002_d001.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import project.hm.hmp002_d001.dao.Hmp002_d001DAO;
import project.hm.hmp002_d001.vo.Hmp002_d001VO;

@Service("hmp002_d001Service")
@Transactional(propagation = Propagation.REQUIRED)
public class Hmp002_d001ServiceImpl implements Hmp002_d001Service {
	@Autowired
	private Hmp002_d001DAO hmp002_d001DAO;

	@Override
	public List<Hmp002_d001VO> searchList(Map<String, Object> searchMap) throws DataAccessException {
		List<Hmp002_d001VO> list =  hmp002_d001DAO.searchList(searchMap); 
		return list;
	}

	@Override
	public void saveData(Map<String, String[]> dataMap)  throws DataAccessException  {
		String[] status = dataMap.get("STATUS");
		int length = status.length; // row수
		int i = 0;
		
		for(String str : status) {
			Map<String, String> row = getRow(dataMap, length, i); // 현재 Index의 Row Map
			if("I".equals(str)) { // 추가
				hmp002_d001DAO.insertData(row);
				System.out.println("---------------------->>"+row.get("key_id")); // selectKey로 가져온 값을 리턴받을 수 있다
			}else if("U".equals(str)) { // 수정
				hmp002_d001DAO.updateData(row);
			}else if("D".equals(str)) { // 삭제
				hmp002_d001DAO.deleteData(row);
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
