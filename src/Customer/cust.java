package Customer;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Send.SendList;
import dao.Jdbc;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class cust {

	JFrame frame;
	private JTextField custname;
	private JTextField address;
	private JTextField tel;
	private JTextField contact;
	private JTextField remark;
	private CustList cl;
	private JTextField custcode;
	private JTextField abbreviation;
	private JTextField helpcode;
	public JTextField senid;
	public JTextField sendname;
	public JTextField idsend;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cust window = new cust();
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
	public cust() {
		initialize();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public cust(CustList clr) {
		this.cl=clr;
		initialize();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
	}
	//调用刷新table数据的方法
	public void dd() {
       this.cl.refrshCust();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u5BA2\u6237\u57FA\u672C\u8D44\u6599");
		frame.setBounds(100, 100, 617, 342);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5BA2\u6237\u540D\u79F0");
		label.setBounds(211, 25, 54, 15);
		frame.getContentPane().add(label);
		
		custname = new JTextField();
		custname.setBounds(275, 22, 268, 21);
		frame.getContentPane().add(custname);
		custname.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5730    \u5740");
		label_1.setBounds(35, 100, 54, 15);
		frame.getContentPane().add(label_1);
		
		address = new JTextField();
		address.setBounds(105, 97, 268, 21);
		frame.getContentPane().add(address);
		address.setColumns(10);
		
		JLabel label_2 = new JLabel("\u7535\u8BDD");
		label_2.setBounds(243, 134, 54, 15);
		frame.getContentPane().add(label_2);
		
		tel = new JTextField();
		tel.setBounds(287, 131, 106, 21);
		frame.getContentPane().add(tel);
		tel.setColumns(10);
		
	
		
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.setBounds(479, 231, 93, 23);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		frame.getContentPane().add(button_1);
		
		JLabel label_3 = new JLabel("\u533A\u57DF");
		label_3.setBounds(388, 66, 54, 15);
		frame.getContentPane().add(label_3);
		
		JList list = new JList();
		list.setBounds(212, 24, 1, 1);
		frame.getContentPane().add(list);
		
		JComboBox area = new JComboBox();
		area.setBounds(426, 63, 106, 21);
		area.setModel(new DefaultComboBoxModel(new String[] {"\u4E1C\u57CE\u533A", "\u897F\u57CE\u533A", "\u671D\u9633\u533A", "\u4E30\u53F0\u533A", "\u77F3\u666F\u5C71\u533A", "\u6D77\u6DC0\u533A", "\u987A\u4E49\u533A", "\u901A\u5DDE\u533A", "\u5927\u5174\u533A", "\u623F\u5C71\u533A", "\u95E8\u5934\u6C9F\u533A", "\u660C\u5E73\u533A", "\u5E73\u8C37\u533A", "\u5BC6\u4E91\u533A", "\u6000\u67D4\u533A", "\u5EF6\u5E86\u533A"}));
		area.setToolTipText("");
		frame.getContentPane().add(area);
		
		JLabel label_4 = new JLabel("\u8054 \u7CFB \u4EBA");
		label_4.setBounds(36, 137, 54, 15);
		frame.getContentPane().add(label_4);
		
		contact = new JTextField();
		contact.setBounds(105, 128, 117, 21);
		frame.getContentPane().add(contact);
		contact.setColumns(10);
		
		JLabel label_5 = new JLabel("\u5907    \u6CE8");
		label_5.setBounds(35, 199, 54, 15);
		frame.getContentPane().add(label_5);
		
		remark = new JTextField();
		remark.setBounds(104, 196, 261, 21);
		frame.getContentPane().add(remark);
		remark.setColumns(10);
		
		JButton button_2 = new JButton("\u63D0\u4EA4");
		button_2.setBounds(333, 231, 93, 23);
		button_2.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent arg0) {
				Jdbc j=new Jdbc();
				j.insertCust(custname.getText(), area.getSelectedItem().toString(), address.getText(), contact.getText(), tel.getText(), remark.getText(),custcode.getText(),abbreviation.getText(),helpcode.getText(),senid.getText(),sendname.getText());

				frame.dispose();
                 //赋值				
				dd();
			}
		});
		frame.getContentPane().add(button_2);
		
		JLabel label_6 = new JLabel("");
		label_6.setBounds(215, 25, 54, 15);
		frame.getContentPane().add(label_6);
		
		JLabel lblNewLabel = new JLabel("\u5BA2\u6237\u4EE3\u7801");
		lblNewLabel.setBounds(34, 25, 54, 15);
		frame.getContentPane().add(lblNewLabel);
		
		custcode = new JTextField();
		custcode.setBounds(104, 22, 66, 21);
		frame.getContentPane().add(custcode);
		custcode.setColumns(10);
		
		JLabel label_7 = new JLabel("\u7B80\u79F0");
		label_7.setBounds(34, 69, 54, 15);
		frame.getContentPane().add(label_7);
		
		abbreviation = new JTextField();
		abbreviation.setBounds(104, 63, 66, 21);
		frame.getContentPane().add(abbreviation);
		abbreviation.setColumns(10);
		
		JLabel label_8 = new JLabel("\u52A9\u8BB0\u7801");
		label_8.setBounds(211, 63, 54, 15);
		frame.getContentPane().add(label_8);
		
		helpcode = new JTextField();
		helpcode.setBounds(275, 63, 66, 21);
		frame.getContentPane().add(helpcode);
		helpcode.setColumns(10);
		
		JButton button_3 = new JButton("\u914D\u9001\u5458\u9009\u62E9");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openCust();
			}
		});
		button_3.setBounds(20, 166, 93, 23);
		frame.getContentPane().add(button_3);
		
		senid = new JTextField();
		senid.setBounds(115, 167, 66, 21);
		frame.getContentPane().add(senid);
		senid.setColumns(10);
		
		sendname = new JTextField();
		sendname.setBounds(199, 167, 98, 21);
		frame.getContentPane().add(sendname);
		sendname.setColumns(10);
		
		idsend = new JTextField();
		idsend.setVisible(false);
		idsend.setBounds(408, 167, 66, 21);
		frame.getContentPane().add(idsend);
		idsend.setColumns(10);
		JButton button = new JButton("\u63D0\u4EA4");
	
	}

	protected void openCust() {
		// TODO Auto-generated method stub
		
		SendList sl=new SendList(this);
		sl.frame.setVisible(true);
	}

	public void putSend(String idsend, String sendid1, String sendname1) {
		// TODO Auto-generated method stub
		
		this.idsend.setText(idsend);
		this.senid.setText(sendid1);
		this.sendname.setText(sendname1);
		
		
	}
}
