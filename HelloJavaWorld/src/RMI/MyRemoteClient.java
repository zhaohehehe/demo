package zhaohe.study;

import java.rmi.Naming;

public class MyRemoteClient {
	public static void main(String[] args) {
		new MyRemoteClient().go();
	}
	public void go(){
		try {
			MyRemote service = (MyRemote) Naming.lookup("rmi://127.0.0.1:5008/myRemoteService");
			String s = service.sayHello();
			System.out.println("service:"+s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}
}
