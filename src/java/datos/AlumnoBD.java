/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Alumno;
import modelo.Permiso;

/**
 *
 * @author Hugo
 */
public class AlumnoBD {
    
    
    
    public List<Alumno> getAlumnos(Connection con) throws Exception{
        List<Alumno> listaAlumno = new ArrayList<Alumno>();
        String consulta = "SELECT * FROM MAlumno";
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while(rs.next()){
            Alumno alumno = new Alumno();
            alumno.setIdAlu(rs.getInt("id_alu"));
            alumno.setIdUsu(rs.getInt("id_usu"));
            alumno.setBolAlu(rs.getInt("bol_alu"));
            alumno.setAmaAlu(rs.getString("ama_alu"));
            alumno.setApaAlu(rs.getString("apa_alu"));
            alumno.setNomAlu(rs.getString("nom_alu"));
            alumno.setCorAlu(rs.getString("cor_usu"));
            alumno.setGpoAlu(rs.getString("gpo_alu"));
            listaAlumno.add(alumno);
        }
        con.commit();
        return listaAlumno;
    }
    public int insertarAlumno(Connection con, Alumno alumno) throws Exception{
        con.setAutoCommit(false);
        PreparedStatement pst = con.prepareStatement("INSERT INTO MAlumno (id_usu, apa_alu, ama_alu, nom_alu, bol_alu, gpo_alu, cor_usu) VALUES (?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        int idAlu = 0;
        //String query = "INSERT INTO MAlumno (id_usu, apa_alu, ama_alu, nom_alu, bol_alu, gpo_alu, cor_usu) VALUES ("+alumno.getIdUsu()+", '"+alumno.getApaAlu()+"', '"+alumno.getAmaAlu()+"', '"+alumno.getNomAlu()+"', "+alumno.getBolAlu()+", '"+alumno.getBolAlu()+"', '"+alumno.getCorAlu()+"')";
        pst.setInt(1, alumno.getIdUsu());
        pst.setString(2, alumno.getApaAlu());
        pst.setString(3, alumno.getAmaAlu());
        pst.setString(4, alumno.getNomAlu());
        pst.setInt(5, alumno.getBolAlu());
        pst.setString(6, alumno.getGpoAlu());
        pst.setString(7, alumno.getGpoAlu());
        pst.setString(7, alumno.getCorAlu());
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        while(rs.next()){
           idAlu = rs.getInt(1);
        }
        con.commit();
        return idAlu;
    
    }
    
}
