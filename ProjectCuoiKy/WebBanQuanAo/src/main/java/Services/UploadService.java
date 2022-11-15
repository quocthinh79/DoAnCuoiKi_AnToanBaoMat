package Services;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

public class UploadService {
    public static String uploadFile(Part images, String readPath) throws IOException {
        try {
        	Part img = images;
            String fileName = Paths.get(img.getSubmittedFileName()).getFileName().toString();
            File folder = new File(readPath);
            if(!folder.exists()) {
                folder.mkdir();
            }
            InputStream inputStream = img.getInputStream();
            FileOutputStream fis = new FileOutputStream(readPath + "/" + fileName);
            byte[] data = new byte[1024];
            int byteRead = 0;
            while ((byteRead = inputStream.read(data, 0, data.length)) != -1) {
                fis.write(data, 0, byteRead);
            }
            fis.close();
            inputStream.close();
            return "/assets/imgs/product-imgs/"+fileName;
		} catch (NullPointerException e) {
			return "";
		}
    }
}
