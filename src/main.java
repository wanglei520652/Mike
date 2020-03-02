import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import BigTable.BB;
import Category.CategoryList;
import Customer.CustList;
import Month.MonthReport;
import Order.OrderList;
import Order.OrderListEmpty;
import Order.OrderSelect;
import Product.ProductList;
import Send.SendList;
import Spec.SpecList;

import java.awt.Font;
import java.awt.Color;

public class main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
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
	public main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u9500\u552E\u7CFB\u7EDF\u4E3B\u754C\u9762");
		frame.setBounds(50, 39, 1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton button = new JButton("\u5BA2\u6237");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustList window = null;
				try {
					window = new CustList();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				window.frame.setVisible(true);
			}
		});
		button.setBounds(184, 138, 110, 35);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u5976\u54C1\u5217\u8868");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProductList window = null;
				window = new ProductList();
				window.frame.setVisible(true);
			}
		});
		button_1.setBounds(494, 138, 110, 35);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u914D\u9001\u5458");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SendList window = null;
				window = new SendList();
				window.frame.setVisible(true);
			}
		});
		button_2.setBounds(778, 138, 110, 35);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("\u4E0B\u5355");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderList window = null;
				window = new OrderList();
				window.frame.setVisible(true);
			}
		});
		button_3.setBounds(184, 183, 110, 35);
		frame.getContentPane().add(button_3);
		
		JButton button_5 = new JButton("\u6708\u62A5");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MonthReport window = null;
				window = new MonthReport();
				window.frame.setVisible(true);
			}
		});
		button_5.setBounds(184, 245, 110, 35);
		frame.getContentPane().add(button_5);
		
		JButton button_6 = new JButton("\u79CD\u7C7B");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CategoryList window = null;
				window = new CategoryList();
				window.frame.setVisible(true);
			}
		});
		button_6.setBounds(346, 138, 110, 35);
		frame.getContentPane().add(button_6);
		
		JButton button_7 = new JButton("\u5927\u7EDF\u8BA1\u8868");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BB window = null;
				window = new BB();
				window.frame.setVisible(true);
			}
		});
		button_7.setBounds(346, 245, 110, 35);
		frame.getContentPane().add(button_7);
		
		JButton button_8 = new JButton("\u4E0B\u5355\u67E5\u8BE2");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OrderSelect os=new OrderSelect();
				os.frame.setVisible(true);
			}
		});
		button_8.setBounds(346, 183, 110, 35);
		frame.getContentPane().add(button_8);
		
		JLabel label = new JLabel("\u57FA\u7840\u6570\u636E");
		label.setBounds(119, 148, 54, 15);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u65E5\u5E38\u64CD\u4F5C");
		label_1.setBounds(119, 193, 54, 15);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u62A5\u8868\u7BA1\u7406");
		label_2.setBounds(120, 255, 54, 15);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u5976\u54C1\u9500\u552E\u7BA1\u7406\u7CFB\u7EDF");
		label_3.setForeground(Color.DARK_GRAY);
		label_3.setFont(new Font("ËÎÌו", Font.BOLD, 40));
		label_3.setBounds(310, 41, 410, 47);
		frame.getContentPane().add(label_3);
		
		JButton button_4 = new JButton("\u89C4\u683C");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SpecList window = null;
				window = new SpecList();
				window.frame.setVisible(true);
			}
		});
		button_4.setBounds(641, 138, 110, 35);
		frame.getContentPane().add(button_4);
		
		JButton button_9 = new JButton("\u6253\u5370\u7A7A\u8BA2\u5355");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OrderListEmpty window = null;
				window = new OrderListEmpty();
				window.frame.setVisible(true);
			}
		});
		button_9.setBounds(494, 189, 110, 29);
		frame.getContentPane().add(button_9);
	}
}
