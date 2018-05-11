package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/headerNotIn.jsp");
    _jspx_dependants.add("/footerNotIn.jsp");
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

      out.write("<html>\r\n");
      out.write(" <head>\r\n");
      out.write("  <title>Welcome to JMail</title>\r\n");
      out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css?");
      out.print( (int) (Math.random() * 1000));
      out.write("\">\r\n");
      out.write(" </head>\r\n");
      out.write(" <body>\r\n");
      out.write("  <header>\r\n");
      out.write("   <a href=\".\">\r\n");
      out.write("    <img src=\"./images/logo.png\" height=100% />\r\n");
      out.write("   </a>\r\n");
      out.write("  </header>");
      out.write('\r');
      out.write('\n');
 String t = request.getParameter("t");
 if (t == null || t.equalsIgnoreCase("")) {
  } else if (t.equalsIgnoreCase("0")) { 
      out.write("<script>alert(\"Incorrect Details!\");</script>");

  } else if (t.equalsIgnoreCase("1")) { 
      out.write("<script>alert(\"Please enter username.\");</script>");

  } else if (t.equalsIgnoreCase("2")) { 
      out.write("<script>alert(\"Please Enter Password\");</script>");

  } else if (t.equalsIgnoreCase("3")) { 
      out.write("<script>alert(\"Signup Successful! You can login now.\");</script>");

  } else if (t.equalsIgnoreCase("6")) { 
      out.write("<script>alert(\"Logged out successfully!\");</script>");

  } else if (t.equalsIgnoreCase("7")) { 
      out.write("<script>alert(\"Password Reset Success!\");</script>");
 
 }

      out.write("\r\n");
      out.write("\r\n");
      out.write("<loginbox><center>\r\n");
      out.write("  <img src=\"./images/uavatar.png\" id=\"loginavatar\" alt=\"Welcome to JMail\" /><br>\r\n");
      out.write("\r\n");
      out.write("  <form action=\"signIn.jsp\" method=\"POST\">    \r\n");
      out.write("   <h1>Sign In!</h1>\r\n");
      out.write("   <h2>User ID  : &nbsp;&nbsp;&nbsp;&nbsp;<input type=\"text\" name=\"uid\" placeholder=\"Enter User Id\" id=\"loginpage\">\r\n");
      out.write("    <!%=u%>\r\n");
      out.write("    <br>\r\n");
      out.write("    Password : <input type=\"password\" name=\"ps\" placeholder=\"Enter Password\" class=\"indexboxes\" id=\"loginpage\"  style=\"margin-top:2%\"><br> \r\n");
      out.write("    <forgottext>\r\n");
      out.write("     <a href=\"forgotPass.jsp\">Forgot Password? Click HERE</a></forgottext>\r\n");
      out.write("   </h2>\r\n");
      out.write("\r\n");
      out.write("   <div class=\"buttonbar\">\r\n");
      out.write("    <input type=\"submit\" value=\"Log In\" id=\"loginbutton\" />\r\n");
      out.write("  </form>\r\n");
      out.write("  <form action=\"signUp.jsp\">\r\n");
      out.write("   <input type=\"submit\" value=\"Not Registered? Sign Up NOW!\"  id=\"signupredirect\" /></form>\r\n");
      out.write("  </div> \r\n");
      out.write("\r\n");
      out.write(" </center></loginbox>\r\n");
      out.write("\r\n");
      out.write("  <footer><br><center>Copyright 2015 - JMail, All Rights Reserved</center></footer>\r\n");
      out.write(" </body>\r\n");
      out.write("</html>");
      out.write('\r');
      out.write('\n');
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
