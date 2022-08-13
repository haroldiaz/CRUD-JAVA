
package Views;

import Principal.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class TablaController implements Initializable {

   private Main ventana;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }  
    
         
    public void setVentanaPrincipal(Main main)
    {
        
        this.ventana = main;
    }
    
    @FXML
    void verRegistroEmpleados()
    {
        this.ventana.Menu();
    }
}
