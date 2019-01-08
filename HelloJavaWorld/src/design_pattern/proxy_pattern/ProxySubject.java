package test.design_pattern.proxy_pattern;

public class ProxySubject extends Subject {

	private RealSubject realSubject;
	/**
	 * 真实角色功能
	 */
	@Override
	public void request() {
		
		this.preRequest();//可省

		if(null==realSubject){
			realSubject=new RealSubject();
		}
		realSubject.request();
		
		this.postRequest();//可省
	}
	/**
	 * 在真实角色操作之前，代理角色附加功能
	 */
	private void preRequest(){
		System.out.println("ProxySubject Pre Request!");
	}
	/**
	 * 在真实角色操作之后，代理角色附加功能
	 */
	private void postRequest(){
		System.out.println("ProxySubject Post Request!");
	}

}
