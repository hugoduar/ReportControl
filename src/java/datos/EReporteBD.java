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
import modelo.EReporte;

/**
 *
 * @author Hugo
 */
public class EReporteBD {
    public List<EReporte> getEReportes(Connection con) throws Exception{
        List<EReporte> listaEReporte = new ArrayList<EReporte>();
        String consulta = "SELECT * FROM CTipoFalla";
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while(rs.next()){
            EReporte eReporte = new EReporte(
                    rs.getInt("id_ece"),
                    rs.getInt("id_pro"),
                    rs.getInt("id_alu")
                    );
            listaEReporte.add(eReporte);
        }
        con.commit();
        return listaEReporte;
    }
    
    public EReporte getPorIdEce(Connection con, int IdEce) throws Exception{
        String consulta = "SELECT * FROM EReporte WHERE id_ece="+IdEce;
        EReporte eReporte = null;
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while(rs.next()){
            eReporte = new EReporte(
                    rs.getInt("id_ece"),
                    rs.getInt("id_pro"),
                    rs.getInt("id_alu")
                    );
        }
        con.commit();
        return eReporte;
    }
    
}
