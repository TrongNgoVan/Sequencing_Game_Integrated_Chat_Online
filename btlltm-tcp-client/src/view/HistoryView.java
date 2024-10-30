/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Ngọ Văn Trọng
 */
public class HistoryView extends javax.swing.JFrame {

    /**
     * Creates new form HistoryView
     */
    public HistoryView() {
        initComponents();
        setIcon();
    }
     private void setIcon() {
        // Tạo ImageIcon từ tài nguyên
        ImageIcon icon = new ImageIcon(getClass().getResource("/Static/icon.png"));
        // Thiết lập icon cho JFrame
        setIconImage(icon.getImage());
    }
    public void setHistory(String result, String username) {
        // Tách các mục lịch sử dựa trên dấu phân cách "|"
        String[] historyEntries = result.split("\\|");

        // Lấy DefaultTableModel từ JTable trong `HistoryView`
        DefaultTableModel model = (DefaultTableModel) History.getModel();

        // Xóa toàn bộ dữ liệu hiện tại trong bảng
        model.setRowCount(0);

        // Duyệt qua từng mục lịch sử
        for (String entry : historyEntries) {
            String[] details = entry.split("/\\s*"); // Tách thông tin mỗi entry bằng dấu "/"

            // Kiểm tra số lượng dữ liệu để tránh lỗi
            if (details.length >= 7) {
                // Thay thế ký tự 'T' bằng khoảng trắng trong mục thông tin ngày giờ
                String formattedTime = details[5].replace("T", " "); // Thay 'T' bằng khoảng trắng

                // Xác định người chơi và điểm của mình
                String opponent;
                String myScore;
                String opponentScore;
                String resultText;

                // Lấy điểm của user1 và user2, thay thế ',' bằng '.'
                float score1 = Float.parseFloat(details[4].replace(',', '.')); // Điểm của user1
                float score2 = Float.parseFloat(details[6].replace(',', '.')); // Điểm của user2

                if (details[1].equals(username)) {
                    opponent = details[2]; // Nếu user1 là username, đối thủ là user2
                    // Kiểm tra kết quả
                    if (details[3].equals(username)) { // Thắng
                        resultText = "Thắng";
                        myScore = String.valueOf(Math.max(score1, score2));
                        opponentScore = String.valueOf(Math.min(score1, score2));
                    } else if ("DRAW".equals(details[3])) { // Hòa
                        resultText = "Hòa";
                        myScore = String.valueOf(score1);
                        opponentScore = String.valueOf(score2);
                    } else { // Thua
                        resultText = "Thua";
                        myScore = String.valueOf(Math.min(score1, score2));
                        opponentScore = String.valueOf(Math.max(score1, score2));
                    }
                } else if (details[2].equals(username)) {
                    opponent = details[1]; // Nếu user2 là username, đối thủ là user1
                    // Kiểm tra kết quả
                    if (details[3].equals(username)) { // Thắng
                        resultText = "Thắng";
                        myScore = String.valueOf(Math.max(score1, score2));
                        opponentScore = String.valueOf(Math.min(score1, score2));
                    } else if ("DRAW".equals(details[3])) { // Hòa
                        resultText = "Hòa";
                        myScore = String.valueOf(score2);
                        opponentScore = String.valueOf(score1);
                    } else { // Thua
                        resultText = "Thua";
                        myScore = String.valueOf(Math.min(score1, score2));
                        opponentScore = String.valueOf(Math.max(score1, score2));
                    }
                } else {
                    opponent = "Unknown"; // Nếu không tìm thấy
                    myScore = "0"; // Điểm của bạn
                    opponentScore = "0"; // Điểm của đối thủ
                    resultText = "Unknown"; // Kết quả không xác định
                }

                // Thêm dòng mới vào bảng với từng mục thông tin
                model.addRow(new Object[]{
                    formattedTime, // Ngày
                    details[0],    // ID trận
                    opponent,      // Đối thủ
                    resultText,    // Kết quả trận đấu
                    myScore,       // Điểm của bạn
                    opponentScore   // Điểm đối thủ
                });
            }
        }

   
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        History = new javax.swing.JTable();
        exit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jDesktopPane1.setBackground(new java.awt.Color(204, 0, 0));
        jDesktopPane1.setForeground(new java.awt.Color(204, 0, 0));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Lịch Sử Đấu");

        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(635, 635, 635)
                .addComponent(jLabel1)
                .addContainerGap(654, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        History.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        History.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Thời điểm", "ID Trận đấu", "Đối thủ", "Kết quả trận", "Điểm của bạn", "Điểm đối thủ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        History.setRowHeight(32);
        jScrollPane1.setViewportView(History);
        if (History.getColumnModel().getColumnCount() > 0) {
            History.getColumnModel().getColumn(5).setResizable(false);
        }
        History.getTableHeader().setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 18));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Áp dụng renderer cho từng cột
        for (int i = 0; i < History.getColumnCount(); i++) {
            History.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Tăng chiều cao của header
        History.getTableHeader().setPreferredSize(new java.awt.Dimension(History.getTableHeader().getPreferredSize().width, 40));

        exit.setBackground(new java.awt.Color(204, 0, 0));
        exit.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        exit.setForeground(new java.awt.Color(255, 255, 255));
        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Static/exit.png"))); // NOI18N
        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exit)
                .addGap(36, 36, 36))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_exitActionPerformed

    /**
     * @param args the command line arguments
     */

    

    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HistoryView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable History;
    private javax.swing.JButton exit;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
