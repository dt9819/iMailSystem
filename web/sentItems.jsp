<%@ page import ="java.lang.*" %>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%@ include file="header.jsp" %>


<!-- Mail Body Starts.. ! -->
<td>
 <div class="viewmailbox">
  <table border="0" cellspacing="3" id="alter" cellpadding="5" style="width:100%">
   <thead>

    <tr>
     <th width=30%><b><center>--FROM-- </center></b></th>
     <th width=30%><b><center>SUBJECT</center></b></th>
     <th width=10%><b><center>DATE<br>&nbsp;</center></b></th>
     <th width=10%><b><center>TIME<br>&nbsp;<center></b></th>
         <th width=15%><b><center>Delete</center></b></th>
         </tr>
         </thead>
         <tbody><form method="post">
          <%
           int maxmails = 0;
           int i = 0;
           int begid;
           if (request.getParameter("b") == null) {
            begid = 0;
           } else if (request.getParameter("b") == "") {
            begid = 0;
           } else {
            begid = Integer.parseInt(request.getParameter("b"));
           }

           String curuser = (String) session.getAttribute("Id");
           if (curuser == null) {
            response.sendRedirect("index.jsp");
            return;
           }
           int mid = 0;
           Class.forName("com.mysql.jdbc.Driver");
           Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/JMailDB", "root", "root");
           Statement statement = connection.createStatement();

           ResultSet resultset = statement.executeQuery("Select mails.* from mails,foldermap where foldermap.uid='" + curuser + "' AND foldermap.folderid=3 AND foldermap.mailid=mails.mailid ORDER BY mails.mailid desc LIMIT " + begid + ",10;");
           while (resultset.next()) {
            i++;
          %>
          <tr>
           <td><%= resultset.getString(5)%></td>
           <td><a href="viewMail.jsp?mid=<%= resultset.getString(1)%>"><%= resultset.getString(3)%></a></td>
           <td><%= resultset.getString(6)%></td>
           <td><%= resultset.getString(7)%></td>
           <td><center>Remove <input type="checkbox" name="DEL" value="<%= resultset.getString(1)%>" /></center></td>
          </tr>	
          <%
           }
           resultset = statement.executeQuery("Select count(*) from mails,foldermap where foldermap.uid='" + curuser + "' AND foldermap.folderid=3 AND foldermap.mailid=mails.mailid;");

           while (resultset.next()) {
            maxmails = Integer.parseInt(resultset.getString(1));
           }
           int previd;
           if (begid < 10) {
            previd = 0;
           } else {
            previd = begid - 10;
           }

           int nextid;
           if (begid > (maxmails - 10)) {
            nextid = begid;
           } else {
            nextid = begid + 10;
           }
          %>
          </tbody>
          </table>
          <br><center><a href="?b=<%= previd%>">« Previous</a><b> Showing <%= begid + 1%>-<%= begid + i%> of <%= maxmails%> </b><a href="?b=<%= nextid%>">Next »</a></center><input type="submit" value="Delete Checked" style="float:right;" formaction="delCheck.jsp?b=<%= begid%>&f=3"/></form>
         <!-- Mail Body Ends ! -->

         </div>

         <%@include file="footerLogIn.jsp" %>

