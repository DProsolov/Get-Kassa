import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.ConnectException;
import java.net.URL;

public class Conn {
    String addr = "https://test.creditpilot.ru:";
    int port = 8080;
    String path = "/KPDealerWeb/KPBossHttpServer?";
    private static final int AUTH = 1;
    private static final int CHECK = 2;
    private static final int PAY = 3;
    String cmd;
    URL obj;
    String error;
    public int responseCode;
    StringBuilder response;
    private int resultCode;
    private String desc;
    private String comm;
    public boolean authin = false;

    public static void main(String[] args) throws Exception {
        Conn conn = new Conn();
        conn.openConn(1);
        conn.parse(1);
    }

    public void openConn(int step) throws Exception {
        switch (step) {
            case AUTH:
                comm = "ACCOUNT";
                cmd = addr + port + path + "actionName=" + comm;
                break;
            default:
                System.out.println("Не задана команда");
        }
        obj = new URL(cmd);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        AuthWindow authw = new AuthWindow();
        String auth = authw.slogin + ":" + authw.spassw;
        System.out.println(auth);
        String authStringEnc = Base64.encode(auth.getBytes());
        con.setRequestProperty("Authorization", "Basic " + authStringEnc);
        System.out.println("\nSending 'POST' request: " + obj.toString());

        try {
            response = new StringBuilder();
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (ConnectException e) {
            error += e;
        }
        con.disconnect();
    }

    public void parse(int step) throws Exception {
        try {
            System.out.println("Response Code : " + responseCode);
            System.out.println(response.toString());
            System.out.println("Парсим XML");
            XMLStreamReader xmlr = XMLInputFactory.newInstance().createXMLStreamReader(new StringReader(response.toString()));
            while (xmlr.hasNext()) {
                xmlr.next();
                if (xmlr.isStartElement() && xmlr.getLocalName().equals("result") &&
                        xmlr.getAttributeLocalName(0).equals("resultCode")) {
                    resultCode = Integer.parseInt(xmlr.getAttributeValue(0));
                    desc = xmlr.getAttributeValue(1);
                }
            }
            switch (step) {
                case AUTH:
                    if (comm.equals("ACCOUNT") && resultCode == 0) {
                        authin = true;
                        System.out.println("Команда AUTH выполнена успешно!");
                    } else {
                        System.out.println(resultCode + " " + desc);
                    }
                    break;
                default:
                    System.out.println("Пустышка");
            }
        } catch (Throwable e) {
            System.out.println("Не прокатило " + e);
        }
    }
}
