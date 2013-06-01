package datos;

import conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Permiso;

/**
 *
 * @author Hugo
 */
public class PermisoBD {
    public List<Permiso> getPermisos(Connection con) throws Exception{
        List<Permiso> listaPermiso = new ArrayList<Permiso>();
        Permiso permiso = new Permiso();
        String consulta = "SELECT * FROM CPermiso";
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while(rs.next()){
            permiso.setIdPer(rs.getInt("id_per"));
            permiso.setDesPer(rs.getString("des_per"));
            listaPermiso.add(permiso);
        }
        con.commit();
        return listaPermiso;  
    }
    public Permiso getPorDescripcion(Connection con, String desPer) throws Exception{
        String consulta = "SELECT * FROM CPermiso WHERE des_per='"+desPer+"'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        Permiso permiso = null;
        while(rs.next()){
            permiso = new Permiso(
                   rs.getInt("id_per"), 
                   rs.getString("des_per")
                   ); 
        }
        return permiso;
    }
    
}
