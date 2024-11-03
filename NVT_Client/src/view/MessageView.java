/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import run.ClientRun;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
/**
 *
 * @author Ngọ Văn Trọng
 */
public class MessageView extends javax.swing.JFrame {
    String userChat = "";
    /**
     * Creates new form MessageView
     */
    public MessageView() {
        initComponents();
        setIcon();
        
        messageContainer.setOpaque(true);
        messageContainer.setBackground(new Color(255, 241, 241));
        messageContainer.setLayout(new BoxLayout(messageContainer, BoxLayout.Y_AXIS));
        // close window event
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(MessageView.this,
                        "Mày muốn rời khỏi chát Ư?", "Leave chat?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    ClientRun.socketHandler.leaveChat(userChat);
                    dispose();
                }
            }
        });
    }
     private void setIcon() {
        // Tạo ImageIcon từ tài nguyên
        ImageIcon icon = new ImageIcon(getClass().getResource("/Static/icon.png"));
        // Thiết lập icon cho JFrame
        setIconImage(icon.getImage());
    }

    public void setInfoUserChat (String username) {
        userChat = username;
        infoUserChat.setText( username);
    }


private JPanel createAvatarPanel(String imagePath) {
    JPanel avatarPanel = new JPanel() {
        private BufferedImage avatarImage;

        {
            // Load hình ảnh từ đường dẫn
            try {
                avatarImage = ImageIO.read(new File(imagePath));
            } catch (IOException e) {
                e.printStackTrace();
                avatarImage = null; // Nếu không tải được, để trống
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (avatarImage != null) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Vẽ ảnh avatar trong hình tròn
                int diameter = Math.min(getWidth(), getHeight()) - 2;
                int x = (getWidth() - diameter) / 2;
                int y = (getHeight() - diameter) / 2;

                // Tạo ảnh bo tròn
                Shape circle = new java.awt.geom.Ellipse2D.Double(x, y, diameter, diameter);
                g2d.setClip(circle);
                g2d.drawImage(avatarImage, x, y, diameter, diameter, null);
            }
        }
    };

    avatarPanel.setPreferredSize(new Dimension(45, 45)); // Đặt kích thước avatar
    avatarPanel.setOpaque(false);
    return avatarPanel;
}
 public JPanel recieveMessagePanel(String message, String time) {
    JPanel recieveMessagePanel = new JPanel();
    recieveMessagePanel.setLayout(new BoxLayout(recieveMessagePanel, BoxLayout.X_AXIS));

    // Avatar panel (trái)
     JPanel avatarPanel = createAvatarPanel("src/Static/son.png");

    // Tin nhắn (giữa) với bo góc
    RoundedPanel messagePanel = new RoundedPanel(15, new Color(255, 241, 241)); // Màu nền nhẹ, góc bo 15px
    messagePanel.setLayout(new BorderLayout());
    JLabel messageLabel = new JLabel("<html><div style='width: 250px;'>" + message + "</div></html>");
    messageLabel.setFont(new Font("Tahoma", Font.BOLD, 20)); // Đặt cỡ chữ 20 và in đậm
    messageLabel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Khoảng cách nội dung bên trong

    messagePanel.add(messageLabel, BorderLayout.CENTER);

    // Thời gian (phải)
    JLabel timeLabel = new JLabel(time);
    timeLabel.setForeground(Color.GRAY);
    timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

    // Thêm các thành phần vào recieveMessagePanel
    recieveMessagePanel.add(avatarPanel);
    recieveMessagePanel.add(Box.createRigidArea(new Dimension(10, 0)));
    recieveMessagePanel.add(messagePanel);
    recieveMessagePanel.add(Box.createRigidArea(new Dimension(10, 0)));
    recieveMessagePanel.add(timeLabel);

    recieveMessagePanel.setMaximumSize(new Dimension(480, recieveMessagePanel.getPreferredSize().height));
    recieveMessagePanel.setBackground(new Color(255, 241, 241));

    return recieveMessagePanel;
}

    
public JPanel sendMessagePanel(String message, String time) {
    JPanel sendMessagePanel = new JPanel();
    sendMessagePanel.setLayout(new BoxLayout(sendMessagePanel, BoxLayout.X_AXIS));

    // Tin nhắn (giữa) với bo góc
    RoundedPanel messagePanel = new RoundedPanel(15, new Color(235, 245, 255)); // Màu nền nhẹ, góc bo 15px
    messagePanel.setLayout(new BorderLayout());
    JLabel messageLabel = new JLabel("<html><div style='width: 250px; text-align: right;'>" + message + "</div></html>");
    messageLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
    messageLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
    messageLabel.setHorizontalAlignment(SwingConstants.RIGHT);

    messagePanel.add(messageLabel, BorderLayout.CENTER);

    // Thời gian (trái)
    JLabel timeLabel = new JLabel(time);
    timeLabel.setForeground(Color.GRAY);
    timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

    // Thêm các thành phần vào sendMessagePanel
    sendMessagePanel.add(timeLabel);
    sendMessagePanel.add(Box.createRigidArea(new Dimension(10, 0)));
    sendMessagePanel.add(messagePanel);
    sendMessagePanel.add(Box.createRigidArea(new Dimension(10, 0)));

    // Avatar panel (phải)
    JPanel avatarPanel = createAvatarPanel("src/Static/avatar.jpg");

    sendMessagePanel.add(avatarPanel);

    sendMessagePanel.setMaximumSize(new Dimension(480, sendMessagePanel.getPreferredSize().height));
    sendMessagePanel.setBackground(new Color(235, 245, 255));

    return sendMessagePanel;
}


    public void addRecieveMessage(String message, String time) {
        messageContainer.add(recieveMessagePanel(message, time));
        messageContainer.revalidate(); // Cập nhật layout
        messageContainer.repaint();
    }

    public void addSendMessage(String message, String time) {
        messageContainer.add(sendMessagePanel(message, time));
        messageContainer.revalidate(); // Cập nhật layout
        messageContainer.repaint();
    }
    
    public void eventSendMessage () {
        String message = tfMessage.getText().trim();
        if (message.equals("")) {
            tfMessage.grabFocus();
        } else {
            ClientRun.socketHandler.sendMessage(userChat, message);
            tfMessage.setText("");
            tfMessage.grabFocus();
        }
    }
    private static void customizeScrollBar(JScrollPane scrollPane){
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setBackground(Color.WHITE);
        verticalScrollBar.setUI(new javax.swing.plaf.basic.BasicScrollBarUI(){
            @Override
            protected void configureScrollBarColors(){
                this.thumbColor = new Color(236, 236, 236);
                this.trackColor = Color.WHITE;
            }
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton(); 
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }
        });
    }
    private void scrollToBottom() {
        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalScrollBar = ((JScrollPane) SwingUtilities.getAncestorOfClass(JScrollPane.class, messageContainer)).getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
        });
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfMessage = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        infoUserChat = new javax.swing.JLabel();
        btnLeaveChat = new javax.swing.JButton();
        messageContainer = new javax.swing.JPanel();
        imageAvatar1 = new view.ImageAvatar();
        jLabel1 = new javax.swing.JLabel();
        activeStatus1 = new view.ActiveStatus();
        jLabel2 = new javax.swing.JLabel();
        activeStatus2 = new view.ActiveStatus();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        tfMessage.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        tfMessage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfMessageKeyPressed(evt);
            }
        });

        btnSend.setBackground(new java.awt.Color(204, 0, 0));
        btnSend.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnSend.setForeground(new java.awt.Color(255, 255, 255));
        btnSend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Static/send (1).gif"))); // NOI18N
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        infoUserChat.setBackground(new java.awt.Color(204, 0, 0));
        infoUserChat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        btnLeaveChat.setBackground(new java.awt.Color(204, 0, 0));
        btnLeaveChat.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnLeaveChat.setForeground(new java.awt.Color(255, 255, 255));
        btnLeaveChat.setText("Exit");
        btnLeaveChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeaveChatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout messageContainerLayout = new javax.swing.GroupLayout(messageContainer);
        messageContainer.setLayout(messageContainerLayout);
        messageContainerLayout.setHorizontalGroup(
            messageContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        messageContainerLayout.setVerticalGroup(
            messageContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 264, Short.MAX_VALUE)
        );

        imageAvatar1.setBorderSize(0);
        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/Static/son.png"))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Static/message1.gif"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 2, 15)); // NOI18N
        jLabel2.setText("Online");

        activeStatus2.setActive(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(activeStatus2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(infoUserChat, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(activeStatus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(104, 104, 104))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnLeaveChat, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(messageContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imageAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnLeaveChat, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(infoUserChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(activeStatus1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(activeStatus2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tfMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLeaveChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeaveChatActionPerformed
        ClientRun.socketHandler.leaveChat(userChat);
        dispose();
    }//GEN-LAST:event_btnLeaveChatActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        eventSendMessage();
    }//GEN-LAST:event_btnSendActionPerformed

    private void tfMessageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfMessageKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            eventSendMessage();
        }
    }//GEN-LAST:event_tfMessageKeyPressed

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
            java.util.logging.Logger.getLogger(MessageView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MessageView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MessageView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MessageView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MessageView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.ActiveStatus activeStatus1;
    private view.ActiveStatus activeStatus2;
    private javax.swing.JButton btnLeaveChat;
    private javax.swing.JButton btnSend;
    private view.ImageAvatar imageAvatar1;
    private javax.swing.JLabel infoUserChat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel messageContainer;
    private javax.swing.JTextField tfMessage;
    // End of variables declaration//GEN-END:variables
}
