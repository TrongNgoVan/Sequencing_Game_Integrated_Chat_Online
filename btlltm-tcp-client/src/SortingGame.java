import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SortingGame extends JFrame {
    private JPanel[] rows;
    private DraggableNumber[][] numbers;
    private int selectedRow = -1;
    private int selectedCol = -1;

    public SortingGame() {
        setTitle("Sorting Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));  // Cập nhật GridLayout để có thêm hàng cho nút submit
        getContentPane().setBackground(new Color(240, 240, 240));

        rows = new JPanel[4];
        numbers = new DraggableNumber[4][5];

        int[][] initialNumbers = {
            {1, 2, 5, 4, 3},
            {2, 1, 7, 8, 9},
            {10, 4, 3, 7, 1},
            {3, 5, 8, 1, 2}
        };

        for (int i = 0; i < 4; i++) {
            rows[i] = new JPanel();
            rows[i].setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
            rows[i].setBackground(new Color(240, 240, 240));

            ArrowLabel rowLabel = new ArrowLabel("Dãy " + (i + 1));
            rows[i].add(rowLabel);

            for (int j = 0; j < 5; j++) {
                numbers[i][j] = new DraggableNumber(String.valueOf(initialNumbers[i][j]));
                setupDragAndDrop(numbers[i][j], i, j);
                rows[i].add(numbers[i][j]);
            }
            add(rows[i]);
        }

        // Tạo nút submit
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printSortedArrays();
            }
        });
        add(submitButton);

        setSize(500, 500);
        setLocationRelativeTo(null);
    }

    // Hàm in ra dãy đã sắp xếp
    private void printSortedArrays() {
        for (int i = 0; i < 4; i++) {
            System.out.print("Dãy " + (i + 1) + ": ");
            for (int j = 0; j < 5; j++) {
                System.out.print(numbers[i][j].getText() + " ");
            }
            System.out.println();
        }
    }

    // Các lớp phụ trợ vẫn giữ nguyên
    class ArrowLabel extends JLabel {
        public ArrowLabel(String text) {
            super(text);
            setPreferredSize(new Dimension(100, 60));
            setForeground(Color.WHITE);
            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(new Font("Arial", Font.BOLD, 14));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int[] xPoints = {0, 70, 70, 100, 70, 70, 0};
            int[] yPoints = {0, 0, 10, 30, 50, 60, 60};

            g2d.setColor(new Color(180, 0, 0));
            g2d.fillPolygon(xPoints, yPoints, 7);

            g2d.setColor(getForeground());
            FontMetrics fm = g2d.getFontMetrics();
            int x = 10;
            int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
            g2d.drawString(getText(), x, y);

            g2d.dispose();
        }
    }

    class DraggableNumber extends JLabel {
        public DraggableNumber(String text) {
            super(text);
            setOpaque(true);
            setBackground(new Color(180, 0, 0));
            setForeground(Color.WHITE);
            setHorizontalAlignment(SwingConstants.CENTER);
            setVerticalAlignment(SwingConstants.CENTER);
            setPreferredSize(new Dimension(60, 60));
            setFont(new Font("Arial", Font.BOLD, 24));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(getBackground());
            g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);

            g2d.setColor(getForeground());
            FontMetrics fm = g2d.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
            g2d.drawString(getText(), x, y);

            g2d.dispose();
        }
    }

    private void setupDragAndDrop(DraggableNumber label, int row, int col) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedRow = row;
                selectedCol = col;
                label.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Point p = e.getPoint();
                SwingUtilities.convertPointToScreen(p, label);

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        numbers[i][j].setBackground(new Color(180, 0, 0));
                    }
                }

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        DraggableNumber targetLabel = numbers[i][j];
                        Point targetPoint = targetLabel.getLocationOnScreen();
                        Rectangle targetBounds = new Rectangle(targetPoint.x, targetPoint.y, 
                                                            targetLabel.getWidth(), targetLabel.getHeight());

                        if (targetBounds.contains(p) && (i != selectedRow || j != selectedCol)) {
                            String temp = label.getText();
                            label.setText(targetLabel.getText());
                            targetLabel.setText(temp);
                            break;
                        }
                    }
                }

                label.setBorder(null);
            }
        });

        label.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = e.getPoint();
                SwingUtilities.convertPointToScreen(p, label);

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        numbers[i][j].setBackground(new Color(180, 0, 0));
                    }
                }

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        DraggableNumber targetLabel = numbers[i][j];
                        Point targetPoint = targetLabel.getLocationOnScreen();
                        Rectangle targetBounds = new Rectangle(targetPoint.x, targetPoint.y, 
                                                            targetLabel.getWidth(), targetLabel.getHeight());

                        if (targetBounds.contains(p) && (i != selectedRow || j != selectedCol)) {
                            targetLabel.setBackground(new Color(255, 140, 0));
                        }
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SortingGame().setVisible(true);
        });
    }
}
