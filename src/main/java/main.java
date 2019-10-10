

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.zip.GZIPInputStream;

public class main {

    public static void main (String [] args) {

        String url = "jdbc:oracle:thin:@172.19.23.24:1521:rsadb2";
        String login = "rsa_eosago";
        String pass = "soP63KgoHhwZ2KtrA";
        String xmlString;
        try {

            Connection con = DriverManager.getConnection(url,login,pass);
            Statement stmt = con.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT INS_REQ_JOUR_ID,REQ_BODY FROM INS_REQUEST_JOUR WHERE INS_REQ_JOUR_ID = 2714017050");
            while(rs.next()) {
                Long id = rs.getLong(1);
                Blob blob = rs.getBlob(2);
                byte[] buffer = blob.getBytes(1L,(int)blob.length());
                xmlString = IOUtils.toString(new GZIPInputStream(new ByteArrayInputStream(buffer)),"UTF-8");
                System.out.println(id + " " + xmlString);
                //int id = rs.getInt(1);
                //System.out.println(blob);
            }
            con.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
