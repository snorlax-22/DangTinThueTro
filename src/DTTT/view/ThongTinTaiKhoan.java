/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTTT.view;

/**
 *
 * @author huynhkhanh
 */

import DTTT.dao.KTTK;
import DTTT.dao.TaiKhoanDB;
import DTTT.model.ChuanHoa;
import DTTT.model.TaiKhoan;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public final class ThongTinTaiKhoan extends javax.swing.JDialog {

    /**
     * Creates new form ThongTinTaiKhoan
     */
    public TaiKhoan tk = new TaiKhoan();

    public ThongTinTaiKhoan() {
        initComponents();
        setView(jPanel1, 510, 230);
    }
  
    public int Dispose_On_Close(){
        jPanel6.removeAll();
        jPanel2.setVisible(false);
        setView(jPanel1, 510,230);
        return DISPOSE_ON_CLOSE;
    }
    public void setView(JPanel jpn, int w, int h){
        this.setBounds(90,90,w,h);
        this.setLocationRelativeTo(null);
        jPanel6.removeAll();
        jPanel6.setLayout(new BorderLayout());
        jPanel6.add(jpn, BorderLayout.CENTER);
        jPanel6.validate();
        jPanel6.repaint();
    }
    
    public ThongTinTaiKhoan(String taiKhoan) throws IOException, SQLException {
        initComponents();
//        this.setDefaultCloseOperation(Dispose_On_Close());

        setView(jPanel1, 510, 230);
            tk = TaiKhoanDB.luuTTTaiKhoan(taiKhoan);
            jTxtHoTen.setText(tk.getHoTen());
            jTxtTaiKhoan.setText(tk.getTenTK());
            jTxtSDT.setText(tk.getSdt());
            jTxtGmail.setText(tk.getGmail());
            this.setTitle("Thông tin tài khoản");
        jbtnChinhSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setView(jPanel2, 520, 400);
                jTxtHoTen1.setText(tk.getHoTen());
                jTxtTaiKhoan1.setText(tk.getTenTK());
                jTxtSDT1.setText(tk.getSdt());
                jTxtGmail1.setText(tk.getGmail());
            }
        });
            
            
            
            
            jbtnEye.addActionListener(new ActionListener() {
                Image onEye = ImageIO.read(getClass().getResource("eyeOnBlack.png"));
                Image offEye = ImageIO.read(getClass().getResource("eyeOffBlack.png"));
                @Override
                public void actionPerformed(ActionEvent e) {
                    Container eye = getContentPane();
                    if(jbtnEye.isSelected()){
                        jbtnEye.setIcon(new ImageIcon(onEye));
                        jpwOld.setEchoChar((char)0);
                        jPwNew.setEchoChar((char)0);
                        jPwNewAgain.setEchoChar((char)0);
                    }else{
                        jbtnEye.setIcon(new ImageIcon(offEye));
                        jpwOld.setEchoChar('*');
                        jPwNew.setEchoChar('*');
                        jPwNewAgain.setEchoChar('*');
                    }
                    
                }
            });
            jbtnLuu.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if(ktTT() == true){
                            int a = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thay đổi thông tin không?");
                            if(a == JOptionPane.YES_OPTION){
                                String mkNew = new String(jPwNew.getPassword());
                                tk.setHoTen(jTxtHoTen.getText());
                                tk.setSdt(jTxtSDT.getText());
                                tk.setGmail(jTxtGmail.getText());
                                tk.setMk(mkNew);
                                JOptionPane.showMessageDialog(null, "Đã lưu thay đổi!");
                                TaiKhoanDB.updateTaiKhoan(tk);
                                Container frame = jbtnLuu.getParent();
                                do{
                                    frame = frame.getParent(); 
                                }while (!(frame instanceof ThongTinTaiKhoan));  
                                ((ThongTinTaiKhoan) frame).dispose();
                            }
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
            
            });
            jbtnQuayLai1.addActionListener(new ActionListener(){
            @Override
                public void actionPerformed(ActionEvent e) {
                    setView(jPanel1, 510,230);
                }
                
            });
            jbtnQuayLai.addActionListener(new ActionListener(){
            @Override
                public void actionPerformed(ActionEvent e) {
//                    Container frame = jbtnQuayLai.getParent();
//                    do{
//                        frame = frame.getParent(); 
//                    }while (!(frame instanceof ThongTinTaiKhoan));  
//                    ((ThongTinTaiKhoan) frame).dispose();
                }
                
            });
            
    }

public boolean ktTT() throws SQLException{
    String mkOld = new String(jpwOld.getPassword());
    String mkNew = new String(jPwNew.getPassword());
    String mkNewAgain = new String(jPwNewAgain.getPassword());
        if(jTxtHoTen.getText().length() == 0 || jTxtTaiKhoan.getText().length() == 0 || jTxtSDT.getText().length() ==0
                || jTxtGmail.getText().length() ==0  || mkOld.length() == 0
                || mkNew.length() == 0|| mkNewAgain.length() == 0
                ){
            if(jTxtHoTen.getText().length() == 0){
                JOptionPane.showMessageDialog(null,"Vui lòng nhập 'Họ tên'!");
            }
            else if(jTxtSDT.getText().length() == 0){
                JOptionPane.showMessageDialog(null,"Vui lòng nhập 'Số điện thoại'!");
            }
            else if(jTxtGmail.getText().length() == 0){
                JOptionPane.showMessageDialog(null,"Vui lòng nhập 'Email'!");
            }
            else if(mkOld.length() == 0){
                JOptionPane.showMessageDialog(null,"Vui lòng nhập 'Mật khẩu' trước đó!");
            }
            else if(mkNew.length() == 0){
                JOptionPane.showMessageDialog(null,"Vui lòng nhập 'Mật khẩu mới'!");
            }
            else if(mkNewAgain.length() == 0){
                JOptionPane.showMessageDialog(null,"Vui lòng nhập xác nhận 'Mật khẩu mới'!");
            }
            return false;
        }
        else {
            if (!ChuanHoa.ChuanHoaSDT(jTxtSDT.getText())) {
                JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!");
                jTxtSDT.requestFocus();
                jTxtSDT.setText("");
            }
            else if(!jTxtGmail.getText().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")){
                JOptionPane.showMessageDialog(null,"Mail không hợp lệ!");
                jTxtGmail.requestFocus();
                jTxtGmail.setText("");
            }
            else if(mkNew.length()<6){
                JOptionPane.showMessageDialog(null, "Mật khẩu phải có ít nhất 6 kí tự!");
                jPwNew.setText("");
            }
            else if(!mkNewAgain.equals(mkNew)){
                JOptionPane.showMessageDialog(null, "Mật khẩu không giống nhau!");
            }else if(!TaiKhoanDB.KTTTTaiKhoan(jTxtTaiKhoan.getText(), mkOld)){
                JOptionPane.showMessageDialog(null, "Mật khẩu cũ không đúng!");
            }else if(mkOld.equals(mkNewAgain)){
                JOptionPane.showMessageDialog(null, "Mật khẩu mới vừa nhập trùng với mật khẩu cũ!");
                jPwNew.requestFocus();
            }
            else{
                return true;
            }
        }
        return false;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTxtTaiKhoan1 = new javax.swing.JTextField();
        jTxtHoTen1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTxtSDT1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTxtGmail1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jbtnLuu = new javax.swing.JButton();
        jbtnQuayLai1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPwNew = new javax.swing.JPasswordField();
        jPwNewAgain = new javax.swing.JPasswordField();
        jPanel5 = new javax.swing.JPanel();
        jbtnEye = new javax.swing.JToggleButton();
        jLabel12 = new javax.swing.JLabel();
        jpwOld = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTxtTaiKhoan = new javax.swing.JTextField();
        jTxtHoTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTxtSDT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTxtGmail = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jbtnDangXuat = new javax.swing.JButton();
        jbtnChinhSua = new javax.swing.JButton();
        jbtnQuayLai = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();

        jPanel2.setBackground(new java.awt.Color(206, 221, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "----)***(----", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 8), new java.awt.Color(0, 51, 153))); // NOI18N
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.setPreferredSize(new java.awt.Dimension(503, 400));
        jPanel2.setRequestFocusEnabled(false);
        jPanel2.setVerifyInputWhenFocusTarget(false);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Tên tài khoản:");

        jTxtTaiKhoan1.setEditable(false);
        jTxtTaiKhoan1.setBackground(new java.awt.Color(255, 255, 255));
        jTxtTaiKhoan1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTxtTaiKhoan1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTxtTaiKhoan1.setBorder(null);

        jTxtHoTen1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTxtHoTen1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTxtHoTen1.setBorder(null);
        jTxtHoTen1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtHoTen1FocusLost(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Họ Tên: ");

        jTxtSDT1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTxtSDT1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTxtSDT1.setBorder(null);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Số điện thoại:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("Email:");

        jTxtGmail1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTxtGmail1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTxtGmail1.setBorder(null);
        jTxtGmail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtGmail1ActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(206, 221, 255));

        jbtnLuu.setBackground(new java.awt.Color(115, 187, 255));
        jbtnLuu.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jbtnLuu.setText("Lưu");
        jbtnLuu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Times New Roman", 0, 11), new java.awt.Color(102, 255, 204))); // NOI18N
        jbtnLuu.setBorderPainted(false);
        jbtnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLuuActionPerformed(evt);
            }
        });

        jbtnQuayLai1.setBackground(new java.awt.Color(115, 187, 255));
        jbtnQuayLai1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jbtnQuayLai1.setText("Quay lại");
        jbtnQuayLai1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Times New Roman", 0, 11), new java.awt.Color(102, 255, 204))); // NOI18N
        jbtnQuayLai1.setBorderPainted(false);
        jbtnQuayLai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnQuayLai1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbtnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnQuayLai1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnQuayLai1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText("Mật khẩu mới:");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setText("Nhập lại mật khẩu:");

        jPwNewAgain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPwNewAgainActionPerformed(evt);
            }
        });

        jPanel5.setPreferredSize(new java.awt.Dimension(29, 29));

        jbtnEye.setBackground(new java.awt.Color(206, 221, 255));
        jbtnEye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTTT/view/eyeOffBlack.png"))); // NOI18N
        jbtnEye.setBorder(null);
        jbtnEye.setOpaque(true);
        jbtnEye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEyeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jbtnEye, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jbtnEye, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setText("Mật khẩu cũ:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jpwOld, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTxtHoTen1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                            .addComponent(jTxtSDT1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTxtGmail1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTxtTaiKhoan1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPwNew, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPwNewAgain, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtTaiKhoan1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtHoTen1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtSDT1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtGmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jpwOld, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPwNew, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPwNewAgain, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(206, 221, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "----)***(----", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 8), new java.awt.Color(0, 51, 153))); // NOI18N
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setPreferredSize(new java.awt.Dimension(506, 224));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Tên tài khoản:");

        jTxtTaiKhoan.setEditable(false);
        jTxtTaiKhoan.setBackground(new java.awt.Color(206, 221, 255));
        jTxtTaiKhoan.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTxtTaiKhoan.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTxtTaiKhoan.setBorder(null);

        jTxtHoTen.setEditable(false);
        jTxtHoTen.setBackground(new java.awt.Color(206, 221, 255));
        jTxtHoTen.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTxtHoTen.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTxtHoTen.setBorder(null);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Họ Tên: ");

        jTxtSDT.setEditable(false);
        jTxtSDT.setBackground(new java.awt.Color(206, 221, 255));
        jTxtSDT.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTxtSDT.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTxtSDT.setBorder(null);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Số điện thoại:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Email:");

        jTxtGmail.setEditable(false);
        jTxtGmail.setBackground(new java.awt.Color(206, 221, 255));
        jTxtGmail.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTxtGmail.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTxtGmail.setBorder(null);
        jTxtGmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtGmailActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(206, 221, 255));

        jbtnDangXuat.setBackground(new java.awt.Color(115, 187, 255));
        jbtnDangXuat.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jbtnDangXuat.setText("Đăng xuất");
        jbtnDangXuat.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Times New Roman", 0, 11), new java.awt.Color(102, 255, 204))); // NOI18N
        jbtnDangXuat.setBorderPainted(false);
        jbtnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDangXuatActionPerformed(evt);
            }
        });

        jbtnChinhSua.setBackground(new java.awt.Color(115, 187, 255));
        jbtnChinhSua.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jbtnChinhSua.setText("Chỉnh sửa");
        jbtnChinhSua.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Times New Roman", 0, 11), new java.awt.Color(102, 255, 204))); // NOI18N
        jbtnChinhSua.setBorderPainted(false);
        jbtnChinhSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnChinhSuaActionPerformed(evt);
            }
        });

        jbtnQuayLai.setBackground(new java.awt.Color(115, 187, 255));
        jbtnQuayLai.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jbtnQuayLai.setText("Quay lại");
        jbtnQuayLai.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Times New Roman", 0, 11), new java.awt.Color(102, 255, 204))); // NOI18N
        jbtnQuayLai.setBorderPainted(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jbtnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jbtnChinhSua, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jbtnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnChinhSua, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTxtTaiKhoan)
                            .addComponent(jTxtHoTen)
                            .addComponent(jTxtSDT)
                            .addComponent(jTxtGmail))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtGmail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTxtGmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtGmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtGmailActionPerformed

    private void jbtnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDangXuatActionPerformed
        // TODO add your handling code here:
        int kq = JOptionPane.showConfirmDialog(null,"Bạn có muốn đăng xuất tài khoản '" + KTTK.getTtk() + "' không?","",JOptionPane.YES_NO_OPTION);
        if(kq == JOptionPane.YES_OPTION){
                            KTTK.setTtk("");
                            JOptionPane.showMessageDialog(this, "Đăng xuất thành công !");
                            this.dispose();
                        }
    }//GEN-LAST:event_jbtnDangXuatActionPerformed

    private void jbtnChinhSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnChinhSuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnChinhSuaActionPerformed

    private void jTxtHoTen1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtHoTen1FocusLost
        // TODO add your handling code here:
        // TODO add your handling code here:
        String chuanHoa = ChuanHoa.ChuanHoaHoTen(jTxtHoTen.getText());
        jTxtHoTen.setText(chuanHoa);
    }//GEN-LAST:event_jTxtHoTen1FocusLost

    private void jTxtGmail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtGmail1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtGmail1ActionPerformed

    private void jbtnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLuuActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jbtnLuuActionPerformed

    private void jbtnQuayLai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnQuayLai1ActionPerformed
        // TODO add your handling code here:
            
    }//GEN-LAST:event_jbtnQuayLai1ActionPerformed

    private void jPwNewAgainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPwNewAgainActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPwNewAgainActionPerformed

    private void jbtnEyeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEyeActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jbtnEyeActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPasswordField jPwNew;
    private javax.swing.JPasswordField jPwNewAgain;
    private javax.swing.JTextField jTxtGmail;
    private javax.swing.JTextField jTxtGmail1;
    private javax.swing.JTextField jTxtHoTen;
    private javax.swing.JTextField jTxtHoTen1;
    private javax.swing.JTextField jTxtSDT;
    private javax.swing.JTextField jTxtSDT1;
    private javax.swing.JTextField jTxtTaiKhoan;
    private javax.swing.JTextField jTxtTaiKhoan1;
    private javax.swing.JButton jbtnChinhSua;
    private javax.swing.JButton jbtnDangXuat;
    private javax.swing.JToggleButton jbtnEye;
    private javax.swing.JButton jbtnLuu;
    private javax.swing.JButton jbtnQuayLai;
    private javax.swing.JButton jbtnQuayLai1;
    private javax.swing.JPasswordField jpwOld;
    // End of variables declaration//GEN-END:variables
}
