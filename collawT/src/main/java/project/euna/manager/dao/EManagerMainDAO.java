package project.euna.manager.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface EManagerMainDAO {

	public List<Map> main() throws DataAccessException;
}
