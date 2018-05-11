import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class DelUserAction extends HttpServlet
{
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     String u = req.getParameter("t1");
     String m = admin.DB.delUser(u);
     req.setAttribute("t", m);
     req.getRequestDispatcher("DelUser").forward(req, resp);
  }
}  