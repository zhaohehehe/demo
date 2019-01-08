package test.design_pattern.proxy_pattern;

public class RealSubject extends Subject{


	@Override
	public void request() {
		System.out.println("Real Subjct Request!");
	}

}
