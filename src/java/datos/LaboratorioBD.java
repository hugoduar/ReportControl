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
import modelo.Laboratorio;


/**
 *
 * @author Hugo
 */
public class LaboratorioBD {
    public List<Laboratorio> getReparaciones(Connection con) throws Exception{
        List<Laboratorio> listaLaboratorio = new ArrayList<Laboratorio>();
        String consulta = "SELECT * FROM MLaboratorio";
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while(rs.next()){
            Laboratorio laboratorio = new Laboratorio(
                    rs.getInt("id_lab"),
                    rs.getString("nom_lab")
                    );
            listaLaboratorio.add(laboratorio);
        }
        con.commit();
        return listaLaboratorio;
    }
    public Laboratorio getPorIdLab(Connection con, int idLab) throws Exception{
        String consulta = "SELECT * FROM MLaboratorio WHERE id_lab="+idLab;
        Laboratorio laboratorio = null;
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while(rs.next()){
            laboratorio = new Laboratorio(
                    rs.getInt("id_lab"),
                    rs.getString("nom_lab")
                    );
        }
        con.commit();
        return laboratorio;
    }
}
