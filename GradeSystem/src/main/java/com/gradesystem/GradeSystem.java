/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.gradesystem;

/**
 *
 * @author nabeelnazeer
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JOptionPane;


public class GradeSystem extends JFrame {
    private HashMap<String, HashMap<String, Integer>> studentData;

    private JTextField nameField;
    private JTextField subjectField;
    private JTextField marksField;
    private JTextArea outputArea;

    public GradeSystem() {
        studentData = new HashMap<>();

        JLabel nameLabel = new JLabel("Student Name:");
        nameField = new JTextField(20);

        JLabel subjectLabel = new JLabel("Subject:");
        subjectField = new JTextField(20);

        JLabel marksLabel = new JLabel("Marks:");
        marksField = new JTextField(5);

        JButton addStudentButton = new JButton("Add Student");
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInputDialog("enter student name");
                addStudent();
                
            }
        });

        JButton addMarksButton = new JButton("Add Marks");
        addMarksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMarks();
            }
        });

        JButton displayNamesButton = new JButton("Display Names");
        displayNamesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayNames();
            }
        });

        JButton displayGradesButton = new JButton("Display Grades");
        displayGradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayGrades();
            }
        });

        JButton findAverageButton = new JButton("Find Average");
        findAverageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findAverage();
            }
        });

        JButton findClassAverageButton = new JButton("Find Class Average");
        findClassAverageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findClassAverage();
            }
        });

        JButton clearDataButton = new JButton("Clear Data");
        clearDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearData();
            }
        });

        outputArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(subjectLabel);
        panel.add(subjectField);
        panel.add(marksLabel);
        panel.add(marksField);
        panel.add(addStudentButton);
        panel.add(addMarksButton);
        panel.add(displayNamesButton);
        panel.add(displayGradesButton);
        panel.add(findAverageButton);
        panel.add(findClassAverageButton);
        panel.add(clearDataButton);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Student Grade Management System");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addStudent() {
        String name = nameField.getText();
        if (!name.isEmpty() && !studentData.containsKey(name)) {
            studentData.put(name, new HashMap<>());
            outputArea.append("Student added: " + name + "\n");
        } else {
            JOptionPane.showMessageDialog(this, "Student name cannot be empty or already exists.");
        }
    }

    private void addMarks() {
        String name = nameField.getText();
        String subject = subjectField.getText();
        String marksString = marksField.getText();

        if (!name.isEmpty() && studentData.containsKey(name) && !subject.isEmpty() && !marksString.isEmpty()) {
            try {
                int marks = Integer.parseInt(marksString);
                studentData.get(name).put(subject, marks);
                outputArea.append("Marks added for " + name + " in " + subject + ": " + marks + "\n");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid marks value. Please enter a valid number.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter valid student name, subject, and marks.");
        }
    }

    private void displayNames() {
        if (!studentData.isEmpty()) {
            outputArea.append("Student Names:\n");
            for (String name : studentData.keySet()) {
                outputArea.append(name + "\n");
            }
        } else {
            outputArea.append("No students found.\n");
        }
    }

    private void displayGrades() {
        String name = nameField.getText();
        if (!name.isEmpty() && studentData.containsKey(name)) {
            outputArea.append(name + "'s Grades:\n");
            HashMap<String, Integer> marks = studentData.get(name);
            for (String subject : marks.keySet()) {
                outputArea.append(subject + ": " + marks.get(subject) + "\n");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid student name.");
        }
    }

    private void findAverage() {
        String name = nameField.getText();
        if (!name.isEmpty() && studentData.containsKey(name)) {
            HashMap<String, Integer> marks = studentData.get(name);
            int totalMarks = 0;
            for (int mark : marks.values()) {
                totalMarks += mark;
            }
            double average = (double) totalMarks / marks.size();
            outputArea.append(name + "'s Average Grade: " + average + "\n");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid student name.");
        }
    }

    private void findClassAverage() {
        if (!studentData.isEmpty()) {
            int totalMarks = 0;
            int totalSubjects = 0;
            for (HashMap<String, Integer> marks : studentData.values()) {
                for (int mark : marks.values()) {
                    totalMarks += mark;
                    totalSubjects++;
                }
            }
            double classAverage = (double) totalMarks / totalSubjects;
            outputArea.append("Class Average Grade: " + classAverage + "\n");
        } else {
            outputArea.append("No students found.\n");
        }
    }

    private void clearData() {
        studentData.clear();
        outputArea.setText("");
        JOptionPane.showMessageDialog(this, "Data cleared successfully.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GradeSystem();
            }
        });
    }
}
