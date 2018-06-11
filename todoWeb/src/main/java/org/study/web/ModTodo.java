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
import org.study.model.Todo;

/**
 * Servlet implementation class ModTodoServlet
 */
@WebServlet("/ModTodo")
public class ModTodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoService service=new TodoDaoImpl();
	private UserService userService=new UserDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Todo todo =new Todo();
		HttpSession session = request.getSession();
		if(request.getParameter("start_date")==null) {
			request.setAttribute("error", "투두 수정에 실패했습니다.");
			request.getRequestDispatcher("TodoList.do?page=1&view=all").forward(request, response);
			return;
		}
		
		StringBuffer Start_date= new StringBuffer(request.getParameter("start_date"));
		StringBuffer Target_date= new StringBuffer(request.getParameter("target_date"));
		
		Start_date.append(":00");
		Target_date.append(":00");
		
		todo.setUser_id((String)session.getAttribute("userid"));
		todo.setIdx(Integer.parseInt(request.getParameter("idx")));
		todo.setStart_date(Start_date.toString());
		todo.setTarget_date(Target_date.toString());
		todo.setCategory(Integer.parseInt(request.getParameter("cate")));
		todo.setTitle(request.getParameter("title"));
		todo.setContent(request.getParameter("content"));
		
		if(!service.isCorrectUser(todo.getUser_id(),todo.getIdx())) {
			request.setAttribute("error", "할일 수정에 실패했습니다. 해당 할일을 수정할 권한이 없습니다.");
			request.getRequestDispatcher("TodoList.do?page=1&view=all").forward(request, response);
			return;
		}
		
		String ex="\"|<>{}";
		
		for(int i=0;i<ex.length();i++) {
			if(todo.getTitle().contains(Character.toString(ex.charAt(i)))
					||todo.getContent().contains(Character.toString(ex.charAt(i)))) {
				request.setAttribute("error", "\",|,<,>,{,}는 입력하실 수 없습니다.");
				request.getRequestDispatcher("TodoMod.do?idx="+todo.getIdx()).forward(request, response);
				return;
			}
		}
		
		if(service.updateTodo(todo)) {
			request.setAttribute("msg", "투두가 수정되었습니다.");
			request.getRequestDispatcher("Todo.do?idx="+todo.getIdx()).forward(request, response);
		}else {
			request.setAttribute("error", "투두 추가에 실패했습니다.");
			request.getRequestDispatcher("TodoMod.do?idx="+todo.getIdx()).forward(request, response);
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
