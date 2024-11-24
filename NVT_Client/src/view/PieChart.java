package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;

/**
 * PieChart Component có thể tích hợp vào giao diện khác
 * @author Ngọ Văn Trọng
 */
public class PieChart extends JPanel {
    private double[] values = {23.43,55.72 , 20.83}; // Phần trăm cho mỗi mục
    private String[] labels = {"Thắng: ", "Hòa: ", "Thua: "};
    private Color[] colors = {
//        new Color(204, 0, 0),
//        new Color(255,0,0),
//        new Color(255,102,102)  // Màu xanh đậm
//        new Color(142, 236, 245),
//        new Color(100, 204, 225),
        new Color(80, 169, 199),
        new Color(60, 134, 173),
        new Color(40, 99, 147)
    };

    public PieChart() {
        setPreferredSize(new Dimension(300, 300));
    }
    
    // Constructor cho phép tùy chỉnh dữ liệu
    public PieChart(double[] values, String[] labels, Color[] colors) {
        this();
        if (values != null && labels != null && colors != null 
            && values.length == labels.length && values.length == colors.length) {
            this.values = values;
            this.labels = labels;
            this.colors = colors;
        }
    }
    
    // Phương thức cập nhật dữ liệu
    public void updateData(double[] values, String[] labels) {
        if (values != null && labels != null && values.length == labels.length) {
            this.values = values;
            this.labels = labels;
            repaint(); // Vẽ lại biểu đồ với dữ liệu mới
        }
    }
    
    // Phương thức cập nhật màu sắc
    public void updateColors(Color[] colors) {
        if (colors != null && colors.length == this.values.length) {
            this.colors = colors;
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Tính toán kích thước và vị trí của biểu đồ
        int width = getWidth();
        int height = getHeight();
        int size = Math.min(width, height) - 100;
        int x = (width - size) / 2;
        int y = (height - size) / 2;

        double total = 0;
        for (double value : values) {
            total += value;
        }

        double startAngle = 0;
        for (int i = 0; i < values.length; i++) {
            double angle = (values[i] / total) * 360;
            
            // Vẽ phần của biểu đồ
            Arc2D.Double arc = new Arc2D.Double(x, y, size, size, startAngle, angle, Arc2D.PIE);
            g2d.setColor(colors[i]);
            g2d.fill(arc);
            
            // Vẽ chú thích
            double radians = Math.toRadians(startAngle + angle/2);
            int labelX = x + size/2 + (int)(Math.cos(radians) * (size/2 + 30));
            int labelY = y + size/2 + (int)(Math.sin(radians) * (size/2 + 30));
            
            g2d.setColor(Color.BLACK);
            String label = labels[i] + "\n" + String.format("%.1f", values[i]) + "%";
            g2d.drawString(label, labelX, labelY);
            
            startAngle += angle;
        }
    }

    // Ví dụ sử dụng
    public static void main(String[] args) {
        // Ví dụ cách sử dụng component
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test PieChart");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // Tạo dữ liệu mẫu
            double[] testValues = {30, 20, 15};
            String[] testLabels = {"A", "B", "C"};
            
            // Tạo biểu đồ
            PieChart chart = new PieChart();
            chart.updateData(testValues, testLabels);
            
            frame.add(chart);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}