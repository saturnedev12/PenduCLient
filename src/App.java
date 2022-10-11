import java.awt.Color;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import logics.AppColors;
import logics.ColorsApp;
import logics.IconState;
import logics.Icons;

public class App {
    public static Window win;
    static UIManager ui;

    public static void main(String[] args) throws Exception {
        ui = new UIManager();

        win = new Window();

    }

    void showMessage(ResponseStatus rps) {

        if (rps == ResponseStatus.YES) {
            UIManager.put("OptionPane.background", new AppColors().getColor(ColorsApp.GREEN));
            UIManager.put("Panel.background", new AppColors().getColor(ColorsApp.GREEN));
            Icon dialogIcon = new Icons().getIcon(IconState.OK);
            JOptionPane.showMessageDialog(win, "Lettre correcte", "Résultat", JOptionPane.INFORMATION_MESSAGE,
                    dialogIcon);
        } else if (rps == ResponseStatus.NO) {
            UIManager.put("OptionPane.background", new AppColors().getColor(ColorsApp.RED));
            UIManager.put("Panel.background", new AppColors().getColor(ColorsApp.RED));
            Icon dialogIcon = new Icons().getIcon(IconState.NO);
            JOptionPane.showMessageDialog(win, "Lettre incorrecte", "Résultat", JOptionPane.INFORMATION_MESSAGE,
                    dialogIcon);
        } else if (rps == ResponseStatus.SUCCESS) {
            UIManager.put("OptionPane.background", new AppColors().getColor(ColorsApp.YELLO));
            UIManager.put("Panel.background", new AppColors().getColor(ColorsApp.YELLO));
            Icon dialogIcon = new Icons().getIcon(IconState.SUCCESS);
            JOptionPane.showMessageDialog(win, "Vous avez gané", "Résultat", JOptionPane.INFORMATION_MESSAGE,
                    dialogIcon);
        }

    }
}
