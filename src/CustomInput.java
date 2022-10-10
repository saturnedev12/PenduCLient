
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import logics.AppColors;
import logics.ColorsApp;
import logics.IconState;
import logics.Icons;

@SuppressWarnings("serial")
class CustomInput extends JPanel {
     JTextField textField;
     boolean activate = false;
     App app = new App();
     Registry reg; // Recherche dans le registre de l'objet distant
     Hello stub;

     public CustomInput() {
          try {
               // Récupérer le registre
               reg = LocateRegistry.getRegistry("192.168.1.3"); // Recherche dans le
               // registre de l'objet distant
               stub = (Hello) reg.lookup("Hello");
               // Appel de la méthode distante à l'aide de l'objet

          } catch (Exception e) {
               // TODO: handle exception
               System.err.println(e.toString());
               e.printStackTrace();
          }

          AppColors appColors = new AppColors();
          setBackground(appColors.getColor(ColorsApp.BACKGROUND));
          this.setLayout(new BorderLayout());
          textField = new JTextField("A-A-A", 4);
          JLabel indic = new JLabel("ENTRER LES LETTRE CHOISIS PAR LE SERVEUR");
          indic.setBorder(BorderFactory.createEmptyBorder(10, 10, 50, 10));

          Font fieldFont = new Font("Arial", Font.PLAIN, 40);
          indic.setFont(new Font("Arial", Font.PLAIN, 20));
          textField.setFont(fieldFont);

          DocumentFilter filter = new UppercaseDocumentFilter();
          AbstractDocument firstNameDoc = (AbstractDocument) textField.getDocument();
          firstNameDoc.setDocumentFilter(filter);
          textField.setBackground(Color.white);
          textField.setBorder(BorderFactory.createEmptyBorder(10, 300, 10, 400));

          // textField.setForeground(Color.gray.brighter());
          // textField.setColumns(3);

          textField.setBorder(BorderFactory.createCompoundBorder(
                    new CustomeBorder(),
                    new EmptyBorder(new Insets(15, 25, 15, 25))));
          textField.addActionListener(new FieldListener());
          textField.setAlignmentX(CENTER_ALIGNMENT);
          textField.addMouseListener(new FieldMouseListener());
          textField.setSize(200, 50);
          textField.setFocusable(false);
          setSize(getWidth(), 200);
          add(indic, BorderLayout.NORTH);
          add(textField, BorderLayout.CENTER);
          // setBackground(Color.blue);
          // setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

     }

     static class UppercaseDocumentFilter extends DocumentFilter {
          @Override
          public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr)
                    throws BadLocationException {
               fb.insertString(offset, text.toUpperCase(), attr);
          }

          @Override
          public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
               fb.replace(offset, length, text.toUpperCase(), attrs);
          }
     }

     @SuppressWarnings("serial")
     class CustomeBorder extends AbstractBorder {
          @Override
          public void paintBorder(Component c, Graphics g, int x, int y,
                    int width, int height) {
               // TODO Auto-generated method stubs
               super.paintBorder(c, g, x, y, width, height);
               Graphics2D g2d = (Graphics2D) g;
               g2d.setStroke(new BasicStroke(12));
               g2d.setColor(new AppColors().getColor(ColorsApp.BACKGROUND));
               g2d.drawRoundRect(x, y, width - 1, height - 1, 25, 25);
          }
     }

     class FieldListener implements ActionListener {

          @Override
          public void actionPerformed(ActionEvent e) {
               // TODO Auto-generated method stub
               String letter = textField.getText();
               System.out.println(textField.getText());
               try {
                    ResponseServer rps = stub.showMsg(letter);
                    if (rps == ResponseServer.YES) {
                         app.showMessage(ResponseServer.YES);
                    }
                    if (rps == ResponseServer.NO) {
                         app.showMessage(ResponseServer.NO);
                         textField.setText(letter.substring(0, letter.length() - 1));
                    }
                    if (rps == ResponseServer.SUCCESS) {
                         app.showMessage(ResponseServer.SUCCESS);
                    }
               } catch (RemoteException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
               }
               Icon dialogIcon = new Icons().getIcon(IconState.OK);

          }

     }

     class FieldMouseListener implements MouseListener {
          @Override
          public void mouseClicked(MouseEvent e) {
               textField.setFocusable(true);
               // TODO Auto-generated method stub
               if (activate == false) {
                    textField.setText("");
               }
               activate = true;
               textField.setForeground(Color.black);
          }

          @Override
          public void mousePressed(MouseEvent e) {
               // TODO Auto-generated method stub
          }

          @Override
          public void mouseReleased(MouseEvent e) {
               // TODO Auto-generated method stub
          }

          @Override
          public void mouseEntered(MouseEvent e) {
               textField.setFocusable(true);
               // TODO Auto-generated method stub

          }

          @Override
          public void mouseExited(MouseEvent e) {
               // TODO Auto-generated method stub

          }

     }

}