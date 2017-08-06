package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class test_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write(" \n");
      out.write("<html> \n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <title>ajax????</title> \n");
      out.write("        <style> \n");
      out.write("            body{ \n");
      out.write("                font-size:12px; \n");
      out.write("            } \n");
      out.write("        </style> \n");
      out.write("    </head> \n");
      out.write("    <body> \n");
      out.write("        ?????????????????????????  \n");
      out.write("        <hr/> \n");
      out.write("        <table border=\"1\"> \n");
      out.write("            <thead><tr><td>????</td><td>????</td></tr></thead> \n");
      out.write("            <tbody id=\"a\"><!--???????tbody--> \n");
      out.write("            </tbody> \n");
      out.write("        </table> \n");
      out.write("\n");
      out.write("        ?????<input id=\"data_id\" type=\"text\" /><br/> \n");
      out.write("        ?????<input id=\"data_content\" type=\"text\" /><br/> \n");
      out.write("\n");
      out.write("        <input type=\"button\" value=\"????\" onclick=\"Edit_Data()\"/> \n");
      out.write("        <span id=\"msgaes\" style=\"color:red\"></span> \n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("            function ajax_xmlhttp() {\n");
      out.write("        //?IE???xmlhttpRequest,???IE5.0?????? \n");
      out.write("                var msXmlhttp = new Array(\"Msxml2.XMLHTTP.5.0\", \"Msxml2.XMLHTTP.4.0\", \"Msxml2.XMLHTTP.3.0\", \"Msxml2.XMLHTTP\", \"Microsoft.XMLHTTP\");\n");
      out.write("                for (var i = 0; i < msXmlhttp.length; i++) {\n");
      out.write("                    try\n");
      out.write("                    {\n");
      out.write("                        _xmlhttp = new ActiveXObject(msXmlhttp[i]);\n");
      out.write("                    }\n");
      out.write("                    catch (e)\n");
      out.write("                    {\n");
      out.write("                        _xmlhttp = null;\n");
      out.write("                    }\n");
      out.write("                } //??????IE????xmlhttp.?? \n");
      out.write("        //???IE?????????FireFox?????xmlhttpRequest \n");
      out.write("                if (!_xmlhttp && typeof XMLHttpRequest != \"undefined\")\n");
      out.write("                {\n");
      out.write("                    _xmlhttp = new XMLHttpRequest();\n");
      out.write("                }\n");
      out.write("                return _xmlhttp;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("        //?????? \n");
      out.write("            function Read() {\n");
      out.write("                var ajax = ajax_xmlhttp(); //?xmlhttprequest?????????? \n");
      out.write("                ajax.open(\"post\", \"Edit_Data.asp?action=read\", true);//?????????????url?action???read????? \n");
      out.write("                ajax.onreadystatechange = function () {//??????????????????? \n");
      out.write("                    if (ajax.readyState == 4) {//?????? \n");
      out.write("                        if (ajax.status == 200) {//http???????ok \n");
      out.write("                            var xmlData = ajax.responseXML;//?xml??????????????xmlData??? \n");
      out.write("                            var list = xmlData.getElementsByTagName(\"list\");//????????????list?? \n");
      out.write("                            if (list.length != 0) {\n");
      out.write("                                var t = document.getElementById(\"a\");//????????? \n");
      out.write("                                for (var i = 0; i < list.length; i++) {\n");
      out.write("                                    var tr = t.insertRow();//???list????????? \n");
      out.write("                                    for (var k = 0; k < list[i].childNodes.length; k++) { //????list????? \n");
      out.write("                                        var td = tr.insertCell();//??list???????????????? \n");
      out.write("                                        td.innerHTML = list[i].childNodes[k].firstChild.nodeValue;//????????i?list??k?????????? \n");
      out.write("                                    }\n");
      out.write("                                }\n");
      out.write("                            }\n");
      out.write("                        }\n");
      out.write("                    }\n");
      out.write("                }\n");
      out.write("                ajax.send(null);//????????null \n");
      out.write("            }\n");
      out.write("            window.load = Read();\n");
      out.write("\n");
      out.write("        //??????? \n");
      out.write("            function Edit_Data() {\n");
      out.write("                var msgaes = document.getElementById(\"msgaes\");//???????????? \n");
      out.write("                var id = document.getElementById(\"data_id\");//?????????? \n");
      out.write("                var content = document.getElementById(\"data_content\");//???????? \n");
      out.write("                if (id.value.length == 0 || content.value.length == 0) {\n");
      out.write("                    msgaes.innerHTML = \"?????????!\";\n");
      out.write("                    return;\n");
      out.write("                }\n");
      out.write("        //?????id?content????param??????send????param \n");
      out.write("                var param = \"id=\" + escape(id.value) + \"&content=\" + escape(content.value);\n");
      out.write("                var ajax = ajax_xmlhttp();\n");
      out.write("                ajax.open(\"post\", \"Edit_Data.asp?action=edit\", true);\n");
      out.write("                ajax.onreadystatechange = function () {// \n");
      out.write("                    if (ajax.readyState == 4) {\n");
      out.write("                        if (ajax.status == 200) {\n");
      out.write("                            var xmlData = ajax.responseXML;\n");
      out.write("\n");
      out.write("                            var msg = xmlData.getElementsByTagName(\"msg\");\n");
      out.write("                            if (msg.length != 0) {\n");
      out.write("                                switch (msg[0].firstChild.nodeValue) {//??msg?? \n");
      out.write("                                    case \"0\":\n");
      out.write("                                        msgaes.innerHTML = \"???????\";\n");
      out.write("                                        var a = document.getElementById(\"a\");\n");
      out.write("                                        for (var i = 0; i < a.rows.length; i++) {//???????????????? \n");
      out.write("                                            for (var k = 0; k < a.rows[i].cells.length; k++) {\n");
      out.write("                                                if (a.rows[i].cells[0].innerHTML == id.value) {\n");
      out.write("                                                    a.rows[i].cells[1].innerHTML = content.value;\n");
      out.write("                                                    id.value = \"\";\n");
      out.write("                                                    content.value = \"\";\n");
      out.write("                                                    return;\n");
      out.write("                                                }\n");
      out.write("\n");
      out.write("                                            }\n");
      out.write("                                        }\n");
      out.write("                                        break;\n");
      out.write("                                    case \"1\":\n");
      out.write("                                        msgaes.innerHTML = \"?????????????\";\n");
      out.write("                                        break;\n");
      out.write("                                    case \"3\":\n");
      out.write("                                        msgaes.innerHTML = \"???????????????????\";\n");
      out.write("                                        break;\n");
      out.write("                                    case \"4\":\n");
      out.write("                                        msgaes.innerHTML = \"??????????????????????\";\n");
      out.write("                                        break;\n");
      out.write("                                    default:\n");
      out.write("                                        msgaes.innerHTML = \"??????!??????QQ30458885\";\n");
      out.write("                                        break;\n");
      out.write("                                }\n");
      out.write("                            }\n");
      out.write("                        }\n");
      out.write("                    }\n");
      out.write("                }\n");
      out.write("                ajax.setRequestHeader(\"content-type\", \"application/x-www-form-urlencoded\");// \n");
      out.write("                ajax.send(param);\n");
      out.write("            }\n");
      out.write("        </script> \n");
      out.write("    </body> \n");
      out.write("</html> \n");
      out.write("\n");
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
