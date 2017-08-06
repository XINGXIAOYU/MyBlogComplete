package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import information.User;

public final class aboutMe_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" \n");
      out.write("              href=\"css/style4.css\"/>\n");
      out.write("        <script src=\"js/jquery.js\"></script>\n");
      out.write("        <script src=\"js/checkInfo.js\"></script>  \n");
      out.write("    </head>\n");
      out.write("    <body class=\"aboutMe\">\n");
      out.write("        ");

            User user = (User) session.getAttribute("user");
            if (user != null && user.getId() == (int) session.getAttribute("user_id")) {
        
      out.write("\n");
      out.write("        <div class=\"wrapper\">\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <div class=\"menu\"> \n");
      out.write("                    <!--the nav of the blog-->\n");
      out.write("                    <ul>\n");
      out.write("                        <li id=\"blog_main\"><a href=\"main.jsp\">首页</a> </li>\n");
      out.write("                        <li id=\"blog_list\" ><a href=\"blog_list.jsp\">博文目录</a> </li>\n");
      out.write("                        <li id=\"image\" ><a href=\"\">图片</a> </li>\n");
      out.write("                        <li id=\"aboutMe\"><a href=\"\">关于我</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"head\">\n");
      out.write("                <h1>");
      out.print(session.getAttribute("name"));
      out.write("的博客</h1>\n");
      out.write("                <h2>http://blog.");
      out.print(session.getAttribute("idName"));
      out.write(".com</h2>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"content\">\n");
      out.write("                <h3>个人资料</h3>\n");
      out.write("                <form id=\"myForm2\" action=\"UploadServlet\" method=\"post\" enctype=\"multipart/form-data\">\n");
      out.write("                    <div id=\"hh\">\n");
      out.write("                        <div id=\"preview\">\n");
      out.write("                            <img  id=\"imghead\" src=\"image/defaultphoto.jpeg\" alt=\"\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <input type=\"file\" onchange=\"previewImage(this)\"/> \n");
      out.write("                    </div>\n");
      out.write("                </form>\n");
      out.write("                <form id=\"myForm\" action=\"PersonServlet\" method=\"post\">             \n");
      out.write("                    <div class=\"article_content_info\">\n");
      out.write("                        <h4 class = \"mtitle\">昵称</h4>\n");
      out.write("                        <input type = \"text\" name = \"cute\" id=\"cute\" value=\"\"/>\n");
      out.write("                        <h4 class = \"mtitle\">密码</h4>\n");
      out.write("                        <input type = \"password\" name = \"pw\" id=\"pw\" value=\"\"/>\n");
      out.write("                        <h4 class = \"mtitle\">确认密码</h4>\n");
      out.write("                        <input type = \"password\" name = \"rpw\" id=\"rpw\" value=\"\"/>\n");
      out.write("\n");
      out.write("                        <div class=\"clear\"></div>\n");
      out.write("                    </div>                      \n");
      out.write("                    <div id=\"ok\">\n");
      out.write("                        <span>\n");
      out.write("                            ");
      out.print("<a onclick='checkAll(\"");
      out.print(user.getPassword());
      out.print("\")'>确认修改</a>");
      out.write("\n");
      out.write("                        </span>\n");
      out.write("                    </div>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        ");

            } else {
                request.getRequestDispatcher("main.jsp").forward(request, response);
            }
        
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
