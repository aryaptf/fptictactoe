import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutUs extends JDialog {
   public AboutUs(JFrame parent) {
        super(parent, "About", true);
        setSize(400, 200);
        setLocationRelativeTo(parent);

        JLabel photoLabel = new JLabel (new ImageIcon("src\\fotoasd.jpg"));
        JLabel nameLabel = new JLabel ("1 - 5026221131 - Maulina Nur Laila\n" + "2 - 5026221172 - Arya Putra Tsabitah Firjatulloh\n"
                + "3 - 5026221179 - Kadek Mawar Kumala Dewi");
         JPanel panel = new JPanel (new GridLayout(1, 1));
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
}
     

