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

 resultset = statement.executeQuery("Select * from foldermap where mailid = " + mid + " and folderid = 3;");

 if (resultset.next()) {

%>
<div class="composemailbox">
 <form method="post">
  <input type="submit" value="Send" id="composebutton" formaction="sendReply.jsp?mid=<%= mid%>"/>
  <input type="submit" value="Save to Drafts" id="composebutton" formaction="saveReply.jsp??mid=<%= mid%>" /><br>
  <composemargin>Reciever: &nbsp;&nbsp;<outputbox><%= resultset.getString(1)%> </outputbox></composemargin><br>
  <composemargin>Subject:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="subject" maxlength=50 id="composepage" /><br></composemargin><br> 
  <br><composemargin>Message Body:<br></composemargin><textarea name="messagebody" id="composebody"></textarea><br>     
 </form>

</div>

<%    }
%>

<%@include file="footerLogIn.jsp" %>
