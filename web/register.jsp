<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>

<%
 int sucCode1 = 0;
 int sucCode2 = 0;
 int sucCode3 = 0;
 Class.forName("com.mysql.jdbc.Driver");
 Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/JMailDB", "root", "root");
 PreparedStatement statement = connection.prepareStatement("insert into details values(?,?,?,?,?,?,?,?,?,?)");
 statement.setString(1, request.getParameter("uid"));
 statement.setString(2, request.getParameter("fn"));
 statement.setString(3, request.getParameter("ln"));
 statement.setString(4, request.getParameter("country"));
 statement.setString(5, request.getParameter("st"));
 statement.setString(6, request.getParameter("dob"));
 statement.setString(7, request.getParameter("sex"));
 statement.setString(8, request.getParameter("addrs"));
 statement.setString(9, request.getParameter("pin"));
 statement.setString(10, request.getParameter("ct"));
 sucCode1 = statement.executeUpdate();
 if (sucCode1 != 0) {
  statement = connection.prepareStatement("insert into login values(?,?,?)");
  statement.setString(1, request.getParameter("uid"));
  statement.setString(2, request.getParameter("ps"));
  statement.setString(3, request.getParameter("sex"));
  sucCode2 = statement.executeUpdate();
  if (sucCode2 != 0) {
   statement = connection.prepareStatement("insert into secques values(?,?,?)");
   statement.setString(1, request.getParameter("uid"));
   statement.setString(2, request.getParameter("secques"));
   statement.setString(3, request.getParameter("secans"));
   sucCode3 = statement.executeUpdate();
   if (sucCode3 != 0) {
    response.sendRedirect("index.jsp?t=3");
    return;
   } else {
    response.sendRedirect("signUp.jsp?t=0");
    return;
   }

  }

 }


%>