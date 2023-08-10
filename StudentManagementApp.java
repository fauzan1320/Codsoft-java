import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Student {
    private String name;
    private int rollNumber;
    private String course;
    private String address;
    private int mobileNumber;

    public Student(String name, int rollNumber, String course , int mobileNumber, String address ) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.course = course;
        this.mobileNumber=mobileNumber;
        this.address=address;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }
    public String getCourse() {
        return course;
    }
     public String getAddress() {
        return address;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return " Name: " + name + "\n Roll Number: " + rollNumber + "\n Course: " + course + "\n Mobile Number: "+mobileNumber+"\n Address: "+address;
    }
}

public class StudentManagementApp {
    private List<Student> students = new ArrayList<>();
    private DefaultListModel<Student> studentListModel;
    private JList<Student> studentList;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentManagementApp app = new StudentManagementApp();
            app.showWelcomeScreen();
        });
    }
public void showWelcomeScreen() {
        JFrame welcomeFrame = new JFrame("Welcome to Our Student Management System");
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setSize(600, 400);
        welcomeFrame.setLayout(new BorderLayout());
        JPanel lab= new JPanel(new FlowLayout());
        JLabel heading= new JLabel("Student Management System", null, 10);
        heading.setFont(new Font("Arial", Font.PLAIN, 30));
        lab.add(heading);
        JLabel imageLabel = new JLabel(new ImageIcon("images.png"));
        welcomeFrame.add(imageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton registerButton = new JButton("Register Student");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomeFrame.dispose(); // Close the welcome screen
                showAddStudentForm();
            }
        });
        buttonPanel.add(registerButton);
       JButton viewAllButton = new JButton("View All Students");
        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               welcomeFrame.dispose(); // Close the welcome screen
                showAllStudents();
            }
        });
        buttonPanel.add(viewAllButton);

        JButton searchButton = new JButton("Search Student");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomeFrame.dispose(); // Close the welcome screen
                searchStudent();
            }
        });
        buttonPanel.add(searchButton);
        welcomeFrame.add(lab,BorderLayout.NORTH);
        welcomeFrame.add(buttonPanel, BorderLayout.SOUTH);

        welcomeFrame.setLocationRelativeTo(null);
        welcomeFrame.setVisible(true);
        welcomeFrame.setResizable(false);
    
    }

     
    public void showAddStudentForm() {
        JFrame frame = new JFrame("Add Student");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        //formPanel.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel courseLabel = new JLabel("Course:");
        JTextField courseField = new JTextField();
        JLabel rollNumberLabel = new JLabel("Roll Number:");
        JTextField rollNumberField = new JTextField();
        JLabel mobileNumberLabel = new JLabel("Mobile Number:");
        JTextField mobileNumberField = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField();

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(courseLabel);
        formPanel.add(courseField);
        formPanel.add(rollNumberLabel);
        formPanel.add(rollNumberField);
        formPanel.add(mobileNumberLabel);
        formPanel.add(mobileNumberField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);

        JButton addButton = new JButton("Add Student");
        JButton exitButton = new JButton("Home");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showWelcomeScreen();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String course = courseField.getText();
                int rollNumber = Integer.parseInt(rollNumberField.getText());
                int mobileNumber = Integer.parseInt(mobileNumberField.getText());
                String address = addressField.getText();
                Student newStudent = new Student( name,rollNumber,  course ,  mobileNumber,  address );
                students.add(newStudent);
                studentListModel.addElement(newStudent);
                frame.dispose();
                showWelcomeScreen();
                clearInputFields(); // Close the add student window
            }
            private void clearInputFields() {
                nameField.setText("");
                courseField.setText("");
                mobileNumberField.setText("");
                addressField.setText("");
                rollNumberField.setText("");
            }
        
        });
        formPanel.add(addButton);
        formPanel.add(exitButton);
        frame.add(formPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
     

     public void showAllStudents() {
        JFrame frame = new JFrame("View All Students");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(450, 300);
        frame.setLayout(new BorderLayout());

        studentListModel = new DefaultListModel<>();
        for (Student student : students) {
            studentListModel.addElement(student);
        }
        studentList = new JList<>(studentListModel);
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                saveStudentNamesToFile();
            }
        });
        JButton exitButton = new JButton("Home");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showWelcomeScreen();
            }
        });
        frame.add(new JScrollPane(studentList), BorderLayout.CENTER);
        frame.add(saveButton, BorderLayout.NORTH);
        frame.add(exitButton,BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    public void saveStudentNamesToFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()))) {
                for (int i = 0; i < studentListModel.size(); i++) {
                    Student student = studentListModel.get(i);
                    writer.write(student.getName());
                    writer.newLine();
                }
                writer.flush();
                JOptionPane.showMessageDialog(null, "Student names saved successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving student names: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public void searchStudent() {
        JFrame frame = new JFrame("Search Student by Roll Number");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel(new FlowLayout());

        JLabel rollNumberLabel = new JLabel("Enter Roll Number:");
        JTextField rollNumberField = new JTextField(10);
        JButton searchButton = new JButton("Search");

        JTextArea resultTextArea = new JTextArea(10, 30);
        resultTextArea.setEditable(false);
        JButton exitButton = new JButton("Home");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showWelcomeScreen();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rollNumber = Integer.parseInt(rollNumberField.getText());
                Student foundStudent = null;
                for (Student student : students) {
                    if (student.getRollNumber() == rollNumber) {
                        foundStudent = student;
                        break;
                    }
                }
                if (foundStudent != null) {
                    resultTextArea.setText(foundStudent.toString());
                } else {
                    resultTextArea.setText("Student not found.");
                }
            }
        });

        searchPanel.add(rollNumberLabel);
        searchPanel.add(rollNumberField);
        searchPanel.add(searchButton);
        frame.add(exitButton,BorderLayout.SOUTH);
        frame.add(searchPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(resultTextArea), BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

}