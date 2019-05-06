package ads.com.mx.donacionesuabc.dao;



import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ads.com.mx.donacionesuabc.entidades.Articulo;


/* ****************************************************
 *                                                    *
 *   CLASE PARA REALIZAR CONSULTAS A ARTICULOS POR    *
 *   CODIGO Y NOMBRE                                  *
 *                                                    *
 ******************************************************/
public class ArticuloDAO{
    Conexion conexion = new Conexion();
    public ArticuloDAO(){}

    public Articulo consultaCodigo(String id){
        Connection con = null;
        ResultSet rs = null;
        CallableStatement cstm = null;
        Articulo articulo = null;
        String query = "exec consultaCodigo '"+id+"'";

        try{
            con = conexion.getConexion();
            cstm = con.prepareCall(query);
            rs = cstm.executeQuery();
            if(rs.next()){
                articulo = new Articulo();
                articulo.setIdProducto(rs.getInt("idProducto"));
                articulo.setNombre(rs.getString("nombre"));
                articulo.setCantidad(rs.getInt("existencia"));
            }
        }catch(Exception e) {
            System.err.println("Tenemos una excepcion: "+e.getMessage());
        }finally {
            conexion.Cerrar2(cstm,rs); //Cerrar conexion
        }
        return(articulo);
    }

    public ArrayList consultaNombre2(String nombre){
        Connection con = null;
        ResultSet rs = null;
        CallableStatement cstm = null;
        Articulo articulo = null;
        ArrayList<String> lista=new ArrayList();

        String query = "exec busquedaProducto '"+nombre+"'";
        try{
            con = conexion.getConexion();
            cstm = con.prepareCall(query);
            rs = cstm.executeQuery();
            if(rs.next()){
                do {
                    articulo = new Articulo();
                    articulo.setIdProducto(rs.getInt("idProducto"));
                    articulo.setNombre(rs.getString("nombre"));
                    articulo.setCantidad(rs.getInt("cantidad"));
                    lista.add(rs.getString("idProducto")+" "+rs.getString("nombre"));
                } while(rs.next());

            }
        }catch(Exception e) {
            System.err.println("Tenemos una excepcion: "+e.getMessage());

        }finally {
            conexion.Cerrar2(cstm,rs); //Cerrar conexion
        }
        System.out.println(lista);
        return(lista);
    }

    public Articulo consultaNombre(String nombre){
        Connection con = null;
        ResultSet rs = null;
        CallableStatement cstm = null;
        Articulo articulo = null;

        String query = "exec consultaNombre '"+nombre+"'";
        try{
            con = conexion.getConexion();
            cstm = con.prepareCall(query);
            rs = cstm.executeQuery();
            if(rs.next()){

                articulo = new Articulo();
                articulo.setIdProducto(rs.getInt("idProducto"));
                articulo.setNombre(rs.getString("nombre"));
                articulo.setCantidad(rs.getInt("cantidad"));


            }
        }catch(Exception e) {
            System.err.println("Tenemos una excepcion: "+e.getMessage());

        }finally {
            conexion.Cerrar2(cstm,rs); //Cerrar conexion
        }
        return(articulo);
    }

    public boolean AgregarArticulo(Articulo art) {
        Connection con = null;
        CallableStatement cstm = null;
        boolean resp = true;
        try {
            con = conexion.getConexion();
            con.setAutoCommit(false);
            cstm = con.prepareCall("{Call guardaArticulo(?,?,?,?,?,?,?,?)}");
            cstm.setString(1, art.getNombre());
            cstm.setInt(2,art.getCantidad());
            cstm.setString(3,art.getFacultad());
            cstm.setString(4,art.getDia());
            cstm.setString(5,art.getHora());
            cstm.setString(6,art.getLugar());
            cstm.setBytes(7,art.getImagen());
            cstm.setString(8,art.getDescripcion());
            resp = cstm.execute();
            con.commit();
        } catch (SQLException e){
            e.printStackTrace();
            e.getErrorCode();
            e.getCause();
        }finally{
            conexion.Cerrar1(con, cstm);
        }
        return resp;
    }

    public List<Articulo> ListarArticulos() {
        Connection con = null;
        CallableStatement cstm = null;
        ResultSet rs = null;
        List<Articulo> lista = null;
        try {
            lista = new ArrayList<>();
            con = conexion.getConexion();
            cstm = con.prepareCall("{Call ListarArticulos}");
            rs = cstm.executeQuery();
            Articulo art = null;
            while (rs.next()) {
                art = new Articulo();
                art.setIdProducto(rs.getInt("idProducto"));
                art.setNombre(rs.getString("nombre"));
                art.setCantidad(rs.getInt("cantidad"));
                art.setFacultad(rs.getString("facultad"));
                art.setDia(rs.getString("dia"));
                art.setHora(rs.getString("hora"));
                art.setLugar(rs.getString("lugar"));
                art.setImagen(rs.getBytes("imagen"));
                art.setDescripcion(rs.getString("descripcion"));

                lista.add(art);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            conexion.Cerrar2(cstm, rs);
        }
        return lista;
    }

}
