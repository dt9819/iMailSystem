
import javax.servlet.http.*;
import javax.servlet.*;
import java.security.Security;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;

public class Register extends HttpServlet
{
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     String u = req.getParameter("t1");
     String p = req.getParameter("t2");
     String fn = req.getParameter("t3");
     String ln = req.getParameter("t4");
     String mail = req.getParameter("t5");
     String db = req.getParameter("t6");
     String mb = req.getParameter("t7");
     String addr = req.getParameter("t8");
     String q = req.getParameter("t9");
     String verify=Integer.toString((int)(Math.random()*100000));
        java.io.PrintWriter out = resp.getWriter();
     out.println("<body bgcolor=lightgreen><h1><pre>");
     try
     {
       String to="mrajora@yahoo.com"; // Admin Id 
       String subject="Request mail for verification code";
       String emailMsgTxt="Hello "+fn+", ur verification code is - "+verify+"<br>This code is sent to register ur account. ";
       String SMTP_HOST_NAME = "smtp.gmail.com";
       String SMTP_PORT = "465"; // gmail/yahoo
       final String SENDERID = "rahulmaurya48";
       final String SENDERPASS="numanmauryarahulmaurya";
       String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
       String[] EMAIL_TO_ADDRESS = {to,mail};
       Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
       Properties props = new Properties();
       props.put("mail.smtp.host", SMTP_HOST_NAME);
       props.put("mail.smtp.auth", "true");
       props.put("mail.debug", "true");
       props.put("mail.smtp.port", SMTP_PORT);
       props.put("mail.smtp.socketFactory.port", SMTP_PORT);
       props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
       props.put("mail.smtp.socketFactory.fallback", "false");
       Session ses = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(SENDERID, SENDERPASS);//Please Provide The Sender Address
           }
       });
       ses.setDebug(true);
       Message msg = new MimeMessage(ses); // MIME - Multi porpose Internel Mail Extension
       InternetAddress addressFrom = new InternetAddress(SENDERID);
       msg.setFrom(addressFrom);
       InternetAddress[] ADDRESS_TO = new InternetAddress[EMAIL_TO_ADDRESS.length];
         for (int i = 0; i < EMAIL_TO_ADDRESS.length; i++) {
             ADDRESS_TO[i] = new InternetAddress(EMAIL_TO_ADDRESS[i]);
       }
       msg.setRecipients(Message.RecipientType.TO, ADDRESS_TO);
      // Setting the Subject and Content Type
       msg.setSubject(subject);
       msg.setContent(emailMsgTxt, "text/html");
       Transport.send(msg);
      } catch(Exception e){
                 out.println(e.getMessage());
      }
     String status="N";
     String m = admin.DB.addUser(u, p, fn, ln, mail, db, mb, addr, q , verify, status);
     if(m.indexOf("CN_UID")!=-1)
     {
       m = "Sorry user already exist, try another userid or contact ur manager.";
       req.setAttribute("t", m);
       req.getRequestDispatcher("SignUp.jsp").forward(req, resp);
     }
     req.setAttribute("t", m); 
     req.getRequestDispatcher("index.jsp").forward(req, resp);
  }
}  