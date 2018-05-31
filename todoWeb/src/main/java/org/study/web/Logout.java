package org.study.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.study.dao.User;
import org.study.dao.UserDaoImpl;
import org.study.dao.UserService;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/Logout.do")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService service = new UserDaoImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userid") !=null) {
//			User user=service.getUser((String)session.getAttribute("userid"));
			session.invalidate();
			response.sendRedirect("/index.jsp");
//			request.getRequestDispatcher("/index.jsp");
		}else {
			request.setAttribute("error", "먼저 로그인을 하세요");;
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

}
