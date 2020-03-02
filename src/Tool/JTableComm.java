package Tool;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class JTableComm {
   
    public static void HiddenCell(JTable table, int column) {
        TableColumn tc = table.getTableHeader().getColumnModel().getColumn(column);
        tc.setMaxWidth(0);
        tc.setPreferredWidth(0);
        tc.setWidth(0);
        tc.setMinWidth(0);
        table.getTableHeader().getColumnModel().getColumn(column).setMaxWidth(0);
        table.getTableHeader().getColumnModel().getColumn(column).setMinWidth(0);
    }
   
    public static void showColumn(JTable table, int column, int width) {
        TableColumn tc = table.getColumnModel().getColumn(column);
        tc.setMaxWidth(width+100);
        tc.setPreferredWidth(width);
        tc.setWidth(width);
        tc.setMinWidth(width-100);
        table.getTableHeader().getColumnModel().getColumn(column).setMaxWidth(width+100);
        table.getTableHeader().getColumnModel().getColumn(column).setMinWidth(width-100);
    }
}
