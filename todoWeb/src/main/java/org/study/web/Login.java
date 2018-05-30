package org.study.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.study.dao.UserDaoImpl;
import org.study.dao.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login.do")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService service = new UserDaoImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String remember= request.getParameter("remember");
		if (service.isValidUser(id)) {
			if(service.passwordCheck(id, password)) {

				HttpSession session = request.getSession();
				session.setAttribute("userid", id);
				request.setAttribute("name", service.getUser(id).getName());
				
				
				Cookie cookie= null;
				if(remember!=null) {
					cookie=new Cookie("id",java.net.URLEncoder.encode(id, "UTF-8"));
					cookie.setMaxAge(60*60*24*30);//유효기간 한달
					response.addCookie(cookie);
				}else {
					cookie=new Cookie("id",null);
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				
				if(((String)request.getParameter("orgPath")).equals("")) {
					request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);
				}else {
					response.sendRedirect(request.getParameter("orgPath"));
				}
			}else {
				request.setAttribute("error", "패스워드가 일치하지 않습니다.");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("error", "존재하지 않는 아이디입니다.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}   
	}
}
