<%@ page import ="java.lang.*" %>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%@include file="header.jsp" %>

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
 }

 resultset = statement.executeQuery("Select * from mails where mailid=" + mid);

 if (resultset.next()) {

%>

<div class="viewmailbigbox">
 <form method="post">
  <input type="submit" value="Forward" id="composebutton" formaction="forward.jsp?mid=<%= resultset.getString(1)%>"/>
  <input type="submit" value="Delete" id="composebutton" formaction="delMail.jsp?mid=<%= resultset.getString(1)%>" />
  <input type="submit" value="Reply" id="composebutton" formaction="reply.jsp?mid=<%= resultset.getString(1)%>" />
  <br>
  <composemargin>Reciever: <outputbox><%= resultset.getString(2)%></outputbox></composemargin><br>
  <composemargin>Sender: <outputbox><%= resultset.getString(5)%></outputbox></composemargin><br>
  <composemargin>Subject:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <outputbox><%= resultset.getString(3)%></outputbox><br></composemargin><br>
  <composemargin>TIME:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <outputbox><%= resultset.getString(6)%><br><%= resultset.getString(7)%></outputbox><br></composemargin><br>
  <br><composemargin>Message Body:<br></composemargin>
  <msgbodydisp><%= resultset.getString(4)%></msgbodydisp><br>     
 </form>

</div>
<%
 }
%>

<%@include file="footerLogIn.jsp" %>
