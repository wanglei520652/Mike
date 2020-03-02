package dao;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream; 
import java.util.Iterator;
import java.util.Properties; 

public class JdbcAll {
	Connection con = null;
	 PreparedStatement ps=null;
    ResultSet rs=null;
   Statement statement = null;
   ResultSet res = null;
   PreparedStatement pst=null;
   PreparedStatement pstdel=null;
   String driver = "com.mysql.jdbc.Driver";

   public Connection getCon() {
	   try {
		   //

        File f = new File("D:\\props\\db.properties");                                      
		Properties p = new Properties();
		p.load(new FileInputStream(f)); 
        Class.forName(driver).newInstance();
        con = DriverManager.getConnection(p.getProperty("url"), p.getProperty("name"), p.getProperty("passwd"));
           } catch (ClassNotFoundException e) {
           System.out.println("对不起，找不到这个Driver");
           e.printStackTrace();
       } catch (SQLException e) {
           e.printStackTrace();
       } catch (Exception e) {
           e.printStackTrace();
       }
	return con;
} 
  public void connClose(){
	   try {
		if(!con.isClosed()) {
			   con.close();
		   }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }

}
