
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import logics.AppColors;
import logics.ColorsApp;
import logics.IconState;
import logics.Icons;

import java.awt.BorderLayout;

public class Window extends JFrame {
     ResultLabel rsult = new ResultLabel();
     UIManager ui = new UIManager();
     JTextField jt = new JTextField();

     public Window() {
          this.setLayout(new BorderLayout());
          this.setTitle("Jeu du pendu"); // On donne un titre à l'application
          this.setSize(550, 600); // On donne une taille à notre fenêtre
          this.setLocationRelativeTo(null); // On centre la fenêtre sur l'écran
          this.setResizable(false);
          this.setLocationRelativeTo(null);// On interdit la redimensionnement de la fenêtre
          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

          //
          CustomInput mainPanel = new CustomInput();
          mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 30, 5, 30));
          this.add(mainPanel, BorderLayout.NORTH);
          jt.addActionListener(null);
          // jl.setBounds();
          // jl.setBackground(Color.decode("#98F1FB"));
          JLabel score = new JLabel("Essaie restant: 3");
          this.getContentPane().add(rsult, BorderLayout.CENTER);

          score.setBorder(null);
          // score.setFont(new Font("Arial", Font.PLAIN, 24));
          // score.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
          this.add(score, BorderLayout.SOUTH);
          getContentPane().setBackground(new AppColors().getColor(ColorsApp.BACKGROUND));

          setVisible(true);
     }

}
