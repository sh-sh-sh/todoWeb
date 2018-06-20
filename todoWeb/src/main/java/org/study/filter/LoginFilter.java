package org.study.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.study.dao.UserDaoImpl;
import org.study.dao.UserService;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {
	private UserService service = new UserDaoImpl();
	FilterConfig config;
	String[] excludedUrls;

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		StringBuffer path=new StringBuffer(req.getRequestURI());
		
		if(req.getQueryString()!=null) {
			path.append("?").append(req.getQueryString());
		}
		
		if (path.toString().startsWith("/webjars") //웹 페이지가 아닌 경우 넘긴다
				||path.toString().startsWith("/css") 
				|| path.toString().startsWith("/font") 
				|| path.toString().startsWith("/js")) {
			chain.doFilter(request, response);
			
			return;
		}
		
		for (String url : excludedUrls) {//web.xml에 등록한 값을 돌며 일치하는 경우 넘긴다
			if (path.toString().equals(url)) {
				chain.doFilter(request, response);
				
				return;
			}
		}
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		
		if(session.getAttribute("userid") !=null) {//이미 로그인 되어 있는 경우
			if(!service.isValidUser((String)(session.getAttribute("userid")))) {//유효한 아이디가 아니면 로그아웃시킨다
				session.invalidate();
				request.setAttribute("error", "유효하지 않는 아이디로 접속되어 있습니다. 다시 로그인하세요.");
				request.setAttribute("orgPath", path.toString());
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}//유효한 아이디일 경우 넘어감
			chain.doFilter(request, response);
		}else {//로그인해야 할 경우 로그인 페이지로 이동
			request.setAttribute("error", "먼저 로그인하셔야 합니다.");
			request.setAttribute("orgPath", path.toString());
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.config =fConfig;
		String s=config.getInitParameter("excluded");
		excludedUrls=s.split(",");
		
		for(int i=0;i<excludedUrls.length; i++) {
			excludedUrls[i] = excludedUrls[i].trim();
		}
	}

}
