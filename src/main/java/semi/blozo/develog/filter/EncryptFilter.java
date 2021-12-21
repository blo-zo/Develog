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

import semi.blozo.develog.wrapper.EncryptWrapper;

@WebFilter(filterName = "encryptFilter" , 
urlPatterns = {"/member/login" , "/member/signup", "/member/searchpw" , "/member/updatepw", "/admin/login/try"})
public class EncryptFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
	System.out.println("벨로그 필터 생성");
	}
   
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		//HttpServletResponse resp = (HttpServletResponse)response;
		
		//필터링 확인하기
		//System.out.println(req.getParameter("memberPw"));
		if(req.getMethod().equals("POST")) {
			EncryptWrapper encWrapper = new EncryptWrapper(req);
			
			chain.doFilter(encWrapper, response);
		}else {
			
			chain.doFilter(request, response);
		}
		
		
	}
	public void destroy() {
		System.out.println("벨로그필터 제거! 재생성");
	}



}
