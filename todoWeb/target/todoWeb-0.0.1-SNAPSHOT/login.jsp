<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<jsp:include page="link.jsp" flush="false"/>
</head>
<link rel="stylesheet" href="/webjars/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="/webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="/webjars/jquery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet" href="/../css/login.css">
<% Cookie[] cookie= request.getCookies();
	String id=null;
	if(cookie!=null){
		for(int i=0;i<cookie.length;i++){
			if(cookie[i].getName().equals("id")){
				id=cookie[i].getValue();
			}
		}
	}%>
<body>
<section class="container-login">
  <article class="half">
     <h1>TODOLIST</h1>
     <font color="red">${error}</font>
     <div class="tabs">
        <span class="tab signin active"><a href="#signin">로그인</a></span>
        <span class="tab signup"><a href="#signup">회원가입</a></span>
     </div>
     <div class="content">
        <div class="signin-cont cont">
          <form action="/Login.do" method="post" >
          		<input type='hidden' name="orgPath" value="${orgPath}">
          		<%if(id!=null){ %>
               <input type="text" name="id" id="id" class="inpt" required="required" value="<%=id%>">
               <%}else{ %>
               <input type="text" name="id" id="id" class="inpt" required="required" placeholder="아이디 입력">
               <%} %>
               <label for="id">아이디 입력</label>
               <input type="password" name="password" id="password" class="inpt" required="required" placeholder="비밀번호 입력">
     			<label for="password">비밀번호 입력</label>
               <input type="checkbox" name="remember" id="remember" class="checkbox" checked>
               <label for="remember">Remember me</label>
               <div class="submit-wrap">
                    <input type="submit" value="로그인" class="submit">
               </div>
	        </form>
        </div>
        <div class="signup-cont cont">
        <form action="/SignUp.do" method="post" >
        	<input type="text" name="id" id="id" class="inpt" required="required" placeholder="아이디 입력" />
           <label for="id">아이디 입력</label>
           <input type="password" name="password" id="password" class="inpt" required="required" placeholder="비밀번호 입력">
 		   <label for="password">비밀번호 입력</label>
           <input type="text" name="name" id="name" class="inpt" required="required" placeholder="이름 입력">
           <label for="name">이름 입력</label>
     	   <input type="email" name="email" id="email" class="inpt" required="required" placeholder="이메일 입력">
           <label for="email">이메일 입력</label>
           <div class="submit-wrap">
                <input type="submit" value="회원가입" class="submit">
                <a href="#" class="more">이용 약관</a>
           </div>
        </form>
      </div>
     </div>
  </article>
  <div class="half bg"></div>
	</section>

<script>
$('.tabs .tab').click(function(){
    if ($(this).hasClass('signin')) {
        $('.tabs .tab').removeClass('active');
        $(this).addClass('active');
        $('.cont').hide();
        $('.signin-cont').show();
    } 
    if ($(this).hasClass('signup')) {
        $('.tabs .tab').removeClass('active');
        $(this).addClass('active');
        $('.cont').hide();
        $('.signup-cont').show();
    }
});
$('.container .bg').mousemove(function(e){
    var amountMovedX = (e.pageX * -1 / 30);
    var amountMovedY = (e.pageY * -1 / 9);
    $(this).css('background-position', amountMovedX + 'px ' + amountMovedY + 'px');
});
</script>
</body>
</html>