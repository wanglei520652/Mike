package dao;

import Tool.LoggableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class OrderJdbc {
	JdbcAll ja=new JdbcAll();
	public Connection con = null;
	public Statement statement = null;
	public Statement statement_delete_order = null;
	public Statement statement_delete_orderdetail = null;
	public PreparedStatement ps;
	public ResultSet rs;
	public Object[][] a;
	public Object[][] obja;
	private Statement st;
	private Object[][] obja_detail;
	public OrderJdbc() {
		// TODO Auto-generated constructor stub
		con=ja.getCon();
		try {
			statement = con.createStatement();
			statement_delete_order=con.createStatement();
			statement_delete_orderdetail=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void delete_order_and_detail(String orderid) {
		String sql_order="delete from order1 where orderid=?";
		String sql_orderdetail="delete from orderdetail where orderid=?";
		
		
	}
	public void insertOrder(String orderid,String odate,String custcode,String custname,String custabb,String custtel,String custaddress,String sendid,String sendname, String total_num, String total_amout) {
		
		 String sql = "insert into order1(orderid,odate,custcode,custname,custabb,custtel,custaddress,sendid,sendname,total_num,total_amout) values(\"" + orderid + "\",\"" + odate + "\",\"" + custcode + "\",\"" + custname + "\",\"" + custabb + "\",\"" + custtel +  "\",\"" +custaddress +  "\",\""+sendid +  "\",\""+sendname +  "\",\"" +total_num +  "\",\"" +  total_amout+"\")";
	        System.out.println("sql="+sql);
	        try {
	            int a = statement.executeUpdate(sql);
	            
	            if (a == 1) {
//	                JOptionPane.showMessageDialog(null, "订单添加完成！");
	            }
	        } catch (SQLException e) {
//	            JOptionPane.showMessageDialog(null, "对不起订单添加有问题！");
	            e.printStackTrace();
	        }
	}
	public void insertOrderDetel(String orderid,String orderdetailid,String productid,String productname,Float productnum,Float productprice,Float amount,String unitname,String spec,String bz) {
		
		 String sql = "insert into orderdetail(orderid,orderdetailid,productid,productname,productnum,productprice,amount,unitname,spec,bz) values(\"" + orderid + "\",\"" + orderdetailid + "\",\"" + productid + "\",\"" + productname + "\",\"" + productnum + "\",\"" + productprice + "\",\"" + amount + "\",\""+ unitname + "\",\""+ spec + "\",\"" + bz+"\")";
	        System.out.println("sql="+sql);
	        try {
	            int a = statement.executeUpdate(sql);
	            if (a == 1) {
//	                JOptionPane.showMessageDialog(null, "订单明细添加完成！");
	            }
	        } catch (SQLException e) {
//	            JOptionPane.showMessageDialog(null, "对不起订单明细有问题！");
	            e.printStackTrace();
	        }
	}
	public void insertOrderDetel2(String orderid,String orderdetailid,String productid,String productname,String productnum,String productprice,String amount,String unitname,String spec,String bz) {
		
		 String sql = "insert into orderdetail(orderid,orderdetailid,productid,productname,productnum,productprice,amount,unitname,spec,bz) values(\"" + orderid + "\",\"" + orderdetailid + "\",\"" + productid + "\",\"" + productname + "\"," + productnum + "," + productprice + "," + amount + ",\""+ unitname + "\",\""+ spec + "\",\"" + bz+"\")";
	        System.out.println("sql="+sql);
	        try {
	            int a = statement.executeUpdate(sql);
	            if (a == 1) {
//	                JOptionPane.showMessageDialog(null, "订单明细添加完成！");
	            }
	        } catch (SQLException e) {
//	            JOptionPane.showMessageDialog(null, "对不起订单明细有问题！");
	            e.printStackTrace();
	        }
	}
	public Object[][] selectOrder(String begindate,String enddate,String custname,String sendname) {
		
		
		String sql="select orderid,odate,custcode,custname,custtel,custaddress,sendid,sendname,total_num,total_amout from order1 where 1=1 ";
		if(!"".equals(begindate)) {
			sql=sql+ " and odate>='"+begindate+"'";
		}if(!"".equals(enddate)) {
			sql=sql+ " and odate<='"+enddate+"'";
		}if(!"".equals(custname)) {
			sql=sql+" and custname='"+custname+"'";
		}if(!"".equals(sendname)) {
			sql=sql+ " and sendname='"+sendname+"'";
		}
		System.out.println("sql="+sql);
		JdbcAll ja=new JdbcAll();
		con=ja.getCon();
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			((ResultSet) rs).last();
			int rows=rs.getRow();			
			rs.first();			
			obja = new Object[rows][10];			
			for (int i= 0; i < rows; i++) 			
			{
				obja[i][0]=rs.getString(1);
				obja[i][1]=rs.getString(2);
				obja[i][2]=rs.getString(3);
				obja[i][3]=rs.getString(4);
				obja[i][4]=rs.getString(5);
				obja[i][5]=rs.getString(6);
				obja[i][6]=rs.getString(7);
				obja[i][7]=rs.getString(8);
				obja[i][8]=rs.getString(9);
				obja[i][9]=rs.getString(10);
//				System.out.println("订单号="+obja[i][0]+",日期="+obja[i][1]+",客户名称="+obja[i][2]);
				rs.next();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(!con.isClosed()) {
					con.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return obja;
		
	}
	public void closeOrderJdbc() {
		
		try {
			if(!con.isClosed()) {
				con.close();
			}
			if(!statement.isClosed()) {
				statement.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	public Object[][] selectOrderDetail(String orderid){
		String sql="select productid,productname,unitname,spec,productnum,productprice,amount,bz from orderdetail where orderid='"+orderid+"'";
		
		System.out.println("sql="+sql);
		JdbcAll ja=new JdbcAll();
		con=ja.getCon();
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			((ResultSet) rs).last();
			int rows=rs.getRow();			
			rs.first();			
			obja_detail = new Object[rows][8];			
			for (int i= 0; i < rows; i++) 			
			{
				obja_detail[i][0]=rs.getString(1);
				obja_detail[i][1]=rs.getString(2);
				obja_detail[i][2]=rs.getString(3);
				obja_detail[i][3]=rs.getString(4);
				obja_detail[i][4]=rs.getString(5);
				obja_detail[i][5]=rs.getString(6);
				obja_detail[i][6]=rs.getString(7);
				obja_detail[i][7]=rs.getString(8);
				System.out.println("金额="+rs.getString(7));
//				System.out.println("订单号="+obja[i][0]+",日期="+obja[i][1]+",客户名称="+obja[i][2]);
				rs.next();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(!con.isClosed()) {
					con.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return obja_detail;
		
	}
	

}
