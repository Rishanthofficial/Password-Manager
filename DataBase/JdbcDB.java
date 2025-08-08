package DataBase;
 
import java.sql.*;
import java.util.ArrayList;
 
//import java.util.*;
 
public class JdbcDB {
    String url="jdbc:mysql://localhost:3306/test";  
    Connection con = null;       
    Statement st = null;
    ResultSet rs = null;
    String query;
 
    public void StartConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,"root","");
            System.out.println("Connection Established successfully");
            st = con.createStatement();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
 
    public void StoreData(String domain, String user,String password){
        try{
            query = "INSERT INTO passwordsdb VALUES('"+domain+"','"+user+"','"+password+"')";
            st.executeUpdate(query);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
 
    public void ChangePassword(String domain, String user,String password){
        try{
            query = "UPDATE passwordsdb SET password = '"+password+"' WHERE domain = '"+domain+"' AND username = '"+user+"';";
            st.executeUpdate(query);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
 
    public void CloseConnection(){
        try{
            con.close();
            System.out.println("Connection Closed Successfully");
        }
        catch(Exception e){
            System.out.println("Failure");
        }
    }
 
    public ArrayList<String> retrieveData(String domain,String username){
        ArrayList<String> dataList = new ArrayList<>();
        if(username.isEmpty()){
            query = "SELECT * FROM passwordsdb WHERE domain= '"+domain+"';";
        }
        else{
            query = "SELECT * FROM passwordsdb WHERE domain = '"+domain+"' AND username = '"+username+"';";
        }
        try{
        rs = st.executeQuery(query);
        
        while(rs.next()){
            String st = "";
            st += rs.getString(1)+" ";
            st += rs.getString(2)+" ";
            st += rs.getString(3)+" ";
            dataList.add(st);
        }    
        }
        catch(Exception e){
            System.out.println("Query Failure");
        }
        
        return dataList;
    }
 
} 