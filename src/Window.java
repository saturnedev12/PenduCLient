
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
import widgets.Score;

import java.awt.BorderLayout;
import java.awt.Font;

public class Window extends JFrame {
     static int sc = 3;
     ResultLabel rsult = new ResultLabel();
     UIManager ui = new UIManager();
     JTextField jt = new JTextField();
     // Score score = new Score();
     JLabel score = new JLabel("Nombre essaie: " + sc);

     public Window() {
          this.setLayout(new BorderLayout());
          this.setTitle("Jeu du pendu"); // On donne un titre à l'application
          this.setSize(550, 600); // On donne une taille à notre fenêtre
          this.setLocationRelativeTo(null); // On centre la fenêtre sur l'écran
          this.setResizable(false);
          this.setLocationRelativeTo(null);// On interdit la redimensionnement de la fenêtre
          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

          //
          CustomInput mainPanel = new CustomInput(this);
          mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 30, 5, 30));
          this.add(mainPanel, BorderLayout.NORTH);
          jt.addActionListener(null);

          this.getContentPane().add(rsult, BorderLayout.CENTER);
          score.setFont(new Font("Arial", Font.PLAIN, 24));
          score.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
          this.add(score, BorderLayout.SOUTH);
          getContentPane().setBackground(new AppColors().getColor(ColorsApp.BACKGROUND));

          setVisible(true);
     }

     public void soustractScore() {
          System.out.println("score " + sc);
          if (sc == 0) {
               UIManager.put("OptionPane.background", new AppColors().getColor(ColorsApp.RED));
               UIManager.put("Panel.background", new AppColors().getColor(ColorsApp.RED));
               Icon dialogIcon = new Icons().getIcon(IconState.FAIL);
               Object[] options = { "Réjouer",
                         "Quiter" };
               int n = JOptionPane.showOptionDialog(this,
                         "Vous avez Perdu",
                         "A Silly Question",
                         JOptionPane.YES_NO_OPTION,
                         JOptionPane.QUESTION_MESSAGE,
                         dialogIcon, // do not use a custom Icon
                         options, // the titles of buttons
                         options[0]);
               if (n == JOptionPane.NO_OPTION) {
                    this.dispose();

               }
               sc = 3;
               score.setText("Nombre essaie: " + sc);
               this.add(score, BorderLayout.SOUTH);

          } else if (sc > 0) {
               sc = sc - 1;

               score.setText("Nombre essaie: " + sc);
               this.add(score, BorderLayout.SOUTH);
          }

     }

}
