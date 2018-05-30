package org.study.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.study.dao.TodoDaoImpl;
import org.study.dao.TodoService;
import org.study.dao.UserDaoImpl;
import org.study.dao.UserService;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile.do")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService=new UserDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("userid");
		
		if(userService.isValidUser(id)) {
			request.setAttribute("user", userService.getUser(id));
			request.getRequestDispatcher("/WEB-INF/views/Profile.jsp")
					.forward(request, response);
		}else {
			request.setAttribute("error", "에러99:");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
