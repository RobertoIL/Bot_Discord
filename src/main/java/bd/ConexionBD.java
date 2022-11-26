package bd;

import okhttp3.Connection;

import java.sql.DriverManager;

public class ConexionBD {
    public static final String URL = "";
    public static final String USERNAME = "";
    public static final String PASSWORD = "";

    public static Connection getConnection(){
        Connection conexion = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch(Exception e){
            System.out.println(e);
        }
        return conexion;
    }
}
