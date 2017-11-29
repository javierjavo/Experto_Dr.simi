package DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
/**
 *
 * @author javie
 */
public class Conectar {
    private static Connection conn;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String pass = "";
    //nombre de la db
    private static final String url = "jdbc:mysql://localhost/experto";
    
    public Conectar() throws SQLException {
        conn = null;
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,pass);
            if(conn != null){
                System.out.println("conectado");
            }
        }catch(ClassNotFoundException e){}
    }
    
    public Connection GetConnection(){
        return conn;
    }
    
    public void desconectar(){
        conn = null;
    }
    
    public List<String> buscar(String busqueda){
        return new ArrayList<>();
    }
    
}
