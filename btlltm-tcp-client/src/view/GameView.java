
package view;

import java.util.concurrent.Callable;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import run.ClientRun;
import helper.*;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;

/**
 *
 * @author Ngọ Văn Trọng
 */
public class GameView extends javax.swing.JFrame {

    String competitor = "";
    CountDownTimer matchTimer;
    CountDownTimer waitingClientTimer;
    
    String a1 = "";
    String a2 = "";
    String a3 = "";
    String a4 = "";
   
    
    boolean answer = false;
   

    private void setIcon() {
        // Tạo ImageIcon từ tài nguyên
        ImageIcon icon = new ImageIcon(getClass().getResource("/Static/icon.png"));
        // Thiết lập icon cho JFrame
        setIconImage(icon.getImage());
    }
    public GameView() {
        initComponents();
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
    
    public void setPanel(int a){
        if(a == 1){
       jPanel2.setVisible(true);
        jPanel3.setVisible(true);
         jPanel4.setVisible(true);
          jPanel5.setVisible(true);
        }
        if(a==0){
           jPanel2.setVisible(false);
           jPanel3.setVisible(false);
           jPanel4.setVisible(false);
           jPanel5.setVisible(false);
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
        if(msg == "Chúc mừng bạn đã thắng, bạn được 1.5 điểm, tiếp tục chứ?"){
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
    
    public void setQuestion1 (String a, String answerA, String answerB, String answerC, String answerD) {
        setA1(a);
       
        lbQuestion1.setText("1. " + a );
        answer1a.setText(answerA);
        answer1b.setText(answerB);
        answer1c.setText(answerC);
        answer1d.setText(answerD);
    }
    
    public void setQuestion2 (String a,  String answerA, String answerB, String answerC, String answerD) {
        setA2(a);
        
        lbQuestion2.setText("2. " + a );
        answer2a.setText(answerA);
        answer2b.setText(answerB);
        answer2c.setText(answerC);
        answer2d.setText(answerD);
    }
    
    public void setQuestion3 (String a, String answerA, String answerB, String answerC, String answerD) {
        setA3(a);
        
        lbQuestion3.setText("3. " + a );
        answer3a.setText(answerA);
        answer3b.setText(answerB);
        answer3c.setText(answerC);
        answer3d.setText(answerD);
    }
    
    public void setQuestion4 (String a,  String answerA, String answerB, String answerC, String answerD) {
        setA4(a);
       
        lbQuestion4.setText("4. " + a );
        answer4a.setText(answerA);
        answer4b.setText(answerB);
        answer4c.setText(answerC);
        answer4d.setText(answerD);
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
    
    public void setStartGame (int matchTimeLimit) {
        answer = false;
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
        buttonGroup3.clearSelection();
        buttonGroup4.clearSelection();
        
        btnStart.setVisible(false);
        lbWaiting.setVisible(false);
        setPanel(1);
        btnSubmit.setVisible(true);
        pbgTimer.setVisible(true);       
        jLabel1.setVisible(true);
        matchTimer = new CountDownTimer(matchTimeLimit);
        matchTimer.setTimerCallBack(
                // end match callback
                null,
                // tick match callback
                (Callable) () -> {
                    pbgTimer.setValue(100 * matchTimer.getCurrentTick() / matchTimer.getTimeLimit());
                    pbgTimer.setString("" + CustumDateTimeFormatter.secondsToMinutes(matchTimer.getCurrentTick()));
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
        waitingClientTimer = new CountDownTimer(10);
        waitingClientTimer.setTimerCallBack(
                null,
                (Callable) () -> {
                    lbWaitingTimer.setText("" + CustumDateTimeFormatter.secondsToMinutes(waitingClientTimer.getCurrentTick()));
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
    
    public String getSelectedButton1() {  
        for (Enumeration<AbstractButton> buttons = buttonGroup1.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                    return button.getText();
            }
        }
        return null;
    }
    
    public String getSelectedButton2() {  
        for (Enumeration<AbstractButton> buttons = buttonGroup2.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                    return button.getText();
            }
        }
        return null;
    }
    
    public String getSelectedButton3() {  
        for (Enumeration<AbstractButton> buttons = buttonGroup3.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                    return button.getText();
            }
        }
        return null;
    }
    
    public String getSelectedButton4() {  
        for (Enumeration<AbstractButton> buttons = buttonGroup4.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                    return button.getText();
            }
        }
        return null;
    }
    
    public void pauseTime () {
        // pause timer
        matchTimer.pause();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        jPanel5 = new javax.swing.JPanel();
        lbQuestion4 = new javax.swing.JLabel();
        answer4a = new javax.swing.JRadioButton();
        answer4b = new javax.swing.JRadioButton();
        answer4c = new javax.swing.JRadioButton();
        answer4d = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        lbQuestion3 = new javax.swing.JLabel();
        answer3a = new javax.swing.JRadioButton();
        answer3b = new javax.swing.JRadioButton();
        answer3c = new javax.swing.JRadioButton();
        answer3d = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        lbQuestion2 = new javax.swing.JLabel();
        answer2a = new javax.swing.JRadioButton();
        answer2b = new javax.swing.JRadioButton();
        answer2c = new javax.swing.JRadioButton();
        answer2d = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        lbQuestion1 = new javax.swing.JLabel();
        answer1a = new javax.swing.JRadioButton();
        answer1b = new javax.swing.JRadioButton();
        answer1c = new javax.swing.JRadioButton();
        answer1d = new javax.swing.JRadioButton();
        rectangleBackground2 = new view.RectangleBackground();
        win = new view.RectangleBackground();
        lose = new view.RectangleBackground();

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
        rectangleBackground1.setImage(new javax.swing.ImageIcon(getClass().getResource("/Static/SEXY PIXEL GIFS - GIFs.gif"))); // NOI18N

        panelPlayAgain.setBackground(new java.awt.Color(255, 255, 255));
        panelPlayAgain.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbWaitingTimer.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
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

        lbResult.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbResult.setForeground(new java.awt.Color(204, 0, 0));
        lbResult.setText("Bạn có muốn chơi lại không ? ");

        javax.swing.GroupLayout panelPlayAgainLayout = new javax.swing.GroupLayout(panelPlayAgain);
        panelPlayAgain.setLayout(panelPlayAgainLayout);
        panelPlayAgainLayout.setHorizontalGroup(
            panelPlayAgainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPlayAgainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbResult, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbWaitingTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnYes, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        panelPlayAgainLayout.setVerticalGroup(
            panelPlayAgainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPlayAgainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPlayAgainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnYes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNo, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelPlayAgainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbWaitingTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbResult, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rectangleBackground1.add(panelPlayAgain);
        panelPlayAgain.setBounds(90, 140, 800, 50);

        pbgTimer.setBackground(new java.awt.Color(255, 255, 255));
        pbgTimer.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        pbgTimer.setStringPainted(true);
        rectangleBackground1.add(pbgTimer);
        pbgTimer.setBounds(30, 190, 860, 50);
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
        btnSubmit.setBounds(780, 540, 120, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Static/timing.gif"))); // NOI18N
        rectangleBackground1.add(jLabel1);
        jLabel1.setBounds(910, 170, 87, 70);

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

        jPanel5.setBackground(new java.awt.Color(204, 0, 0));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbQuestion4.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbQuestion4.setForeground(new java.awt.Color(255, 255, 255));
        lbQuestion4.setText("4. CHUỖI SẮP XẾP 4");

        buttonGroup4.add(answer4a);
        answer4a.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        answer4a.setText("jRadioButton1");

        buttonGroup4.add(answer4b);
        answer4b.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        answer4b.setText("jRadioButton2");

        buttonGroup4.add(answer4c);
        answer4c.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        answer4c.setText("jRadioButton3");

        buttonGroup4.add(answer4d);
        answer4d.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        answer4d.setText("jRadioButton4");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbQuestion4, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(answer4a, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(answer4b, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(answer4c, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(answer4d, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbQuestion4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(answer4a)
                .addGap(18, 18, 18)
                .addComponent(answer4b)
                .addGap(18, 18, 18)
                .addComponent(answer4c)
                .addGap(18, 18, 18)
                .addComponent(answer4d)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rectangleBackground1.add(jPanel5);
        jPanel5.setBounds(730, 250, 180, 240);

        jPanel4.setBackground(new java.awt.Color(204, 0, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        lbQuestion3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbQuestion3.setForeground(new java.awt.Color(255, 255, 255));
        lbQuestion3.setText("3. CHUỖI SẮP XẾP 3");

        buttonGroup3.add(answer3a);
        answer3a.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        answer3a.setText("jRadioButton1");

        buttonGroup3.add(answer3b);
        answer3b.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        answer3b.setText("jRadioButton2");

        buttonGroup3.add(answer3c);
        answer3c.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        answer3c.setText("jRadioButton3");

        buttonGroup3.add(answer3d);
        answer3d.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        answer3d.setText("jRadioButton4");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbQuestion3, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(answer3a, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(answer3b, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(answer3c, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(answer3d, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbQuestion3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(answer3a)
                .addGap(18, 18, 18)
                .addComponent(answer3b)
                .addGap(18, 18, 18)
                .addComponent(answer3c)
                .addGap(18, 18, 18)
                .addComponent(answer3d)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rectangleBackground1.add(jPanel4);
        jPanel4.setBounds(520, 250, 167, 240);

        jPanel3.setBackground(new java.awt.Color(204, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbQuestion2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbQuestion2.setForeground(new java.awt.Color(255, 255, 255));
        lbQuestion2.setText("2. CHUỖI SẮP XẾP 2");

        buttonGroup2.add(answer2a);
        answer2a.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        answer2a.setText("jRadioButton1");

        buttonGroup2.add(answer2b);
        answer2b.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        answer2b.setText("jRadioButton2");

        buttonGroup2.add(answer2c);
        answer2c.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        answer2c.setText("jRadioButton3");

        buttonGroup2.add(answer2d);
        answer2d.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        answer2d.setText("jRadioButton4");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(answer2a, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(answer2b, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(answer2c, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(answer2d, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbQuestion2, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbQuestion2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(answer2a)
                .addGap(18, 18, 18)
                .addComponent(answer2b)
                .addGap(18, 18, 18)
                .addComponent(answer2c)
                .addGap(18, 18, 18)
                .addComponent(answer2d)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rectangleBackground1.add(jPanel3);
        jPanel3.setBounds(270, 250, 190, 240);

        jPanel2.setBackground(new java.awt.Color(204, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbQuestion1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbQuestion1.setForeground(new java.awt.Color(255, 255, 255));
        lbQuestion1.setText("1. CHUỖI SẮP XẾP 1");

        buttonGroup1.add(answer1a);
        answer1a.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        answer1a.setText("jRadioButton1");

        buttonGroup1.add(answer1b);
        answer1b.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        answer1b.setText("jRadioButton2");

        buttonGroup1.add(answer1c);
        answer1c.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        answer1c.setText("jRadioButton3");

        buttonGroup1.add(answer1d);
        answer1d.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        answer1d.setText("jRadioButton4");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbQuestion1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(answer1a, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(answer1b, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(answer1c, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(answer1d, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbQuestion1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(answer1a)
                .addGap(18, 18, 18)
                .addComponent(answer1b)
                .addGap(18, 18, 18)
                .addComponent(answer1c)
                .addGap(18, 18, 18)
                .addComponent(answer1d)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rectangleBackground1.add(jPanel2);
        jPanel2.setBounds(30, 250, 175, 240);
        rectangleBackground1.add(rectangleBackground2);
        rectangleBackground2.setBounds(190, 190, 100, 100);

        win.setBorderSize(0);
        win.setImage(new javax.swing.ImageIcon(getClass().getResource("/Static/kqwin.gif"))); // NOI18N
        rectangleBackground1.add(win);
        win.setBounds(270, 0, 300, 140);

        lose.setBorderSize(0);
        lose.setImage(new javax.swing.ImageIcon(getClass().getResource("/Static/khocthua.gif"))); // NOI18N
        rectangleBackground1.add(lose);
        lose.setBounds(470, 0, 300, 140);

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
        ClientRun.socketHandler.submitResult(competitor);
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
    
    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    public String getA4() {
        return a4;
    }

    public void setA4(String a4) {
        this.a4 = a4;
    }

   

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton answer1a;
    private javax.swing.JRadioButton answer1b;
    private javax.swing.JRadioButton answer1c;
    private javax.swing.JRadioButton answer1d;
    private javax.swing.JRadioButton answer2a;
    private javax.swing.JRadioButton answer2b;
    private javax.swing.JRadioButton answer2c;
    private javax.swing.JRadioButton answer2d;
    private javax.swing.JRadioButton answer3a;
    private javax.swing.JRadioButton answer3b;
    private javax.swing.JRadioButton answer3c;
    private javax.swing.JRadioButton answer3d;
    private javax.swing.JRadioButton answer4a;
    private javax.swing.JRadioButton answer4b;
    private javax.swing.JRadioButton answer4c;
    private javax.swing.JRadioButton answer4d;
    private javax.swing.JButton btnLeaveGame;
    private javax.swing.JButton btnNo;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnYes;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JLabel infoPLayer;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lbQuestion1;
    private javax.swing.JLabel lbQuestion2;
    private javax.swing.JLabel lbQuestion3;
    private javax.swing.JLabel lbQuestion4;
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
