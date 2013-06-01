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
import modelo.Equipo;

/**
 *
 * @author Hugo
 */
public class EquipoBD {
    public List<Equipo> getReparaciones(Connection con) throws Exception{
        List<Equipo> listaEquipo = new ArrayList<Equipo>();
        String consulta = "SELECT * FROM MEquipo";
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while(rs.next()){
            Equipo equipo = new Equipo(
                    rs.getString("nom_equ"),
                    rs.getInt("id_lab")
                    );
            listaEquipo.add(equipo);
        }
        con.commit();
        return listaEquipo;
    }
    public Equipo getPorNomEqu(Connection con, String nomEqu) throws Exception{
        String consulta = "SELECT * FROM MEquipo WHERE id_equ='"+nomEqu+"'";
        Equipo equipo = null;
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while(rs.next()){
            equipo = new Equipo(
                    rs.getString("nom_equ"),
                    rs.getInt("id_lab")
                    );
        }
        con.commit();
        return equipo;
    }
}
