import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentPanel extends JPanel {

    private String backgroundImageFile = "C:\\Users\\user\\Desktop\\School\\Java2\\ManagementSystem\\ManagementSystem\\Images\\Background.jpg";

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        //Load the background image
        ImageIcon backgroundIcon = new ImageIcon(backgroundImageFile);
        Image backgroundImage = backgroundIcon.getImage();

        //Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // Load the logo image
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\user\\Desktop\\School\\Java2\\ManagementSystem\\ManagementSystem\\Images\\LOGO2.png");
        Image logoImage = logoIcon.getImage();

        // Draw the logo image on the left side
        int logoWidth = 500;
        int logoHeight = 500;

        g.drawImage(logoImage, 0, 150, logoWidth, logoHeight, this);
    }

    private DatabaseManager database;
    public StudentPanel(CardLayout cardLayout, JPanel jPanel, DatabaseManager database) {
        this.database = database;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 10, 5, 10);

        // Create buttons for add, update, delete, and view function
        JButton exportButton = new JButton("Export Student Information File");
        exportButton.setPreferredSize(new Dimension(350, 65));
        exportButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        exportButton.setForeground(new Color(255, 255, 255, 255));
        exportButton.setRolloverEnabled(true);// Enable rollover effect
        exportButton.setBackground(new Color(88, 88, 255, 186));// Set a semi-transparent background
        exportButton.addActionListener(new ActionListener() {
            @Override
            // Method to handle exporting student information
            public void actionPerformed(ActionEvent e) {
                exportStudentInform();
            }
        });
        JButton addButton = new JButton("Add Student Information");
        addButton.setPreferredSize(new Dimension(350, 65));
        addButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        addButton.setForeground(new Color(255, 255, 255, 255));
        addButton.setRolloverEnabled(true);// Enable rollover effect
        addButton.setBackground(new Color(88, 88, 255, 186));// Set a semi-transparent background
        addButton.addActionListener(new ActionListener() {
            @Override
            // Method to handle adding student information
            public void actionPerformed(ActionEvent e) {
                addStudentInform();
            }
        });
        JButton updateButton = new JButton("Update Student Information");
        updateButton.setPreferredSize(new Dimension(350, 65));
        updateButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        updateButton.setForeground(new Color(255, 255, 255, 255));
        updateButton.setRolloverEnabled(true);// Enable rollover effect
        updateButton.setBackground(new Color(88, 88, 255, 186));// Set a semi-transparent background
        updateButton.addActionListener(new ActionListener() {
            @Override
            // Method to handle updating student information
            public void actionPerformed(ActionEvent e) {
                updateStudentInform();
            }
        });
        JButton deleteButton = new JButton("Delete Student Information");
        deleteButton.setPreferredSize(new Dimension(350, 65));
        deleteButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        deleteButton.setForeground(new Color(255, 255, 255, 255));
        deleteButton.setRolloverEnabled(true);// Enable rollover effect
        deleteButton.setBackground(new Color(88, 88, 255, 186));// Set a semi-transparent background
        deleteButton.addActionListener(new ActionListener() {
            @Override
            // Method to handle deleting student information
            public void actionPerformed(ActionEvent e) {deleteStudentInform();}
        });
        JButton viewButton = new JButton("View Student Information");
        viewButton.setPreferredSize(new Dimension(350, 65));
        viewButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        viewButton.setForeground(new Color(255, 255, 255, 255));
        viewButton.setRolloverEnabled(true);// Enable rollover effect
        viewButton.setBackground(new Color(88, 88, 255, 186));// Set a semi-transparent background
        viewButton.addActionListener(new ActionListener() {
            @Override
            // Method to handle viewing student information
            public void actionPerformed(ActionEvent e) {viewStudentInform();}
        });
        JButton backButton = new JButton("Back Previous Page");
        backButton.setPreferredSize(new Dimension(350, 65));
        backButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        backButton.setForeground(new Color(255, 255, 255, 255));
        backButton.setRolloverEnabled(true);// Enable rollover effect
        backButton.setBackground(new Color(88, 88, 255, 186));// Set a semi-transparent background
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(jPanel,"Home");
            }
        });

        gbc.gridy = 0;
        add(backButton, gbc);
        gbc.gridy++;
        add(addButton, gbc);
        gbc.gridy++;
        add(updateButton, gbc);
        gbc.gridy++;
        add(deleteButton, gbc);
        gbc.gridy++;
        add(viewButton, gbc);
        gbc.gridy++;
        add(exportButton, gbc);
    }
    public void exportStudentInform(){
        try {
            File file = new File("student_information.txt");
            file.delete();
            FileWriter csvWriter = new FileWriter("student_information.txt");
            Statement statement = database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT studentid,studentname,studentage,studentic,phone," +
                    "birthdata,address,postcode,deptid,programme,intakesem FROM students");

            int i = 1;
            String message = "";
            // Iterate through result set to build the message
            while (resultSet.next()) {
                message +=
                        "Student " + i +
                        "\nStudent ID: " + resultSet.getString("studentid") +
                        "\nStudent Name: " + resultSet.getString("studentname") +
                        "\nAge: " + resultSet.getString("studentage") +
                        "\nI.C.: " + resultSet.getString("studentic") +
                        "\nPhone: " + resultSet.getString("phone") +
                        "\nBirth Date: " + resultSet.getString("birthdata") +
                        "\nAddress: " + resultSet.getString("address") +
                        "\nPostcode: " + resultSet.getString("postcode") +
                        "\nDepartment: " + resultSet.getString("deptid") +
                        "\nProgramme: " + resultSet.getString("programme") +
                        "\nSem Intake: " + resultSet.getString("intakesem") +"\n\n";
                i++;
            }
            csvWriter.write(message);
            csvWriter.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addStudentInform(){
        // Create a panel to hold input fields
        JPanel panel = new JPanel(new GridLayout(12,2,10,10));

        // Create input fields for student information
        JTextField studentID = new JTextField();
        JTextField studentName = new JTextField();
        JTextField studentAge = new JTextField();
        JTextField studentIC = new JTextField();
        JTextField phoneNumber = new JTextField();
        JTextField birthDate = new JTextField();
        JTextField address = new JTextField();
        JTextField postcode = new JTextField();
        JTextField department = new JTextField();
        JTextField programme = new JTextField();
        JTextField intakeSem = new JTextField();

        // Add labels and input fields to the panel
        panel.add(new JLabel("Student ID:"));
        panel.add(studentID);
        panel.add(new JLabel("Student Name:"));
        panel.add(studentName);
        panel.add(new JLabel("Age:"));
        panel.add(studentAge);
        panel.add(new JLabel("IC Number:"));
        panel.add(studentIC);
        panel.add(new JLabel("Phone Number:"));
        panel.add(phoneNumber);
        panel.add(new JLabel("Birth Date: (example:2000-05-02)"));
        panel.add(birthDate);
        panel.add(new JLabel("Address:"));
        panel.add(address);
        panel.add(new JLabel("Postcode:"));
        panel.add(postcode);
        panel.add(new JLabel("Department:"));
        panel.add(department);
        panel.add(new JLabel("Programme:"));
        panel.add(programme);
        panel.add(new JLabel("Intake Semester:"));
        panel.add(intakeSem);

        // Display the input dialog to the user
        JOptionPane.showMessageDialog(this,panel,"Add Information",JOptionPane.PLAIN_MESSAGE);

        // Retrieve user inputs from the input fields
        String id = studentID.getText();
        String name = studentName.getText();
        int age = Integer.parseInt(studentAge.getText());
        String ic = studentIC.getText();
        String phone = phoneNumber.getText();
        String birDate = birthDate.getText();
        String stuAddress = address.getText();
        int postCode = Integer.parseInt(postcode.getText());
        String departments = department.getText();
        String programmes = programme.getText();
        String intake = intakeSem.getText();

        try{
            // Create a prepared statement to insert data into the database
            PreparedStatement preparedStatement = database.getConnection().prepareStatement("INSERT INTO students " +
                    "(studentid,studentname,studentage,studentic,phone,birthdata,address,postcode,deptid,programme," +
                    "intakesem) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setInt(3,age);
            preparedStatement.setString(4,ic);
            preparedStatement.setString(5,phone);
            preparedStatement.setString(6,birDate);
            preparedStatement.setString(7,stuAddress);
            preparedStatement.setInt(8,postCode);
            preparedStatement.setString(9,departments);
            preparedStatement.setString(10,programmes);
            preparedStatement.setString(11,intake);

            // Execute the SQL update
            preparedStatement.executeUpdate();
        }
        // Handle any SQL exceptions
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateStudentInform(){
        try{
            // Retrieve list of student IDs for selection
            Statement statement = database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT studentid FROM students");

            // Populate the combo box with student IDs
            JComboBox comboID = new JComboBox<>();

            while (resultSet.next()){
                comboID.addItem(resultSet.getString("studentid"));
            }
            JOptionPane.showMessageDialog(this,comboID, "Choose Student ID", JOptionPane.PLAIN_MESSAGE);

            // Get the selected student ID
            String selectedID = comboID.getSelectedItem().toString();

            // Retrieve student information for the selected student ID
            PreparedStatement preparedStatement = database.getConnection().prepareStatement("SELECT studentid,studentname," +
                    "studentage,studentic,phone,birthdata,address,postcode,deptid,programme,intakesem FROM students WHERE studentid = ?");
            preparedStatement.setString(1, selectedID);
            ResultSet studentInfo = preparedStatement.executeQuery();

            String id = "";
            String name = "";
            String age = "";
            String ic = "";
            String phone = "";
            String birDate = "";
            String stuAddress = "";
            String postCode = "";
            String departments = "";
            String programmes = "";
            String intake = "";

            // Extract student information from the result set
            while (studentInfo.next()){
                id = studentInfo.getString("studentid");
                name = studentInfo.getString("studentname");
                age = studentInfo.getString("studentage");
                ic = studentInfo.getString("studentic");
                phone = studentInfo.getString("phone");
                birDate = studentInfo.getString("birthdata");
                stuAddress = studentInfo.getString("address");
                postCode = studentInfo.getString("postcode");
                departments = studentInfo.getString("deptid");
                programmes = studentInfo.getString("programme");
                intake = studentInfo.getString("intakesem");
            }

            // Create a panel with input fields pre-filled with existing information
            JPanel panel = new JPanel(new GridLayout(12,2,10,10));

            JTextField studentID = new JTextField(id);
            JTextField studentName = new JTextField(name);
            JTextField studentAge = new JTextField(age);
            JTextField studentIC = new JTextField(ic);
            JTextField phoneNumber = new JTextField(phone);
            JTextField birthDate = new JTextField(birDate);
            JTextField address = new JTextField(stuAddress);
            JTextField postcode = new JTextField(postCode);
            JTextField department = new JTextField(departments);
            JTextField programme = new JTextField(programmes);
            JTextField intakeSem = new JTextField(intake);

            panel.add(new JLabel("Student ID"));
            panel.add(studentID);
            panel.add(new JLabel("Student Name"));
            panel.add(studentName);
            panel.add(new JLabel("Age"));
            panel.add(studentAge);
            panel.add(new JLabel("IC Number"));
            panel.add(studentIC);
            panel.add(new JLabel("Phone Number"));
            panel.add(phoneNumber);
            panel.add(new JLabel("Birth Date example:2000-05-02"));
            panel.add(birthDate);
            panel.add(new JLabel("Address"));
            panel.add(address);
            panel.add(new JLabel("Postcode"));
            panel.add(postcode);
            panel.add(new JLabel("Department"));
            panel.add(department);
            panel.add(new JLabel("Programme"));
            panel.add(programme);
            panel.add(new JLabel("Intake Semester"));
            panel.add(intakeSem);

            JOptionPane.showMessageDialog(this, panel, "Update Student Information", JOptionPane.PLAIN_MESSAGE);

            // Get updated values from input fields
            id = studentID.getText();
            name = studentName.getText();
            age = studentAge.getText();
            ic = studentIC.getText();
            phone = phoneNumber.getText();
            birDate = birthDate.getText();
            stuAddress = address.getText();
            postCode = postcode.getText();
            departments = department.getText();
            programmes = programme.getText();
            intake = intakeSem.getText();

            // Update student information in the database
            preparedStatement = database.getConnection().prepareStatement("UPDATE students SET studentid =?,studentname =?," +
                    "studentage =?,studentic =?,phone =? ,birthdata =?,address =?,postcode =?,deptid =?,programme =?,intakesem =? WHERE studentid = ?");
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setInt(3,Integer.parseInt(age));
            preparedStatement.setString(4,ic);
            preparedStatement.setString(5,phone);
            preparedStatement.setString(6,birDate);
            preparedStatement.setString(7,stuAddress);
            preparedStatement.setInt(8,Integer.parseInt(postCode));
            preparedStatement.setString(9,departments);
            preparedStatement.setString(10,programmes);
            preparedStatement.setString(11,intake);
            preparedStatement.setString(12, selectedID);

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteStudentInform(){
        try {
            Statement statement = database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT studentid FROM students");

            JComboBox comboID = new JComboBox<>();

            while (resultSet.next()) {
                comboID.addItem(resultSet.getString("studentid"));
            }
            JOptionPane.showMessageDialog(this, comboID, "Choose Student ID", JOptionPane.PLAIN_MESSAGE);

            String selectedID = comboID.getSelectedItem().toString();

            int result = JOptionPane.showConfirmDialog(this,"Are you sure to delete "+ selectedID +"?",
                    "Delete Student", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            // If user confirms deletion, proceed
            if(result == JOptionPane.YES_OPTION) {
                PreparedStatement preparedStatement = database.getConnection().prepareStatement("DELETE FROM students WHERE studentid = ?");
                preparedStatement.setString(1, selectedID);

                preparedStatement.executeUpdate();
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void viewStudentInform(){
        try {
            Statement statement = database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT studentid,studentname,studentage,studentic,phone," +
                    "birthdata,address,postcode,deptid,programme,intakesem FROM students");

            String message = "";
            // Iterate through result set to build the message
            while (resultSet.next()) {
                message += "Student ID: : " + resultSet.getString("studentid") +
                        "\nStudent Name: " + resultSet.getString("studentname") +
                        "\nAge: " + resultSet.getString("studentage") +
                        "\nI.C.: " + resultSet.getString("studentic") +
                        "\nPhone: " + resultSet.getString("phone") +
                        "\nBirth Date: " + resultSet.getString("birthdata") +
                        "\nAddress: " + resultSet.getString("address") +
                        "\nPostcode: " + resultSet.getString("postcode") +
                        "\nDepartment: " + resultSet.getString("deptid") +
                        "\nProgramme: " + resultSet.getString("programme") +
                        "\nSem Intake: " + resultSet.getString("intakesem") +"\n\n";
            }

            JOptionPane.showMessageDialog(this, message, "List of Students", JOptionPane.PLAIN_MESSAGE);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
