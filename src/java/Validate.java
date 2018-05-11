
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Validate extends HttpServlet
{
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     String u = req.getParameter("t1");
     String p = req.getParameter("t2");
     String m = admin.DB.checkId(u, p); 
     if(m.startsWith("Sorry")) // agar user exist nahi hai to
     {
        req.setAttribute("t", m); 
        req.getRequestDispatcher("index.jsp").forward(req, resp);
     }
     else
     {
        HttpSession ses = req.getSession(true); // session enable
        ses.setAttribute("id", u);
        if(m.equals("Y"))
        {
            m=admin.DB.getMode(u);
            ses.setAttribute("mode", m);
            resp.sendRedirect("Main");
        }
        else
        {
            m="U are not verified";
            ses.setAttribute("mverify", m);
            resp.sendRedirect("verify.jsp");
        }
        
        
     }   
  }
    
}