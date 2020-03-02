package Month;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.hssf.model.Sheet;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.eclipse.ui.forms.IFormColors;

import com.eltima.components.ui.DatePicker;

import Customer.CustListSelect3;
import dao.Customer;
import dao.Jdbc;
import dao.MonthReportJdbc;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class MonthReport {

	 public JFrame frame;
	 JTextField custname;
	 JTable table;
	 DefaultTableModel tableModel;
	JScrollPane scrollPane;
	String[] head={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonthReport window = new MonthReport();
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
	public MonthReport() {
		initialize();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	 DatePicker datepick_begin;
	 DatePicker datepick_end;
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u6708\u62A5\u8868");
		frame.setBounds(20, 40, 1300, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5F00\u59CB\u65E5\u671F");
		label.setBounds(43, 47, 54, 15);
		frame.getContentPane().add(label);
		//ʱ��ؼ���ʼ����
		//ʱ��ؼ�
				
		        datepick_begin = getDatePicker_begin(); 
		        
		        frame.getContentPane().add(datepick_begin);
		
		//ʱ��ؼ���������
		      //ʱ��ؼ�
				
				datepick_end = getDatePicker_end(); 
				//
				Date d =(Date) datepick_end.getValue();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
				String dateString = formatter.format(d);
				AncestorListener listener = null;
				//
		        frame.getContentPane().add(datepick_end);
		       
		
		JLabel label_1 = new JLabel("\u7ED3\u675F\u65E5\u671F");
		label_1.setBounds(250, 47, 54, 15);
		frame.getContentPane().add(label_1);
		
		JButton button = new JButton("\u9009\u62E9\u5BA2\u6237");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openCustListSelect();
			}
		});
		button.setBounds(461, 36, 93, 23);
		frame.getContentPane().add(button);
		
		custname = new JTextField();
		custname.setBounds(571, 37, 190, 21);
		frame.getContentPane().add(custname);
		custname.setColumns(10);
		
		JButton button_1 = new JButton("\u67E5\u8BE2");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//ˢ������
				dd();
			}
		});
		button_1.setBounds(782, 40, 66, 23);
		frame.getContentPane().add(button_1);
		
		table = new JTable();
		table.setBounds(51, 401, 1065, 299);
		tableModel=new DefaultTableModel(queryData(),head){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        table.setModel(tableModel);
//		frame.getContentPane().add(table);
		
        scrollPane= new JScrollPane();
		scrollPane.setBounds(40, 80, 1200, 220);
		scrollPane.setViewportView(table);
		frame.getContentPane().add(scrollPane);
		
		JButton btnexcel = new JButton("\u5BFC\u51FAExcel");
		btnexcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				FileDialog fd = new FileDialog(frame, "�±�����Excel", FileDialog.SAVE);
				 fd.setLocation(400, 250);
			     fd.setVisible(true);  
			     String stringfile = fd.getDirectory()+fd.getFile()+".xls";  
		         MonthReport oDao = new MonthReport();
				 Date d2 =(Date) datepick_end.getValue();
				SimpleDateFormat formatter22 = new SimpleDateFormat("yyyy��MM��");
				String enddate = formatter22.format(d2);
				 try {
					oDao.exportTasble(table, new File(stringfile),custname.getText().toString(),enddate);
					JOptionPane.showMessageDialog(null, "������ɣ�·��="+stringfile);
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnexcel.setBounds(858, 40, 93, 23);
		frame.getContentPane().add(btnexcel);
		
		
	}
	private void datepick_endaddFocusListener(FocusListener focusListener) {
		// TODO Auto-generated method stub
		
	}

	MonthReportJdbc jj=new MonthReportJdbc();
	
	public void dd() {
		// TODO Auto-generated method stub
		//���ô洢���̣���������ʼ���ڡ��������ڡ��ͻ�����
		//
		Date d1 =(Date) datepick_begin.getValue();
		SimpleDateFormat formatter12 = new SimpleDateFormat("yyyy-MM-dd");
		String begindate = formatter12.format(d1);
		
		Date d2 =(Date) datepick_end.getValue();
		SimpleDateFormat formatter22 = new SimpleDateFormat("yyyy-MM-dd");
		String enddate = formatter22.format(d2);
		
	
		
		//��ʼ���ڴ��� �������� �쳣����		
		if(begindate.compareTo(enddate)>0) {

			 DefaultTableModel	tableModel=new DefaultTableModel(new Object[0][0],head){
		            public boolean isCellEditable(int row, int column)
		            {
		                return false;
		            }
		        };
		        
		        table.setModel(tableModel);
		        table.setEnabled(true);
		        scrollPane.setViewportView(table); 
			JOptionPane.showMessageDialog(null, "����ʼ���ڡ� ���� ���������ڡ���������ѡ��");
			
		}
		else {
			jj.excute_proceduer(begindate,enddate,custname.getText());
			Object zz[][]= queryData_select_date();
			String[] hh=queryData_select_head();
			//��ѯ�Ƿ��д˲�ѯ���������ݡ�
			if(zz.length>0) {					
				//
				 DefaultTableModel	tableModel=new DefaultTableModel(zz,hh){
			            public boolean isCellEditable(int row, int column)
			            {
			                return false;
			            }
			        };
			        
			        table.setModel(tableModel);
			        table.setEnabled(true);
			        scrollPane.setViewportView(table); 
			        String[] headWeek=getWeek();
			        
			        if(headWeek!=null) {
			        	tableModel.insertRow(0, headWeek);
			        }
			        
			        DefaultTableCellRenderer  renderer  =  new  DefaultTableCellRenderer();   //set column align center
			        DefaultTableCellRenderer  renderer_right  =  new  DefaultTableCellRenderer();   //set column align center
			        DefaultTableCellRenderer  renderer_left  =  new  DefaultTableCellRenderer();   //set column align center
					renderer.setHorizontalAlignment(JTextField.CENTER);
					renderer_left.setHorizontalAlignment(JTextField.LEFT);
					renderer_right.setHorizontalAlignment(JTextField.RIGHT);
					
					table.setDefaultRenderer(Object.class, renderer);
					table.getColumnModel().getColumn(0).setCellRenderer(renderer_left);
					table.getColumnModel().getColumn(hh.length-1).setCellRenderer(renderer_right);

			}
			else {
				
				 DefaultTableModel	tableModel=new DefaultTableModel(new Object[0][0],head){
			            public boolean isCellEditable(int row, int column)
			            {
			                return false;
			            }
			        };
			        
			        table.setModel(tableModel);
			        table.setEnabled(true);
			        scrollPane.setViewportView(table); 
			}
			
		}
	
		
		
	}

	private String[] getWeek() {
		// TODO Auto-generated method stub
		
	return	jj.selectMonthReport_dateWeek();
	}

	protected void openCustListSelect() {
		// TODO Auto-generated method stub
		try {
			CustListSelect3 cls=new CustListSelect3(this);
			cls.frame.setVisible(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Object[][] queryData() {
		// TODO Auto-generated method stub
//		MonthReportJdbc jj=new MonthReportJdbc();       
//        return jj.selectMonthReport();
		Object[][] a=null;
		return a;
		
	}
	
	public Object[][] queryData_select_date() {
		 
		return jj.selectMonthReport_date();
	}
	public String[] queryData_select_head() {
		 
		return jj.selectMonthReport_head();
	}
	
	public void putCustNmae(String a){
		this.custname.setText(a);
	}
	
	private static DatePicker getDatePicker_begin() {
		final DatePicker datepick;
		// ��ʽ
        String DefaultFormat = "yyyy-MM-dd";
        // ��ǰʱ��
        Date date = new Date();
        // ����
        Font font = new Font("Times New Roman", Font.BOLD, 14);
  
        Dimension dimension = new Dimension(177, 24);
  
        int[] hilightDays = { 1, 3, 5, 7 };
  
        int[] disabledDays = { 4, 6, 5, 9 };
  
        datepick = new DatePicker(date, DefaultFormat, font, dimension);
  
        datepick.setLocation(137, 83);
        datepick.setBounds(120, 40, 110, 24);
      
        // ���ù���
        datepick.setLocale(Locale.CHINA);
        
        // ����ʱ�����ɼ�
        datepick.setTimePanleVisible(false);
		return datepick;
	}
	private static DatePicker getDatePicker_end() {
		final DatePicker datepick;
		// ��ʽ
//        String DefaultFormat = "yyyy-MM";
        String DefaultFormat = "yyyy-MM-dd";
        // ��ǰʱ��
        Date date = new Date();
        // ����
        Font font = new Font("Times New Roman", Font.BOLD, 14);
  
        Dimension dimension = new Dimension(177, 24);
  
        int[] hilightDays = { 1, 3, 5, 7 };
  
        int[] disabledDays = { 4, 6, 5, 9 };
  
        datepick = new DatePicker(date, DefaultFormat, font, dimension);
  
        datepick.setLocation(137, 83);
        datepick.setBounds(320, 40, 110, 24);
       
        // ���ù���
        datepick.setLocale(Locale.CHINA);
        
        // ����ʱ�����ɼ�
        datepick.setTimePanleVisible(false);
		return datepick;
	}
	/**����JTable��excel */
    public void exportTable1(JTable table, File file) throws IOException {
        TableModel model = table.getModel();
        BufferedWriter bWriter = new BufferedWriter(new FileWriter(file));  
        bWriter.newLine();
        for(int i=0; i < model.getColumnCount(); i++) {
            bWriter.write(model.getColumnName(i));
            bWriter.write("\t");
        }
        bWriter.newLine();
        for(int i=0; i< model.getRowCount(); i++) {
            for(int j=0; j < model.getColumnCount(); j++) {
                bWriter.write(model.getValueAt(i,j).toString());
                bWriter.write("\t");
            }
            bWriter.newLine();
        }
        bWriter.close();
        System.out.println("write out to: " + file);

    }
    public void exportTasble(JTable table, File file,String cust,String enmonth) throws IOException, RowsExceededException, Throwable {
        TableModel model = table.getModel();
        HSSFWorkbook wb = new HSSFWorkbook();
	    HSSFSheet sheet = wb.createSheet("new sheet");
	    //
	    File xlsFile = new File(file.toString());
	      // ����һ��������
	      WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
	      // ����һ��������
	      WritableSheet t = workbook.createSheet("sheet1", 0);
	      
	      int rowss=0;
	      t.insertRow(rowss);rowss++;
	      t.setColumnView(0,20);
	      //�����еĿ��Ϊ5����λ
	      for (int i = 1; i < 32; i++) {
	    	  t.setColumnView(i,5);
		}
	    //���⣺ ����12 �Ӵ�
	      WritableFont titleFont=new WritableFont(WritableFont.createFont("����"),12,WritableFont.BOLD);
	      WritableCellFormat titleFormat=new WritableCellFormat(); //����WritableCellFormat���󣬽��ö���Ӧ���ڵ�Ԫ��Ӷ����õ�Ԫ�����ʽ 
	      titleFormat.setFont(titleFont); //���������ʽ 
	      
	      try {
	    	  
	    	//�ͻ�  
			t.addCell(new Label(0, rowss, "��λ ��"+cust,titleFormat));
			//�ϲ����¸�
			t.mergeCells(0, rowss, 4, rowss);
			
			
			//�·���2019��10��
			//�ϲ����¸�
			int kji=0;
			if(model.getColumnCount()>5)
			{
				kji=model.getColumnCount();
				
			}
			else {
				kji=5;
			
			}
			int jj=(int) Math.floor(kji/2);
			System.out.println("kji="+kji+",jj="+jj);
			if( jj >= 6) {

				t.mergeCells(jj, rowss, jj + 4, rowss);
				t.addCell(new Label(jj, rowss, enmonth,titleFormat));
			}
			else {
				t.mergeCells(6, rowss, 10, rowss);
				t.addCell(new Label(6, rowss, enmonth,titleFormat));
			}
			
			
			
			rowss++;
			t.insertRow(rowss);rowss++;
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	      //���ݸ�ʽ
	      WritableFont contentFormatFont=new WritableFont(WritableFont.createFont("����"),12);
	      WritableCellFormat contentFormat=new WritableCellFormat(); //����WritableCellFormat���󣬽��ö���Ӧ���ڵ�Ԫ��Ӷ����õ�Ԫ�����ʽ 
	      contentFormat.setFont(contentFormatFont); //���������ʽ 
	      contentFormat.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
	      for(int i=0; i < model.getColumnCount(); i++) {
	    	  try {
	    		 
				t.addCell(new Label(i, rowss, model.getColumnName(i),contentFormat));
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        }rowss++;
	     
	        //��Ʒ����
	        
	        
	      //��������հ��е���
	        int rownull=0;
	        String ss="";
	      for (int row = 0; row < model.getRowCount(); row++)
	      {
	    	  t.addCell(new Label(0, rowss, model.getValueAt(row,0).toString(),contentFormat));
	         for (int col = 1; col < model.getColumnCount(); col++)
	         {
	            // ���������������
	            try {
       	             //��ֵ��ӡ
	            	if(model.getRowCount()<=0) {
	            		t.addCell(new Label(col, rowss,"" ,contentFormat) );
	            	}
	            	//��ӡ�������ݵ�0�У����ڼ�
	            	else if(row == 0) {
	            		if(model.getValueAt(row,col)!=null) {
	            			 ss=model.getValueAt(row,col).toString();
	            		}else {
	            			ss="";
	            		}	            		
	            		t.addCell(new Label(col, rowss, (ss),contentFormat) );
	            	}else {
	            	    //�����ݵ��ж�
	            		if(model.getValueAt(row,col).toString().isEmpty()) 
	            		{
	            			t.addCell(new Label(col, rowss, "",contentFormat) );
	            		}
	            		//���ǿ����ݣ���ӡΪ���ָ�ʽ
	            		else {
	            			
	            			t.addCell(new Number(col, rowss, Float.parseFloat(model.getValueAt(row,col).toString()),contentFormat) );
	            		}
	            	}
	            						
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	         }
	         rowss++;
	         rownull=rowss;
	      }
	      //����հ���
	      int rownull1=0;
	      for(int u=rownull-1;u<13;u++) {
	    	  t.insertRow(u);
	    	  for (int col = 0; col < model.getColumnCount(); col++)
		         {
	    		  t.addCell(new Label(col, u,"" ,contentFormat) );
		         }	    	  
	      }
	     
	      
	      workbook.write();
	      try {
			workbook.close();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	    
        

    }
}
