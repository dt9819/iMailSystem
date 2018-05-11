import javax.servlet.http.*;
import javax.servlet.*;
import java.security.Security;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;

public class SendPass extends HttpServlet
{
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     String u = req.getParameter("t1");
     String dob = req.getParameter("t2");
     String ps = admin.DB.forget(u); 
     String mail = admin.DB.getMailId(u,dob); 
     if(ps.startsWith("Sorry")) // agar user exist nahi hai to
     {
        req.setAttribute("t", ps); 
        req.getRequestDispatcher("Forget.jsp").forward(req, resp);
     }
     if(mail.startsWith("Sorry")) // agar user exist nahi hai to
     {
        req.setAttribute("t", mail); 
        req.getRequestDispatcher("Forget.jsp").forward(req, resp);
     }
     else
     {
       PrintWriter out = resp.getWriter();
     out.println("<body bgcolor=lightgreen><h1><pre>");
     try
     {
       String to="mrajora@yahoo.com";
       String subject="Request mail for password recovery";
       String emailMsgTxt="Hello "+u+", ur passwored is - "+ps+"<br>This password sended on ur request for Password Recovery. ";
       String SMTP_HOST_NAME = "smtp.gmail.com";
       String SMTP_PORT = "465"; // gmail/yahoo
       final String SENDERID = "rahulmaurya48";// koi bhi id dal de
       final String SENDERPASS="numanmauryarahulmaurya"; // us id ka password
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
       out.println("ur password send to ur mail id.......");
       } catch(Exception e){
                 out.println(e.getMessage());
      }
    }   
  }
    
}