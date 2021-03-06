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

/**
 * Servlet implementation class TodoDelServlet
 */
@WebServlet("/TodoDel.do")
public class TodoDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TodoService service=new TodoDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx=Integer.parseInt(request.getParameter("idx"));

		HttpSession session = request.getSession();
		if(service.isCorrectUser((String)session.getAttribute("userid"), idx)) {
			service.deleteTodo(idx);
			request.setAttribute("msg", "Todo를 성공적으로 삭제했습니다.");
//			response.sendRedirect("TodoList.do?page=1&view=all");
			request.getRequestDispatcher("TodoList.do?page=1&view=all").forward(request, response);
		}else {
			request.setAttribute("error", "해당 TODO를 삭제할 권한이 없습니다.");
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
