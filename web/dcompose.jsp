<%@ page import ="java.lang.*" %>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
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

 String m = request.getParameter("mid");
 String d = request.getParameter("did");

 if (d != null) {
  ResultSet resultset = statement.executeQuery("Select * from foldermap WHERE uid='" + curuser + "' AND mailid=" + m);
  if (!resultset.next()) {
   response.sendRedirect("inbox.jsp");
   return;
  }

 }
%>
<div class="composemailbox">
 <form method="post">
  <input type="submit" value="Send" id="composebutton" formaction="sendIt.jsp"/>
  <input type="submit" value="Save to Drafts" id="composebutton" formaction="saveIt.jsp" /><br>
  <composemargin>Reciever(s): <input type="text" placeholder="Seperate multiple recievers with comma(s)" name="reciever" id="composepage" /></composemargin><br>
  <composemargin>Subject:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="subject" maxlength=50 id="composepage" /><br></composemargin><br> 
  <br><composemargin>Message Body:<br></composemargin>
  <textarea name="messagebody" id="composebody" > 
   <%
    if (d != null) {
     ResultSet resultset = statement.executeQuery("Select mailcontent from mails WHERE mailid=" + m);
     if (resultset.next()) {
      resultset.getString(1);
     }
    }
   %> </textarea><br>
  <br><composemargin>Attachment(s):<br></composemargin>
  <input type="file" name="attach1" id="attachbutton" />
  <input type="file" name="attach2" id="attachbutton" />
  <input type="file" name="attach3" id="attachbutton" />        
 </form>

</div>



<%@include file="footerLogIn.jsp" %>
