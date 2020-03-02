package Product;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



import dao.Ca;
import dao.CatJdbc;
import dao.ProductDao;
import dao.ProductJdbc;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

public class ProductList {

	public JFrame frame;
	private JTable table;
	private JTextField category;
	private JTextField pcode;
	private JTextField product;
	private DefaultTableModel tableModel;
	String[] head={"id","种类代码","种类名称","奶品代码","奶品名称","简称","助记码","单价","单位","规格"};
	public String[] head_select={"奶品代码","奶品名称","单位","规格","单价"};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductList window = new ProductList();
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
	public ProductList() {
		initialize();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u5976\u54C1\u5217\u8868");
		frame.setBounds(100, 100, 1037, 488);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBounds(57, 331, 891, -204);
		tableModel=new DefaultTableModel(queryData(),head){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        
        table.setModel(tableModel);
//		frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 87, 957, 306);
		scrollPane.setViewportView(table);
		frame.getContentPane().add(scrollPane);
		
		JLabel label = new JLabel("\u79CD\u7C7B");
		label.setBounds(29, 23, 54, 15);
		frame.getContentPane().add(label);
		
		category = new JTextField();
		category.setBounds(78, 20, 66, 21);
		frame.getContentPane().add(category);
		category.setColumns(10);
		category.setText("");
		
		JLabel label_1 = new JLabel("\u5976\u54C1\u4EE3\u7801");
		label_1.setBounds(181, 23, 54, 15);
		frame.getContentPane().add(label_1);
		
		pcode = new JTextField();
		pcode.setBounds(237, 20, 66, 21);
		frame.getContentPane().add(pcode);
		pcode.setColumns(10);
		pcode.setText("");
		
		JLabel label_2 = new JLabel("\u5976\u54C1\u540D\u79F0");
		label_2.setBounds(327, 23, 54, 15);
		frame.getContentPane().add(label_2);
		
		product = new JTextField();
		product.setBounds(382, 20, 66, 21);
		frame.getContentPane().add(product);
		product.setColumns(10);
		product.setText("");
		
		JButton selectbutton = new JButton("\u67E5\u8BE2");
		selectbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refrshProductList();
//				System.out.println("this.category.getText()"+category.getText());
			}
		});
		selectbutton.setBounds(487, 19, 93, 23);
		frame.getContentPane().add(selectbutton);
		
		JButton pinsert = new JButton("\u65B0\u589E");
		pinsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				ProudctNew pn=new ProudctNew(this);
//				pn.frame.setVisible(true);
				openInsertProductNew();
				
			}
		});
		pinsert.setBounds(29, 403, 93, 23);
		frame.getContentPane().add(pinsert);
		
		JButton pdelete = new JButton("\u5220\u9664");
		pdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductJdbc j=new ProductJdbc();
				//获取管理数据的模式
		    	DefaultTableModel model = (DefaultTableModel) table.getModel();

		    	//获取选中的行数
		    	int row = table.getSelectedRow();
		    	int col = table.getSelectedColumn();
		    	//获取ID	
		    	
		    	if(table.getSelectedRow()>-1) {
		    		Integer id = (Integer)model.getValueAt(row,0);
		    		j.deleteProduct(id);
					//刷新数据
					refrshCat();
		    	}
		    	else {
		    		JOptionPane.showMessageDialog(null, "请选择要删除的数据");
		    	}
			}
		});
		pdelete.setBounds(252, 403, 93, 23);
		frame.getContentPane().add(pdelete);
		
		JButton button = new JButton("\u53D6\u6D88");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		button.setBounds(355, 403, 93, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u4FEE\u6539");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateProduct();
			}
		});
		button_1.setBounds(142, 403, 93, 23);
		frame.getContentPane().add(button_1);
	}
	protected void updateProduct() {
		// TODO Auto-generated method stub
		
		ProductUpdate pu=new ProductUpdate(this);
		pu.frame.setVisible(true);
		int getrow=this.table.getSelectedRow();
		String id1=this.tableModel.getValueAt(getrow, 0).toString();
		String ccode1=this.tableModel.getValueAt(getrow, 1).toString();
		String cname1=this.tableModel.getValueAt(getrow, 2).toString();
		String pcode1=this.tableModel.getValueAt(getrow, 3).toString();
		String pname1=this.tableModel.getValueAt(getrow, 4).toString();
		String pabb1=this.tableModel.getValueAt(getrow, 5).toString();
		String phelpcode1=this.tableModel.getValueAt(getrow, 6).toString();
		String pprice1=this.tableModel.getValueAt(getrow, 7).toString();
		String unitname1=this.tableModel.getValueAt(getrow, 8).toString();
		String remark1=this.tableModel.getValueAt(getrow, 9).toString();
		
		
		pu.putString(ccode1,cname1,pcode1,pname1,pabb1,phelpcode1,pprice1,unitname1,remark1,id1);
		
	}

	protected void refrshCat() {
		// TODO Auto-generated method stub
		 tableModel=new DefaultTableModel(queryData(),head){
	            public boolean isCellEditable(int row, int column)
	            {
	                return false;
	            }
	        };
	        
	        table.setModel(tableModel);
	        table.setEnabled(true);
		
	}

	protected void openInsertProductNew() {
		// TODO Auto-generated method stub
		ProudctNew pn=new ProudctNew(this);
		pn.frame.setVisible(true);
	}

	public Object[][] queryData(){
    	ProductJdbc jj=new ProductJdbc();
    	List<ProductDao> list=jj.selectProductList("","","");
    	
		Object[][] data = new Object[list.size()][head.length];
    
        for(int i=0;i<list.size();i++){
            for(int j=0;j<head.length;j++){
                data[i][0]=list.get(i).getId();
                data[i][1]=list.get(i).getCcode();
                data[i][2]=list.get(i).getCname();
                data[i][3]=list.get(i).getPcode();
                data[i][4]=list.get(i).getPname();
                data[i][5]=list.get(i).getPabb();
                data[i][6]=list.get(i).getPhelpcode();
                data[i][7]=list.get(i).getPrice();
                data[i][8]=list.get(i).getUnitname();
                data[i][9]=list.get(i).getRemark();
                
            }
        }
        return data;
    }
	public Object[][] queryData2(){
    	ProductJdbc jj=new ProductJdbc();
    	String a1=this.category.getText()+"";
    	String a2=this.pcode.getText()+"";
    	String a3=this.product.getText()+"";
    	
        List<ProductDao> list=jj.selectProductList(a1,  a2,a3);
    	
    	
		Object[][] data = new Object[list.size()][head.length];
    
        for(int i=0;i<list.size();i++){
            for(int j=0;j<head.length;j++){
            	  data[i][0]=list.get(i).getId();
                  data[i][1]=list.get(i).getCcode();
                  data[i][2]=list.get(i).getCname();
                  data[i][3]=list.get(i).getPcode();
                  data[i][4]=list.get(i).getPname();
                  data[i][5]=list.get(i).getPabb();
                  data[i][6]=list.get(i).getPhelpcode();
                  data[i][7]=list.get(i).getPrice();
                  data[i][8]=list.get(i).getUnitname();
                  data[i][9]=list.get(i).getRemark();
                
            }
        }
        return data;
    }
	public Object[][] queryData2_select(){
    	ProductJdbc jj=new ProductJdbc();
    	String a1=this.category.getText()+"";
    	String a2=this.pcode.getText()+"";
    	String a3=this.product.getText()+"";
    	
        List<ProductDao> list=jj.selectProductList_select(a1,  a2,a3);
    	
    	
		Object[][] data = new Object[list.size()][head_select.length];
		DecimalFormat    df   = new DecimalFormat("######0.00"); 
        for(int i=0;i<list.size();i++){
            for(int j=0;j<head_select.length;j++){
                
                data[i][0]=list.get(i).getPcode();
                data[i][1]=list.get(i).getPname();               
                data[i][2]=list.get(i).getUnitname();
                data[i][3]=list.get(i).getRemark();
                data[i][4]= df.format(list.get(i).getPrice());
//                data[i][4]=( list.get(i).getPrice());
                
                
            }
        }
        return data;
    }
	public Object[][] queryData_select(String a1){
    	ProductJdbc jj=new ProductJdbc();

    	
        List<ProductDao> list=jj.selectProductList_select(a1,  "","");
    	
    	
		Object[][] data = new Object[list.size()][head_select.length];
    
        for(int i=0;i<list.size();i++){
            for(int j=0;j<head_select.length;j++){
            	   data[i][0]=list.get(i).getPcode();
                   data[i][1]=list.get(i).getPname();               
                   data[i][2]=list.get(i).getUnitname();
                   data[i][3]=list.get(i).getPrice();
                   data[i][4]=list.get(i).getRemark();
                
            }
        }
        return data;
    }
	public Object[][] queryData_select33(String a1){
    	ProductJdbc jj=new ProductJdbc();

    	
        List<ProductDao> list=jj.selectProductList_select33(a1,  "","");
    	
    	
		Object[][] data = new Object[list.size()][head_select.length];
    
        for(int i=0;i<list.size();i++){
            for(int j=0;j<head_select.length;j++){
            	   data[i][0]=list.get(i).getPcode();
                   data[i][1]=list.get(i).getPname();               
                   data[i][2]=list.get(i).getUnitname();
                   data[i][3]=list.get(i).getRemark();
                   data[i][4]=list.get(i).getPrice();
                
            }
        }
        return data;
    }
	 //刷新数据
    public void refrshProductList() {
		   	   tableModel=new DefaultTableModel(queryData2(),head){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        
        table.setModel(tableModel);
        table.setEnabled(true);
        
	}
	 //刷新数据奶品列表数据
    public void refrshProductList_select(String ss) {
		   	   tableModel=new DefaultTableModel(queryData_select(ss),head){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        
        table.setModel(tableModel);
        table.setEnabled(true);
        
	}
}
