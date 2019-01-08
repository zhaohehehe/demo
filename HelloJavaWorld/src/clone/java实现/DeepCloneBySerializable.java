package clone.java实现;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 * 实现serializable实现深clone，需要clone的对象必须实现Serializable接口
 * @author ZH
 *
 */
public abstract class DeepCloneBySerializable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unchecked")
	public static <T> T cloneTo(T src) throws RuntimeException  {
		ByteArrayOutputStream memoryBufer=new ByteArrayOutputStream();
		ObjectOutputStream out=null;
		ObjectInputStream in=null;
		T dist=null;
		try{
			//将对象输出到磁盘
			out=new ObjectOutputStream(memoryBufer);
			out.writeObject(src);
			out.flush();
			//再将拷贝的对象写入到内存
			in=new ObjectInputStream(new ByteArrayInputStream(memoryBufer.toByteArray()));
			dist=(T)in.readObject();
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			if(out!=null)
				try {
					out.close();
					out=null;
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			if(in!=null)
				try {
					in.close();
					in=null;
				} catch (Exception e3) {
					throw new RuntimeException(e3);
				}
		}
		return dist;
		}
	public static void main(String[] args) {
		DeepCloneUser src=new DeepCloneUser(new ShallowCloneUser("000","A"),"000","A");
		DeepCloneUser dist=DeepCloneBySerializable.cloneTo(src);
		
		System.out.println(src==dist);//F
		System.out.println(src.equals(dist));//T
		System.out.println(src.getUser()==dist.getUser());//F
		System.out.println(src.getUser().equals(dist.getUser()));//T

	}

}
