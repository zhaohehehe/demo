
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.Vector;
import java.util.zip.ZipInputStream;

import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.yahoo.platform.yui.compressor.YUICompressor;

public class FileUtil {

	private String fPath;

	private String tPath;

	Logger log = LogManager.getLogger(FileUtil.class);
	public static String fileSeperator = File.separator;

	public static void createDir(String dir) {
		File outputDirFile = new File(dir);
		if (!outputDirFile.exists())
			outputDirFile.mkdirs();
	}

	public static boolean exists(String filePath) {
		File filePathFile = new File(filePath);
		return filePathFile.exists();
	}

	public File getFileByPath(String filePath) {
		File tempFile = new File(filePath);
		return tempFile;

	}
	public static boolean compareFileMD5(String fileName,String toFileName){

		return getFileMD5(new File(fileName)).equals(getFileMD5(new File(toFileName)));
	}
	public static boolean compareFileMD5(File file,File toFile){

		return getFileMD5(file).equals(getFileMD5(toFile));
	}
	/**
	 * 获取单个文件的MD5值！
	 * @param file
	 * @return
	 */
	public static String getFileMD5(File file) {
		if (!file.isFile()){
			return "";
		}
		MessageDigest digest = null;
		FileInputStream in=null;
		byte buffer[] = new byte[10240];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 10240)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());
		return bigInt.toString(16);
	}
	/**
	 * 获取以/分割的父路径
	 * @param path
	 * @return
	 */
	public static String getParent(String path){
		
		File file = new File(path);
		String parent = file.getParent();
		if(parent!=null){
			parent.replace("\\", "/");
		}
		return parent;
	}
	public ZipInputStream getZipInputStreamByPath(String path) {
		try {
			return new ZipInputStream(new FileInputStream(path));
		} catch (Exception e) {
			log.error(e);
			return null;
		}

	}

	public String getFileBeafs(String file) {
		String s, s2 = new String();
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			while ((s = in.readLine()) != null)
				s2 += s + "\n";
			in.close();
			s2 = new String(s2.getBytes(), "UTF-8");
		} catch (Exception e) {
			log.error(e.getMessage());

		}

		return s2;

	}
	public void setDataSource(DataSource dataSource) {
	}


  
	public void copyFile(String oFile, String tFile) {
//		FileUtils fu = FileUtils.getFileUtils();
		try {
			copyFilebase(this.fPath + oFile, this.tPath + File.separator
					+ tFile);

		} catch (Exception e) {
		}

	}

	public static void copyFileByFullPath(String oFile, String tFile) {
//		FileUtils fu = FileUtils.getFileUtils();
		FileUtil fu = new FileUtil();
		try {
			fu.copyFilebase(oFile, tFile);

		} catch (Exception e) {
		}

	}
	/**
	 * 删除文件
	 * @param path
	 * @return
	 */
	public static Boolean deteFile(String path){
		
		File file = new File(path);
		if(file.exists()){
			file.delete();
		}
		return true;
	}
	public void copyFileByFullPath(File oFile, String tFile) {
//		FileUtils fu = FileUtils.getFileUtils();
		FileUtil fu = new FileUtil();
		try {
			String oFileStr = oFile.getAbsolutePath() + File.separator
					+ oFile.getName();

			fu.copyFilebase(oFileStr, tFile);

		} catch (Exception e) {
			log.error(e);
		}

	}

	public void copyFileByFullPath(File oFile, String tFilePath, String fileId) {
//		FileUtils fu = FileUtils.getFileUtils();
		try {
			String oFileStr = oFile.getAbsolutePath();
			File f = new File(tFilePath);
			f.mkdirs();
			copyFilebase(oFileStr, tFilePath + fileId);

		} catch (Exception e) {
		}

	}

	public static void copyFileByFullPath(String toPath, String fromFile,
			String a) {
//		FileUtils fu = FileUtils.getFileUtils();
		FileUtil fu = new FileUtil();
		try {
			fu.copyFilebase(fromFile, toPath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteDirsFiles(File f) {
		Vector dirList = new Vector();
		if (f.isDirectory()) {
			dirList.add(f);
			File[] fl = f.listFiles();
			for (int i = 0; i < fl.length; i++) {
				if (fl[i].isFile()) {
					fl[i].delete();
				} else {
					deleteDirsFiles(fl[i]);
				}
			}
		}
		for (int i = dirList.size(); i > 0; i--) {
			File tempF = (File) dirList.get(i - 1);
			tempF.delete();
		}
	}
	public void deleteDirsFiles(String dirs) {
		File f = new File(dirs);
		if (f.isDirectory()) {
			deleteDirsFiles(f);

		} else if (f.isFile()) {
			f.delete();
		}

	}

	public void replaceFileName(File f,String oldStr,String newStr) throws Exception{
		String fileName=f.getName();
		String fPath=f.getParent();
		if(fileName.indexOf(oldStr)>=0){
			f.renameTo(new File(fPath+File.separator+StringUtils.replace(fileName, oldStr, newStr)));

		}
	}
	public void replaceFilesName(File f,String oldStr,String newStr) throws Exception{

		if(f.isDirectory()){
			for(File cf:f.listFiles()){
				replaceFilesName(cf, oldStr, newStr);
			}
			replaceFileName(f, oldStr, newStr);
		}else{
			replaceFileName(f, oldStr, newStr);
		}
	}

	public static void copySingleFolder(File src, File dest) throws IOException {
		if (src.exists()) {
			if (src.isDirectory()) {
				if (!dest.exists()) {
					dest.mkdir();
				}
				String[] files = src.list();
				for (String file : files) {
					File srcFile = new File(src, file);
					File destFile = new File(dest, file);

					if (srcFile.isFile())
						copyFileByFullPath(srcFile.getAbsolutePath(), destFile.getAbsolutePath());
				}
			} else {
				copyFileByFullPath(src.getAbsolutePath(), dest.getAbsolutePath());
			}
		}
		else System.out.println("文件：" + src.getAbsolutePath() + " 不存在"); 
	}

	public static void copySingleFolder(String srcStr, String destStr)
	{
		try { copySingleFolder(new File(srcStr), new File(destStr));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void copyFolder(File src, File dest) throws IOException { if (src.exists()) {
		if (src.isDirectory()) {
			if (!dest.exists()) {
				dest.mkdir();
			}
			String[] files = src.list();
			for (String file : files) {
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);

				copyFolder(srcFile, destFile);
			}
		} else {
			copyFileByFullPath(src.getAbsolutePath(), dest.getAbsolutePath());
		}
	}
	else System.out.println("文件：" + src.getAbsolutePath() + " 不存在");  } 
	public static void copyFolder(String srcStr, String destStr)
	{
		try
		{
			copyFolder(new File(srcStr), new File(destStr));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static boolean fileExists(String rootPath, String packageName,
			String fileName) {
		String path = StringUtils.replace(packageName, ".", File.separator);
		File f = new File(rootPath + path + File.separator + fileName);
		return f.isFile();
	}
	public static boolean fileExists(String path,
			String fileName) {
		File f = new File(path +  File.separator + fileName);
		return f.isFile();
	}

	public String getFPath() {
		return fPath;
	}

	public void setFPath(String path) {
		fPath = path;
	}

	public String getTPath() {
		return tPath;
	}

	public void setTPath(String path) {
		tPath = path;
	}
    
	public  void copyFilebase(String sourceFile,String destFile) throws Exception{
		copyFileBase(new File(sourceFile),new File(destFile));
	} 
	public void copyFileBase(File sourceFile,File destFile) throws Exception{
			copyFile(sourceFile,destFile);
			
	}
	public void copyFile(File sourceFile, File destFile )
			throws IOException
	{
		if ((!destFile.exists()) || (destFile.lastModified() < sourceFile.lastModified()))
		{
			if ((destFile.exists()) && (destFile.isFile())) {
				destFile.delete();
			}
			File parent = destFile.getParentFile();
			if ((parent != null) && (!parent.exists())) {
				parent.mkdirs();
			}
			  FileInputStream in = null;
		        FileOutputStream out = null;
		        try {
		          in = new FileInputStream(sourceFile);
		          out = new FileOutputStream(destFile);
		          byte[] buffer = new byte[8192];
		          int count = 0;
		          do {
		            out.write(buffer, 0, count);
		            count = in.read(buffer, 0, buffer.length);
		          }while (count != -1);
		        } finally {
		          close(out);
		          close(in);
		        }
		}
	}

	

	public void close(OutputStream device)
	{
		if (device != null)
			try {
				device.close();
			}
		catch (IOException ioex)
		{
		}
	}

	public  void close(InputStream device)
	{
		if (device != null)
			try {
				device.close();
			}
		catch (IOException ioex)
		{
		}
	}

	 public static void close(Writer device)
	  {
	    if (device != null)
	      try {
	        device.close();
	      }
	      catch (IOException ioex)
	      {
	      }
	  }

	  public static void close(Reader device)
	  {
	    if (device != null)
	      try {
	        device.close();
	      }
	      catch (IOException ioex)
	      {
	      }
	  }

	
	public static void copyFileByFullPathYUI(String oFile, String tFile) {
		FileUtil fu = new FileUtil();
		try {
			fu.copyFile(oFile, tFile);
			File f=new File(tFile);
			if(f.length()>100)
			if(tFile.length()>5&&tFile.substring(tFile.length()-3).equals(".js")){
				YUICompressor.main(new String[]{tFile, "--charset", "UTF-8", "-o", tFile});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String writeFile(List<String> sqlAry){
		String filePath = null;
		FileOutputStream out = null;
		if(sqlAry != null && !sqlAry.isEmpty()){
			String classpath = getClass().getClassLoader().getResource("").getPath();
			File sqlFile = new File(classpath,"../../docs/repository/"+UUIDUtil.generateUUID()+".sql");
			try {
				filePath = sqlFile.getCanonicalPath();
				if (!sqlFile.getParentFile().exists()) {
					sqlFile.getParentFile().mkdirs();
				}
				out = new FileOutputStream(sqlFile);
				for(int i = 0,size = sqlAry.size();i < size ; i++){
					out.write((sqlAry.get(i)+"\r\n").getBytes());
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				try {
					if (out != null ) {
						out.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return filePath;
	}
	public String writeFile(String fileName,String paramXml){
		String filePath = null;
		FileOutputStream out = null;
		OutputStreamWriter osw  = null;
		if(paramXml != null && !"".equals(paramXml)){
			File sqlFile = new File(fileName);
			try {
				filePath = sqlFile.getCanonicalPath();
				if (!sqlFile.getParentFile().exists()) {
					sqlFile.getParentFile().mkdirs();
				}
				out = new FileOutputStream(sqlFile);
				osw  =   new  OutputStreamWriter(out, "UTF-8" );
		        osw.write(paramXml);
		        osw.flush(); 
				//out.write(paramXml.getBytes());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				try {
					if (out != null ) {
						out.close();
					}
					if (osw != null ) {
						osw.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return filePath;
	}
	
	public String readFileByLines(String fileName) {  
        File file = new File(fileName);  
        BufferedReader reader = null;  
        StringBuffer returnVal = new StringBuffer(1024);
        try {  
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));  
            String tempString = null;  
            int line = 1;  
            // 一次读入一行，直到读入null为文件结束  
            while ((tempString = reader.readLine()) != null) {  
                // 显示行号  
            	returnVal.append(tempString);  
                line++;  
            }  
            reader.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e1) {  
                }  
            }  
        }
        return returnVal.toString();
    }
	public String readFileByLines(File file) {    
        BufferedReader reader = null;  
        StringBuffer returnVal = new StringBuffer();
        try {  
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));  
            String tempString = null;  
            int line = 1;  
            // 一次读入一行，直到读入null为文件结束  
            while ((tempString = reader.readLine()) != null) {  
                // 显示行号  
            	returnVal.append(tempString);  
                line++;  
            }  
            reader.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e1) {  
                }  
            }  
        }
        return returnVal.toString();
    }
	public byte[] transStreamToByteArray(InputStream in){
		 ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
        byte[] buff = new byte[100];  
        int rc = 0;  
        try {
			while ((rc = in.read(buff, 0, 100)) > 0) {  
			     swapStream.write(buff, 0, rc);  
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        byte[] in2b = swapStream.toByteArray();
        return in2b;
	}
}
