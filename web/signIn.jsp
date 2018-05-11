<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>

<%
 Class.forName("com.mysql.jdbc.Driver");
 Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/JMailDB", "root", "root");
 String u = request.getParameter("uid");
 String p = request.getParameter("ps");
 Statement statement = connection.createStatement();
 ResultSet resultSet = statement.executeQuery("select * from login where user=\"" + u + "\" AND password=\"" + p + "\"");
 if (u == null) {
  response.sendRedirect("index.jsp?t=1");
  return;
 } else if (u.equals("")) {
  response.sendRedirect("index.jsp?t=1");
  return;
 }
 if (p == null) {
  response.sendRedirect("index.jsp?t=2");
  return;
 } else if (p.equals("")) {
  response.sendRedirect("index.jsp?t=2");
  return;
 } else if (resultSet.isBeforeFirst()) {
  session.setAttribute("Id", u);
  response.sendRedirect("inbox.jsp");
  return;
 } else {
  response.sendRedirect("index.jsp?t=0");
  return;
 }
%>