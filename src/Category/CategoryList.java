package Category;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Tool.Tools;
import dao.Ca;
import dao.CatJdbc;
import dao.Customer;
import dao.Jdbc;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CategoryList {

	public JFrame frame;
	private JTable table;
	String[] head={"id","�������","��������","������","��ע","ʱ��"};
	public String[] head_select={"�������","��������"};
	private DefaultTableModel tableModel;
	private CategoryUpdate cu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoryList window = new CategoryList();
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
	public CategoryList() {
		initialize();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u79CD\u7C7B\u7C7B\u522B");
		frame.setBounds(100, 100, 667, 375);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBounds(41, 157, 378, 129);
		
		tableModel=new DefaultTableModel(queryData(),head){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        
        table.setModel(tableModel);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 21, 597, 241);
       
		table.setAutoResizeMode(0);
		//���ñ���Զ��������
	     Tools to=new Tools();
//        to.FitTableColumns(table);
		 to.setColumnSize(table, 2, 30, 300, 200);
        scrollPane.setViewportView(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		frame.getContentPane().add(scrollPane);
		
		JButton button = new JButton("\u65B0\u589E");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openInsertCust();
			}
		});
		button.setBounds(27, 272, 93, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u4FEE\u6539");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openCategoryUpdate();
			}
		});
		button_1.setBounds(130, 272, 93, 23);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u5220\u9664");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CatJdbc j=new CatJdbc();
				//��ȡ�������ݵ�ģʽ
		    	DefaultTableModel model = (DefaultTableModel) table.getModel();

		    	//��ȡѡ�е�����
		    	int row = table.getSelectedRow();
		    	int col = table.getSelectedColumn();
		    	//��ȡID	
		    	
		    	if(table.getSelectedRow()>-1) {
		    		Integer id = (Integer)model.getValueAt(row,0);
		    		j.deleteCategory(id);
					//ˢ������
					refrshCat();
		    	}
		    	else {
		    		JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ��������");
		    	}
			}
		});
		button_2.setBounds(233, 272, 93, 23);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("\u53D6\u6D88");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_3.setBounds(332, 272, 93, 23);
		frame.getContentPane().add(button_3);
	}
	protected void openCategoryUpdate() {
		// TODO Auto-generated method stub
		
		 if(table.getSelectedRow()>-1) {
	    	   cu=new CategoryUpdate(this);
	    	   cu.frame.setVisible(true);
	    	   printData(this.table);
  	   }
  	   else {
  		   JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ�����");
  	   }
	}

	private void printData(JTable table){

    	//��ȡ���������� int row = table.getRowCount();int col = table.getColumnCount();   	

    	//��ȡѡ�е�����
    	int row = table.getSelectedRow();
    	int col = table.getSelectedColumn();
    	//��ȡ�������ݵ�ģʽ
    	DefaultTableModel model = (DefaultTableModel) table.getModel();

    	//��ȡID
    	Integer id = (Integer)model.getValueAt(row,0);
    	String ccode = (String)model.getValueAt(row,1);
    	String cname = (String)model.getValueAt(row,2);
    	String cabb=(String)model.getValueAt(row,3);
    	String remark=(String)model.getValueAt(row, 4);
    	
    	Date ts = (Date)model.getValueAt(row,5);    	
    	   	
    	

    	//��ֵ    	
    	cu.putstring(id,ccode,cname,cabb,remark);
    	
    	//��ȡ����
    	String a = model.getColumnName(col);

    	

    	}
	public Object[][] queryData(){
    	CatJdbc jj=new CatJdbc();
        List<Ca> list=jj.selectCat();
        
		Object[][] data = new Object[list.size()][head.length];
    
        for(int i=0;i<list.size();i++){
            for(int j=0;j<head.length;j++){
                data[i][0]=list.get(i).getId();
                data[i][1]=list.get(i).getCcode();
                data[i][2]=list.get(i).getCname();
                data[i][3]=list.get(i).getCabb();
                data[i][4]=list.get(i).getRemark();
                data[i][5]=list.get(i).getTs();
                
            }
        }
        return data;
    }
	public Object[][] queryData_select(){
    	CatJdbc jj=new CatJdbc();
        List<Ca> list=jj.selectCat_select();
        
		Object[][] data = new Object[list.size()][head_select.length];
    
        for(int i=0;i<list.size();i++){
            for(int j=0;j<head.length;j++){
                
                data[i][0]=list.get(i).getCcode();
                data[i][1]=list.get(i).getCname();
                
                
            }
        }
        return data;
    }
	public void openInsertCust() {
   		CategoryNew cld=new CategoryNew(this);
   		cld.frame.setVisible(true);

   	}
	  //ˢ������
    public void refrshCat() {
		   	   tableModel=new DefaultTableModel(queryData(),head){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        
        table.setModel(tableModel);
        table.setEnabled(true);
        
	}
    public static void setSomeColumnSize(JTable table, int[] i, int preferedWidth, int maxWidth, int minWidth){
		TableColumnModel cm = table.getColumnModel();
		if(i.length == 0){
			return;
		}
		for(int j = 0; j < i.length; j++){
			TableColumn column = cm.getColumn(i[j]);  
			column.setPreferredWidth(preferedWidth);
			column.setMaxWidth(maxWidth);
			column.setMinWidth(minWidth);
		}
    }
 
}
