package fenye;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class PageTable extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1698867170940729036L;
    //����
    private JPanel contentPane;
    //���
    private JTable table;
    //��ť��ҳ
    private JButton firstPageButton;
    //ǰһҳ
    private JButton latePageButton;
    //��һҳ
    private JButton nextPageButton;
    //ĩҳ
    private JButton lastPageButton;
    //
    private int maxPageNumber;
    private int currentPageNumber = 1;
    private double pageSize = 5;
    private DefaultTableModel defaultModel;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PageTable frame = new PageTable();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public PageTable() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        //���� 
        setTitle("���ķ�ҳ");
        //�ر�
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //��С��λ��
        setBounds(100, 100, 450, 300);
        //����
        contentPane = new JPanel();
        //�����߿�
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        //��������
        contentPane.setLayout(new BorderLayout(0, 0));
        //�������
        setContentPane(contentPane);
        //���
        JPanel panel = new JPanel();
        //������
        contentPane.add(panel, BorderLayout.SOUTH);
        //��ҳ
        firstPageButton = new JButton("��ҳ");
        //Ϊ��ҳ����¼�
        firstPageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_firstPageButton_actionPerformed(e);
            }
        });
        //��ҳ����
        firstPageButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        //�����ҳ
        panel.add(firstPageButton);
        //ǰһҳ
        latePageButton = new JButton("ǰһҳ");
        //Ϊǰһҳ����¼�
        latePageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_latePageButton_actionPerformed(e);
            }
        });
        //ǰһҳ��������
        latePageButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        //���ǰһҳ
        panel.add(latePageButton);
        //��һҳ
        nextPageButton = new JButton("��һҳ");
        //Ϊ��һҳ����¼�
        nextPageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_nextPageButton_actionPerformed(e);
            }
        });
        //Ϊ��һҳ��������
        nextPageButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        //�����һҳ
        panel.add(nextPageButton);
        //ĩҳ
        lastPageButton = new JButton("ĩҳ");
        //ĩҳ�¼�
        lastPageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_lastPageButton_actionPerformed(e);
            }
        });
        //ĩҳ��������
        lastPageButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        //���ĩҳ
        panel.add(lastPageButton);
        //������
        JScrollPane scrollPane = new JScrollPane();
        //��ӹ�����
        contentPane.add(scrollPane, BorderLayout.CENTER);
        //���
        table = new JTable();
        //�������
        table.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        //����и�
        table.setRowHeight(35);
        //��ͷ
        JTableHeader header = table.getTableHeader();
        //��ͷ��������
        header.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        //���ñ�ͷ�߶�
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        //�����ӹ�����
        scrollPane.setViewportView(table);
    }
    //�������ʱ
    protected void do_this_windowActivated(WindowEvent e) {
    	//���ģ��
        defaultModel = (DefaultTableModel) table.getModel();
        //
        defaultModel.setRowCount(0);
        defaultModel.setColumnIdentifiers(new Object[] { "���", "ƽ����" });
        for (int i = 0; i < 23; i++) {
            defaultModel.addRow(new Object[] { i, i * i });
        }
        maxPageNumber = (int) Math.ceil(defaultModel.getRowCount() / pageSize);
        table.setModel(defaultModel);
        firstPageButton.setEnabled(false);
        latePageButton.setEnabled(false);
        nextPageButton.setEnabled(true);
        lastPageButton.setEnabled(true);
    }
    
    @SuppressWarnings("unchecked")
    //��ҳ�¼�
    protected void do_firstPageButton_actionPerformed(ActionEvent e) {
    	//��ʼҳ
        currentPageNumber = 1;
        //�õ�����
        Vector dataVector = defaultModel.getDataVector();
        //ģ��
        DefaultTableModel newModel = new DefaultTableModel();
        
        newModel.setColumnIdentifiers(new Object[] { "���", "�����" });
        for (int i = 0; i < pageSize; i++) {
            newModel.addRow((Vector) dataVector.elementAt(i));
        }
        table.setModel(newModel);
        firstPageButton.setEnabled(false);
        latePageButton.setEnabled(false);
        nextPageButton.setEnabled(true);
        lastPageButton.setEnabled(true);
    }
    
    @SuppressWarnings("unchecked")
    //��һҳ�¼�
    protected void do_latePageButton_actionPerformed(ActionEvent e) {
        currentPageNumber--;
        Vector dataVector = defaultModel.getDataVector();
        DefaultTableModel newModel = new DefaultTableModel();
        newModel.setColumnIdentifiers(new Object[] { "���", "�����" });
        for (int i = 0; i < pageSize; i++) {
            newModel.addRow((Vector) dataVector.elementAt((int) (pageSize * (currentPageNumber - 1) + i)));
        }
        table.setModel(newModel);
        if (currentPageNumber == 1) {
            firstPageButton.setEnabled(false);
            latePageButton.setEnabled(false);
        }
        nextPageButton.setEnabled(true);
        lastPageButton.setEnabled(true);
    }
    
    @SuppressWarnings("unchecked")
    //ǰһҳ
    protected void do_nextPageButton_actionPerformed(ActionEvent e) {
        currentPageNumber++;
        Vector dataVector = defaultModel.getDataVector();
        DefaultTableModel newModel = new DefaultTableModel();
        newModel.setColumnIdentifiers(new Object[] { "���", "ƽ����" });
        if (currentPageNumber == maxPageNumber) {
            int lastPageSize = (int) (defaultModel.getRowCount() - pageSize * (maxPageNumber - 1));
            for (int i = 0; i < lastPageSize; i++) {
                newModel.addRow((Vector) dataVector.elementAt((int) (pageSize * (maxPageNumber - 1) + i)));
            }
            nextPageButton.setEnabled(false);
            lastPageButton.setEnabled(false);
        } else {
            for (int i = 0; i < pageSize; i++) {
                newModel.addRow((Vector) dataVector.elementAt((int) (pageSize * (currentPageNumber - 1) + i)));
            }
        }
        table.setModel(newModel);
        firstPageButton.setEnabled(true);
        latePageButton.setEnabled(true);
    }
    
    @SuppressWarnings("unchecked")
    //��һҳ
    protected void do_lastPageButton_actionPerformed(ActionEvent e) {
        currentPageNumber = maxPageNumber;
        Vector dataVector = defaultModel.getDataVector();
        DefaultTableModel newModel = new DefaultTableModel();
        newModel.setColumnIdentifiers(new Object[] { "���", "ƽ����" });
        int lastPageSize = (int) (defaultModel.getRowCount() - pageSize * (maxPageNumber - 1));
        
        if (lastPageSize == 5) {
            for (int i = 0; i < pageSize; i++) {
                newModel.addRow((Vector) dataVector.elementAt((int) (pageSize * (maxPageNumber - 1) + i)));
            }
        } else {
            for (int i = 0; i < lastPageSize; i++) {
                newModel.addRow((Vector) dataVector.elementAt((int) (pageSize * (maxPageNumber - 1) + i)));
            }
        }
        
        table.setModel(newModel);
        firstPageButton.setEnabled(true);
        latePageButton.setEnabled(true);
        nextPageButton.setEnabled(false);
        lastPageButton.setEnabled(false);
        
    }
}
