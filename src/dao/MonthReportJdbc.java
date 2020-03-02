package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class MonthReportJdbc {

	JdbcAll ja=new JdbcAll();
	Connection con = null;
	Statement statement = null;
	public MonthReportJdbc() {
		// TODO Auto-generated constructor stub
		con=ja.getCon();
		try {
			statement = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*public static void main(String[] args) {
		MonthReportJdbc j=new MonthReportJdbc();
		Object[][] aa=j.selectMonthReport();
		for (int i = 0; i < aa.length; i++) {
			for (int k = 0; k < aa[i].length; k++) {
				System.out.println(aa[i][k]);
			}
		}
	}*/
	public void excute_proceduer(String a,String b,String c) {
		try {
			if(con.isClosed()) {
			con=ja.getCon();
			System.out.println("a="+a+",b="+b+",c="+c);
			String sql = "{CALL ProMonthReport(?,?,?)}"; //调用存储过程 
			CallableStatement cstm = con.prepareCall(sql); //实例化对象cstm 
			cstm.setString(1, a); // 存储过程输入参数 
			cstm.setString(2, b); // 存储过程输入参数 
			cstm.setString(3, c); // 存储过程输入参数 
			cstm.execute();
			
			}
			else {
				System.out.println("a="+a+",b="+b+",c="+c);
				String sql = "{CALL ProMonthReport(?,?,?)}"; //调用存储过程 
				CallableStatement cstm = con.prepareCall(sql); //实例化对象cstm 
				cstm.setString(1, a); // 存储过程输入参数 
				cstm.setString(2, b); // 存储过程输入参数 
				cstm.setString(3, c); // 存储过程输入参数 
				cstm.execute();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	public String[] selectMonthReport_head(){
		ResultSet resultSet;
		String[] date = null;
		try {
			if(con.isClosed()) {
			con=ja.getCon();
			
			}
			statement = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			resultSet = statement.executeQuery("select * from tempcalendar3");
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			resultSet.last(); 
			int rows = resultSet.getRow();//总行数
			resultSet.beforeFirst();
			int cols=resultSetMetaData.getColumnCount();//总列数
			date=new String[cols] ;
			//列名
			for (int i = 0; i < cols; i++) {
				date[i]=resultSetMetaData.getColumnName(i+1);
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(!con.isClosed()) {
				con.close();}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return date;
	}
	public Object[][] selectMonthReport_date(){
		ResultSet resultSet;
		Object[][] date = null;
		try {
			if(con.isClosed()) {
			con=ja.getCon();
			
			}
			statement = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			resultSet = statement.executeQuery("select * from tempcalendar3");
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			resultSet.last(); 
			int rows =0;
			rows= resultSet.getRow();//总行数
			System.out.println("查询的行数resultSet.getRow()="+resultSet.getRow()+",rows="+rows);
			resultSet.beforeFirst();
			int cols=resultSetMetaData.getColumnCount();//总列数
			
			
			if(rows>1) {
				//明细数据
				int i=0;
				date=new Object[rows][cols];
				while(resultSet.next()) {
					for (int j = 0; j < cols; j++) {					
					date[i][j]=resultSet.getString(j+1).toString();
					}
					i++;
				}
				
			}
			else {
				JOptionPane.showMessageDialog(null, "查询时间内没有此客户数据！");
				date=new Object[0][0];
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(!con.isClosed()) {
				con.close();}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return date;
	}
	public String[] selectMonthReport_dateWeek(){
		ResultSet resultSet;
		String[] date1 = null ;
		try {
			if(con.isClosed()) {
			con=ja.getCon();
			
			}
			statement = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			resultSet = statement.executeQuery("select monthweek from tempcalendar1");
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			resultSet.last(); 
			int rows =0;
			rows= resultSet.getRow();//总行数
			System.out.println("查询的行数resultSet.getRow()="+resultSet.getRow()+",rows="+rows);
			resultSet.beforeFirst();
			int cols=resultSetMetaData.getColumnCount();//总列数
			
			
			if(rows>1) {
				//明细数据
				int i=0;
				date1 = new String[rows+1];
				date1[i]=" ";
				i++;
				while(resultSet.next()) {
					
					date1[i]=resultSet.getString(1);
					i++;
				}
				
			}
			else {
//				JOptionPane.showMessageDialog(null, "查询时间内没有此客户数据！");
//				date=new Object[0][0];
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(!con.isClosed()) {
				con.close();}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return date1;
	}
		
}
