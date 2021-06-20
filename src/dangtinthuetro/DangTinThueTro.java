package dangtinthuetro;

import DTTT.view.MainScreen;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JFrame;

public class DangTinThueTro extends JFrame{
        
    public static void main(String[] args) throws IOException, SQLException {
        new  MainScreen().setVisible(true);
    }
}
