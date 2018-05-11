
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePass extends HttpServlet
{
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     HttpSession ses = req.getSession(true) ;
     String u = (String)ses.getAttribute("id");
     String op = req.getParameter("t1");
     String np = req.getParameter("t2");
     String m = admin.DB.changePass(u, op, np);
     req.setAttribute("t", m); 
     req.getRequestDispatcher("ChangePass.jsp").forward(req, resp);
  }
}  