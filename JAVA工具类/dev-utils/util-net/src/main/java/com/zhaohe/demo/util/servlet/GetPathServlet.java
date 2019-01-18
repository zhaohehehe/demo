package com.zhaohe.demo.util.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhaohe.demo.util.GetWebPathUtil;

/**
 * Servlet implementation class GetPathServlet
 */
@WebServlet("/getPathServlet")
public class GetPathServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPathServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());// /util-net
		System.out.println(this.getClass().getResource("/").getPath());
		///D:/zhaohe/workspace/workspace_JavaW/zhaohe/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/util-net/WEB-INF/classes/
		GetWebPathUtil.getPathByServletContext(request.getServletContext(), "/WEB-INF");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
