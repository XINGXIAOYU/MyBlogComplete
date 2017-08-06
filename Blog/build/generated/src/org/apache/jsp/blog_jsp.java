package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import operation.EssayAct;
import information.EssayClass;
import information.Comment;
import information.Essay;
import operation.UserAct;
import information.User;
import operation.ConnectDB;
import java.sql.*;
import java.util.*;

public final class blog_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Blog</title>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" \n");
      out.write("              href=\"css/style4.css\"/>\n");
      out.write("        <script src=\"js/jquery.js\"></script>\n");
      out.write("        <script src=\"js/dianzan.js\"></script>\n");
      out.write("        <script src=\"js/comment.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body class=\"blog\">\n");
      out.write("        ");
  User user = (User) session.getAttribute("user");
            if (user != null || request.getParameter("essay_id") != null) {
      out.write("\n");
      out.write("        <div class=\"wrapper\">\n");
      out.write("            <div id=\"to-top\"></div>\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <div class=\"menu\"> \n");
      out.write("                    <!--the nav of the blog-->\n");
      out.write("                    <ul>\n");
      out.write("                        <li id=\"blog_main\" class=\"active\"><a href=\"main.jsp\">首页</a> </li>\n");
      out.write("                        <li id=\"blog_list\" ><a href=\"blog_list.jsp\">博文目录</a> </li>\n");
      out.write("                        <li id=\"image\" ><a href=\"\">图片</a> </li>\n");
      out.write("                        <li id=\"aboutMe\"><a href=\"\">关于我</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div class=\"head\">\n");
      out.write("                ");

                    int essay_id = Integer.parseInt(request.getParameter("essay_id"));
                    EssayAct act = new EssayAct();
                    User author = new User();
                    author = act.getAuthor(essay_id);
                
      out.write("\n");
      out.write("                <h1>");
      out.print(author.getName());
      out.write("的博客</h1>\n");
      out.write("                <h2>http://blog.");
      out.print(author.getIdName());
      out.write(".com</h2>\n");
      out.write("            </div>\n");
      out.write("            ");

                ArrayList<Essay> list = (ArrayList<Essay>) author.getEssay();
                for (int i = 0; i < list.size(); i++) {
                    if (essay_id == list.get(i).getEssay_id()) {
                        Integer read = list.get(i).getRead();
                        if (user == null || user.getIdName().equals(author.getIdName()) == false) {
                            read++;
                            application.setAttribute("read" + essay_id, read);//累加后再放进去
                        }
                        if ((Integer) application.getAttribute("read" + essay_id) != list.get(i).getRead()) {
                            act.savereading(essay_id, read);
                        }
                        list.get(i).setRead(read);
            
      out.write("\n");
      out.write("            <div class=\"content\">\n");
      out.write("                <div class=\"main_content\">\n");
      out.write("                    <div class=\"title\">博文</div>\n");
      out.write("                    <div class=\"article\">\n");
      out.write("                        <div class=\"article_content\">\n");
      out.write("                            <div class=\"article_content_head\">\n");
      out.write("                                <h3 class=\"articleName\">");
      out.print(list.get(i).getEname());
      out.write("</h3>\n");
      out.write("                                <h4 class=\"time\">");
      out.print(list.get(i).getPbtime() + ",Posted by " + list.get(i).getAuthorName());
      out.write("</h4>\n");
      out.write("                                ");
if (list.get(i).getTags().size() != 0) {
      out.write("\n");
      out.write("                                <h4 class='label'>标签:\n");
      out.write("                                    ");
for (int j = 0; j < list.get(i).getTags().size(); j++) {
      out.write("\n");
      out.write("                                    ");
      out.print(list.get(i).getTags().get(j));
      out.write("\n");
      out.write("                                    ");
}
      out.write("\n");
      out.write("                                    分类:\n");
      out.write("                                    ");
      out.print(list.get(i).getClassName());
      out.write("\n");
      out.write("                                </h4>\n");
      out.write("\n");
      out.write("                                ");
}
      out.write("\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"article_content_info\">\n");
      out.write("                                <p>");
      out.print(list.get(i).getContent());
      out.write("</p>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"footer\">\n");
      out.write("                                <span class=\"read\">阅读量：</span>\n");
      out.write("                                <span class=\"rnum\">");
      out.print(list.get(i).getRead());
      out.write("</span>\n");
      out.write("                                ");
if (session.getAttribute("user") != null) {
      out.write("\n");
      out.write("                                <span class=\"like\">");
      out.print("<a onclick='goodplus(");
      out.print(list.get(i).getEssay_id() + ")'>");
      out.write('赞');
      out.print("</a></span>");
      out.write("\n");
      out.write("                                    ");
} else {
      out.write("\n");
      out.write("                                    <span id=\"no\"><a>赞：</a></span>\n");
      out.write("                                    ");
}
      out.write("\n");
      out.write("                                    <span class=\"num\">");
      out.print(list.get(i).getLike());
      out.write("</span>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        ");
if (user != null) {
      out.write("\n");
      out.write("                        <div class=\"content-form\">\n");
      out.write("                            <h3 class=\"mtitle\" id=\"pinglun\">Leave A Comment</h3>\n");
      out.write("                            <form>\n");
      out.write("                                <textarea id=\"textarea\" placeholder=\"Message\"></textarea>\n");
      out.write("                                ");
      out.print("<input type='button' value='SEND' onclick ='comment(");
      out.print(essay_id + "," + user.getId());
      out.print(")'>");
      out.write("\n");
      out.write("                            </form>\n");
      out.write("                        </div>\n");
      out.write("                        ");
}
      out.write("\n");
      out.write("\n");
      out.write("                        <div class=\"comments\">\n");
      out.write("                            <h3>Comment</h3>\n");
      out.write("                            <div class=\"comment-grid\">\n");
      out.write("                                ");

                                    ArrayList<Comment> comment = list.get(i).getComment();
                                
      out.write("\n");
      out.write("                                ");
if (comment.size() != 0) {
      out.write("\n");
      out.write("                                ");
for (int m = 0; m < comment.size(); m++) {
      out.write("\n");
      out.write("                                \n");
      out.write("                                <div class=\"comment-info\">\n");
      out.write("                                    <img src=\"");
      out.print(comment.get(m).getAuthor(comment.get(m).getUser_id()).getPhoto());
      out.write("\" alt=\"\"/>\n");
      out.write("                                    <h4>");
      out.print(comment.get(m).getAuthor(comment.get(m).getUser_id()).getName());
      out.write("</h4>\n");
      out.write("                                    <p>");
      out.print(comment.get(m).getContent());
      out.write("</p>\n");
      out.write("                                    <h5>");
      out.print(comment.get(m).getTime());
      out.write("</h5>\n");
      out.write("                                    ");
if((user!=null&&comment.get(m).getAuthor(comment.get(m).getUser_id()).getIdName().equals(user.getIdName()))||user.getIdName().equals(author.getIdName())){
      out.write("\n");
      out.write("                                    <span class='delete'>");
      out.print("<a onclick ='deleteC(");
      out.print(comment.get(m).getComment_id()+ ","+essay_id);
      out.print(")'>");
      out.write('删');
      out.write('除');
      out.print("</a>");
      out.write("</span>\n");
      out.write("                                    ");
}
      out.write("\n");
      out.write("                                </div>\n");
      out.write("                                ");
}
                                
                                }
      out.write(" <div class=\"clear\"></div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"more\">\n");
      out.write("                        ");
if (i != 0) {
      out.write("\n");
      out.write("                        ");
      out.print("<span id='b'><a href='blog.jsp?essay_id=");
      out.print(list.get(i - 1).getEssay_id());
      out.print("'>前一篇</a></span>");
      out.write("\n");
      out.write("                        ");
}
      out.write("\n");
      out.write("                        ");
if (i != list.size() - 1) {
      out.write("\n");
      out.write("                        ");
      out.print("<span id='a'><a href='blog.jsp?essay_id=");
      out.print(list.get(i + 1).getEssay_id());
      out.print("'>后一篇</a></span>");
      out.write("\n");
      out.write("                        ");
}
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                ");
}
                    }
      out.write("\n");
      out.write("                <div class=\"main_content_right\">\n");
      out.write("                    <div class=\"information\"> \n");
      out.write("                        <div class=\"title\">个人资料</div>\n");
      out.write("                        <div> <img id = \"photo\" src=\"");
      out.print(author.getPhoto());
      out.write("\" alt=\"picture is not available\"/>\n");
      out.write("                            <div id=\"bloggerName\">");
      out.print(author.getName());
      out.write("</div>\n");
      out.write("                        </div>\n");
      out.write("                        <div id=\"introduction\">");
      out.print(author.getIntroduction());
      out.write("</div>\n");
      out.write("                    </div>\n");
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
      out.write("            ");
} else {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
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
