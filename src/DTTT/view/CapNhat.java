package DTTT.view;

import DTTT.controller.ChuyenManHinh;
import DTTT.dao.DBConnect;
import DTTT.dao.ThongTinDB;
import DTTT.model.Anh;
import DTTT.model.ChuanHoa;
import DTTT.model.ThongTinPhong;
import DTTT.model.ThongTinTin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;


public class CapNhat extends javax.swing.JPanel {
    
    ThongTinTin ttt = new ThongTinTin();
    ThongTinPhong ttp = new ThongTinPhong(); 
    
    Anh hinhAnh = new Anh(); // dùng để thêm hình ảnh mới  
    Anh ha = new Anh(); // dùng để duyệt hình ảnh đã có
     
    String an="",lht="",gia="",dt="",tinh="",huyen="",xptt="",idXa="",idHuyen="",idTinh="",maLP="";
    String ttk="";
    String maTin="";
    int loai=0,luu=0;
    ButtonGroup bg = new ButtonGroup();
    byte[] picture = null;

    // loai=1 là thêm mới, loai=0 là chỉnh sửa
    public CapNhat(String ttk, int loai) throws SQLException {
        this.loai=loai;
        this.ttk = ttk;
       
        initComponents();
        
        showTinhTP();
        showLoaiHinhThue();
        
        bg.add(JrbtCo);
        bg.add(JrbtKhong);
        
        jcbbAnNinh.addItem("");
        jcbbAnNinh.addItem("Rất tốt");
        jcbbAnNinh.addItem("Khá tốt");
        jcbbAnNinh.addItem("Tốt");
        jcbbAnNinh.addItem("Trung bình");
       
        
        jcbbTinhTP.addActionListener(new ActionListener() {

           
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== jcbbTinhTP){
                    tinh = (String) jcbbTinhTP.getSelectedItem();
                    try {
                        jcbbQuanHuyen.removeAllItems();
                        showQuanHuyen(getIDTinh(tinh));
                    } catch (SQLException ex) {
                        Logger.getLogger(CapNhat.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        jcbbQuanHuyen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== jcbbQuanHuyen){
                    huyen = (String) jcbbQuanHuyen.getSelectedItem();
                    try {
                        jcbbXPTT.removeAllItems();
                        showXPTT(getIDHuyen(huyen));
                    } catch (SQLException ex) {
                        Logger.getLogger(CapNhat.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });     
        
        jcbbXPTT.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== jcbbXPTT){
                    xptt = (String) jcbbXPTT.getSelectedItem();
                    try {
                        idXa = getIDXa(xptt);
                    } catch (SQLException ex) {
                        Logger.getLogger(CapNhat.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        jcbbAnNinh.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getSource() == jcbbAnNinh) {
                   an = (String)jcbbAnNinh.getSelectedItem();
                }
            }
        });
        
        jcbbLoaiHinhThue.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== jcbbLoaiHinhThue){
                    lht = (String) jcbbLoaiHinhThue.getSelectedItem();
                    try {
                        maLP = getMaLoaiPhong(lht);
                    } catch (SQLException ex) {
                        Logger.getLogger(CapNhat.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    public String getIDTinh(String name) throws SQLException{
        Connection conn = DBConnect.getConnection();
        String sql = "SELECT * FROM TinhThanhPho";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            if(rs.getString("TenTP").trim().equals(name)){
                idTinh = rs.getString("MaTP");
            }
        }
        return idTinh;
    }
    public void showTinhTP() throws SQLException{
        Connection conn = DBConnect.getConnection();
        String sql = "SELECT TenTP FROM TinhThanhPho";
        jcbbTinhTP.addItem("");
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            jcbbTinhTP.addItem(rs.getString("TenTP"));
        }
    }
    public void showQuanHuyen(String id) throws SQLException{
        Connection conn = DBConnect.getConnection();
        String sql = "SELECT * FROM QuanHuyen where MaTP = '" + id + "'";
        jcbbQuanHuyen.addItem("");
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            jcbbQuanHuyen.addItem(rs.getString("TenQH"));
        }
    }
    public String getIDHuyen(String name) throws SQLException{
        Connection conn = DBConnect.getConnection();
        String sql = "SELECT * FROM QuanHuyen WHERE MaTP=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,idTinh);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            if(rs.getString("TenQH").trim().equals(name)){
                idHuyen = rs.getString("MaQH");
            }
        }
        return idHuyen;
    }
    public void showXPTT(String id) throws SQLException{
        Connection conn = DBConnect.getConnection();
        String sql = "SELECT * FROM XaPhuongThiTran where MaQH = '" + id + "'";
        jcbbXPTT.addItem("");
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            jcbbXPTT.addItem(rs.getString("TenXa"));
        }
    }
    public String getIDXa(String name) throws SQLException{
        Connection conn = DBConnect.getConnection();
        String sql = "SELECT * FROM XaPhuongThiTran WHERE MaQH=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, idHuyen);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            if(rs.getString("TenXa").trim().equals(name)){
                idXa = rs.getString("MaXa");
            }
        }
        return idXa;
    } 
   
    public void showLoaiHinhThue() throws SQLException{
        Connection conn = DBConnect.getConnection();
        String sql = "SELECT * FROM LoaiPhong";
        jcbbLoaiHinhThue.addItem("");
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            jcbbLoaiHinhThue.addItem(rs.getString("TenLoaiPhong"));
        }
        ps.close();
        conn.close();
    }
    public String getMaLoaiPhong(String name) throws SQLException{
        Connection conn = DBConnect.getConnection();
        String sql = "SELECT * FROM LoaiPhong";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            if(rs.getString("TenLoaiPhong").trim().equals(name)){
                maLP = rs.getString("MaLoaiPhong");
            }
        }
        return maLP;
    } 
    public String getMayLanh(){
        if(JrbtCo.isSelected())
            return "Có";
        else if(JrbtKhong.isSelected())
            return "Không";
        else
            return "";
    }
    
    public boolean kiemTraTTCB(){
        if(jtfTieuDe.getText().length()==0 || jtfSDT.getText().length()==0 || jtaSoNhaTenDuong.getText().length()==0 || an.length()==0 || lht.length()==0 || tinh.length()==0 || huyen.length()==0 || xptt.length()==0 || ChuanHoa.ChuanHoaSDT(jtfSDT.getText())==false){
            if(jtfTieuDe.getText().length()==0)
                JOptionPane.showMessageDialog(null,"Không để trống ô 'tiêu đề'");
            else if(jtfSDT.getText().length()==0)
                JOptionPane.showMessageDialog(null,"Không để trống 'Số điện thoại'");
            else if(an.length()==0)
                JOptionPane.showMessageDialog(null,"Chọn trạng thái 'An ninh'");
            else if(lht.length()==0)
                JOptionPane.showMessageDialog(null,"Chọn loại hình thuê");
            else if(tinh.length()==0)
                JOptionPane.showMessageDialog(null,"Chọn tỉnh/thành phố");
            else if(huyen.length()==0)
                JOptionPane.showMessageDialog(null,"Chọn quận/huyện");
            else if(xptt.length()==0)
                JOptionPane.showMessageDialog(null,"Chọn Xã,phường/thị trấn ");
            else if(jtaSoNhaTenDuong.getText().length()==0)
                JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy địa chỉ cụ thể");
            else if(ChuanHoa.ChuanHoaSDT(jtfSDT.getText()) == false)
                JOptionPane.showMessageDialog(null,"Số điện thoại không hợp lệ!");
            return false;
        }
        else 
            return true;
    }
    
    public boolean kiemTraTTP(){
        if(jtfTongPhong.getText().length()<1 || jtfGia.getText().length()<1 || jtfDienTich.getText().length()<1 ||getMayLanh().length()<1 || jtaMoTa.getText().length()<1 || hinhAnh.getDsha().isEmpty() || !ChuanHoa.ktSo(jtfTongPhong.getText())){
            if(jtfTongPhong.getText().length()==0)
                JOptionPane.showMessageDialog(null,"Nhập số lượng phòng");
            else if(jtfDienTich.getText().length()==0)
                JOptionPane.showMessageDialog(null,"Nhập đầy đủ kích thước phòng");
            else if(jtfGia.getText().length()==0)
                JOptionPane.showMessageDialog(null,"Nhập giá phòng");
            else if(getMayLanh().length()==0)
                JOptionPane.showMessageDialog(null,"Chọn máy lạnh (có/không)");
            else if(jtaMoTa.getText().length()==0)
                JOptionPane.showMessageDialog(null,"Chưa nhập mô tả phòng");
            else if(hinhAnh.getDsha().isEmpty())
                JOptionPane.showMessageDialog(null,"Vui lòng đăng tin kèm hình");
             else if(!ChuanHoa.ktSo(jtfTongPhong.getText()))
                JOptionPane.showMessageDialog(null,"Số phòng không hợp lệ (chỉ nhập số)");
            return false;
        }
        else 
            return true;
    }
    
    public void taoMoiTTCB(){
        jtfTieuDe.setText("");
        jtfSDT.setText("");
        jtaSoNhaTenDuong.setText("");
        jcbbAnNinh.setSelectedIndex(0);
        jcbbLoaiHinhThue.setSelectedIndex(0);
        jcbbTinhTP.setSelectedIndex(0);
    }
    
    public void taoMoiTTP(){
        jtfGia.setText("");
        jtfDienTich.setText("");
        jtfTongPhong.setText("");
        jtaMoTa.setText("");
        bg.clearSelection();
    }
    public void setXaHuyenTinh(String maXa) throws SQLException{
        String maQH="",maTP="";
        String tenXPTT="",tenQH="";
        Connection conn = DBConnect.getConnection();
        String sql = "SELECT * FROM XaPhuongThiTran";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            if(rs.getString("MaXa").equals(maXa)){
                maQH = rs.getString("MaQH");
                tenXPTT = rs.getString("TenXa");
            }
        }
        String sql1 = "SELECT * FROM QuanHuyen";
        PreparedStatement ps1 = conn.prepareStatement(sql1);
        ResultSet rs1 = ps1.executeQuery();
        while(rs1.next()){
            if(rs1.getString("MaQH").equals(maQH)){
                maTP = rs1.getString("MaTP");
                tenQH = rs1.getString("TenQH");
            }
        }
        String sql2 = "SELECT * FROM TinhThanhPho";
        PreparedStatement ps2 = conn.prepareStatement(sql2);
        ResultSet rs2 = ps2.executeQuery();
        while(rs2.next()){
            if(rs2.getString("MaTP").equals(maTP)){
                jcbbTinhTP.setSelectedItem(rs2.getString("TenTP"));
                jcbbQuanHuyen.setSelectedItem(tenQH);
                jcbbXPTT.setSelectedItem(tenXPTT);
            }
        }
        conn.close();
    }
    
    public void hienThiTTP(){
        
        xuLyGia(ttp.getGiaPhong());
        jtfTongPhong.setText(String.valueOf(ttp.getSoPhong()));
        jtfDienTich.setText(ttp.getDienTich());
        jtaMoTa.setText(ttp.getMoTa());
        if(ttp.getDieuHoa().equals("Có"))
            JrbtCo.setSelected(true);
        else
            JrbtKhong.setSelected(true);
    }
    public void setLoaiPhong(String maLP) throws SQLException{
        Connection conn = DBConnect.getConnection();
        String sql = "Select * FROM LoaiPhong";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            if(rs.getString("MaLoaiPhong").equals(maLP)){
                jcbbLoaiHinhThue.getModel().setSelectedItem(rs.getString("TenLoaiPhong"));
            }
        }
    }
    public void setHinhAnh(String maTin) throws SQLException{
        Anh ha = new Anh();
        Connection conn = DBConnect.getConnection();
        String sql = "Select * FROM Hinh_Anh";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            if(rs.getString("MaTin").equals(maTin)){
                ha.themHinh(rs.getBytes("HinhAnh"));
                ha.themID(rs.getString("MaHinhAnh"));
            }
        }
        this.hinhAnh=ha;
      
    }
    public void setThongTinPhong(String maTin) throws SQLException{
       
        Connection conn = DBConnect.getConnection();
        String sql = "SELECT * FROM Thong_Tin_Phong";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            if(rs.getString("MaTin").equals(maTin)){
                  ttp.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
                  ttp.setGiaPhong(rs.getString("GiaPhong"));
                  ttp.setSoPhong(rs.getInt("SoPhong"));
                  ttp.setDienTich(rs.getString("DienTich"));
                  ttp.setDieuHoa(rs.getString("DieuHoa"));
                  ttp.setMoTa(rs.getString("MoTa"));
                
                  setHinhAnh(maTin); 
                  setLoaiPhong(ttp.getMaLoaiPhong());
            }
        }
        hienThiTTP();
    }
    public void setTin(String maTin) throws SQLException{
        
        Connection conn = DBConnect.getConnection();
        String sql = "SELECT * FROM Thong_Tin_Tin ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            if(rs.getString("MaTin").equals(maTin)){
                jtfTieuDe.setText(rs.getString("TieuDe"));
                jtfSDT.setText(rs.getString("SDTTin"));
                jcbbAnNinh.getModel().setSelectedItem(rs.getString("AnNinh"));
                jtaSoNhaTenDuong.setText(rs.getString("ThongTinDiaChi"));
                ttt.setNgayDang(rs.getDate("NgayDang"));
                setXaHuyenTinh(rs.getString("MaXa")); //để hiển thị ra Combobox;
                
            }
        }
        this.maTin=maTin;
        conn.close();
        setThongTinPhong(maTin);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jtfTieuDe = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfSDT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        JrbtCo = new javax.swing.JRadioButton();
        JrbtKhong = new javax.swing.JRadioButton();
        jtfGia = new javax.swing.JTextField();
        jtfTongPhong = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaMoTa = new javax.swing.JTextArea();
        jbtLuuTin = new javax.swing.JButton();
        jlbDienTich = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jtfDienTich = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jbtXoaTin = new javax.swing.JButton();
        jbtThemAnh = new javax.swing.JButton();
        jcbbQuanHuyen = new javax.swing.JComboBox();
        jcbbTinhTP = new javax.swing.JComboBox();
        jcbbXPTT = new javax.swing.JComboBox();
        jcbbAnNinh = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaSoNhaTenDuong = new javax.swing.JTextArea();
        jbtQuayLai = new javax.swing.JButton();
        jcbbLoaiHinhThue = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jbtLamMoi = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(0, 53, 128));
        setPreferredSize(new java.awt.Dimension(960, 640));

        jtfTieuDe.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jtfTieuDe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfTieuDeKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Tên tiêu đề:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText("Số điện thoại:");

        jtfSDT.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jtfSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSDTActionPerformed(evt);
            }
        });
        jtfSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfSDTKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setText("An Ninh:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setText("Quận/Huyện:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setText("Tỉnh/Thành phố:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setText("Số nhà/Tên đường:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setText("Xã/Phường,Thị trấn:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 53, 128));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Giá");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tổng phòng:");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Máy lạnh");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Mô tả:");

        JrbtCo.setBackground(new java.awt.Color(0, 53, 128));
        JrbtCo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        JrbtCo.setForeground(new java.awt.Color(255, 255, 255));
        JrbtCo.setText("Có");
        JrbtCo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JrbtCoActionPerformed(evt);
            }
        });

        JrbtKhong.setBackground(new java.awt.Color(0, 53, 128));
        JrbtKhong.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        JrbtKhong.setForeground(new java.awt.Color(255, 255, 255));
        JrbtKhong.setText("Không");
        JrbtKhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JrbtKhongActionPerformed(evt);
            }
        });

        jtfGia.setBackground(new java.awt.Color(0, 53, 128));
        jtfGia.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jtfGia.setForeground(new java.awt.Color(255, 255, 255));
        jtfGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfGiaActionPerformed(evt);
            }
        });
        jtfGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfGiaKeyTyped(evt);
            }
        });

        jtfTongPhong.setBackground(new java.awt.Color(0, 53, 128));
        jtfTongPhong.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jtfTongPhong.setForeground(new java.awt.Color(255, 255, 255));
        jtfTongPhong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfTongPhongKeyTyped(evt);
            }
        });

        jtaMoTa.setBackground(new java.awt.Color(0, 53, 128));
        jtaMoTa.setColumns(20);
        jtaMoTa.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jtaMoTa.setForeground(new java.awt.Color(255, 255, 255));
        jtaMoTa.setLineWrap(true);
        jtaMoTa.setRows(5);
        jtaMoTa.setCaretColor(new java.awt.Color(255, 255, 255));
        jtaMoTa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtaMoTaKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jtaMoTa);

        jbtLuuTin.setBackground(new java.awt.Color(76, 175, 80));
        jbtLuuTin.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jbtLuuTin.setForeground(new java.awt.Color(255, 255, 255));
        jbtLuuTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTTT/images/baseline_done_white_24dp1x.png"))); // NOI18N
        jbtLuuTin.setText("Lưu Tin");
        jbtLuuTin.setBorder(null);
        jbtLuuTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtLuuTinActionPerformed(evt);
            }
        });

        jlbDienTich.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlbDienTich.setForeground(new java.awt.Color(255, 255, 255));
        jlbDienTich.setText("Diện tích:");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("(vnđ)");

        jtfDienTich.setBackground(new java.awt.Color(0, 53, 128));
        jtfDienTich.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jtfDienTich.setForeground(new java.awt.Color(255, 255, 255));
        jtfDienTich.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfDienTichKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("(m2)");

        jbtXoaTin.setBackground(new java.awt.Color(255, 51, 51));
        jbtXoaTin.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jbtXoaTin.setForeground(new java.awt.Color(255, 255, 255));
        jbtXoaTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTTT/images/baseline_delete_white_24dp1x.png"))); // NOI18N
        jbtXoaTin.setText("Xóa Tin");
        jbtXoaTin.setBorder(null);
        jbtXoaTin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtXoaTinMouseClicked(evt);
            }
        });

        jbtThemAnh.setBackground(new java.awt.Color(76, 175, 80));
        jbtThemAnh.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jbtThemAnh.setForeground(new java.awt.Color(255, 255, 255));
        jbtThemAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTTT/images/baseline_image_white_24dp1x.png"))); // NOI18N
        jbtThemAnh.setText("Hình ảnh");
        jbtThemAnh.setBorder(null);
        jbtThemAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtThemAnhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtXoaTin, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jbtLuuTin, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlbDienTich, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfDienTich, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(jtfGia)
                    .addComponent(jtfTongPhong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtThemAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JrbtKhong)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(JrbtCo))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfGia, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(jbtThemAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(89, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfTongPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jlbDienTich, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfDienTich, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JrbtCo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(JrbtKhong)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtLuuTin, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtXoaTin, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jcbbQuanHuyen.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jcbbQuanHuyen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbbQuanHuyenActionPerformed(evt);
            }
        });

        jcbbTinhTP.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jcbbTinhTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbbTinhTPActionPerformed(evt);
            }
        });
        jcbbTinhTP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jcbbTinhTPKeyReleased(evt);
            }
        });

        jcbbXPTT.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jcbbXPTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbbXPTTActionPerformed(evt);
            }
        });

        jcbbAnNinh.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jcbbAnNinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbbAnNinhActionPerformed(evt);
            }
        });

        jtaSoNhaTenDuong.setColumns(20);
        jtaSoNhaTenDuong.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jtaSoNhaTenDuong.setLineWrap(true);
        jtaSoNhaTenDuong.setRows(5);
        jtaSoNhaTenDuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtaSoNhaTenDuongKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(jtaSoNhaTenDuong);

        jbtQuayLai.setBackground(new java.awt.Color(0, 53, 128));
        jbtQuayLai.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jbtQuayLai.setForeground(new java.awt.Color(255, 255, 255));
        jbtQuayLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTTT/images/baseline_keyboard_return_white_24dp2x.png"))); // NOI18N
        jbtQuayLai.setText("Quay lại");
        jbtQuayLai.setBorder(null);
        jbtQuayLai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtQuayLaiMouseClicked(evt);
            }
        });
        jbtQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtQuayLaiActionPerformed(evt);
            }
        });

        jcbbLoaiHinhThue.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel13.setText("Loại hình thuê:");

        jbtLamMoi.setBackground(new java.awt.Color(76, 175, 80));
        jbtLamMoi.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jbtLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        jbtLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTTT/images/baseline_refresh_white_24dp1x.png"))); // NOI18N
        jbtLamMoi.setText("Làm mới");
        jbtLamMoi.setBorder(null);
        jbtLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbbAnNinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbbLoaiHinhThue, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 106, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jbtQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(60, 60, 60)
                                        .addComponent(jLabel2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(60, 60, 60)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtfTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                                    .addComponent(jtfSDT))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jcbbTinhTP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jcbbXPTT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                    .addComponent(jcbbQuanHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbbTinhTP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcbbQuanHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbbAnNinh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbbXPTT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbbLoaiHinhThue)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtfGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfGiaActionPerformed
    
    private ThongTinTin layThongTinTin(){
            ttt.setTieuDe(jtfTieuDe.getText());
            ttt.setSDTTin(jtfSDT.getText());
            ttt.setAnNinh(an);
            ttt.setThongTinDiaChi(jtaSoNhaTenDuong.getText());
            ttt.setMaXa(idXa);
            
        return ttt;
    }
    
    private ThongTinPhong layThongTinPhong(){
        xuLyDienTich(1);
        xuLyGia(jtfGia.getText());
            ttp.setMaLoaiPhong(maLP);
            ttp.setDieuHoa(getMayLanh());
            ttp.setGiaPhong(gia);
            ttp.setDienTich(dt);
            ttp.setSoPhong(Integer.parseInt(jtfTongPhong.getText()));
            ttp.setMoTa(jtaMoTa.getText());
           
        return ttp;
    }
    
    private void jbtLuuTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtLuuTinActionPerformed
        if(loai==1){
            if(kiemTraTTCB() && kiemTraTTP()){
                layThongTinTin();
                layThongTinPhong();
                
                long now = System.currentTimeMillis();
                Date sqlDate = new Date(now);
                ttt.setNgayDang(sqlDate);
                try {
                    ThongTinDB.ThemThongTin(ttt, ttp, hinhAnh, ttk);
                    luu=1;
                } catch (SQLException ex) {
                    Logger.getLogger(CapNhat.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                JOptionPane.showMessageDialog(null,"Thêm tin mới thành công!");
                ChuyenManHinh cmh = new ChuyenManHinh(this);
                cmh.setView(new CapNhatMenu());
            }
        }
        else{
            int kq = JOptionPane.showConfirmDialog(null,"Bạn có muốn lưu thay đổi không?","",JOptionPane.YES_NO_OPTION);
            if(kq == JOptionPane.YES_OPTION){
                if(kiemTraTTCB() && kiemTraTTP()){
                    layThongTinTin();
                    layThongTinPhong();
            
                    try {      
                        ThongTinDB.ThemThongTin(ttt, ttp, hinhAnh, ttk);
                        xoaTin(maTin);
                        luu=1;
                    } catch (SQLException ex) {
                        Logger.getLogger(CapNhat.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null,"Đã lưu thay đổi!");
                    ChuyenManHinh cmh = new ChuyenManHinh(this);
                    cmh.setView(new CapNhatMenu());
                    
                }
            }
        }
    }//GEN-LAST:event_jbtLuuTinActionPerformed

    private void jbtThemAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtThemAnhActionPerformed
       
        Hinh hinh = new Hinh(hinhAnh.getDsha()); 
        ha.setDsha(hinh.layDSHinh());
            
    }//GEN-LAST:event_jbtThemAnhActionPerformed

    private void JrbtCoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JrbtCoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JrbtCoActionPerformed

    private void jtfTongPhongKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfTongPhongKeyTyped
        char c = evt.getKeyChar();
        if(!Character.isDigit(c)){
            evt.consume();
        }
        if(jtfTongPhong.getText().length() >= 2){
            evt.consume();
        }
    }//GEN-LAST:event_jtfTongPhongKeyTyped

    public void xoaTin(String maTin) throws SQLException{
        
        Connection conn = DBConnect.getConnection();
        String sql = "DELETE FROM Hinh_Anh where MaTin = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, maTin);
        ps.executeUpdate();
        ps.close();
        
        String sql1 = "DELETE FROM Thong_Tin_Phong where MaTin = ?;";
        PreparedStatement ps1 = conn.prepareStatement(sql1);
        ps1.setString(1, maTin);
        ps1.executeUpdate();
        ps1.close();
        
        String sql2 = "DELETE FROM Thong_Tin_Tin where MaTin = ?;";
        PreparedStatement ps2 = conn.prepareStatement(sql2);
        ps2.setString(1, maTin);
        ps2.executeUpdate();
        ps2.close();
        
        conn.close();
    }
    private void jcbbAnNinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbbAnNinhActionPerformed

    }//GEN-LAST:event_jcbbAnNinhActionPerformed

    private void jcbbXPTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbbXPTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbbXPTTActionPerformed

    private void jcbbTinhTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbbTinhTPActionPerformed

    }//GEN-LAST:event_jcbbTinhTPActionPerformed

    private void jcbbQuanHuyenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbbQuanHuyenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbbQuanHuyenActionPerformed

    private void jtfSDTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfSDTKeyTyped
        char c = evt.getKeyChar();
        if(!Character.isDigit(c)){
            evt.consume();
        }
        if(jtfSDT.getText().length() >= 10){
            evt.consume();
        }
    }//GEN-LAST:event_jtfSDTKeyTyped

    private void jtfSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSDTActionPerformed

    private void jbtQuayLaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtQuayLaiMouseClicked
        if(luu==0){
            int kq = JOptionPane.showConfirmDialog(null,"Bạn có muốn lưu trước khi thoát?","",JOptionPane.YES_NO_OPTION);
         
            if(kq == JOptionPane.YES_OPTION){
                if(kiemTraTTCB() && kiemTraTTP()){
                    layThongTinTin();
                    layThongTinPhong();
                    if(loai==1){
                        long now = System.currentTimeMillis();
                        Date sqlDate = new Date(now);
                        ttt.setNgayDang(sqlDate);
                    }
                     
                    try {
                        xoaTin(maTin);
                        ThongTinDB.ThemThongTin(ttt, ttp, hinhAnh, ttk);
                    } catch (SQLException ex) {
                        Logger.getLogger(CapNhat.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    luu=1;
                    JOptionPane.showMessageDialog(null,"Đã lưu tin!");
                    ChuyenManHinh cmh = new ChuyenManHinh(this);
                    cmh.setView(new CapNhatMenu());   
                }  
            }
            else{
                ChuyenManHinh cmh = new ChuyenManHinh(this);
                cmh.setView(new CapNhatMenu());   
            }
        }
        else{
            ChuyenManHinh cmh = new ChuyenManHinh(this);
            cmh.setView(new CapNhatMenu());
        }
    }//GEN-LAST:event_jbtQuayLaiMouseClicked

    private void jbtXoaTinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtXoaTinMouseClicked
        int kq = JOptionPane.showConfirmDialog(null,"Bạn chắc chắn muốn xóa tin này?","",JOptionPane.YES_NO_OPTION);
        if(kq == JOptionPane.YES_OPTION){
            if(loai==0){
                int kq1 = JOptionPane.showConfirmDialog(null,"Sau khi xoá không thể khôi phục tin, Xác nhận?","",JOptionPane.YES_NO_OPTION);
                if(kq1 == JOptionPane.YES_OPTION){
                    try {
                        xoaTin(maTin);
                    } catch (SQLException ex) {
                        Logger.getLogger(CapNhat.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                JOptionPane.showMessageDialog(null,"Đã xóa tin!");
                    ChuyenManHinh cmh = new ChuyenManHinh(this);
                    cmh.setView(new CapNhatMenu());
            }
        }
    }//GEN-LAST:event_jbtXoaTinMouseClicked

    private void jbtLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtLamMoiActionPerformed
        taoMoiTTCB(); 
        taoMoiTTP();
    }//GEN-LAST:event_jbtLamMoiActionPerformed

    private void jcbbTinhTPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcbbTinhTPKeyReleased
        System.out.print(jcbbAnNinh.getKeyListeners());
        System.out.print("da");
    }//GEN-LAST:event_jcbbTinhTPKeyReleased
    
    private void xuLyGia(String gia){
   
        for(int i=0;i<gia.length();i++){
            if(!Character.isDigit(gia.charAt(i))){
                gia = gia.substring(0,i) + gia.substring(i+1,gia.length());
                i--;
            }
        }
        this.gia = gia;
        if(gia.length()>3 && gia.length()<7)
            gia = gia.substring(0,gia.length()-3) + "." + gia.substring(gia.length()-3,gia.length());
        else if(gia.length()>6 && gia.length()<9){
            gia = gia.substring(0,gia.length()-3) + "." + gia.substring(gia.length()-3,gia.length());
            gia = gia.substring(0,gia.length()-7) + "." + gia.substring(gia.length()-7,gia.length());
        }
        jtfGia.setText(gia);

    }
    private void jtfGiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfGiaKeyTyped
      
        char c = evt.getKeyChar();
        if(!Character.isDigit(c)){
            evt.consume();
        }
        if(jtfGia.getText().length() >= 10){
            JOptionPane.showMessageDialog(null,"Số tiền giao dịch quá lớn!");
            evt.consume();
        }
        xuLyGia(jtfGia.getText());
    }//GEN-LAST:event_jtfGiaKeyTyped
    
    // 0 là kiểm tra có dấu thập phân chưa, 1 là lấy chuỗi chỉ có số;
    private boolean xuLyDienTich(int a){
        if(a==1){
                this.dt = jtfDienTich.getText();
            }  
        for(int i=0;i<jtfDienTich.getText().length();i++){
            if(jtfDienTich.getText().charAt(i)=='.' || jtfDienTich.getText().charAt(i)==','){
                this.dt = dt.replace(',', '.');
                return true;
            }
        }
        return false;
    }
    private void jtfDienTichKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfDienTichKeyTyped
        char c = evt.getKeyChar();
        if( !Character.isDigit(c) && c!='.'  && c!=','){
            evt.consume();
        }
        if( (c=='.' && xuLyDienTich(0)) || (c==',' && xuLyDienTich(0)) )
            evt.consume();
        if(jtfDienTich.getText().length() >= 7){
            evt.consume();
        }
    }//GEN-LAST:event_jtfDienTichKeyTyped

    private void jtfTieuDeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfTieuDeKeyTyped
        char c = evt.getKeyChar();
        if(jtfTieuDe.getText().length() > 300){
            JOptionPane.showMessageDialog(null,"Tên Tiêu đề chỉ chứa tối đa 300 kí tự!");
            evt.consume();
        }
    }//GEN-LAST:event_jtfTieuDeKeyTyped

    private void jtaSoNhaTenDuongKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtaSoNhaTenDuongKeyTyped
        char c = evt.getKeyChar();
        if(jtaSoNhaTenDuong.getText().length() > 100){
            JOptionPane.showMessageDialog(null,"'Địa chỉ' chứa tối đa 50 kí!");
            evt.consume();
        }
    }//GEN-LAST:event_jtaSoNhaTenDuongKeyTyped

    private void jtaMoTaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtaMoTaKeyTyped
//        char c = evt.getKeyChar();
//        if(jtaMoTa.getText().length() > 200){
//            JOptionPane.showMessageDialog(null,"'Mô tả phòng' chứa tối đa 200 kí!");
//            evt.consume();
//        }
    }//GEN-LAST:event_jtaMoTaKeyTyped

    private void jbtQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtQuayLaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtQuayLaiActionPerformed

    private void JrbtKhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JrbtKhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JrbtKhongActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton JrbtCo;
    private javax.swing.JRadioButton JrbtKhong;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtLamMoi;
    private javax.swing.JButton jbtLuuTin;
    private javax.swing.JButton jbtQuayLai;
    private javax.swing.JButton jbtThemAnh;
    private javax.swing.JButton jbtXoaTin;
    private javax.swing.JComboBox jcbbAnNinh;
    private javax.swing.JComboBox jcbbLoaiHinhThue;
    private javax.swing.JComboBox jcbbQuanHuyen;
    private javax.swing.JComboBox jcbbTinhTP;
    private javax.swing.JComboBox jcbbXPTT;
    private javax.swing.JLabel jlbDienTich;
    private javax.swing.JTextArea jtaMoTa;
    private javax.swing.JTextArea jtaSoNhaTenDuong;
    private javax.swing.JTextField jtfDienTich;
    private javax.swing.JTextField jtfGia;
    private javax.swing.JTextField jtfSDT;
    private javax.swing.JTextField jtfTieuDe;
    private javax.swing.JTextField jtfTongPhong;
    // End of variables declaration//GEN-END:variables

}
