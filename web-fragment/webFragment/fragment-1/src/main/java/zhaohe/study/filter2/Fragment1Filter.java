package zhaohe.study.filter2;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import zhaohe.study.filter.MyRootFilter;

public class Fragment1Filter extends MyRootFilter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		super.setModuleName("fragment-1");
		super.doFilter(request, response, chain);
	}

}
