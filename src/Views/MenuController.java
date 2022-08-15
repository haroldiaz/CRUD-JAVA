
package Views;

import BaseDatos.BaseDatos;
import Principal.Empleado;
import Principal.Main;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class MenuController implements Initializable {

    Main ventana;
    @FXML
    private JFXTextField nombre_text;
    @FXML
    private JFXTextField apellido_text;
    @FXML
    private JFXTextField correo_text;
    @FXML
    private JFXComboBox<String> combo_sexo;
    @FXML
    private JFXTextField edad_text;
    
    BaseDatos bd = new BaseDatos();
    @FXML
    private TableView<Empleado> tablaEmpleados;
    @FXML
    private TableColumn c_nombre;
    @FXML
    private TableColumn c_apellido;
    @FXML
    private TableColumn c_correo;
    @FXML
    private TableColumn c_sexo;
    @FXML
    private TableColumn c_edad;
    
    ObservableList<Empleado> obsLista = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
       bd.conectar();
       agregarSexoComobox();
       
       tablaEmpleados.setEditable(true);
         
        llenarColomn();
        agregarEmpleados();
    }
    
    void llenarColomn()
    {
       
        c_nombre.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombre")); 
        c_apellido.setCellValueFactory(new PropertyValueFactory<Empleado, String>("apellido")); 
        c_correo.setCellValueFactory(new PropertyValueFactory<Empleado, String>("correo")); 
        c_sexo.setCellValueFactory(new PropertyValueFactory<Empleado, String>("sexo")); 
        c_edad.setCellValueFactory(new PropertyValueFactory<Empleado, Integer>("edad")); 
        
        tablaEmpleados.setItems(obsLista);
    
    
    }
    void agregarEmpleados()
    {
        List<Empleado> lista = bd.SELECT_EMPLEADO();

        if (!lista.isEmpty()) {
            
                for (int i = 0; i < lista.size(); i++) 
                {
                    obsLista.add(lista.get(i));
                }
        }


    }
             
    public void setVentanaPrincipal(Main main)
    {
        this.ventana = main;
    }
    
    void agregarSexoComobox()
    {
        combo_sexo.getItems().add("MASCULINO");
        combo_sexo.getItems().add("FEMENINO");
    }
    
    @FXML
    void registrarEmpleado()
    {
       
        String nombre = nombre_text.getText();
        String Apellido =apellido_text.getText();
        String correo = correo_text.getText();
        String sexo = combo_sexo.getValue();
        int edad =  Integer.parseInt(edad_text.getText());
      
        Empleado nuevo = new Empleado(nombre, Apellido, correo, sexo, edad);
       
        if(bd.INSERT_EMPLEADO(nuevo)) 
        {
            mensaje("EXITO", "SE REGISTRO EL EMPLEADO CON EXITO", Alert.AlertType.CONFIRMATION);
        }
        
    }

    @FXML
    void limpiarCampos()
    {
        nombre_text.setText("");
        apellido_text.setText("");
        correo_text.setText("");
        edad_text.setText("");
    }  
        
    
    void mensaje(String titulo, String info,Alert.AlertType tipo) 
    {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setTitle(titulo);
        alert.setContentText(info);
        alert.showAndWait();
    }
    
}
