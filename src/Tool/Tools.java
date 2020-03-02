package Tool;
import java.util.Enumeration;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class Tools {

	 public static void setColumnSize(JTable table, int i, int preferedWidth, int maxWidth, int minWidth){
	   		//表格的列模型
	   		TableColumnModel cm = table.getColumnModel();
	   		//得到第i个列对象 
	   		javax.swing.table.TableColumn column = cm.getColumn(i);  
	   		column.setPreferredWidth(preferedWidth);
	   		column.setMaxWidth(maxWidth);
	   		column.setMinWidth(minWidth);
	       }
	       public void FitTableColumns(JTable myTable) {               //設置table的列寬隨內容調整

	           JTableHeader header = myTable.getTableHeader();

	           int rowCount = myTable.getRowCount();

	           Enumeration columns = myTable.getColumnModel().getColumns();

	           while (columns.hasMoreElements()) {

	               javax.swing.table.TableColumn column = (javax.swing.table.TableColumn) columns.nextElement();

	               int col = header.getColumnModel().getColumnIndex(

	                       ((javax.swing.table.TableColumn) column).getIdentifier());

	               int width = (int) myTable.getTableHeader().getDefaultRenderer()

	                       .getTableCellRendererComponent(myTable,

	                               ((javax.swing.table.TableColumn) column).getIdentifier(), false, false, -1, col)

	                       .getPreferredSize().getWidth();

	               for (int row = 0; row < rowCount; row++){

	                   int preferedWidth = (int) myTable.getCellRenderer(row, col)

	                           .getTableCellRendererComponent(myTable,

	                                   myTable.getValueAt(row, col), false, false,

	                                   row, col).getPreferredSize().getWidth();

	                   width = Math.max(width, preferedWidth);

	               }

	               header.setResizingColumn(column);

	               column.setWidth(width + myTable.getIntercellSpacing().width);

	           }

	       }
}
