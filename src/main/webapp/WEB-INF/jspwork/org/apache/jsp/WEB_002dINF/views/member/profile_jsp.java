/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.73
 * Generated at: 2021-12-23 03:50:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class profile_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(3);
    _jspx_dependants.put("/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar", Long.valueOf(1639882520855L));
    _jspx_dependants.put("jar:file:/C:/workspace/Servlet_JSP/Develog/src/main/webapp/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar!/META-INF/fn.tld", Long.valueOf(1425946270000L));
    _jspx_dependants.put("jar:file:/C:/workspace/Servlet_JSP/Develog/src/main/webapp/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar!/META-INF/c.tld", Long.valueOf(1425946270000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

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
      out.write("<link rel=\"stylesheet\" href=\"../resources/css/profile.css\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("	<form action=\"profile\" method=\"POST\" name=\"profileForm\" onsubmit=\"return validate();\">\r\n");
      out.write("		<div class=\"category\">PROFILE</div>\r\n");
      out.write("	    \r\n");
      out.write("	    <main class=\"main\"> \r\n");
      out.write("	       \r\n");
      out.write("	        <div class=\"tab-area\">\r\n");
      out.write("	            <div class=\"tab\">프로필</div>  \r\n");
      out.write("	            <div class=\"tab\">소셜 정보</div>  \r\n");
      out.write("	            <div class=\"tab\">이메일</div>  \r\n");
      out.write("	            <div class=\"tab\" style=\"color: darkgray;\">회원탈퇴</div>  \r\n");
      out.write("	        </div>\r\n");
      out.write("	\r\n");
      out.write("	        <div class=\"line\"></div>\r\n");
      out.write("	        \r\n");
      out.write("	         <!-- 프로필 -->\r\n");
      out.write("	        <div class=\"content\" style=\" display: block;\" >\r\n");
      out.write("	            <div class=\"profile\">\r\n");
      out.write("	                <div class=\"p-img\"><img src=\"images/profile-small.png\"></div>\r\n");
      out.write("	                <div class=\"p-btn\">\r\n");
      out.write("	                    <button class=\"p-choose\" id=\"p-choose\" >이미지 업로드</button>\r\n");
      out.write("	                    <button class=\"p-delete\" id=\"p-delete\" >이미지 제거</button>\r\n");
      out.write("	                </div>\r\n");
      out.write("	            </div>\r\n");
      out.write("	\r\n");
      out.write("	            <div class=\"intro\" style=\"position: relative;\">\r\n");
      out.write("	                <div class=\"int-title\" style=\"height: 100; line-height: 88px;\">자기소개</div>\r\n");
      out.write("	                <div class=\"int-input\">\r\n");
      out.write("	                    <input type=\"text\" id=\"nickname\" name=\"nickname\" placeholder=\"닉네임\" >\r\n");
      out.write("	                    <input type=\"text\"  class=\"line-intro \" id=\"line-intro\" name=\"line-intro\" placeholder=\"한 줄 소개\" style=\"margin: 5px 0px 5px 9px;\" >\r\n");
      out.write("	                </div>\r\n");
      out.write("	            </div>\r\n");
      out.write("	\r\n");
      out.write("	            <div class=\"devel-intro\">\r\n");
      out.write("	                <div class=\"d-title\">디벨로그 제목</div>\r\n");
      out.write("	                <div class=\"d-input\">\r\n");
      out.write("	                    <input type=\"text\" id=\"devel-input\" name=\"devel-input\" placeholder=\"한 줄 소개\" >\r\n");
      out.write("	                </div>\r\n");
      out.write("	            </div>\r\n");
      out.write("	\r\n");
      out.write("	            <div class=\"explain\" >\r\n");
      out.write("	                개인 페이지 좌측 상단에 나타나는 페이지 제목입니다.\r\n");
      out.write("	            </div>\r\n");
      out.write("	        </div> <!-- /프로필 -->\r\n");
      out.write("	        \r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("	        <!-- 소셜 정보 -->\r\n");
      out.write("	        <div class=\"content\" style=\"padding: 35px;\">\r\n");
      out.write("	          <!-- email -->\r\n");
      out.write("	          <div class=\"sns-email\" >\r\n");
      out.write("	            <div class=\"email-title\" >\r\n");
      out.write("	              <img class=\"email-img\" id=\"email-img\" src=\"images/email.png\">\r\n");
      out.write("	            </div>\r\n");
      out.write("	            <div class=\"email-input\">\r\n");
      out.write("	                <input type=\"text\"  class=\"line-intro\" id=\"emailContent\" name=\"\" placeholder=\"이메일을 입력해주세요.\" size=35 >\r\n");
      out.write("	            </div>\r\n");
      out.write("	          </div>\r\n");
      out.write("	\r\n");
      out.write("	          <!-- github -->\r\n");
      out.write("	          <div class=\"sns-github\" >\r\n");
      out.write("	            <div class=\"github-title\" >\r\n");
      out.write("	              <img  class=\"github-img\"  id=\"github-img\" src=\"images/github.png\">\r\n");
      out.write("	            </div>\r\n");
      out.write("	            <div class=\"github-input\">\r\n");
      out.write("	                <input type=\"text\"  class=\"line-intro\" id=\"gitContent\" name=\"\" placeholder=\"Github 계정을 입력하세요.\" size=35 >\r\n");
      out.write("	            </div>\r\n");
      out.write("	          </div>\r\n");
      out.write("	\r\n");
      out.write("	          <!-- twitter -->\r\n");
      out.write("	          <div class=\"sns-twitter\" >\r\n");
      out.write("	            <div class=\"twitter-title\" >\r\n");
      out.write("	              <img class=\"twitter-img\" id=\"twitter-img\" src=\"images/twitter.png\" >\r\n");
      out.write("	            </div>\r\n");
      out.write("	            <div class=\"twitter-input\">\r\n");
      out.write("	                <input type=\"text\" class=\"line-intro\"  id=\"twitterContent\" name=\"\" placeholder=\"Twitter 계정을 입력하세요.\" \r\n");
      out.write("	                size=35\r\n");
      out.write("	                >\r\n");
      out.write("	            </div>\r\n");
      out.write("	          </div>\r\n");
      out.write("	\r\n");
      out.write("	          <!-- facebook -->\r\n");
      out.write("	          <div class=\"sns-facebook\">\r\n");
      out.write("	            <div class=\"facebook-title\" >\r\n");
      out.write("	              <img  class=\"facebook-img\"  src=\"images/facebook.png\" \r\n");
      out.write("	              >\r\n");
      out.write("	            </div>\r\n");
      out.write("	            <div class=\"facebook-input\">\r\n");
      out.write("	                <input type=\"text\"  class=\"line-intro\" id=\"facebookContent\" name=\"\" placeholder=\"http://www.facebook.com/\" size=35 >\r\n");
      out.write("	            </div>\r\n");
      out.write("	          </div>\r\n");
      out.write("	\r\n");
      out.write("	          <!-- homepage -->\r\n");
      out.write("	          <div class=\"sns-homepage\">\r\n");
      out.write("	            <div class=\"homepage-title\" >\r\n");
      out.write("	              <img  class=\"homepage-img\" id=\"homepage-img\" src=\"images/home.png\" alt=\"\" >\r\n");
      out.write("	            </div>\r\n");
      out.write("	            <div class=\"homepage-input\">\r\n");
      out.write("	                <input type=\"text\" class=\"line-intro\" id=\"homeContent\" name=\"\" placeholder=\"홈페이지 주소를 입력하세요.\"  size=35 >\r\n");
      out.write("	            </div>\r\n");
      out.write("	          </div> <!-- homepage -->\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("	            <div class=\"explain-sns\" >\r\n");
      out.write("	                개인 페이지 좌측 상단에 나타나는 페이지 제목입니다.\r\n");
      out.write("	            </div> \r\n");
      out.write("	        </div> <!-- /content -->\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("	        <!-- 내 정보 -->\r\n");
      out.write("	        <div class=\"content\"  style=\" padding: 35px;\">\r\n");
      out.write("	          \r\n");
      out.write("	          <div class=\"email-area\">\r\n");
      out.write("	              <div class=\"email-addr-title\">이메일 주소</div>\r\n");
      out.write("	              <div class=\"email-addr\">\r\n");
      out.write("	                  <span class=\"email-span\" id=\"email-span\">sample1234@gmail.com</span>\r\n");
      out.write("	                </div>\r\n");
      out.write("	          </div>\r\n");
      out.write("	\r\n");
      out.write("	          <!-- 뒤에있는 클래스명은 css적용을 위한 클래스명 -->\r\n");
      out.write("	          <div class=\"pw-area devel-intro\"> \r\n");
      out.write("	            <div class=\"pw-title d-title \" >비밀번호 변경</div>\r\n");
      out.write("	            <div class=\"pw-input d-input \">\r\n");
      out.write("	              <div class=\"pw-btn-area \">\r\n");
      out.write("	                <button class=\"pw-btn \" id=\"pw-btn\" onclick= \"location.href \">변경</butt>\r\n");
      out.write("	              </div>\r\n");
      out.write("	            </div>\r\n");
      out.write("	          </div>\r\n");
      out.write("	\r\n");
      out.write("	          <div class=\"explain ex-myInfo\">\r\n");
      out.write("	            회원 인증 또는 시스템에서 발송하는 이메일을 수신하는 주소입니다.\r\n");
      out.write("	          </div> \r\n");
      out.write("	        </div>\r\n");
      out.write("	\r\n");
      out.write("	        <!-- 회원탈퇴 -->\r\n");
      out.write("	        <div class=\"content\" >\r\n");
      out.write("	          <div class=\"secession\">\r\n");
      out.write("	            <textarea class=\"secessionForm\" id=\"\" cols=\"55\" rows=\"6.5\">\r\n");
      out.write("	[회원탈퇴 약관]\r\n");
      out.write("	\r\n");
      out.write("	회원탈퇴 신청 전 안내 사항을 확인 해 주세요.\r\n");
      out.write("	회원탈퇴를 신청하시면 현재 로그인 된 아이디는 사용하실 수 없습니다.\r\n");
      out.write("	회원탈퇴를 하더라도, 서비스 약관 및 개인정보 취급방침 동의하에 따라 일정 기간동안 회원 개인정보를 보관합니다.\r\n");
      out.write("	            </textarea>\r\n");
      out.write("	          </div>\r\n");
      out.write("	\r\n");
      out.write("	          <div class=\"secession-chk\">\r\n");
      out.write("	              <div class=\"form-chk\">\r\n");
      out.write("	                <input type=\"checkbox\" name=\"agree\" id=\"agree\"> \r\n");
      out.write("	                <label class=\"\" for=\"agree\">위 약관에 동의합니다.</label>\r\n");
      out.write("	              </div>\r\n");
      out.write("	          </div>\r\n");
      out.write("	\r\n");
      out.write("	          <div class=\"btn-secession-area\">\r\n");
      out.write("	            <button class=\"btn-secession\">회원 탈퇴</button>\r\n");
      out.write("	          </div>\r\n");
      out.write("	\r\n");
      out.write("	        </div>\r\n");
      out.write("	    </main>\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("	    \r\n");
      out.write("	    <div class=\"footer\">\r\n");
      out.write("	        <div class=\"btn-area\">\r\n");
      out.write("	            <button class=\"btn-submit\">저장</button>\r\n");
      out.write("	        </div>\r\n");
      out.write("	    </div>\r\n");
      out.write("	</form>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    \r\n");
      out.write("    <!-- BOOTSTRAP -->\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("    <!-- JS -->\r\n");
      out.write("    <script src=\"https://code.jquery.com/jquery-3.6.0.min.js\" integrity=\"sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("\r\n");
      out.write("    <script src=\"../resources/js/profile.js\"></script>\r\n");
      out.write("    \r\n");
      out.write("    \r\n");
      out.write("  \r\n");
      out.write(" \r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
