package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.lang.*;
import java.sql.*;
import javax.sql.*;

public final class inbox_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/header.jsp");
    _jspx_dependants.add("/footerLogIn.jsp");
  }

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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write(" <head>\r\n");
      out.write("  <title></title>\r\n");
      out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css?");
      out.print( (int) (Math.random() * 1000));
      out.write("\">\r\n");
      out.write("\r\n");
      out.write(" </head> \r\n");
      out.write(" <body> \r\n");
      out.write("  <header id=\"mailpage\">\t   \r\n");
      out.write("   <a href=\"./inbox.jsp\">\r\n");
      out.write("    <img src=\"./images/logo.png\" height=100% />\r\n");
      out.write("   </a>\r\n");
      out.write("   <a href=\"logout.jsp\"><div style=\"float:right;margin-right: 2%;margin-top: 1.5%\"><font size=20>Logout</font></div><a>\r\n");
      out.write("     </header>\r\n");
      out.write("     <div class=\"sidebar\">\r\n");
      out.write("\r\n");
      out.write("      <table cellpadding=\"2\">\r\n");
      out.write("       <tbody style=\"margin-top: 10px;\">\r\n");
      out.write("       <br><center>Hello</center><br>\r\n");
      out.write("       <tr>\r\n");
      out.write("        <td> &nbsp;</td>\r\n");
      out.write("        <td></td>\r\n");
      out.write("        <td><a href=\"compose.jsp\"><b style=\"text-shadow: 3;color: blue\"> Compose </a></b></td>\r\n");
      out.write("       </tr>\r\n");
      out.write("       <tr>\r\n");
      out.write("        <td> &nbsp;</td>\r\n");
      out.write("        <td></td>\r\n");
      out.write("        <td><a href=\"inbox.jsp\"><b style=\"text-shadow: 3;color: blue\"> Inbox</a></b></td>\r\n");
      out.write("       </tr>\r\n");
      out.write("       <tr>\r\n");
      out.write("        <td> &nbsp;</td>\r\n");
      out.write("        <td></td>\r\n");
      out.write("        <td><a href=\"draft.jsp\"><b style=\"text-shadow: 3;color: blue\"> Draft</a></b></td>\r\n");
      out.write("       </tr>\r\n");
      out.write("       <tr>\r\n");
      out.write("        <td> &nbsp;</td>\r\n");
      out.write("        <td></td>\r\n");
      out.write("        <td><a href=\"sentItems.jsp\"><b style=\"text-shadow: 3;color: blue\"> Sent Items</a></b></td>\r\n");
      out.write("       </tr>\r\n");
      out.write("       <tr>\r\n");
      out.write("        <td> &nbsp;</td>\r\n");
      out.write("        <td></td>\r\n");
      out.write("        <td><a href=\"trash.jsp\"><b style=\"text-shadow: 3;color: blue\"> Trash </b></a></td>\r\n");
      out.write("       </tr>\r\n");
      out.write("       </tbody>\r\n");
      out.write("      </table> \r\n");
      out.write("     </div> \r\n");
      out.write("     </td>\r\n");
      out.write("     <!-- Mail description Ends.. ! -->");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- Mail Body Starts.. ! -->\r\n");
      out.write("<td>\r\n");
      out.write(" <div class=\"viewmailbox\">\r\n");
      out.write("  <table border=\"0\" cellspacing=\"3\" id=\"alter\" cellpadding=\"5\" style=\"width:100%\">\r\n");
      out.write("   <thead>\r\n");
      out.write("\r\n");
      out.write("    <tr>\r\n");
      out.write("     <th width=30%><b><center>--FROM-- </center></b></th>\r\n");
      out.write("     <th width=30%><b><center>SUBJECT</center></b></th>\r\n");
      out.write("     <th width=10%><b><center>DATE<br>&nbsp;</center></b></th>\r\n");
      out.write("     <th width=10%><b><center>TIME<br>&nbsp;<center></b></th>\r\n");
      out.write("         <th width=15%><b><center>Delete</center></b></th>\r\n");
      out.write("         </tr>\r\n");
      out.write("         </thead>\r\n");
      out.write("         <tbody><form method=\"post\">\r\n");
      out.write("          ");

           int maxmails = 0;
           int i = 0;
           int begid;
           if (request.getParameter("b") == null) {
            begid = 0;
           } else if (request.getParameter("b") == "") {
            begid = 0;
           } else {
            begid = Integer.parseInt(request.getParameter("b"));
           }

           String curuser = (String) session.getAttribute("Id");
           if (curuser == null) {
            response.sendRedirect("index.jsp");
           }
           int mid = 0;
           Class.forName("com.mysql.jdbc.Driver");
           Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/JMailDB", "root", "root");
           Statement statement = connection.createStatement();

           ResultSet resultset = statement.executeQuery("Select mails.* from mails,foldermap where foldermap.uid='" + curuser + "' AND foldermap.folderid=1 AND foldermap.mailid=mails.mailid ORDER BY mails.mailid desc LIMIT " + begid + ",10;");
           while (resultset.next()) {
            i++;
          
      out.write("\r\n");
      out.write("          <tr>\r\n");
      out.write("           <td>");
      out.print( resultset.getString(5));
      out.write("</td>\r\n");
      out.write("           <td><a href=\"viewMail.jsp?mid=");
      out.print( resultset.getString(1));
      out.write('"');
      out.write('>');
      out.print( resultset.getString(3));
      out.write("</a></td>\r\n");
      out.write("           <td>");
      out.print( resultset.getString(6));
      out.write("</td>\r\n");
      out.write("           <td>");
      out.print( resultset.getString(7));
      out.write("</td>\r\n");
      out.write("           <td><center>Remove <input type=\"checkbox\" name=\"DEL\" value=\"");
      out.print( resultset.getString(1));
      out.write("\" /></center></td>\r\n");
      out.write("          </tr>\t\r\n");
      out.write("          ");

           }
           resultset = statement.executeQuery("Select count(*) from mails,foldermap where foldermap.uid='" + curuser + "' AND foldermap.folderid=1 AND foldermap.mailid=mails.mailid;");

           while (resultset.next()) {
            maxmails = Integer.parseInt(resultset.getString(1));
           }
           int previd;
           if (begid < 10) {
            previd = 0;
           } else {
            previd = begid - 10;
           }

           int nextid;
           if (begid > (maxmails - 10)) {
            nextid = begid;
           } else {
            nextid = begid + 10;
           }
          
      out.write("\r\n");
      out.write("          </tbody>\r\n");
      out.write("          </table>\r\n");
      out.write("          <br><center><a href=\"?b=");
      out.print( previd);
      out.write("\">« Previous</a><b> Showing ");
      out.print( begid + 1);
      out.write('-');
      out.print( begid + i);
      out.write(" of ");
      out.print( maxmails);
      out.write(" </b><a href=\"?b=");
      out.print( nextid);
      out.write("\">Next »</a></center><input type=\"submit\" value=\"Delete Checked\" style=\"float:right;\" formaction=\"delCheck.jsp?b=");
      out.print( begid);
      out.write("&f=1\"/></form>\r\n");
      out.write("         <!-- Mail Body Ends ! -->\r\n");
      out.write("\r\n");
      out.write("         </div>\r\n");
      out.write("\r\n");
      out.write("         ");
      out.write("\r\n");
      out.write("  <footer>\r\n");
      out.write("   <br><center>Copyright 2015 - JMail, All Rights Reserved</center>\r\n");
      out.write("   <br><center>For any changes to your account like password or security questions, send a mail to <b>admin</b></center>\r\n");
      out.write("  </footer>\r\n");
      out.write(" </body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("\r\n");
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
