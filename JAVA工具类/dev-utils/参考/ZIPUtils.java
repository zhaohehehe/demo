
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;



public class ZIPUtils {

	public final static String encoding = "gbk";  
	private static int k = 1;
	private static ZIPUtils instance = new ZIPUtils();
	private ZIPUtils() {
	}
	public static ZIPUtils getInstance() {
		return instance;
	}

	/**
	 * 压缩文件或者文件目录到指定的zip或者rar包
	 * @param inputFilename 要压缩的文件或者文件夹，如果是文件夹的话，会将文件夹下的所有文件包含子文件夹的内容进行压缩
	 * @param zipFilename 生成的zip或者rar文件的名称
	 * @throws IOException
	 */
	public synchronized void zip(String inputFilename, String zipFilename)
			throws Exception {
		ZIPUtils.zip(zipFilename,  
				new File(inputFilename)); 
		//		zip(new File(inputFilename), zipFilename);
	}

	public static void main(String[] args) {
		try {
			//测试压缩文件 
			//			bean.zip("D:\\工作目录\\编码管理\\刘荣涛-编码管理测试数据\\10张码表的数据", "d:/测试.zip");
			//测试解压缩文件
			//bean.unzip("d:\\测试.zip", "d:");
			ZIPUtils bean = new ZIPUtils();
						bean.zip("E:\\test", "E:\\test2\\test.zip");
			//ZIPUtils.unzip("E:\\test\\test.zip", "E:\\test2");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除文件夹 文件夹必须存在
	 * @param file
	 * @return
	 */
	private boolean deleteDirectory(File file) throws Exception { 
	    boolean flag = true;  
	    //删除文件夹下的所有文件(包括子目录)  
	    File[] files = file.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //删除子文件  
	        if (files[i].isFile()) {  
	            flag = deleteFile(files[i]); 
	            if (!flag) break;  
	        } //删除子目录  
	        else {  
	            flag = deleteDirectory(files[i]); 
	            if (!flag) break;  
	        }  
	    }
	    if (!flag)
	    	return false;
	    //删除当前目录  
	    if (file.delete())
	        return true;  
	    else
	        return false;
	}  
	
	/**
	 * 删除文件 文件必须存在
	 * @param file
	 * @return
	 */
	private boolean deleteFile(File file) throws Exception {
		boolean flag = false;
		if (file.isFile() && file.exists()) {
			flag = file.delete();
		}
		return flag;
	}
	
	/**
     * 删除目录（文件夹）以及目录下的文件
     * @param   sPath 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
     */
    public boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }
    
	   /**
     * 删除单个文件
     * @param   sPath    被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
    
	/***
	 * 文件压缩
	 * @param zipFileName 需要压缩的路径或文件
	 * @param inputFile   压缩后的文件
	 * @throws Exception
	 */
	public static synchronized void zip(String zipFileName, File inputFile) throws Exception {  
		if (!inputFile.exists())
			return;
		File zip = new File(zipFileName);
		//isNewFile用于判断是否是文件压缩，当时文件压缩时压缩完毕后删除创建的临时文件夹
		if (!zip.isDirectory()) {
			//创建文件夹
			File file = new File(zip.getAbsolutePath().substring(0, zip.getAbsolutePath().lastIndexOf("\\")));
			file.mkdirs();

		}
		System.out.println("压缩中...");  
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(  
				zipFileName));  
		BufferedOutputStream bo = new BufferedOutputStream(out);  
		zip(out, inputFile, inputFile.getName(), bo);  
		bo.close();  
		out.close(); // 输出流关闭  
		System.out.println("压缩完成");  
	}  
	public static synchronized void zip(ZipOutputStream out, File f, String base,  
			BufferedOutputStream bo) throws Exception { // 方法重载  
		if (f.isDirectory()) {  
			File[] fl = f.listFiles();  
			if (fl.length == 0) {  
				out.putNextEntry(new ZipEntry(base + "/")); // 创建zip压缩进入点base  
				System.out.println(base + "/");  
			}  
			for (int i = 0; i < fl.length; i++) {  
				zip(out, fl[i], base + "/" + fl[i].getName(), bo); // 递归遍历子文件夹  
			}  
			System.out.println("第" + k + "次递归");  
			k++;  
		} else {  
			out.putNextEntry(new ZipEntry(base)); // 创建zip压缩进入点base  
			System.out.println(base);  
			FileInputStream in = new FileInputStream(f);  
			BufferedInputStream bi = new BufferedInputStream(in);  
			int b;  
			while ((b = bi.read()) != -1) {  
				bo.write(b); // 将字节流写入当前zip目录  
			}  
			bi.close();  
			in.close(); // 输入流关闭  
		}  
	} 
	
	/**
	 * 文件解压
	 * @param zipFileName  需要解压的文件
	 * @param inputFile    解压路径
	 */
	public static synchronized void unzip(String zipFileName, String inputFile){
		long startTime=System.currentTimeMillis();  
		try {  
			ZipInputStream Zin=new ZipInputStream(new FileInputStream(  
					zipFileName));//输入源zip路径  
			BufferedInputStream Bin=new BufferedInputStream(Zin);  
			String Parent=inputFile; //输出路径（文件夹目录）  
			File Fout=null;  
			ZipEntry entry;  
			try {  
				while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){  
					Fout=new File(Parent,entry.getName());  
					if(!Fout.exists()){  
						(new File(Fout.getParent())).mkdirs();  
					}  
					FileOutputStream out=new FileOutputStream(Fout);  
					BufferedOutputStream Bout=new BufferedOutputStream(out);  
					int b;  
					while((b=Bin.read())!=-1){  
						Bout.write(b);  
					}  
					Bout.close();  
					out.close();  
					System.out.println(Fout+"解压成功");      
				}  
				Bin.close();  
				Zin.close();  
			} catch (IOException e) {  
				// TODO Auto-generated catch block  
				e.printStackTrace();  
			}  
		} catch (FileNotFoundException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
		}  
		long endTime=System.currentTimeMillis();  
		System.out.println("耗费时间： "+(endTime-startTime)+" ms");
	}
}
