package project.imgblob;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ImgBlobDao {
	@Autowired
	private SqlSession query;

	public void saveImage(Map<String, Object> hmap) {
		query.insert("imgblob.saveImage",hmap);
	}

	public Map<String, Object> getByteImage() {
		return query.selectOne("imgblob.getByteImage");
	}

}
