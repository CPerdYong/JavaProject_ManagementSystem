import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel {
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

        //Set the logo to be at the middle
        int xCoordinate = (getWidth()-logoWidth) / 2;
        int yCoordinate = (getHeight()-logoHeight) / 2;

        g.drawImage(logoImage, xCoordinate, yCoordinate, logoWidth,logoHeight,this);
    }
    public HomePanel(CardLayout cardLayout, JPanel jPanel) {
        // Use FlowLayout with horizontal alignment for the panel
        setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton studentButton = new JButton("Log In");
        studentButton.setPreferredSize(new Dimension(350, 65));
        studentButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        studentButton.setForeground(new Color(255, 255, 255, 255));
        studentButton.setRolloverEnabled(true);// Enable rollover effect
        studentButton.setBackground(new Color(88, 88, 255, 186));// Set a semi-transparent background
        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login();
                cardLayout.show(jPanel, "Student Management Information");
            }

            public void Login() {
                String password = JOptionPane.showInputDialog(null, "Enter your password:", "Must Login First!!!", JOptionPane.WARNING_MESSAGE);
                while (!password.equals("12345678")) {
                    password = JOptionPane.showInputDialog(null, "Enter your password:", "Must Login First!!!", JOptionPane.WARNING_MESSAGE);
                    if (password.equals("12345678")) {
                        break;
                    }
                }
            }
        });
        // Add the studentButton to the panel
        add(studentButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(350, 65));
        exitButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        exitButton.setForeground(new Color(255, 255, 255, 255));
        exitButton.setRolloverEnabled(true);// Enable rollover effect
        exitButton.setBackground(new Color(88, 88, 255, 186));// Set a semi-transparent background
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        // Add the exitButton to the panel
        add(exitButton);
    }
}
