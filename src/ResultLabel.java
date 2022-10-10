
import javax.swing.BorderFactory;
import javax.swing.JLabel;

import logics.IconState;
import logics.Icons;

public class ResultLabel extends JLabel {
     Icons icon = new Icons();

     ResultLabel() {
          icon.getIcon(IconState.NO);
          setBorder(BorderFactory.createEmptyBorder(10, 150, 10, 50));
          setIcon(icon.getIcon(IconState.PENDING));
     }

}
