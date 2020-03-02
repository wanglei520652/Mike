package BigTable;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Date;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.eltima.components.ui.DatePicker;

import Month.MonthReport;
import Tool.FrameShow;
import Tool.Tools;
import dao.JdbcAll;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class BB {

	public JFrame frame;
	public JTable table;
	public DefaultTableModel tableModel;
	public DatePicker datepick_begin;
	public DatePicker datepick_end;
	public Connection con = null;
	public CallableStatement cstm=null;
	public PreparedStatement psdate;
	public PreparedStatement pshead;
	public ResultSet rsdate;
	public ResultSet rshead;
	ResultSetMetaData resultSetMetaData =null;
	public Object[] obj_head;
	public JScrollPane scrollPane ;
	private Object[][] obj_date;
	private JButton btnexcel;
	private String file;
	private JLabel custname;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BB window = new BB();
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
	public BB() {
		initialize();
		//�Զ���ҳ���С--ȫ��
				int fraWidth = this.frame.getWidth();//frame�Ŀ�
				int fraHeight = this.frame.getHeight();//frame�ĸ�
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int screenWidth = screenSize.width;
				int screenHeight = screenSize.height;
				this.frame.setSize(screenWidth, screenHeight);
				this.frame.setLocation(0, 0);
				float proportionW = screenWidth/fraWidth;
				float proportionH = screenHeight/fraHeight;
				
				FrameShow.modifyComponentSize(this, proportionW,proportionH);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u6708\u5927\u8868");
		frame.setBounds(50, 40, 1200, 615);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		JLabel label = new JLabel("\u5F00\u59CB\u65E5\u671F");
		label.setBounds(58, 40, 62, 30);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u7ED3\u675F\u65E5\u671F");
		label_1.setBounds(264, 48, 54, 15);
		frame.getContentPane().add(label_1);
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refeshDate();
			}
		});
		btnNewButton.setBounds(458, 44, 93, 23);
		frame.getContentPane().add(btnNewButton);
		//ʱ��ؼ���ʼ����
				//ʱ��ؼ�
						
		datepick_begin = getDatePicker_begin(); 
		frame.getContentPane().add(datepick_begin);
		
		//ʱ��ؼ���������
	      //ʱ��ؼ�
		datepick_end = getDatePicker_end(); 
		frame.getContentPane().add(datepick_end);
		scrollPane= new JScrollPane();
		scrollPane.setBounds(44, 80, 1250, 600);
		
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		btnexcel = new JButton("\u5BFC\u51FAExcel");
		btnexcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(table.getRowCount()>0) {
						outExcel();
					}else {
						JOptionPane.showMessageDialog(null, "���Ȳ�ѯ���ݺ��ٴ�ӡ��");
					}
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnexcel.setBounds(604, 44, 93, 23);
		frame.getContentPane().add(btnexcel);
		
	}

	protected void outExcel() throws IOException {
		// TODO Auto-generated method stub
		FileDialog fd = new FileDialog(frame, "�´󱨱���Excel", FileDialog.SAVE);
		 fd.setLocation(400, 250);
	     fd.setVisible(true);  
	     String stringfile = fd.getDirectory()+fd.getFile()+".xls";  
        
        Date d1 =(Date) datepick_begin.getValue();
		 Date d2 =(Date) datepick_end.getValue();
		SimpleDateFormat formatter22 = new SimpleDateFormat("yyy-MM-dd");
		String begindate=formatter22.format(d1);
		String enddate = formatter22.format(d2);
		
		
		 try {
			exportTasble(table, new File(stringfile),begindate,enddate);
			JOptionPane.showMessageDialog(null, "������ɣ�·��="+stringfile);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public void exportTasble(JTable table, File file,String begindate,String enddate) throws IOException, RowsExceededException, Throwable {
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
//	      t.setColumnView(0,20);
	      //�����еĿ��Ϊ5����λ
//	      for (int i = 1; i < 32; i++) {
//	    	  t.setColumnView(i,5);
//		}
	    //���⣺ ����12 �Ӵ�
	      WritableFont titleFont=new WritableFont(WritableFont.createFont("����"),12,WritableFont.BOLD);
	      WritableCellFormat titleFormat=new WritableCellFormat(); //����WritableCellFormat���󣬽��ö���Ӧ���ڵ�Ԫ��Ӷ����õ�Ԫ�����ʽ 
	      titleFormat.setFont(titleFont); //���������ʽ 
	      
	      try {
	    	  
	    	//�ͻ�  
			t.addCell(new Label(0, rowss, "��ʼ���� ��"+begindate+"--�������ڣ�"+enddate,titleFormat));
			//�ϲ����¸�
			t.mergeCells(0, rowss, 10, rowss);
			
			
			//�·���2019��10��
			//�ϲ����¸�
			t.mergeCells(15, rowss, 20, rowss);
//			t.addCell(new Label(15, rowss, enmonth,titleFormat));
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
	        
	        
	        
	        
	        //��Ʒ����
	        
	        
	      //��������հ��е���
	        int rownull=0;
	      for (int row = 0; row < model.getRowCount(); row++)
	      {
	    	  t.addCell(new Label(0, rowss, model.getValueAt(row,0).toString(),contentFormat));
	         for (int col = 1; col < model.getColumnCount(); col++)
	         {
	            // ���������������
	            try {
	            	if("".equals(model.getValueAt(row,col).toString()) &&model.getValueAt(row,col) != null) {
	            		t.addCell(new Label(col, rowss,"" ,contentFormat) );
	            	}else {
	            		if(col == model.getColumnCount()-1 ) {	            			
	            			t.addCell(new Number(col, rowss, Float.parseFloat(model.getValueAt(row,col).toString()),contentFormat) );
	            		}
	            		else if(row== model.getRowCount()-1) {
	            			
	            			t.addCell(new Number(col, rowss, Float.parseFloat(model.getValueAt(row,col).toString()),contentFormat) );
	            		}else {
	            		t.addCell(new Label(col, rowss, model.getValueAt(row,col).toString(),contentFormat));
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
	            
	      workbook.write();
	      try {
			workbook.close();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	    
        

    }



	protected void refeshDate() {
		// TODO Auto-generated method stub
		//
		Date d1 =(Date) datepick_begin.getValue();
		SimpleDateFormat formatter12 = new SimpleDateFormat("yyyy-MM-dd");
		String begindate = formatter12.format(d1);
		
		Date d2 =(Date) datepick_end.getValue();
		SimpleDateFormat formatter22 = new SimpleDateFormat("yyyy-MM-dd");
		String enddate = formatter22.format(d2);
		JdbcAll ja=new JdbcAll();
		
		con=ja.getCon();
		String sql = "{CALL Probigmonth22(?,?)}"; //���ô洢���� 
		String sqldate="select * from temp2bigmonth2 order by ����";
		
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();//���ü�����
		render.setHorizontalAlignment(SwingConstants.RIGHT);//���ж���
		
		
		
		try {
			cstm = con.prepareCall(sql);
			cstm.setString(1, begindate); // �洢����������� 
			cstm.setString(2, enddate); // �洢����������� 	
			//ִ�д洢����
			
			boolean sok = cstm.execute();
				psdate=con.prepareStatement(sqldate);	
				rsdate=psdate.executeQuery();
				resultSetMetaData =rsdate.getMetaData();
				int rows =0;
				rsdate.last(); 				
				rows= rsdate.getRow();//������				
				rsdate.beforeFirst();
				int cols=resultSetMetaData.getColumnCount();//������
				obj_head=new Object[cols] ;
				//����
				for (int i = 0; i < cols; i++) {
					obj_head[i]=resultSetMetaData.getColumnName(i+1);
				}
				
				obj_date=new String[rows][cols];
				int i=0;
				//����
				while(rsdate.next()) {
					for (int j = 0; j < cols; j++) {					
						obj_date[i][j]=rsdate.getString(j+1).toString();
//						
					}
					i++;
				}
				if(rows>0) {
					 DefaultTableModel	tableModel=new DefaultTableModel(obj_date,obj_head){
				            public boolean isCellEditable(int row, int column)
				            {
				                return false;
				            }
				        };
				      //���ñ���Զ��������
					     Tools to=new Tools();
					   
				        table.setModel(tableModel);
				        table.setEnabled(true);
				        scrollPane.setViewportView(table); 
				        
				        table.setAutoResizeMode(0);
				        to.setColumnSize(table, 0, 70, 300, 50);
					     to.setColumnSize(table, 1, 40, 300, 20);
					     for (int j = 2; j < table.getModel().getColumnCount()-1; j++) {
					    	 to.setColumnSize(table, j, 150, 1000, 50);
						}

					     
				      for (int i1 = 1; i1 < cols; i1++) {
						
				    	  TableColumn column=table.getColumnModel().getColumn(i1);
				    	  column.setCellRenderer(render);//�Ҷ���

					}
				        
				}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(!con.isClosed()) {
					con.close();
					psdate.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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
        datepick = new DatePicker(date, DefaultFormat, font, dimension);
        datepick.setLocation(137, 83);
        datepick.setBounds(320, 40, 110, 24);
       
        // ���ù���
        datepick.setLocale(Locale.CHINA);
        
        // ����ʱ�����ɼ�
        datepick.setTimePanleVisible(false);
		return datepick;
	}
	
	
}
