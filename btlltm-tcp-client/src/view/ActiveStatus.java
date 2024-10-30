package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
///**
// *
// * @author Ngọ Văn Trọng
// */
//public class ActiveStatus extends Component {
//
//    public boolean isActive() {
//        return active;
//    }
//
//    public void setActive(boolean active) {
//        this.active = active;
//        repaint();
//    }
//
//    private boolean active;
//
//    public ActiveStatus() {
//        setPreferredSize(new Dimension(8, 8));
//    }
//
//    @Override
//    public void paint(Graphics grphcs) {
//        if (active) {
//            Graphics2D g2 = (Graphics2D) grphcs;
//            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            g2.setColor(new Color(62, 165, 49));
//            g2.fillOval(0, (getHeight() / 2) - 4, 8, 8);
//        }
//    }
//}
public class ActiveStatus extends Component {

    private boolean active;

    public ActiveStatus() {
        setPreferredSize(new Dimension(20, 20)); // Tăng kích thước mặc định của Component
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        repaint();
    }

    @Override
    public void paint(Graphics grphcs) {
        if (active) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(62, 165, 49));
            int diameter = 12; // Đặt kích thước hình tròn là 12x12
            int x = (getWidth() - diameter) / 2; // Tính toán vị trí X để hình tròn nằm giữa theo chiều ngang
            int y = (getHeight() - diameter) / 2; // Tính toán vị trí Y để hình tròn nằm giữa theo chiều dọc
            g2.fillOval(x, y, diameter, diameter); // Vẽ hình tròn với kích thước mới
        }
    }
}
