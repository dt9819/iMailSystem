<%@ page import ="java.lang.*" %>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%@include file="headerNotIn.jsp" %>

<loginbox ><center>
  <img src="./images/uavatar.png" id="loginavatar" alt="Welcome to JMail" /><br>
  <% String uid = request.getParameter("uid");
   if (uid == null) {
    response.sendRedirect("forgotPass.jsp");
    return;
   }
  %>
  <form action="chSecAns.jsp?uid=<%= uid%>" method="POST">    
   <h1>Answer your Security Question</h1>
   <h2>Question  : &nbsp;&nbsp;&nbsp;&nbsp;<secquebox><%
    String id = request.getParameter("uid");
    Class.forName("com.mysql.jdbc.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/JMailDB", "root", "root");
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery("Select sec_que from secques WHERE user='" + id + "';");
    if (resultset.next()) {
     out.print(resultset.getString(1));
    } else {
     response.sendRedirect("forgotPass.jsp?m=1");
    }
     %></secquebox>


    <br>
    Answer : <input type="ps" name="secan" placeholder="Enter Security Answer" class="indexboxes" id="loginpage"  style="margin-top:2%"><br> 
    <forgottext>

   </h2>

   <br>
   <input type="submit" value="NEXT>>" id="loginbutton" style="margin-left:25%;" />


 </center></loginbox>

<%@include file="footerNotIn.jsp" %>

