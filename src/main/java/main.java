

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.zip.GZIPInputStream;

public class main {

    public static void main (String [] args) {

        String url = "URL";
        String login = "LOGIN";
        String pass = "PASS";
        String xmlString;
        try {

            Connection con = DriverManager.getConnection(url,login,pass);
            Statement stmt = con.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT INS_REQ_JOUR_ID,REQ_BODY FROM INS_REQUEST_JOUR WHERE INS_REQ_JOUR_ID = 1873581991");
            while(rs.next()) {
                int id = rs.getInt(1);
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
