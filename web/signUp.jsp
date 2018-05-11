<%@include file="headerNotIn.jsp" %>
<% String t = request.getParameter("t");
 if (t == null || t.equalsIgnoreCase("")) {
 } else if (t.equalsIgnoreCase("0") || t.equalsIgnoreCase("1")) { %><script>alert("Signup failed sorry, please try again.");</script><% }
%>

<signupbox><center>
  <img src="./images/uavatar.png" id="signupavatar" alt="Welcome to JMail" /><br>

  <form action="register.jsp" method="POST">    
   <h1>Sign Up!</h1>

   <h2>First Name* : <input type="text" name="fn" placeholder="Enter Your First Name" id="signuppage"><br>
    Last Name* : <input type="text" name="ln" placeholder="Enter Your Last Name" id="signuppage"  style="margin-top:2%"><br>
    Sex(M/F)* : <input type=text name="sex" placeholder="Enter your Gender(M/F)?" maxlength=1 size=20  id="signuppage"  style="margin-top:2%" /><br>


    Desired JMail User ID* : <input type=text name="uid" size="16" placeholder="Your JMail EMail will be yourUserID@JMail.com" maxlength=20  id="signuppage"  style="margin-top:2%" /><br>

    Password* : <input type=password name="ps" placeholder="Enter your desired Password" maxlength=20 size=20  id="signuppage"  style="margin-top:2%" /><br>

    Date Of Birth* : <input type=date name="dob" size="20" placeholder="Enter your Date of Birth" id="signuppage"  style="margin-top:2%"/><br>

    Address* :  
    <textarea name="addrs" rows="4"  id="signuppage" style="margin-top:2%"></textarea><br>

    State* :     <input type=text name="st" id="signuppage" style="margin-top:2%"><br>

    City* :     <input type=text name="ct" id="signuppage" style="margin-top:2%"><br>

    Postal code* : <input type="text" name="pin" value="" maxlength=6 placeholder="Enter City pincode" id="signuppage" style="margin-top:2%" /><br>
    Country* : <input type="text" name="country" value="" maxlength=6 placeholder="Enter Country" id="signuppage" style="margin-top:2%" /><br>

    Security Question* : <input type="text" name="secques" value="" placeholder="Enter your personal Security Question" id="signuppage" style="margin-top:2%" /><br>

    Security Answer* : <input type="text" name="secans" value="" placeholder="Enter the ANSWER to your Security Question." id="signuppage" style="margin-top:2%" /><br>
    <div class="buttonbar">
     <input type="submit" value="Sign Up with details entered." class="signuppage" id="loginbutton" />
     </form>
     <form action="index.jsp">
      <input type="submit" value="Already Registered? Click Here to Sign In" class="signuppage" id="signupredirect" /></form> 


     </center></signupbox>

     <%@include file="footerNotIn.jsp" %>
