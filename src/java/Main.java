
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Main extends HttpServlet
{
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     HttpSession ses = req.getSession(true);
     String u = (String)ses.getAttribute("id");
     String m = (String)ses.getAttribute("mode");
     if(m==null)
     {
        req.setAttribute("t","Sorry U can't Contd.. Login 1st"); 
        req.getRequestDispatcher("index.jsp").forward(req, resp); 
     }
     else
     { 
       PrintWriter out = resp.getWriter();
       out.println("<html>");
       out.println("<frameset rows='10%,*' border=0>");
       out.println("  <frame src=Wel.jsp></frame>");
       out.println("   <frameset cols='15%,*' border=0>");
       out.println("    <frame src=Link.jsp name=f1></frame>");
       out.println("    <frame src=Inbox name=f2></frame>");
       out.println("   </frameset>");
       out.println("</frameset>");
       
     }  
  }
    
}