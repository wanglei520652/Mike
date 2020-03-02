package Customer;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Month.MonthReport;
import Order.OrderList;
import Order.OrderListEmpty;
import Order.OrderSelect;
import Order.OrderUpdate;
import Tool.Tools;
import dao.Customer;
import dao.Jdbc;

public class CustListSelect {

	 public JFrame frame;
	 JTable table;
	 DefaultTableModel tableModel=null;
	String[] head={"id","客户代码","客户名称","简称","助记码","区域","地址","联系人","电话","配送员代码","配送员名字","备注"};
	CustUpdate cu=null;
	OrderList ol;
	OrderListEmpty ole;
	MonthReport mr;
	public OrderSelect os;
	public OrderUpdate ou;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustListSelect window = new CustListSelect();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public CustListSelect() throws SQLException {
		initialize();

		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public CustListSelect(OrderList ol) throws SQLException {
		initialize();
		this.ol=ol;

		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public CustListSelect(MonthReport mr) throws SQLException {
		initialize();
		this.mr=mr;

		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public CustListSelect(OrderSelect os) throws SQLException {
		initialize();
		this.os=os;

		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public CustListSelect(OrderListEmpty ole) throws SQLException {
		initialize();
		this.ole=ole;

		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public CustListSelect(OrderUpdate ou) throws SQLException {
		initialize();
		this.ou=ou;

		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	
	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize()  {
		frame = new JFrame();
		frame.setTitle("\u5BA2\u6237\u5217\u8868");
		frame.setBounds(100, 100, 1062, 453);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
//		table.setCellSelectionEnabled(true);
//		table.setColumnSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setBounds(27, 70, 956, 221);
		tableModel=new DefaultTableModel(queryData(),head){
           public boolean isCellEditable(int row, int column)
           {
               return false;
           }
       };
       
       table.setModel(tableModel);
       Tools.setColumnSize(table, 2, 50, 300, 200);
     //滚动条
       JScrollPane scrollPane_2 = new JScrollPane();
       scrollPane_2.setLocation(37, 35);
       scrollPane_2.setSize(979, 307);
       scrollPane_2.setViewportView(table);
       table.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent e) {
       		if(e.getClickCount() == 2) {
       			//JOptionPane.showMessageDialog(null, "双击");
       			int row1=table.getSelectedRow();
       			String custcode1;
       			if("null".equals(String.valueOf(table.getModel().getValueAt(row1, 1)))) {
       				 custcode1="";
       				
       			}
       			else {
       				custcode1= String.valueOf(table.getModel().getValueAt(row1, 1)); 
       			}
           		 
           		String custname1=String.valueOf(table.getModel().getValueAt(row1, 2));
           		String custabb1=String.valueOf(table.getModel().getValueAt(row1, 3));
           		String custtel1=String.valueOf(table.getModel().getValueAt(row1, 8));
           		String custaddress1=String.valueOf(table.getModel().getValueAt(row1, 6));
           		String sendid=String.valueOf(table.getModel().getValueAt(row1, 9));
           		String sendname=String.valueOf(table.getModel().getValueAt(row1, 10));
           		if(ol!=null) {
           			ol.putCust(custcode1, custname1, custabb1, custtel1, custaddress1,sendid,sendname);
                    frame.dispose();
           			
           		}else if(os!=null) {
           			os.putCust(custcode1, custname1,sendid,sendname);
                    frame.dispose();
           		}
           		else if(ole!=null) {
           			ole.putCust(custcode1, custname1, custabb1, custtel1, custaddress1,sendid,sendname);
                   frame.dispose();
           		}
           		else if(ou!=null) {
           			ou.putCust(custcode1, custname1, custabb1, custtel1, custaddress1,sendid,sendname);
                    frame.dispose();
           		}
                
       		}
       		
       	}
       });
       
                    
       frame.getContentPane().add(scrollPane_2,BorderLayout.CENTER);
       
       JButton button = new JButton("\u63D2\u5165");
       button.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       		int row1=table.getSelectedRow();
   			String custcode1;
   			if("null".equals(String.valueOf(table.getModel().getValueAt(row1, 1)))) {
   				 custcode1="";
   				
   			}
   			else {
   				custcode1= String.valueOf(table.getModel().getValueAt(row1, 1)); 
   			}
       		 
       		String custname1=String.valueOf(table.getModel().getValueAt(row1, 2));
       		String custabb1=String.valueOf(table.getModel().getValueAt(row1, 3));
       		String custtel1=String.valueOf(table.getModel().getValueAt(row1, 8));
       		String custaddress1=String.valueOf(table.getModel().getValueAt(row1, 6));
       		String sendid1=String.valueOf(table.getModel().getValueAt(row1, 9));
       		String sendname1=String.valueOf(table.getModel().getValueAt(row1, 10));
            ol.putCust(custcode1, custname1, custabb1, custtel1, custaddress1,sendid1,sendname1);
            frame.dispose();
       	}
       });
       button.setBounds(401, 363, 93, 23);
       frame.getContentPane().add(button);
	}

   public Object[][] queryData(){
   	Jdbc jj=new Jdbc();
       List<Customer> list=jj.selectCustList();
       Object[][] data = new Object[list.size()][head.length];
   
       for(int i=0;i<list.size();i++){
           for(int j=0;j<head.length;j++){
               data[i][0]=list.get(i).getId();
               data[i][1]=list.get(i).getCustcode();
               data[i][2]=list.get(i).getCustname();
               data[i][3]=list.get(i).getAbbreviation();
               data[i][4]=list.get(i).getHelpcode();
               data[i][5]=list.get(i).getArea();
               data[i][6]=list.get(i).getAddress();
               data[i][7]=list.get(i).getContact();
               data[i][8]=list.get(i).getTel();
               data[i][9]=list.get(i).getSendid();
               data[i][10]=list.get(i).getSendname();
               data[i][11]=list.get(i).getRemark();
           }
       }
       return data;
   }
   private void printData(JTable table){

   	//获取到所有行数 int row = table.getRowCount();int col = table.getColumnCount();   	

   	//获取选中的行数
   	int row = table.getSelectedRow();
   	int col = table.getSelectedColumn();
   	//获取管理数据的模式
   	DefaultTableModel model = (DefaultTableModel) table.getModel();

   	//获取ID
   	Integer id = (Integer)model.getValueAt(row,0);
   	String custcode = (String)model.getValueAt(row,1);
   	String custname = (String)model.getValueAt(row,2);
   	String abbreviation=(String)model.getValueAt(row,3);
   	String helpcode=(String)model.getValueAt(row, 4);
   	
   	String area = (String)model.getValueAt(row,5);    	
   	String address = (String)model.getValueAt(row,6);
   	String contact = (String)model.getValueAt(row,7);
   	String tel = (String)model.getValueAt(row,8);
   	String remark = (String)model.getValueAt(row,9);
   	String sendid="";
   	String sendname="";

   	//赋值    	
   	cu.putstring(id,custname,area,address,contact,tel,remark,custcode,abbreviation,helpcode,sendid,sendname);
   	
   	//获取列名
   	String a = model.getColumnName(col);

   	

   	}
   //刷新数据
      public void refrshCust() {
		   	   tableModel=new DefaultTableModel(queryData(),head){
              public boolean isCellEditable(int row, int column)
              {
                  return false;
              }
          };
          
          table.setModel(tableModel);
          table.setEnabled(true);
          
	}
      public void openCustUpdate() {
   	   
   	   if(table.getSelectedRow()>-1) {
	    	   cu=new CustUpdate();
	    	   cu.frame.setVisible(true);
	    	   printData(this.table);
   	   }
   	   else {
   		   JOptionPane.showMessageDialog(null, "请选择要修改的数据");
   	   }
   	 
      }
}
