/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-06-11 05:46:14 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import org.study.model.Todo;
import org.study.dao.TodoDaoImpl;
import org.study.dao.TodoService;

public final class TodoList_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>TODOLIST</title>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/link.jsp", out, false);
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<script src=\"/webjars/momentjs/2.22.1/moment.js\"></script>\r\n");
      out.write("<script src=\"/webjars/jquery/2.1.4/jquery.min.js\"></script>\r\n");
      out.write("<script src=\"/webjars/bootstrap/3.3.5/js/bootstrap.min.js\"></script>\r\n");
      out.write("<script src=\"/js/footable.min.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/webjars/bootstrap/3.3.5/css/bootstrap.min.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/../css/theme.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/../css/index.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/css/footable.bootstrap.min.css\" />\r\n");
      out.write("<style>\r\n");
      out.write(".pageidx{padding: 5px;}\r\n");
      out.write("div {text-align:center}\r\n");
      out.write(".midd{\r\n");
      out.write("max-width:400px;\r\n");
      out.write("margin:10px auto 0 auto;\r\n");
      out.write("text-align: center;\r\n");
      out.write("}\r\n");
      out.write(".haha{\r\n");
      out.write("margin:auto;\r\n");
      out.write("max-width:600px;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
 TodoService service = new TodoDaoImpl();
List<Todo> list=(List<Todo>)request.getAttribute("list");
String view=request.getParameter("view");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"container3\">\r\n");
      out.write("\t<div class=\"starter-template\">\r\n");
      out.write("\t\t<h1>");
      out.print((String)(request.getAttribute("title")) );
      out.write("</h1>\r\n");
      out.write("\t\t<font color=\"red\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${error}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</font>\r\n");
      out.write("\t\t<font color=\"blue\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${msg}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</font>\r\n");
      out.write("\t");
if(!view.equals("done")&&!view.equals("undone")){ 
      out.write("\r\n");
      out.write("\t\t<div class=\"haha\">\r\n");
      out.write("\t\t<div class=\"progress\">\r\n");
      out.write("  <div class=\"progress-bar progress-bar-striped active\" role=\"progressbar\" style=\"width:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${doneRate}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("%\">\r\n");
      out.write("    <span class=\"sr-only\"></span>\r\n");
      out.write("  </div>\r\n");
      out.write("  </div>\r\n");
      out.write("</div>\r\n");
} 
      out.write("\r\n");
      out.write("\t\t<div class=\"haha\">\r\n");
      out.write("\t\t<table class=\"table table-bordered\">\r\n");
      out.write("\t\t\t<tr class=\"cols123\">\r\n");
      out.write("\t\t\t\t<th>분류</th><th class=\"footable-first-visible\">제목</th>\r\n");
      out.write("\t\t\t\t<th class=\"tha\" data-breakpoints=\"xs\">시작일</th>\r\n");
      out.write("\t\t\t\t<th class=\"tha\" data-breakpoints=\"xs\">종료일</th><th width=46px>달성</th>\r\n");
      out.write("\t\t\t\t<th data-breakpoints=\"xs\">버튼</th>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t");
 
			 for(int i=0;i<list.size();i++){
			 Todo todo=list.get(i);
			 if(todo.getTitle().length()>10){
				 todo.setTitle(todo.getTitle().substring(0, 9)+"…");
			 }
			 
      out.write("\r\n");
      out.write("\t\t\t<tr class=\"row123\">\r\n");
      out.write("\t\t\t\t<td><span class=\"label label-default\">");
      out.print(todo.getCateName());
      out.write("</span></td><td><a href=\"Todo.do?idx=");
      out.print(todo.getIdx());
      out.write('"');
      out.write('>');
      out.print(todo.getTitle());
      out.write("</a></td>\r\n");
      out.write("\t\t\t\t<td>");
      out.print(todo.getStart_date_listver());
      out.write("</td><td>");
      out.print(todo.getTarget_date_listver());
      out.write("</td>\r\n");
      out.write("\t\t\t\t");
 String done;
					if(todo.isDone()){
						done="O";
					}else{
						if(todo.getPast()){
							done="지남";
						}else{
							done="X";
						}
					}
				
      out.write("\r\n");
      out.write("\t\t\t\t<td>");
      out.print(done );
      out.write("</td>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t");
if(!todo.isDone()){ 
      out.write("\r\n");
      out.write("\t\t\t\t<a href=\"TodoDone.do?idx=");
      out.print(todo.getIdx());
      out.write("&done=1\"><input type=\"button\" value=\"완료\" class=\"btn btn-xs btn-success\"></a>\r\n");
      out.write("\t\t\t\t");
}else{ 
      out.write("\r\n");
      out.write("\t\t\t\t<a href=\"TodoDone.do?idx=");
      out.print(todo.getIdx());
      out.write("&done=0\"><input type=\"button\" value=\"취소\" class=\"btn btn-xs btn-warning\"></a>\r\n");
      out.write("\t\t\t\t");
} 
      out.write("\r\n");
      out.write("\t\t\t\t&nbsp;&nbsp;<a href=\"TodoMod.do?idx=");
      out.print(todo.getIdx());
      out.write("\"><input type=\"button\" value=\"수정\" class=\"btn btn-xs btn-info\"></a>\r\n");
      out.write("\t\t\t\t&nbsp;&nbsp;<a href=\"TodoDel.do?idx=");
      out.print(todo.getIdx());
      out.write("\"><input type=\"button\" value=\"삭제\" class=\"btn btn-xs btn-danger\"></a></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t");
} 
      out.write("\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("<nav aria-label=\"Page navigation\">\r\n");
      out.write("  <ul class=\"pagination justify-content-center pagination-sm\">\r\n");
 
int a=Integer.parseInt(request.getParameter("page"));
int maxpage=(Integer)(request.getAttribute("maxpage"));
int pagestart=1+((a-1)/10)*10;
if(pagestart!=1){
      out.write("\r\n");
      out.write("    <li class=\"page-item\">\r\n");
      out.write("      <a class=\"page-link\" href=\"/TodoList.do?page=");
      out.print(pagestart-1);
      out.write("&view=");
      out.print(view);
      out.write("\" aria-label=\"Previous\">\r\n");
      out.write("        <span aria-hidden=\"true\">&laquo;</span>\r\n");
      out.write("        <span class=\"sr-only\">Previous</span>\r\n");
      out.write("      </a></li>\r\n");
      out.write("\t");
}
for(int i=pagestart;i<pagestart+10&&i<=maxpage;i++){ 
	if(a==i){
      out.write("\r\n");
      out.write("    <li class=\"page-item active\"><a class=\"page-link\" href=\"/TodoList.do?page=");
      out.print(i);
      out.write("&view=");
      out.print(view);
      out.write('"');
      out.write('>');
      out.print(i);
      out.write("<span class=\"sr-only\">(current)</span></a></li>\r\n");
      out.write("    ");
}else{ 
      out.write("\r\n");
      out.write("    <li class=\"page-item\"><a class=\"page-link\" href=\"/TodoList.do?page=");
      out.print(i);
      out.write("&view=");
      out.print(view);
      out.write('"');
      out.write('>');
      out.print(i);
      out.write("</a></li>\r\n");
}} 
if(pagestart+10<=maxpage){
      out.write("\r\n");
      out.write("    <li class=\"page-item\">\r\n");
      out.write("      <a class=\"page-link\" href=\"/TodoList.do?page=");
      out.print(pagestart+10);
      out.write("&view=");
      out.print(view);
      out.write("\" aria-label=\"Next\">\r\n");
      out.write("        <span aria-hidden=\"true\">&raquo;</span>\r\n");
      out.write("        <span class=\"sr-only\">Next</span>\r\n");
      out.write("      </a></li>\r\n");
} 
      out.write("\r\n");
      out.write("  </ul>\r\n");
      out.write("</nav>\r\n");
      out.write("<br>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div><!-- /.container -->\r\n");
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("    jQuery(function($){\r\n");
      out.write("    \t$('.table').footable({\r\n");
      out.write("    \t\t\"cascade\": true\r\n");
      out.write("    \t});\r\n");
      out.write("    });\r\n");
      out.write("    </script>\r\n");
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
