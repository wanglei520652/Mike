package Product;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Category.CategoryList;
import Spec.SpecList;
import dao.ProductJdbc;
import unit.UnitList;

public class ProductUpdate {

	 JFrame frame;
	 ProductList pl;
	 private DefaultTableModel tableModel;
	 String[] head={"id","种类代码","种类名称","种类简称","备注","时间"};
	private JTextField ccode;
	private JTextField cname;
	private JTextField pcode;
	private JTextField pname;
	private JTextField pabb;
	private JTextField phelpcode;
	private JTextField pprice;
	private JTextField remark;
	private JTable table;
	private JTextField unitname;
	private JTextField id11;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductUpdate window = new ProductUpdate();
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
	public ProductUpdate() {
		initialize();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public ProductUpdate(ProductList pl) {
		this.pl=pl;
		initialize();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u5976\u54C1\u65B0\u589E");
		frame.setBounds(100, 100, 864, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u79CD\u7C7B\u4EE3\u7801");
		label.setBounds(399, 68, 54, 15);
		frame.getContentPane().add(label);
		
		ccode = new JTextField();
		ccode.setBounds(463, 60, 91, 31);
		frame.getContentPane().add(ccode);
		ccode.setColumns(10);
		
		JLabel label_1 = new JLabel("\u79CD\u7C7B\u540D\u79F0");
		label_1.setBounds(584, 68, 54, 15);
		frame.getContentPane().add(label_1);
		
		cname = new JTextField();
		cname.setBounds(659, 60, 91, 31);
		frame.getContentPane().add(cname);
		cname.setColumns(10);
		
		JLabel label_2 = new JLabel("\u5976\u54C1\u4EE3\u7801");
		label_2.setBounds(399, 109, 54, 15);
		frame.getContentPane().add(label_2);
		
		pcode = new JTextField();
		pcode.setBounds(465, 106, 91, 31);
		frame.getContentPane().add(pcode);
		pcode.setColumns(10);
		
		JLabel label_3 = new JLabel("\u5976\u54C1\u540D\u79F0");
		label_3.setBounds(584, 109, 54, 15);
		frame.getContentPane().add(label_3);
		
		pname = new JTextField();
		pname.setBounds(659, 106, 91, 31);
		frame.getContentPane().add(pname);
		pname.setColumns(10);
		
		JLabel label_4 = new JLabel("\u7B80    \u79F0");
		label_4.setBounds(399, 156, 54, 15);
		frame.getContentPane().add(label_4);
		
		pabb = new JTextField();
		pabb.setBounds(465, 153, 91, 31);
		frame.getContentPane().add(pabb);
		pabb.setColumns(10);
		
		JLabel label_5 = new JLabel("\u52A9 \u8BB0 \u7801");
		label_5.setBounds(584, 156, 54, 15);
		frame.getContentPane().add(label_5);
		
		phelpcode = new JTextField();
		phelpcode.setBounds(659, 153, 91, 31);
		frame.getContentPane().add(phelpcode);
		phelpcode.setColumns(10);
		
		JLabel label_6 = new JLabel("\u5355    \u4EF7");
		label_6.setBounds(399, 204, 54, 15);
		frame.getContentPane().add(label_6);
		
		pprice = new JTextField();
		pprice.setBounds(463, 196, 91, 31);
		frame.getContentPane().add(pprice);
		pprice.setColumns(10);
		
		remark = new JTextField();
		remark.setBounds(484, 245, 266, 31);
		frame.getContentPane().add(remark);
		remark.setColumns(10);
		
		JButton button = new JButton("\u4FEE\u6539");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductJdbc pj=new ProductJdbc();
				Float fa=(float) 0.00;
				if(!"".equals(pprice.getText())) {
					fa=Float.valueOf(pprice.getText()) ;
					System.out.println("单价="+fa);
				}
				else {
					System.out.println("单价为空值");
				}
//				pj.insertProduct(ccode.getText(), cname.getText(), pcode.getText(), pname.getText(), pabb.getText(), phelpcode.getText(), fa,unitname.getText(), remark.getText());
				pj.updateProduct(ccode.getText(), cname.getText(), pcode.getText(), pname.getText(), pabb.getText(), phelpcode.getText(), fa,unitname.getText(), remark.getText(),id11.getText());
				frame.dispose();
				dd();
			}
		});
		button.setBounds(399, 291, 105, 31);
		frame.getContentPane().add(button);
		
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					//点击种类列表，得到点击的种类后，刷新奶品列表
					dd(table.rowAtPoint(e.getPoint()));
        			
        			
        		}
				
			}
		});
		table.setBounds(29, 300, 288, -262);
		CategoryList cl=new CategoryList();
		tableModel=new DefaultTableModel(cl.queryData_select(),cl.head_select){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        
        table.setModel(tableModel);
//		frame.getContentPane().add(table);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(51, 60, 317, 253);
		frame.getContentPane().add(scrollPane);
		
		unitname = new JTextField();
		unitname.setBounds(659, 196, 91, 31);
		frame.getContentPane().add(unitname);
		unitname.setColumns(10);
		
		JButton btnNewButton = new JButton("\u5355\u4F4D\u9009\u62E9");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openUnitList();
			}
		});
		btnNewButton.setBounds(564, 200, 91, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel label_8 = new JLabel("1\u53CC\u51FB\u201C\u79CD\u7C7B\u201D");
		label_8.setForeground(Color.RED);
		label_8.setBounds(48, 22, 156, 15);
		frame.getContentPane().add(label_8);
		
		JLabel label_9 = new JLabel("2.\u586B\u5199\u5976\u54C1\u540D\u79F0\u3001\u5355\u4EF7\u3001\u5355\u4F4D\u7B49");
		label_9.setForeground(Color.RED);
		label_9.setBounds(399, 22, 361, 15);
		frame.getContentPane().add(label_9);
		
		id11 = new JTextField();
		id11.setVisible(false);
		id11.setBounds(703, 315, 66, 21);
		frame.getContentPane().add(id11);
		id11.setColumns(10);
		
		JButton button_1 = new JButton("\u89C4\u683C\u9009\u62E9");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openSpecList();
			}
		});
		button_1.setBounds(378, 249, 91, 23);
		frame.getContentPane().add(button_1);
	}

	
	protected void openSpecList() {
		// TODO Auto-generated method stub
		SpecList spl=new SpecList(this);
		spl.frame.setVisible(true);
	}
	protected void openUnitList() {
		// TODO Auto-generated method stub
		UnitList ul=new UnitList(this);
		ul.frame.setVisible(true);
	}

	protected void dd(int rr) {
		// TODO Auto-generated method stub
		
		ccode.setText(table.getModel().getValueAt(rr, 0).toString()); 
		cname.setText(table.getModel().getValueAt(rr, 1).toString());
		ProductJdbc p=new ProductJdbc();
		String jj="";
		jj=p.getMaxProductId(table.getModel().getValueAt(rr, 0).toString());
		pcode.setText(jj);
	}

	protected void dd() {
		// TODO Auto-generated method stub
		if(pl!=null) {
			this.pl.refrshProductList();
		}
		
	}

	public void putSend(String idsend, String sendid1, String sendname1) {
		// TODO Auto-generated method stub
		this.unitname.setText(sendname1);
		
		
	}

	public void putString(String ccode1, String cname1, String pcode1, String pname1, String pabb1, String phelpcode1,
			String pprice1, String unitname1, String remark1, String id1) {
		// TODO Auto-generated method stub
		this.ccode.setText(ccode1);
		this.cname.setText(cname1);
		this.pcode.setText(pcode1);
		this.pname.setText(pname1);
		this.pabb.setText(pabb1);
		this.phelpcode.setText(phelpcode1);
		this.pprice.setText(pprice1);
		this.unitname.setText(unitname1);
		this.remark.setText(remark1);
		this.id11.setText(id1);
		
		
	}

	public void putstring_send(String idsend, String sendid1, String sendname1) {
		// TODO Auto-generated method stub
		this.remark.setText(sendname1);
		
	}
}
