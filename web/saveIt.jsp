<%@ page import ="java.lang.*" %>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>

<%
 int mid = 0;
 Class.forName("com.mysql.jdbc.Driver");
 Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/JMailDB", "root", "root");
 String rec = request.getParameter("reciever");
 String sub = request.getParameter("subject");
 String body = request.getParameter("messagebody");
 String curuser = (String) session.getAttribute("Id");
 Statement statement = connection.createStatement();

 ResultSet resultset = statement.executeQuery("Select max(mailid) as maxmid from foldermap;");
 while (resultset.next()) {
  mid = Integer.parseInt(resultset.getString("maxmid")) + 1;
 }
 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
 Date date = new Date();

 DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
 Date time = new Date();

 statement.executeUpdate("insert into foldermap values('" + curuser + "'," + mid + ",2)");
 statement.executeUpdate("insert into mails values(" + mid + ",'" + rec + "','" + sub + "','" + body + "',null,'" + dateFormat.format(date) + "','" + timeFormat.format(time) + "')");
 response.sendRedirect("draft.jsp");
 return;
%>