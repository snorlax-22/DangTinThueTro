/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTTT.view;
import DTTT.controller.ThongTinPhongController;
import DTTT.dao.DBConnect;
import DTTT.model.Anh;
import DTTT.model.ChuanHoa;
import DTTT.model.LoaiPhong;
import DTTT.model.ThongTinPhong;
import DTTT.service.AnhService;
import DTTT.service.AnhServiceImpl;
import DTTT.service.ThongTinPhongService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextArea;
import java.util.List;
import java.lang.String;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ButtonGroup;

/**
 *
 * @author Snorlax
 */
public final class PhongJDialog_LichHen extends javax.swing.JDialog {
    String MaTinTin = ""; 
    HienThiLichHen hienThi;
    public PhongJDialog_LichHen() throws SQLException {
        initComponents();
        this.anhService = new AnhServiceImpl();
        
    }
    private AnhService anhService = new AnhServiceImpl();
    
    private ThongTinPhongService thongTinPhongService = null;
    
    
    public PhongJDialog_LichHen(ThongTinPhong tinphong, LoaiPhong lp) throws SQLException {
        initComponents();
        showTextArea();
        this.anhService = new AnhServiceImpl();
        ThongTinPhongController controller = new ThongTinPhongController(jtfMaTin,jtfDienTich,jtfGiaPhong,jtfSoPhong,jtfDieuHoa,jtfLoaiPhong,jtaMoTa);
        controller.setView(tinphong, lp);  
        
    }
    
    public static int xacNhan = 0;
    Anh hinhAnh = new Anh(); // dùng để thêm hình ảnh mới  
    Anh ha = new Anh(); // dùng để duyệt hình ảnh đã có
    
    
    
   
    int loai=0,luu=0;
    ButtonGroup bg = new ButtonGroup();
    byte[] picture = null;
    List<byte[]> listAnhByte = null;
    
    
    public PhongJDialog_LichHen(ThongTinPhong tinphong) throws SQLException {
        initComponents();
        showTextArea();
        this.anhService = new AnhServiceImpl();
        ThongTinPhongController controller = new ThongTinPhongController(jtfMaTin,jtfDienTich,jtfGiaPhong,jtfSoPhong,jtfDieuHoa,jtfLoaiPhong,jtaMoTa);
        controller.setView(tinphong);
        
         
    }
    public TextArea jtaMoTa = new TextArea("", 16, 33, TextArea.SCROLLBARS_VERTICAL_ONLY);
    public void showTextArea(){
        System.setProperty ( "mauXanh" , "0C007E" );
        jtaMoTa.setSize(290, 255);
        jtaMoTa.setBounds(0, 0, 290, 255);
        jtaMoTa.setEditable(false);
        jtaMoTa.setForeground(Color.BLACK);
        jtaMoTa.setBackground(Color.getColor("mauXanh"));
        jtaMoTa.setVisible(true);
        jPanel1.removeAll();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(jtaMoTa);
        jPanel1.validate();
        jPanel1.repaint();
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtfMaTin = new javax.swing.JTextField();
        jtaMoTa2 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jtfGiaPhong = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfSoPhong = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtfDieuHoa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtfLoaiPhong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtfDienTich = new javax.swing.JTextField();
        jbtXemHinhPhong = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        jtfMaTin.setEditable(false);
        jtfMaTin.setBackground(new java.awt.Color(224, 236, 255));
        jtfMaTin.setForeground(new java.awt.Color(204, 239, 255));
        jtfMaTin.setText("jTextField1");
        jtfMaTin.setBorder(null);

        jtaMoTa2.setEditable(false);
        jtaMoTa2.setBackground(new java.awt.Color(12, 0, 126));
        jtaMoTa2.setColumns(20);
        jtaMoTa2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jtaMoTa2.setForeground(new java.awt.Color(255, 255, 255));
        jtaMoTa2.setRows(5);
        jtaMoTa2.setTabSize(15);
        jtaMoTa2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mô tả", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);

        jPanel3.setBackground(new java.awt.Color(224, 236, 255));

        jPanel2.setBackground(new java.awt.Color(12, 0, 126));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP)), "Thông tin phòng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        jtfGiaPhong.setEditable(false);
        jtfGiaPhong.setBackground(new java.awt.Color(12, 0, 126));
        jtfGiaPhong.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jtfGiaPhong.setForeground(new java.awt.Color(255, 255, 255));
        jtfGiaPhong.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jtfGiaPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfGiaPhongActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTTT/images/round_attach_money_white_24dp.png"))); // NOI18N
        jLabel1.setText("Giá Phòng :");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTTT/images/round_meeting_room_white_24dp.png"))); // NOI18N
        jLabel2.setText("Số phòng :");

        jtfSoPhong.setEditable(false);
        jtfSoPhong.setBackground(new java.awt.Color(12, 0, 126));
        jtfSoPhong.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jtfSoPhong.setForeground(new java.awt.Color(255, 255, 255));
        jtfSoPhong.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jtfSoPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSoPhongActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTTT/images/round_ac_unit_white_24dp.png"))); // NOI18N
        jLabel3.setText("Điều hòa :");

        jtfDieuHoa.setEditable(false);
        jtfDieuHoa.setBackground(new java.awt.Color(12, 0, 126));
        jtfDieuHoa.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jtfDieuHoa.setForeground(new java.awt.Color(255, 255, 255));
        jtfDieuHoa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jtfDieuHoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfDieuHoaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTTT/images/baseline_format_list_bulleted_white_24dp.png"))); // NOI18N
        jLabel4.setText("Loại phòng :");

        jtfLoaiPhong.setEditable(false);
        jtfLoaiPhong.setBackground(new java.awt.Color(12, 0, 126));
        jtfLoaiPhong.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jtfLoaiPhong.setForeground(new java.awt.Color(255, 255, 255));
        jtfLoaiPhong.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jtfLoaiPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfLoaiPhongActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTTT/images/baseline_architecture_white_24dp.png"))); // NOI18N
        jLabel6.setText("Diện tích :");

        jtfDienTich.setEditable(false);
        jtfDienTich.setBackground(new java.awt.Color(12, 0, 126));
        jtfDienTich.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jtfDienTich.setForeground(new java.awt.Color(255, 255, 255));
        jtfDienTich.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jtfDienTich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfDienTichActionPerformed(evt);
            }
        });

        jbtXemHinhPhong.setBackground(new java.awt.Color(255, 255, 255));
        jbtXemHinhPhong.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jbtXemHinhPhong.setText("Xem ảnh phòng");
        jbtXemHinhPhong.setBorder(null);
        jbtXemHinhPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtXemHinhPhongActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(12, 0, 126));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mô tả", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setAutoscrolls(true);
        jPanel1.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 309, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 302, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jtfGiaPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfSoPhong)
                                    .addComponent(jtfDienTich)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(24, 24, 24)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtfDieuHoa)
                                    .addComponent(jtfLoaiPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 529, Short.MAX_VALUE)
                        .addComponent(jbtXemHinhPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfGiaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfSoPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfDienTich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfDieuHoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jtfLoaiPhong)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 15, Short.MAX_VALUE)
                .addComponent(jbtXemHinhPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfGiaPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfGiaPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfGiaPhongActionPerformed

    private void jtfSoPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSoPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSoPhongActionPerformed

    private void jtfDieuHoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfDieuHoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfDieuHoaActionPerformed

    private void jtfLoaiPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfLoaiPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfLoaiPhongActionPerformed

    private void jtfDienTichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfDienTichActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfDienTichActionPerformed

    private void jbtXemHinhPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtXemHinhPhongActionPerformed
        // TODO add your handling code here:
        MaTinTin = ChuanHoa.chuanHoaMa(jtfMaTin.getText());

        try {
            //        System.out.println(hinhAnh.getDsha());
            setTin(MaTinTin);
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        
        HinhPhong hinhphong = new HinhPhong(hinhAnh.getDsha());
            ha.setDsha(hinhphong.layDSHinh());
            this.setAlwaysOnTop(false);
            
            
    }//GEN-LAST:event_jbtXemHinhPhongActionPerformed
    
    public void setTin(String MaTinTin) throws SQLException{
        Connection conn = DBConnect.getConnection();
        String sql = "SELECT * FROM Thong_Tin_Tin ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        
        this.MaTinTin=MaTinTin;
        MaTinTin = ChuanHoa.chuanHoaMa(MaTinTin);
        conn.close();
        setThongTinPhong(MaTinTin);
    }
        
        public void setThongTinPhong(String MaTinTin) throws SQLException{
        Connection conn = DBConnect.getConnection();
        String sql = "SELECT * FROM Thong_Tin_Phong";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            if(ChuanHoa.chuanHoaMa(rs.getString("MaTin")).equals(MaTinTin)){

                  setHinhAnh(MaTinTin); 
                 
            }
        }
    }
    
    public void setHinhAnh(String MaTinTin) throws SQLException{
        Anh ha = new Anh();
        Connection conn = DBConnect.getConnection();
        String sql = "Select * FROM Hinh_Anh";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            if(ChuanHoa.chuanHoaMa(rs.getString("MaTin")).equals(MaTinTin)){
               
                ha.themHinh(rs.getBytes("HinhAnh"));
                ha.themID(rs.getString("MaHinhAnh"));
            }
        }
        this.hinhAnh=ha;
      
    }
    
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jbtXemHinhPhong;
    private javax.swing.JTextArea jtaMoTa2;
    private javax.swing.JTextField jtfDienTich;
    private javax.swing.JTextField jtfDieuHoa;
    private javax.swing.JTextField jtfGiaPhong;
    private javax.swing.JTextField jtfLoaiPhong;
    private javax.swing.JTextField jtfMaTin;
    private javax.swing.JTextField jtfSoPhong;
    // End of variables declaration//GEN-END:variables
}
