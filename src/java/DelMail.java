import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class DelMail extends HttpServlet
{
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     HttpSession ses = req.getSession(true);
     String u = (String)ses.getAttribute("id");
     String s = req.getParameter("t");
     String m = admin.DB.delMail(u,"Send",s);
     req.setAttribute("m", m);
     req.getRequestDispatcher("SendMails").forward(req, resp);
  }
}  