import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class DelUser extends HttpServlet
{
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     HttpSession ses = req.getSession(true) ;
     String m = (String)ses.getAttribute("mode");
     PrintWriter out = resp.getWriter();
     out.println("<body bgcolor=lightyellow>");
     if(!m.equals("Admin")) // agar user admin nahi hai to sorry msg denge.
     {
        out.println("<h3><pre>U have no privilege for view this page.");
        out.close();
     }
     out.println("<h3><pre>");
     out.println("<form action=DelUserAction method=post>");
     out.println("  Select Userid for delete - ");
     out.println("");
     out.println("       <select name=t1>");
     List a = admin.DB.getUsers();
     for(int i=0;i<a.size();i++) 
       out.println("            <option value='"+a.get(i)+"'>"+a.get(i) +"</option>");
     out.println("       </select>");
     out.println("");
     out.println("");
     out.println("  <input type=submit value='Delete'></form><hr size=4 color=red>");
     out.println("");
     out.println("");
     out.println("");
     String msg = (String)req.getAttribute("t");
     if(msg!=null) // agar user ne arraibute pass kiya hai to
        out.println(msg);
   }
}  