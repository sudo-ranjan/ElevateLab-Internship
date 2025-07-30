import javax.swing.*;
import java.awt.*;

public class ToDoApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("To-Do List");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> list = new JList<>(model);
        JTextField field = new JTextField();
        JButton add = new JButton("Add");
        JButton delete = new JButton("Delete");

        JPanel top = new JPanel(new BorderLayout());
        top.add(field, BorderLayout.CENTER);
        top.add(add, BorderLayout.EAST);

        frame.add(top, BorderLayout.NORTH);
        frame.add(new JScrollPane(list), BorderLayout.CENTER);
        frame.add(delete, BorderLayout.SOUTH);

        add.addActionListener(e -> {
            String text = field.getText();
            if (!text.isEmpty()) {
                model.addElement(text);
                field.setText("");
            }
        });

        delete.addActionListener(e -> {
            int index = list.getSelectedIndex();
            if (index != -1) {
                model.remove(index);
            }
        });

        frame.setVisible(true);
    }
}