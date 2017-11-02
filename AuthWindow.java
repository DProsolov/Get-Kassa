import javafx.application.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;
import java.util.Properties;

public class AuthWindow extends JFrame implements ActionListener {
        JPanel pnl = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        JButton btn = new JButton( "Войти" );
        JButton btn1 = new JButton( "Отмена" );
        JTextField login = new JTextField("Логин", 17);
        JPasswordField passw = new JPasswordField ("Пароль", 17);
        String[] servers = {"Основной рабочий сервер","Тестовый сервер"};
        JComboBox serversbox = new JComboBox(servers);
        private Properties success = new Properties();
        String slogin = "";
        String spassw = "";
        boolean succ;
        char[] checPass = {'t', 'e', 's', 't'};
        char[] entPass;

        public static void main(String[] args) {
            AuthWindow gui = new AuthWindow();
        }

        public AuthWindow() {
            super("Get-Kassa");
            setSize(330, 240);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            add(pnl);
            setVisible(true);
            setLayout(gbl);

            c.anchor = GridBagConstraints.NORTH;
            c.fill = GridBagConstraints.NONE;
            c.gridheight = 1;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridx = GridBagConstraints.RELATIVE;
            c.gridy = GridBagConstraints.RELATIVE;
            c.insets = new Insets(1, 0, 0, 0);
            c.ipadx = 0;
            c.ipady = 0;
            c.weightx = 0;
            c.weighty = 0;
            gbl.setConstraints(login, c);
            add(login);
            login.addFocusListener(new FocusListener() {
                public void focusGained(FocusEvent e) {
                    login.setSelectionStart(0);
                    login.setSelectionEnd(10);
                }
                public void focusLost(FocusEvent e) {
                    login.select(0 ,0);
                }
            });

            c.insets = new Insets(2, 10, 0, 0);
            gbl.setConstraints(passw, c);
            passw.setEchoChar('*');
            add(passw);
            /*passw.addFocusListener(new FocusListener() {
                public void focusGained(FocusEvent e) {
                    login.setSelectionStart(0);
                    login.setSelectionEnd(10);
                }
                public void focusLost(FocusEvent e) {
                    login.select(0 ,0);
                }
            });*/

            c.insets = new Insets(3, 10, 0, 0);
            c.ipadx = 6;
            gbl.setConstraints(serversbox, c);
            add(serversbox);

            c.insets = new Insets(4, 12, 0, 0);
            c.gridwidth = GridBagConstraints.WEST;
            gbl.setConstraints(btn, c);
            add(btn);
            btn.addActionListener(this);

            c.insets = new Insets(4, 30, 0, 0);
            c.gridwidth = GridBagConstraints.EAST;
            gbl.setConstraints(btn1, c);
            add(btn1);
            btn1.addActionListener(this);
        }

        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == btn) {
                //success.setProperty(login.getText(), passw.getPassword().toString());
                slogin = login.getText();
                spassw = passw.getPassword().toString();
                entPass = passw.getPassword();
                if (slogin.equals("sol_oper") && Arrays.equals(entPass, checPass)) {
                    succ = true;
                    //JOptionPane.showMessageDialog(this, slogin, "Верно", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    succ = false;
                    JOptionPane.showMessageDialog(this,
                            slogin + "/" + spassw, "Ошибка 1",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (event.getSource() == btn1) {
                System.exit(0);
            }
        }


}
