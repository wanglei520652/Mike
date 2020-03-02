package fenye;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class StudentTable extends JTable {
    // JTable���ҳ��Ϣ��ر���
    public int currentPage = 1;
    public int pageCount = 5;
    public int totalPage = 0;
    public int totalRowCount = 0;
    public int column = 0;
    public int restCount;
    public Object[][] resultData;
    // JTable����Ϣ��ر���
    public List<Student> students = Student.students;
    public String[] columnNames = { "ID", "Name", "Sex", "Age" };
    public DefaultTableModel model = null;

    public StudentTable() {
        initTable();
    }

    /**
     * ��ȡ��һҳ
     */
    public int getNextPage() {
        if (this.currentPage != this.totalPage) {
            return ++currentPage;
        }
        return -1;
    }

    /**
     * ��ȡ��һҳ
     */
    public int getPreviousPage() {
        if (this.currentPage != 1) {
            return --currentPage;
        }
        return -1;
    }

    /**
     * ��ȡ���һҳ
     */
    public int getLastPage() {
        return this.totalPage;
    }

    /**
     * ��ȡ��һҳ
     */
    public int getFirstPage() {
        return 1;
    }

    /**
     * ��ȡ��ҳ��
     */
    public int getTotolPage() {
        return this.totalPage;
    }

    /**
     * ��ȡ��ǰҳ
     */
    public int getCurrentPage() {
        return this.currentPage;
    }

    /**
     * ���ԭʼ���ݼ�
     * 
     * @param students
     * @return
     */
    public Object[][] getData(List<Student> students) {
        if (students.size() > 0) {
            Object[][] data = new Object[students.size()][4];
            for (int i = 0; i < students.size(); i++) {
                Student s = students.get(i);
                Object[] a = { s.getId(), s.getName(), s.getSex(), s.getAge() };// ��List**�����ݸ���Object����
                data[i] = a;// �������ֵ������ά�����һ��
            }
            return data;
        }
        return null;
    }

    public void initResultData(Object[][] data) {
        if (data != null) {
            resultData = data;// �ܵĽ����
            column = data[0].length;// �������
            totalRowCount = data.length;// ��ĳ���
            totalPage = totalRowCount % pageCount == 0 ? totalRowCount
                    / pageCount : totalRowCount / pageCount + 1;// ���������ҳ��
            restCount = totalRowCount % pageCount == 0 ? 5 : totalRowCount
                    % pageCount;// ���һҳ��������
        }
    }

    /**
     * ��ȡ��ҳ����
     * 
     * @return
     */
    public Object[][] getPageData() {
        Object[][] currentPageData = new Object[pageCount][column];// ����ÿҳ���ݼ�
        if (this.getCurrentPage() < this.totalPage) {// �����ǰҳ��С����ҳ������ôÿҳ��ĿӦ���ǹ涨����pageCount
            for (int i = pageCount * (this.getCurrentPage() - 1); i < pageCount
                    * (this.getCurrentPage() - 1) + pageCount; i++) {
                for (int j = 0; j < column; j++) {
                    // �ѽ�����ж�Ӧÿҳ��ÿһ������ȫ����ֵ����ǰҳ��ÿһ�е�ÿһ��
                    currentPageData[i % pageCount][j] = resultData[i][j];
                }
            }
        } else {
            // �ڶ�̬�ı����ݽ������ʱ�������ǰҳû�������ˣ���ص�ǰһҳ��һ��������һҳ���ԣ�
            if (pageCount * (this.getCurrentPage() - 1) >= totalRowCount)
                this.currentPage--;
            for (int i = pageCount * (this.getCurrentPage() - 1); i < pageCount
                    * (this.getCurrentPage() - 1) + restCount; i++) {
                for (int j = 0; j < column; j++) {
                    currentPageData[i % pageCount][j] = resultData[i][j];
                }
            }
        }
        return currentPageData;
    }

    /**
     * ��ʼ���������
     */
    public void initTable() {
        Object[][] data = getData(students);
        if (data != null) {
            initResultData(data);
            model = new DefaultTableModel(getPageData(), columnNames);
        } else {
            // ����������û�����ݣ���ô���ÿ����������ݼ��е�ÿһ��
            Object[][] nothing = { {}, {}, {}, {}, {} };
            model = new DefaultTableModel(nothing, columnNames);
            totalRowCount = 0;
        }
        this.setModel(model);
        this.setRowHeight(20);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        setDefaultRenderer(Object.class, r);
    }
}