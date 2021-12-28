import javax.swing.*;
import java.awt.*;

public class Circle extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(255, 118, 117));
        g.fillOval(0,0,getWidth(),getHeight());
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(new Color(0));
        g.drawOval(0,0,getWidth(),getHeight());
    }
}
