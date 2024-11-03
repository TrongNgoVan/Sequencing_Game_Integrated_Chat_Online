


package view;

/**
 *
 * @author Ngọ Văn Trọng
 */

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RectangleBackground extends JComponent {

    private Icon image;
    private int borderSize = 5;
    private Color borderColor = new Color(60, 60, 60);

    public RectangleBackground() {
        // Constructor mặc định
    }

    // Getter và setter cho ảnh nền
    public Icon getImage() {
        return image;
    }

    public void setImage(Icon image) {
        this.image = image;
        repaint();
    }

    // Getter và setter cho kích thước đường viền
    public int getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
        repaint();
    }

    // Getter và setter cho màu đường viền
    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Nếu có ảnh nền, vẽ ảnh nền bên trong hình chữ nhật
        if (image != null) {
            BufferedImage bufferedImage = new BufferedImage(
                    getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.drawImage(toImage(image), 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
            g2.drawImage(bufferedImage, 0, 0, null);
        }

        // Vẽ đường viền hình chữ nhật
        if (borderSize > 0) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(borderSize));
            g2.drawRect(borderSize / 2, borderSize / 2, getWidth() - borderSize, getHeight() - borderSize);
        }
    }

    // Phương thức chuyển Icon sang Image
    private Image toImage(Icon icon) {
        return ((ImageIcon) icon).getImage();
    }
}
