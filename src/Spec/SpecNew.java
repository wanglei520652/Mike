package Spec;

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

public class SpecNew {

	 JFrame frame;
		private JTextField sendid;
		private JTextField sendname;
		private JTextField remark;
		private PreparedStatement ps;
		SpecList spl =new SpecList();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpecNew window = new SpecNew();
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
	public SpecNew() {
		initialize();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public SpecNew(SpecList spl) {
		initialize();
		this.spl=spl;
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u89C4\u683C\u4FE1\u606F\u65B0\u589E");
		frame.setBounds(100, 100, 513, 311);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u89C4\u683C\u4EE3\u7801");
		label.setBounds(58, 45, 89, 15);
		frame.getContentPane().add(label);
		
		sendid = new JTextField();
		sendid.setBounds(123, 42, 100, 21);
		frame.getContentPane().add(sendid);
		sendid.setColumns(10);
		
		JLabel label_1 = new JLabel("\u89C4\u683C\u540D\u79F0");
		label_1.setBounds(261, 45, 100, 15);
		frame.getContentPane().add(label_1);
		
		sendname = new JTextField();
		sendname.setBounds(334, 42, 123, 21);
		frame.getContentPane().add(sendname);
		sendname.setColumns(10);
		
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
				insertSend(sendid.getText(),sendname.getText(),remark.getText());
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
		this.spl.RefrshTable();
		
	}

	protected void insertSend(String a,String b,String c) {
		// TODO Auto-generated method stub
		JdbcAll j=new JdbcAll();
		Connection con=j.getCon();
		String sql="insert into spec(speccode,specname,remark) VALUES(?,?,?)";
		
			 try {
				ps= con.prepareStatement(sql);
				ps.setString(1, a);
				 ps.setString(2, b);
				 ps.setString(3, c);
				int bd= ps.executeUpdate();
				if(bd>0) {
					JOptionPane.showMessageDialog(null, "'"+b+"'规格添加成功");
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
