package test;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
public class MainFrame extends JFrame {
private String content = "row\r\nrow\r\nrow\r\nrow\r\nrow\r\nrow\r\nrow\r\nrow\r\nrow\r\nrow\r\nrow\r\nrow\r\nrow\r\nrow\r\nrow\r\nrow\r\nrow\r\nrow\r\nrow\r\nrow\r\nrow\r\nrow\r\nrow\r\nrow\r\nrow\r\n";
public MainFrame() {
setSize(400, 300);
setDefaultCloseOperation(EXIT_ON_CLOSE);
JScrollPane scrollPane_2 = new JScrollPane();
getContentPane().add(scrollPane_2, BorderLayout.CENTER);

JTextPane textPane = new JTextPane();
textPane.setText(content);
//this.getContentPane().add(textPane);

scrollPane_2.setViewportView(textPane);
setVisible(true);
}
public static void main(String[] args) {
new MainFrame();
}
}