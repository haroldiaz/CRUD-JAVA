
package Views;

import BaseDatos.BaseDatos;
import Principal.Empleado;
import Principal.Main;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;


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
       obsLista.clear();
       bd.conectar();
       tablaEmpleados.setEditable(true);
       
       agregarSexoComobox();
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
        obsLista.add(nuevo);
        
        
        if(!bd.INSERT_EMPLEADO(nuevo)) 
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
        
    
    @FXML
    void verDatosEmpleado()
    {
        if (!tablaEmpleados.getSelectionModel().isEmpty())
        {
            String nombre = tablaEmpleados.getSelectionModel().getSelectedItems().get(0).getNombre();
            nombre_text.setText(nombre);
           
            
            String apellido = tablaEmpleados.getSelectionModel().getSelectedItems().get(0).getApellido();
            apellido_text.setText(apellido);
            
            String correo =  tablaEmpleados.getSelectionModel().getSelectedItems().get(0).getCorreo();
            correo_text.setText(correo);
            
            String sexo = tablaEmpleados.getSelectionModel().getSelectedItems().get(0).getSexo();
            combo_sexo.setValue(sexo);
            
            String edad = tablaEmpleados.getSelectionModel().getSelectedItems().get(0).getEdad() + "";
            edad_text.setText(edad);
        }
                
    }
    
    @FXML
    public void DELETE()
    {
       
        if (!tablaEmpleados.getSelectionModel().isEmpty()) 
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("ELIMINAR EMPLEADO");

            alert.setTitle("Eliminar Empleado");
            alert.setContentText("¿Deseas realmente Eliminar el Empleado?");
            
            Optional<ButtonType> action = alert.showAndWait();
        
            if (action.get() == ButtonType.OK) 
            {  
            
            
            int codigo =(int) tablaEmpleados.getSelectionModel().getSelectedItem().getId_empleado();
            System.out.println(codigo);
                if(bd.DELETE_EMPLEADO(codigo))
                {
                    obsLista.remove(tablaEmpleados.getSelectionModel().getSelectedItem());
                    mensaje("EXITO", "SE ELIMINO EL EMPLEADO",Alert.AlertType.CONFIRMATION);
                }
            
            
            }
        }
        else
        {
            mensaje("ERROR", "NO SELECCIONO NINGUNA NOTA",Alert.AlertType.CONFIRMATION);
        }
        
    
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
