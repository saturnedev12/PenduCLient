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
import java.awt.event.KeyListener;
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
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import logics.AppColors;
import logics.ColorsApp;
import logics.IconState;
import logics.Icons;
import widgets.Score;

@SuppressWarnings("serial")
class CustomInput extends JPanel {
     JTextField textField;
     JLabel trust = new JLabel("", SwingConstants.CENTER);
     Window win;
     boolean activate = false;
     App app = new App();
     Registry reg; // Recherche dans le registre de l'objet distant
     Hello stub;

     public CustomInput(Window win) {
          this.win = win;
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
          JPanel zoneText = new JPanel();
          AppColors appColors = new AppColors();
          setBackground(appColors.getColor(ColorsApp.BACKGROUND));
          this.setLayout(new BorderLayout());
          textField = new JTextField("-", 1);
          textField.setHorizontalAlignment(JTextField.CENTER);
          textField.setDocument(new JTextFieldLimit(1));
          zoneText.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
          zoneText.setBackground(new AppColors().getColor(ColorsApp.BACKGROUND));

          JLabel indic = new JLabel("ENTRER LES LETTRES DU MOT CHOISIS PAR LE SERVEUR", SwingConstants.CENTER);
          indic.setBorder(BorderFactory.createEmptyBorder(10, 10, 50, 10));
          indic.setForeground(appColors.getColor(ColorsApp.BLACK));
          trust.setBorder(BorderFactory.createEmptyBorder(10, 10, 50, 10));

          Font fieldFont = new Font("Arial", Font.PLAIN, 40);
          indic.setFont(new Font("Arial", Font.BOLD, 15));
          trust.setFont(new Font("Arial", Font.BOLD, 25));
          textField.setFont(fieldFont);

          DocumentFilter filter = new UppercaseDocumentFilter();
          AbstractDocument firstNameDoc = (AbstractDocument) textField.getDocument();
          firstNameDoc.setDocumentFilter(filter);
          textField.setBackground(Color.white);

          // textField.setForeground(Color.gray.brighter());
          textField.setColumns(3);

          textField.setBorder(BorderFactory.createCompoundBorder(
                    new CustomeBorder(),
                    new EmptyBorder(new Insets(15, 10, 15, 10))));
          textField.addActionListener(new FieldListener());
          textField.setAlignmentX(CENTER_ALIGNMENT);
          textField.addMouseListener(new FieldMouseListener());
          textField.setSize(200, 50);
          textField.setFocusable(false);

          setSize(getWidth(), 200);
          add(indic, BorderLayout.NORTH);
          zoneText.add(textField);
          add(zoneText, BorderLayout.CENTER);
          add(trust, BorderLayout.SOUTH);
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
                    System.out.println(rps.toString());
                    if (rps.rps == ResponseStatus.YES) {

                         app.showMessage(ResponseStatus.YES);
                         new Score().reninitialize();
                         trust.setText(rps.trustWord);
                    }
                    if (rps.rps == ResponseStatus.NO) {
                         win.soustractScore();
                         app.showMessage(ResponseStatus.NO);
                         // letter.substring(0, letter.length() - 1)
                    }
                    if (rps.rps == ResponseStatus.SUCCESS) {

                         app.showMessage(ResponseStatus.SUCCESS);
                         trust.setText(rps.trustWord);
                    }
               } catch (RemoteException e1) {
                    // TODO Auto-generated catch block
                    System.err.println(e.toString());
                    e1.printStackTrace();
               }

               textField.setText("");
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

     public class JTextFieldLimit extends PlainDocument {
          private int limit;

          JTextFieldLimit(int limit) {
               super();
               this.limit = limit;
          }

          public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
               if (str == null)
                    return;

               if ((getLength() + str.length()) <= limit) {
                    super.insertString(offset, str, attr);
               }
          }
     }

}