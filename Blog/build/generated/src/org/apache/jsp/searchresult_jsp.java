package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import information.EssayClass;
import information.User;
import information.Essay;
import java.util.*;

public final class searchresult_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Search Result</title>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\"\n");
      out.write("              href=\"css/style4.css\"/>\n");
      out.write("        <script src=\"js/jquery.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"wrapper\">\n");
      out.write("            <div id=\"to-top\"></div>\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <div class=\"menu\"> \n");
      out.write("                    <!--the nav of the blog-->\n");
      out.write("                    <ul>\n");
      out.write("                        <li id=\"blog_main\" ><a href=\"main.jsp\">首页</a> </li>\n");
      out.write("                        <li id=\"blog_list\" class=\"active\"><a href=\"blog_list.jsp\">博文目录</a> </li>\n");
      out.write("                        <li id=\"image\" ><a href=\"\">图片</a> </li>\n");
      out.write("                        <li id=\"aboutMe\"><a href=\"\">关于我</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div class=\"head\">\n");
      out.write("                ");
User user = (User) session.getAttribute("user");
      out.write("\n");
      out.write("                <h1>");
      out.print(session.getAttribute("name"));
      out.write("的博客</h1>\n");
      out.write("                <h2>http://blog.");
      out.print(session.getAttribute("idName"));
      out.write(".com</h2>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"content\">\n");
      out.write("                <div class=\"main_content\">\n");
      out.write("                    <div class=\"title\">搜索结果</div>\n");
      out.write("                    ");

                        ArrayList<Essay> essay = (ArrayList<Essay>) request.getAttribute("result");
                        if (essay.size() == 0) {
                    
      out.write("\n");
      out.write("                    <h3>没有符合搜索条件的博文</h3>\n");
      out.write("                    ");
} else {
      out.write("\n");
      out.write("                    ");
for (int i = 0; i < essay.size(); i++) {
                    
      out.write("\n");
      out.write("                    ");
      out.print("<div class='article' id='");
      out.print(essay.get(i).getEssay_id() + "'>");
      out.write("\n");
      out.write("                    ");
      out.print("<div class='article_content'>");
      out.write("\n");
      out.write("                    ");
      out.print("<div class='article_content_head'>");
      out.write("\n");
      out.write("                    ");
      out.print("<h3 class='articleName'>" + "<a href ='blog.jsp?essay_id=" + essay.get(i).getEssay_id() + "'>" + essay.get(i).getEname() + "</a></h3>");
      out.write("\n");
      out.write("                    ");
      out.print("<h4 class='time'>" + essay.get(i).getPbtime() + ",Posted by " + essay.get(i).getAuthorName() + "</h4>");
      out.write("\n");
      out.write("                    ");
if (essay.get(i).getTags().size() != 0) {
      out.write("\n");
      out.write("                    ");
      out.print("<h4 class='label'>标签:");
      out.write("\n");
      out.write("                    ");
for (int j = 0; j < essay.get(i).getTags().size(); j++) {
      out.write("\n");
      out.write("                    ");
      out.print(essay.get(i).getTags().get(j));
      out.write("\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("                    ");
      out.print("</h4>");
      out.write("\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("                    ");
      out.print("</div>");
      out.write("\n");
      out.write("                    ");
      out.print("</div>");
      out.write("\n");
      out.write("                    ");
      out.print("</div>");
      out.write("\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("                </div>\n");
      out.write("                <div class=\"main_content_right\">\n");
      out.write("                    <div class=\"search\">\n");
      out.write("                        <h3>SEARCH HERE</h3>\n");
      out.write("                        <form action=\"SearchServlet\" method=\"post\">\n");
      out.write("                            <input type=\"text\" value=\"\" name=\"condition\"/>\n");
      out.write("                            <div>\n");
      out.write("                                <input type=\"radio\" checked=\"checked\" name=\"range\" value=\"1\"/> 当前用户\n");
      out.write("                                <input type=\"radio\" name=\"range\" value=\"2\"/> 全站搜索\n");
      out.write("                            </div>\n");
      out.write("                            <div>\n");
      out.write("                                <input type=\"radio\" checked=\"checked\" name=\"keyword\" value=\"1\"/> 标题\n");
      out.write("                                <input type=\"radio\" name=\"keyword\" value=\"2\"/> 正文\n");
      out.write("                                <input type=\"radio\" name=\"keyword\" value=\"3\"/> 标签\n");
      out.write("                            </div>\n");
      out.write("                            <input type=\"submit\" value=\"SEARCH\">\n");
      out.write("                        </form>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <!--foot-->\n");
      out.write("                <div class=\"copywrite\">\n");
      out.write("                    <p>Copyright &copy; 2015 XXX,All Rights ReservedXXX</p>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <a href=\"#to-top\" id=\"toTop\" style=\"display: block;\"> <span id=\"toTopHover\" style=\"opacity: 1;\"></span></a>\n");
      out.write("\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
