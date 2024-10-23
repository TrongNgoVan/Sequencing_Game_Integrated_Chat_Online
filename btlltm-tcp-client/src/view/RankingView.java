/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MEDIAMART PHU SON
 */
public class RankingView extends javax.swing.JFrame {
    
    /**
     * Creates new form RankingView
     */
    public RankingView() {
        initComponents();
        setIcon();
    }
   
     private void setIcon() {
        // Tạo ImageIcon từ tài nguyên
        ImageIcon icon = new ImageIcon(getClass().getResource("/Static/icon.png"));
        // Thiết lập icon cho JFrame
        setIconImage(icon.getImage());
    }

 public void setRanking(String rankingData) {
    // Tách dữ liệu xếp hạng thành từng dòng
    String[] lines = rankingData.split("\n");

    // Nếu không có dữ liệu xếp hạng, thông báo và thoát
    if (lines.length < 2) {
        return; // Không có thông tin xếp hạng
    }

    // Lấy DefaultTableModel từ JTable
    DefaultTableModel model = (DefaultTableModel) Ranking.getModel();

    // Xóa toàn bộ dữ liệu hiện tại trong bảng
    model.setRowCount(0);

    // Duyệt qua từng dòng dữ liệu (bỏ qua dòng tiêu đề)
    for (int i = 0; i < lines.length; i++) {
        String line = lines[i];
        String[] data = line.split("\t");
        
        // Kiểm tra số lượng dữ liệu để tránh lỗi
        if (data.length >= 5) {
            // Thêm dòng mới vào bảng
            model.addRow(new Object[]{i+1, data[1], data[2], data[3], data[4]});
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

        jDesktopPane1 = new javax.swing.JDesktopPane();
        panel1 = new java.awt.Panel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Ranking = new javax.swing.JTable();

        jDesktopPane1.setBackground(new java.awt.Color(204, 0, 0));
        jDesktopPane1.setForeground(new java.awt.Color(204, 51, 0));
        jDesktopPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 76, Short.MAX_VALUE)
        );

        panel1.setLayout(new java.awt.BorderLayout());

        Ranking.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        Ranking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "RANK", "USER", "ĐIỂM", "ĐỐI THỦ TRUNG BÌNH", "THỜI GIAN TRUNG BÌNH"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Ranking.setGridColor(new java.awt.Color(153, 153, 153));
        Ranking.setMaximumSize(new java.awt.Dimension(2147483647, 800));
        Ranking.setMinimumSize(new java.awt.Dimension(200, 800));
        Ranking.setRowHeight(30);
        jScrollPane1.setViewportView(Ranking);
        if (Ranking.getColumnModel().getColumnCount() > 0) {
            Ranking.getColumnModel().getColumn(0).setHeaderValue("RANK");
            Ranking.getColumnModel().getColumn(1).setHeaderValue("USER");
            Ranking.getColumnModel().getColumn(2).setHeaderValue("ĐIỂM");
            Ranking.getColumnModel().getColumn(3).setHeaderValue("ĐỐI THỦ TRUNG BÌNH");
            Ranking.getColumnModel().getColumn(4).setHeaderValue("THỜI GIAN TRUNG BÌNH");
        }
        Ranking.getTableHeader().setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 18));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Áp dụng renderer cho từng cột
        for (int i = 0; i < Ranking.getColumnCount(); i++) {
            Ranking.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Tăng chiều cao của header
        Ranking.getTableHeader().setPreferredSize(new java.awt.Dimension(Ranking.getTableHeader().getPreferredSize().width, 40));

        panel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1302, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
       
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
            java.util.logging.Logger.getLogger(RankingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RankingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RankingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RankingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RankingView().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Ranking;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Panel panel1;
    // End of variables declaration//GEN-END:variables
}
