package logics;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Icons {
     public Icons() {
     }

     public Icon getIcon(IconState icon) {

          if (icon == IconState.OK) {
               return new ImageIcon(this.getClass().getResource("/assets/ok.gif"));
          } else if (icon == IconState.NO) {
               return new ImageIcon(this.getClass().getResource("/assets/no.gif"));
          } else if (icon == IconState.PENDING) {
               return new ImageIcon(this.getClass().getResource("/assets/pending.gif"));
          } else if (icon == IconState.FAIL) {
               return new ImageIcon(this.getClass().getResource("/assets/fail.gif"));
          } else if (icon == IconState.SUCCESS) {
               return new ImageIcon(this.getClass().getResource("/assets/success.gif"));
          }
          return new ImageIcon(this.getClass().getResource("/assets/pending.gif"));
     }
}
