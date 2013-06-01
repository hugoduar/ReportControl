/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Reporte;
import java.util.List;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hugo
 */
public class ReporteBD {
    public List<Reporte> getReportes(Connection con) throws Exception{
        List<Reporte> listaReporte = new ArrayList<Reporte>();
        String consulta = "SELECT * FROM MReporte";
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while(rs.next()){
            Reporte reporte = new Reporte(
                    rs.getInt("id_rep"),
                    rs.getString("des_rep"),
                    rs.getString("fec_rep"),
                    rs.getString("nom_asi"),
                    rs.getInt("id_ece"),
                    rs.getInt("id_ere"),
                    rs.getInt("id_tfa"),
                    rs.getString("nom_equ")
                    );
            listaReporte.add(reporte);
        }
        con.commit();
        return listaReporte;
    }
    public List<Reporte> getReportesPorMes(Connection con, int mes, int anio) throws ParseException, Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date fecha = new Date();
        con.setAutoCommit(false);
        List<Reporte> listaReporte = getReportes(con);
        List<Reporte> listaReportePorMes = new ArrayList<Reporte>();
        Iterator itrReporte = listaReporte.iterator();
        Calendar cal = Calendar.getInstance();
        DateFormat df =  DateFormat.getDateInstance();
        while(itrReporte.hasNext()){
            Reporte reporte = (Reporte) itrReporte.next();
            fecha = sdf.parse(reporte.getFecRep());
            cal.setTime(fecha);
            int anioObt = cal.get(Calendar.YEAR);
            int mesObt = cal.get(Calendar.MONTH);
            if(fecha.getMonth()==mes && anioObt==anio){
                listaReportePorMes.add(reporte);
            } 
        }
        con.commit();
        return listaReportePorMes;
    }
//    public List<Reporte> getPorMasNumeroDeReportes(Connection con) throws Exception{
//        List<Reporte> listaReporte = new ArrayList<Reporte>();
//        String consulta = "SELECT *,count(nom_equ) as num FROM MReporte GROUP BY nom_equ ORDER BY num DESC;";
//        con.setAutoCommit(false);
//        Statement st = con.createStatement();
//        ResultSet rs = st.executeQuery(consulta);
//        while(rs.next()){
//            Reporte reporte = new Reporte(
//                    rs.getInt("id_rep"),
//                    rs.getString("des_rep"),
//                    rs.getString("fec_rep"),
//                    rs.getString("nom_asi"),
//                    rs.getInt("id_ece"),
//                    rs.getInt("id_ere"),
//                    rs.getInt("id_tfa"),
//                    rs.getString("nom_equ")
//                    );
//            listaReporte.add(reporte);
//        }
//        con.commit();
//        return listaReporte;
//    }
    public static ResultSet getPorMasNumeroDeReportes(Connection con) throws Exception{
        String consulta = "SELECT nom_equ,count(nom_equ) as num FROM MReporte GROUP BY nom_equ ORDER BY num DESC";
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        con.commit();
        return rs;
    }
    
    public static String getNombreCompletoAlumnoPorIdReporte(int idRep){
        String nom = "";
        try {
            Connection con = Conexion.getConexion();
            
            con.setAutoCommit(false);
            PreparedStatement pst = con.prepareStatement("SELECT apa_alu, ama_alu, nom_alu,id_rep FROM MAlumno NATURAL JOIN EReporte NATURAL JOIN MReporte WHERE id_rep=?", PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setInt(1, idRep);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                nom += " "+rs.getString("apa_alu")+" ";
                nom += " "+rs.getString("ama_alu")+" ";
                nom += " "+rs.getString("nom_alu")+" ";
            }
            con.commit();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(ReporteBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nom;
    }
    public static String getNombreTecnicoPorIdReporte(int idRep){
        String nom = "";
        try {
            Connection con = Conexion.getConexion();
            
            con.setAutoCommit(false);
            PreparedStatement pst = con.prepareStatement("SELECT apa_tec, ama_tec, nom_tec FROM MTecnico NATURAL JOIN MReparacion NATURAL JOIN MReporte WHERE id_rep=?", PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setInt(1, idRep);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                nom += " "+rs.getString("apa_tec")+" ";
                nom += " "+rs.getString("ama_tec")+" ";
                nom += " "+rs.getString("nom_tec")+" ";
            }
            con.commit();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(ReporteBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nom;
    }
    public static String getNombreCompletoProfesorPorIdReporte(int idRep){
        String nom = "";
        try {
            Connection con = Conexion.getConexion();
            
            con.setAutoCommit(false);
            PreparedStatement pst = con.prepareStatement("SELECT apa_pro, ama_pro, nom_pro,id_rep FROM MProfesor NATURAL JOIN EReporte NATURAL JOIN MReporte WHERE id_rep=?", PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setInt(1, idRep);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                nom += " "+rs.getString("apa_pro")+" ";
                nom += " "+rs.getString("ama_pro")+" ";
                nom += " "+rs.getString("nom_pro")+" ";
            }
            con.commit();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(ReporteBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nom;
    }
    public static String getEstadoPorIdReporte(int idRep){
        String nom = "";
        try {
            Connection con = Conexion.getConexion();
            
            con.setAutoCommit(false);
            PreparedStatement pst = con.prepareStatement("SELECT des_ere FROM CEstadoReporte NATURAL JOIN MReporte WHERE id_rep=?", PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setInt(1, idRep);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                nom += " "+rs.getString("des_ere")+" ";
                
            }
            con.commit();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(ReporteBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nom;
    }
    public static String getDesReqPorIdReporte(int idRep){
        String nom = "";
        try {
            Connection con = Conexion.getConexion();
            
            con.setAutoCommit(false);
            PreparedStatement pst = con.prepareStatement("SELECT des_req FROM MReparacion NATURAL JOIN MReporte WHERE id_rep=?", PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setInt(1, idRep);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                nom += " "+rs.getString("des_req")+" ";
                
            }
            con.commit();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(ReporteBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nom;
    }
   public static String getFecReqPorIdReporte(int idRep){
        String nom = "";
        try {
            Connection con = Conexion.getConexion();
            
            con.setAutoCommit(false);
            PreparedStatement pst = con.prepareStatement("SELECT fec_req FROM MReparacion NATURAL JOIN MReporte WHERE id_rep=?", PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setInt(1, idRep);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                nom += " "+rs.getString("fec_req")+" ";
                
            }
            con.commit();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(ReporteBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nom;
    }
   
   
   
   public static ResultSet getReportesPorIdLab(Connection con, int idLab){
        ResultSet rs = null;
        try {
            con.setAutoCommit(false);
            PreparedStatement pst = con.prepareStatement("SELECT id_rep, des_rep, nom_equ, fec_rep, des_ere FROM MLaboratorio NATURAL JOIN MEquipo NATURAL JOIN MReporte NATURAL JOIN CEstadoReporte WHERE id_lab=?", PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setInt(1, idLab);
            rs = pst.executeQuery();
            con.commit();
            
        } catch (Exception ex) {
            Logger.getLogger(ReporteBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
