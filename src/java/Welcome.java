
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Welcome extends HttpServlet
{
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     HttpSession ses = req.getSession(true);
     String u = (String)ses.getAttribute("id");
     String m = (String)ses.getAttribute("mode");
     if(u==null) // if page direct open hai to.. 
     {
       req.setAttribute("t","Sorry U can't Contd.. Login 1st...");
       req.getRequestDispatcher("index.jsp").forward(req, resp);
     }
     else
     {
       PrintWriter out = resp.getWriter();
       out.println("<body bgcolor=lightgreen><h2><tt>");
       out.println("Hello "+u+", Welcome's U <div align=right>"+m+"</div>");
       out.println("<br><a href=Compose.jsp>Compose</a>");
     }
  }
    
}