package org.study.web;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.study.dao.TodoDaoImpl;
import org.study.dao.TodoService;

/**
 * Servlet implementation class TodoDoneServlet
 */
@WebServlet("/TodoDone.do")
public class TodoDone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TodoService service=new TodoDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx=Integer.parseInt(request.getParameter("idx"));
		int done =Integer.parseInt(request.getParameter("done"));

		HttpSession session = request.getSession();
		if(service.isCorrectUser((String)session.getAttribute("userid"), idx)) {
			if(done==1) {
				service.TodoDone(idx, true);
			}else if(done==0) {
				service.TodoDone(idx, false);
			}else {
				request.setAttribute("error", "올바르지 않은 접근입니다.");
				request.getRequestDispatcher("TodoList.do?page=1&view=all").forward(request, response);
				return;
			}
//			request.setAttribute("msg", "완료 상태가 변경되었습니다.");
			String orgPath=(String)request.getHeader("Referer");
			String[] path=orgPath.split("/");
			if(path[3].trim().equals("ModTodo")) {
				path[3]="Todo.do?idx="+idx;
				StringBuilder sb=new StringBuilder(path[0]);
				for(int i=1;i<path.length;i++) {
					sb.append("/");
					sb.append(path[i].trim());
				}
				orgPath=sb.toString();
			}
//			String Path=orgPath.split("/")[3].trim();
//			System.out.println(Path);
//			request.getRequestDispatcher(Path).forward(request, response);
			response.sendRedirect(orgPath);
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
