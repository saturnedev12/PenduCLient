package logics;

import java.awt.Color;

public class AppColors {
     public AppColors() {

     }

     public Color getColor(ColorsApp color) {
          if (color == ColorsApp.BACKGROUND) {
               return Color.decode("#BAF7FE");
          } else if (color == ColorsApp.RED) {
               return Color.decode("#FA4848");
          } else if (color == ColorsApp.GREEN) {
               return Color.decode("#70F780");
          } else if (color == ColorsApp.YELLO) {
               return Color.decode("#FAD791");
          }
          return Color.BLACK;
     }
}
