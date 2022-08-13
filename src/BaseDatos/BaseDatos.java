package BaseDatos;



import Principal.Empleado;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BaseDatos {

    String BD = "jdbc:postgresql://localhost:5432/Empleados";
    String usuario = "postgres";
    String pass = "12345";

    Connection conectar = null;

    Statement instruccion = null;
    ResultSet resultados = null;

    public BaseDatos()
    {
       // String usuario = "postgres";
       // String pass = "12345";
    }
    public BaseDatos(String usuario,String pass)
    {
        this.usuario = usuario;
        this.pass = pass;
    }

    public void conectar() {
        try {
            conectar = DriverManager.getConnection(BD, usuario, pass);

            System.out.println(" base de datos conectada ");

        } catch (Exception e) {
            System.out.println(" error al conceta " + e);

        }

    }

    
    public List<Empleado> SELECT_EMPLEADO()
    {
        List<Empleado> lista = new ArrayList<>();
        try {
            instruccion = conectar.createStatement();

            String query = "SELECT * FROM  EMPLEADO";
            resultados = instruccion.executeQuery(query);

           
            while (resultados.next()) {

           
                Empleado n = new Empleado(
                        resultados.getInt("id_empleado"),
                        resultados.getString("nombre"),
                        resultados.getString("apellido"),
                        resultados.getString("correo"),
                        resultados.getString("sexo"),
                        resultados.getInt("edad")
                );

                lista.add(n);
            }
            
            instruccion.close();
            return lista;

        } catch (SQLException e) {
            System.out.println("ERROR SELECT " + e);
        }

        return lista;
    }
    
    public boolean INSERT_EMPLEADO(Empleado nuevo)
    {
        
        String sql = "INSERT INTO EMPLEADO(nombre,apellido,correo,sexo,edad)"
                    + "\n" 
                    + "VALUES ( "+ "'"+ nuevo.getNombre() +"','"+  
                                        nuevo.getApellido() +"','"+
                                        nuevo.getCorreo() +"','"+
                                        nuevo.getSexo()+"','"+
                                        nuevo.getEdad()
                                        +"')";
        return EJECUTE_QUERY(sql);
        
    }
   
    boolean EJECUTE_QUERY(String query) 
    {
        try 
        {
            instruccion = conectar.createStatement();
            resultados = instruccion.executeQuery(query);
         
           
            System.out.println("--SE EJECUTO- CORRECTAMENTE-");
            return  true;
            

        } catch (SQLException e)
        {
            System.out.println("ERROR EJECUTE_QUERY -> " + e);
            return  false;
            
        }
        
        
        
    }

}
