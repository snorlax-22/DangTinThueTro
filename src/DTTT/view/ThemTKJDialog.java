package DTTT.view;
import DTTT.dao.KTTK;
import DTTT.dao.TaiKhoanDB;
import javax.swing.JOptionPane;
import DTTT.model.ChuanHoa;
import DTTT.model.TaiKhoan;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ThemTKJDialog extends javax.swing.JDialog {

    int kq = 0;
    String ttk = "";
    public ThemTKJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jbtQuayLai.setVisible(true);
        jbtQuayLai.setVisible(true);
        
    }
    private void jbtDangKiMouseClicked(java.awt.event.MouseEvent evt) {                                       
        TaiKhoan tk = new TaiKhoan();
        String mk = new String(jpwMK.getPassword());
        String xnmk = new String(jpwXNMK.getPassword());
        jtfHoTen5.setText(ChuanHoa.ChuanHoaHoTen(jtfHoTen5.getText()));
        if(jtfHoTen5.getText().length()==0){
            JOptionPane.showMessageDialog(null,"Không được để trống 'Họ Tên'");
        }
        else if(jtfSDT.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Không được để trống 'Số Điện Thoại'");
        }
        else if(jtfTenDN.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Không được để trống 'Tên Đăng Nhập'");
            jtfTenDN.setText("");
        }
        else if(jtftGmail.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Không được để trống 'Gmail'");
            jtftGmail.setText("");
        }
        else if(mk.length() == 0){
            JOptionPane.showMessageDialog(null,"Không được để trống 'Mật khẩu'");
        }
         else if(xnmk.length() == 0){
            JOptionPane.showMessageDialog(null,"Không được để trống 'Xác nhận mật khẩu'");
        }
//         
//        else if(!jtfHoTen5.getText().matches("^[a-zA-Z ]{1,29}$")){
//            JOptionPane.showMessageDialog(null,"Họ tên không hợp lệ!");
//            jtfHoTen5.setText("");
//        }
        else if(!jtftGmail.getText().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")){
            JOptionPane.showMessageDialog(null,"Mail không hợp lệ!");
            jtftGmail.setText("");
        }
        else if(!ChuanHoa.ChuanHoaSDT(jtfSDT.getText())){
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!");
            jtfSDT.setText("");
        }
        else if(jtfTenDN.getText().length()<4){
            JOptionPane.showMessageDialog(null, "Tên tài khoản quá ngắn!");
            jtfTenDN.setText("");
        }
        else if(mk.length()<6){
            JOptionPane.showMessageDialog(null, "Mật khẩu phải có ít nhất 6 kí tự!");
            jpwMK.setText("");
            jpwXNMK.setText("");
        }
        else if(mk.compareToIgnoreCase(xnmk)!=0){
            JOptionPane.showMessageDialog(null, "Mật khẩu không khớp!");
            jpwMK.setText("");
            jpwXNMK.setText("");
        }
        else{
            tk.setHoTen(jtfHoTen5.getText());
            tk.setSdt(jtfSDT.getText());
            tk.setTenTK(jtfTenDN.getText());
            tk.setMk(mk);
            tk.setGmail(jtftGmail.getText());
            
            try {
                if(TaiKhoanDB.kTTKTonTai(tk.getTenTK())==0){
                    TaiKhoanDB.themTaiKhoan(tk);
                    JOptionPane.showMessageDialog(null, "Đã thêm tài khoản!");
                    this.dispose();
                    kq=1;
                    ttk = tk.getTenTK();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Tên TK đã được sử dụng! Vui lòng dùng tên TK khác");
                    jtfTenDN.setText("");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThemTKJDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }
    }
    
//    private void jtfSDTKeyTyped(java.awt.event.KeyEvent evt) {                                
//        char c = evt.getKeyChar();
//        if(!Character.isDigit(c)){
//            evt.consume();
//        }
//        if(jtfSDT.getText().length() >= 10){
//
//            evt.consume();
//        }
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jtfHoTen5 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtfSDT = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jtfTenDN = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jpwMK = new javax.swing.JPasswordField();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jpwXNMK = new javax.swing.JPasswordField();
        jbtDangKi1 = new javax.swing.JButton();
        jbtQuayLai = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jtftGmail = new javax.swing.JTextField();

        jLabel6.setText("jLabel6");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTTT/images/house.png"))); // NOI18N

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(242, 242, 255));
        jPanel10.setPreferredSize(new java.awt.Dimension(550, 50));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setText("Họ tên:");

        jtfHoTen5.setBorder(null);
        jtfHoTen5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfHoTen5FocusLost(evt);
            }
        });
        jtfHoTen5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfHoTen5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtfHoTen5, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfHoTen5, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(242, 242, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(550, 50));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Số điện thoại:");

        jtfSDT.setBorder(null);
        jtfSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfSDTKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtfSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(242, 242, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(550, 50));
        jPanel3.setVerifyInputWhenFocusTarget(false);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Tên tài khoản:");

        jtfTenDN.setBorder(null);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtfTenDN, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtfTenDN)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(242, 242, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(550, 50));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Mật khẩu:");

        jpwMK.setBorder(null);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpwMK, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jpwMK))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(242, 242, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(550, 50));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Xác nhận mật khẩu:");
        jLabel5.setPreferredSize(new java.awt.Dimension(140, 17));

        jpwXNMK.setBorder(null);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpwXNMK, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jpwXNMK))
                .addContainerGap())
        );

        jbtDangKi1.setBackground(new java.awt.Color(242, 242, 242));
        jbtDangKi1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jbtDangKi1.setText("Đăng kí");
        jbtDangKi1.setBorder(null);
        jbtDangKi1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtDangKi1MouseClicked(evt);
            }
        });
        jbtDangKi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDangKi1ActionPerformed(evt);
            }
        });

        jbtQuayLai.setBackground(new java.awt.Color(242, 242, 242));
        jbtQuayLai.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jbtQuayLai.setText("Quay lại");
        jbtQuayLai.setBorder(null);
        jbtQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtQuayLaiActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(242, 242, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(550, 50));
        jPanel8.setVerifyInputWhenFocusTarget(false);

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("Gmail:");

        jtftGmail.setBorder(null);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtftGmail, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtftGmail)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                    .addComponent(jbtQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbtDangKi1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtDangKi1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtDangKi1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtDangKi1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtDangKi1MouseClicked

    private void jbtQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtQuayLaiActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jbtQuayLaiActionPerformed

    private void jbtDangKi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDangKi1ActionPerformed
        // TODO add your handling code here:
        TaiKhoan tk = new TaiKhoan();
        String mk = new String(jpwMK.getPassword());
        String xnmk = new String(jpwXNMK.getPassword());
        jtfHoTen5.setText(ChuanHoa.ChuanHoaHoTen(jtfHoTen5.getText()));
        System.out.println("");
        if(jtfHoTen5.getText().length()==0){
            JOptionPane.showMessageDialog(null,"Không được để trống 'Họ Tên'");
        }
        else if(jtfSDT.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Không được để trống 'Số Điện Thoại'");
        }
        else if(jtfTenDN.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Không được để trống 'Tên Đăng Nhập'");
            jtfTenDN.setText("");
        }
        else if(mk.length() == 0){
            JOptionPane.showMessageDialog(null,"Không được để trống 'Mật khẩu'");
        }
         else if(xnmk.length() == 0){
            JOptionPane.showMessageDialog(null,"Không được để trống 'Xác nhận mật khẩu'");
        }
//        else if(!jtfHoTen5.getText().matches("^[a-zA-Z ]{1,29}$")){
//            JOptionPane.showMessageDialog(null,"Họ tên không hợp lệ!");
//            jtfHoTen5.setText("");
//        }
        else if(!jtftGmail.getText().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")){
            JOptionPane.showMessageDialog(null,"Mail không hợp lệ!");
            jtftGmail.setText("");
        }
        else if(!ChuanHoa.ChuanHoaSDT(jtfSDT.getText())){
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!");
            jtfSDT.setText("");
        }
        else if(jtfTenDN.getText().length()<4){
            JOptionPane.showMessageDialog(null, "Tên tài khoản quá ngắn!");
            jtfTenDN.setText("");
        }
        else if(mk.length()<6){
            JOptionPane.showMessageDialog(null, "Mật khẩu phải có ít nhất 6 kí tự!");
            jpwMK.setText("");
            jpwXNMK.setText("");
        }
        else if(mk.compareToIgnoreCase(xnmk)!=0){
            JOptionPane.showMessageDialog(null, "Mật khẩu không khớp!");
            jpwMK.setText("");
            jpwXNMK.setText("");
        }
        else{
            tk.setHoTen(jtfHoTen5.getText());
            tk.setSdt(jtfSDT.getText());
            tk.setTenTK(jtfTenDN.getText());
            tk.setMk(mk);
            tk.setGmail(jtftGmail.getText());
            try {
                if(TaiKhoanDB.kTTKTonTai(tk.getTenTK())==0){
                    TaiKhoanDB.themTaiKhoan(tk);
                    JOptionPane.showMessageDialog(null, "Đã thêm tài khoản!");
                    this.dispose();
                    kq=1;
                    ttk = tk.getTenTK();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Tên TK đã được sử dụng! Vui lòng dùng tên TK khác");
                    jtfTenDN.setText("");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThemTKJDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }
    }//GEN-LAST:event_jbtDangKi1ActionPerformed

    private void jtfSDTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfSDTKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!Character.isDigit(c)){
            evt.consume();
        }
        if(jtfSDT.getText().length() >= 10){

            evt.consume();
        }
    }//GEN-LAST:event_jtfSDTKeyTyped

    private void jtfHoTen5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfHoTen5ActionPerformed
        // TODO add your handling code here:
        String chuanHoa = ChuanHoa.ChuanHoaHoTen(jtfHoTen5.getText());
        jtfHoTen5.setText(chuanHoa);
    }//GEN-LAST:event_jtfHoTen5ActionPerformed

    private void jtfHoTen5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfHoTen5FocusLost
        // TODO add your handling code here:
        String chuanHoa = ChuanHoa.ChuanHoaHoTen(jtfHoTen5.getText());
        jtfHoTen5.setText(chuanHoa);
    }//GEN-LAST:event_jtfHoTen5FocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JButton jbtDangKi1;
    private javax.swing.JButton jbtQuayLai;
    private javax.swing.JPasswordField jpwMK;
    private javax.swing.JPasswordField jpwXNMK;
    private javax.swing.JTextField jtfHoTen5;
    private javax.swing.JTextField jtfSDT;
    private javax.swing.JTextField jtfTenDN;
    private javax.swing.JTextField jtftGmail;
    // End of variables declaration//GEN-END:variables
}
