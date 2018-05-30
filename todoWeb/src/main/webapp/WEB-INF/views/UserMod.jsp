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
			<h2>프로필 수정</h2>
			<font color="red">${error}</font>
			<font color="blue">${msg}</font>
	<div class="wrap">
	<form action="ModUser.do" method="post">  
		<table class="table table-hover">
			<tr>
				<td>이름 : </td><td>
					<div class="inputcss">
					<input type="text" required="required" name="name" id="name" 
					class="form-control floating-label" placeholder="20글자 이내" value="<%=user.getName()%>" onKeyDown="checkNumber();">
					</div>
					</td>
			</tr>
			<tr>
				<td>기존 비밀번호 : </td><td><input type='password' required="required" 
				placeholder="필수 입력" class="form-control floating-label" name="password"/></td>
			</tr>
			<tr>
				<td>변경할 비밀번호 : </td><td><input type='password' 
				placeholder="변경시에만 입력" class="form-control floating-label" name="newpassword"/></td>
			</tr>
			<tr>
				<td>변경할 비밀번호 확인 : </td><td><input type='password' 
				placeholder="변경시에만 입력" class="form-control floating-label" name="newpasswordC"/></td>
			</tr>
			<tr>
				<td>메일 : </td><td><input type="email" name="email" required="required" 
				value="<%=user.getEmail()%>"/></td>
			</tr>
		</table>
		
		<p>
			<input type='submit' name="수정" class="btn btn-sm btn-info" value="수정"/>
			<a href="/UserDel.jsp"><input type="button" value="탈퇴" class="btn btn-sm btn-danger"></a></p>
		</form>
		</div>
      </div>
    </div><!-- /.container -->
    <script>
    function checkNumber()
		{
		 var objEv = event.srcElement;
		 var num ="|\"";    //입력을 막을 특수문자 기재.
		 event.returnValue = true;
		  
		 for (var i=0;i<objEv.value.length;i++)
		 {
		
		 if(-1 != num.indexOf(objEv.value.charAt(i)))
		 event.returnValue = false;
		 }
		  
		 if (!event.returnValue)
		 {
		  alert("해당 문자는 입력하실 수 없습니다.");
		  objEv.value="";
		 }
		}
		</script>
</body>
</html>