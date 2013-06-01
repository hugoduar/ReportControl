/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author Hugo
 */
public class Conexion {
    public static Connection getConexion() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ReCoBD", "root", "n0m3l0");
        return con;
    }
    public void cerrarConexion() throws Exception{
        getConexion().close();
    }
    public ResultSet ejecutarConsulta(String sql) throws Exception{
        return getConexion().createStatement().executeQuery(sql);
    }
}
