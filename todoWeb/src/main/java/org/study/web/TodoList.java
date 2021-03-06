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
 * Servlet implementation class TodoListServlet
 */
@WebServlet("/TodoList.do")
public class TodoList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TodoService service=new TodoDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("page")!=null) {
			HttpSession session = ((HttpServletRequest)request).getSession();
			int page=Integer.parseInt(request.getParameter("page"));
			int sort;
			if(request.getParameter("sort")!=null) {
				sort=Integer.parseInt(request.getParameter("sort"));
			}else {
				sort=0;
			}
			String view;
			if(request.getParameter("view")!=null) {
				view=request.getParameter("view");
			}else {
				view="all";
			}
			request.setAttribute("sort", sort);
			String id=(String)session.getAttribute("userid");
			
			request.setAttribute("doneRate", service.doneRate(id, view));
			request.setAttribute("maxpage", service.maxpage(id, view));
			
			request.setAttribute("title", "TODOLIST");
			if(view.equals("done")) {
				request.setAttribute("title", "DONE LIST");
			}else if(view.equals("undone")) {
				request.setAttribute("title", "UNDONE TODOLIST");
			}else if(view.equals("today")) {
				request.setAttribute("title", "TODAY'S TODOLIST");
			}else if(view.equals("week")) {
				request.setAttribute("title", "WEEKLY TODOLIST");
			}else if(view.equals("month")) {
				request.setAttribute("title", "MONTHLY TODOLIST");
			}
			
			if(service.maxpage(id, "all")==0) {
				request.setAttribute("msg", "등록한 할 일이 없습니다. Todo를 등록하세요!");
				request.getRequestDispatcher("addTodo.jsp").forward(request, response);
			}else if(service.maxpage(id, view)==0) {
				if(view.equals("done")) {
					request.setAttribute("error", "완료한 할 일이 없습니다.");
				}else if(view.equals("undone")) {
					request.setAttribute("msg", "완료하지 않은 할 일이 없습니다!");
				}else if(view.equals("today")) {
					request.setAttribute("msg", "오늘의 할 일이 없습니다!");
				}else if(view.equals("week")) {
					request.setAttribute("msg", "이번주에 해야 할 일이 없습니다!");
				}else if(view.equals("month")) {
					request.setAttribute("msg", "이번달에 해야 할 일이 없습니다!");
				}
				request.setAttribute("list", service.getTodoList(id, page, sort,view));
				request.setAttribute("page", page);
				request.getRequestDispatcher("/WEB-INF/views/TodoList.jsp").forward(request, response);
			}else if(page<1||page>service.maxpage(id,view)) {
				request.setAttribute("error", "존재하지 않는 페이지에 접근해 1페이지로 이동되었습니다.");
				request.getRequestDispatcher("TodoList.do?page=1&view="+view).forward(request, response);
			}else {
				request.setAttribute("list", service.getTodoList(id, page, sort,view));
				request.setAttribute("page", page);
				request.getRequestDispatcher("/WEB-INF/views/TodoList.jsp").forward(request, response);
			}
		}else {
			request.getRequestDispatcher("TodoList.do?page=1&view=all").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
