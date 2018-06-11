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
import org.study.model.User;
import org.study.sec.PasswordAuthentication;

/**
 * Servlet implementation class UserDel
 */
@WebServlet("/UserDel.do")
public class UserDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TodoService service=new TodoDaoImpl();
	private UserService userService=new UserDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PasswordAuthentication passAuth = new PasswordAuthentication();
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("userid");
		User user=userService.getUser(id);
		
		if(passAuth.authenticate(((String)request.getParameter("password")).toCharArray(), user.getPassword())) {
			if(userService.isValidUser(id)) {
				if(userService.deleteUser(id)) {
					session.invalidate();
					request.setAttribute("msg", "성공적으로 탈퇴되었습니다.");
					request.getRequestDispatcher("index.jsp")
							.forward(request, response);
					return;
				}else {
					request.setAttribute("error", "탈퇴에 실패했습니다.");
					request.getRequestDispatcher("/WEB-INF/views/Profile.jsp")
							.forward(request, response);
					return;
				}
			}else {
				request.setAttribute("error", "에러98: - UserMod");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
		}else {
			request.setAttribute("error", "비밀번호가 일치하지 않습니다.");
			request.getRequestDispatcher("UserDel.jsp").forward(request, response);
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
