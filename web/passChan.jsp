<%@ page import ="java.lang.*" %>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>

<% 
 Class.forName("com.mysql.jdbc.Driver");
 if (session.getAttribute("seccor") == null) {
  response.sendRedirect("forgotPass.jsp");
  return;
 }
 session.setAttribute("seccor", null);
 String id = request.getParameter("uid");
 String ps2 = request.getParameter("ps2");
 Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/JMailDB", "root", "root");
 PreparedStatement statement = connection.prepareStatement("Update login set password=? WHERE user=?;");
 statement.setString(1, ps2);
 statement.setString(2, id);
 statement.executeUpdate();

 response.sendRedirect("index.jsp?t=7");
 return;

%>