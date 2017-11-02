import javax.swing.*;
import java.awt.*;

class MainWindow extends JFrame {
    JPanel pnl = new JPanel();

    public static void main(String[] args) {
        AuthWindow auth = new AuthWindow();
        //MainWindow gui = new MainWindow();
    }

    public MainWindow() {
        super("Get-Kassa");
        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(pnl);
        setVisible(true);
    }
}
