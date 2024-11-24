


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


/**
 *
 * @author Son
 */
public class GamePanel extends javax.swing.JPanel {

    private static final int WIDTH = 80;
    private static final int HEIGHT = 80;
    private static final int X_SPACING = 50;
    private static final int Y_SPACING = 4;
    private static final int X_OFFSET = 8;
    private static final int Y_OFFSET = 5;
    
    private JLabel[] labels = new JLabel[10]; 
    private JPanel[] answerJPanels = new JPanel[10];
    private JPanel[] containerJPanels = new JPanel[10];
    private Map<JLabel, Integer> originalPositions = new HashMap<>();
    private Map<JLabel, Integer> answerPositions = new HashMap<>();
    private PriorityQueue<Integer> emptyPanels = new PriorityQueue<>();
    private List<Integer> availableNumbers = new ArrayList<>();
    
    
    public GamePanel() {
        initComponents();
        answerPanel.setLayout(null);
        containerPanel.setPreferredSize(new java.awt.Dimension(710, 172)); 
        initializeAvailableNumbers();
        addPanelsToContainerPanel();
        addPanelsToAnswerPanel();
        addLabelListeners();    
    }
    
    private void initializeAvailableNumbers() {
        for (int i = 0; i <= 100; i++) {
            availableNumbers.add(i);
        }
        Collections.shuffle(availableNumbers);
    }
    
    private void addPanelsToAnswerPanel() {
        int x = 8;
        int y = 0;
        for (int i = 0; i < answerJPanels.length; i++) {
            answerJPanels[i] = new JPanel();
            answerJPanels[i].setPreferredSize(new Dimension(WIDTH, HEIGHT));
            answerJPanels[i].setBackground(new java.awt.Color(0, 204, 255));
            answerJPanels[i].setBounds(x, y, WIDTH, HEIGHT);

            answerPanel.add(answerJPanels[i]);
            emptyPanels.add(i);

            x += WIDTH + X_OFFSET;
        }
        answerPanel.revalidate();
        answerPanel.repaint();
    }
    
    private void addPanelsToContainerPanel() {
        // Create and add labels to containerPanel
        for (int i = 0; i < containerJPanels.length; i++) {
            labels[i] = createLabel(i);
            containerJPanels[i] = new JPanel();
            containerJPanels[i].setPreferredSize(new Dimension(WIDTH, HEIGHT));
            containerJPanels[i].setBackground(Color.WHITE);
            containerJPanels[i].setBounds(
                55 + (i % 5) * (WIDTH + X_SPACING),
                4 + (i / 5) * (HEIGHT + Y_SPACING),
                WIDTH, HEIGHT
            );
            containerJPanels[i].add(labels[i]);
            answerPositions.put(labels[i], null);
            containerPanel.add(containerJPanels[i]);
        }
        containerPanel.revalidate();
        containerPanel.repaint();
    }
    
    private JLabel createLabel(int index ) {
        if (availableNumbers.isEmpty()) {
            initializeAvailableNumbers();
        }
        int randomNumber = availableNumbers.remove(0);
        JLabel label = new JLabel(String.valueOf(randomNumber), SwingConstants.CENTER);
        label.setBackground(Color.WHITE);
        label.setOpaque(true);
        label.setForeground(Color.BLACK);
        label.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        label.setFont(new Font("Arial", Font.PLAIN, 24));
        originalPositions.put(label, index);
        return label;
    }
    
    private void addLabelListeners() {
    
        for (JLabel label : labels) {
            label.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Label clicked: " + label.getText());
                    if (containerPanel.isAncestorOf(label)) {
                        moveToAnswerPanel(label);
                    } else {
                        moveToContainerPanel(label);
                    }
                }
            });
        }
    }

    private void moveToAnswerPanel(JLabel label) {
        if (!emptyPanels.isEmpty()) {
            int emptyPanelIndex = emptyPanels.poll();
            JPanel emptyPanel = answerJPanels[emptyPanelIndex];
            emptyPanel.add(label); 
            answerPositions.put(label, emptyPanelIndex);         
            label.setBounds(emptyPanel.getBounds());             
            emptyPanels.remove(emptyPanelIndex);
            
            emptyPanel.revalidate();
            emptyPanel.repaint();
            updatePanelsAfterMove(); 
        }
    }


    private void moveToContainerPanel(JLabel label) {
        int originalPosition = originalPositions.get(label);
        JPanel originalJPanel = containerJPanels[originalPosition];
        originalJPanel.add(label);
        emptyPanels.add(answerPositions.get(label));
        answerPositions.put(label, null);
        
        originalJPanel.revalidate();
        originalJPanel.repaint();
        updatePanelsAfterMove();
    }
    
    private void updatePanelsAfterMove() {
        answerPanel.revalidate();
        answerPanel.repaint();
        containerPanel.revalidate();
        containerPanel.repaint();
        revalidate();
        repaint();
    }
    
    private String getTextByAnswerPosition() {
        String result = "";

        boolean hasNullPosition = answerPositions.values().stream().anyMatch(Objects::isNull);
        if (hasNullPosition) {
            return result;
        }

        for (int i = 1; i <= 10; i++) {
            // Duyệt qua các entry trong answerPositions và kiểm tra vị trí
            for (Map.Entry<JLabel, Integer> entry : answerPositions.entrySet()) {
                Integer position = entry.getValue();
                if (position != null && position == i) {
                    JLabel label = entry.getKey();
                    try {
                        result += label.getText() + ",";
                    } catch (NumberFormatException e) {
                        System.err.println("Không thể chuyển đổi text của JLabel thành Integer: " + label.getText());
                    }
                }
            }
        }

        return result.substring(0, result.length() - 1);
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        answerPanel = new javax.swing.JPanel();
        containerPanel = new javax.swing.JPanel();
        submit = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 0, 51));

        answerPanel.setBackground(new java.awt.Color(0, 204, 255));
        answerPanel.setPreferredSize(new java.awt.Dimension(890, 90));

        javax.swing.GroupLayout answerPanelLayout = new javax.swing.GroupLayout(answerPanel);
        answerPanel.setLayout(answerPanelLayout);
        answerPanelLayout.setHorizontalGroup(
            answerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 888, Short.MAX_VALUE)
        );
        answerPanelLayout.setVerticalGroup(
            answerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        containerPanel.setMinimumSize(new java.awt.Dimension(710, 172));

        javax.swing.GroupLayout containerPanelLayout = new javax.swing.GroupLayout(containerPanel);
        containerPanel.setLayout(containerPanelLayout);
        containerPanelLayout.setHorizontalGroup(
            containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
        );
        containerPanelLayout.setVerticalGroup(
            containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 172, Short.MAX_VALUE)
        );

        submit.setText("Submit");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addComponent(containerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(submit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(answerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(answerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(containerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(submit)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        String result = getTextByAnswerPosition();
        if (result != "") {
            System.out.println("Các giá trị trả về: " + result);
        } else {
            // Nếu danh sách trống, có thể hiển thị thông báo hoặc xử lý khác
            System.out.println("Vui lòng điền đầy đủ, có position là null.");
        }
    }//GEN-LAST:event_submitActionPerformed

   
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(930, 400);
        frame.add(new GamePanel());
        frame.setVisible(true); 
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel answerPanel;
    private javax.swing.JPanel containerPanel;
    private javax.swing.JButton submit;
    // End of variables declaration//GEN-END:variables
}
