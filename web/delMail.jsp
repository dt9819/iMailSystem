<%@ page import ="java.lang.*" %>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>

<%
 String curuser = (String) session.getAttribute("Id");
 if (curuser == null) {
  response.sendRedirect("index.jsp");
  return;
 }
 String mid = request.getParameter("mid");
 Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/JMailDB", "root", "root");
 Statement statement = connection.createStatement();
 ResultSet resultset = statement.executeQuery("Select * from foldermap WHERE uid='" + curuser + "' AND mailid=" + mid + "");

 if (!resultset.next()) {
  response.sendRedirect("inbox.jsp");
  return;
 } else {
  int p = Integer.parseInt(resultset.getString(3));
  int delid = 5;
  String redirURL = new String();
  if (p == 1) {
   redirURL = "inbox.jsp";
   delid = 4;
  } else if (p == 3) {
   redirURL = "sentItems.jsp";
   delid = 4;
  } else if (p == 2) {
   redirURL = "draft.jsp";
  } else if (p == 4) {
   redirURL = "trash.jsp";
  }
  statement.executeUpdate("Update foldermap set folderid='" + delid + "' where uid='" + curuser + "' AND mailid=" + mid + "");
  response.sendRedirect(redirURL);
  return;
 }
%>