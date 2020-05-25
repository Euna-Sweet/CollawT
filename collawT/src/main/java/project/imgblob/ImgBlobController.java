package project.imgblob;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ImgBlobController {
	@Autowired
	private ImgBlobDao dao;

	/**
	 * 이미지 저장위한 Form Tag
	 * @return
	 */
	@RequestMapping(value = "/formFile")
	public String formFile() {
		System.out.println("이미지 저장 ");
		return "imgblob/formFile";
	}

	/**
	 * 이미지 저장처리
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/saveImage")
	public String saveImage(ImgBlobVO vo) {
		//이미지 저장 컨트롤러
		try {
			Map<String, Object> hmap = new HashMap<String, Object>();
			
			
			hmap.put("img", vo.getImgFile().getBytes());
			dao.saveImage(hmap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:view";
	}

	/**
	 * 이미지 조회위한 Form Tag
	 * 
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view() {
		return "imgblob/view";
	}

	/**
	 * 이미지태그의 src 컨트롤러
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getByteImage")
	public ResponseEntity<byte[]> getByteImage() {
		System.out.println("그림파일 가져오기");
		System.out.println(dao.getByteImage());
		Map<String, Object> map = dao.getByteImage();
		byte[] imageContent = (byte[]) map.get("img");
		System.out.println("getImg: "+map.get("img"));
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	}
}
