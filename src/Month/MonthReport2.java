package Month;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.eltima.components.ui.DatePicker;

import Customer.CustListSelect3;
import dao.Customer;
import dao.Jdbc;
import dao.MonthReportJdbc;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class MonthReport2 {

	private JFrame frame;
	private JTextField custname;
	private JTable table;
	private DefaultTableModel tableModel;
	JScrollPane scrollPane;
	String[] head={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonthReport2 window = new MonthReport2();
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
	public MonthReport2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	 DatePicker datepick_begin;
	 DatePicker datepick_end;
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u6708\u62A5\u8868");
		frame.setBounds(100, 100, 1220, 504);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5F00\u59CB\u65E5\u671F");
		label.setBounds(43, 47, 54, 15);
		frame.getContentPane().add(label);
		//时间控件开始日期
		//时间控件
				
		        datepick_begin = getDatePicker_begin(); 
		        
		        frame.getContentPane().add(datepick_begin);
		        
		
		
		//时间控件结束日期
		      //时间控件
				
				datepick_end = getDatePicker_end(); 
		        
		        frame.getContentPane().add(datepick_end);
		       
		
		JLabel label_1 = new JLabel("\u7ED3\u675F\u65E5\u671F");
		label_1.setBounds(313, 47, 54, 15);
		frame.getContentPane().add(label_1);
		
		JButton button = new JButton("\u9009\u62E9\u5BA2\u6237");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openCustListSelect();
			}
		});
		button.setBounds(610, 39, 93, 23);
		frame.getContentPane().add(button);
		
		custname = new JTextField();
		custname.setBounds(720, 40, 190, 21);
		frame.getContentPane().add(custname);
		custname.setColumns(10);
		
		JButton button_1 = new JButton("\u67E5\u8BE2");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//刷新数据
				dd();
			}
		});
		button_1.setBounds(931, 43, 66, 23);
		frame.getContentPane().add(button_1);
		
		table = new JTable();
		table.setBounds(51, 401, 1065, -299);
		tableModel=new DefaultTableModel(queryData(),head){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        table.setModel(tableModel);
//		frame.getContentPane().add(table);
		
        scrollPane= new JScrollPane();
		scrollPane.setBounds(37, 92, 1140, 308);
		scrollPane.setViewportView(table);
		frame.getContentPane().add(scrollPane);
		
		JButton btnexcel = new JButton("\u5BFC\u51FAExcel");
		btnexcel.setBounds(1007, 43, 93, 23);
		frame.getContentPane().add(btnexcel);
		
		JButton button_2 = new JButton("\u6253\u5370");
		button_2.setBounds(1110, 43, 61, 23);
		frame.getContentPane().add(button_2);
	}
	MonthReportJdbc jj=new MonthReportJdbc();
	protected void dd() {
		// TODO Auto-generated method stub
		//调用存储过程：参数：开始日期、结束日期、客户名称
		//
		Date d1 =(Date) datepick_begin.getValue();
		SimpleDateFormat formatter12 = new SimpleDateFormat("yyyy-MM-dd");
		String begindate = formatter12.format(d1);
		
		Date d2 =(Date) datepick_end.getValue();
		SimpleDateFormat formatter22 = new SimpleDateFormat("yyyy-MM-dd");
		String enddate = formatter22.format(d2);
		//
		System.out.println("开始日期="+begindate+",结束日期="+enddate+",客户="+custname.getText());
		jj.excute_proceduer(begindate,enddate,custname.getText());
		
		//
		 DefaultTableModel	tableModel=new DefaultTableModel(queryData_select_date(),queryData_select_head()){
	            public boolean isCellEditable(int row, int column)
	            {
	                return false;
	            }
	        };
	        
	        table.setModel(tableModel);
	        table.setEnabled(true);
	        scrollPane.setViewportView(table); 
		
	}

	protected void openCustListSelect() {
		// TODO Auto-generated method stub
		try {
			CustListSelect3 cls=new CustListSelect3();
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
		// 格式
        String DefaultFormat = "yyyy-MM-dd";
        // 当前时间
        Date date = new Date();
        // 字体
        Font font = new Font("Times New Roman", Font.BOLD, 14);
  
        Dimension dimension = new Dimension(177, 24);
  
        int[] hilightDays = { 1, 3, 5, 7 };
  
        int[] disabledDays = { 4, 6, 5, 9 };
  
        datepick = new DatePicker(date, DefaultFormat, font, dimension);
  
        datepick.setLocation(137, 83);
        datepick.setBounds(120, 40, 177, 24);
        // 设置一个月份中需要高亮显示的日子
        datepick.setHightlightdays(hilightDays, Color.red);
        // 设置一个月份中不需要的日子，呈灰色显示
        datepick.setDisableddays(disabledDays);
        // 设置国家
        datepick.setLocale(Locale.CHINA);
        
        // 设置时钟面板可见
        datepick.setTimePanleVisible(false);
		return datepick;
	}
	private static DatePicker getDatePicker_end() {
		final DatePicker datepick;
		// 格式
        String DefaultFormat = "yyyy-MM-dd";
        // 当前时间
        Date date = new Date();
        // 字体
        Font font = new Font("Times New Roman", Font.BOLD, 14);
  
        Dimension dimension = new Dimension(177, 24);
  
        int[] hilightDays = { 1, 3, 5, 7 };
  
        int[] disabledDays = { 4, 6, 5, 9 };
  
        datepick = new DatePicker(date, DefaultFormat, font, dimension);
  
        datepick.setLocation(137, 83);
        datepick.setBounds(390, 40, 177, 24);
        // 设置一个月份中需要高亮显示的日子
        datepick.setHightlightdays(hilightDays, Color.red);
        // 设置一个月份中不需要的日子，呈灰色显示
        datepick.setDisableddays(disabledDays);
        // 设置国家
        datepick.setLocale(Locale.CHINA);
        
        // 设置时钟面板可见
        datepick.setTimePanleVisible(false);
		return datepick;
	}
}
