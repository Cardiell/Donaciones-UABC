package ads.com.mx.donacionesuabc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.com.mx.donacionesuabc.entidades.Persona;

public class PersonaDao {
    Conexion conexion = new Conexion();

    public PersonaDao(){}

    public boolean agregarPersona(Persona per) {
        Connection con = null;
        CallableStatement cstm = null;
        boolean resp = true;
        try {
            con = conexion.getConexion();
            con.setAutoCommit(false);
            cstm = con.prepareCall("{Call guardaPersona(?,?,?,?)}");
            cstm.setString(1, per.getNombre());
            cstm.setString(2,per.getApellidoM());
            cstm.setString(3,per.getApellidoP());
            cstm.setString(4,per.getTelefono());
            resp = cstm.execute();
            con.commit();
        } catch (SQLException e){
            e.printStackTrace();
        }finally{
            conexion.Cerrar1(con, cstm);
        }
        return resp;
    }


    public Persona DamePersona(int idPersona){
        Connection con = null;
        ResultSet rs = null;
        CallableStatement cstm = null;
        Persona persona = null;
        String query = "exec DamePersona "+idPersona;

        try{
            con = conexion.getConexion();
            cstm = con.prepareCall(query);
            rs = cstm.executeQuery();
            if(rs.next()){
                persona = new Persona();
                persona.setNombre(rs.getString("nombre"));
                persona.setApellidoP(rs.getString("apellidoP"));
                persona.setApellidoM(rs.getString("apellidoM"));
                persona.setTelefono(rs.getString("telefono"));
            }
        }catch(Exception e) {
            System.err.println("Tenemos una excepcion: "+e.getMessage());
        }finally {
            conexion.Cerrar2(cstm,rs); //Cerrar conexion
        }
        return(persona);
    }


    public Persona endPersona(){
        Connection con = null;
        ResultSet rs = null;
        CallableStatement cstm = null;
        Persona persona = null;
        String query = "exec EndPersona";

        try{
            con = conexion.getConexion();
            cstm = con.prepareCall(query);
            rs = cstm.executeQuery();
            if(rs.next()){
                persona = new Persona();
                persona.setIdPersona(rs.getInt("idPersona"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellidoP(rs.getString("apellidoP"));
                persona.setApellidoM(rs.getString("apellidoM"));
                persona.setTelefono(rs.getString("telefono"));
            }
        }catch(Exception e) {
            System.err.println("Tenemos una excepcion: "+e.getMessage());
        }finally {
            conexion.Cerrar2(cstm,rs); //Cerrar conexion
        }
        return(persona);
    }

}
