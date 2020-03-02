package dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;

public class CatJdbc {
	Connection con = null;
	 PreparedStatement ps=null;
    ResultSet rs=null;
   Statement statement = null;
   ResultSet res = null;
   PreparedStatement pst=null;
   PreparedStatement pstdel=null;
   String driver = "com.mysql.jdbc.Driver";
   
//   String url = "jdbc:mysql://localhost:3306/milk?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
//   String name = "root";
//   String passwd = "198110";
	
	public CatJdbc() {
		try {
            Class.forName(driver).newInstance();
            
            File f = new File("D:\\props\\db.properties");                                      
    		Properties p = new Properties();
    		p.load(new FileInputStream(f)); 
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(p.getProperty("url"), p.getProperty("name"), p.getProperty("passwd"));
             
            statement = con.createStatement();
 
        } catch (ClassNotFoundException e) {
            System.out.println("对不起，找不到这个Driver");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public List<Ca> selectCat() {
		// 查询种类
		String sql="select id,ccode,cname,cabb,remark,ts from category";
		List<Ca> list=new ArrayList<Ca>();
		try {
			
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				Ca c=new Ca();
				c.setId(rs.getInt(1));
				c.setCcode(rs.getString(2));
				c.setCname(rs.getString(3));
				c.setCabb(rs.getString(4));
				c.setRemark(rs.getString(5));
				c.setTs(rs.getDate(6));
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return list;
		

	}
	public List<Ca> selectCat_select() {
		// 查询种类
		String sql="select ccode,cname from category";
		List<Ca> list=new ArrayList<Ca>();
		try {
			
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				Ca c=new Ca();
				
				c.setCcode(rs.getString(1));
				c.setCname(rs.getString(2));
				
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return list;
		

	}

	public void insertCat(String ccode,String cname,String cabb,String remark) {
		  if (cname == null || cname.trim().length() <= 0) {
	            JOptionPane.showMessageDialog(null, "种类名称为空，请重新输入！");
	            return;
	        }
		  Date dNow = new Date( );
	      SimpleDateFormat ft =new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
	        String sql = "insert into category(ccode,cname,cabb,remark,ts) values(\"" + ccode + "\",\"" + cname + "\",\"" + cabb + "\",\"" + remark + "\",\"" +ft.format(dNow)+ "\")";
	        System.out.println("sql="+sql);
	        
	        try {
	            int a = statement.executeUpdate(sql);
	            con.close();
	            statement.close();
	            if (a == 1) {
	                JOptionPane.showMessageDialog(null, "种类添加完成！");
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "有错误！");
	            e.printStackTrace();
	        }
		
	}
    
	 public void updateCategory(String ccode,String cname,String cabb,String remark,Integer id) {
  	   String sqlUpdate="update category set ccode=? , cname=? , cabb=? , remark=? where id=?";
  	   try {
			pst=con.prepareStatement(sqlUpdate);
			pst.setString(1, ccode);
			pst.setString(2, cname);
			pst.setString(3, cabb);
			pst.setString(4, remark);
			
			pst.setInt(5, id);
			
			int a=pst.executeUpdate();
			 if (a == 1) {
	                JOptionPane.showMessageDialog(null, "种类修改成功！");
	                
	            }
			 pst.close();
			 con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
	 public void deleteCategory(Integer id) {
			// TODO Auto-generated method stub
	        String sqldel="delete from category where id="+id;
	        try {
				pstdel=con.prepareStatement(sqldel);
				int result =JOptionPane.showConfirmDialog(null, "是否删除此数据？", "请确认删除", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION){
				     //是
					int a =pstdel.executeUpdate();
					 if (a == 1) {
			                JOptionPane.showMessageDialog(null, "删除成功！");
			                
			            }
					 pstdel.close();
					 con.close();
				}else{
				    //否
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    	   
		}
}
