/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jkero
 */
public class DBConnection {
        static String daneZBazy;
  private final String DRIVER = "com.mysql.jdbc.Driver";
    public Connection Connect() {
        try {
            Class.forName(DRIVER);
            System.out.println("1.Zarejestrowano sterownik");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mkl_database","root","");
            return conn;
        } catch (ClassNotFoundException ex) {
            System.err.println("Niewłasciwy sterownik lub jego brak");
        } catch (SQLException ex) {
           System.out.println("probelm z otwarciem polaczenia");
        }
         
        return null;
    }
}
