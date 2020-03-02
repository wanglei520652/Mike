package Order;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.eltima.components.ui.DatePicker;

import Customer.CustListSelect;
import Send.SendList;
import Send.SendUpdate;
import Tool.JTableComm;
import dao.JdbcAll;
import dao.OrderJdbc;
import test.SwingCommonPrinitTools;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.Color;


/**
 * @author Administrator
 * 订单查询页面
 *
 */
public class OrderSelect {
	public JFrame frame;
	public JTextField ocustname;
	public JTextField sendname;
	public JTable table_order;
	public JTable table_orderdetail;
	public JButton button_order;
	public JTextField custcode;
	public JTextField sendid;
	public Object[][] obja;
	public String[] head= {"订单号","日期","客户代码","客户名称","电话","地址","配送员代码","配送员","总数量","总金额"};
	public String[] head_detail={"商品代码","商品名称","单位","规格","数量","单价","金额","备注"};
	 DatePicker datepick_begin;
	 DatePicker datepick_end;
	 public String dateString_bengin;
	 public String dateString_end;
	private SimpleDateFormat formatter;
	private DefaultTableModel tableModel;
	private DefaultTableModel tableModel2;
	public OrderUpdate ou;

	/**
	 * Launch the application.
	 * 订单查询表
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderSelect window = new OrderSelect();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OrderSelect() {
		initialize();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u8BA2\u5355\u67E5\u8BE2");
		frame.setBounds(20, 20, 1335, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5F00\u59CB\u65E5\u671F");
		label.setBounds(52, 45, 54, 15);
		frame.getContentPane().add(label);
		
		
		//时间控件开始日期
				//时间控件
						
				        datepick_begin = getDatePicker_begin(); 
				        
				        frame.getContentPane().add(datepick_begin);
				
				//时间控件结束日期
				      //时间控件
						
						datepick_end = getDatePicker_end(); 
						frame.getContentPane().add(datepick_end);
						//
						
						 
						
						 
		
		JButton button_cust = new JButton("\u5BA2\u6237\u9009\u62E9");
		button_cust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openCust();
			}
		});
		button_cust.setBounds(52, 82, 93, 23);
		frame.getContentPane().add(button_cust);
		
		ocustname = new JTextField();
		ocustname.setBounds(241, 83, 214, 21);
		frame.getContentPane().add(ocustname);
		ocustname.setColumns(10);
		
		JLabel label_1 = new JLabel("\u7ED3\u675F\u65E5\u671F");
		label_1.setBounds(262, 45, 54, 15);
		frame.getContentPane().add(label_1);
		
		sendname = new JTextField();
		sendname.setBounds(659, 83, 93, 21);
		frame.getContentPane().add(sendname);
		sendname.setColumns(10);
		
		JScrollPane scrollPane_order = new JScrollPane();
		scrollPane_order.setBounds(46, 129, 738, 433);
		frame.getContentPane().add(scrollPane_order);
		
		
		
		table_order = new JTable();
		table_order.setBounds(27, 70, 956, 221);
		tableModel=new DefaultTableModel(new Object[0][8],head){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        table_order.setModel(tableModel);
        
        JTableComm.showColumn(table_order, 3, 200);
        JTableComm.HiddenCell(table_order, 4);//“电话”列不显示
		JTableComm.HiddenCell(table_order, 5);//"地址"列不显示
		JTableComm.HiddenCell(table_order, 6);//“配送员代码”不显示

		scrollPane_order.setViewportView(table_order);
		table_order.addMouseListener(new MouseAdapter() {
			@Override
			//订单列表的双击事件
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					//插入一条新的订单明细数据
					int sr=table_order.rowAtPoint(e.getPoint());
					String a1=table_order.getModel().getValueAt(sr, 0).toString();
			
					
					uu(a1);
        			
        			
        		}
			}
		});
		
		JScrollPane scrollPane_orderdetail = new JScrollPane();
		scrollPane_orderdetail.setBounds(794, 140, 485, 422);
		frame.getContentPane().add(scrollPane_orderdetail);
		
		
		
		table_orderdetail = new JTable();
		scrollPane_orderdetail.setViewportView(table_orderdetail);
		tableModel2=new DefaultTableModel(new Object[0][8],this.head_detail){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        table_orderdetail.setModel(tableModel2);
		
		scrollPane_order.setViewportView(table_order);
		
		
		
		button_order = new JButton("\u8BA2\u5355\u67E5\u8BE2");
		button_order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openSelectOderList();
				
			}
		});
		button_order.setBounds(758, 82, 93, 23);
		frame.getContentPane().add(button_order);
		
		custcode = new JTextField();
		custcode.setBounds(162, 83, 66, 21);
		frame.getContentPane().add(custcode);
		custcode.setColumns(10);
		
		sendid = new JTextField();
		sendid.setBounds(586, 83, 66, 21);
		frame.getContentPane().add(sendid);
		sendid.setColumns(10);
		
		JButton button_insert = new JButton("\u65B0\u589E");
		button_insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OrderList ol=new OrderList();
				ol.frame.setVisible(true);
			}
		});
		button_insert.setBounds(48, 585, 93, 23);
		frame.getContentPane().add(button_insert);
		
		JButton button_update = new JButton("\u4FEE\u6539");
		button_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openOrderUpdate();
			}
		});
		button_update.setBounds(153, 585, 93, 23);
		frame.getContentPane().add(button_update);
		
		JButton button_delete = new JButton("\u5220\u9664");
		button_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteSend();
			}
		});
		button_delete.setBounds(267, 585, 93, 23);
		frame.getContentPane().add(button_delete);
		
		JButton button = new JButton("\u5355\u4E2A\u6253\u5370");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				excelPrint();
				
			}
		});
		button.setBounds(888, 82, 93, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_1.setBounds(381, 585, 93, 23);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u914D\u9001\u5458\u9009\u62E9");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openSend();
			}
		});
		button_2.setBounds(463, 82, 113, 23);
		frame.getContentPane().add(button_2);
		
		JLabel label_2 = new JLabel("\u8BA2\u5355\u5217\u8868");
		label_2.setForeground(Color.RED);
		label_2.setBounds(52, 115, 54, 15);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u8BA2\u5355\u660E\u7EC6\u6570\u636E");
		label_3.setForeground(Color.RED);
		label_3.setBounds(801, 115, 93, 15);
		frame.getContentPane().add(label_3);
	}

	protected void openSend() {
		// TODO Auto-generated method stub
		SendList sl=new SendList(this);
		sl.frame.setVisible(true);
		
	}

	protected void excelPrint() {
		// TODO Auto-generated method stub
		int row=this.table_order.getSelectedRow();
		int table_detail_num=this.table_orderdetail.getRowCount();
		if(table_detail_num>0) {
		String orderid=table_order.getModel().getValueAt(row, 0).toString();
		String custname=table_order.getModel().getValueAt(row, 3).toString();
		String orderdate=table_order.getModel().getValueAt(row, 1).toString();
		String sendname=table_order.getModel().getValueAt(row, 7).toString();
		String total_amout=table_order.getModel().getValueAt(row, 9).toString();
		
		SwingCommonPrinitTools tt=new SwingCommonPrinitTools();
		
    	tt.printTable(this.table_orderdetail.getModel(), "销 售 单",custname,orderid,orderdate,sendname,total_amout,"北京百和乐商贸有限公司");
		}
		else {
			JOptionPane.showMessageDialog(null, "请选择需要打印的订单");
		}
	}

	protected void openOrderUpdate() {
		// TODO Auto-generated method stub
		ou=new OrderUpdate(this);
		OrderUpdate.frame.setVisible(true);
		printData(this.table_order);
		
	}
	public void printData(JTable table2) {
		// TODO Auto-generated method stub
		//获取选中的行数
    	int row = table2.getSelectedRow();
    	int col = table2.getSelectedColumn();
    	//获取管理数据的模式
    	DefaultTableModel model = (DefaultTableModel) table2.getModel();

    	//获取ID
    	String orderid = (String)model.getValueAt(row,0);
    	String odate = (String)model.getValueAt(row,1);
    	String custcode = (String)model.getValueAt(row,2); 
    	String custname = (String)model.getValueAt(row,3);    	
    	String custtel=(String)model.getValueAt(row, 4);
    	String custaddress=(String)model.getValueAt(row, 5);
    	String sendcode=(String)model.getValueAt(row, 6);
    	String sendname=(String)model.getValueAt(row, 7);
    	String total_num=(String)model.getValueAt(row, 8);
    	String total_amout=(String)model.getValueAt(row, 9);
   	//赋值 

//    	ou.putmodel(this.table_orderdetail.getModel());
    	ou.putstring(orderid,odate,custcode,custname,custtel,custaddress,sendcode,sendname,total_num,total_amout);
    	ou.putOrderdetail(orderid);
	}

	protected void uu(String a1) {
		// TODO Auto-generated method stub
		
		OrderJdbc oj=new OrderJdbc();
		
		refeshdata(table_orderdetail,oj.selectOrderDetail(a1),this.head_detail);
	}

	protected void openCust() {
		// TODO Auto-generated method stub
		CustListSelect cs;
		try {
			cs = new CustListSelect(this);
			cs.frame.setVisible(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void openSelectOderList() {
		// TODO Auto-generated method stub
		OrderJdbc oj=new OrderJdbc();
		
		formatter = new SimpleDateFormat("yyyy-MM-dd");

		Date bengine =(Date) datepick_begin.getValue();
		dateString_bengin = formatter.format(bengine);

		Date bengind =(Date) this.datepick_end.getValue();
		dateString_end = formatter.format(bengind);
		obja=oj.selectOrder(this.dateString_bengin, this.dateString_end, this.ocustname.getText(), this.sendname.getText());
        //刷新数据
		refeshdata(table_order,obja,head);
		
		JTableComm.HiddenCell(table_order, 4);//“电话”列不显示
		JTableComm.HiddenCell(table_order, 5);//"地址"列不显示
		JTableComm.HiddenCell(table_order, 6);//“配送员代码”不显示
		JTableComm.showColumn(table_order, 3, 200);
      
	}
	public void refeshdata(JTable jj,Object[][] oo,String[] hh){
		 DefaultTableModel tableModel2 = new DefaultTableModel(oo,hh){
	        
			private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column)
	            {
	                return false;
	            }
	        };
	        jj.setModel(tableModel2);
	        

	        
	        jj.setVisible(true);
	        

	}

	public void putCust(String custcode1, String custname1, String sendid2, String sendname2) {
		// TODO Auto-generated method stub
		this.custcode.setText(custcode1);
		this.ocustname.setText(custname1);
		this.sendid.setText(sendid2);
		this.sendname.setText(sendname2);
		
	}
	public static DatePicker getDatePicker_begin() {
		final DatePicker datepick;
		// 格式
        String DefaultFormat = "yyyy-MM-dd";
        // 当前时间
        Date date = new Date();
        // 字体
        Font font = new Font("Times New Roman", Font.BOLD, 14);
  
        Dimension dimension = new Dimension(177, 24);
  
        int[] hilightDays = { 1, 3, 5, 7 };
  
        int[] disabledDays = { 4, 6, 5, 9 };
  
        datepick = new DatePicker(date, DefaultFormat, font, dimension);
  
        datepick.setLocation(137, 83);
        datepick.setBounds(120, 40, 110, 24);
      
        // 设置国家
        datepick.setLocale(Locale.CHINA);
        
        // 设置时钟面板可见
        datepick.setTimePanleVisible(false);
		return datepick;
	}
	public static DatePicker getDatePicker_end() {
		final DatePicker datepick;
		// 格式
//        String DefaultFormat = "yyyy-MM";
        String DefaultFormat = "yyyy-MM-dd";
        // 当前时间
        Date date = new Date();
        // 字体
        Font font = new Font("Times New Roman", Font.BOLD, 14);
  
        Dimension dimension = new Dimension(177, 24);
  
        int[] hilightDays = { 1, 3, 5, 7 };
  
        int[] disabledDays = { 4, 6, 5, 9 };
  
        datepick = new DatePicker(date, DefaultFormat, font, dimension);
  
        datepick.setLocation(137, 83);
        datepick.setBounds(320, 40, 110, 24);
       
        // 设置国家
        datepick.setLocale(Locale.CHINA);
        
        // 设置时钟面板可见
        datepick.setTimePanleVisible(false);
		return datepick;
	}
	public void deleteSend() {
		// TODO Auto-generated method stub
		JdbcAll ja=new JdbcAll();
		Connection con = ja.getCon();
		String sql_orderdetail="delete from orderdetail where orderid=?";
		String sql_order="delete from order1 where orderid=?";
		int row=this.table_order.getSelectedRow();
		String rowdate=table_order.getModel().getValueAt(row, 0).toString();
		try {
			System.out.println("删除订单明细="+sql_orderdetail);
			System.out.println("删除订单表头数据="+sql_order);
			PreparedStatement ps_orderdetail = con.prepareStatement(sql_orderdetail);
			PreparedStatement ps_order = con.prepareStatement(sql_order);
			
			ps_orderdetail.setString(1, rowdate);
			ps_order.setString(1, rowdate);			
			int result =JOptionPane.showConfirmDialog(null, "是否删除此数据？", "请确认删除", JOptionPane.YES_NO_OPTION);
			if(result==JOptionPane.YES_OPTION){
			     //是
				int a =ps_orderdetail.executeUpdate();
				 if (a >0) {
					   int zz=  ps_order.executeUpdate();
					   if(zz>0) {
						   JOptionPane.showMessageDialog(null, "删除成功！");
					   }
		                
		                
		            }
				 ps_orderdetail.close();
				 ps_order.close();
				 con.close();
			}else{
			    //否
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//刷新数据
		openSelectOderList();
		
	}

	public void putstring_send(String idsend, String sendid1, String sendname1) {
		// TODO Auto-generated method stub
		this.sendid.setText(sendid1);
		this.sendname.setText(sendname1);
	}
}
