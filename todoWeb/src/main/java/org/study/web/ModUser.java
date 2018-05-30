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
import org.study.sec.PasswordAuthentication;

/**
 * Servlet implementation class ModUser
 */
@WebServlet("/ModUser.do")
public class ModUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService service=new UserDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PasswordAuthentication passAuth = new PasswordAuthentication();
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("userid");
		User user= service.getUser(id);
		if(user==null) {
			request.setAttribute("error", "에러 01 : 정보 수정에 실패했습니다.");
			request.getRequestDispatcher("UserMod.do").forward(request, response);
			return;
		}
//passAuth.authenticate((String)request.getParameter("password"), e.getPassword())
		if(passAuth.authenticate(((String)request.getParameter("password")).toCharArray(), user.getPassword())) {
			user.setPassword((String)request.getParameter("password"));
			if(((String)request.getParameter("newpassword")).length()>0) {
				if(((String)request.getParameter("newpassword")).equals((String)request.getParameter("newpasswordC"))) {
					user.setPassword((String)request.getParameter("newpassword"));
				}else {
					request.setAttribute("error", "변경할 비밀번호와 확인이 일치하지 않습니다.");
					request.getRequestDispatcher("UserMod.do").forward(request, response);
					return;
				}
			}
			user.setName((String)request.getParameter("name"));
			user.setEmail((String)request.getParameter("email"));
			boolean rs=service.updateUser(user);
			if(rs) {
				request.setAttribute("msg", "정보를 수정하였습니다.");
				request.getRequestDispatcher("Profile.do").forward(request, response);
				return;
			}else{
				request.setAttribute("error", "에러 : 정보를 수정하지 못했습니다.");
				request.getRequestDispatcher("UserMod.do").forward(request, response);
				return;
			}
		}else {
			request.setAttribute("error", "에러 : 비밀번호가 틀렸습니다.");
			request.getRequestDispatcher("UserMod.do").forward(request, response);
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
