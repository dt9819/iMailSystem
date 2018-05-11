<%@ page import ="java.lang.*" %>
<%@ page import ="java.util.*" %>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ include file="header.jsp" %>

<%  
 Class.forName("com.mysql.jdbc.Driver");
 Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/JMailDB", "root", "root");
 Statement statement = connection.createStatement();
 String curuser = (String) session.getAttribute("Id");

 if (curuser == null) {
  response.sendRedirect("index.jsp");
  return;
 }
%>
<div class="composemailbox">
 <form method="post">
  <input type="submit" value="Send" id="composebutton" formaction="sendIt.jsp"/>
  <input type="submit" value="Save to Drafts" id="composebutton" formaction="saveIt.jsp" /><br>
  <composemargin>Reciever(s): <input type="text" placeholder="One Recepient per mail." name="reciever" id="composepage" /></composemargin><br>
  <composemargin>Subject:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="subject" maxlength=50 id="composepage" /><br></composemargin><br> 
  <br><composemargin>Message Body:<br></composemargin><textarea name="messagebody" id="composebody"></textarea><br>     
 </form>

</div>

<%@include file="footerLogIn.jsp" %>
