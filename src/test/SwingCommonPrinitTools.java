package test;

import java.awt.BasicStroke;
import java.awt.Font;   
import java.awt.FontMetrics;   
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;   
import java.awt.print.PrinterException;   
import java.awt.print.PrinterJob;   
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;   
import javax.swing.table.TableModel;

import Order.ChineseNumber;   
    
/**  
 *  
 * @author xiaoquan  
 */  
public class SwingCommonPrinitTools implements Printable {   
    
    private TableModel model = null;   
    private String info; 
    private String kh;
    private String tel;
    private String orderid;
    private String total_amout;
    private int totalRow = 0;
	private String tt;
	private String sendname;
	private String Orderdate; 
	private String sendcomp; 
    private static final int LEFT = 0;   
    private static final int RIGHT = 1;   
    private static final int CENTER = 2;   
    private static final int AUTO = 3;  
    
     static final int pagesY=0;
    
    public void printTable(TableModel model,   
            String info,String kh,String orderid,String orderdate,String sendname,String total_amout,String sendcomp) {   
        this.model = model;   
        this.info = info; 
        this.kh=kh;
        this.tel=tel;
        this.orderid=orderid;
        this.Orderdate=orderdate;
        this.sendname=sendname;
        this.total_amout=total_amout;
        this.sendcomp=sendcomp;
        totalRow = model.getRowCount();   
        PrinterJob printJob = PrinterJob.getPrinterJob();  
     // 通俗理解就是书、文档
        Book book = new Book();
        PageFormat pf = new PageFormat();
        pf.setOrientation(PageFormat.PORTRAIT); 
     // 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
        Paper p = new Paper();
        p.setSize(595,263.6);//纸张大小
        p.setImageableArea(0,0, 595,263.6);//A4(595 X 842)设置打印区域，其实0，0应该是72，72，因为A4纸的默认X,Y边距是72
        pf.setPaper(p);
        // 把 PageFormat 和 Printable 添加到书中，组成一个页面
        
        //
        int pagesY = 0; 
        /**/
        int currentPageBodyCapacityRows=5;
        if (model.getRowCount() % currentPageBodyCapacityRows == 0) {   
            pagesY = (int) (model.getRowCount() /   
                    currentPageBodyCapacityRows); 
            
        } else {   
            pagesY = (int) (model.getRowCount() /   
                    currentPageBodyCapacityRows) +   
                    1;   
        } 
       
        //

        book.append(this, pf,pagesY);
        printJob.setPageable(book);
             
//        printJob.setPrintable(this); 
        
        if (printJob.printDialog()) {   
            try {   
                printJob.print();   
            } catch (Exception ex) {   
                ex.printStackTrace();   
            }   
        }   
    }   
    private static final double paper_offset_x = 20;   
    private static final double paper_offset_y = 23;   
    private static final double title_time_margin = 10;   
    private static final double time_body_margin = 2;   
    private static final double cell_padding_y = 2;   
    private static final double cell_padding_x = 2;   
    private static final double body_btm_margin = 20;   
    private static final double body_cell_height = 20;   
    private static final Font title_font = new Font("黑体", Font.PLAIN, 20);   
    private static final Font time_font = new Font("Dialog", Font.PLAIN, 10);   
    private static final Font time_font13 = new Font("Dialog", Font.PLAIN, 12);  
    private static final Font body_font = new Font("Dialog", Font.PLAIN, 10);   
   
    private final static BasicStroke stokeLine = new BasicStroke(0.4f);
	

    
 
        
    public int print(Graphics g, PageFormat pf, int pageIndex) throws  
            PrinterException {     	
    	Graphics2D g2d=(Graphics2D)g;
    	g2d.setStroke(stokeLine);
        //纸张宽   
        double pageWidth = pf.getImageableWidth();   //575
//        double pageWidth =595.32;
        //纸张高   
        double pageHeight = pf.getImageableHeight();  //272
       
//        double pageHeight =263.6;
    
        
        //打印的内容起始X   
//        double pageStartX = pf.getImageableX();   
        double pageStartX = 0;
        //打印的内容起始Y   
        double pageStartY = pf.getImageableY();
//        double pageStartY = 10;
    
        //表头高   
        double tableHeadH = 0;   
        //Cell高   
        double cellH = 0;   
    
        //计算表头高度和单元格高度   
        g.setFont(body_font);   
        FontMetrics cellFm = g.getFontMetrics();   
        cellH = cellFm.getHeight() + cell_padding_y * 2 + 1;   
        tableHeadH = cellH * 1.5;   
    
        //计算Title以及其位置   
        String title = info;   
        g.setFont(title_font);   
        FontMetrics titleFm = g.getFontMetrics();   
        int titleW = titleFm.stringWidth(title);   
    
        //表底和表头文字属性   
        g.setFont(time_font);   
        FontMetrics btmFm = g.getFontMetrics();   
        FontMetrics timeFm = g.getFontMetrics();   
    
        //表格以上的Margin   
//        double tableTopMargin = paper_offset_y + titleFm.getHeight() +   
//                title_time_margin + timeFm.getHeight() + time_body_margin; 
        double tableTopMargin =50;
    
        //表格每列的最大宽度   
        double[] cellColMaxWidths = caculateTableCellWidth(model, cellFm);   
    
        //当前Page的数据容量高度-不包括表头和表尾   
        double currentPageDataCapacityHeight = pageHeight - tableTopMargin -   
                tableHeadH - btmFm.getHeight() - body_btm_margin - 1;   
    
        //当前Page的数据容量   
//        int currentPageBodyCapacityRows = (int) (currentPageDataCapacityHeight / cellH);  
        int currentPageBodyCapacityRows = 5; 
         
        //Y方向的分页数量   
       
        int pagesY = 0; 
        /**/
        if (model.getRowCount() % currentPageBodyCapacityRows == 0) {   
            pagesY = (int) (model.getRowCount() /   
                    currentPageBodyCapacityRows); 
            
        } else {   
            pagesY = (int) (model.getRowCount() /   
                    currentPageBodyCapacityRows) +   
                    1;   
        }   
     
        //当前页数大于总页数时不打印   
        if (pageIndex + 1 > pagesY) { 
        	
            return NO_SUCH_PAGE; 
            
        }   
//         System.out.println("分页数量="+pagesY);
        //绘制Title   
        g.setFont(title_font);   
        g.drawString(title, (int) (pageStartX +   
                (pageWidth - titleW) / 2), (int) (pageStartY +   
                paper_offset_y +   
                titleFm.getAscent()));   

    
        //绘制区域移动到新的(0,0)点   
//        g.translate((int) (paper_offset_x + pageStartX), (int) (tableTopMargin +   
//                pageStartY));   
        g.translate((int) (40 ), (int) (78)); 
        int currentX = 0, currentY = 0;   
    
        //绘制第一张表   
    
        //绘制表头 
        Date currentTime = new Date();
		//时间
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(currentTime);
        
		g.setFont(time_font13); 
        
        
        String sendcomp="收货单位: " + kh;
        g.drawString(sendcomp, currentX, currentY-15); 
        
        g.setFont(time_font); 
        if(this.sendcomp==null) {
        	this.sendcomp="北京百和乐商贸有限公司";
        }
        
        String time = "送货单位: " + this.sendcomp +"          销售单号："+orderid;  
        String sendtime="送货日期："+Orderdate;
        g.drawString(time, currentX, currentY); 
        FontMetrics fm = g.getFontMetrics(); 
        g.drawString(sendtime, (int) (pageWidth -70 - fm.stringWidth(sendtime)), currentY); 
        
        
        currentY += 5;  
        
        //绘制单一表头 
        int[] hh= {70,120,50,80,50,50,70,40};
        for (int i = 0; i < model.getColumnCount(); i++) {  
        	double width = hh[i];
//            double width = 67;//cellColMaxWidths[i]; 
//            double width =cellColMaxWidths[i];
            double height = tableHeadH;   
            String name = model.getColumnName(i);   
            drawCell(g, name, currentX, currentY, (int) width,   
                    (int) height, CENTER);   
            currentX += width;   
        }   
    
        //绘制数据   
        currentX = 0;   
        currentY = (int) tableHeadH;   
        //当前Page的数据容量   
        int rightCellX = 0;   
        int yIndex = pageIndex;  
        DecimalFormat    df   = new DecimalFormat("######0.00"); 
        int startRow = currentPageBodyCapacityRows * yIndex;   
        int endRow = (currentPageBodyCapacityRows * (yIndex + 1)) >  //aa   
                totalRow   
                ? totalRow   
                : (currentPageBodyCapacityRows * (yIndex + 1));   
        for (int row = startRow; row < endRow; row++) {   
            //绘制单项表头下面的数据   
            for (int i = 0; i < model.getColumnCount(); i++) { 
            	double width = hh[i];
//                double width = 67;//cellColMaxWidths[i];  
//            	double width =cellColMaxWidths[i];
                double height = body_cell_height;   
                Object value = model.getValueAt(row, i); 
                switch(i){
                case 4 :
                	if(value!=null) {
                	 {
                		 if(!value.toString().isEmpty())
                			value=df.format(Float.valueOf(value.toString()) );
                		}               		
                	}
                    break; //可选
                case 5 :
                	if(value!=null) {
                   	 {
                   		 if(!value.toString().isEmpty())
                   			value=df.format(Float.valueOf(value.toString()) );
                   		}               		
                   	}
                    break; //可选
                case 6 :
                	if(value!=null) {
                   	 {
                   		 if(!value.toString().isEmpty())
                   			value=df.format(Float.valueOf(value.toString()) );
                   		}               		
                   	}               
                   break; //可选
            }
                drawCell(g, value, currentX, currentY, (int) width,   
                        (int) height, AUTO);   
                currentX += width;   
                rightCellX = currentX;   
            }   
            currentX = 0;   
            currentY += cellH;   
        }   
    
        //绘制闭合线，下面和右侧两条   
        g.drawLine(currentX, currentY, rightCellX, currentY);   
        g.drawLine(rightCellX, 5, rightCellX, currentY);   
    
        drawBottomInfo(pageIndex, pagesY, currentY, g, (int) pageWidth);   
        currentY += cellH;  

        drawBottomInfo2(pageIndex, pagesY, currentY, g, (int) pageWidth); 
        return PAGE_EXISTS;   
    }   
    
    private void drawBottomInfo(int pageIndex, int pagesY,   
            int currentY, Graphics g, int pageWidth) {   
        if (pageIndex + 1 == pagesY) {   
            //绘制底部信息   
            int btmX = 0;   
            int btmY = currentY + 20; 
            FontMetrics fm = g.getFontMetrics(); 
            String ssleft="合计金额："+ChineseNumber.getChineseNumber(total_amout);
            String sstotalamount=total_amout;
            DecimalFormat    df   = new DecimalFormat("######0.00"); 
            if(total_amout!=null) {
              	 {
              		 if(!total_amout.toString().isEmpty())
              			sstotalamount=df.format(Float.valueOf(total_amout.toString()) );
              		}               		
              	} 
            
            g.drawString(ssleft, btmX+10, btmY-4);
            g.drawString(sstotalamount, pageWidth -130 - fm.stringWidth(sstotalamount), btmY-4);
            
         // 横线
          g.drawLine(btmX, currentY+20, 530, currentY+20); 
          //右数线
          g.drawLine(530, currentY, 530, currentY+20); 
          //金额短线左
          g.drawLine(530-110, currentY, 530-110, currentY+20); 
          //金额短线右
          g.drawLine(530-40, currentY, 530-40, currentY+20); 
          //左数线
          g.drawLine(btmX, currentY, btmX, currentY+20);
            
 
        }   
    }   
    private void drawBottomInfo2(int pageIndex, int pagesY,   
            int currentY, Graphics g, int pageWidth) {   
        if (pageIndex + 1 == pagesY) {   
            //绘制底部信息  
        	FontMetrics fm = g.getFontMetrics(); 
            int btmX = 0;   
            int btmY = currentY + 20; 
            String str1="制单人：孙营桂"; 
            String str2="送货人："+this.sendname+"                                   收货人：                                      ";

            
            
//            g.drawString("总金额："+total_amout, btmX, btmY);
            g.drawString(str1, btmX+10, btmY);   
//            g.drawString("制表:", pageWidth / 3, btmY);   
            
            g.drawString(str2, pageWidth -60 - fm.stringWidth(str2), btmY);   
        }   
    }   
    
    /**  
     * 计算最大列宽  
     * @param cellFm  
     * @return  
     */  
    private double[] caculateTableCellWidth(   
            TableModel model,   
            FontMetrics cellFm) {   
        //表格每列的最大宽度   
        double[] cellColMaxWidths = new double[model.getColumnCount()];   
    
        //计算表头每列最大宽度   
        double[] headerColMaxWidths = new double[model.getColumnCount()];   
    
        for (int i = 0; i < model.getColumnCount(); i++) {   
            String name = model.getColumnName(i);   
            headerColMaxWidths[i] = cellFm.stringWidth(name) + cell_padding_x *   
                    2 + 1;   
        }   
        //没有数据时，表头每列的最大宽度就是表格每列的最大宽度   
        cellColMaxWidths = headerColMaxWidths;   
    
        //算数据每列的最大宽度和表头每列最大宽度对比   
        for (int j = 0; j < model.getRowCount(); j++) {   
            for (int i = 0; i < model.getColumnCount(); i++) {   
                //做些数据类型的判断   
                Object value = model.getValueAt(j, i);   
                if (value instanceof BigDecimal) {   
                    value = ((BigDecimal) value).doubleValue();   
                }   
                String text = "";   
                if (value != null) {   
                    text = value.toString();   
                }   
                double temp = cellFm.stringWidth(text) + cell_padding_x * 2 + 1;   
                if (cellColMaxWidths[i] < temp) {   
                    cellColMaxWidths[i] = temp;   
                }   
            }   
        }   
        return cellColMaxWidths;   
    }   
    
    /**  
     * 绘制单元格及里面的文字  
     * @param g  
     * @param value  
     * @param x  
     * @param y  
     * @param width  
     * @param height  
     */  
    private static void drawCell(Graphics g, Object value, int x, int y,   
            int width,   
            int height, int locate) {   
    
        g.drawLine(x, y, x + width - 1, y);   
        g.drawLine(x, y, x, y + height - 1);   
        FontMetrics fm = g.getFontMetrics();   
        if (value == null) {   
            value = "";   
        }   
        switch (locate) {   
            case 0:   
                //居左   
                g.drawString(value.toString(), (int) (x + cell_padding_x), y +   
                        (height - fm.getHeight()) / 2 + fm.getAscent());   
            case 1:   
                //居右   
                g.drawString(value.toString(),   
                        (int) (x +   
                        (width - fm.stringWidth(value.toString()) + width -   
                        fm.stringWidth(value.toString()) - cell_padding_x) /   
                        2), y +   
                        (height - fm.getHeight()) / 2 + fm.getAscent());   
            case 2:   
                //居中   
                g.drawString(value.toString(), x + (width - fm.stringWidth(   
                        value.toString())) / 2, y + (height -   
                        fm.getHeight()) / 2 + fm.getAscent());   
            case 3:   
                //自动判断   
                NumberFormat formatter = NumberFormat.getNumberInstance();   
                formatter.setMinimumFractionDigits(2);   
                formatter.setMaximumFractionDigits(2);   
                //根据数据类型左对齐还是右对齐绘制还是居中对齐   
                if (value instanceof BigDecimal) {   
                    //居右   
                    value = ((BigDecimal) value).doubleValue();   
                    value = formatter.format(value);   
                    g.drawString(value.toString(),   
                            (int) (x +   
                            (width - fm.stringWidth(value.toString()) + width -   
                            fm.stringWidth(value.toString()) - cell_padding_x) /   
                            2), y +   
                            (height - fm.getHeight()) / 2 + fm.getAscent());   
                } else if (value instanceof Integer || value instanceof Long ||   
                        value instanceof Double) {   
                    //居右   
                    g.drawString(value.toString(),   
                            (int) (x +   
                            (width - fm.stringWidth(value.toString()) + width -   
                            fm.stringWidth(value.toString()) - cell_padding_x) /   
                            2), y +   
                            (height - fm.getHeight()) / 2 + fm.getAscent());   
                } else {   
                    //居中   
                    g.drawString(value.toString(), x + (width - fm.stringWidth(   
                            value.toString())) / 2, y + (height -   
                            fm.getHeight()) / 2 + fm.getAscent());   
                }   
        }   
    }   
    
    public static void main(String[] args) {   
//        new SwingCommonPrinitTools().printTable(testData(), "测试", Orderdate, Orderdate, Orderdate, Orderdate, Orderdate, Orderdate);   
    }   
    
    private static TableModel testData() {   
        final Object rows[][] = {   
            {"one", "ichi - \u4E00", "Test1", "Test2", "Test3"},   
            {"two", "ni - \u4E8C", "Test1", "Test2", "Test3"},   
            {"three", "san - \u4E09", "Test1", "Test2", "Test3"},   
            {"four", "shi - \u56DB", "Test1", "Test2", "Test3"},   
            {"five", "go - \u4E94", "Test1", "Test2", "Test3"},   
            {"six", "roku - \u516D", "Test1", "Test2", "Test3"},   
            {"seven", "shichi - \u4E03", "Test1", "Test2", "Test3"},   
            {"eight", "hachi - \u516B", "Test1", "Test2", "Test3"},   
            {"nine", "kyu - \u4E5D", "Test1", "Test2", "Test3"},   
            {"ten", "ju - \u5341", "Test1", "Test2", "Test3"},   
            {"one", "ichi - \u4E00", "Test1", "Test2", "Test3"},   
            {"two", "ni - \u4E8C", "Test1", "Test2", "Test3"},   
            {"three", "san - \u4E09", "Test1", "Test2", "Test3"},   
            {"four", "shi - \u56DB", "Test1", "Test2", "Test3"},   
            {"five", "go - \u4E94", "Test1", "Test2", "Test3"},   
            {"six", "roku - \u516D", "Test1", "Test2", "Test3"},   
            {"seven", "shichi - \u4E03", "Test1", "Test2", "Test3"},   
            {"eight", "hachi - \u516B", "Test1", "Test2", "Test3"},   
            {"nine", "kyu - \u4E5D", "Test1", "Test2", "Test3"},   
            {"ten", "ju - \u5341", "Test1", "Test2", "Test3"},   
            {"one", "ichi - \u4E00", "Test1", "Test2", "Test3"},   
            {"two", "ni - \u4E8C", "Test1", "Test2", "Test3"},   
            {"three", "san - \u4E09", "Test1", "Test2", "Test3"},   
            {"four", "shi - \u56DB", "Test1", "Test2", "Test3"},   
            {"five", "go - \u4E94", "Test1", "Test2", "Test3"},   
            {"six", "roku - \u516D", "Test1", "Test2", "Test3"},   
            {"seven", "shichi - \u4E03", "Test1", "Test2", "Test3"},   
            {"eight", "hachi - \u516B", "Test1", "Test2", "Test3"},   
            {"nine", "kyu - \u4E5D", "Test1", "Test2", "Test3"},   
            {"ten", "ju - \u5341", "Test1", "Test2", "Test3"},   
            {"one", "ichi - \u4E00", "Test1", "Test2", "Test3"},   
            {"two", "ni - \u4E8C", "Test1", "Test2", "Test3"},   
            {"three", "san - \u4E09", "Test1", "Test2", "Test3"},   
            {"four", "shi - \u56DB", "Test1", "Test2", "Test3"},   
            {"five", "go - \u4E94", "Test1", "Test2", "Test3"},   
            {"six", "roku - \u516D", "Test1", "Test2", "Test3"},   
            {"seven", "shichi - \u4E03", "Test1", "Test2", "Test3"},   
            {"eight", "hachi - \u516B", "Test1", "Tes12121t2", "Test3"},   
            {"nine", "kyu - \u4E5D", "Test1", "Test2", "Test3"},   
            {"ten", "ju - \u5341", "Test1", "Test2", "Test3"},   
            {"one", "ichi - \u4E00", "Test1", "Test2", "Test3"},   
            {"two", "ni - \u4E8C", "Test1", "Test2", "Test3"},   
            {"three", "san - \u4E09", "Test1", "Test2", "Test3"},   
            {"four", "shi - \u56DB", 12, "Test2", "Test3"},   
            {"five", "go - \u4E94", 121212, "Test2", "Test3"},   
            {"six", "roku - \u516D", 1212121212, "Test2", "Test3"},   
            {"seven", "shichi - \u4E03", 12.01, "Test2", "Test3"},   
            {"eight", "hachi - \u516B", 135.12, "Test2", "Test3"},   
            {"nine", "kyu - \u4E5D", 93828.34, "Test2", "Test3"},   
            {"ten", "ju - \u5341", "Test1", "Test2", "Test3"},   
            {"one", "ichi - \u4E00", "Test1", "Test2", "Test3"},   
            {"two", "ni - \u4E8C", "Test1", "Test2", "Test3"},   
            {"three", "san - \u4E09", "Test1", "Test2", "Test3"},   
            {"four", "shi - \u56DB", "Test1", "Test2", "Test3"},   
            {"five", "go - \u4E94", "Test1", "Test2", "Test3"},   
            {"six", "roku - \u516D", "Test1", "Test2", "Test3"},   
            {"seven", "shichi - \u4E03", "Test1", "Test2", "Test3"},   
            {"eight", "hachi - \u516B", "Test1", "Test2", "T1212121212est3"},   
            {"nine", "kyu - \u4E5D", "Test1", "Test2", "Test3"},   
            {"ten", "ju - \u5341", "Test1", "Test2", "Test3"},};   
        final Object headers[] = {"English", "Japanese", "Column1", "Column2",   
            "Column3"};   
        JTable table = new JTable(rows, headers);   
        return table.getModel();   
    }   
}  