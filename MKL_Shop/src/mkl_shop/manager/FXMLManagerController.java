/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.manager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import mkl_shop.connection.DBConnection;

/**
 * FXML Controller class
 *
 * @author jkero
 */
public class FXMLManagerController implements Initializable {

    /**
     * Initializes the controller class.
     */
     private DBConnection dc;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       dc = new DBConnection();
          java.sql.Connection conn = dc.Connect();
    }    
    
}
