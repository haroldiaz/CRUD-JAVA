
package Views;

import BaseDatos.BaseDatos;
import Principal.Main;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class MenuController implements Initializable {

    Main ventana;
    @FXML
    private JFXTextField nombre_text;
    @FXML
    private JFXTextField apellido_text;
    @FXML
    private JFXTextField correo_text;
    @FXML
    private JFXComboBox<String> combo_edad;
    @FXML
    private JFXTextField edad_text;
    
    BaseDatos bd = new BaseDatos();
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        bd.conectar();
       agregarSexoComobox();
    }    
    public void setVentanaPrincipal(Main main)
    {
        
        this.ventana = main;
    }
    
    void agregarSexoComobox()
    {
        combo_edad.getItems().add("MASCULINO");
        combo_edad.getItems().add("FEMENINO");
    }
    
}
