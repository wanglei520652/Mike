package Customer;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import Send.SendList;
import dao.Jdbc;

public class CustUpdate {

	JFrame frame;
	 JTextField custname;
	 JTextField address;
	 JTextField tel;
	 JTextField contact;
	 JTextField remark;
	JComboBox area;
	private CustList cl;
	private JTextField id;
	private JTextField custcode;
	private JTextField abbreviation;
	private JTextField helpcode;
	private JTextField sendid;
	private JTextField sendname;
	
	public String custupdateString="¿Í»§ÐÞ¸Ä";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustUpdate window = new CustUpdate();
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
	public CustUpdate() {
		initialize();

		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}
	public CustUpdate(CustList cl) {
		this.cl=cl;
		initialize();

		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}
	public void dd() {
	       this.cl.refrshCust();
		}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setTitle("\u4FEE\u6539\u5BA2\u6237\u8D44\u6599");
		frame.setBounds(100, 100, 627, 351);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5BA2\u6237\u540D\u79F0");
		label.setBounds(211, 25, 54, 15);
		frame.getContentPane().add(label);
		
		custname = new JTextField();
		custname.setBounds(275, 25, 117, 21);
		frame.getContentPane().add(custname);
		custname.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5730    \u5740");
		label_1.setBounds(20, 100, 54, 15);
		frame.getContentPane().add(label_1);
		
		address = new JTextField();
		address.setBounds(84, 97, 308, 21);
		frame.getContentPane().add(address);
		address.setColumns(10);
		
		JLabel label_2 = new JLabel("\u7535\u8BDD");
		label_2.setBounds(211, 128, 54, 15);
		frame.getContentPane().add(label_2);
		
		tel = new JTextField();
		tel.setBounds(236, 125, 106, 21);
		frame.getContentPane().add(tel);
		tel.setColumns(10);
		
	
		
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_1.setBounds(419, 222, 93, 23);
		frame.getContentPane().add(button_1);
		
		JLabel label_3 = new JLabel("\u533A\u57DF");
		label_3.setBounds(374, 66, 54, 15);
		frame.getContentPane().add(label_3);
		
		JList list = new JList();
		list.setBounds(212, 24, 1, 1);
		frame.getContentPane().add(list);
		
		area = new JComboBox();
		area.setModel(new DefaultComboBoxModel(new String[] {"\u4E1C\u57CE\u533A", "\u897F\u57CE\u533A", "\u671D\u9633\u533A", "\u4E30\u53F0\u533A", "\u77F3\u666F\u5C71\u533A", "\u6D77\u6DC0\u533A", "\u987A\u4E49\u533A", "\u901A\u5DDE\u533A", "\u5927\u5174\u533A", "\u623F\u5C71\u533A", "\u95E8\u5934\u6C9F\u533A", "\u660C\u5E73\u533A", "\u5E73\u8C37\u533A", "\u5BC6\u4E91\u533A", "\u6000\u67D4\u533A", "\u5EF6\u5E86\u533A"}));
		area.setToolTipText("");
		area.setBounds(406, 63, 106, 21);
		frame.getContentPane().add(area);
		
		JLabel label_4 = new JLabel("\u8054 \u7CFB \u4EBA");
		label_4.setBounds(20, 128, 54, 15);
		frame.getContentPane().add(label_4);
		
		contact = new JTextField();
		contact.setBounds(84, 125, 117, 21);
		frame.getContentPane().add(contact);
		contact.setColumns(10);
		
		JLabel label_5 = new JLabel("\u5907    \u6CE8");
		label_5.setBounds(20, 194, 54, 15);
		frame.getContentPane().add(label_5);
		
		remark = new JTextField();
		remark.setBounds(84, 191, 308, 21);
		frame.getContentPane().add(remark);
		remark.setColumns(10);
		
		JButton button_2 = new JButton("\u4FEE\u6539");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Jdbc j=new Jdbc();
				j.updateCust(custname.getText(), area.getSelectedItem().toString(), address.getText(), contact.getText(), tel.getText(), remark.getText(),custcode.getText(),abbreviation.getText(),helpcode.getText(),Integer.parseInt(id.getText()),sendid.getText(),sendname.getText());
				frame.dispose();
				

				dd();
				
			}
		});
		button_2.setBounds(271, 222, 93, 23);
		frame.getContentPane().add(button_2);
		
		id = new JTextField();
		id.setVisible(false);
		id.setBounds(49, 241, 66, 21);
		frame.getContentPane().add(id);
		id.setColumns(10);
		
		JLabel label_6 = new JLabel("\u5BA2\u6237\u4EE3\u7801");
		label_6.setBounds(20, 25, 54, 15);
		frame.getContentPane().add(label_6);
		
		custcode = new JTextField();
		custcode.setBounds(84, 22, 93, 21);
		frame.getContentPane().add(custcode);
		custcode.setColumns(10);
		
		JLabel label_7 = new JLabel("\u7B80\u79F0");
		label_7.setBounds(20, 69, 54, 15);
		frame.getContentPane().add(label_7);
		
		abbreviation = new JTextField();
		abbreviation.setBounds(84, 66, 93, 21);
		frame.getContentPane().add(abbreviation);
		abbreviation.setColumns(10);
		
		JLabel label_8 = new JLabel("\u52A9\u8BB0\u7801");
		label_8.setBounds(211, 69, 54, 15);
		frame.getContentPane().add(label_8);
		
		helpcode = new JTextField();
		helpcode.setBounds(275, 66, 77, 21);
		frame.getContentPane().add(helpcode);
		helpcode.setColumns(10);
		
		JButton button_3 = new JButton("\u914D\u9001\u5458\u9009\u62E9 ");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				opensend();
			}
		});
		button_3.setBounds(10, 161, 105, 23);
		frame.getContentPane().add(button_3);
		
		sendid = new JTextField();
		sendid.setBounds(125, 160, 77, 21);
		frame.getContentPane().add(sendid);
		sendid.setColumns(10);
		
		sendname = new JTextField();
		sendname.setBounds(221, 160, 137, 21);
		frame.getContentPane().add(sendname);
		sendname.setColumns(10);
		JButton button = new JButton("\u63D0\u4EA4");
	}

	protected void opensend() {
		// TODO Auto-generated method stub
		SendList sl=new SendList(this,custupdateString);
		sl.frame.setVisible(true);
		
	}

	public void putstring(Integer a0,String a1,String a2,String a3,String a4,String a5,String a6,String a7,String a8,String a9,String a10,String a11) {
		// TODO Auto-generated method stub
        id.setText(a0.toString());
		custname.setText(a1);
		area.setSelectedItem(a2);
		address.setText(a3);
		contact.setText(a4);
		tel.setText(a5);
		remark.setText(a6);
		custcode.setText(a7);
		abbreviation.setText(a8);
		helpcode.setText(a9);
		sendid.setText(a10);
		sendname.setText(a11);
	}

	public void putstring_send(String idsend, String sendid1, String sendname1) {
		// TODO Auto-generated method stub
		
		this.sendid.setText(sendid1);
		this.sendname.setText(sendname1);
		
		
	}
}
