
package Principal;

import Views.MenuController;
import Views.TablaController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application{

    public Stage primaryStage;
    private BorderPane rootLayout;
   
    @Override
    public void start(Stage stage) throws Exception 
    {
        primaryStage = stage;
        primaryStage.setTitle("CRUD JAVA");
        
        Menu();
    }
     
    public void Menu() {

        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Views/Menu.fxml"));
            rootLayout = (BorderPane) loader.load();

            
            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();

            
            MenuController menu = loader.getController();
            menu.setVentanaPrincipal(this);

            primaryStage.show();
        } catch (IOException ex) {
            System.out.println("--ERROR MENU-" + ex);
        }
    }
    public void tablaEmpleados() {

        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Views/Tabla.fxml"));
            rootLayout = (BorderPane) loader.load();

            
            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();

            //Creamos el controlador de la ventana
            TablaController tabla = loader.getController();
            tabla.setVentanaPrincipal(this);

            primaryStage.show();
            
        } catch (IOException ex) 
        {
            System.out.println("--ERROR TABLA-" + ex);
        }
    }
    
    public static void main(String[] args) 
    {
        launch(args);
    }

    
    
    
}
