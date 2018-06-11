<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="org.study.dao.User" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PROFILE</title>
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
.haha{
margin:auto;
max-width:300px;
}
</style>
<body>
<div class="container2">
	<div class="starter-template">
	<%User user=(User)request.getAttribute("user"); %>
			<h2><%=user.getName()%></h2>
			<font color="red">${error}</font>
			<font color="blue">${msg}</font>
			<%if(user.getTodoNum()!=0){ %>
				<div class="haha">
					<div class="progress">
					  <div class="progress-bar progress-bar-striped active" role="progressbar" style="width: 
					  <%= user.getDoneNum()*100/user.getTodoNum()%>%">
					    <span class="sr-only"></span>
					  </div>
					  </div>
					</div>
					<%} %>
	<div class="wrap">
		<table class="table table-striped">
			<tr>
				<td>메일 : </td><td><%=user.getEmail()%></td>
			</tr>
			<tr>
				<td nowrap>등록한 할일 수 : </td><td><%=user.getTodoNum()%></td>
			</tr>
			<tr>
				<td>완료한 할일 수 : </td><td><%=user.getDoneNum()%></td>
			</tr>

		</table>
		<p>
		<a href="UserMod.do"><input type="button" value="수정" class="btn btn-sm btn-info"></a>
			<a href="/UserDel.jsp"><input type="button" value="탈퇴" class="btn btn-sm btn-danger"></a></p>
		</div>

      </div>
    </div><!-- /.container -->
</body>
</html>