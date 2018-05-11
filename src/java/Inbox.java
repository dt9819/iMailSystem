
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class Inbox extends HttpServlet
{
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String no="";
    HttpSession ses = req.getSession(true); 
    String u = (String)ses.getAttribute("id");
    PrintWriter out = resp.getWriter();
    out.println("<html><body bgcolor=lightyellow>");
    String m = (String)req.getAttribute("msg"); // for send msg
    if(m!=null)
      out.print("<h3><tt>"+m+"</tt></h3>");
    try
    {
      ResultSet rs = admin.DB.getInbox(u);
      ResultSetMetaData mt = rs.getMetaData(); // for get the colname & col type
      out.println("<table border=1 width=100%><tr>");
      for(int i=1;i<=mt.getColumnCount();i++) // while not end of the cols.
        out.print("<th>"+mt.getColumnName(i));
      while(rs.next())
      {
        out.println("<tr>");
        for(int i=1;i<=mt.getColumnCount();i++) // while not end of the cols.
        {
          if(i==1) // for msg  
          {
              no = rs.getString(i);
              out.print("<td>"+no);
          }
          else if(i==3) // for msg  
              out.print("<td><a href=View?n="+no+">"+rs.getString(i)+"</a>");
          else if(i==4) // for msg 
          {
            String str = rs.getString(i);
            if(str.length()>10) str = str.substring(0,10);
            out.print("<td>"+str+"......");
          }
          else
            out.print("<td>"+rs.getString(i));    
        }
      }    
    }catch(Exception ex){ out.println(ex.getMessage()); }
  }
}