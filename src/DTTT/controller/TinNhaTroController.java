/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTTT.controller;

/**
 *
 * @author Snorlax
 */

import DTTT.model.Anh;
import DTTT.model.ChuanHoa;
import DTTT.model.LoaiPhong;
import DTTT.utility.MultiLineTableCellRenderer;
import DTTT.model.QuanHuyen;
import DTTT.model.ThanhPho;
import DTTT.model.ThongTinPhong;
import DTTT.model.ThongTinTin;
import DTTT.model.XaPhuong;
import DTTT.service.AnhService;
import DTTT.service.AnhServiceImpl;
import DTTT.service.ThanhPhoService;
import DTTT.service.ThanhPhoServiceImpl;
import DTTT.service.ThongTinPhongService;
import DTTT.service.ThongTinPhongServiceImpl;
import DTTT.service.ThongTinTinService;
import DTTT.service.ThongTinTinServiceImpl;
import DTTT.utility.ClassTableModel;
import DTTT.utility.TextTableRenderer;
import DTTT.view.PhongJDialog;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Vector;
import javax.accessibility.AccessibleContext;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author TVD
 */
public class TinNhaTroController {
    String MaTinTin;
    ThongTinPhong tinphong = new ThongTinPhong();
    LoaiPhong lp = new LoaiPhong();
    Anh anh = new Anh();
    //N??t n??y ch??a x??i t???i
    private JPanel jpnView;
//    private JButton btnAdd;
    private JTextField jtfSearch;
    private JComboBox jcbLoai;
    private JLabel jlbAnh;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    
    PhongJDialog jdialog = new PhongJDialog(tinphong, lp);
    private ClassTableModel classTableModel = null;
    
    private final String[] COLUMNS = {"M?? tin","???nh", "Ti??u ?????",
        "Th??ng tin ?????a ch???", "Th??ng tin chung","Gi?? Ph??ng" };
    
    
    private ThongTinTinService thongTinService = null;
    private AnhService anhService = null;
    private TableRowSorter<TableModel> rowSorter = null;
    private ThongTinPhongService thongTinPhongService = null;
    private ThanhPhoService thanhPhoService = null;
//    private List<String> dsMaHinh = null;
    ThongTinTin tin = null;
    public TinNhaTroController(JPanel jpnView, JTextField jtfSearch,JComboBox jcbLoai) {
        this.jpnView = jpnView;
        this.jcbLoai = jcbLoai;
        this.jtfSearch = jtfSearch;
        this.thongTinPhongService = new ThongTinPhongServiceImpl();
        this.classTableModel = new ClassTableModel();
        this.anhService = new AnhServiceImpl();
        this.thongTinService = new ThongTinTinServiceImpl();
//        this.xaPhuongService = new XaPhuongServiceImpl();
        this.thanhPhoService = new ThanhPhoServiceImpl();
    }

    
    
    // set th??ng tin v??o b???ng
    public void setDataToTable() throws IOException, SQLException {
        
        //Danh s??ch c??c list c?? li??n quan t???i th??ng tin ???????c show tr??n b???ng
        List<ThongTinTin> listItem = thongTinService.getList();
        List<Anh> listAnh = anhService.getList();
        List<ThongTinPhong> listPhong = thongTinPhongService.getList();
        List<XaPhuong> listXP = thanhPhoService.getXPList();
        List<ThanhPho> listTP = thanhPhoService.getTPList();
        List<QuanHuyen> listQH = thanhPhoService.getQHList();
        List<LoaiPhong> listLP = thongTinPhongService.getLPList();
        
        DefaultTableModel model = classTableModel.setTableThongTinTin(listLP ,listPhong,listQH ,listTP,listXP, listAnh, listItem, COLUMNS);
        
        JTable table = new JTable(model);
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        
        
        // x??? l?? ho???t ?????ng t??m ki???m cho jtfSearch
        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(jcbLoai.getSelectedIndex()==0){
                    String text = jtfSearch.getText();               
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text,0));
                  }
                }
                if(jcbLoai.getSelectedIndex()==1){
                    String text = jtfSearch.getText();               
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text,3));
                  }
                }
                if(jcbLoai.getSelectedIndex()==2){
                    String text = jtfSearch.getText();               
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text,5));
                  }
                }
                
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();  
                if(jcbLoai.getSelectedIndex()==0){
                                 
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text,0));
                  }
                }
                
                if(jcbLoai.getSelectedIndex()==1){
                                 
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text,3));
                  }
                }
                
                if(jcbLoai.getSelectedIndex()==2){
                                 
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text,5));
                  }
                }
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        }); 
              
        // c??c thao t??c v???i b???ng 
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // getClickCoung = 2 => nh???p ????p v??o
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    
                    // L???y m?? tin ??? h??ng ???????c ch???n v?? ??? c???t 0
                    int selectedRowIndex = table.getSelectedRow();
                    String MaTinTin = ChuanHoa.chuanHoaMa((String)table.getValueAt(selectedRowIndex, 0)) ;
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
//                    table.setDefaultRenderer(String.class, new LineWrapCellRenderer());
                    //l???y ti??u ????? ??? h??ng ???????c ch???n c???t 2
                    String TieuDe = model.getValueAt(selectedRowIndex, 2).toString();
                    
                    int loaiPhongIndex = 0;
                    int phongIndex = 0;
                    String MaTinPhong = null;
                    String TenPhong = null;
                    
                    // v??ng for ????? duy???t l???y t???ng tin ????? v??o table
                    for (int i = 0; i < listItem.size(); i++) {

                        for( int j = 0; j < listPhong.size(); j++){
                            tinphong = listPhong.get(j);    
                            MaTinPhong = ChuanHoa.chuanHoaMa(tinphong.getMaTin());
                            if (MaTinTin.equals(MaTinPhong)){
                                phongIndex = j;
                            }         
                        }
                         
                                tinphong = listPhong.get(phongIndex); 
                        // v??ng for ????? duy???t l???y t???ng ph??ng ????? v??o jdialog         
                        for( int k = 0; k < listLP.size(); k++  ){
                                lp = listLP.get(k);
                                if (tinphong.getMaLoaiPhong().equals(lp.getMaLoaiPhong())){
                                    loaiPhongIndex = k;
                                    break;
                                }   
                                
                        } 
                        
                        lp = listLP.get(loaiPhongIndex);
                        
                        jdialog.setTinPhong(tinphong, lp); 
                        
                    }
                    // set th??ng s??? cho jdialog
                    jdialog.setTitle(TieuDe);
                    jdialog.setVisible(false);
                    jdialog.setBounds(90, 90,730, 440);
                    jdialog.setLocationRelativeTo(null);
                    jdialog.setVisible(true);
                    
                }
            }
           
        
            
            
        });


        //Thi???t k??? 
        
        table.setFont(new Font("Arial",0,18));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(200);
        table.setShowVerticalLines(true);
        table.setBackground(new Color(224, 236, 255));
        table.setSelectionBackground(new Color(255, 196, 125));
        table.setGridColor(new Color(230, 200, 29));
        
        table.validate();
        table.repaint();
        
        // C??n tr??i ph???i gi???a cho c???t trong jtable
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer testRenderer = new DefaultTableCellRenderer();
        
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        testRenderer.setHorizontalAlignment(JLabel.HEIGHT);

        MultiLineTableCellRenderer renderer = new MultiLineTableCellRenderer();
        table.setDefaultRenderer(String[].class, renderer);
        
        //C???t m?? tin
        table.getColumnModel().getColumn(0).setMaxWidth(48);
        table.getColumn("M?? tin").setCellRenderer(centerRenderer);
         
        //C???t ?????a ch???
//        table.getColumnModel().getColumn(3).setCellRenderer(new TextTableRenderer());
        
//        table.setRowHeight(0, 80);
    
        //C???t h??nh ???nh
        table.getColumnModel().getColumn(1).setMaxWidth(300);
        table.getColumnModel().getColumn(1).setMinWidth(300);
       
        //C???t tr???ng th??i
        table.getColumnModel().getColumn(5).setMaxWidth(150);
        table.getColumnModel().getColumn(5).setMinWidth(150);
        table.getColumn("Gi?? Ph??ng").setCellRenderer(centerRenderer);

        //C???t ti??u ?????
        table.getColumnModel().getColumn(3).setPreferredWidth(300);
        
        
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(new Dimension(1800, 400));
        jpnView.removeAll();
        jpnView.setLayout(new CardLayout());
        jpnView.add(scroll);
        jpnView.validate();
        jpnView.repaint();

    }
    
    
}
