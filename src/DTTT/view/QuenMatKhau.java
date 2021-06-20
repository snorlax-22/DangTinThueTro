/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTTT.view;

import DTTT.controller.ChuyenManHinh;
import DTTT.dao.KTTK;
import DTTT.dao.KiemTraDN;
import DTTT.Maill.Mail;
import DTTT.model.TaiKhoan;
import DTTT.dao.TaiKhoanDB;
import DTTT.model.ChuanHoa;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author huynhkhanh
 */
public class QuenMatKhau extends javax.swing.JDialog {

    /**
     * Creates new form QuenMatKhau
     * @param root
     * @param root1
     * @return 
     */
    private JPanel root,root1,node1;
    private TaiKhoan tk;
    private String maCode;
    public int Dispose_On_Close(JPanel root, JPanel root1, JPanel hienPanel){
        jPanel1.removeAll();
        root.setVisible(false);
        root1.setVisible(false);
        setView(jPanel5);
        return DISPOSE_ON_CLOSE;
    }
    
    public void setView(JPanel jpn){
        jPanel1.removeAll();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(jpn, BorderLayout.CENTER);
        jPanel1.validate();
        jPanel1.repaint();
        
    }
    public QuenMatKhau() {
            initComponents();
        try {
            this.setDefaultCloseOperation(Dispose_On_Close(jPanelThongTin,jPanelMaCode, jPanel5));
            setView(jPanel5);
//            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jPanelThongTin.setVisible(false);
            jPanelMaCode.setVisible(false);
            Action action = new AbstractAction()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                         if(jtxtNhapmail.getText().length() == 0){
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng điền email!");
                        jtxtNhapmail.requestFocus();
                        }else{
                        if(!jtxtNhapmail.getText().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")){
                            JOptionPane.showMessageDialog(null,"Mail không hợp lệ!");
                            jtxtNhapmail.requestFocus();
                        }else{
                            try {
                                tk = TaiKhoanDB.getTTTaiKhoanQuaMail(jtxtNhapmail.getText());
                                jPanelThongTin.setVisible(true);
                                jlbMail.setText(tk.getGmail());
                                jlbTen.setText(tk.getHoTen());
                            } catch (SQLException ex) {
                                Logger.getLogger(QuenMatKhau.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        }
                    }
                    }
                };
                jtxtNhapmail.addActionListener( action );
            jbtnTimKiem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(jtxtNhapmail.getText().length() == 0){
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng điền email!");
                        jtxtNhapmail.requestFocus();
                    }else{
                        if(!jtxtNhapmail.getText().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")){
                            JOptionPane.showMessageDialog(null,"Mail không hợp lệ!");
                            jtxtNhapmail.requestFocus();
                        }else{
                            try {
                                tk = TaiKhoanDB.getTTTaiKhoanQuaMail(jtxtNhapmail.getText());
                                jPanelThongTin.setVisible(true);
                                jlbMail.setText(tk.getGmail());
                                jlbTen.setText(tk.getHoTen());
                            } catch (SQLException ex) {
                                Logger.getLogger(QuenMatKhau.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        }
                    }
                }
            });
            jbtnKhongPhaiban.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jPanelThongTin.setVisible(false);
                    jtxtNhapmail.requestFocus();
                }
            });
            jBtbTiepTucGuiCode.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jPanelMaCode.setVisible(true);
                    maCode = ChuanHoa.taoMaCode();
                    String mailNhan = tk.getGmail();
                    String mailSucject = "Mã bảo mật của bạn";
                    String mailMsg = " \n" +
                    "Mã bảo mật DangTinNhaTro của bạn\n" +
                    " \n" +
                    "Xin chào "+ tk.getHoTen()+" ,\n" +
                    "Mã bảo mật của bạn là:\n" + maCode+" \n" +
                    "Nếu bạn không yêu cầu mã nào, có thể ai đó đang cố truy cập vào tài khoản của bạn. Bạn có thể đổi mật khẩu để bảo vệ tài khoản của mình.\n" +
                    " \n" +
                    "Cảm ơn bạn!\n" +
                    "Đội ngũ bảo mật của DangTinNhaTro";
                    Mail.sendMail(mailNhan, mailSucject, mailMsg);
                }
            });
            jbtnTiepTucNhapCode.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(maCode.equals(jtxtmaCode.getText())){
                        setView(jPanel3);
                    }else{
                        JOptionPane.showMessageDialog(null, "Mã nhập không chính xác!");
                        jtxtmaCode.requestFocus();
                    }
                }
            });
            jbtnTiepTucNhapMK.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String mkNhap = new String(jPwNeww.getPassword());
                   if(mkNhap.length() == 0){
                        jlbThongBao.setText("Vui lòng nhập mật khẩu!");
                        jPwNeww.requestFocus();
                   }else{
                       if(mkNhap.length()<6){
                            jlbThongBao.setText("Mật khẩu quá ngắn");
                            jPwNeww.requestFocus();
                       }else try {
                           if(TaiKhoanDB.checktrungMk(tk.getGmail(), mkNhap) == true) {
                              jlbThongBao.setText("Mật khẩu mới bị trùng, vui lòng đặt mặt khẩu khác để bảo mật!");
                              jPwNeww.requestFocus();
                           }else{
                               tk.setMk(mkNhap);
                               TaiKhoanDB.updateTaiKhoan(tk);
                               JOptionPane.showMessageDialog(null, "Đã thay đổi mật khẩu");
                               Container frame = jbtnHuyNhapMK.getParent();
                                do{
                                    frame = frame.getParent(); 
                                }while (!(frame instanceof QuenMatKhau));  
                                ((QuenMatKhau) frame).dispose();
                               
                           }
                       } catch (SQLException ex) {
                           Logger.getLogger(QuenMatKhau.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
                }
            });
            jbtnOnOff.addActionListener(new ActionListener() {
                Image onEye = ImageIO.read(getClass().getResource("eyeOnBlack.png"));
                Image offEye = ImageIO.read(getClass().getResource("eyeOffBlack.png"));
                @Override
                public void actionPerformed(ActionEvent e) {
                    Container eye = getContentPane();
                    if(jbtnOnOff.isSelected()){
                        jbtnOnOff.setIcon(new ImageIcon(onEye));
                        jPwNeww.setEchoChar((char)0);
                        jPwNeww.requestFocus();
                    }else{
                        jbtnOnOff.setIcon(new ImageIcon(offEye));
                        jPwNeww.setEchoChar('*');
                        jPwNeww.requestFocus();
                    }
                    
                }
            });
            jbtnHuyNhapCode.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    jPanelMaCode.setVisible(false);
                    jPanelThongTin.setVisible(false);
                }
                
            });
            jbtnHuyNhapMK.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    Container frame = jbtnHuyNhapMK.getParent();
                    do{
                        frame = frame.getParent(); 
                    }while (!(frame instanceof QuenMatKhau));  
                    ((QuenMatKhau) frame).dispose();
                }
                
            });
            
        } catch (IOException ex) {
            Logger.getLogger(QuenMatKhau.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jbtnOnOff = new javax.swing.JToggleButton();
        jPwNeww = new javax.swing.JPasswordField();
        jbtnHuyNhapMK = new javax.swing.JButton();
        jbtnTiepTucNhapMK = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jlbThongBao = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jtxtNhapmail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jbtnTimKiem = new javax.swing.JButton();
        jbtnHuy = new javax.swing.JButton();
        jPanelThongTin = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanelMaCode = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jtxtmaCode = new javax.swing.JTextField();
        jlbThongBaoCode = new javax.swing.JLabel();
        jbtnHuyNhapCode = new javax.swing.JButton();
        jbtnTiepTucNhapCode = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jlbTen = new javax.swing.JLabel();
        jlbMail = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jBtbTiepTucGuiCode = new javax.swing.JButton();
        jbtnKhongPhaiban = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(512, 435));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText(" Chọn mật khẩu mới của bạn");
        jLabel4.setOpaque(true);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jbtnOnOff.setBackground(new java.awt.Color(255, 255, 255));
        jbtnOnOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTTT/view/eyeOffBlack.png"))); // NOI18N
        jbtnOnOff.setBorderPainted(false);
        jbtnOnOff.setOpaque(true);
        jbtnOnOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnOnOffActionPerformed(evt);
            }
        });

        jPwNeww.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(204, 255, 255), new java.awt.Color(0, 51, 153)));

        jbtnHuyNhapMK.setBackground(new java.awt.Color(204, 204, 204));
        jbtnHuyNhapMK.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jbtnHuyNhapMK.setText("Hủy");
        jbtnHuyNhapMK.setBorderPainted(false);

        jbtnTiepTucNhapMK.setBackground(new java.awt.Color(0, 51, 153));
        jbtnTiepTucNhapMK.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jbtnTiepTucNhapMK.setForeground(new java.awt.Color(255, 255, 255));
        jbtnTiepTucNhapMK.setText("Tiếp tục");
        jbtnTiepTucNhapMK.setBorderPainted(false);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText(" Nhập mật khẩu mới:");

        jlbThongBao.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jlbThongBao.setForeground(new java.awt.Color(255, 51, 0));
        jlbThongBao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbThongBao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(0, 262, Short.MAX_VALUE)
                                .addComponent(jbtnHuyNhapMK, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jbtnTiepTucNhapMK, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPwNeww))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnOnOff, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbtnOnOff, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(jPwNeww))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnTiepTucNhapMK, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnHuyNhapMK, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Vui lòng nhập số điện thoại hoặc mail để tìm tài khoản của bạn.");

        jtxtNhapmail.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(51, 0, 255), new java.awt.Color(51, 51, 51), new java.awt.Color(204, 255, 255), new java.awt.Color(204, 204, 204)));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Tìm tài khoản của bạn");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jbtnTimKiem.setBackground(new java.awt.Color(0, 51, 153));
        jbtnTimKiem.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jbtnTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        jbtnTimKiem.setText("Tìm kiếm");
        jbtnTimKiem.setBorderPainted(false);
        jbtnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTimKiemActionPerformed(evt);
            }
        });

        jbtnHuy.setBackground(new java.awt.Color(204, 204, 204));
        jbtnHuy.setText("Hủy");
        jbtnHuy.setBorderPainted(false);
        jbtnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnTimKiem)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnTimKiem)
                    .addComponent(jbtnHuy))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelThongTin.setBackground(new java.awt.Color(255, 255, 255));
        jPanelThongTin.setOpaque(false);

        jPanelMaCode.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel6.setText("Vui lòng kiểm tra mã trong email. Mã này gồm 6 chữ số");

        jtxtmaCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtmaCodeActionPerformed(evt);
            }
        });
        jtxtmaCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtmaCodeKeyTyped(evt);
            }
        });

        jbtnHuyNhapCode.setBackground(new java.awt.Color(204, 204, 204));
        jbtnHuyNhapCode.setText("Hủy");
        jbtnHuyNhapCode.setBorderPainted(false);
        jbtnHuyNhapCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnHuyNhapCodeActionPerformed(evt);
            }
        });

        jbtnTiepTucNhapCode.setBackground(new java.awt.Color(0, 51, 153));
        jbtnTiepTucNhapCode.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jbtnTiepTucNhapCode.setForeground(new java.awt.Color(255, 255, 255));
        jbtnTiepTucNhapCode.setText("Tiếp tục");
        jbtnTiepTucNhapCode.setBorderPainted(false);
        jbtnTiepTucNhapCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTiepTucNhapCodeActionPerformed(evt);
            }
        });

        jLabel7.setText("Nhập mã:");

        javax.swing.GroupLayout jPanelMaCodeLayout = new javax.swing.GroupLayout(jPanelMaCode);
        jPanelMaCode.setLayout(jPanelMaCodeLayout);
        jPanelMaCodeLayout.setHorizontalGroup(
            jPanelMaCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMaCodeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMaCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMaCodeLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbThongBaoCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jtxtmaCode)
                    .addGroup(jPanelMaCodeLayout.createSequentialGroup()
                        .addGroup(jPanelMaCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelMaCodeLayout.createSequentialGroup()
                                .addComponent(jbtnHuyNhapCode, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnTiepTucNhapCode))
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanelMaCodeLayout.setVerticalGroup(
            jPanelMaCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMaCodeLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanelMaCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbThongBaoCode, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtxtmaCode, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelMaCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbtnTiepTucNhapCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnHuyNhapCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTTT/images/userBlack.png"))); // NOI18N

        jlbTen.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jlbTen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jlbMail.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jlbMail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Tiếp tục gửi mã code về mail");

        jBtbTiepTucGuiCode.setBackground(new java.awt.Color(204, 204, 204));
        jBtbTiepTucGuiCode.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jBtbTiepTucGuiCode.setText("Tiếp tục");
        jBtbTiepTucGuiCode.setBorder(null);
        jBtbTiepTucGuiCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtbTiepTucGuiCodeActionPerformed(evt);
            }
        });

        jbtnKhongPhaiban.setBackground(new java.awt.Color(102, 153, 255));
        jbtnKhongPhaiban.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jbtnKhongPhaiban.setText("Đây không phải là bạn?");
        jbtnKhongPhaiban.setBorder(null);
        jbtnKhongPhaiban.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnKhongPhaibanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlbMail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtbTiepTucGuiCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbtnKhongPhaiban, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jLabel3)
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbTen, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlbMail, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtbTiepTucGuiCode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnKhongPhaiban)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelThongTinLayout = new javax.swing.GroupLayout(jPanelThongTin);
        jPanelThongTin.setLayout(jPanelThongTinLayout);
        jPanelThongTinLayout.setHorizontalGroup(
            jPanelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelThongTinLayout.createSequentialGroup()
                .addComponent(jSeparator2)
                .addContainerGap())
            .addGroup(jPanelThongTinLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelMaCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelThongTinLayout.setVerticalGroup(
            jPanelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelThongTinLayout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelMaCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jtxtNhapmail)
                        .addContainerGap())
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jPanelThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtxtNhapmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 457, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnOnOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnOnOffActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jbtnOnOffActionPerformed

    private void jbtnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnHuyActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jbtnHuyActionPerformed

    private void jbtnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnTimKiemActionPerformed

    private void jbtnKhongPhaibanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnKhongPhaibanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnKhongPhaibanActionPerformed

    private void jbtnHuyNhapCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnHuyNhapCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnHuyNhapCodeActionPerformed

    private void jBtbTiepTucGuiCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtbTiepTucGuiCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtbTiepTucGuiCodeActionPerformed

    private void jbtnTiepTucNhapCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTiepTucNhapCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnTiepTucNhapCodeActionPerformed

    private void jtxtmaCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtmaCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtmaCodeActionPerformed

    private void jtxtmaCodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtmaCodeKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!Character.isDigit(c)){
            evt.consume();
        }
        if(jtxtmaCode.getText().length() >= 6){

            evt.consume();
        }
    }//GEN-LAST:event_jtxtmaCodeKeyTyped

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
            java.util.logging.Logger.getLogger(QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuenMatKhau().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtbTiepTucGuiCode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelMaCode;
    private javax.swing.JPanel jPanelThongTin;
    private javax.swing.JPasswordField jPwNeww;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton jbtnHuy;
    private javax.swing.JButton jbtnHuyNhapCode;
    private javax.swing.JButton jbtnHuyNhapMK;
    private javax.swing.JButton jbtnKhongPhaiban;
    private javax.swing.JToggleButton jbtnOnOff;
    private javax.swing.JButton jbtnTiepTucNhapCode;
    private javax.swing.JButton jbtnTiepTucNhapMK;
    private javax.swing.JButton jbtnTimKiem;
    private javax.swing.JLabel jlbMail;
    private javax.swing.JLabel jlbTen;
    private javax.swing.JLabel jlbThongBao;
    private javax.swing.JLabel jlbThongBaoCode;
    private javax.swing.JTextField jtxtNhapmail;
    private javax.swing.JTextField jtxtmaCode;
    // End of variables declaration//GEN-END:variables
}
