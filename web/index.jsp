<%@include file="headerNotIn.jsp" %>
<% String t = request.getParameter("t");
 if (t == null || t.equalsIgnoreCase("")) {
  } else if (t.equalsIgnoreCase("0")) { %><script>alert("Incorrect Details!");</script><%
  } else if (t.equalsIgnoreCase("1")) { %><script>alert("Please enter username.");</script><%
  } else if (t.equalsIgnoreCase("2")) { %><script>alert("Please Enter Password");</script><%
  } else if (t.equalsIgnoreCase("3")) { %><script>alert("Signup Successful! You can login now.");</script><%
  } else if (t.equalsIgnoreCase("6")) { %><script>alert("Logged out successfully!");</script><%
  } else if (t.equalsIgnoreCase("7")) { %><script>alert("Password Reset Success!");</script><% 
 }
%>

<loginbox><center>
  <img src="./images/uavatar.png" id="loginavatar" alt="Welcome to JMail" /><br>

  <form action="signIn.jsp" method="POST">    
   <h1>Sign In!</h1>
   <h2>User ID  : &nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="uid" placeholder="Enter User Id" id="loginpage">
    <!%=u%>
    <br>
    Password : <input type="password" name="ps" placeholder="Enter Password" class="indexboxes" id="loginpage"  style="margin-top:2%"><br> 
    <forgottext>
     <a href="forgotPass.jsp">Forgot Password? Click HERE</a></forgottext>
   </h2>

   <div class="buttonbar">
    <input type="submit" value="Log In" id="loginbutton" />
  </form>
  <form action="signUp.jsp">
   <input type="submit" value="Not Registered? Sign Up NOW!"  id="signupredirect" /></form>
  </div> 

 </center></loginbox>

<%@include file="footerNotIn.jsp" %>
