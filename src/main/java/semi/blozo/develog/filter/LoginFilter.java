package semi.blozo.develog.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.blozo.develog.admin.model.vo.Member;


@WebFilter(filterName = "loginFilter", urlPatterns = "/admin/*" )
public class LoginFilter implements Filter {

  
    public void init(FilterConfig fConfig) throws ServletException {
    }


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		
		String command = uri.substring( (contextPath + "/admin/").length());
		if(command.equals("member")     ||
		   command.equals("statistics") ||		
		   command.equals("enquiry")    ||		
		   command.equals("report")     ||		
		   command.equals("post")		||
		   command.equals("logout")
		) {
			HttpSession session = req.getSession();
			Member admin = (Member)session.getAttribute("admin");
			if(admin == null){
				String message = "관리자 로그인이 필요합니다.";
				session.setAttribute("message", message);
				resp.sendRedirect(req.getContextPath() + "/admin/login");
			}else {
				chain.doFilter(request, response); // 관리자면 다시 보내야지
			}
		}else {
			chain.doFilter(request, response);
		}
		
		
	}

	public void destroy() {
	}

}
