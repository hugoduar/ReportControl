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
import modelo.EstadoReporte;



/**
 *
 * @author Hugo
 */
public class EstadoReporteBD {
    public List<EstadoReporte> getEstadosReportes(Connection con) throws Exception{
        List<EstadoReporte> listaEstadoReporte = new ArrayList<EstadoReporte>();
        String consulta = "SELECT * FROM CTipoFalla";
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while(rs.next()){
            EstadoReporte estadoReporte = new EstadoReporte(
                    rs.getInt("id_ere"),
                    rs.getString("des_ere")
                    );
            listaEstadoReporte.add(estadoReporte);
        }
        con.commit();
        return listaEstadoReporte;
    }
    public EstadoReporte getPorIdEre(Connection con, int idEre) throws Exception{
        String consulta = "SELECT * FROM CEstadoReporte WHERE id_ere="+idEre;
        EstadoReporte estadoReporte = null;
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while(rs.next()){
            estadoReporte = new EstadoReporte(
                    rs.getInt("id_ere"),
                    rs.getString("des_ere")
                    );
        }
        con.commit();
        return estadoReporte;
    }
}
