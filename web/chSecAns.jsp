<%@ page import ="java.lang.*" %>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>

<%
 Class.forName("com.mysql.jdbc.Driver");
 String SA = request.getParameter("secan");
 String id = request.getParameter("uid");
 Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/JMailDB", "root", "root");
 Statement statement = connection.createStatement();
 ResultSet resultset = statement.executeQuery("Select * from secques WHERE user='" + id + "' AND sec_ans='" + SA + "';");
 if (!resultset.next()) {
  response.sendRedirect("secCheck.jsp?msg=1");
  return;
 }
 session.setAttribute("seccor", "1");
 response.sendRedirect("resetPass.jsp?uid=" + id);
 return;

%>