/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-06-07 07:17:21 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.study.dao.Todo;

public final class TodoMod_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>타이틀 입력</title>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/link.jsp", out, false);
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<script src=\"/webjars/jquery/2.1.4/jquery.min.js\"></script>\r\n");
      out.write("<script src=\"/webjars/bootstrap/3.3.5/js/bootstrap.min.js\"></script>\r\n");
      out.write("<script src=\"/webjars/momentjs/2.22.1/moment.js\"></script>\r\n");
      out.write("<script src=\"/js/bootstrap-material-datetimepicker.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/webjars/bootstrap/3.3.5/css/bootstrap.min.css\">\r\n");
      out.write("<link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/../css/theme.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/../css/index.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/../css/bootstrap-material-datetimepicker.css\" />\r\n");
      out.write("\t\t\r\n");
      out.write("<style>\r\n");
      out.write(".content{\r\n");
      out.write("width:300px;\r\n");
      out.write("height:100px;\r\n");
      out.write("}\r\n");
      out.write(".wrap{\tmax-width: 300px;\r\n");
      out.write("margin:auto;}\r\n");
      out.write("</style>\r\n");
      out.write("<body>\r\n");
Todo todo=(Todo)request.getAttribute("todo"); 
      out.write("\r\n");
      out.write("<div class=\"container2\">\r\n");
      out.write("\t<div class=\"starter-template\">\r\n");
      out.write("\t\t<font color=\"red\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${error}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</font>\r\n");
      out.write("\t\t<font color=\"blue\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${msg}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</font>\r\n");
      out.write("\t\t\t<h1 class=\"ha\">할 일 수정</h1>\r\n");
      out.write("\t\t<form action=\"ModTodo\" method=\"post\">  \r\n");
      out.write("\t\t<input type='hidden' name='idx' value='");
      out.print(todo.getIdx());
      out.write("'>\r\n");
      out.write("\t\t<div class=\"wrap\">\r\n");
      out.write("\t\t\t<table class=\"table table-hover\">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td nowrap>시작일</td><td>\r\n");
      out.write("\t\t\t\t\t<div class=\"inputcss\">\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" name=\"start_date\" id=\"date-start\" required=\"required\"  \r\n");
      out.write("\t\t\t\t\tclass=\"form-control floating-label\" placeholder=\"Start Date\" value=\"");
      out.print(todo.getStart_date());
      out.write("\">\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td nowrap>기한/종료일</td><td>\r\n");
      out.write("\t\t\t\t\t<div class=\"inputcss\">\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" name=\"target_date\" id=\"date-end\"  required=\"required\" \r\n");
      out.write("\t\t\t\t\tclass=\"form-control floating-label\" placeholder=\"End Date\" value=\"");
      out.print(todo.getTarget_date());
      out.write("\">\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>카테고리</td>\r\n");
      out.write("\t\t\t\t\t<td><Select name=\"cate\" style=\"width:150px\" class=\"form-control floating-label\" id=\"co\">\r\n");
      out.write("\t\t\t\t\t\t<option value=\"1\">생활</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"2\">학업</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"3\">직장</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"4\">연애</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"5\">가족</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"6\">기타</option>\r\n");
      out.write("\t\t\t\t\t</Select></td>\r\n");
      out.write("\t\t\t\t\t<script>\r\n");
      out.write("\t\t\t\t\t$('#co').val(\"");
      out.print(todo.getCategory());
      out.write("\").prop('selected',true);\r\n");
      out.write("\t\t\t\t\t</script>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>제목</td><td>\r\n");
      out.write("\t\t\t\t\t<input type='text' name=\"title\" class=\"form-control floating-label\" \r\n");
      out.write("\t\t\t\t\trequired=\"required\" placeholder=\"1자 이상 20자 이하\" value=\"");
      out.print(todo.getTitle());
      out.write("\" \r\n");
      out.write("\t\t\t\t\tonKeyDown=\"checkNumber();\" maxlength=\"20\" pattern=\".{1,20}\"/>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>내용</td><td><input type=\"text\" name=\"content\" class=\"form-control floating-label\"  \r\n");
      out.write("\t\t\t\t\tplaceholder=\"입력하지 않으셔도 됩니다. 200글자 이하\" value=\"");
      out.print(todo.getContent());
      out.write("\" \r\n");
      out.write("\t\t\t\t\tonKeyDown=\"checkNumber();\" maxlength=\"200\" pattern=\".{,200}\"/></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t\t<input type='submit' name=\"수정\" class=\"btn btn-info\" value=\"수정\"/>\r\n");
      out.write("\t\t\t<a href=\"TodoDel.do?idx=");
      out.print(todo.getIdx());
      out.write("\"><input type=\"button\" value=\"삭제\" class=\"btn btn-danger\"></a>\r\n");
      out.write("\t\t\t<br>\r\n");
      out.write("\t\t\t<br>\r\n");
      out.write("\t\t</div><!-- wrap -->\r\n");
      out.write("\t\t</form>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div><!-- /.container -->\r\n");
      out.write("    \t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t$(document).ready(function()\r\n");
      out.write("\t\t{\r\n");
      out.write("\r\n");
      out.write("\t\t\t$('#date-end').bootstrapMaterialDatePicker\r\n");
      out.write("\t\t\t({\r\n");
      out.write("\t\t\t\tweekStart: 0, format: 'YYYY-MM-DD HH:mm'\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t$('#date-start').bootstrapMaterialDatePicker\r\n");
      out.write("\t\t\t({\r\n");
      out.write("\t\t\t\tweekStart: 0, format: 'YYYY-MM-DD HH:mm', shortTime : true\r\n");
      out.write("\t\t\t}).on('change', function(e, date)\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\t$('#date-end').bootstrapMaterialDatePicker('setMinDate', date);\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t\t$('#min-date').bootstrapMaterialDatePicker({ format : 'YYYY-MM-DD HH:mm', minDate : new Date() });\r\n");
      out.write("\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction checkNumber()\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t var objEv = event.srcElement;\r\n");
      out.write("\t\t var num =\"|\\\"<>{}\";    //입력을 막을 특수문자 기재.\r\n");
      out.write("\t\t event.returnValue = true;\r\n");
      out.write("\t\t  \r\n");
      out.write("\t\t for (var i=0;i<objEv.value.length;i++)\r\n");
      out.write("\t\t {\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t if(-1 != num.indexOf(objEv.value.charAt(i)))\r\n");
      out.write("\t\t event.returnValue = false;\r\n");
      out.write("\t\t }\r\n");
      out.write("\t\t  \r\n");
      out.write("\t\t if (!event.returnValue)\r\n");
      out.write("\t\t {\r\n");
      out.write("\t\t  alert(\"해당 문자는 입력하실 수 없습니다.\");\r\n");
      out.write("\t\t  objEv.value=\"\";\r\n");
      out.write("\t\t }\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t</script>\r\n");
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
