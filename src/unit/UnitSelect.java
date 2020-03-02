package unit;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Customer.CustUpdate;
import Customer.cust;
import Order.OrderList;
import Order.OrderUpdate;
import Send.SendUpdate;
import dao.JdbcAll;

public class UnitSelect {

	 public JFrame frame;
		private JTable table;
		String[] head={"id","单位代码","单位名字","备注"};
		private DefaultTableModel tableModel;
		private PreparedStatement pst;
		private ResultSet rs;
		private Object[][] a;
		private SendUpdate su;
		public cust c;
		public CustUpdate cu;
		String ab;
		public OrderList ol;
		public OrderUpdate ou;
		private UnitUpdate uu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnitSelect window = new UnitSelect();
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
	public UnitSelect() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u5355\u4F4D\u5217\u8868");
		frame.setBounds(100, 100, 836, 398);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		table = new JTable();
		table.setBounds(119, 122, 314, 96);
		tableModel=new DefaultTableModel(queryData(),head){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };		

        table.addMouseListener(new MouseAdapter() {
           	@Override
           	public void mouseClicked(MouseEvent e) {
           		if(e.getClickCount() == 2) {
           			//JOptionPane.showMessageDialog(null, "双击");
           			int row1=table.getSelectedRow();
           			String sendid1;
           			String idsend;
           			idsend= table.getModel().getValueAt(row1, 0).toString(); 
           			if("null".equals(String.valueOf(table.getModel().getValueAt(row1, 1)))) {
           				 sendid1="";
           				
           			}
           			else {
           				sendid1= String.valueOf(table.getModel().getValueAt(row1, 1)); 
           			}
               		 
               		String sendname1=String.valueOf(table.getModel().getValueAt(row1, 2));
               		String remark=String.valueOf(table.getModel().getValueAt(row1, 3));
               		
               		if(c!=null) {
               			System.out.println("客户表录入配送员");
               			c.putSend(idsend,sendid1,sendname1);
               			frame.dispose();
               		}
               		else if(cu!=null){
               			System.out.println("客户修改表录入配送员");
               			cu.putstring_send(idsend,sendid1,sendname1);
               			frame.dispose();
               		}else if(ol!=null) {
               			System.out.println("订单表录入配送员");
               			ol.putstring_send(idsend,sendid1,sendname1);
               			frame.dispose();
               		}else if(ou!=null) {
               			System.out.println("订单表录入配送员");
               			ou.putstring_send(idsend,sendid1,sendname1);
               			frame.dispose();
               		}
//                    ol.putCust(custcode1, custname1, custabb1, custtel1, custaddress1,sendid,sendname);
//                    frame.dispose();
           		}
           		
           	}
           });
        
        table.setModel(tableModel);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(87, 36, 616, 230);
		scrollPane.setViewportView(table);
		frame.getContentPane().add(scrollPane);
		
		JButton button_insert = new JButton("\u65B0\u589E");
		button_insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				opendSendNew();
			}
		});
		button_insert.setBounds(87, 295, 93, 23);
		frame.getContentPane().add(button_insert);
		
		JButton button_update = new JButton("\u4FEE\u6539");
		button_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				openSendUpdate();
				
			}
		});
		button_update.setBounds(208, 295, 93, 23);
		frame.getContentPane().add(button_update);
		
		JButton button = new JButton("\u5220\u9664");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				deleteSend();
				
			}
		});
		button.setBounds(333, 295, 93, 23);
		frame.getContentPane().add(button);
	}
	public void printData(JTable table2) {
		// TODO Auto-generated method stub
		//获取选中的行数
    	int row = table2.getSelectedRow();
    	int col = table2.getSelectedColumn();
    	//获取管理数据的模式
    	DefaultTableModel model = (DefaultTableModel) table2.getModel();

    	//获取ID
    	Integer id = (Integer)model.getValueAt(row,0);
    	String unitid = (String)model.getValueAt(row,1);
    	String unitname = (String)model.getValueAt(row,2);    	
    	String remark=(String)model.getValueAt(row, 3);
    	System.out.println("选择的数据为："+id+",sendid="+unitid+",sendname="+unitname);
    	//赋值 
    	
    	uu.putstring(id,unitid,unitname,remark);
	}
	public Object[][] queryData() {
		// TODO Auto-generated method stub
		JdbcAll j=new JdbcAll();
		Connection con=j.getCon();
		String sql="select id,unitcode,unitname,remark from unit";
		try {
			System.out.println("查询sql="+sql);
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			rs.last();
			int rows=rs.getRow();			
			rs.first();			
			a=new Object[rows][4];			
			for (int i= 0; i < rows; i++) 			
			{
				a[i][0]=rs.getInt(1);
				a[i][1]=rs.getString(2);
				a[i][2]=rs.getString(3);
				a[i][3]=rs.getString(4);				
				rs.next();
			}
		}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(!con.isClosed()) {
					con.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return a;
		}


}
