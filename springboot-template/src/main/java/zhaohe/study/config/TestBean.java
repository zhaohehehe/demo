package zhaohe.study.config;

public class TestBean {
	private String name;

	public TestBean() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String hello() {
		return "hello " + name;
	}

}
