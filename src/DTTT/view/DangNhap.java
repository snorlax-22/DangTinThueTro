package DTTT.view;
import DTTT.dao.KiemTraDN;
import DTTT.controller.ChuyenManHinh;
import DTTT.dao.KTTK;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;



public class DangNhap extends javax.swing.JPanel {

    public DangNhap() throws IOException {
        initComponents();
            this.setVisible(true);
                Action action = new AbstractAction()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                         try {
                            KiemTraDN dn = null;
                            String mk = new String(jpwMK.getPassword());
                            int i=dn.DangNhapTK(jtfTTK.getText(),mk);
                            if(i==1){
                                jpwMK.setText("");
                            }
                            else if(i==2){
                                ChuyenManHinh cmh = new ChuyenManHinh(jPanel1);
                                cmh.setView(new TrangChinh());
                                KTTK.setTtk(jtfTTK.getText()); 
                                
                            }
                            else if(i==3){
                                jtfTTK.setText("");
                                jpwMK.setText("");
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                };
                jpwMK.addActionListener( action );
                
                jbtnQuenMK.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        QuenMatKhau quenMK = new QuenMatKhau();
                            quenMK.setVisible(false);
                            quenMK.setBounds(10, 120, 650, 500);
                            quenMK.setLocationRelativeTo(null);
                            quenMK.setVisible(true);
                        
                    }
                
                });
                jbtDangNhap.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            KiemTraDN dn = null;
                            String mk = new String(jpwMK.getPassword());
                            int i=dn.DangNhapTK(jtfTTK.getText(),mk);
                            if(i==1){
                                jpwMK.setText("");
                            }
                            else if(i==2){
                                ChuyenManHinh cmh = new ChuyenManHinh(jPanel1);
                                cmh.setView(new TrangChinh());
                                KTTK.setTtk(jtfTTK.getText()); 
                            }
                            else if(i==3){
                                jtfTTK.setText("");
                                jpwMK.setText("");
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                jbtThemTK.addActionListener(new ActionListener(){ 
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ThemTKJDialog themTK = new ThemTKJDialog(null, true);
                        themTK.setTitle("Thêm tài khoản");
                        themTK.setResizable(true);
                        themTK.setLocationRelativeTo(null);
                        themTK.setVisible(true);
                        if(themTK.kq == 1){
                            ChuyenManHinh cmh = new ChuyenManHinh(jPanel1);
                            cmh.setView(new theme());
                            KTTK.setTtk(themTK.ttk);
                        }
                    }
                });
                jbtnEye.addActionListener(new ActionListener() {
//                ImageIcon onEye = new ImageIcon("eyeOnBlack.png");
//                ImageIcon offEye = new ImageIcon("eyeOffBlack.png");
                Image onEye = ImageIO.read(getClass().getResource("eyeOnBlack.png"));
                Image offEye = ImageIO.read(getClass().getResource("eyeOffBlack.png"));
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(jbtnEye.isSelected()){
                        jbtnEye.setIcon(new ImageIcon(onEye));
                        jpwMK.setEchoChar((char)0);
                        jpwMK.requestFocus();
                    }else{
                        jbtnEye.setIcon(new ImageIcon(offEye));
                        jpwMK.setEchoChar('*');
                        jpwMK.requestFocus();
                    }
                    
                }

            
            });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jpnTaiKhoan = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jtfTTK = new javax.swing.JTextField();
        jpnMatKhau = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jpwMK = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbtDangNhap = new javax.swing.JButton();
        jbtThemTK = new javax.swing.JButton();
        jbtnQuenMK = new javax.swing.JButton();
        jbtnEye = new javax.swing.JToggleButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1060, 564));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setPreferredSize(new java.awt.Dimension(1060, 564));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setVerifyInputWhenFocusTarget(false);

        jPanel3.setBackground(new java.awt.Color(248, 248, 255));

        jpnTaiKhoan.setBackground(new java.awt.Color(242, 242, 242));
        jpnTaiKhoan.setPreferredSize(new java.awt.Dimension(634, 60));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setText("Tên tài khoản:");

        jtfTTK.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jtfTTK.setBorder(null);
        jtfTTK.setPreferredSize(new java.awt.Dimension(468, 37));

        javax.swing.GroupLayout jpnTaiKhoanLayout = new javax.swing.GroupLayout(jpnTaiKhoan);
        jpnTaiKhoan.setLayout(jpnTaiKhoanLayout);
        jpnTaiKhoanLayout.setHorizontalGroup(
            jpnTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTaiKhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfTTK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnTaiKhoanLayout.setVerticalGroup(
            jpnTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnTaiKhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfTTK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jpnMatKhau.setBackground(new java.awt.Color(242, 242, 242));
        jpnMatKhau.setPreferredSize(new java.awt.Dimension(634, 60));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Mật khẩu:");
        jLabel5.setPreferredSize(new java.awt.Dimension(142, 37));

        jpwMK.setBorder(null);
        jpwMK.setPreferredSize(new java.awt.Dimension(468, 37));
        jpwMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpwMKActionPerformed(evt);
            }
        });
        jpwMK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jpwMKKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jpnMatKhauLayout = new javax.swing.GroupLayout(jpnMatKhau);
        jpnMatKhau.setLayout(jpnMatKhauLayout);
        jpnMatKhauLayout.setHorizontalGroup(
            jpnMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMatKhauLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpwMK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnMatKhauLayout.setVerticalGroup(
            jpnMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMatKhauLayout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addGroup(jpnMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jpwMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 54, 128));
        jPanel2.setPreferredSize(new java.awt.Dimension(634, 70));

        jLabel1.setBackground(new java.awt.Color(0, 54, 128));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ĐĂNG NHẬP");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jbtDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        jbtDangNhap.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jbtDangNhap.setText("Đăng Nhập");
        jbtDangNhap.setPreferredSize(new java.awt.Dimension(140, 50));
        jbtDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtDangNhapMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtDangNhapMouseEntered(evt);
            }
        });
        jbtDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDangNhapActionPerformed(evt);
            }
        });
        jbtDangNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbtDangNhapKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jbtDangNhapKeyTyped(evt);
            }
        });

        jbtThemTK.setBackground(new java.awt.Color(255, 255, 255));
        jbtThemTK.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jbtThemTK.setText("Đăng Ký");
        jbtThemTK.setMaximumSize(new java.awt.Dimension(127, 31));
        jbtThemTK.setMinimumSize(new java.awt.Dimension(127, 31));
        jbtThemTK.setPreferredSize(new java.awt.Dimension(140, 50));
        jbtThemTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtThemTKMouseClicked(evt);
            }
        });
        jbtThemTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtThemTKActionPerformed(evt);
            }
        });

        jbtnQuenMK.setBackground(new java.awt.Color(255, 255, 255));
        jbtnQuenMK.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jbtnQuenMK.setText("Quên mật khẩu");
        jbtnQuenMK.setPreferredSize(new java.awt.Dimension(140, 50));
        jbtnQuenMK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnQuenMKMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtnQuenMKMouseEntered(evt);
            }
        });
        jbtnQuenMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnQuenMKActionPerformed(evt);
            }
        });
        jbtnQuenMK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbtnQuenMKKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jbtnQuenMKKeyTyped(evt);
            }
        });

        jbtnEye.setBackground(new java.awt.Color(248, 248, 255));
        jbtnEye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTTT/view/eyeOffBlack.png"))); // NOI18N
        jbtnEye.setBorder(null);
        jbtnEye.setOpaque(true);
        jbtnEye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEyeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(257, 257, 257)
                .addComponent(jbtnQuenMK, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(285, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnEye, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jbtDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(354, 354, 354)
                            .addComponent(jbtThemTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jpnMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpnTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(65, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addComponent(jbtnEye, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jbtnQuenMK, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(8, 8, 8)
                    .addComponent(jpnTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                    .addComponent(jpnMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbtDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbtThemTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(194, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(157, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(225, 225, 225))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void jbtDangNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtDangNhapMouseClicked
        
    }//GEN-LAST:event_jbtDangNhapMouseClicked

    private void jbtThemTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtThemTKMouseClicked

        
    }//GEN-LAST:event_jbtThemTKMouseClicked

    private void jbtDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDangNhapActionPerformed
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_jbtDangNhapActionPerformed

    private void jbtThemTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtThemTKActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jbtThemTKActionPerformed

    private void jbtDangNhapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbtDangNhapKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtDangNhapKeyPressed

    private void jbtDangNhapMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtDangNhapMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtDangNhapMouseEntered

    private void jbtDangNhapKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbtDangNhapKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtDangNhapKeyTyped

    private void jpwMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpwMKActionPerformed
        // TODO add your handling code here:
        this.jbtDangNhap.requestFocus();
    }//GEN-LAST:event_jpwMKActionPerformed

    private void jpwMKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jpwMKKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jpwMKKeyPressed

    private void jbtnQuenMKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnQuenMKMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnQuenMKMouseClicked

    private void jbtnQuenMKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnQuenMKMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnQuenMKMouseEntered

    private void jbtnQuenMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnQuenMKActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jbtnQuenMKActionPerformed

    private void jbtnQuenMKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbtnQuenMKKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnQuenMKKeyPressed

    private void jbtnQuenMKKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbtnQuenMKKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnQuenMKKeyTyped

    private void jbtnEyeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEyeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnEyeActionPerformed
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jbtDangNhap;
    private javax.swing.JButton jbtThemTK;
    private javax.swing.JToggleButton jbtnEye;
    private javax.swing.JButton jbtnQuenMK;
    private javax.swing.JPanel jpnMatKhau;
    private javax.swing.JPanel jpnTaiKhoan;
    private javax.swing.JPasswordField jpwMK;
    private javax.swing.JTextField jtfTTK;
    // End of variables declaration//GEN-END:variables


}
