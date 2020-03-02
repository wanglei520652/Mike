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
    //容器
    private JPanel contentPane;
    //表格
    private JTable table;
    //按钮首页
    private JButton firstPageButton;
    //前一页
    private JButton latePageButton;
    //下一页
    private JButton nextPageButton;
    //末页
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
        //标题 
        setTitle("表格的分页");
        //关闭
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //大小及位置
        setBounds(100, 100, 450, 300);
        //容器
        contentPane = new JPanel();
        //容器边框
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        //容器布局
        contentPane.setLayout(new BorderLayout(0, 0));
        //添加容器
        setContentPane(contentPane);
        //面板
        JPanel panel = new JPanel();
        //添加面板
        contentPane.add(panel, BorderLayout.SOUTH);
        //首页
        firstPageButton = new JButton("首页");
        //为首页添加事件
        firstPageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_firstPageButton_actionPerformed(e);
            }
        });
        //首页字体
        firstPageButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        //添加首页
        panel.add(firstPageButton);
        //前一页
        latePageButton = new JButton("前一页");
        //为前一页添加事件
        latePageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_latePageButton_actionPerformed(e);
            }
        });
        //前一页设置字体
        latePageButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        //添加前一页
        panel.add(latePageButton);
        //下一页
        nextPageButton = new JButton("下一页");
        //为下一页添加事件
        nextPageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_nextPageButton_actionPerformed(e);
            }
        });
        //为下一页设置字体
        nextPageButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        //添加下一页
        panel.add(nextPageButton);
        //末页
        lastPageButton = new JButton("末页");
        //末页事件
        lastPageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_lastPageButton_actionPerformed(e);
            }
        });
        //末页设置字体
        lastPageButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        //添加末页
        panel.add(lastPageButton);
        //滚动条
        JScrollPane scrollPane = new JScrollPane();
        //添加滚动条
        contentPane.add(scrollPane, BorderLayout.CENTER);
        //表格
        table = new JTable();
        //表格字体
        table.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        //表格行高
        table.setRowHeight(35);
        //表头
        JTableHeader header = table.getTableHeader();
        //表头设置字体
        header.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        //设置表头高度
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        //表格添加滚动条
        scrollPane.setViewportView(table);
    }
    //窗体加载时
    protected void do_this_windowActivated(WindowEvent e) {
    	//表格模型
        defaultModel = (DefaultTableModel) table.getModel();
        //
        defaultModel.setRowCount(0);
        defaultModel.setColumnIdentifiers(new Object[] { "序号", "平方数" });
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
    //首页事件
    protected void do_firstPageButton_actionPerformed(ActionEvent e) {
    	//初始页
        currentPageNumber = 1;
        //得到集合
        Vector dataVector = defaultModel.getDataVector();
        //模型
        DefaultTableModel newModel = new DefaultTableModel();
        
        newModel.setColumnIdentifiers(new Object[] { "序号", "随机数" });
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
    //下一页事件
    protected void do_latePageButton_actionPerformed(ActionEvent e) {
        currentPageNumber--;
        Vector dataVector = defaultModel.getDataVector();
        DefaultTableModel newModel = new DefaultTableModel();
        newModel.setColumnIdentifiers(new Object[] { "序号", "随机数" });
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
    //前一页
    protected void do_nextPageButton_actionPerformed(ActionEvent e) {
        currentPageNumber++;
        Vector dataVector = defaultModel.getDataVector();
        DefaultTableModel newModel = new DefaultTableModel();
        newModel.setColumnIdentifiers(new Object[] { "序号", "平方数" });
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
    //下一页
    protected void do_lastPageButton_actionPerformed(ActionEvent e) {
        currentPageNumber = maxPageNumber;
        Vector dataVector = defaultModel.getDataVector();
        DefaultTableModel newModel = new DefaultTableModel();
        newModel.setColumnIdentifiers(new Object[] { "序号", "平方数" });
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
