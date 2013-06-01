/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;

/**
 *
 * @author Hugo
 */
public class UsuarioBD {
    public Usuario getPorNicUsuANDConUsu(Connection con, String nicUsu, String conUsu) throws Exception{
        Usuario usuario = new Usuario();
        con.setAutoCommit(false);
        String consulta = "SELECT * FROM MUsuario WHERE nic_usu=? AND con_usu=?";
        PreparedStatement pst = con.prepareStatement(consulta); 
        pst.setString(1, nicUsu);
        pst.setString(2, conUsu);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            usuario.setNicUsu(rs.getString("nic_usu"));
            usuario.setIdTus(rs.getInt("id_tus"));
            usuario.setIdUsu(rs.getInt("id_usu"));
        }
        return usuario;
    }
    public Usuario getPorIdUsu(Connection con, int idUsu) throws Exception{
        Usuario usuario = new Usuario();
        con.setAutoCommit(false);
        String consulta = "SELECT * FROM MUsuario WHERE id_usu=?";
        PreparedStatement pst = con.prepareStatement(consulta); 
        pst.setString(1, String.valueOf(idUsu));
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            usuario.setNicUsu(rs.getString("nic_usu"));
            usuario.setIdTus(rs.getInt("id_tus"));
            usuario.setIdUsu(rs.getInt("id_usu"));
        }
        return usuario;
    }
    public int insertarUsuario(Connection con, Usuario usuario) throws Exception{
        con.setAutoCommit(false);
        PreparedStatement pst = con.prepareStatement("INSERT INTO MUsuario (id_tus, nic_usu, con_usu) VALUES (?,?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        int idUsu = 0;
        //String query = "INSERT INTO MUsuario (id_tus, nic_usu, con_usu) VALUES ("+idTipo+",'"+nick+"', '"+password+"')";
        pst.setInt(1, usuario.getIdTus());
        pst.setString(2, usuario.getNicUsu());
        pst.setString(3, usuario.getConUsu());
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        while(rs.next()){
           idUsu = rs.getInt(1);
        }
        con.commit();
        return idUsu;
    }
    public List<Usuario> getUsuarios(Connection con) throws Exception{
        List<Usuario> listaAlumno = new ArrayList<Usuario>();
        String consulta = "SELECT * FROM MUsuario";
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while(rs.next()){
            Usuario usuario = new Usuario();
            usuario.setIdUsu(rs.getInt("id_usu"));
            usuario.setIdTus(rs.getInt("id_tus"));
            usuario.setNicUsu(rs.getString("nic_usu"));
            listaAlumno.add(usuario);
        }
        con.commit();
        return listaAlumno;
    }
}
