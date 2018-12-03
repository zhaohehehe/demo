package zhaohe.study.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;  
   
@Component  
public class CrossFilter implements Filter {  
	@Value("${service.cross.origin}")
    private String origin;
	@Value("${service.cross.credentials}")
	private String credentials;
	@Value("${service.cross.methods}")
	private String methods;
	@Value("${service.cross.headers}")
	private String headers;
	@Value("${service.cross.maxage}")
	private String maxage;
    final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CrossFilter.class);  
 
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {  
        HttpServletResponse response = (HttpServletResponse) res;  
        HttpServletRequest request= (HttpServletRequest) req;  

        response.setHeader("Access-Control-Allow-Origin",  request.getHeader("Origin")==null?origin:request.getHeader("Origin"));  
        response.setHeader("Access-Control-Allow-Credentials", credentials);
        response.setHeader("Access-Control-Allow-Methods", methods);  
        response.setHeader("Access-Control-Max-Age", maxage);  
        response.setHeader("Access-Control-Allow-Headers", headers);  
        System.out.println("*********************************过滤器被使用**************************");  
        chain.doFilter(req, res);  
    }  
    public void init(FilterConfig filterConfig) {}  
    public void destroy() {}  
}  
