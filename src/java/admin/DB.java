package admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DB 
{
   static CallableStatement cs; // for procedure calling  
   public static Connection connect() throws Exception
   {
     Class.forName("oracle.jdbc.driver.OracleDriver"); // for connectivity with oracle 
     return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","mail","gzb"); //check for xe with new database
   }                      // connectivity with thin Layer:host/ip addr:oraport:dbname,userid,pass
   public static String addUser(String u,String p,String fn,String ln,String mail,String db,String mb,String addr,String quali,String verify,String status)
   {
     try
     {
        cs = connect().prepareCall("{call pro_regis(?,?,?,?,?,?,?,?,?,?,?,?)}");
        cs.setString(1,u); // set the 1st ? value
        cs.setString(2,p);
        cs.setString(3,fn);
        cs.setString(4,ln); 
        cs.setString(5,mail); 
        cs.setString(6,db);
        cs.setString(7,mb); 
        cs.setString(8,addr); 
        cs.setString(9,quali);
        cs.setString(10,verify);
        cs.setString(11,status);
        cs.registerOutParameter(12,Types.VARCHAR); // register 10th parameter as output mode for return value.
        cs.execute(); // procedure executed
        return cs.getString(12); // return out mode parameter value
     }catch(Exception ex) { return ex.getMessage(); }
   }
   public static String checkId(String u,String p)   
   {
     try
     {
        cs = connect().prepareCall("{call checkId(?,?,?)}");
        cs.setString(1,u); // set the 1st ? value
        cs.setString(2,p);
        cs.registerOutParameter(3,Types.VARCHAR); // register 10th parameter as output mode for return value.
        cs.execute(); // procedure executed
        return cs.getString(3); // return out mode parameter value
     }catch(Exception ex) { return ex.getMessage(); }
   }
    public static String getMode(String u)   
   {
     try
     {
        cs = connect().prepareCall("{call getMode(?,?)}");
        cs.setString(1,u); // set the 1st ? value
        cs.registerOutParameter(2,Types.VARCHAR); // register 10th parameter as output mode for return value.
        cs.execute(); // procedure executed
        return cs.getString(2); // return out mode parameter value
     }catch(Exception ex) { return ex.getMessage(); }
   }
   public static ResultSet getDetails(String u)  
   {
     try{
      return connect().createStatement().executeQuery("select fname,lname,mailid,to_char(dob,'dd-mon-yyyy') as dob,mobile,address,quali from tblRegis where userid='"+u+"'");
     }catch(Exception ex) { return null;} 
   }
   public static List getUsers()  
   {
     List a = new ArrayList(); // array list - dynamic array.  
     try{
      ResultSet rs = connect().createStatement().executeQuery("select userid from tblLogin where not wmode='Admin' order by 1");
      while(rs.next())
        a.add(rs.getString(1));  
     }catch(Exception ex) { a.add(ex.getMessage()); }
     return a;
   }
   public static String delUser(String u)  
   {
     try{
      int x = connect().createStatement().executeUpdate("delete from tblLogin where userid='"+u+"'");
      if(x==1) // agar user delete hua hai to - means return value is 1 - means 1 user delete hua hai to 
          return "User Successfully deleted";
      else
          return "Sorry this is Invalid Login Id for delete.";
     }catch(Exception ex) { return ex.getMessage(); }
   }
   public static String updateProfile(String u,String fn,String ln,String m,String db,String mb,String ad,String q)  
   {
     try{
      int x = connect().createStatement().executeUpdate("update tblRegis set fname='"+fn+"',lname='"+ln+"',mailid='"+m+"',dob='"+db+"',mobile='"+mb+"',address='"+ad+"',quali='"+q+"' where userid='"+u+"'");
      if(x==1)
        return "ur profile Successfully Updated.";
      else
        return "Sorry ur profile not Updated.";  
     }catch(Exception ex) { return ex.getMessage(); } 
   }
   public static String changePass(String u,String op,String np)   
   {
     try
     {
        cs = connect().prepareCall("{call changePass(?,?,?,?)}");
        cs.setString(1,u); // set the 1st ? value
        cs.setString(2,op);
        cs.setString(3,np);
        cs.registerOutParameter(4,Types.VARCHAR); // register 10th parameter as output mode for return value.
        cs.execute(); // procedure executed
        return cs.getString(4); // return out mode parameter value
     }catch(Exception ex) { return ex.getMessage(); }
   }
   public static String Vcheck(String u,String v)   
   {
     try
     {
        cs = connect().prepareCall("{call verifyId(?,?,?)}");
        cs.setString(1,u); // set the 1st ? value
        cs.setString(2,v);
        cs.registerOutParameter(3,Types.VARCHAR); // register 10th parameter as output mode for return value.
        cs.execute(); // procedure executed
        return cs.getString(3); // return out mode parameter value
     }catch(Exception ex) { return ex.getMessage(); }
   }
   public static String forget(String u)   
   {
     try
     {
        cs = connect().prepareCall("{call forget(?,?)}");
        cs.setString(1,u); // set the 1st ? value
        cs.registerOutParameter(2,Types.VARCHAR); // register 10th parameter as output mode for return value.
        cs.execute(); // procedure executed
        return cs.getString(2); // return out mode parameter value
     }catch(Exception ex) { return ex.getMessage(); }
   }
   public static String getMailId(String u,String d)   
   {
     try
     {
        cs = connect().prepareCall("{call getMailId(?,?,?)}");
        cs.setString(1,u); // set the 1st ? value
        cs.setString(2,d);
        cs.registerOutParameter(3,Types.VARCHAR); // register 10th parameter as output mode for return value.
        cs.execute(); // procedure executed
        return cs.getString(3); // return out mode parameter value
     }catch(Exception ex) { return ex.getMessage(); }
   }
   public static String sendMail(String u,String ty,String sid,String sb,String msg)
   {
     try
     {
        cs = connect().prepareCall("{call pro_send(?,?,?,?,?,?)}");
        cs.setString(1,sid); // set the 1st ? value
        cs.setString(2,ty);
        cs.setString(3,u);
        cs.setString(4,sb); 
        cs.setString(5,msg); 
        cs.registerOutParameter(6,Types.VARCHAR); // register 10th parameter as output mode for return value.
        cs.execute(); // procedure executed
        return cs.getString(6); // return out mode parameter value
     }catch(Exception ex) { return ex.getMessage(); }
   }
   public static String delMail(String u,String ty,String sno)
   {
     try
     {
        cs = connect().prepareCall("{call pro_delMail(?,?,?,?)}");
        cs.setString(1,u); // set the 1st ? value
        cs.setString(2,ty);
        cs.setString(3,sno);
        cs.registerOutParameter(4,Types.VARCHAR); // register 10th parameter as output mode for return value.
        cs.execute(); // procedure executed
        return cs.getString(4); // return out mode parameter value
     }catch(Exception ex) { return ex.getMessage(); }
   }
   public static ResultSet getInbox(String u) throws Exception
   {
      return connect().createStatement().executeQuery("select sno,senderid,subject,msg,msize,to_char(senddt,'dd-MM-YYYY - HH24:MM') as DateTime from the(select mail from tblmails where userid='"+u+"' and mtype='Inbox')");
   }
   public static ResultSet getSend(String u) throws Exception
   {
      return connect().createStatement().executeQuery("select sno,senderid,subject,msg,msize,to_char(senddt,'dd-MM-YYYY - HH24:MM') as DateTime from the(select mail from tblmails where userid='"+u+"' and mtype='Send') order by 1");
   }

}
