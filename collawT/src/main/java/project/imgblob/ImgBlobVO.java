package project.imgblob;

import org.springframework.web.multipart.MultipartFile;

public class ImgBlobVO {
    private MultipartFile imgFile;
    
    public MultipartFile getImgFile() {
        return imgFile;
    }
 
    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }
}
