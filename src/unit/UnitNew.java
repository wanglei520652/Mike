package unit;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Send.SendList;
import dao.JdbcAll;

public class UnitNew {

	 JFrame frame;
		private JTextField unitcode;
		private JTextField unitname;
		private JTextField remark;
		private PreparedStatement ps;
//		SendList sl =new SendList();
		public UnitList ul;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnitNew window = new UnitNew();
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
	public UnitNew() {
		initialize();
	}
	public UnitNew(UnitList ul) {
		this.ul=ul;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 513, 311);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5355\u4F4D\u4EE3\u7801");
		label.setBounds(58, 45, 89, 15);
		frame.getContentPane().add(label);
		
		unitcode = new JTextField();
		unitcode.setBounds(123, 42, 100, 21);
		frame.getContentPane().add(unitcode);
		unitcode.setColumns(10);
		
		JLabel label22 = new JLabel("\u5355\u4F4D\u540D\u79F0");
		label22.setBounds(261, 45, 100, 15);
		frame.getContentPane().add(label22);
		
		unitname = new JTextField();
		unitname.setBounds(334, 42, 123, 21);
		frame.getContentPane().add(unitname);
		unitname.setColumns(10);
		
		JLabel label_2 = new JLabel("\u5907\u6CE8");
		label_2.setBounds(60, 88, 54, 15);
		frame.getContentPane().add(label_2);
		
		remark = new JTextField();
		remark.setBounds(123, 85, 334, 21);
		frame.getContentPane().add(remark);
		remark.setColumns(10);
		
		JButton button = new JButton("\u65B0\u589E");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insertSend(unitcode.getText(),unitname.getText(),remark.getText());
				frame.dispose();
				dd();
				
			}
		});
		button.setBounds(54, 161, 93, 23);
		frame.getContentPane().add(button);
	}

	protected void dd() {
		// TODO Auto-generated method stub
		System.out.println("进入刷新页面");
		this.ul.RefrshTable();
		
	}

	protected void insertSend(String a,String b,String c) {
		// TODO Auto-generated method stub
		JdbcAll j=new JdbcAll();
		Connection con=j.getCon();
		String sql="insert into unit(unitcode,unitname,remark) VALUES(?,?,?)";
		
			 try {
				ps= con.prepareStatement(sql);
				ps.setString(1, a);
				 ps.setString(2, b);
				 ps.setString(3, c);
				int bd= ps.executeUpdate();
				if(bd>0) {
					JOptionPane.showMessageDialog(null, "'"+b+"'单位添加成功");
				}
				else {
					JOptionPane.showMessageDialog(null, "插入错误");
				}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		
			 
		
	}

}
