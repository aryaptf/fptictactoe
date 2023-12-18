import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutUs extends JDialog {
    public AboutUs(JFrame parent) {
        super(parent, "About", true);
        setSize(600, 400);
        setLocationRelativeTo(parent);

        ImageIcon originalIcon = new ImageIcon("src\\fotoasd.jpg");

        // Mendapatkan gambar dari ImageIcon
        Image originalImage = originalIcon.getImage();

        // Menyesuaikan ukuran gambar
        Image scaledImage = originalImage.getScaledInstance(400, 300, Image.SCALE_SMOOTH);

        // Membuat ImageIcon baru dengan gambar yang sudah diubah ukurannya
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel photoLabel = new JLabel(scaledIcon);
        JLabel nameLabel = new JLabel("<html><center>Tic Tac Toe<br>Version 1.0<br>Created by Group 9<br>5026221131 - Maulina Nur Laila<br>5026221172 - Arya Putra Tsabitah Firjatulloh<br>5026221179 - Kadek Mawar Kumala Dewi<br></center></html>");

        JPanel panel = new JPanel(new GridLayout(1, 1));
        panel.add(photoLabel);
        panel.add(nameLabel);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(panel, BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            JButton openButton = new JButton("Open AboutUs Dialog");

            openButton.addActionListener(e -> {
                AboutUs aboutUs = new AboutUs(frame);
                aboutUs.setVisible(true);
            });

            frame.setLayout(new FlowLayout());
            frame.add(openButton);
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
