<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.*" %>
<%@ page import="org.study.dao.Todo" %>
<%@ page import="org.study.dao.TodoDaoImpl" %>
<%@ page import="org.study.dao.TodoService" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TODOLIST</title>
<jsp:include page="/link.jsp" flush="false"/>
</head>
<link rel="stylesheet" href="/webjars/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="/webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="/webjars/jquery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet" href="/../css/theme.css">
<link rel="stylesheet" href="/../css/index.css">
<style>
.pageidx{padding: 5px;}
div {text-align:center}
.midd{
max-width:400px;
margin:10px auto 0 auto;
text-align: center;
}
.haha{
margin:auto;
max-width:600px;
}
</style>
<% TodoService service = new TodoDaoImpl();
String view=request.getParameter("view");%>
<body>
<div class="container3">
	<div class="starter-template">
	<%if(view.equals("today")){ %>
	<h1>TODAY'S TODOLIST</h1>
	<%}else if(view.equals("week")){ %>
	<h1>WEEKLY TODOLIST</h1>
	<%}else if(view.equals("month")){ %>
	<h1>MONTHLY TODOLIST</h1>
	<%}else if(view.equals("done")){ %>
	<h1>DONE TODOLIST</h1>
	<%}else if(view.equals("undone")){ %>
	<h1>UNDONE TODOLIST</h1>
	<%}else{ %>
		<h1>TODOLIST</h1>
	<%} %>
		<font color="red">${error}</font>
		<font color="blue">${msg}</font>
	<%if(!view.equals("done")&&!view.equals("undone")){ %>
		<div class="haha">
		<div class="progress">
  <div class="progress-bar progress-bar-striped active" role="progressbar" style="width: 
  <%= service.doneRate((String)session.getAttribute("userid"), view) %>%">
    <span class="sr-only"></span>
  </div>
  </div>
</div>
<%} %>
		<div class="haha">
		<table class="table table-bordered">
			<tr class="cols123">
				<th>분류</th><th>제목</th>
				<th class="tha">시작일</th><th class="tha">종료일</th><th>달성</th>
				<th>완료</th><th>수정</th><th>삭제</th>
			</tr>
			<% List<Todo> list=(List<Todo>)request.getAttribute("list");
			 for(int i=0;i<list.size();i++){
			 Todo todo=list.get(i);%>
			<tr class="row123">
				<td><span class="label label-default"><%=todo.getCateName()%></span></td><td><a href="Todo.do?idx=<%=todo.getIdx()%>"><%=todo.getTitle()%></td>
				<td><%=todo.getStart_date()%></td><td><%=todo.getTarget_date()%></td>
				<% String done;
				if(todo.isDone()){
					done="O";
				}else{
					done="X";
				}%>
				<td><%=done %></td>
				<td>
				<%if(!todo.isDone()){ %>
				<a href="TodoDone.do?idx=<%=todo.getIdx()%>&done=1"><input type="button" value="완료" class="btn btn-xs btn-success"></a>
				<%}else{ %>
				<a href="TodoDone.do?idx=<%=todo.getIdx()%>&done=0"><input type="button" value="취소" class="btn btn-xs btn-warning"></a>
				<%} %>
				</td>
				<td><a href="TodoMod.do?idx=<%=todo.getIdx()%>"><input type="button" value="수정" class="btn btn-xs btn-info"></a></td>
				<td><a href="TodoDel.do?idx=<%=todo.getIdx()%>"><input type="button" value="삭제" class="btn btn-xs btn-danger"></a></td>
			</tr>
			<%} %>
		</table>
		</div>
<div class="midd">
<% 
int a=Integer.parseInt(request.getParameter("page"));
int pagestart=1+((a-1)/10)*10;
if(pagestart!=1){%>
<a href="/TodoList.do?page=<%=pagestart-1%>&view=<%=view%>">
<input type="button" value="☜" class="btn btn-sm btn-info"></a>
<%}
for(int i=pagestart;i<pagestart+10&&i<=service.maxpage((String)session.getAttribute("userid"),view);i++){ %>
	<a href="/TodoList.do?page=<%=i%>&view=<%=view%>" class="pageidx"><%=i%></a>
<%} 
if(pagestart+10<service.maxpage((String)session.getAttribute("userid"),view)){%>
<a href="/TodoList.do?page=<%=pagestart+10%>&view=<%=view%>">
<input type="button" value="☞" class="btn btn-sm btn-info"></a>
<%} %>
<br><br>
</div>
      </div>
    </div><!-- /.container -->
</body>
</html>