/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.TipoFalla;

/**
 *
 * @author Hugo
 */
public class TipoFallaBD {
    public List<TipoFalla> getTipoFallas(Connection con) throws Exception{
        List<TipoFalla> listaReporte = new ArrayList<TipoFalla>();
        String consulta = "SELECT * FROM CTipoFalla";
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while(rs.next()){
            TipoFalla tipoFalla = new TipoFalla(
                    rs.getInt("id_tfa"),
                    rs.getString("des_tfa"),
                    rs.getInt("tip_fla")
                    );
            listaReporte.add(tipoFalla);
        }
        con.commit();
        return listaReporte;
    }
    public TipoFalla getPorIdTfa(Connection con, int idTfa) throws Exception{
        String consulta = "SELECT * FROM CTipoFalla WHERE id_tfa="+idTfa;
        TipoFalla tipoFalla = null;
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while(rs.next()){
            tipoFalla = new TipoFalla(
                    rs.getInt("id_tfa"),
                    rs.getString("des_tfa"),
                    rs.getInt("tip_fla")
                    );
        }
        con.commit();
        return tipoFalla;
    }
}
