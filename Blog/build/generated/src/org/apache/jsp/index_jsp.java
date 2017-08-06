package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE HTML>\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Home</title>\n");
      out.write("        <!-- Custom Theme files -->\n");
      out.write("        <link href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\"/>\n");
      out.write("        <!-- Custom Theme files -->\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\">\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> \n");
      out.write("        <meta name=\"keywords\" content=\"Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design\" />\n");
      out.write("        <!--Google Fonts-->\n");
      out.write("        <link href='http://fonts.useso.com/css?family=Roboto:500,900italic,900,400italic,100,700italic,300,700,500italic,100italic,300italic,400' rel='stylesheet' type='text/css'>\n");
      out.write("        <link href='http://fonts.useso.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>\n");
      out.write("        <!--Google Fonts-->\n");
      out.write("        <link href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\"/>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <fieldset class=\"login\">\n");
      out.write("            <h2>Acced Form</h2>\n");
      out.write("            <div class=\"login-top\">\n");
      out.write("                <h1>LOGIN FORM</h1>\n");
      out.write("                <form action=\"LoginServlet\" id=\"myForm1\" method=\"post\">\n");
      out.write("                    <input type=\"text\" value=\"\" name=\"idName\" id=\"idName\" placeholder=\"user id\">\n");
      out.write("                    <input type=\"password\" value=\"\" name=\"password\" id=\"password\" placeholder=\"password\">\n");
      out.write("                </form>\n");
      out.write("                <div class=\"deal\">\n");
      out.write("                    <input type=\"submit\" value=\"Login\" id=\"login\">\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"login-bottom\">\n");
      out.write("                <h3>New User &nbsp;<a href=\"#\">Register</a>&nbsp Here</h3>\n");
      out.write("            </div>\n");
      out.write("        </fieldset>\n");
      out.write("        <fieldset class=\"register\">\n");
      out.write("            <div class=\"register-top\">\n");
      out.write("                <h1>REGISTER FORM</h1>\n");
      out.write("                <form action=\"RegServlet\" id=\"myForm2\">\n");
      out.write("                    <input type=\"text\" value=\"\" name=\"regidName\" id=\"regidName\" placeholder=\"user id\">\n");
      out.write("                    <input type=\"text\" value=\"\" name=\"name\" id=\"name\" placeholder=\"nickname\">\n");
      out.write("                    <input type=\"password\" value=\"\" name=\"regpassword\" id=\"regpassword\" placeholder=\"password\">\n");
      out.write("                    <input type=\"password\" value=\"\" name=\"rpassword\" id=\"rpassword\" placeholder=\"validate password\">\n");
      out.write("                </form>\n");
      out.write("                <div class=\"deal\">\n");
      out.write("                    <input type=\"submit\" value=\"DONE\" name=\"ok\" id=\"ok\">\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"login-bottom\">\n");
      out.write("                <h3>I wanna go &nbsp;<a href=\"#\">BACK</a>&nbsp</h3>\n");
      out.write("            </div>\n");
      out.write("        </fieldset>\t\n");
      out.write("        <script src=\"js/checklogin.js\" type=\"text/javascript\" charset=\"utf-8\" ></script>\n");
      out.write("    </body>\n");
      out.write("</html>");
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
