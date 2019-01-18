
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;
  
/** 
 * 基于Ant的Zip压缩工具类 
 *  
 * @author 陈峰 
 */  
public class AntZipUtils {  
  
    public static final String ENCODING_DEFAULT = "UTF-8";  
  
    public static final int BUFFER_SIZE_DIFAULT = 1024;  
  
    public static void makeZip(String inFilePaths, String zipPath)  
            throws Exception {  
        makeZip(inFilePaths, zipPath, ENCODING_DEFAULT);  
    }  
  
    public static void makeZip(String inFilePaths, String zipPath,  
            String encoding) throws Exception {  
        File inFiles = new File(inFilePaths);  
        makeZip(inFiles, zipPath, encoding);  
    }  
  
    public static void makeZip(File inFiles, String zipPath) throws Exception {  
        makeZip(inFiles, zipPath, ENCODING_DEFAULT);  
    }  
  
    public static void makeZip(File inFiles, String zipPath, String encoding)  
            throws Exception {  
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipPath));  
        if(!inFiles.exists()){
        	throw new FileNotFoundException("在制定路径下未找到需要压缩的文件！");
        }
        zipOut.setEncoding(encoding);  
        doZipFile(zipOut, inFiles, inFiles.getParent());  
        zipOut.flush();  
        zipOut.close();  
    }  
  
    private static void doZipFile(ZipOutputStream zipOut, File file,  
            String dirPath) throws FileNotFoundException, IOException {  
        if (file.isFile()) {  
            BufferedInputStream bis = new BufferedInputStream(  
                    new FileInputStream(file));  
            String zipName = file.getPath().substring(dirPath.length());  
            while (zipName.charAt(0) == '\\' || zipName.charAt(0) == '/') {  
                zipName = zipName.substring(1);  
            }  
            ZipEntry entry = new ZipEntry(zipName);  
            zipOut.putNextEntry(entry);  
            byte[] buff = new byte[BUFFER_SIZE_DIFAULT];  
            int size;  
            while ((size = bis.read(buff, 0, buff.length)) != -1) {  
                zipOut.write(buff, 0, size);  
            }  
            zipOut.closeEntry();  
            bis.close();  
        } else {  
            File[] subFiles = file.listFiles();  
            for (File subFile : subFiles) {  
                doZipFile(zipOut, subFile, dirPath);  
            }  
        }  
    }  
    /** 
     * 解压缩 
     *  
     * @param zipFilePath 
     *            压缩包路径 
     * @param fileSavePath 
     *            解压路径 
     * @param isDelete 
     *            是否删除源文件 
     * @throws Exception 
     */  
    public void unZip(String zipFilePath, String fileSavePath, boolean isDelete) throws Exception {  
        try {  
            (new File(fileSavePath)).mkdirs();  
            File f = new File(zipFilePath);  
            if ((!f.exists()) && (f.length() <= 0)) {  
                throw new Exception("要解压的文件不存在!");  
            }  
            ZipFile zipFile = new ZipFile(f);  
            String strPath, gbkPath, strtemp;  
            File tempFile = new File(fileSavePath);// 从当前目录开始  
            strPath = tempFile.getAbsolutePath();// 输出的绝对位置  
            Enumeration<ZipEntry> e = zipFile.getEntries();  
            while (e.hasMoreElements()) {  
                org.apache.tools.zip.ZipEntry zipEnt = e.nextElement();  
                gbkPath = zipEnt.getName();  
                if (zipEnt.isDirectory()) {  
                    strtemp = strPath + File.separator + gbkPath;  
                    File dir = new File(strtemp);  
                    dir.mkdirs();  
                    continue;  
                } else {  
                    // 读写文件  
                    InputStream is = zipFile.getInputStream(zipEnt);  
                    BufferedInputStream bis = new BufferedInputStream(is);  
                    gbkPath = zipEnt.getName();  
                    strtemp = strPath + File.separator + gbkPath;  
                    // 建目录  
                    String strsubdir = gbkPath;  
                    for (int i = 0; i < strsubdir.length(); i++) {  
                        if (strsubdir.substring(i, i + 1).equalsIgnoreCase("/")) {  
                            String temp = strPath + File.separator + strsubdir.substring(0, i);  
                            File subdir = new File(temp);  
                            if (!subdir.exists())  
                                subdir.mkdir();  
                        }  
                    }  
                    FileOutputStream fos = new FileOutputStream(strtemp);  
                    BufferedOutputStream bos = new BufferedOutputStream(fos);  
                    int len;  
                    byte[] buff = new byte[BUFFER_SIZE_DIFAULT];  
                    while ((len = bis.read(buff)) != -1) {  
                        bos.write(buff, 0, len);  
                    }  
                    bos.close();  
                    fos.close();  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
            throw e;  
        }  
        if (isDelete) {  
            new File(zipFilePath).delete();  
        }  
    }  
  
    public static void main(String[] args) throws Exception {  
    	String path = "";
        String rootDir = ";  
        String zipPath = ;  
        // File[] inFiles = new File(rootDir).listFiles();  
        // makeZip(inFiles, zipPath);  
        makeZip( path, path+"b.zip");  
        File file = new File(path);
        System.out.println(file.getParent());
        //unZip(zipPath, "D:\\chenfeng_zip");  
    }  
}
