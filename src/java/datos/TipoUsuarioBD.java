/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.TipoUsuario;

/**
 *
 * @author Hugo
 */
public class TipoUsuarioBD {
    public TipoUsuario getPorIdTus(Connection con, int idTus) throws Exception{
        TipoUsuario tipoUsuario = new TipoUsuario();
        con.setAutoCommit(false);
        String consulta = "SELECT * FROM MTipoUsuario WHERE id_tus='"+idTus+"'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while(rs.next()){
            tipoUsuario.setDesTus(rs.getString("des_tus"));
            tipoUsuario.setIdTus(rs.getInt("id_tus"));
            tipoUsuario.setTiemSem(rs.getInt("tie_sem"));
        }
        con.commit();
        return tipoUsuario;
    }
    
}
