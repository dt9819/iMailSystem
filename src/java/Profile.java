import java.io.*;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.*;
public class Profile extends HttpServlet 
{
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
  {
     HttpSession ses = req.getSession(true);
     String u = (String)ses.getAttribute("id");
     ResultSet rs = admin.DB.getDetails(u);
     req.setAttribute("r", rs); // set resultset as request attribute 
     req.getRequestDispatcher("Profile.jsp").forward(req, resp);
  }
}
