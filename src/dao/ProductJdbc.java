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

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;



public class ProductJdbc {
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
	public PreparedStatement ps1;
	public ResultSet rs1;
    
    public ProductJdbc(){
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
    
	public List<ProductDao> selectProductList(String sccode,String spcode,String spname) {
		String sql="select id,ccode,cname,pcode,pname,pabb,phelpcode,price,unitname,remark from product";
		
		if(sccode.isEmpty()&&spcode.isEmpty()&&spname.isEmpty()) {
			
		}else if(sccode.isEmpty()&&spcode.isEmpty()) {
			sql+=" where pname like '%"+spname+"%'";
		}else if(sccode.isEmpty()&&spname.isEmpty()) {
			sql+=" where pcode like '%"+spcode+"'%";
		}else if("".equals(spcode)&&"".equals(spname)) {
			
			sql+=" where ccode like '%"+sccode+"%'";
		}
		else if(sccode.isEmpty()) {
			sql+=" where pcode like '%"+spcode+"%' and pname like '%"+spname+"%'";
		}
		else if(spcode.isEmpty()) {
			sql+=" where ccode like '%"+sccode+"%' and pname like '%"+spname+"%'";
		}
		else if(spname.isEmpty()) {
			sql+=" where ccode like '%"+sccode+"%' and pcode like '%"+spcode+"%'";
		}else {
			sql+=" where ccode like '%"+sccode+"%' and pcode like '%"+spcode+ "%' and pname like %'"+spname+"%'";
		}
		
		List<ProductDao> list=new ArrayList<ProductDao>();
		 try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				ProductDao pd=new ProductDao();
				pd.setId(rs.getInt(1));
				pd.setCcode(rs.getString(2));
				pd.setCname(rs.getString(3));
				pd.setPcode(rs.getString(4));
				pd.setPname(rs.getString(5));
				pd.setPabb(rs.getString(6));
				pd.setPhelpcode(rs.getString(7));
				pd.setPrice(rs.getFloat(8));
				pd.setUnitname(rs.getString(9));
				pd.setRemark(rs.getString(10));
				list.add(pd);
				
				
//				String sql="select id,ccode,cname,pcode.pname,pabb,phelpcode,price,remark from product";
			}
			rs.close();
            ps.close();
            con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
         
		
	}
	public List<ProductDao> selectProductList_select33(String sccode,String spcode,String spname) {
		String sql="select pcode,pname,unitname,price,remark from product";
		
		if(sccode.isEmpty()&&spcode.isEmpty()&&spname.isEmpty()) {
			
		}else if(sccode.isEmpty()&&spcode.isEmpty()) {
			sql+=" where pname like '%"+spname+"%'";
		}else if(sccode.isEmpty()&&spname.isEmpty()) {
			sql+=" where pcode like '%"+spcode+"'%";
		}else if("".equals(spcode)&&"".equals(spname)) {
			
			sql+=" where ccode like '%"+sccode+"%'";
		}
		else if(sccode.isEmpty()) {
			sql+=" where pcode like '%"+spcode+"%' and pname like '%"+spname+"%'";
		}
		else if(spcode.isEmpty()) {
			sql+=" where ccode like '%"+sccode+"%' and pname like '%"+spname+"%'";
		}
		else if(spname.isEmpty()) {
			sql+=" where ccode like '%"+sccode+"%' and pcode like '%"+spcode+"%'";
		}else {
			sql+=" where ccode like '%"+sccode+"%' and pcode like '%"+spcode+ "%' and pname like %'"+spname+"%'";
		}
		
		List<ProductDao> list=new ArrayList<ProductDao>();
		 try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				ProductDao pd=new ProductDao();
			
				pd.setPcode(rs.getString(1));
				pd.setPname(rs.getString(2));
				pd.setUnitname(rs.getString(3));
				pd.setPrice(rs.getFloat(4));
				
				pd.setRemark(rs.getString(5));
				list.add(pd);
				
				
//				String sql="select id,ccode,cname,pcode.pname,pabb,phelpcode,price,remark from product";
			}
			rs.close();
            ps.close();
            con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
         
		
	}
	
	public List<ProductDao> selectProductList_select(String sccode,String spcode,String spname) {
		String sql="select pcode,pname,unitname,format(price,2) as price,remark from product";
		
		if(sccode.isEmpty()&&spcode.isEmpty()&&spname.isEmpty()) {
			
		}else if(sccode.isEmpty()&&spcode.isEmpty()) {
			sql+=" where pname like '%"+spname+"%'";
		}else if(sccode.isEmpty()&&spname.isEmpty()) {
			sql+=" where pcode like '%"+spcode+"'%";
		}else if("".equals(spcode)&&"".equals(spname)) {
			
			sql+=" where ccode like '%"+sccode+"%'";
		}
		else if(sccode.isEmpty()) {
			sql+=" where pcode like '%"+spcode+"%' and pname like '%"+spname+"%'";
		}
		else if(spcode.isEmpty()) {
			sql+=" where ccode like '%"+sccode+"%' and pname like '%"+spname+"%'";
		}
		else if(spname.isEmpty()) {
			sql+=" where ccode like '%"+sccode+"%' and pcode like '%"+spcode+"%'";
		}else {
			sql+=" where ccode like '%"+sccode+"%' and pcode like '%"+spcode+ "%' and pname like %'"+spname+"%'";
		}
		
		List<ProductDao> list=new ArrayList<ProductDao>();
		 try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				ProductDao pd=new ProductDao();
			
				pd.setPcode(rs.getString(1));
				pd.setPname(rs.getString(2));
				pd.setUnitname(rs.getString(3));
				pd.setPrice(rs.getFloat(4));
				
				pd.setRemark(rs.getString(5));
				list.add(pd);
				
				
//				String sql="select id,ccode,cname,pcode.pname,pabb,phelpcode,price,remark from product";
			}
			rs.close();
            ps.close();
            con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
         
		
	}
	public void insertProduct(String ccode,String cname,String pcode,String pname,String pabb,String phelpcode,Float fa,String unitname, String remark) {
		 String sql = "insert into product(ccode,cname,pcode,pname,pabb,phelpcode,price,unitname,remark) values(\"" + ccode + "\",\"" + cname + "\",\"" + pcode + "\",\"" + pname + "\",\"" +pabb+ "\",\"" +phelpcode+ "\",\"" +fa+ "\",\""+unitname+ "\",\"" +remark+"\")";
	        System.out.println("sql="+sql);
	        
	        try {
	            int a = statement.executeUpdate(sql);
	            con.close();
	            statement.close();
	            if (a == 1) {
	                JOptionPane.showMessageDialog(null, "新增奶品完成");
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "有错误！");
	            e.printStackTrace();
	        }
	}
	public void updateProduct(String ccode1, String cname1, String pcode1, String pname1, String pabb1, String phelpcode1,
			Float fa, String unitname1, String remark1, String id1)
//	pj.updateProduct(ccode.getText(), cname.getText(), pcode.getText(), pname.getText(), pabb.getText(), phelpcode.getText(), fa,unitname.getText(), remark.getText(),id11.getText());
	{
		String sql="update product set ccode=?,cname=?,pcode=?,pname=?,pabb=?,phelpcode=?,price=?,unitname=?,remark=? where id=?";
		PreparedStatement ps2;
		try {
			ps2 = con.prepareStatement(sql);
			ps2.setString(1, ccode1);
			ps2.setString(2, cname1);
			ps2.setString(3, pcode1);
			ps2.setString(4, pname1);
			ps2.setString(5, pabb1);
			ps2.setString(6, phelpcode1);
			ps2.setFloat(7, fa);
			ps2.setString(8, unitname1);
			ps2.setString(9, remark1);
			ps2.setString(10, id1);
			
			int a=ps2.executeUpdate();
			if(a>0) {
				JOptionPane.showMessageDialog(null, "修改数据完成");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String getMaxProductId(String aa) {
		String sql="select pcode from product where ccode=? order by pcode desc LIMIT 1";
		String r1="";
		String r2="";
		String getcode="";
		try {
			ps1=con.prepareStatement(sql);
			ps1.setString(1, aa);
			rs1=ps1.executeQuery();
			
			while(rs1.next())
			{
				r1=rs1.getString(1);
			}
			
			if(r1.isEmpty()) {
				getcode=aa.concat("001");
			}else {
				if("0".equals(aa.substring(0,1))) {
					r2="0";
				}
				getcode=r2+(Integer.parseInt(r1)+1);
			}
			
//			System.out.println("最大值为："+getcode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getcode;
		
	}

	public void deleteProduct(Integer id) {
		// TODO Auto-generated method stub
		
		 String sqldel="delete from product where id="+id;
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
