<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="org.study.dao.Todo" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TODO</title>
<jsp:include page="/link.jsp" flush="false"/>
</head>
<link rel="stylesheet" href="/webjars/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="/webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="/webjars/jquery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet" href="/../css/theme.css">
<link rel="stylesheet" href="/../css/index.css">
<style>
.wrap{
margin:auto;
max-width:300px;
}
</style>
<body>
<div class="container2">
	<div class="starter-template">
	<%Todo todo=(Todo)request.getAttribute("todo"); %>
			<h2><span class="label label-default"><%=todo.getCateName()%></span> <%=todo.getTitle()%></h2>
			<font color="red">${error}</font>
			<font color="blue">${msg}</font>
			<p>
			<% String done;
				if(todo.isDone()){done="완료";}else{done="미완료";}%>
				<%=done%>
				<%if(!todo.isDone()){ %>
				<a href="TodoDone.do?idx=<%=todo.getIdx()%>&done=1"><input type="button" value="완료함" class="btn btn-xs btn-success"></a>
				<%}else{ %>
				<a href="TodoDone.do?idx=<%=todo.getIdx()%>&done=0"><input type="button" value="미완료함" class="btn btn-xs btn-warning"></a>
				<%} %>
				
			</p>
	<div class="wrap">
		<table class="table table-striped">
			<tr>
				<td>내용 : </td><td><%=todo.getContent()%></td>
			</tr>
			<tr>
				<td>시작일 : </td><td><%=todo.getStart_date()%></td>
			</tr>
			<tr>
				<td>기한일 : </td><td><%=todo.getTarget_date()%></td>
			</tr>
			<tr>
				<td>생성일 : </td><td><%=todo.getCreate_date()%></td>
			</tr>
			
		</table>
		<p>
		<a href="TodoList.do?page=1&view=all"><input type="button" value="목록" class="btn btn-sm btn-warning"></a>
		<a href="TodoMod.do?idx=<%=todo.getIdx()%>"><input type="button" value="수정" class="btn btn-sm btn-info"></a>
			<a href="TodoDel.do?idx=<%=todo.getIdx()%>"><input type="button" value="삭제" class="btn btn-sm btn-danger"></a></p>
		</div>

      </div>
    </div><!-- /.container -->
</body>
</html>