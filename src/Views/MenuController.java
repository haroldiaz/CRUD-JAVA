
package Views;

import Principal.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


public class MenuController implements Initializable {

    Main ventana;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    public void setVentanaPrincipal(Main main)
    {
        this.ventana = main;
    }
    
}
