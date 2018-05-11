
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class SendMails extends HttpServlet
{
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession ses = req.getSession(true); 
    String u = (String)ses.getAttribute("id");
    PrintWriter out = resp.getWriter();
    out.println("<html><body bgcolor=lightyellow>");
    String m = (String)req.getAttribute("msg"); // for send msg
    if(m!=null)
      out.print("<h3><tt>"+m+"</tt></h3>");
    try
    {
      ResultSet rs = admin.DB.getSend(u);
      ResultSetMetaData mt = rs.getMetaData(); // for get the colname & col type
      out.println("<table border=1 width=100%><tr>");
      for(int i=1;i<=mt.getColumnCount();i++) // while not end of the cols.
        out.print("<th>"+mt.getColumnName(i));
      out.println("<th>Del");
      int ctr=0;
      String x;
      while(rs.next())
      {
        x = rs.getString(1);
        ctr++;
        out.println("<tr><td>"+ctr);
        for(int i=2;i<=mt.getColumnCount();i++) // while not end of the cols.
        {
          if(i==4) // for msg 
          {
            String str = rs.getString(i);
            if(str.length()>10) str = str.substring(0,10);
            out.print("<td>"+str+"......");
          }
          else
            out.print("<td>"+rs.getString(i));    
        }
        out.println("<td><a href=DelMail?t="+x+"><img src=del.png></a>");
      }    
      
    }catch(Exception ex){ out.println(ex.getMessage()); }
    String msg =(String) req.getAttribute("m");
    out.println("</table>"+msg);
  }
}