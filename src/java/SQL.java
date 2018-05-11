import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class SQL extends HttpServlet
{
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     HttpSession ses = req.getSession(true) ;
     String m = (String)ses.getAttribute("mode");
     PrintWriter out = resp.getWriter();
     out.println("<body bgcolor=lightyellow>");
     if(!m.equals("Admin"))
     {
        out.println("<h3><pre>U have no privilege for view this page.");
        out.close();
     }
     out.println("<form action=SQL method=post>");
     out.println("<h3><pre>SQL");
     out.println("   <textarea name=t1 rows=5 cols=70></textarea>");
     out.println(" <input type=submit value='Execute'></form><hr size=4 color=red>");
     String str = req.getParameter("t1"); // get user statement
     String msg = "Statement executed.";
     if(str!=null) // agar user ne statement likha hai to - not 1st time
     {
      try{  
        Statement s = admin.DB.connect().createStatement();
        s.execute(str); // execute the user statement
        ResultSet rs = s.getResultSet(); // get the ResultSet object from statement object
        if(rs!=null) // if statement was the select statement
        {
          ResultSetMetaData mt = rs.getMetaData(); // for get the colname & col type
          out.println("<table border=1 width=100%><tr>");
          for(int i=1;i<=mt.getColumnCount();i++) // while not end of the cols.
            out.print("<th>"+mt.getColumnName(i));
          while(rs.next())
          {
            out.println("<tr>");
            for(int i=1;i<=mt.getColumnCount();i++) // while not end of the cols.
              out.print("<td>"+rs.getString(i));    
          }
          out.println("</table>");
        }  
      }catch(Exception ex) {msg = ex.getMessage(); }
      out.println(msg);  
     }
   }
}  