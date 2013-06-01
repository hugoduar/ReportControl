/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Hugo
 */
public class JoinBD {
    
    public static ResultSet getReportesPorFecha(Connection con, String fechaIni, String fechaFin) throws SQLException{
        con.setAutoCommit(false);
        PreparedStatement pst = con.prepareStatement("SELECT id_ere, id_alu, id_pro, id_rep, des_rep, nom_equ, nom_lab, tip_fal, des_ere, fec_rep FROM EReporte NATURAL JOIN MReporte NATURAL JOIN CEstadoReporte NATURAL JOIN CTipoFalla NATURAL JOIN MEquipo NATURAL JOIN MLaboratorio WHERE fec_rep >=? AND fec_rep <=? ORDER BY  fec_rep ASC");
        pst.setString(1, fechaIni);
        pst.setString(2, fechaFin);
        ResultSet rs = pst.executeQuery();
        con.commit();
        return rs;
    }
    
}
