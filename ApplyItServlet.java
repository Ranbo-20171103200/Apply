package cn.edu.imnu.ApplyItServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.edu.imnu.Apply.Apply;
import cn.edu.imnu.UserDAO.UserDAO;

/**
 * Servlet implementation class ApplyItServlet
 */
@WebServlet("/ApplyItServlet")
public class ApplyItServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyItServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        //获取微信小程序get的参数值并打印
        String username = request.getParameter("username");
        String realname = request.getParameter("realname");
        String academy = request.getParameter("academy");
        String applyreason = request.getParameter("applyreason");
        String enddate = request.getParameter("enddate");
        UserDAO dao=new UserDAO();
        Apply apply=new Apply();
        apply.setUsername(username);
        apply.setRealname(realname);
        apply.setAcademy(academy);
        apply.setApplyreason(applyreason);
        apply.setEnddate(enddate);
        int userID=dao.isAllow(apply);        
        if(userID!=0)
        {
        	dao.applyinfo(apply);
        	PrintWriter out=response.getWriter();
            out.write(username);
            out.flush();
        }
	}

}
