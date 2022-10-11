package widgets;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Score extends JLabel {
     private static int trys = 3;

     public Score() {
          setText("Nombre essaie: " + trys);
          setFont(new Font("Arial", Font.ITALIC, 20));
          setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));

     }

     public void soustractScore() {
          trys -= 1;
     }

     public void reninitialize() {
          trys = 0;
     }

     int getScore() {
          return trys;
     }
}
