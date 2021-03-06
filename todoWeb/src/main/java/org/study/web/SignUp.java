package org.study.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.study.dao.UserDaoImpl;
import org.study.dao.UserService;
import org.study.model.User;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUp.do")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService service=new UserDaoImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user= new User();
		user.setId(request.getParameter("id"));
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		

		String ex="\"|<>{}";
		
		for(int i=0;i<ex.length();i++) {
			if(user.getId().contains(Character.toString(ex.charAt(i)))
					||user.getName().contains(Character.toString(ex.charAt(i)))) {
				request.setAttribute("error", "\",|,<,>,{,}는 입력하실 수 없습니다.");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}
		}
		
		if(!service.isValidUser(user.getId())) {
			if(service.addUser(user)) {
				request.setAttribute("name", user.getName());
				request.getRequestDispatcher("/WEB-INF/views/signUpOk.jsp").forward(request, response);
			}else {
				request.setAttribute("error", "회원가입에 실패했습니다.");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}else {
			request.setAttribute("error", "이미 존재하는 아이디입니다.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		
		
	}

}
