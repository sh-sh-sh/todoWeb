package org.study.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.study.dao.Todo;
import org.study.dao.TodoDaoImpl;
import org.study.dao.TodoService;

/**
 * Servlet implementation class TodoServlet
 */
@WebServlet("/Todo.do")
public class TodoView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TodoService service=new TodoDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx=Integer.parseInt(request.getParameter("idx"));
		HttpSession session = request.getSession();
		if(service.isCorrectUser((String)session.getAttribute("userid"), idx)) {
			request.setAttribute("todo", service.getTodo(idx));
			request.getRequestDispatcher("/WEB-INF/views/Todo.jsp")
					.forward(request, response);
		}else {
			request.setAttribute("error", "올바르지 않은 접근입니다.");
			request.getRequestDispatcher("TodoList.do?page=1&view=all").forward(request, response);
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
