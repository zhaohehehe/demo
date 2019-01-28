package zhaohe.study;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyRemote extends Remote{
	/**
	 * Stub底层用到了网络和I/O，所以最好声明异常
	 * @return 确定返回类型是primitive类型或者可序列化类型，因为远程方法的变量必须打包通过网络运送，需要靠序列化完成。
	 * @throws RemoteException
	 */
	public String sayHello() throws RemoteException;

}
