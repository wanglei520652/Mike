package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jdesktop.swingx.JXDatePicker;

public class TestGUI {
	public static void main(String[] args) {

		JFrame f = new JFrame("LoL");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.setLayout(null);

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 ParsePosition pos = new ParsePosition(10);
		Date currentTime_2 = sdf.parse(sdf.format(date), pos);

		final JXDatePicker datepick = new JXDatePicker();

		// ���� date����
		datepick.setDate(date);

		datepick.setBounds(137, 83, 177, 24);

		f.add(datepick);

		JButton b = new JButton("��ȡʱ��");
		b.setBounds(137, 183, 100, 30);
		f.add(b);

		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ȡ ����
				Date d = datepick.getDate();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(d);
				JOptionPane.showMessageDialog(f, "��ȡ�ؼ��е����� :" + dateString);

			}
		});

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		f.setVisible(true);
	}
}
