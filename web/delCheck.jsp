<%@ page import ="java.lang.*" %>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>

<%
 String selected[] = request.getParameterValues("DEL");
 int p = Integer.parseInt(request.getParameter("f"));
 int delid = 5;
 String redirURL = new String();
 if (p == 1) {
  redirURL = "inbox.jsp?b=" + Integer.parseInt(request.getParameter("b"));
  delid = 4;
 } else if (p == 3) {
  redirURL = "sentItems.jsp?b=" + Integer.parseInt(request.getParameter("b"));
  delid = 4;
 } else if (p == 2) {
  redirURL = "draft.jsp?b=" + Integer.parseInt(request.getParameter("b"));
 } else if (p == 4) {
  redirURL = "trash.jsp?b=" + Integer.parseInt(request.getParameter("b"));
 }
 Class.forName("com.mysql.jdbc.Driver");
 Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/JMailDB", "root", "root");
 Statement statement = connection.createStatement();
 for (String mid : selected) {
  statement.executeUpdate("Update foldermap SET folderid=" + delid + " WHERE mailid='" + mid + "' AND folderid=" + p + "");
 }
 response.sendRedirect(redirURL);
%> 