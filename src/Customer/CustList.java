package Customer;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import org.eclipse.swt.widgets.TableColumn;

import Tool.Tools;
import dao.*;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustList {

	 public JFrame frame;
	 JTable table;
	 DefaultTableModel tableModel=null;
	String[] head={"id","客户代码","客户名称","简称","助记码","区域","地址","联系人","电话","配送员代码","配送员名字","备注"};
	CustUpdate cu=null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustList window = new CustList();
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
	public CustList() throws SQLException {
		initialize();

		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	
	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize()  {
		frame = new JFrame();
		frame.setTitle("\u5BA2\u6237\u5217\u8868");
		frame.setBounds(100, 100, 1221, 592);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setFont(new Font("宋体", Font.PLAIN, 13));
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
      //测试开始
        Tools to=new Tools();
//        to.FitTableColumns(table);
        to.setColumnSize(table, 2, 30, 300, 300);
        
        
        //测试结束
      //滚动条
        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setLocation(33, 30);
        scrollPane_2.setSize(1137, 372);
        scrollPane_2.setViewportView(table);
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(e.getClickCount() == 2) {
        			//JOptionPane.showMessageDialog(null, "双击");
        			openCustUpdate();
        		}
        		
        	}
        });
        
                     
        frame.getContentPane().add(scrollPane_2,BorderLayout.CENTER);
		
		JButton button = new JButton("\u65B0\u589E");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//调用方法传值
				openInsertCust();
				
			}
		});
		button.setBounds(33, 426, 93, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u4FEE\u6539");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openCustUpdate();				
				
 
			}
		});
		button_1.setBounds(183, 426, 93, 23);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u5220\u9664");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jdbc j=new Jdbc();
				//获取管理数据的模式
		    	DefaultTableModel model = (DefaultTableModel) table.getModel();

		    	//获取选中的行数
		    	int row = table.getSelectedRow();
		    	int col = table.getSelectedColumn();
		    	//获取ID	
		    	
		    	if(table.getSelectedRow()>-1) {
		    		Integer id = (Integer)model.getValueAt(row,0);
		    		j.deleteCust(id);
					//刷新数据
					refrshCust();
		    	}
		    	else {
		    		JOptionPane.showMessageDialog(null, "请选择要删除的数据");
		    	}
				
			}
		});
		button_2.setBounds(323, 426, 93, 23);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("\u53D6\u6D88");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_3.setBounds(467, 426, 93, 23);
		frame.getContentPane().add(button_3);
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
    	String sendid = (String)model.getValueAt(row,9);
    	String sendname = (String)model.getValueAt(row,10);
    	String remark = (String)model.getValueAt(row,11);

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
         

           Tools.setColumnSize(table, 2, 30, 300, 300);
           table.setEnabled(true);
           
	}
       public void openInsertCust() {
   		cust cl=new cust(this);
   		cl.frame.setVisible(true);

   	}
       public void openCustUpdate() {
    	   
    	   if(table.getSelectedRow()>-1) {
	    	   cu=new CustUpdate(this);
	    	   cu.frame.setVisible(true);
	    	   printData(this.table);
    	   }
    	   else {
    		   JOptionPane.showMessageDialog(null, "请选择要修改的数据");
    	   }
    	 
       }
      
       
}
