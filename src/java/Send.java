import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class Send extends HttpServlet
{
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     String sid = req.getParameter("t1");
     String scc = req.getParameter("t2");
     String sbcc = req.getParameter("t3");
     String sub = req.getParameter("t4");
     String msg = req.getParameter("t5");
     HttpSession ses = req.getSession(true);
     String u = (String)ses.getAttribute("id");
     String m = admin.DB.sendMail(u, "Inbox", sid, sub, msg);
     req.setAttribute("msg", m);  
     if(m.indexOf("Message")!=-1)
       req.getRequestDispatcher("Inbox").forward(req, resp);
     else
       req.getRequestDispatcher("Compose.jsp").forward(req, resp);
   }
}  