

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class FileUtils {

	private FileUtils(){}
	
	public static void downloadFile(HttpServletResponse response, String fileName, File file){
		try {
			InputStream fis = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			response.reset();
			if(fileName.endsWith("xls")){
				response.setContentType("application/vnd.ms-excel");
			}else{
				response.setContentType("multipart/form-data");
			}
			response.setHeader("Content-Disposition", "attachment;Filename="+new String(fileName.getBytes("gbk"),"iso-8859-1"));
			OutputStream out = response.getOutputStream();
			OutputStream toClient = new BufferedOutputStream(out);
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			out.close();
			toClient.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
