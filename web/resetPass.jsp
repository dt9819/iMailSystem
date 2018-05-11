<%@include file="headerNotIn.jsp" %>

<% 
 String id = request.getParameter("uid");

 if (id == null) {
  response.sendRedirect("forgotPass.jsp");
  return;
 }
%>
<loginbox><center>
  <img src="./images/uavatar.png" id="loginavatar" alt="Welcome to JMail" /><br>

  <form action="passChan.jsp?uid=<%= id%>" method="POST" >    
   <h1>Password Recovery</h1>
   <h2>Enter new Password : &nbsp;&nbsp;<input type="password" name="ps2" placeholder="Enter your new Password" id="loginpage">
    <br>

    <br>
    <input type="submit" value="Change Password" id="loginbutton" style="margin-left:25%;" />

  </form>


 </center></loginbox>


<%@include file="footerNotIn.jsp" %>