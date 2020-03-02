package Send;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.JdbcAll;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SendNew {

	 JFrame frame;
	private JTextField sendid;
	private JTextField sendname;
	private JTextField remark;
	private PreparedStatement ps;
	SendList sl =new SendList();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SendNew window = new SendNew();
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
	public SendNew() {
		initialize();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public SendNew(SendList sl) {
		initialize();
		this.sl=sl;
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 513, 311);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u914D\u9001\u5458\u4EE3\u7801");
		label.setBounds(58, 45, 89, 15);
		frame.getContentPane().add(label);
		
		sendid = new JTextField();
		sendid.setBounds(123, 42, 100, 21);
		frame.getContentPane().add(sendid);
		sendid.setColumns(10);
		
		JLabel label_1 = new JLabel("\u914D\u9001\u5458\u540D\u5B57");
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
		this.sl.RefrshTable();
		
	}

	protected void insertSend(String a,String b,String c) {
		// TODO Auto-generated method stub
		JdbcAll j=new JdbcAll();
		Connection con=j.getCon();
		String sql="insert into send(sendid,sendname,remark) VALUES(?,?,?)";
		
			 try {
				ps= con.prepareStatement(sql);
				ps.setString(1, a);
				 ps.setString(2, b);
				 ps.setString(3, c);
				int bd= ps.executeUpdate();
				if(bd>0) {
					JOptionPane.showMessageDialog(null, "'"+b+"'配送员添加成功");
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
