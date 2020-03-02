package test;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class JTableTest extends JFrame {

 class TableTableModel extends DefaultTableModel {
  /**
   * 
   */
  private static final long serialVersionUID = 679265889547674796L;
  public final String[] COLUMN_NAMES = new String[] {"��0", "��1", "��2", "��3"};
  
  public TableTableModel()
  {
  }

  public int getColumnCount() {
   return COLUMN_NAMES.length;
  }
  public String getColumnName(int columnIndex) {
   return COLUMN_NAMES[columnIndex];
  }
  // ��Table���ֻ����
  public boolean isCellEditable(int row,
                int column)
  {
   return false;
  }
 }

 private JTable table;
 private int i = 0;
 private int j = 0;
 private int rowI = -1;
 /**
  * Launch the application
  * @param args
  */
 public static void main(String args[]) {
  try {
   JTableTest frame = new JTableTest();
   frame.setVisible(true);
  } catch (Exception e) {
   e.printStackTrace();
  }
 }

 /**
  * Create the frame
  */
 public JTableTest() {
  super();
  setTitle("JTable Test");
  getContentPane().setLayout(null);
  setBounds(100, 100, 500, 375);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  final JScrollPane scrollPane = new JScrollPane();
  scrollPane.setBounds(10, 28, 460, 271);
  getContentPane().add(scrollPane);

  table = new JTable();
  table.setShowGrid(true);
  table.addMouseListener(new UserMouseAdapter() {
   /** *//**
      * ��굥���¼�
      * @param e �¼�Դ����
      */
   public void mouseSingleClicked(MouseEvent e){
       //System.out.println("Single Clicked!");
    rowI  = table.rowAtPoint(e.getPoint());// �õ�table���к�
    if (rowI > -1)
        System.out.println("������� "+((TableTableModel)table.getModel()).getValueAt(rowI, 0));
     }

     /** *//**
      * ���˫���¼�
      * @param e �¼�Դ����
      */
     public void mouseDoubleClicked(MouseEvent e){
       //System.out.println("Doublc Clicked!");
      rowI  = table.rowAtPoint(e.getPoint());// �õ�table���к�
     if (rowI > -1)
         System.out.println("˫����� "+((TableTableModel)table.getModel()).getValueAt(rowI, 0));
     }
     
  });
  table.setModel(new TableTableModel());
  scrollPane.setViewportView(table);

  final JButton button = new JButton();
  button.addMouseListener(new MouseAdapter() {
   public void mouseClicked(MouseEvent arg0) {
    ((TableTableModel)table.getModel()).addRow(new String[] {"��"+(i++),"��һ","��2","��3"});
   }
  });
  button.setText("���һ��");
  button.setBounds(10, 308, 99, 23);
  getContentPane().add(button);

  final JButton button_1 = new JButton();
  button_1.addMouseListener(new MouseAdapter() {
   public void mouseClicked(MouseEvent arg0) {
    ((TableTableModel)table.getModel()).insertRow((rowI>=0?rowI:0),new String[] {"������"+(j++),"������1","������2","������3"});
   }
  });
  button_1.setText("������");
  button_1.setBounds(121, 308, 99, 23);
  getContentPane().add(button_1);

 }

}

