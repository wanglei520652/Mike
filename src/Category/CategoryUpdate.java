package Category;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.CatJdbc;

public class CategoryUpdate {

	JFrame frame;
	private CategoryList cl;
	private JTextField ccode;
	private JTextField cname;
	private JTextField cabb;
	private JTextField remark;
	private JTextField id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoryUpdate window = new CategoryUpdate();
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
	public CategoryUpdate() {
		initialize();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public CategoryUpdate(CategoryList cl) {
		initialize();
		this.cl=cl;
		initialize();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel label = new JLabel("\u79CD\u7C7B\u4EE3\u7801");
		label.setBounds(37, 35, 54, 15);
		frame.getContentPane().add(label);
		
		ccode = new JTextField();
		ccode.setBounds(110, 32, 66, 21);
		frame.getContentPane().add(ccode);
		ccode.setColumns(10);
		
		JLabel label_1 = new JLabel("\u79CD\u7C7B\u540D\u79F0");
		label_1.setBounds(221, 35, 54, 15);
		frame.getContentPane().add(label_1);
		
		cname = new JTextField();
		cname.setBounds(301, 32, 66, 21);
		frame.getContentPane().add(cname);
		cname.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u7B80\u79F0");
		lblNewLabel.setBounds(37, 77, 54, 15);
		frame.getContentPane().add(lblNewLabel);
		
		cabb = new JTextField();
		cabb.setBounds(110, 74, 66, 21);
		frame.getContentPane().add(cabb);
		cabb.setColumns(10);
		
		JLabel label_2 = new JLabel("\u5907\u6CE8");
		label_2.setBounds(37, 114, 54, 15);
		frame.getContentPane().add(label_2);
		
		remark = new JTextField();
		remark.setBounds(110, 111, 66, 21);
		frame.getContentPane().add(remark);
		remark.setColumns(10);
		
		JButton insertButton = new JButton("\u4FEE\u6539");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CatJdbc j=new CatJdbc();
				j.updateCategory(ccode.getText(),cname.getText(),cabb.getText(),remark.getText(),Integer.parseInt(id.getText()) );

				frame.dispose();
                 //¸³Öµ				
				dd();
			}
		});
		insertButton.setBounds(37, 165, 93, 23);
		frame.getContentPane().add(insertButton);
		
		JButton button = new JButton("\u53D6\u6D88");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button.setBounds(182, 165, 93, 23);
		frame.getContentPane().add(button);
		
		id = new JTextField();
		id.setVisible(false);
		id.setBounds(122, 217, 66, 21);
		frame.getContentPane().add(id);
		id.setColumns(10);
	}

	protected void dd() {
		// TODO Auto-generated method stub
		this.cl.refrshCat();
		
	}

	public void putstring(Integer id, String ccode2, String cname2, String cabb2, String remark2) {
		// TODO Auto-generated method stub
		this.id.setText(id.toString());
		ccode.setText(ccode2);
		cname.setText(cname2);
		cabb.setText(cabb2);
		remark.setText(remark2);
		
		
	}

}
