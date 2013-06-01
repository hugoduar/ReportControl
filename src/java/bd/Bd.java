package bd;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hugo
 */
public class Bd {
    //public  Connection conGlobal = Conexion.getConexion();
    public static Connection getConexion() throws ClassNotFoundException, SQLException{
        //Class.forName("com.mysql.jdbc.Driver");
        //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ReCoBD", "root", "n0m3l0");
        Connection con = null;
        try {
            con = Conexion.getConexion();
        } catch (Exception ex) {
            Logger.getLogger(Bd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    public static void closeConexion() throws SQLException, ClassNotFoundException{
        getConexion().close();
    }
    public static String UpdateColumString(String table, String campo, String cambio,  String pkeycampo, String pk) throws SQLException, ClassNotFoundException{
        Connection con = getConexion();
        Statement st = con.createStatement();
        String query = "UPDATE "+table+" SET "+campo+"='"+cambio+"' WHERE "+pkeycampo+"="+pk;
        st.execute("UPDATE "+table+" SET "+campo+"='"+cambio+"' WHERE "+pkeycampo+"="+pk);
        return query;
    }
    public static String addTipoUsuario(String desUsuario, int tiempoSesion) throws ClassNotFoundException, SQLException{
        Connection con = getConexion();
        Statement st = con.createStatement();
        String query = "INSERT INTO MTipoUsuario (des_tus, tie_sem) VALUES ('"+desUsuario+"',"+tiempoSesion+")";
        st.execute(query);
        return query;
    }
    
    public static String addPermiso(String permiso) throws ClassNotFoundException, SQLException{
        Connection con = getConexion();
        Statement st = con.createStatement();
        String query = "INSERT INTO CPermiso (des_per) VALUES ('"+permiso+"')";
        st.execute(query);
        return query;
    }
    public static String addPermisoTipoUsuario(String permiso, String tipoUsuario) throws ClassNotFoundException, SQLException{
        Connection con = getConexion();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT id_tus FROM MTipoUsuario WHERE des_tus='"+tipoUsuario+"'");
        int idTipo = 0;
        while(rs.next()){
            idTipo = rs.getInt("id_tus");
        }
        st.executeQuery("SELECT id_per FROM MTipoUsuario WHERE des_per='"+permiso+"'");
        int idPermiso = 0;
        while(rs.next()){
            idPermiso = rs.getInt("id_per");
        }
        String query = "INSERT INTO EPermisoUsuario (id_tus, id_per) VALUES ("+idTipo+","+idPermiso+")";
        st.execute(query);
        return query;
    } 
    public static String addUsuario(String nick, String password, int idTipo) throws ClassNotFoundException, SQLException{
        Connection con = getConexion();
        Statement st = con.createStatement();
        String query = "INSERT INTO MUsuario (id_tus, nic_usu, con_usu) VALUES ("+idTipo+",'"+nick+"', '"+password+"')";
        st.execute(query);
        System.out.println(query);
        return query;
    }
    public static String addAlumno(int idUsu, String apPat, String apMat, String nombre, String grupo, int boleta, String correo) throws ClassNotFoundException, SQLException{
        Connection con = getConexion();
        Statement st = con.createStatement();
        String query = "INSERT INTO MAlumno (id_usu, apa_alu, ama_alu, nom_alu, bol_alu, gpo_alu, cor_usu) VALUES ("+idUsu+", '"+apPat+"', '"+apMat+"', '"+nombre+"', "+boleta+", '"+grupo+"', '"+correo+"')";
        st.execute(query);
        return query;
    }
    public static String addProfesor(Connection con, int idUsu, String apPat, String apMat, String nombre, String correo) throws ClassNotFoundException, SQLException{
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        String query = "INSERT INTO MProfesor (id_usu, apa_pro, ama_pro, nom_pro, cor_pro) VALUES ("+idUsu+", '"+apPat+"', '"+apMat+"', '"+nombre+"', '"+correo+"')";
        st.execute(query);
        con.commit();
        return query;
    }
    public static String addTecnico(int idUsu, String apPat, String apMat, String nombre, String correo) throws ClassNotFoundException, SQLException{
        Connection con = getConexion();
        Statement st = con.createStatement();
        String query = "INSERT INTO MTecnico (id_usu, apa_tec, ama_tec, nom_tec, cor_tec) VALUES ("+idUsu+", '"+apPat+"', '"+apMat+"', '"+nombre+"', '"+correo+"')";
        st.execute(query);
        return query;
    }
    public static ResultSet getUsuarios(String nusuario, String cusuario) throws SQLException{
        ResultSet rs=null;
        Statement st;
        try {
            String nic = null;
            String pass;
            st = getConexion().createStatement();
            rs = st.executeQuery("SELECT nic_usu, AES_DECRYPT(con_usu, 's3cr3t'), id_usu FROM MUsuario");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public static ResultSet getTiposUsuarios() throws SQLException{
        ResultSet rs=null;
        Statement st;
        try {
            String nic = null;
            String pass;
            st = getConexion().createStatement();
            rs = st.executeQuery("SELECT id_tus, des_tus, tie_sem FROM MTipoUsuario");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public static ResultSet getDatosAlumnos() throws SQLException{
        ResultSet rs=null;
        Statement st;
        try {
            String nic = null;
            String pass;
            st = getConexion().createStatement();
            rs = st.executeQuery("SELECT nic_usu, id_usu, id_alu, apa_alu, ama_alu, nom_alu, bol_alu, gpo_alu, cor_usu FROM MUsuario NATURAL JOIN MAlumno");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public static ResultSet getDatosProfesores(Connection con) throws SQLException{
        ResultSet rs=null;
        Statement st;
        String nic = null;
        String pass;
        st = con.createStatement();
        rs = st.executeQuery("SELECT nic_usu, id_usu, id_pro, apa_pro, ama_pro, nom_pro, cor_pro FROM MUsuario NATURAL JOIN MProfesor");
        return rs;
    }
    public static ResultSet getDatosTecnicos(Connection con) throws SQLException{
        ResultSet rs=null;
        Statement st;

            String nic = null;
            String pass;
            st = con.createStatement();
            rs = st.executeQuery("SELECT nic_usu, id_usu, id_tec, apa_tec, ama_tec, nom_tec, cor_tec FROM MUsuario NATURAL JOIN MTecnico");

        return rs;
    }
    public static ResultSet getUsuarios() throws SQLException, ClassNotFoundException{
        Statement st = getConexion().createStatement();
        ResultSet rs = st.executeQuery("SELECT des_tus, id_tus, nic_usu, con_usu, id_usu FROM MUsuario NATURAL JOIN MTipoUsuario");
        return rs;
    }
    public static ResultSet getTiposUsuario() throws SQLException{
        ResultSet rs=null;
        Statement st;
        try {
            String nic = null;
            String pass;
            st = getConexion().createStatement();
            rs = st.executeQuery("SELECT id_tus, des_tus, tie_sem FROM MTipoUsuario");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public static boolean getEntrada(String nusuario, String npassword) throws SQLException, ClassNotFoundException{
        ResultSet usuarios = getUsuarios();
        boolean res = false;
        String nic;
        String pass;
        while(usuarios.next()){
            nic = usuarios.getString("nic_usu");
            pass = usuarios.getString("con_usu");
                if(nic.equals(nusuario)){
                    if(npassword.equals(pass)){
                        res = true;
                        break;
                    }else{
                      res=false;
                    }
                }else{
                    res=false;
                }
        }
        return res;
    }
    public static void setPassword(String nusuario, String newPass) throws SQLException, ClassNotFoundException{
        Statement st = getConexion().createStatement();
        st.executeUpdate("UPDATE MUsuario SET con_usu='"+newPass+"' WHERE nic_usu='"+nusuario+"'");
    }
    public static int getIdUsuario(String nusuario, String pass) throws SQLException, ClassNotFoundException{
        int id = 0;
        ResultSet usuario = getUsuarios(nusuario, pass);
        String nic_usu;
        int id_usu;
        if(getEntrada(nusuario, pass)){
            while(usuario.next()){
                nic_usu = usuario.getString("nic_usu");
                id_usu =usuario.getInt("id_usu");
                if(nusuario.equals(usuario.getString("nic_usu"))){
                    id = id_usu;
                }
            }
        }
        return id; 
    }
    public static int getIdUsuario(String nusuario) throws SQLException, ClassNotFoundException{
        int id = 0;
        ResultSet usuario = getUsuarios();
        String nic_usu;
        int id_usu;
        while(usuario.next()){
            nic_usu = usuario.getString("nic_usu");
            id_usu =usuario.getInt("id_usu");
            if(nusuario.equals(usuario.getString("nic_usu"))){
                id = id_usu;
            }
        }
        return id; 
    }
    public static int getIdAlumno(String nusuario) throws SQLException, ClassNotFoundException{
        int id = 0;
        Statement st = getConexion().createStatement();
        int idUsu = getIdUsuario(nusuario);
        ResultSet usuario = st.executeQuery("SELECT id_alu, id_usu FROM MUsuario NATURAL JOIN MAlumno");
        int id_alu;
        int id_usu;
        while(usuario.next()){
            id_usu = usuario.getInt("id_usu");
            id_alu =usuario.getInt("id_alu");
            if(idUsu==id_usu){
                id = id_alu;
            }
        }
        return id; 
    }
    public static int getIdProfesor(String nusuario) throws SQLException, ClassNotFoundException{
        int id = 0;
        Connection con = getConexion();
        Statement st = con.createStatement();
        int idUsu = getIdUsuario(nusuario);
        ResultSet usuario = st.executeQuery("SELECT id_pro, id_usu FROM MUsuario NATURAL JOIN MProfesor WHERE nic_usu='"+nusuario+"'");
        int id_pro = 0;
        int id_usu;
        while(usuario.next()){
            id_pro = usuario.getInt("id_pro");
        }
        con.close();
        return id_pro; 
    }
    public static int getIdTecnico(String nusuario) throws SQLException, ClassNotFoundException{
        int id = 0;
        Statement st = getConexion().createStatement();
        int idUsu = getIdUsuario(nusuario);
        ResultSet usuario = st.executeQuery("SELECT id_tec, id_usu FROM MUsuario NATURAL JOIN MTecnico");
        int id_pro;
        int id_usu;
        while(usuario.next()){
            id_usu = usuario.getInt("id_usu");
            id_pro =usuario.getInt("id_tec");
            if(idUsu==id_usu){
                id = id_pro;
            }
        }
        return id; 
    }
    public static String getNickUsuario(int idUsuario) throws SQLException, ClassNotFoundException{
        String nick = null;
        ResultSet usuario = getUsuarios();
        String nic_usu;
        int id_usu;
        while(usuario.next()){
            nic_usu = usuario.getString("nic_usu");
            id_usu =usuario.getInt("id_usu");
            if(idUsuario == id_usu){
                nick = nic_usu;
            }
        }
        return nick; 
    }
    public static int getIdTipoUsuario(int idUsuario) throws SQLException, ClassNotFoundException{
        int idtipoUsuario = 0;
        ResultSet usuario = getUsuarios();
        int tip_usu;
        int id_usu;
        while(usuario.next()){
            tip_usu = usuario.getInt("id_tus");
            id_usu =usuario.getInt("id_usu");
            if(idUsuario == id_usu){
                idtipoUsuario = tip_usu;
            }
        }
        return idtipoUsuario; 
    }
    public static String getDescTipoUsuario(int idTipoUsuario) throws SQLException{
        String tipoUsuario = null;
        ResultSet tipos = getTiposUsuario();
        int id_tus;
        String des_tus;
        while(tipos.next()){
            id_tus = tipos.getInt("id_tus");
            des_tus =tipos.getString("des_tus");
            if(idTipoUsuario == id_tus){
                tipoUsuario = des_tus;
            }
        }
        return tipoUsuario;  
    }
    public static ResultSet getDatosAlumno(String nusuario) throws SQLException{
        ResultSet rs=null;
        Statement st;
        try {
            String nic = null;
            String pass;
            st = getConexion().createStatement();
            rs = st.executeQuery("SELECT id_alu, apa_alu, ama_alu, nom_alu, bol_alu, gpo_alu, cor_usu FROM MUsuario NATURAL JOIN MAlumno WHERE nic_usu ='"+nusuario+"'");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public static ResultSet getDatosAlumno(int boletaAlumno) throws SQLException{
        ResultSet rs=null;
        Statement st;
        try {
            String nic = null;
            String pass;
            st = getConexion().createStatement();
            rs = st.executeQuery("SELECT id_usu, id_alu, apa_alu, ama_alu, nom_alu, bol_alu, gpo_alu, cor_usu FROM MAlumno WHERE bol_alu ="+boletaAlumno);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public static String getNickAlumno(int idAlu) throws ClassNotFoundException, SQLException{
        ResultSet rs = getConexion().createStatement().executeQuery("SELECT nic_usu FROM MAlumno NATURAL JOIN MUsuario WHERE id_alu="+idAlu);
        String nicAlu = null;
        while(rs.next()){
         nicAlu = rs.getString("nic_usu");
        }
        getConexion().close();
        return nicAlu;
    }
    public static String getNickProfesor(int idPro) throws ClassNotFoundException, SQLException{
        ResultSet rs = getConexion().createStatement().executeQuery("SELECT nic_usu FROM MProfesor NATURAL JOIN MUsuario WHERE id_pro="+idPro);
        String nicPro = null;
        while(rs.next()){
         nicPro = rs.getString("nic_usu");
        }
        return nicPro;
    }
    public static ResultSet getDatosProfesor(String nusuario) throws SQLException{
        ResultSet rs=null;
        Statement st;
        try {
            String nic = null;
            String pass;
            st = getConexion().createStatement();
            rs = st.executeQuery("SELECT id_usu, id_pro, apa_pro, ama_pro, nom_pro, cor_pro FROM MUsuario NATURAL JOIN MProfesor WHERE nic_usu ='"+nusuario+"'");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public static ResultSet getDatosTecnico(String nusuario) throws SQLException{
        ResultSet rs=null;
        Statement st;
        try {
            String nic = null;
            String pass;
            st = getConexion().createStatement();
            rs = st.executeQuery("SELECT id_tec, id_usu, apa_tec, ama_tec, nom_tec, cor_tec FROM MUsuario NATURAL JOIN MTecnico WHERE nic_usu ='"+nusuario+"'");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public static String getNombreAlumno(String nusuario) throws SQLException{
        ResultSet usuario = getDatosAlumno(nusuario);
        String nombre = null;
        while (usuario.next()){
            nombre = usuario.getString("nom_alu");
        }
        return nombre;
    }
    public static String getApellidoPaternoAlumno(String nusuario) throws SQLException{
        ResultSet usuario = getDatosAlumno(nusuario);
        String nombre = null;
        while (usuario.next()){
            nombre = usuario.getString("apa_alu");
        }
        return nombre;
    }
    public static String getApellidoMaternoAlumno(String nusuario) throws SQLException{
        ResultSet usuario = getDatosAlumno(nusuario);
        String nombre = null;
        while (usuario.next()){
            nombre = usuario.getString("ama_alu");
        }
        return nombre;
    }
    public static String getNombreProfesor(String nusuario) throws SQLException{
        ResultSet usuario = getDatosProfesor(nusuario);
        String nombre = null;
        while (usuario.next()){
            nombre = usuario.getString("nom_pro");
        }
        return nombre;
    }
    public static String getApellidoPaternoProfesor(String nusuario) throws SQLException{
        ResultSet usuario = getDatosProfesor(nusuario);
        String nombre = null;
        while (usuario.next()){
            nombre = usuario.getString("apa_pro");
        }
        return nombre;
    }
    public static String getApellidoMaternoProfesor(String nusuario) throws SQLException{
        ResultSet usuario = getDatosProfesor(nusuario);
        String nombre = null;
        while (usuario.next()){
            nombre = usuario.getString("ama_pro");
        }
        return nombre;
    }
    public static String getNombreTecnico(String nusuario) throws SQLException{
        ResultSet usuario = getDatosTecnico(nusuario);
        String nombre = null;
        while (usuario.next()){
            nombre = usuario.getString("nom_tec");
        }
        return nombre;
    }
    public static String getApellidoPaternoTecnico(String nusuario) throws SQLException{
        ResultSet usuario = getDatosTecnico(nusuario);
        String nombre = null;
        while (usuario.next()){
            nombre = usuario.getString("apa_tec");
        }
        return nombre;
    }
    public static String getApellidoMaternoTecnico(String nusuario) throws SQLException{
        ResultSet usuario = getDatosTecnico(nusuario);
        String nombre = null;
        while (usuario.next()){
            nombre = usuario.getString("ama_tec");
        }
        return nombre;
    }
    public static String getNombreCompletoProfesor(int idPro) throws SQLException, ClassNotFoundException{
       Statement st = getConexion().createStatement();
       ResultSet rs = st.executeQuery("SELECT apa_pro, ama_pro, nom_pro FROM MProfesor WHERE id_pro="+idPro);
       String res = "";
       while(rs.next()){
           res += rs.getString("apa_pro")+" ";
           res += rs.getString("ama_pro")+" ";
           res += rs.getString("nom_pro");
           
       }
        return res;
    }
    public static String getNombreCompletoAlumno(int idAlu) throws SQLException, ClassNotFoundException{
       Statement st = getConexion().createStatement();
       ResultSet rs = st.executeQuery("SELECT apa_alu, ama_alu, nom_alu FROM MAlumno WHERE id_alu="+idAlu);
       String res = "";
       while(rs.next()){
           res += rs.getString("apa_alu")+" ";
           res += rs.getString("ama_alu")+" ";
           res += rs.getString("nom_alu");
           
       }
        return res;
    }
    public static boolean usuarioNuevo(String usuario) throws SQLException, ClassNotFoundException{
        ResultSet usuarios = getUsuarios();
        boolean res = true;
        String nicbd;
        while(usuarios.next()){
            nicbd = usuarios.getString("nic_usu");
                if(nicbd.equals(usuario)){
                    res = false;
                    break;
                }else{
                    res=true;
                }
        }
        return res;
    }
    public static int addEReporte(String nicAlu, String nicPro) throws ClassNotFoundException, SQLException{
        Connection con = getConexion();
        Statement st = con.createStatement();
        int idProf = getIdProfesor(nicPro);
        int idAlu = getIdAlumno(nicAlu);
        st.execute("INSERT INTO EReporte (id_alu, id_pro) VALUES ("+idAlu+", "+idProf+")");
        ResultSet rs = st.executeQuery("SELECT id_ece FROM EReporte WHERE id_alu="+idAlu+" AND id_pro="+idProf);
        int idERep = 0;
        while(rs.next()){
            idERep = rs.getInt("id_ece");
        }
        return idERep;
    }
    public static int addEReporteProfesor(String nicPro) throws Exception{
        Connection con = Conexion.getConexion();
        int idPro = getIdProfesor(nicPro);
        con.setAutoCommit(false);
        PreparedStatement pst = con.prepareStatement("INSERT INTO EReporte (id_pro) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setInt(1, idPro);
        pst.execute();
        ResultSet rs = pst.getGeneratedKeys();
        int idERep = 0 ;
        while(rs.next()){
            idERep = rs.getInt(1);
        }
        con.commit();
        con.close();
        return idERep;
    }
    public static int addCEstadoReporte(String estRep) throws ClassNotFoundException, SQLException{
        Connection con = getConexion();
        Statement st = con.createStatement();
        int idCEstadoRep = 0;
        st.execute("INSERT INTO CEstadoReporte (des_ere) VALUES('"+estRep+"')");
        ResultSet rs = st.executeQuery("SELECT id_ere FROM CEstadoReporte WHERE des_ere="+estRep);
        while(rs.next()){
            idCEstadoRep = rs.getInt("id_ere");
        }
        return idCEstadoRep;
    }
    public static int addCTipoFalla(String desTipFalla, int tipFalla) throws ClassNotFoundException, SQLException{
        Connection con = getConexion();
        Statement st = con.createStatement();
        int idTipoFalla = 0;
        st.execute("INSERT INTO CTipoFalla (des_tfa, tip_fal) VALUES('"+desTipFalla+"', "+tipFalla+")");
        ResultSet rs = st.executeQuery("SELECT id_tfa FROM CTipoFalla WHERE des_tfa='"+desTipFalla+"'");
        while(rs.next()){
            idTipoFalla = rs.getInt("id_tfa");
        }
        return idTipoFalla;
    }
    public static int addMLaboratorio(String nomLab) throws ClassNotFoundException, SQLException{
        Connection con = getConexion();
        Statement st = con.createStatement();
        int idLab = 0;
        st.execute("INSERT INTO MLaboratorio (nom_lab) VALUES('"+nomLab+"')");
        ResultSet rs = st.executeQuery("SELECT id_lab FROM MLaboratorio WHERE nom_lab='"+nomLab+"'");
        while(rs.next()){
            idLab = rs.getInt("id_lab");
        }
        return idLab;
    }
    public static String addMEquipo(String nomEqui, int idLab) throws ClassNotFoundException, SQLException{
        Connection con = getConexion();
        Statement st = con.createStatement();
        String nomEq = null;
        st.execute("INSERT INTO MEquipo (id_lab, nom_equ) VALUES("+idLab+", '"+nomEqui+"')");
        ResultSet rs= st.executeQuery("SELECT nom_equ FROM MEquipo WHERE nom_equ='"+nomEqui+"'");
        while(rs.next()){
            nomEq = rs.getString("nom_equ");
        }
        return nomEq;
    }
    public static ResultSet getLaboratorios(Connection con) throws Exception{
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT id_lab,nom_lab FROM MLaboratorio");
        con.commit();
        return rs;
    }
    public static String getNombreLaboratorio(int idLab) throws Exception{
        Connection  con  = Conexion.getConexion();
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        String nombre = null;
        ResultSet rs = st.executeQuery("SELECT nom_lab FROM MLaboratorio WHERE id_lab="+idLab);
        while(rs.next()){
            nombre = rs.getString("nom_lab");
        }
        con.commit();
        return nombre;
    }
    public static ResultSet getEquipos() throws Exception{
        Connection  con  = Conexion.getConexion();
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT nom_equ,id_lab FROM MEquipo ORDER BY id_lab,nom_equ");
        con.commit();
        return rs;
    }
    
    public static String addReporteNuevoAlumno(String nicAlu, String nicPro, String asi,String descRep, String nomEqu, String descTipoFalla, int tipFalla) throws Exception{
        Connection  con  = Conexion.getConexion();
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        int idERep = addEReporte(nicAlu, nicPro);
        int idCTFlla= addCTipoFalla(descTipoFalla, tipFalla);
        if(idERep!=0 && idCTFlla!=0){ 
            st.execute("INSERT INTO MReporte (nom_equ,nom_asi,des_rep,fec_rep,id_ece,id_ere, id_tfa) VALUES('"+nomEqu+"','"+asi+"', '"+descRep+"', NOW(), "+idERep+", 1, "+idCTFlla+")");
        }
        con.commit();
        int idRep = 0;
        
        return null;
    }
    public static String addReporteNuevoProfesor(String nicPro, String asi,String descRep, String nomEqu, String descTipoFalla, int tipFalla) throws Exception{
        Connection con = getConexion();
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        int idERep = addEReporteProfesor(nicPro);
        int idCTFlla= addCTipoFalla(descTipoFalla, tipFalla);
        if(idERep!=0 && idCTFlla!=0){ 
            st.execute("INSERT INTO MReporte (nom_equ,nom_asi,des_rep,fec_rep,id_ece,id_ere, id_tfa) VALUES('"+nomEqu+"','"+asi+"', '"+descRep+"', NOW(), "+idERep+", 1, "+idCTFlla+")");
        }
        int idRep = 0;
        con.commit();
        con.close();
        return null;
    }
    public static void EliminarUsuario(String nick) throws  ClassNotFoundException, SQLException{
        Statement st = getConexion().createStatement();
        st.executeUpdate("DELETE FROM MUsuario WHERE nic_usu='"+nick+"'");
    }
    public static void EliminarAlumno(int idAlu) throws  ClassNotFoundException, SQLException{
        Statement st = getConexion().createStatement();
        st.executeUpdate("DELETE FROM MAlumno WHERE id_alu="+idAlu);
    }
    public static void EliminarTecnico(int idTec) throws  ClassNotFoundException, SQLException{
        Statement st = getConexion().createStatement();
        st.executeUpdate("DELETE FROM MTecnico WHERE id_tec="+idTec);
    }
    public static void EliminarProfesor(int idPro) throws  ClassNotFoundException, SQLException{
        Statement st = getConexion().createStatement();
        st.executeUpdate("DELETE FROM MProfesor WHERE id_pro="+idPro);
    }
    public static void EliminarLaboratorio(int idLab) throws  ClassNotFoundException, SQLException{
        Statement st = getConexion().createStatement();
        st.executeUpdate("DELETE FROM MLaboratorio WHERE id_lab="+idLab);
    }
    public static void EliminarEquipo(String nomEqui) throws  ClassNotFoundException, SQLException{
        Statement st = getConexion().createStatement();
        st.executeUpdate("DELETE FROM MEquipo WHERE nom_equ='"+nomEqui+"'");
    }
    public static void EliminarReporte(Connection con, int idRep) throws SQLException, ClassNotFoundException{
        Statement st = con.createStatement();
        con.setAutoCommit(false);
        ResultSet rs = st.executeQuery("SELECT id_rep,id_ere, id_tfa, id_ece FROM MReporte NATURAL JOIN EReporte NATURAL JOIN CEstadoReporte NATURAL JOIN CTipoFalla WHERE id_rep="+idRep);
        int idERep = 0;
        int idTfal = 0;
        int idEst = 0;
        while(rs.next()){
            idERep = rs.getInt("id_ece");
            idTfal = rs.getInt("id_tfa");
            idEst = rs.getInt("id_ere");
        }
        st.executeUpdate("DELETE FROM EReporte WHERE id_ece="+idERep);
        st.executeUpdate("DELETE FROM CTipoFalla WHERE id_tfa="+idTfal);
        st.executeUpdate("DELETE FROM MReporte WHERE id_rep="+idRep);
        con.commit();
    }
    public static int ModificarNombreLaboratorio(int idLab, String nomLab) throws  ClassNotFoundException, SQLException{
        Statement st = getConexion().createStatement();
        st.executeUpdate("UPDATE MLaboratorio SET nom_lab='"+nomLab+"' WHERE id_lab="+idLab);
        return idLab;
    }
    public static ResultSet getReportesAlumno(String nicUsu) throws SQLException, ClassNotFoundException{
        Statement st = getConexion().createStatement();
        int idUsu = getIdAlumno(nicUsu);
        ResultSet rs = st.executeQuery("SELECT  id_pro, id_rep, des_rep, nom_equ, nom_lab, des_ere, fec_rep FROM MAlumno NATURAL JOIN VProfesor_EReporte NATURAL JOIN EReporte NATURAL JOIN MReporte NATURAL JOIN CEstadoReporte NATURAL JOIN CTipoFalla NATURAL JOIN MEquipo NATURAL JOIN MLaboratorio WHERE id_pro="+idUsu);
        return rs;
    }
    public static ResultSet getReportesProfesor(String nicUsu) throws SQLException, ClassNotFoundException{
        Statement st = getConexion().createStatement();
        int idUsu = getIdProfesor(nicUsu);
        ResultSet rs = st.executeQuery("SELECT  id_alu,id_pro, id_rep, des_rep, nom_equ, nom_lab, des_ere, fec_rep FROM VProfesor_EReporte NATURAL JOIN EReporte NATURAL JOIN MReporte NATURAL JOIN CEstadoReporte NATURAL JOIN CTipoFalla NATURAL JOIN MEquipo NATURAL JOIN MLaboratorio WHERE id_pro="+idUsu);
        return rs;
    }
    public static ResultSet getReportes() throws SQLException, ClassNotFoundException{
        Statement st = getConexion().createStatement();
        ResultSet rs = st.executeQuery("SELECT id_ere, id_alu, id_pro, id_rep, des_rep, nom_equ, nom_lab, tip_fal, des_ere, fec_rep FROM MAlumno NATURAL JOIN VProfesor_EReporte NATURAL JOIN EReporte NATURAL JOIN MReporte NATURAL JOIN CEstadoReporte NATURAL JOIN CTipoFalla NATURAL JOIN MEquipo NATURAL JOIN MLaboratorio");
        return rs;  
    }
    
    public static ResultSet getReportesAdm(Connection con) throws SQLException, ClassNotFoundException{
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT id_alu, id_ere, id_pro, id_rep, des_rep, nom_equ, nom_lab, tip_fal, des_ere, fec_rep FROM VProfesor_EReporte NATURAL JOIN EReporte NATURAL JOIN MReporte NATURAL JOIN CEstadoReporte NATURAL JOIN CTipoFalla NATURAL JOIN MEquipo NATURAL JOIN MLaboratorio");
        con.commit();
        return rs;  
    }
    public static void setEstadoReporte(int idErep, int idRep) throws SQLException, ClassNotFoundException, Exception{
        Connection conGlobal = Conexion.getConexion();
        conGlobal.setAutoCommit(false);
        Statement st = conGlobal.createStatement();
        st.executeUpdate("UPDATE MReporte SET id_ere="+idErep+" WHERE id_rep="+idRep);
        conGlobal.commit();
        conGlobal.close();
    }
    public static void addMReparacion(int idTec, int idRep, String desRep, String nomEqu) throws SQLException, ClassNotFoundException{
        Statement st = getConexion().createStatement();
        st.execute("INSERT INTO MReparacion (des_req, fec_req, id_rep, nom_equ, id_tec) VALUES ('"+desRep+"', NOW(), "+idRep+", '"+nomEqu+"', "+idTec+")");
    }
    public static String getNombreEquipo(int idRep) throws SQLException, ClassNotFoundException{
        Statement st = getConexion().createStatement();
        ResultSet rs = st.executeQuery("SELECT nom_equ FROM MReporte WHERE id_rep="+idRep);
        String nomEqu = null;
        while(rs.next()){
            nomEqu = rs.getString("nom_equ");
        }
        return nomEqu;
        
    }
    
}