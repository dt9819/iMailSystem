import java.io.*;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.*;
public class ProfileAction extends HttpServlet 
{
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
  {
     HttpSession ses = req.getSession(true);
     String u = (String)ses.getAttribute("id");
     String fn = req.getParameter("t1");
     String ln = req.getParameter("t2");
     String mail = req.getParameter("t3");
     String dob = req.getParameter("t4");
     String mob = req.getParameter("t5");
     String add = req.getParameter("t6");
     String q = req.getParameter("t7");
     String msg = admin.DB.updateProfile(u, fn, ln, mail, dob, mob, add, q);
     req.setAttribute("t", msg);
     ResultSet rs = admin.DB.getDetails(u);
     req.setAttribute("r", rs); // set resultset as request attribute 
     req.getRequestDispatcher("Profile.jsp").forward(req, resp);
  }
}
