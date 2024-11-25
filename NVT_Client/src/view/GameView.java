
package view;

import controller.ClientController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import run.ClientRun;

import model.*;
import java.util.*;
import java.util.concurrent.Callable;
import javax.swing.*;

/**
 *
 * @author Ngọ Văn Trọng
 */
public class GameView extends javax.swing.JFrame {
    
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

    String competitor = "";
    DemTG matchTimer;
    DemTG waitingClientTimer;
    

    
    boolean answer = false;
   

    private void setIcon() {
        // Tạo ImageIcon từ tài nguyên
        ImageIcon icon = new ImageIcon(getClass().getResource("/Static/icon.png"));
        // Thiết lập icon cho JFrame
        setIconImage(icon.getImage());
    }
    public GameView() {
        initComponents();
       
       
        answerPanel.setLayout(null);
        addPanelsToAnswerPanel();
        
        setIcon(); 
        setPanel(0);
        panelPlayAgain.setVisible(false);
        win.setVisible(false);
        lose.setVisible(false);
        btnSubmit.setVisible(false);
        pbgTimer.setVisible(false);
        
        // close window event
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(GameView.this, "Nếu rời khỏi thì chắc chắn bạn sẽ thua?", "LEAVE GAME", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
                    ClientRun.socketHandler.leaveGame(competitor);
                    ClientRun.socketHandler.setRoomIdPresent(null);
                    dispose();
                } 
            }
        });
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
            containerJPanels[i].setBackground(new java.awt.Color(204, 0, 0));
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
    
  private JLabel createLabel(int index) {
    int randomNumber = 0;
    if (!availableNumbers.isEmpty()) {
        randomNumber = availableNumbers.remove(0);  // Lấy số từ danh sách
        System.out.println("Created label with number: " + randomNumber);  // In số được tạo cho JLabel
    } else {
        System.out.println("Danh sách availableNumbers đã rỗng!");  // Kiểm tra nếu danh sách trống
    }
    JLabel label = new JLabel(String.valueOf(randomNumber), SwingConstants.CENTER);
    label.setBackground(Color.WHITE);
    label.setOpaque(true);
    label.setForeground(Color.BLACK);
    label.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    label.setFont(new Font("Arial", Font.PLAIN, 24));
    originalPositions.put(label, index);
    return label;
}

    
    private int totalClicks = 0; 
    private void addLabelListeners() {
        for (JLabel label : labels) {
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    totalClicks++; // Tăng biến đếm mỗi lần click
                    System.out.println("Label clicked: " + label.getText());
                    System.out.println("Total clicks: " + totalClicks); // In tổng số lượt click

                    // Di chuyển giữa các panel
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
    
    public String getTextByAnswerPosition() {
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
private void resetGameState() {
    // Di chuyển tất cả các JLabel về containerPanel ban đầu
    for (Map.Entry<JLabel, Integer> entry : answerPositions.entrySet()) {
        JLabel label = entry.getKey();
        Integer position = entry.getValue();

        if (position != null) { // Nếu JLabel đang nằm ở answerPanel
            moveToContainerPanel(label); // Di chuyển JLabel về containerPanel
        }
    }

    // Reset lại danh sách emptyPanels
    emptyPanels.clear();
    for (int i = 0; i < answerJPanels.length; i++) {
        emptyPanels.add(i); // Thêm lại tất cả các vị trí trống vào emptyPanels
    }

    // Làm sạch trạng thái trong answerPositions
    answerPositions.clear();

    // Cập nhật lại giao diện sau khi reset
    updatePanelsAfterMove();

    // In thông báo để kiểm tra quá trình reset (tuỳ chọn)
    System.out.println("Game state has been reset.");
}


public void setPanel(int a) {
    if (a == 1) {
        gamePanel.setVisible(true);
    }
    if (a == 0) {
        gamePanel.setVisible(false);
        resetGameState(); 
    }
}



    
    public void setWaitingRoom () {
        
        setPanel(0);
        btnSubmit.setVisible(false);
        pbgTimer.setVisible(false);
        btnStart.setVisible(false);
 
        jLabel1.setVisible(false);
 
        lbWaiting.setText("Đợi đối thủ...");
        waitingReplyClient();
    }
    
    public void showAskPlayAgain (String msg) {
        if(msg == "Chúc mừng bạn đã thắng, bạn được 1 điểm, tiếp tục chứ?"){
        win.setVisible(true);
        panelPlayAgain.setVisible(true);
        lbResult.setText(msg);
        }
        if(msg == "Game hòa, cả hai được 0.5 điểm, bạn có muốn tiếp tục chơi không?"){
        
        panelPlayAgain.setVisible(true);
        lbResult.setText(msg);
        }
        if(msg == "Rất tiếc bạn đã thua, 0 điểm, tiếp tục chơi ?"){
        lose.setVisible(true);
        panelPlayAgain.setVisible(true);
        lbResult.setText(msg);
        }
    }
    
    public void hideAskPlayAgain () {
        panelPlayAgain.setVisible(false);
      
    }
    
    public void setInfoPlayer (String username) {
        competitor = username;
        infoPLayer.setText("Đối đầu cùng:" + username);
    }
    
    
    public void setStateHostRoom () {
        answer = false;
        btnStart.setVisible(true);
      
        jLabel1.setVisible(false);
    
        lbWaiting.setVisible(false);
    }
    
    public void setStateUserInvited () {
        answer = false;
        btnStart.setVisible(false);

        jLabel1.setVisible(false);

        lbWaiting.setVisible(true);
    }
    
    public void afterSubmit() {
        setPanel(0);
        btnSubmit.setVisible(false);
        lbWaiting.setVisible(true);
        lbWaiting.setText("Đợi kết quả trả về từ Server...");
    }
    
    public void setStartGame (int matchTimeLimit, String numbers) {
        setPanel(0);
        totalClicks = 0;
        answer = false;
        if (numbers == null || numbers.trim().isEmpty()) {
        throw new IllegalArgumentException("Chuỗi không hợp lệ");
    }

    // Tách chuỗi thành mảng
    String[] numberStrings = numbers.split(",");
    System.out.println("Numbers received: " + numbers);  // In ra chuỗi numbers nhận được từ server

    // Kiểm tra nếu không đủ 10 số
    if (numberStrings.length != 10) {
        throw new IllegalArgumentException("Cần đúng 10 số để khởi tạo game");
    }

    // Xóa danh sách cũ và thêm các số từ chuỗi
    availableNumbers.clear();
    for (String numStr : numberStrings) {
        try {
            int num = Integer.parseInt(numStr.trim()); // Chuyển đổi chuỗi thành số nguyên
            availableNumbers.add(num);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Chuỗi chứa giá trị không phải số: " + numStr, e);
        }
    }
    
    // In ra trạng thái của availableNumbers sau khi set game
   System.out.println("After setting game: " + availableNumbers);
            
        
        containerPanel.setPreferredSize(new java.awt.Dimension(710, 172)); 
      
        addPanelsToContainerPanel();
 
        addLabelListeners();  
        btnStart.setVisible(false);
        lbWaiting.setVisible(false);
        setPanel(1);
        btnSubmit.setVisible(true);
        pbgTimer.setVisible(true);       
        jLabel1.setVisible(true);
        matchTimer = new DemTG(matchTimeLimit);
        matchTimer.setTimerCallBack(
                // end match callback
                null,
                // tick match callback
                (Callable) () -> {
                    pbgTimer.setValue(100 * matchTimer.getCurrentTick() / matchTimer.getTimeLimit());
                    pbgTimer.setString("" + KieuTG.secondsToMinutes(matchTimer.getCurrentTick()));
                    if (pbgTimer.getString().equals("00:00")) {
                        afterSubmit();
                    }
                    return null;
                },
                // tick interval
                1
        );
    }
    
    public void waitingReplyClient () {
        waitingClientTimer = new DemTG(10);
        waitingClientTimer.setTimerCallBack(
                null,
                (Callable) () -> {
                    lbWaitingTimer.setText("" + KieuTG.secondsToMinutes(waitingClientTimer.getCurrentTick()));
                    if (lbWaitingTimer.getText().equals("00:00") && answer == false) {
                        hideAskPlayAgain();
                    }
                    return null;
                },
                1
        );
    }
    
    public void showMessage(String msg){
        JOptionPane.showMessageDialog(this, msg);
    }
    
    
    public void pauseTime () {
        matchTimer.pause();
    }
    
    public void continueTime(){
        matchTimer.resume();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel3 = new javax.swing.JLabel();
        btnLeaveGame = new javax.swing.JButton();
        infoPLayer = new javax.swing.JLabel();
        rectangleBackground1 = new view.RectangleBackground();
        panelPlayAgain = new javax.swing.JPanel();
        lbWaitingTimer = new javax.swing.JLabel();
        btnYes = new javax.swing.JButton();
        btnNo = new javax.swing.JButton();
        lbResult = new javax.swing.JLabel();
        pbgTimer = new javax.swing.JProgressBar();
        btnSubmit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        lbWaiting = new javax.swing.JLabel();
        gamePanel = new javax.swing.JPanel();
        answerPanel = new javax.swing.JPanel();
        containerPanel = new javax.swing.JPanel();
        rectangleBackground2 = new view.RectangleBackground();
        lose = new view.RectangleBackground();
        win = new view.RectangleBackground();
        Luat = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(204, 0, 0));

        jDesktopPane1.setBackground(new java.awt.Color(204, 0, 0));
        jDesktopPane1.setForeground(new java.awt.Color(204, 0, 0));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Static/vs.gif"))); // NOI18N

        btnLeaveGame.setBackground(new java.awt.Color(255, 0, 0));
        btnLeaveGame.setForeground(new java.awt.Color(255, 255, 255));
        btnLeaveGame.setText("Leave Game");
        btnLeaveGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeaveGameActionPerformed(evt);
            }
        });

        infoPLayer.setBackground(new java.awt.Color(204, 0, 0));
        infoPLayer.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        infoPLayer.setForeground(new java.awt.Color(255, 255, 255));
        infoPLayer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Static/swords.gif"))); // NOI18N
        infoPLayer.setText("Đối thủ:");

        jDesktopPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnLeaveGame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(infoPLayer, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addComponent(infoPLayer, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 250, Short.MAX_VALUE)
                .addComponent(btnLeaveGame, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLeaveGame, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(infoPLayer, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        rectangleBackground1.setBorderSize(0);
        rectangleBackground1.setImage(new javax.swing.ImageIcon(getClass().getResource("/Static/giphy.gif"))); // NOI18N

        panelPlayAgain.setBackground(new java.awt.Color(255, 255, 255));
        panelPlayAgain.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbWaitingTimer.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbWaitingTimer.setForeground(new java.awt.Color(204, 0, 0));
        lbWaitingTimer.setText("00:00");

        btnYes.setBackground(new java.awt.Color(204, 0, 0));
        btnYes.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnYes.setForeground(new java.awt.Color(255, 255, 255));
        btnYes.setText("Yes");
        btnYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYesActionPerformed(evt);
            }
        });

        btnNo.setBackground(new java.awt.Color(204, 0, 0));
        btnNo.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnNo.setForeground(new java.awt.Color(255, 255, 255));
        btnNo.setText("No");
        btnNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoActionPerformed(evt);
            }
        });

        lbResult.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbResult.setForeground(new java.awt.Color(204, 0, 0));
        lbResult.setText("Bạn có muốn chơi lại không ? ");

        javax.swing.GroupLayout panelPlayAgainLayout = new javax.swing.GroupLayout(panelPlayAgain);
        panelPlayAgain.setLayout(panelPlayAgainLayout);
        panelPlayAgainLayout.setHorizontalGroup(
            panelPlayAgainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPlayAgainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbResult, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbWaitingTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnYes, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelPlayAgainLayout.setVerticalGroup(
            panelPlayAgainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPlayAgainLayout.createSequentialGroup()
                .addGroup(panelPlayAgainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPlayAgainLayout.createSequentialGroup()
                        .addComponent(lbResult, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelPlayAgainLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelPlayAgainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnYes, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbWaitingTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        rectangleBackground1.add(panelPlayAgain);
        panelPlayAgain.setBounds(40, 160, 850, 50);

        pbgTimer.setBackground(new java.awt.Color(255, 255, 255));
        pbgTimer.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        pbgTimer.setStringPainted(true);
        rectangleBackground1.add(pbgTimer);
        pbgTimer.setBounds(40, 170, 860, 50);
        pbgTimer.getAccessibleContext().setAccessibleName("");

        btnSubmit.setBackground(new java.awt.Color(204, 0, 0));
        btnSubmit.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnSubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        rectangleBackground1.add(btnSubmit);
        btnSubmit.setBounds(830, 560, 120, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Static/timing.gif"))); // NOI18N
        rectangleBackground1.add(jLabel1);
        jLabel1.setBounds(920, 150, 87, 70);

        btnStart.setBackground(new java.awt.Color(204, 0, 0));
        btnStart.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnStart.setForeground(new java.awt.Color(255, 255, 255));
        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        rectangleBackground1.add(btnStart);
        btnStart.setBounds(100, 10, 98, 34);

        lbWaiting.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbWaiting.setForeground(new java.awt.Color(255, 255, 255));
        lbWaiting.setText("Chờ đối thủ Start...");
        rectangleBackground1.add(lbWaiting);
        lbWaiting.setBounds(40, 50, 288, 48);

        gamePanel.setBackground(new java.awt.Color(255, 255, 255));
        gamePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

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

        containerPanel.setBackground(new java.awt.Color(204, 0, 0));
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

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(answerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(containerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(answerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(containerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        rectangleBackground1.add(gamePanel);
        gamePanel.setBounds(40, 230, 930, 320);
        rectangleBackground1.add(rectangleBackground2);
        rectangleBackground2.setBounds(190, 190, 0, 0);

        lose.setBorderSize(0);
        lose.setImage(new javax.swing.ImageIcon(getClass().getResource("/Static/khocthua.gif"))); // NOI18N

        win.setBorderSize(0);
        win.setImage(new javax.swing.ImageIcon(getClass().getResource("/Static/think-smart.gif"))); // NOI18N
        lose.add(win);
        win.setBounds(-120, 0, 280, 160);

        rectangleBackground1.add(lose);
        lose.setBounds(450, 0, 290, 160);

        Luat.setBackground(new java.awt.Color(204, 51, 0));
        Luat.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        Luat.setForeground(new java.awt.Color(255, 255, 255));
        Luat.setText("Luật chơi");
        Luat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LuatActionPerformed(evt);
            }
        });
        rectangleBackground1.add(Luat);
        Luat.setBounds(860, 90, 110, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
            .addComponent(rectangleBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rectangleBackground1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLeaveGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeaveGameActionPerformed
        if (JOptionPane.showConfirmDialog(GameView.this, "Nếu rời khỏi Game, bạn sẽ thua?", "LEAVE GAME", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            ClientRun.socketHandler.leaveGame(competitor);
            ClientRun.socketHandler.setRoomIdPresent(null);
            dispose();
        } 
    }//GEN-LAST:event_btnLeaveGameActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        ClientRun.socketHandler.startGame(competitor);
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed

        String result = getTextByAnswerPosition();
        String tong = String.valueOf(totalClicks);
        ClientRun.socketHandler.submitResult(competitor, result, tong);
        if (result == "") {
            ClientRun.gameView.showMessage("Hãy điển đầy đi!");
        } else {
            System.out.println(result);

       
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoActionPerformed
        ClientRun.socketHandler.notAcceptPlayAgain();
        answer = true;
        hideAskPlayAgain();
    }//GEN-LAST:event_btnNoActionPerformed

    private void btnYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYesActionPerformed
        ClientRun.socketHandler.acceptPlayAgain();
        answer = true;
        hideAskPlayAgain();
    }//GEN-LAST:event_btnYesActionPerformed
        
    private void LuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LuatActionPerformed
        Luatchoi luatChoiFrame = new Luatchoi();
    
      luatChoiFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      luatChoiFrame.setVisible(true); // Mở cửa sổ LuatChoi
    }//GEN-LAST:event_LuatActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameView().setVisible(true);
            }
        });
    }
       

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Luat;
    private javax.swing.JPanel answerPanel;
    private javax.swing.JButton btnLeaveGame;
    private javax.swing.JButton btnNo;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnYes;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JPanel containerPanel;
    private javax.swing.JPanel gamePanel;
    private javax.swing.JLabel infoPLayer;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbResult;
    private javax.swing.JLabel lbWaiting;
    private javax.swing.JLabel lbWaitingTimer;
    private view.RectangleBackground lose;
    private javax.swing.JPanel panelPlayAgain;
    public static javax.swing.JProgressBar pbgTimer;
    private view.RectangleBackground rectangleBackground1;
    private view.RectangleBackground rectangleBackground2;
    private view.RectangleBackground win;
    // End of variables declaration//GEN-END:variables
}
