/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package macaddr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author KimDaHyun
 */
public class runayo {

    ResultSet rs;

    public void getResultSet() {
        try {
            String c1, c2, c3, c4;
            String ipServer, scope, ipRange, ip, mac;
            c1 = "netsh dhcp server ";
            ipServer = "10.126.15.10";
            c2 = " scope ";
            scope = "10.126.15.0";
            c3 = " add reservedip ";
            ipRange = "10.126.15.";
            // create our mysql database connection
            String myUrl = "jdbc:mysql://localhost/lpsi";
            Connection conn = DriverManager.getConnection(myUrl, "root", "");

            // our SQL SELECT query. 
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "SELECT * FROM table1";

            // create the java statement
            Statement st = conn.createStatement();

            //netsh dhcp server 10.126.15.10 scope 10.126.15.0 add reservedip 10.126.15.11 FCAA141F2C66
            
            // execute the query, and get a java resultset
            rs = st.executeQuery(query);
            while (rs.next()) {
                mac = rs.getString("mac");
                ip = rs.getString("ip");
                ip = ipRange+ip+" ";
                mac = mac.replaceAll("[^\\p{IsAlphabetic}^\\p{IsDigit}]", "");
                System.out.println(c1+ipServer+c2+scope+c3+ip+mac);
            }
            st.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}
