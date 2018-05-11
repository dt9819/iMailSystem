
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Vcode extends HttpServlet
{
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     HttpSession ses=req.getSession(true);
     String u = (String)ses.getAttribute("id");
     String v= req.getParameter("t1");
     String m = admin.DB.Vcheck(u, v); 
     PrintWriter out=resp.getWriter();
     if(m.startsWith("Sorry")) // agar user exist nahi hai to
     {
        req.setAttribute("t", m); 
        out.println("<h4>sorry</h4>");
        req.getRequestDispatcher("verify.jsp").forward(req, resp);
     }
     else
     {
        ses.setAttribute("id", u);
        if(m.equals("yes"))
        {
            m=admin.DB.getMode(u);
            ses.setAttribute("mode", m);
            resp.sendRedirect("Main");
        }
        else
        {
            m="Verification code is wrong ,re-enter plz....";
            req.setAttribute("mverify", m);
            req.getRequestDispatcher("verify.jsp").forward(req, resp);
        }
        
        
     }   
  }
    
}