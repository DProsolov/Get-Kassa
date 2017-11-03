import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

class MainWindow extends JFrame {
    JPanel pnl = new JPanel();

    public static void main(String[] args) {
        AuthWindow auth = new AuthWindow();
        //MainWindow mw = new MainWindow();
    }

    public MainWindow() {
        super("Get-Kassa");
        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(pnl);
        setVisible(true);
    }
}
