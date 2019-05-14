package ads.com.mx.donacionesuabc.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import ads.com.mx.donacionesuabc.entidades.Usuario;


/* ****************************************************
 *                                                    *
 *   CLASE PARA REALIZAR CONSULTA DE USUARIO          *
 *                                                    *
 ******************************************************/
public class UsuarioDAO {

    Conexion conexion = new Conexion();

    public UsuarioDAO(){}

    public Usuario consultaUsuario(String correo, String pass){
        Connection con = null;
        ResultSet rs = null;
        CallableStatement cstm = null;
        Usuario usuario = null;
        String query = "exec consultaUsuario '"+correo+"','"+pass+"'";

        try{
            con = conexion.getConexion();
            cstm = con.prepareCall(query);
            rs = cstm.executeQuery();
            if(rs.next()){
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setPassword(rs.getString("password"));
                usuario.setIdPersona(rs.getInt("idPersona"));
                usuario.setAcceso(rs.getBoolean("acceso"));
                usuario.setRol(rs.getBoolean("idRol"));
                usuario.setTipo(rs.getBoolean("tipo"));

            }
        }catch(Exception e) {
            System.err.println("Tenemos una excepcion: "+e.getMessage());
        }finally {
            conexion.Cerrar2(cstm,rs); //Cerrar conexion
        }
        return(usuario);
    }

    public boolean agregarUsuario(Usuario user) {
        Connection con = null;
        CallableStatement cstm = null;
        boolean resp = true;
        try {
            con = conexion.getConexion();
            con.setAutoCommit(false);
            cstm = con.prepareCall("{Call guardaUsuarios(?,?,?,?,?,?)}");
            cstm.setString(1, user.getCorreo());
            cstm.setString(2,user.getPassword());
            cstm.setInt(3,user.getIdPersona());
            cstm.setBoolean(4,user.isAcceso());
            cstm.setBoolean(5,user.isRol());
            cstm.setBoolean(6,user.isTipo());
            resp = cstm.execute();
            con.commit();
        } catch (SQLException e){
            e.printStackTrace();
        }finally{
            conexion.Cerrar1(con, cstm);
        }
        return resp;
    }

    public boolean UpdateUsuario(Usuario user) {
        Connection con = null;
        CallableStatement cstm = null;
        boolean resp = true;
        try {
            con = conexion.getConexion();
            con.setAutoCommit(false);
            cstm = con.prepareCall("{Call UpdateUsuario(?,?,?,?,?,?)}");
            cstm.setString(1, user.getCorreo());
            cstm.setString(2,user.getPassword());
            cstm.setInt(3,user.getIdPersona());
            cstm.setBoolean(4,user.isAcceso());
            cstm.setBoolean(5,user.isRol());
            cstm.setBoolean(6,user.isTipo());
            resp = cstm.execute();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            conexion.Cerrar1(con, cstm);
        }
        return resp;
    }

}
