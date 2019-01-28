package zhaohe.study;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote{

	public static void main(String[] args) {
		MyRemote service;
		try {
			service = new MyRemoteImpl();
			//产生远程对象，再使用Naming.rebind()绑定到rmiresigistry。客户将使用你所注册的名称在RMI registry中找到它。
			LocateRegistry.createRegistry(5008);
			Naming.rebind("rmi://127.0.0.1:5008/myRemoteService", service);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected MyRemoteImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String sayHello() throws RemoteException {
		// TODO Auto-generated method stub
		return "Hello";
	}

}
