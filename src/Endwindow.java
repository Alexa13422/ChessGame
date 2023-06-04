import javax.swing.*;
import java.awt.*;

public class Endwindow  {
  public Endwindow(String text){
      JFrame desk = new JFrame();
      desk.setTitle("Victory");
      int width = 480, height =480;
      desk.setSize(width, height);
      desk.setLayout(new BorderLayout());
      desk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      desk.setVisible(true);
      JLabel label = new JLabel(text+" WINS!");
      label.setHorizontalAlignment(SwingConstants.CENTER);
      label.setVerticalAlignment(SwingConstants.CENTER);
      label.setFont(new Font("Ami-ac",Font.ITALIC,30));
      label.setVisible(true);
      desk.add(label);
    }
}
