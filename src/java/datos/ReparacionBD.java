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
import modelo.Reparacion;

/**
 *
 * @author Hugo
 */
public class ReparacionBD {
    public List<Reparacion> getReparaciones(Connection con) throws Exception{
        List<Reparacion> listaReparacion = new ArrayList<Reparacion>();
        String consulta = "SELECT * FROM CTipoFalla";
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while(rs.next()){
            Reparacion reparacion = new Reparacion(
                    rs.getInt("id_rci"),
                    rs.getString("des_req"),
                    rs.getString("fec_req"),
                    rs.getInt("id_rep"),
                    rs.getString("nom_equ"),
                    rs.getInt("id_tec")
                    );
            listaReparacion.add(reparacion);
        }
        con.commit();
        return listaReparacion;
    }
    
    public Reparacion getPorIdRep(Connection con, int idRep) throws Exception{
        String consulta = "SELECT * FROM MReparacion WHERE id_req="+idRep;
        Reparacion reparacion = null;
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while(rs.next()){
            reparacion = new Reparacion(
                    rs.getInt("id_rci"),
                    rs.getString("des_req"),
                    rs.getString("fec_req"),
                    rs.getInt("id_rep"),
                    rs.getString("nom_equ"),
                    rs.getInt("id_tec")
                    );
        }
        con.commit();
        return reparacion;
    }
    
    public ResultSet getReparacionesPorIdTecnico(){
        return null;
    }
}
