import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel; // Import Nimbus Look and Feel
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create a JFrame for the application
                JFrame jFrame = new JFrame("College Management System");
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.setSize(1400, 800); // Increased height for a better view

                // Set Nimbus Look and Feel
                try {
                    UIManager.setLookAndFeel(new NimbusLookAndFeel());
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }

                // Create a CardLayout to manage panels
                CardLayout cardLayout = new CardLayout();
                JPanel jPanel = new JPanel(cardLayout);

                // Create a DatabaseManager instance for managing data
                DatabaseManager database = new DatabaseManager();

                // Create instances of custom panels
                HomePanel homePanel = new HomePanel(cardLayout, jPanel);
                StudentPanel studentPanel = new StudentPanel(cardLayout, jPanel, database);

                // Add panels to the main JPanel with unique names
                jPanel.add(homePanel, "Home");
                jPanel.add(studentPanel, "Student Management Information");

                // Add the main JPanel to the JFrame
                jFrame.add(jPanel);

                // Center the JFrame on the screen
                jFrame.setLocationRelativeTo(null);

                // Make the JFrame visible
                jFrame.setVisible(true);
            }
        });
    }
}
