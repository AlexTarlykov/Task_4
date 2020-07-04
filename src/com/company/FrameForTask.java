package com.company;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FrameForTask {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Task_4");
        frame.setSize(400, 420);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(table());
        panel.add(buttonForSort(panel));
        panel.add(buttonForSaveList(panel));
        panel.add(textFieldForNameFile(panel));
        panel.add(buttonForOpenList(panel));
        panel.add(buttonForAddColumn(panel));
        panel.add(buttonForRemoveColumn(panel));
        frame.add(panel);
        frame.setVisible(true);
    }

    static JScrollPane table() {
        JTable table = new JTable(1, 3);
        table.setTableHeader(null);
        table.setCellSelectionEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setBorder(new EtchedBorder());
        table.setRowHeight(30);
        JScrollPane pane = new JScrollPane(table);
        pane.setBorder(new EtchedBorder());
        pane.setSize(200, 130);
        pane.setLocation(160, 10);
        return pane;
    }

    static JTextField textFieldForNameFile(JPanel panel) {
        JLabel label = new JLabel("Input name file:");
        label.setLocation(25, 310);
        label.setSize(90, 30);
        panel.add(label);
        JTextField textField = new JTextField();
        textField.setLocation(10, 340);
        textField.setSize(110, 30);
        return textField;
    }

    static JButton buttonForSort(JPanel panel) {
        JButton button = new JButton("Sort");
        button.setLocation(10, 10);
        button.setSize(110, 50);
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JScrollPane pane = (JScrollPane) panel.getComponentAt(160, 10);
                JTable table = (JTable) pane.getViewport().getView();
                String[] words = new String[table.getColumnCount()];
                for (int i = 0; i < table.getColumnCount(); i++) {
                    words[i] = (table.getValueAt(0, i).toString());
                }
                Logic.sort(words);
                for (int i = 0; i < words.length; i++) {
                    table.setValueAt(words[i], 0, i);
                }
            }
        });
        return button;
    }

    static JButton buttonForSaveList(JPanel panel) {
        JButton button = new JButton("Save");
        button.setLocation(10, 180);
        button.setSize(110, 50);
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JTextField textField = (JTextField) panel.getComponentAt(10, 340);
                    FileWriter writer = new FileWriter("files\\" + textField.getText() + ".txt");
                    JScrollPane pane = (JScrollPane) panel.getComponentAt(160, 10);
                    JTable table = (JTable) pane.getViewport().getView();
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < table.getColumnCount(); i++) {
                        builder.append(table.getValueAt(0, i)).append(" ");
                    }
                    writer.write(String.valueOf(builder));
                    textField.setText("");
                    writer.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        return button;
    }

    static JButton buttonForOpenList(JPanel panel) {
        JButton button = new JButton("Open");
        button.setLocation(10, 240);
        button.setSize(110, 50);
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JTextField textField = (JTextField) panel.getComponentAt(10, 340);
                    File file = new File("files\\" + textField.getText() + ".txt");
                    JScrollPane pane = (JScrollPane) panel.getComponentAt(160, 10);
                    JTable table = (JTable) pane.getViewport().getView();
                    Scanner read = new Scanner(file);
                    textField.setText("");
                    String[] strings = read.nextLine().split(" ");
                    DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                    dtm.setColumnCount(strings.length);
                    for (int i = 0; i < strings.length; i++) {
                        table.setValueAt(strings[i], 0, i);
                    }
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(null, "File not found");
                }
            }
        });
        return button;
    }

    static JButton buttonForAddColumn(JPanel panel) {
        JButton button = new JButton("Add");
        button.setLocation(205, 180);
        button.setSize(110, 50);
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JScrollPane pane = (JScrollPane) panel.getComponentAt(160, 10);
                JTable table = (JTable) pane.getViewport().getView();
                DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                dtm.setColumnCount(dtm.getColumnCount() + 1);
            }
        });
        return button;
    }

    static JButton buttonForRemoveColumn(JPanel panel) {
        JButton button = new JButton("Remove");
        button.setLocation(205, 240);
        button.setSize(110, 50);
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JScrollPane pane = (JScrollPane) panel.getComponentAt(160, 10);
                JTable table = (JTable) pane.getViewport().getView();
                DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                if (dtm.getColumnCount() > 0) {
                    dtm.setColumnCount(dtm.getColumnCount() - 1);
                }
            }
        });
        return button;
    }
}
