package DTTT.controller;

import DTTT.bean.DanhMuc;
import DTTT.dao.KTTK;
import DTTT.view.CapNhatMenu;
import DTTT.view.DangNhap;
import DTTT.view.HienThiLichHen;
import DTTT.view.ThongKeJPanel;
import DTTT.view.ThongTinTaiKhoan;
import DTTT.view.TrangChinh;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;




public class ChuyenManHinh {
    ThongTinTaiKhoan dl = new ThongTinTaiKhoan();
    public ChuyenManHinh(){
        
    }
    
    private JPanel root,root1,node1;
    private String chon="theme";
    private List<DanhMuc> listItem=null;
    
    public ChuyenManHinh(JPanel jpnRoot){
        this.root = jpnRoot;
    }
    
    public void setView() throws IOException {
       
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new DangNhap());
        root.validate();
        root.repaint();
    }
    
    public void setView(JPanel jpn){

        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(jpn, BorderLayout.CENTER);
        root.validate();
        root.repaint();
        
    }
    
    
    public void setEvent(List<DanhMuc> listItem){
        this.listItem=listItem;
        for(DanhMuc item:listItem){
            item.getJlb().addMouseListener(new LabelEvent(item.getLoai(), item.getJpn(), item.getJlb()));
        }
    }
    
    class LabelEvent implements MouseListener{
        private JPanel node;
        private JFrame jfr;
        private String loai;
        private JPanel jpnItem;
        private JLabel jlbItem;
        ThongTinTaiKhoan dl = new ThongTinTaiKhoan();
        int thu;
        int dem = 0;
        public LabelEvent(String loai, JPanel jpnItem, JLabel jlbItem) {
            this.loai = loai;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }
        
      
        @Override
        public void mousePressed(MouseEvent e) {
            chon=loai;
            jpnItem.setBackground(new Color(15,65,134));
            jlbItem.setBackground(new Color(15,65,134));
            switch(loai){
                case "LichHen":
                {
                    dem = 0;
                    if(KTTK.getTtk().length()!=0){
                        try {
                            node = new HienThiLichHen();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        thu=1;
                    }
                    else{
                            JOptionPane.showMessageDialog(null, "Bạn cần đăng nhập tài khoản cho chức năng này!");
                            thu=1;
                        try {
                            node = new DangNhap();
                        } catch (IOException ex) {
                            Logger.getLogger(ChuyenManHinh.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                break;
                    
                }
                
                case "ThongKe":
                    dem = 0;
                    node = new ThongKeJPanel();
                    thu = 1;
                    break;
                case "TrangChinh":
                {
                        dem = 0;
                    try {
                        node = new TrangChinh();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                }
                }thu=1;
                break;
                

                case "CapNhat":
                    dem = 0;
                    if(KTTK.getTtk().length()!=0){
                        node = new CapNhatMenu();
                        thu=1;
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Bạn cần đăng nhập tài khoản cho chức năng này!");
                try {
                    node = new DangNhap();
                } catch (IOException ex) {
                    Logger.getLogger(ChuyenManHinh.class.getName()).log(Level.SEVERE, null, ex);
                }
                        thu=1;
                    }
                break;                
                case "DangNhap": 
                        if(KTTK.getTtk().length()==0){
                try {
                    node = new DangNhap();
                } catch (IOException ex) {
                    Logger.getLogger(ChuyenManHinh.class.getName()).log(Level.SEVERE, null, ex);
                }
thu=1;
                        }
                        else if(KTTK.getTtk().length()!=0){
                try {
                    
                    dl.toFront();
                    if(!dl.isShowing()){
                    dl = new ThongTinTaiKhoan(KTTK.getTtk());
                    dl.setVisible(false);
                    dl.setBounds(10, 120, 500, 300);
                    dl.setLocationRelativeTo(null);                                                                                                                      
                    dl.setVisible(true);
                    dl.setAutoRequestFocus(true);
                    dl.toFront();           
                    }                   
                    
                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(ChuyenManHinh.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ChuyenManHinh.class.getName()).log(Level.SEVERE, null, ex);
                }
                             
                        }
                break;
               
                default: break;
            }
            if(thu==1){
                setView(node);
            }
            else{
                root.removeAll();
                root.setLayout(new BorderLayout());
                root.validate();
               
            }
            setChangeBackground(loai);
        }


        @Override
        public void mouseClicked(MouseEvent e) {
            chon=loai;
            jpnItem.setBackground(new Color(15,65,134));
            jlbItem.setBackground(new Color(15,65,134));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(15,65,134));
            jlbItem.setBackground(new Color(15,65,134));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(!chon.equalsIgnoreCase(loai)){
                jpnItem.setBackground(new Color(0,54,128));
                jlbItem.setBackground(new Color(0,54,128));
            }
        }

        
        
    }
    
    private void setChangeBackground(String loai){
        for(DanhMuc item:listItem){
            if(item.getLoai().equalsIgnoreCase(loai)){
                item.getJpn().setBackground(new Color(15,65,134));
                item.getJlb().setBackground(new Color(15,65,134));
            }
            else{
                item.getJpn().setBackground(new Color(0,54,128));
                item.getJlb().setBackground(new Color(0,54,128));
            }
        }
    }
   
}
