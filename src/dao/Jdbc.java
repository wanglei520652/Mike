package dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Jdbc {

	Connection con = null;
	 PreparedStatement ps=null;
     ResultSet rs=null;
    Statement statement = null;
    ResultSet res = null;
    PreparedStatement pst=null;
    PreparedStatement pstdel=null;
    String driver = "com.mysql.jdbc.Driver";
    
//    String url = "jdbc:mysql://localhost:3306/milk?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
//    String name = "root";
//    String passwd = "198110";
    public Jdbc() {
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jdbc j=new Jdbc();
//		j.insertCust("9测试用户", "朝阳区", "劲松", "联系人aa", "1234567", "备注1111","custcode","ab","helpcode");

	}
	//用户注册功能的实现，添加数据
    public void insertCust(String custname, String area,String address,String contact,String tel,String remark,String custcode,String abbreviation,String helpcode,String sendid,String sendname) {
        if (custname == null || custname.trim().length() <= 0) {
            JOptionPane.showMessageDialog(null, "客户名称为空，请重新输入！");
            return;
        }
        String sql = "insert into custs(custname,area,address,contact,tel,remark,custcode,abbreviation,helpcode,sendid,sendname) values(\"" + custname + "\",\"" + area + "\",\"" + address + "\",\"" + contact + "\",\"" + tel + "\",\"" + remark +  "\",\"" + custcode+ "\",\"" + abbreviation+ "\",\"" + helpcode+ "\",\"" + sendid+ "\",\"" + sendname+"\")";
        System.out.println("sql="+sql);
        try {
            int a = statement.executeUpdate(sql);
            con.close();
            statement.close();
            if (a == 1) {
                JOptionPane.showMessageDialog(null, "客户添加完成！");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "对不起该用户名已经有了！");
            e.printStackTrace();
        }
    }
    

       public List<Customer> selectCustList(){
    	   String sql="select id,custcode,custname,abbreviation,helpcode,area,address,contact,tel,sendid,sendname,remark from custs";
    	   List<Customer> list=new ArrayList<Customer>();
    	   try {
			
    		   ps=con.prepareStatement(sql);
               rs=ps.executeQuery();
               
               while(rs.next()) {
            	   Customer user=new Customer();
            	   user.setId(rs.getInt(1));
            	   user.setCustcode(rs.getString(2));
            	   user.setCustname(rs.getString(3));
            	   user.setAbbreviation(rs.getString(4));
            	   user.setHelpcode(rs.getString(5));
            	   user.setArea(rs.getString(6));
            	   user.setAddress(rs.getString(7));
            	   user.setContact(rs.getString(8));
            	   user.setTel(rs.getString(9));            	   
            	   user.setSendid(rs.getString(10));             	   
            	   user.setSendname(rs.getString(11)); 
            	   user.setRemark(rs.getString(12)); 
            	   
            	   
            	   list.add(user);
            	   
            	   
               }
               rs.close();
               ps.close();
               con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("数据库错误");
		}
    	   
		return list;
    	   
       }
       public void updateCust(String custname,String area,String address,String contact,String tel,String remark,String custcode,String abbreviation,String helpcode,Integer id,String sendid,String sendname) {
    	   String sqlUpdate="update custs set custname=? , area=? , address=? , contact=? , tel=? , remark=?,custcode=?,abbreviation=?,helpcode=? ,sendid=? ,sendname=?  where id=?";
    	   try {
			pst=con.prepareStatement(sqlUpdate);
			pst.setString(1, custname);
			pst.setString(2, area);
			pst.setString(3, address);
			pst.setString(4, contact);
			pst.setString(5, tel);
			pst.setString(6, remark);
			pst.setString(7, custcode);
			pst.setString(8, abbreviation);
			pst.setString(9, helpcode);
			pst.setString(10, sendid);
			pst.setString(11, sendname);
			
			
			pst.setInt(12, id);
			
			int a=pst.executeUpdate();
			 if (a == 1) {
	                JOptionPane.showMessageDialog(null, "客户修改成功！");
	                
	            }
			 pst.close();
			 con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       }

       public void deleteCust(Integer id) {
		// TODO Auto-generated method stub
        String sqldel="delete from custs where id="+id;
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
