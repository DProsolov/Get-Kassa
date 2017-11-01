import javax.swing.*;

public class MainWindow extends JFrame {
    JPanel pnl = new JPanel();

    public static void main(String[] args) {
        AuthWindow auth = new AuthWindow();
        if (auth.succ) {
            auth.dispose();
            MainWindow gui = new MainWindow();
        }
    }

    public MainWindow() {
        super("Get-Kassa");
        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(pnl);
        setVisible(true);
    }
}
