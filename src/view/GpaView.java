
package view;

import model.Course;
import model.GpaModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GpaView extends JFrame {

    private final JTextField nameField = new JTextField(15);
    private final JTextField hoursField = new JTextField(5);

    private final JComboBox<String> gradeBox = new JComboBox<>(
        new String[]{"A+", "A", "B+", "B", "C+", "C", "D+", "D", "F"}
    );

    private final JComboBox<String> scaleBox = new JComboBox<>(
        new String[]{"5", "4"}
    );

    private final JButton addButton = new JButton("Add");
    private final JButton editButton = new JButton("Edit");
    private final JButton deleteButton = new JButton("Delete");

    private final DefaultTableModel tableModel = new DefaultTableModel(
        new String[]{"Course", "Credit Hours", "Grade"}, 0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private final JTable courseTable = new JTable(tableModel);
    private final JLabel gpaLabel = new JLabel("GPA: 0.00");
    private final JLabel hoursLabel = new JLabel("Total Credit Hours: 0");

    public GpaView() {
        setTitle("GPA Calculator");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        inputPanel.add(new JLabel("Course Name:"));
        inputPanel.add(new JLabel("Credit Hours:"));
        inputPanel.add(new JLabel("Grade:"));
        inputPanel.add(new JLabel("GPA Scale:"));

        inputPanel.add(nameField);
        inputPanel.add(hoursField);
        inputPanel.add(gradeBox);
        inputPanel.add(scaleBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel resultPanel = new JPanel(new GridLayout(1, 2));
        resultPanel.add(gpaLabel);
        resultPanel.add(hoursLabel);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(courseTable), BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);
    }

    public String getCourseName() {
        return nameField.getText();
    }

    public String getCreditHours() {
        return hoursField.getText();
    }

    public String getSelectedGrade() {
        return (String) gradeBox.getSelectedItem();
    }

    public int getSelectedScale() {
        return Integer.parseInt((String) scaleBox.getSelectedItem());
    }

    public int getSelectedRow() {
        return courseTable.getSelectedRow();
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JComboBox<String> getScaleBox() {
        return scaleBox;
    }

    public void refresh(GpaModel model) {
        tableModel.setRowCount(0);

        for (Course course : model.getCourses()) {
            tableModel.addRow(new Object[]{
                course.getName(),
                course.getCreditHours(),
                course.getGrade()
            });
        }

        gpaLabel.setText(String.format("GPA: %.2f / %d",
            model.calculateGpa(), model.getScale()));

        hoursLabel.setText(
            "Total Credit Hours: " + model.getTotalCreditHours()
        );
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(
            this, message, "Error", JOptionPane.ERROR_MESSAGE
        );
    }

    public void clearFields() {
        nameField.setText("");
        hoursField.setText("");
        gradeBox.setSelectedIndex(0);
    }

    public void fillFieldsFromSelectedRow() {
        int row = courseTable.getSelectedRow();

        if (row >= 0) {
            nameField.setText(tableModel.getValueAt(row, 0).toString());
            hoursField.setText(tableModel.getValueAt(row, 1).toString());
            gradeBox.setSelectedItem(tableModel.getValueAt(row, 2));
        }
    }

    public void onCourseSelected(Runnable action) {
        courseTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                action.run();
            }
        });
    }
}
