package Order;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.eltima.components.ui.DatePicker;

import Category.CategoryList;
import Customer.CustListSelect;
import Product.ProductList;
import Send.SendList;
import dao.Jdbc;
import dao.OrderJdbc;
import test.SwingCommonPrinitTools;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Administrator
 *����¼��ҳ��2019-11-29
 */
public class OrderList {

	public JFrame frame;
	public JTextField custcode;
	public JTextField custname;
	public JTextField custabb;
	public JTextField custtel;
	public JTextField custaddress;
	public JTable table;
	public DefaultTableModel tableModel;
	String[] head={"��Ʒ����","��Ʒ����","��λ","���","����","����","���","��ע"};
	public JScrollPane scrollPane;
	JScrollPane scrollPane_product ;
	public JTable tablecategory;
	public JTable tableproduct;
	public DefaultTableModel tableModel_category;
	public DefaultTableModel tableModel_product;
	ProductList pl=new ProductList();
	int rowI;
	public JButton button_3;
	public JButton button_4;
	public JTextField sendid;
	public JTextField sendname;
	public JButton button_5;
	public JTextField total_amout;
	private JLabel label_8;
	private JTextField total_num;
	public DatePicker datepick;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderList window = new OrderList();
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
	public OrderList() {
		initialize();
		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u65E5\u5E38\u8BA2\u5355\u5F55\u5165");
		frame.setBounds(20, 20, 1335, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("1\u65E5\u671F");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(37, 47, 54, 15);
		frame.getContentPane().add(lblNewLabel);
		
		//ʱ��ؼ�
		
        datepick = getDatePicker(); 
        
        frame.getContentPane().add(datepick);
        
        
		
		JLabel label = new JLabel("\u5BA2\u6237\u4EE3\u7801");
		label.setBounds(425, 50, 54, 15);
		frame.getContentPane().add(label);
		
		custcode = new JTextField();
		custcode.setBounds(483, 47, 84, 21);
		frame.getContentPane().add(custcode);
		custcode.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5BA2\u6237\u540D\u79F0");
		label_1.setBounds(588, 50, 54, 15);
		frame.getContentPane().add(label_1);
		
		custname = new JTextField();
		custname.setBounds(652, 47, 169, 21);
		frame.getContentPane().add(custname);
		custname.setColumns(10);
		
		JLabel label_2 = new JLabel("\u5BA2\u6237\u7B80\u79F0");
		label_2.setBounds(831, 47, 54, 15);
		frame.getContentPane().add(label_2);
		
		custabb = new JTextField();
		custabb.setBounds(883, 44, 66, 21);
		frame.getContentPane().add(custabb);
		custabb.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u7535    \u8BDD");
		lblNewLabel_1.setBounds(979, 47, 54, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		custtel = new JTextField();
		custtel.setBounds(1037, 44, 84, 21);
		frame.getContentPane().add(custtel);
		custtel.setColumns(10);
		
		JLabel label_3 = new JLabel("\u5730    \u5740");
		label_3.setBounds(425, 86, 54, 15);
		frame.getContentPane().add(label_3);
		
		custaddress = new JTextField();
		custaddress.setBounds(489, 83, 332, 21);
		frame.getContentPane().add(custaddress);
		custaddress.setColumns(10);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setBounds(37, 179, 716, 192);
		tableModel=new DefaultTableModel(queryData(),head);
		table.setModel(tableModel);
		//
		
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();//���ü�����
		render.setHorizontalAlignment(SwingConstants.RIGHT);//���ж���
		TableColumn column4=table.getColumnModel().getColumn(4);//��ȡĳһ������
		column4.setCellRenderer(render);
		
		TableColumn column3=table.getColumnModel().getColumn(6);//��ȡĳһ������
		column3.setCellRenderer(render);
		
		TableColumn column2=table.getColumnModel().getColumn(5);//��ȡĳһ������
		column2.setCellRenderer(render);
		//
		
		table.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				
			}
			
		});
		tableModel.addTableModelListener(new TableModelListener() {

			
			
			public void tableChanged(TableModelEvent e) {					
				int type = e.getType();// ����¼�������
				int row = e.getFirstRow();// ��ô����˴��¼��ı��������
				int column = e.getColumn();// ��ô����˴��¼��ı��������
				TableModel  model = (TableModel)e.getSource();//�Զ���ȡ���ģ��
				if(column==6) //���Ľ��С��ʱ�������¼������С����������ֵ��6�����д������Ҫ�����ӿ񱨴�
				{
					Float total2=(float) 0.00;
					Float total22=(float) 0.00;
					for (int i = 0; i < table.getRowCount(); i++) {
						total2=total2+ Float.valueOf( (model.getValueAt(i, 6).toString().isEmpty())?"0":model.getValueAt(i, 6).toString());
						total22=total22+ Float.valueOf( (model.getValueAt(i, 4).toString().isEmpty())?"0":model.getValueAt(i, 4).toString());
					}
					 Float a=(float)(Math.round(total2*100))/100;
					 Float b=(float)(Math.round(total22*100))/100;
					total_amout.setText(a.toString());
					total_num.setText(b.toString());
					return;
				}
				else {
					
					if (type == TableModelEvent.UPDATE) {
						//�޸Ľ��С��һ������ֵ
						
				 
				        Object data = model.getValueAt(row, 4);	
				     
				        
						Object shop_price=model.getValueAt(row,5);

//						System.out.println("date="+data+",price="+shop_price);				
						String sa="";
						if(data.toString()!=null && shop_price.toString()!=null ) {
							if(!data.toString().isEmpty() && !shop_price.toString().isEmpty()) {
								sa=multiply(data.toString(),shop_price.toString());
								Float fa=(float)(Math.round(Float.valueOf(sa) * 100))/100;						
								table.getModel().setValueAt(fa, row, 6);
							}
							
						}
						
				}

					
				}
				Float total2=(float) 0.00;
				Float total22=(float) 0.00;
				for (int i = 0; i < table.getRowCount(); i++) {
					total2=total2+ ((model.getValueAt(i, 6).toString().isEmpty())?0: Float.valueOf(model.getValueAt(i, 6).toString()));
					total22=total22+ ((model.getValueAt(i, 4).toString().isEmpty())?0:Float.valueOf(model.getValueAt(i, 4).toString()));
				}
				 Float a=(float)(Math.round(total2*100))/100;
				 Float b=(float)(Math.round(total22*100))/100;
				total_amout.setText(a.toString());
				total_num.setText(b.toString());
			}
			

			
		});
	
		
        

//		frame.getContentPane().add(table);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(425, 129, 849, 428);
		scrollPane.setViewportView(table);
		frame.getContentPane().add(scrollPane);
		
		tablecategory = new JTable();
		tablecategory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					//��������б��õ�����������ˢ����Ʒ�б�
					dd(tablecategory.rowAtPoint(e.getPoint()));
        			
        			
        		}
				
			}
		});
		tablecategory.setBounds(37, 263, 355, 82);
		
		//�����б�
		CategoryList cl=new CategoryList();
		tableModel_category=new DefaultTableModel(cl.queryData_select(),cl.head_select){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        tablecategory.setModel(tableModel_category);
 		
        
        
		tableproduct = new JTable();
		tableproduct.addMouseListener(new MouseAdapter() {
			@Override
			//��Ʒ�б��˫���¼�
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					//����һ���µĶ�����ϸ����
					int sr=tableproduct.rowAtPoint(e.getPoint());
					String a1=tableproduct.getModel().getValueAt(sr, 0).toString();
					String a2=tableproduct.getModel().getValueAt(sr, 1).toString();
					
					String dw="";
					dw=tableproduct.getModel().getValueAt(sr, 2).toString();
//					System.out.println("����="+tableproduct.getModel().getValueAt(sr, 4));
//					Float  a3=(Float) tableproduct.getModel().getValueAt(sr, 4);
					Float  a3=Float.parseFloat(tableproduct.getModel().getValueAt(sr, 4).toString());
					String strspec="";
					strspec=tableproduct.getModel().getValueAt(sr, 3).toString();
					
					uu(a1,a2,dw,a3,strspec);

					int rows=table.getRowCount();
					Float totalamout=(float) 0.00;
					Float totalnum=(float) 0.00;
					for (int i = 0; i < rows; i++) {
						totalamout=totalamout+ Float.valueOf((table.getModel().getValueAt(i, 6).toString().isEmpty())?"0":table.getModel().getValueAt(i, 6).toString());
						totalnum=totalnum+ Float.valueOf((table.getModel().getValueAt(i, 4).toString().isEmpty())?"0":table.getModel().getValueAt(i, 4).toString());
					}

					Float ff_amout =(float)(Math.round(totalamout*100))/100;
					Float ff_num =(float)(Math.round(totalnum*100))/100;
					//�ܽ�����������ֵ
					total_amout.setText(ff_amout.toString());
					total_num.setText(ff_num.toString());
        			
        		}
			}
		});
		tableproduct.setBounds(37, 385, 359, 160);		
		//��Ʒ�б�
         
        tableModel_product=new DefaultTableModel(pl.queryData2_select(),pl.head_select){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        tableproduct.setModel(tableModel_product);

		
		JScrollPane scrollPane_category = new JScrollPane();
		scrollPane_category.setBounds(37, 129, 359, 145);
		scrollPane_category.setViewportView(tablecategory);
		frame.getContentPane().add(scrollPane_category);
		
		scrollPane_product = new JScrollPane();
		scrollPane_product.setBounds(37, 284, 359, 352);
		scrollPane_product.setViewportView(tableproduct); 
//		ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
		
		frame.getContentPane().add(scrollPane_product);
		
		JButton button = new JButton("\u4FDD\u5B58");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OrderJdbc j=new OrderJdbc();
				
				Date currentTime = new Date();
				//ʱ��
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
				String dateString = formatter.format(currentTime);
				//4λ�����
				int random4=(int)((Math.random()*9+1)*1000);
				String randoms4=String.valueOf(random4);
				//6λ�����
				int random6=(int)((Math.random()*9+1)*100000);
				String randoms6=String.valueOf(random6);
				
				String orderid =dateString.concat(randoms4);
				String orderdetailid=dateString.concat(randoms6);
				
				//ʱ��ؼ�
				Date d =(Date) datepick.getValue();
				SimpleDateFormat formatter11 = new SimpleDateFormat("yyyy-MM-dd");
				String dateString22 = formatter11.format(d);
				
				
				if("".equals(custname.getText())||"null".equals(custname.getText())) {
					JOptionPane.showMessageDialog(null, "�ͻ����Ʋ���Ϊ�գ���ͨ�� \"�ͻ�����\" ¼��ͻ���Ϣ��");
				}else if(table.getRowCount()<=0) {
					JOptionPane.showMessageDialog(null, "��ѡ����ߵ���Ʒ��¼�����ݡ����۵ȣ�");
				}
				else {
					
					try {
						//�ر��Զ��ύ
						j.con.setAutoCommit(false);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
					j.insertOrder(orderid, dateString22, custcode.getText(), custname.getText(), custabb.getText(), custtel.getText(), custaddress.getText(),sendid.getText(),sendname.getText(),total_num.getText(),total_amout.getText());
					

					//���붩����ϸ
					int rows=table.getModel().getRowCount();
			    	for (int i = 0; i < rows; i++) {
			    		String a=table.getModel().getValueAt(i, 7).toString();
//			    		System.out.println("��ע="+a);
						/*j.insertOrderDetel(orderid, orderdetailid.concat(String.valueOf(i)), 
								table.getModel().getValueAt(i, 0).toString(), 
								table.getModel().getValueAt(i, 1).toString(),
								Float.valueOf(table.getModel().getValueAt(i, 4).toString()), 
								Float.valueOf(table.getModel().getValueAt(i, 5).toString()), 
								Float.valueOf(table.getModel().getValueAt(i, 6).toString()),
								table.getModel().getValueAt(i, 2).toString(),
								table.getModel().getValueAt(i, 3).toString(),a
								);*/
			    		j.insertOrderDetel2(orderid, orderdetailid.concat(String.valueOf(i)), 
						table.getModel().getValueAt(i, 0).toString(), 
						table.getModel().getValueAt(i, 1).toString(),
						(table.getModel().getValueAt(i, 4).toString().isEmpty())?null:table.getModel().getValueAt(i, 4).toString(), 
						(table.getModel().getValueAt(i, 5).toString().isEmpty())?null:table.getModel().getValueAt(i, 5).toString(), 
						(table.getModel().getValueAt(i, 6).toString().isEmpty())?null:table.getModel().getValueAt(i, 6).toString(),
						table.getModel().getValueAt(i, 2).toString(),
						table.getModel().getValueAt(i, 3).toString(),a
						);
			    		
					}
					
			    	j.con.commit();
			    	
					}
					catch ( Exception ex) {
						ex.printStackTrace();
						try {
							j.con.rollback();
							System.out.println("�ع�");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}finally {
						j.closeOrderJdbc();
					}

					
//					JOptionPane.showMessageDialog(null, "����������ɣ�");
					
					
					int result =JOptionPane.showConfirmDialog(null, "�����������,�Ƿ���Ҫ��ӡ", "��ȷ�ϴ�ӡ", JOptionPane.YES_NO_OPTION);
					if(result==JOptionPane.YES_OPTION) {
						swingPrint(orderid);
					}
					custcode.setText("");
					custname.setText("");
					custabb.setText("");
					custtel.setText("");
					custaddress.setText("");
					sendid.setText("");
					sendname.setText("");
					tableModel.setRowCount(0);
					
				}
				

			}
		});
		button.setBounds(425, 594, 93, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_1.setBounds(554, 594, 93, 23);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u5220\u9664\u8D27\u54C1");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				if(row == -1){
					JOptionPane.showMessageDialog(null,"��ѡ��Ҫɾ������!");
					}else{
						tableModel.removeRow(row);
						
						//
						int rows=table.getRowCount();
						Float totalamout=(float) 0.00;
						Float totalnum=(float) 0.00;
						for (int i = 0; i < rows; i++) {
							totalamout=totalamout+ Float.valueOf((table.getModel().getValueAt(i, 6).toString().isEmpty())?"0":table.getModel().getValueAt(i, 6).toString());
							totalnum=totalnum+ Float.valueOf((table.getModel().getValueAt(i, 4).toString().isEmpty())?"0":table.getModel().getValueAt(i, 4).toString());
						}
                            
						Float ff_amout =(float)(Math.round(totalamout*100))/100;
						Float ff_num =(float)(Math.round(totalnum*100))/100;
						//�ܽ�����������ֵ
						total_amout.setText(ff_amout.toString());
						total_num.setText(ff_num.toString());
						//
					}
				
			}
		});
		button_2.setBounds(1181, 594, 93, 23);
		frame.getContentPane().add(button_2);
		
		button_3 = new JButton("2\u5BA2\u6237\u67E5\u627E");
		button_3.setForeground(Color.RED);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openCustListSelect();
				
			}
		});
		button_3.setBounds(303, 43, 93, 23);
		frame.getContentPane().add(button_3);
		
		sendid = new JTextField();
		sendid.setBounds(948, 79, 66, 21);
		frame.getContentPane().add(sendid);
		sendid.setColumns(10);
		
		sendname = new JTextField();
		sendname.setBounds(1024, 79, 97, 21);
		frame.getContentPane().add(sendname);
		sendname.setColumns(10);
		
		button_5 = new JButton("\u914D\u9001\u5458\u9009\u62E9");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openSend();
			}
		});
		button_5.setBounds(834, 78, 104, 23);
		frame.getContentPane().add(button_5);
		
		JLabel label_4 = new JLabel("3\u9009\u62E9\u79CD\u7C7B\u548C\u5976\u54C1");
		label_4.setForeground(Color.RED);
		label_4.setBounds(37, 103, 123, 15);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("4\u5976\u54C1\u9009\u62E9\u540E\u4FEE\u6539\u6570\u91CF\u3001\u5355\u4EF7\u3001\u91D1\u989D");
		label_5.setForeground(Color.RED);
		label_5.setBounds(425, 111, 267, 15);
		frame.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("5\u4FDD\u5B58\u5355\u636E");
		label_6.setForeground(Color.RED);
		label_6.setBounds(425, 573, 142, 15);
		frame.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("\u603B\u91D1\u989D");
		label_7.setBounds(998, 105, 54, 15);
		frame.getContentPane().add(label_7);
		
		total_amout = new JTextField();
		total_amout.setHorizontalAlignment(SwingConstants.RIGHT);
		total_amout.setEditable(false);
		total_amout.setBounds(1058, 105, 104, 21);
		frame.getContentPane().add(total_amout);
		total_amout.setColumns(10);
		
		label_8 = new JLabel("\u603B\u6570\u91CF");
		label_8.setBounds(817, 105, 54, 15);
		frame.getContentPane().add(label_8);
		
		total_num = new JTextField();
		total_num.setEditable(false);
		total_num.setHorizontalAlignment(SwingConstants.RIGHT);
		total_num.setBounds(856, 105, 93, 21);
		frame.getContentPane().add(total_num);
		total_num.setColumns(10);
		
		JButton button_6 = new JButton("\u63D2\u5165\u7A7A\u767D\u884C");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uu2();
			}
		});
		button_6.setBounds(1016, 594, 130, 23);
		frame.getContentPane().add(button_6);
	
		

	}
	
	protected void uu2() {

    	Vector vRow = new Vector();
    	vRow.add("");
    	vRow.add("");
    	vRow.add("");
    	vRow.add("");
    	vRow.add(""); 
    	vRow.add(""); 
    	vRow.add("");
    	vRow.add("");
    
    	
//    	tableModel.insertRow((tableModel.getRowCount()>=0?tableModel.getRowCount():0),new String[] {"","","","","",""});
    	tableModel.insertRow((tableModel.getRowCount()>=0?tableModel.getRowCount():0),vRow);
	}

	protected void swingPrint(String dh) {
		// TODO Auto-generated method stub
    	
    	if("".equals(dh)) {
    		dh="";
    	}
    	Date d =(Date) datepick.getValue();
		SimpleDateFormat formatter11 = new SimpleDateFormat("yyyy-MM-dd");
		String dateString22 = formatter11.format(d);
		SwingCommonPrinitTools tt=new SwingCommonPrinitTools();
    	tt.printTable(tableModel, "�� �� ��",this.custname.getText(),dh,dateString22,this.sendname.getText(),this.total_amout.getText(),"�����ٺ�����ó���޹�˾");
	}

	protected void aa() {
		// TODO Auto-generated method stub
    	String to="";
    	
    	String custname="�ͻ����ƣ�"+this.custname.getText();
    	String odate= "\n";
    	String custtel="�绰��"+this.custtel.getText();
    	
    	String custaddress="��ַ��"+this.custaddress.getText();
    	
    	
    			
    	MessageFormat footer = new MessageFormat("- {0} -"); //ҳ�ż�ҳ��
		MessageFormat header = new MessageFormat("Printed: " + to.concat(custtel).concat(odate).concat(custaddress)); //ҳü��ʱ��
		
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		aset.add(OrientationRequested.PORTRAIT); //�����д�ӡ����ΪOrientationRequested.LANDSCAPEΪ������
		
		try {
			this.table .print(JTable.PrintMode.FIT_WIDTH, header, footer, true, aset, true);
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void openSend() {
		// TODO Auto-generated method stub
    	SendList sl=new SendList(this);
    	sl.frame.setVisible(true);
		
	}

	protected void openCustListSelect() {
		// TODO Auto-generated method stub
    	try {
			CustListSelect cls=new CustListSelect(this);
			cls.frame.setVisible(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    			
		
	}

	DecimalFormat    df   = new DecimalFormat("######0.00"); 

	protected void uu(String a1, String a2, String dw, Float a3,String strsp) {
		// TODO Auto-generated method stub
//    	tableModel(table.getModel()).
    	Vector vRow = new Vector();
    	vRow.add(a1);
    	vRow.add(a2);
    	vRow.add(dw);
    	vRow.add(strsp);
//    	vRow.add(Float.parseFloat("1"));  
    	vRow.add(("")); 
    	vRow.add(df.format(a3));    	  	
//    	vRow.add(a3);
    	vRow.add("");
    	vRow.add("");
    
    
    	
//    	tableModel.insertRow((tableModel.getRowCount()>=0?tableModel.getRowCount():0),new String[] {"","","","","",""});
    	tableModel.insertRow((tableModel.getRowCount()>=0?tableModel.getRowCount():0),vRow);
	}

	//ˢ����Ʒ�б�����
	public void dd(Integer rr) {
	  
	 	  DefaultTableModel	tableModel=new DefaultTableModel(pl.queryData_select33(tablecategory.getModel().getValueAt(rr, 0).toString()),pl.head_select){
	            public boolean isCellEditable(int row, int column)
	            {
	                return false;
	            }
	        };
	        
	        tableproduct.setModel(tableModel);
	        tableproduct.setEnabled(true);
	        scrollPane_product.setViewportView(tableproduct); 
	}

	private Object[][] queryData_category() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object[][] queryData() {
		// TODO Auto-generated method stub
		Object[][] data=new Object[0][7];

       /* for(int i=0;i<3;i++){
            for(int j=0;j<head.length;j++){
                data[i][0]="��Ʒ����"+i;
                data[i][1]="��Ʒ����"+i;               
                data[i][2]=2.5;
                data[i][3]=2;
                data[i][4]=5;
                
                
            }
        }*/
		
		return data;
	}
	public  String multiply(String num1, String num2) {
		Float aa;
		aa=Float.parseFloat(num1) * Float.parseFloat(num2) ;
		return aa.toString();
    }
	//���ڿؼ�����
	private static DatePicker getDatePicker() {
		final DatePicker datepick;
		// ��ʽ
        String DefaultFormat = "yyyy-MM-dd";
        // ��ǰʱ��
        Date date = new Date();
        // ����
        Font font = new Font("Times New Roman", Font.BOLD, 14);
  
        Dimension dimension = new Dimension(177, 24);
  
//        int[] hilightDays = { 1, 3, 5, 7 };
//  
//        int[] disabledDays = { 4, 6, 5, 9 };
  
        datepick = new DatePicker(date, DefaultFormat, font, dimension);
  
        datepick.setLocation(137, 83);
        datepick.setBounds(80, 40, 177, 24);
        // ����һ���·�����Ҫ������ʾ������
//        datepick.setHightlightdays(hilightDays, Color.red);
//        // ����һ���·��в���Ҫ�����ӣ��ʻ�ɫ��ʾ
//        datepick.setDisableddays(disabledDays);
        // ���ù���
        datepick.setLocale(Locale.CHINA);
        
        // ����ʱ�����ɼ�
        datepick.setTimePanleVisible(false);
		return datepick;
	}
	public void putCust(String a1,String a2,String a3,String a4,String a5,String a6,String a7) {
		// TODO Auto-generated method stub
		custcode.setText(a1);
		custname.setText(a2);
		custabb.setText(a3);
		custtel.setText(a4);
		custaddress.setText(a5);
		sendid.setText(a6);
		sendname.setText(a7);
		

	}

	public void putstring_send(String idsend, String sendid1, String sendname1) {
		
		// TODO Auto-generated method stub
		this.sendid.setText(sendid1);
		this.sendname.setText(sendname1);
		
	}
}
