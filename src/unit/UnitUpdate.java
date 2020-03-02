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

public class UnitUpdate {

	 JFrame frame;
		private JTextField unitid;
		private JTextField unitname;
		private JTextField remark;
		private PreparedStatement ps;
		SendList sl ;
		private JTextField id1;
		private Connection con;
		private UnitList ul;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnitUpdate window = new UnitUpdate();
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
	public UnitUpdate() {
		initialize();
	}
	public UnitUpdate(UnitList ul) {
		this.ul=ul;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u5355\u4F4D\u4FE1\u606F\u4FEE\u6539");
		frame.setBounds(100, 100, 597, 337);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		JLabel label = new JLabel("\u5355\u4F4D\u4EE3\u7801");
		label.setBounds(58, 45, 89, 15);
		frame.getContentPane().add(label);
		
		unitid = new JTextField();
		unitid.setBounds(123, 42, 100, 21);
		frame.getContentPane().add(unitid);
		unitid.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5355\u4F4D\u540D\u5B57");
		label_1.setBounds(261, 45, 100, 15);
		frame.getContentPane().add(label_1);
		
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
		
		JButton button = new JButton("\u4FEE\u6539");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateSend(unitid.getText(),unitname.getText(),remark.getText(),Integer.valueOf(id1.getText().toString()));
				frame.dispose();
				//刷新数据
				dd();
				
			}
		});
		button.setBounds(54, 161, 93, 23);
		frame.getContentPane().add(button);
		
		id1 = new JTextField();
		id1.setVisible(false);
		id1.setBounds(58, 126, 66, 21);
		frame.getContentPane().add(id1);
		id1.setColumns(10);
	}

	protected void dd() {
		// TODO Auto-generated method stub
		this.ul.RefrshTable();
		
	}

	protected void updateSend( String sendid, String sendname, String remark,int id) {
		// TODO Auto-generated method stub
		
		JdbcAll ja=new JdbcAll();
		con=ja.getCon();
		String sql="update unit set unitcode=?,unitname=?,remark=? where id=?";
		
		try {
			System.out.println("更新sql="+sql);
			ps=con.prepareStatement(sql);
			ps.setString(1,sendid);
			ps.setString(2, sendname);
			ps.setString(3, remark);
			ps.setInt(4, id);
			int a=ps.executeUpdate();
			if(a>0) {
				JOptionPane.showMessageDialog(null, "修改数据完成");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void putstring(Integer id, String sendid2, String sendname2, String remark2) {
		// TODO Auto-generated method stub
		System.out.println("打开进入修改页面");
		id1.setText(id.toString());
		unitid.setText(sendid2);
		unitname.setText(sendname2);
		remark.setText(remark2);
		
	}


}
