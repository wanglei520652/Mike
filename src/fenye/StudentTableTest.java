package fenye;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class StudentTableTest extends JFrame implements ActionListener {
    private JScrollPane panel;
    private JButton next, previous, add, delete;
    private JLabel label1;
    private StudentTable table;

    public StudentTableTest() {
        super("���ҳ������");
        initTableData();
        initComponent();
    }

    private void initTableData() {
        // TODO Auto-generated method stub
        Student s = new Student(1, "yangfei", "��", 21);
        Student.students.add(s);
        s = new Student(2, "yangf", "Ů", 22);
        Student.students.add(s);
        s = new Student(3, "yangfei", "��", 23);
        Student.students.add(s);
        s = new Student(4, "yangf", "Ů", 24);
        Student.students.add(s);
        s = new Student(5, "yangfei", "��", 25);
        Student.students.add(s);
        s = new Student(6, "yangf", "Ů", 26);
        Student.students.add(s);
        s = new Student(7, "yangfei", "��", 27);
        Student.students.add(s);
        s = new Student(8, "yangf", "Ů", 28);
        Student.students.add(s);
    }

    private void initComponent() {
        // TODO Auto-generated method stub
        this.setSize(500, 230);
        table = new StudentTable();
        panel = new JScrollPane(table);
        panel.setBounds(10, 10, 470, 119);
        previous = new JButton("��һҳ");
        previous.setBounds(150, 150, 75, 20);
        next = new JButton("��һҳ");
        next.setBounds(255, 150, 75, 20);
        add = new JButton("���");
        add.setBounds(350, 150, 65, 20);
        delete = new JButton("ɾ��");
        delete.setBounds(420, 150, 65, 20);
        previous.addActionListener(this);
        next.addActionListener(this);
        add.addActionListener(this);
        delete.addActionListener(this);
        label1 = new JLabel("�ܹ�" + table.totalRowCount + "��¼|��ǰ��"
                + table.currentPage + "ҳ");
        label1.setBounds(10, 150, 130, 20);
        this.getContentPane().setLayout(null);
        this.getContentPane().add(panel);
        this.getContentPane().add(previous);
        this.getContentPane().add(next);
        this.getContentPane().add(add);
        this.getContentPane().add(delete);
        this.getContentPane().add(label1);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * ��ť�¼�
     */
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JButton button = (JButton) e.getSource();
        if (button.equals(previous)) {
            int i = table.getPreviousPage();
            if (i == -1)
                return;
        }
        if (button.equals(next)) {
            int i = table.getNextPage();
            if (i == -1)
                return;
        }
        if (button.equals(delete)) {
            int i = table.getSelectedRow();
            if (i == -1)
                return;
            Integer id = (Integer) table.getValueAt(i, 0);
            if (id == null)
                return;
            Student s = null;
            for (Student stu : Student.students) {
                if (stu.getId().equals(id))
                    s = stu;
            }
            int index = Student.students.indexOf(s);
            Student.students.remove(index);
            table.initTable();
            label1.setText("�ܹ�" + table.totalRowCount + "��¼|��ǰ��"
                    + table.currentPage + "ҳ");
            return;
        }
        if (button.equals(add)) {
            Integer id = 0;
            for (Student stu : Student.students) {
                if (stu.getId() > id)
                    id = stu.getId();
            }
            Student student = new Student(id + 1, "wuming" + (id + 1), "��", 22);
            Student.students.add(student);
            table.initTable();
            label1.setText("�ܹ�" + table.totalRowCount + "��¼|��ǰ��"
                    + table.currentPage + "ҳ");
            return;
        }
        DefaultTableModel model = new DefaultTableModel(table.getPageData(),
                table.columnNames);
        table.setModel(model);
        label1.setText("�ܹ�" + table.totalRowCount + "��¼|��ǰ��"
                + table.currentPage + "ҳ");
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new StudentTableTest();
    }
}