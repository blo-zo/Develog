/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.73
 * Generated at: 2021-12-22 07:25:44 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class posting_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"ko\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("<title>posting</title>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"../resources/css/posting.css\">\r\n");
      out.write("	<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\r\n");
      out.write("   \r\n");
      out.write("    <!-- include libraries(jQuery, bootstrap) --> \r\n");
      out.write("    <link href=\"http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css\" rel=\"stylesheet\"> \r\n");
      out.write("    <script src=\"http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js\"></script>\r\n");
      out.write("    <script src=\"http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js\"></script>\r\n");
      out.write("     \r\n");
      out.write("     <!-- include summernote css/js-->\r\n");
      out.write("    <link href=\"http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css\" rel=\"stylesheet\"> \r\n");
      out.write("    <script src=\"http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js\"></script>\r\n");
      out.write("   <link rel=\"stylesheet\" href=\"/css/summernote/summernote-lite.css\"> \r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("	<form action=\"insert\" name=\"insertForm\" method=\"post\"  role=\"form\" onsubmit=\"return false\">\r\n");
      out.write("	<div class=\"wrapper\">\r\n");
      out.write("		<div class=\"write-area\" style=\"max-width: 80%;\">\r\n");
      out.write("			<div class=\"head-title\">\r\n");
      out.write("				<textarea id=\"head-textarea\" class=\"head-textarea\" name=\"postTitle\" placeholder=\"제목을 입력하세요\" maxlength=\"200\" ></textarea>\r\n");
      out.write("			</div><!-- head title -->\r\n");
      out.write("\r\n");
      out.write("			<div class=\"line\"></div>\r\n");
      out.write("\r\n");
      out.write("			<textarea class=\"summernote\" id=\"summernote\" name=\"postContent\" style=\" height: fit-content;\"></textarea>\r\n");
      out.write("			<!-- content end -->\r\n");
      out.write("\r\n");
      out.write("			<div class=\"tag-area\">\r\n");
      out.write("                <div class=\"postTags\" id=\"postTags\"></div>  <!-- 태그 생성될 영역 -->\r\n");
      out.write("                <input type=\"text\" class=\"inputTag\" id=\"inputTag\" placeholder=\"태그를 입력하세요\" maxlength=\"15\" >\r\n");
      out.write("            </div> <!-- tag-area -->\r\n");
      out.write("            \r\n");
      out.write("		</div>\r\n");
      out.write("		<!-- write area -->\r\n");
      out.write("\r\n");
      out.write("		<footer>\r\n");
      out.write("            <button class=\"out-area\" onclick=\"location.href='# 목록페이지' \">\r\n");
      out.write("                <div id=\"out-image\">\r\n");
      out.write("                    <img src=\"../resources/images/board/arrow.png\" id=\"img-arrow\" >\r\n");
      out.write("                </div>\r\n");
      out.write("                 <span id=\"out-span\" >나가기</span>\r\n");
      out.write("            </button> <!-- /out-area -->\r\n");
      out.write("            \r\n");
      out.write("            <div class=\"btn-area\" >\r\n");
      out.write("                <button class=\"btn-pre-submit\" id=\"btn-pre-submit\" type=\"button\"> \r\n");
      out.write("                    <a style=\" color: white; text-decoration : none;\">출간하기</a>\r\n");
      out.write("                </button>\r\n");
      out.write("            </div>    \r\n");
      out.write("    \r\n");
      out.write("        </footer>\r\n");
      out.write("	</div><!-- wrapper -->\r\n");
      out.write("\r\n");
      out.write("	<!-- \r\n");
      out.write("        지금은 보이고 안보이고 정도만 작성\r\n");
      out.write("        나중에 움직이는걸로 바꾸고 싶으면 transform 했던거 찾으면 된다 \r\n");
      out.write("    -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("	<!-- modal slide up -->\r\n");
      out.write("	<div class=\"modal-content-area\">\r\n");
      out.write("		<div class=\"modal-content-box\">\r\n");
      out.write("\r\n");
      out.write("			<div class=\"thumbnail-area\">\r\n");
      out.write("				<div class=\"thumbnail-title\">\r\n");
      out.write("					<h4>썸네일 설정</h4>\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"thumb-img\">\r\n");
      out.write("					<img src=\"https://via.placeholder.com/350x200\" alt=\"샘플이미지\">\r\n");
      out.write("				</div>\r\n");
      out.write("				\r\n");
      out.write("			</div> <!-- /thumbnail-area -->\r\n");
      out.write("\r\n");
      out.write("			<div class=\"modalLine\"></div>\r\n");
      out.write("\r\n");
      out.write("			<div class=\"set-area\">\r\n");
      out.write("				<div class=\"open\">\r\n");
      out.write("\r\n");
      out.write("					<div class=\"open-title\">\r\n");
      out.write("						<h4>공개 설정</h4>\r\n");
      out.write("					</div>\r\n");
      out.write("					<div class=\"open-btns\">\r\n");
      out.write("						<button class=\"all-btn postStatusBtn\" value=\"500\" type=\"button\">\r\n");
      out.write("							<img src=\"../resources/images/board/earth.png\" class=\"img-earth\" alt=\"\">\r\n");
      out.write("							<p>전체 공개</p>\r\n");
      out.write("						</button>\r\n");
      out.write("						<button class=\"lock-btn postStatusBtn\" value=\"502\" type=\"button\">\r\n");
      out.write("							<img src=\"../resources/images/board/padlock.png\" class=\"img-lock\" alt=\"\">\r\n");
      out.write("							<p>비공개</p>\r\n");
      out.write("						</button>\r\n");
      out.write("						<!-- 상태코드 넘어가는 input -->\r\n");
      out.write("						<input type=\"hidden\" name=\"postStatusCode\" >\r\n");
      out.write("					</div>\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"category\">\r\n");
      out.write("					<div class=\"category-title\">\r\n");
      out.write("						<h4>카테고리 설정</h4>\r\n");
      out.write("					</div>\r\n");
      out.write("					<div class=\"category-input\">\r\n");
      out.write("						<div class=\"sort-post dropstart border\" data-bs-toggle=\"dropdown\"\r\n");
      out.write("							aria-expanded=\"false\">\r\n");
      out.write("							<select name=\"categoryCode\">\r\n");
      out.write("								<option value=\"\">선택하세요</option>\r\n");
      out.write("								<option value=\"1\">일상</option>\r\n");
      out.write("								<option value=\"2\">영어</option>\r\n");
      out.write("							</select>\r\n");
      out.write("							<!-- <button type=\"button\" class=\"btn dropdown-toggle\"\r\n");
      out.write("								data-bs-toggle=\"dropdown\" aria-expanded=\"false\">정렬 방식</button>\r\n");
      out.write("							<ul class=\"dropdown-menu\">\r\n");
      out.write("								<li><a class=\"dropdown-item\" href=\"#\">날짜(오름차순)</a></li>\r\n");
      out.write("								<li><a class=\"dropdown-item\" href=\"#\">날짜(내림차순)</a></li>\r\n");
      out.write("							</ul> -->\r\n");
      out.write("						</div>\r\n");
      out.write("					</div>\r\n");
      out.write("\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"set-btns\">\r\n");
      out.write("					<button class=\"btn-cancel\" >취소</button>\r\n");
      out.write("					<button class=\"btn-submit\" onclick=\"postValidate();\">출간하기</button>\r\n");
      out.write("				</div>\r\n");
      out.write("\r\n");
      out.write("			</div>\r\n");
      out.write("\r\n");
      out.write("		</div>\r\n");
      out.write("	</div>\r\n");
      out.write("</form>\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write(" <link href=\"https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css\" rel=\"stylesheet\"> \r\n");
      out.write("<script src=\"https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js\"></script>\r\n");
      out.write("<sript src=\" https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js\"></script>\r\n");
      out.write("<script src=\"https://unpkg.com/js-offcanvas@1.2.8/dist/_js/js-offcanvas.pkgd.min.js\"></script> \r\n");
      out.write("<link href=\"https://unpkg.com/js-offcanvas@1.2.8/dist/_css/prefixed/js-offcanvas.css\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("<script src=\"../resources/js/posting.js\"></script>\r\n");
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
