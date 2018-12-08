package zhaohe.study.websocket.org;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyStartSocketServerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("启动状态： " + MyWebSocketServer.isStarted);
		
		
		String port = super.getServletContext().getInitParameter("socketPort");
		String socketIp = super.getServletContext().getInitParameter("socketIp");
		String appIp = InetAddress.getLocalHost().getHostAddress();
		if (socketIp == null || socketIp.length() <= 0) {
			socketIp = appIp;
		}
		if(!MyWebSocketServer.isStarted && isPortInUse(socketIp,port)){
			System.out.println("端口占用");
		}else{
			MyWebSocketServer.startMyWebSocketServerInstance(socketIp, port);
			MyWebSocketServer.clientBroswer = getBrowser(req.getHeader("user-agent"));
			MyWebSocketServer.clientIp = getClientIp();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		if(MyWebSocketServer.isStarted){
			out.write("success");
		}else{
			out.write("fail");
		}
        out.flush();
        out.close();  
	}
	public static boolean isPortInUse(String ip,String port) throws UnknownHostException{
		boolean flag = false;
		
		InetAddress theAddress = InetAddress.getByName(ip);
		try {
			Socket socket = new Socket(theAddress,Integer.parseInt(port));
			flag = true;
		} catch (IOException e) {
			
		} 
		return flag;
	}
	public static String getClientIp() {
		String tempIp = null;
		try {
			tempIp = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			System.err.println(e.getMessage());
			tempIp = "未知主机";

		}
		// 校验ip
		return tempIp;
	}

	public static String getBrowser(String userAgent) {
		String browser = null;
		String agent = userAgent.toLowerCase();
		if (agent.contains("firefox")) {
			browser = "Firefox";
		} else if (agent.contains("opera") || agent.contains("opr")) {
			browser = "Opera";
		} else if (agent.contains("chrome")) {
			browser = "Chrome";
		} else if (agent.contains("safari") && agent.contains("version")) {
			browser = "Safari";
		} else if (agent.contains("edge")) {
			browser = "Edge";
		} else if (agent.contains("msie")) {
			// Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Win64; x64;
			// Trident/6.0)
			String target = agent.split("msie")[1];
			if (target != null && !target.equals("")) {
				String version = target.substring(0, target.indexOf(";")).trim();
				browser = "IE " + version;
			} else {
				browser = "IE";
			}
		} else if (agent.contains("rv:")) {
			// Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like
			// Gecko
			String target = agent.split("rv:")[1];
			if (target != null && !target.equals("")) {
				String version = target.substring(0, target.indexOf(")")).trim();
				browser = "IE " + version;
			} else {
				browser = "IE";
			}
		} else {
			browser = "UnKnown, More-Info: " + userAgent;
		}
		return browser;
	}

}
