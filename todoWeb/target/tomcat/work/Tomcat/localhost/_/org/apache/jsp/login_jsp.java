/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-05-30 06:23:48 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>로그인</title>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "link.jsp", out, false);
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/webjars/bootstrap/3.3.5/css/bootstrap.min.css\">\r\n");
      out.write("<script src=\"/webjars/bootstrap/3.3.5/js/bootstrap.min.js\"></script>\r\n");
      out.write("<script src=\"/webjars/jquery/2.1.4/jquery.min.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/../css/login.css\">\r\n");
 Cookie[] cookie= request.getCookies();
	String id=null;
	if(cookie!=null){
		for(int i=0;i<cookie.length;i++){
			if(cookie[i].getName().equals("id")){
				id=cookie[i].getValue();
			}
		}
	}
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<section class=\"container-login\">\r\n");
      out.write("  <article class=\"half\">\r\n");
      out.write("     <h1>TODOLIST</h1>\r\n");
      out.write("     <font color=\"red\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${error}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</font>\r\n");
      out.write("     <div class=\"tabs\">\r\n");
      out.write("        <span class=\"tab signin active\"><a href=\"#signin\">로그인</a></span>\r\n");
      out.write("        <span class=\"tab signup\"><a href=\"#signup\">회원가입</a></span>\r\n");
      out.write("     </div>\r\n");
      out.write("     <div class=\"content\">\r\n");
      out.write("        <div class=\"signin-cont cont\">\r\n");
      out.write("          <form action=\"/Login.do\" method=\"post\" >\r\n");
      out.write("          \t\t<input type='hidden' name=\"orgPath\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${orgPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("          \t\t");
if(id!=null){ 
      out.write("\r\n");
      out.write("               <input type=\"text\" name=\"id\" id=\"id\" class=\"inpt\" required=\"required\" value=\"");
      out.print(id);
      out.write("\">\r\n");
      out.write("               ");
}else{ 
      out.write("\r\n");
      out.write("               <input type=\"text\" name=\"id\" id=\"id\" class=\"inpt\" required=\"required\" placeholder=\"아이디 입력\">\r\n");
      out.write("               ");
} 
      out.write("\r\n");
      out.write("               <label for=\"id\">아이디 입력</label>\r\n");
      out.write("               <input type=\"password\" name=\"password\" id=\"password\" class=\"inpt\" required=\"required\" placeholder=\"비밀번호 입력\">\r\n");
      out.write("     \t\t\t<label for=\"password\">비밀번호 입력</label>\r\n");
      out.write("               <input type=\"checkbox\" name=\"remember\" id=\"remember\" class=\"checkbox\">\r\n");
      out.write("               <label for=\"remember\">Remember me</label>\r\n");
      out.write("               ");
if(id!=null){
      out.write("\r\n");
      out.write("               \t\t<script>\r\n");
      out.write("\t\t\t\t\t$('#remember').prop(\"checked\",true);\r\n");
      out.write("\t\t\t\t\t</script>\r\n");
      out.write("\t\t\t\t");
} 
      out.write("\r\n");
      out.write("               <div class=\"submit-wrap\">\r\n");
      out.write("                    <input type=\"submit\" value=\"로그인\" class=\"submit\">\r\n");
      out.write("               </div>\r\n");
      out.write("\t        </form>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"signup-cont cont\">\r\n");
      out.write("        <form action=\"/SignUp.do\" method=\"post\" >\r\n");
      out.write("        \t<input type=\"text\" name=\"id\" id=\"id\" class=\"inpt\" required=\"required\" placeholder=\"아이디 입력\"  onKeyDown=\"checkNumber();\"/>\r\n");
      out.write("           <label for=\"id\">아이디 입력</label>\r\n");
      out.write("           <input type=\"password\" name=\"password\" id=\"password\" class=\"inpt\" required=\"required\" placeholder=\"비밀번호 입력\">\r\n");
      out.write(" \t\t   <label for=\"password\">비밀번호 입력</label>\r\n");
      out.write("           <input type=\"text\" name=\"name\" id=\"name\" class=\"inpt\" required=\"required\" placeholder=\"이름 입력\" onKeyDown=\"checkNumber();\">\r\n");
      out.write("           <label for=\"name\">이름 입력</label>\r\n");
      out.write("     \t   <input type=\"email\" name=\"email\" id=\"email\" class=\"inpt\" required=\"required\" placeholder=\"이메일 입력\">\r\n");
      out.write("           <label for=\"email\">이메일 입력</label>\r\n");
      out.write("           <div class=\"submit-wrap\">\r\n");
      out.write("                <input type=\"submit\" value=\"회원가입\" class=\"submit\">\r\n");
      out.write("                <a href=\"#\" class=\"more\">이용 약관</a>\r\n");
      out.write("           </div>\r\n");
      out.write("        </form>\r\n");
      out.write("      </div>\r\n");
      out.write("     </div>\r\n");
      out.write("  </article>\r\n");
      out.write("  <div class=\"half bg\"></div>\r\n");
      out.write("\t</section>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("$('.tabs .tab').click(function(){\r\n");
      out.write("    if ($(this).hasClass('signin')) {\r\n");
      out.write("        $('.tabs .tab').removeClass('active');\r\n");
      out.write("        $(this).addClass('active');\r\n");
      out.write("        $('.cont').hide();\r\n");
      out.write("        $('.signin-cont').show();\r\n");
      out.write("    } \r\n");
      out.write("    if ($(this).hasClass('signup')) {\r\n");
      out.write("        $('.tabs .tab').removeClass('active');\r\n");
      out.write("        $(this).addClass('active');\r\n");
      out.write("        $('.cont').hide();\r\n");
      out.write("        $('.signup-cont').show();\r\n");
      out.write("    }\r\n");
      out.write("});\r\n");
      out.write("$('.container .bg').mousemove(function(e){\r\n");
      out.write("    var amountMovedX = (e.pageX * -1 / 30);\r\n");
      out.write("    var amountMovedY = (e.pageY * -1 / 9);\r\n");
      out.write("    $(this).css('background-position', amountMovedX + 'px ' + amountMovedY + 'px');\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("function checkNumber()\r\n");
      out.write("\t{\r\n");
      out.write("\t var objEv = event.srcElement;\r\n");
      out.write("\t var num =\"|\\\"\";    //입력을 막을 특수문자 기재.\r\n");
      out.write("\t event.returnValue = true;\r\n");
      out.write("\t  \r\n");
      out.write("\t for (var i=0;i<objEv.value.length;i++)\r\n");
      out.write("\t {\r\n");
      out.write("\t\r\n");
      out.write("\t if(-1 != num.indexOf(objEv.value.charAt(i)))\r\n");
      out.write("\t event.returnValue = false;\r\n");
      out.write("\t }\r\n");
      out.write("\t  \r\n");
      out.write("\t if (!event.returnValue)\r\n");
      out.write("\t {\r\n");
      out.write("\t  alert(\"해당 문자는 입력하실 수 없습니다.\");\r\n");
      out.write("\t  objEv.value=\"\";\r\n");
      out.write("\t }\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
