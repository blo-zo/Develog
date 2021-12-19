package semi.blozo.develog.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

<<<<<<< HEAD
	// * : 모든
 	// /* : 모든페이지
=======

>>>>>>> dcf32d051412d6a89fe8c14660b8918114751257
@WebFilter(filterName = "encodingFilter", urlPatterns = "/*" )
public class EncodingFilter implements Filter {

  
    public void init(FilterConfig fConfig) throws ServletException {
    }


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//모든 요청을 UTF-8 로변경
		request.setCharacterEncoding("UTF-8");
		//모든 응답을 UTF-8 로변경
		response.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}

	public void destroy() {
	}

}
