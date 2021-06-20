package DTTT.dao;
import DTTT.model.ChuanHoa;
import DTTT.model.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiKhoanDB{
    
    public static TaiKhoan login(String tenDN, String mk){
        Connection conn = DBConnect.getConnection();
        String sql = "SELECT * FROM Tai_Khoan Where TaiKhoan = ? and MatKhau =?";
        TaiKhoan taiKhoan = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tenDN);
            ps.setString(2, mk);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                taiKhoan = new TaiKhoan();
                taiKhoan.setTenTK(rs.getString(1));
                taiKhoan.setMk(rs.getString(2));
            }  
            ps.close();
            conn.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taiKhoan;
    }
    
    public static void  themTaiKhoan(TaiKhoan tk){
      
        try{
            Connection conn = DBConnect.getConnection();
            String sql = "INSERT INTO Tai_Khoan(TaiKhoan, MatKhau, SDT, HoTen, Gmail) VALUES(?,?,?,?,?)";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            
            ps.setString(1,tk.getTenTK());
            ps.setString(2,tk.getMk());
            ps.setString(3,tk.getSdt());
            ps.setString(4,tk.getHoTen());
            ps.setString(5,tk.getGmail());
            
            ps.executeUpdate();
            System.out.println("Đã thêm tài khoản");
            
            ps.close();
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static int kTTKTonTai(String tenTK) throws SQLException{
        
        int thu=0; 
        Connection conn = DBConnect.getConnection();
        String sql = "SELECT * FROM Tai_Khoan";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            if(rs.getString("TaiKhoan").trim().equals(tenTK)){
                thu = 1;
            }
        }
        ps.close();conn.close();
        return thu;
    }
    public static TaiKhoan luuTTTaiKhoan(String taiKhoan) throws SQLException{
        Connection conn = DBConnect.getConnection();
        String sql = "SELECT * FROM Tai_Khoan Where TaiKhoan = ?";
        TaiKhoan tK = null;
        PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, taiKhoan);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                tK = new TaiKhoan();
                tK.setTenTK(ChuanHoa.xoaKhoangTrang(rs.getString("TaiKhoan")));
                tK.setHoTen(rs.getString("HoTen"));
                tK.setSdt(rs.getString("SDT"));
                tK.setMk(rs.getString("MatKhau"));
                tK.setGmail(ChuanHoa.xoaKhoangTrang(rs.getString("Gmail")));
            }  
            ps.close();
            conn.close(); 
            return tK;
    }
    public static TaiKhoan getTTTaiKhoanQuaMail(String mail) throws SQLException{
        Connection conn = DBConnect.getConnection();
        String sql = "SELECT * FROM Tai_Khoan Where Gmail = ?";
        TaiKhoan tK = null;
        PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mail);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                tK = new TaiKhoan();
                tK.setTenTK(ChuanHoa.xoaKhoangTrang(rs.getString("TaiKhoan")));
                tK.setHoTen(rs.getString("HoTen"));
                tK.setSdt(rs.getString("SDT"));
                tK.setMk(rs.getString("MatKhau"));
                tK.setGmail(ChuanHoa.xoaKhoangTrang(rs.getString("Gmail")));
            }  
            ps.close();
            conn.close(); 
            return tK;
    }
    public static boolean KTTTTaiKhoan(String taiKhoan, String matKhau) throws SQLException{
        Connection conn = DBConnect.getConnection();
        String sql = "SELECT * FROM Tai_Khoan Where TaiKhoan = ? and MatKhau =?";
        TaiKhoan tK = null;
        PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, taiKhoan);
            ps.setString(2, matKhau);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                return true;
            }  
            ps.close();
            conn.close(); 
            return false;
    }
    public static String getMailtaiKhoan(String taiKhoan) throws SQLException{
        Connection conn = DBConnect.getConnection();
        String mail = null;
        String sql = "SELECT * FROM Tai_Khoan Where TaiKhoan = ? ";
        TaiKhoan tK = null;
        PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, taiKhoan);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                mail = rs.getString("Gmail");
            }  
            ps.close();
            conn.close(); 
            return mail;
    }
    public static void updateTaiKhoan(TaiKhoan tk) throws SQLException{
        Connection conn = DBConnect.getConnection();
                String sql = "UPDATE Tai_Khoan SET HoTen = ?, SDT = ?, Gmail = ?, MatKhau = ?  WHERE TaiKhoan = ?";
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
                ps.setString(1, tk.getHoTen());
                ps.setString(2, tk.getSdt());
                ps.setString(3, tk.getGmail());
                ps.setString(4, tk.getMk());
                ps.setString(5, tk.getTenTK());
                ps.executeUpdate();
                ps.close();
    }
    public static boolean checktrungMk(String gmail, String mK) throws SQLException{
        Connection conn = DBConnect.getConnection();
        String mail = null;
        String sql = "SELECT * FROM Tai_Khoan Where Gmail = ? ";
        TaiKhoan tK = null;
        PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, gmail);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                String mkTam = ChuanHoa.xoaKhoangTrang(rs.getString("MatKhau"));
                if(mK.equals(mkTam)){
                    return true;
                }
            }  
            ps.close();
            conn.close(); 
            return false;
    }
    public static void main(String[] args) throws SQLException {
        System.out.println("trung: " + checktrungMk("huynhquockhanh0907@gmail.com","111222"));
    }
}
