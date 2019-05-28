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
        System.out.println("consultaNombre2: "+ query);

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
        System.out.println("consultaNombre: "+ query);

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
            cstm = con.prepareCall("{Call guardaArticulo(?,?,?,?,?,?,?,?,?)}");
            cstm.setInt(1,art.getIdUsuario());
            cstm.setString(2, art.getNombre());
            cstm.setInt(3,art.getCantidad());
            cstm.setBytes(4,art.getImagen());
            cstm.setString(5,art.getFacultad());
            cstm.setString(6,art.getDia());
            cstm.setString(7,art.getHora());
            cstm.setString(8,art.getLugar());
            cstm.setString(9,art.getDescripcion());
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


    public boolean agregarSolicitud(int idUsuario) {
        Connection con = null;
        CallableStatement cstm = null;
        boolean resp = true;
        System.out.println("agregarSolicitud: "+ idUsuario);

        try {
            con = conexion.getConexion();
            con.setAutoCommit(false);
            cstm = con.prepareCall("{Call guardaSolicitud(?)}");
            cstm.setInt(1, idUsuario);
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

    public boolean agregarDetallesDonacion(int idProducto,int idSolicitud) {
        Connection con = null;
        CallableStatement cstm = null;
        boolean resp = true;
        System.out.println("agregarDetallesDonacion: "+ idProducto+"    "+idSolicitud);

        try {
            con = conexion.getConexion();
            con.setAutoCommit(false);
            cstm = con.prepareCall("{Call guardaDetallesDonacion(?,?)}");
            cstm.setInt(1, idProducto);
            cstm.setInt(2, idSolicitud);
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

    public boolean updateDetallesDonacion(int idProducto,int idSolicitud,boolean aceptar) {
        Connection con = null;
        CallableStatement cstm = null;
        boolean resp = true;
        System.out.println("UpdateDetallesDonacion: "+idProducto+"    "+idSolicitud+"    "+aceptar);

        try {
            con = conexion.getConexion();
            con.setAutoCommit(false);
            cstm = con.prepareCall("{Call updateDetallesDonacion(?,?,?)}");
            cstm.setInt(1, idProducto);
            cstm.setInt(2,idSolicitud);
            cstm.setBoolean(3,aceptar);
            resp = cstm.execute();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            conexion.Cerrar1(con, cstm);
        }
        return resp;
    }


    public List<Articulo> listarxUsuario(int idUsuario){
        Connection con = null;
        ResultSet rs = null;
        CallableStatement cstm = null;
        List<Articulo> lista = null;
        String query = "exec ListarxUsuario "+idUsuario;
        System.out.println("ListarxUsuario: "+ query);

        try{
            lista = new ArrayList<>();
            con = conexion.getConexion();
            cstm = con.prepareCall(query);
            rs = cstm.executeQuery();
            Articulo articulo = null;
            while(rs.next()){

                articulo = new Articulo();
                articulo.setIdProducto(rs.getInt("idProducto"));
                articulo.setNombre(rs.getString("nombre"));
                articulo.setCantidad(rs.getInt("cantidad"));
                articulo.setImagen(rs.getBytes("imagen"));
                articulo.setLugar(rs.getString("lugar"));
                articulo.setDia(rs.getString("dia"));
                articulo.setHora(rs.getString("hora"));

                lista.add(articulo);

            }
        }catch(Exception e) {
            System.err.println("Tenemos una excepcion: "+e.getMessage());

        }finally {
            conexion.Cerrar2(cstm,rs); //Cerrar conexion
        }
        return(lista);
    }


    public List<Articulo> listarDetalleDonacion(int idUsuario){
        Connection con = null;
        ResultSet rs = null;
        CallableStatement cstm = null;
        List<Articulo> lista = null;


        String query = "exec listarDetallesDonacionxuser "+idUsuario;
        System.out.println("ListaDetalleDonacion: "+ query);
        try{
            lista = new ArrayList<>();
            con = conexion.getConexion();
            cstm = con.prepareCall(query);
            rs = cstm.executeQuery();
            Articulo articulo = null;
            while(rs.next()){

                articulo = new Articulo();
                articulo.setIdProducto(rs.getInt("idProducto"));
                articulo.setSolicitudes(rs.getInt("idSolicitud"));
                articulo.setAceptar(rs.getBoolean("aceptar"));

                lista.add(articulo);

            }
        }catch(Exception e) {
            System.err.println("Tenemos una excepcion: "+e.getMessage());

        }finally {
            conexion.Cerrar2(cstm,rs); //Cerrar conexion
        }
        return(lista);
    }
    public List<Articulo> listarxNombre(String nombre){
        Connection con = null;
        ResultSet rs = null;
        CallableStatement cstm = null;
        List<Articulo> lista = null;


        String query = "exec ListarArticulosxNombre "+nombre;
        System.out.println("ListarxNombre: "+ query);
        try{
            lista = new ArrayList<>();
            con = conexion.getConexion();
            cstm = con.prepareCall(query);
            rs = cstm.executeQuery();
            Articulo art = null;
            while(rs.next()){

                art = new Articulo();
                art.setIdProducto(rs.getInt("idProducto"));
                art.setIdUsuario(rs.getInt("idUsuario"));
                art.setNombre(rs.getString("nombre"));
                art.setCantidad(rs.getInt("cantidad"));
                art.setImagen(rs.getBytes("imagen"));
                art.setFacultad(rs.getString("facultad"));
                art.setDia(rs.getString("dia"));
                art.setHora(rs.getString("hora"));
                art.setLugar(rs.getString("lugar"));
                art.setDescripcion(rs.getString("descripcion"));
                lista.add(art);

            }
        }catch(Exception e) {
            System.err.println("Tenemos una excepcion: "+e.getMessage());

        }finally {
            conexion.Cerrar2(cstm,rs); //Cerrar conexion
        }
        return(lista);
    }






    public Articulo listarArticuloxId(int idArticulo){
        Connection con = null;
        ResultSet rs = null;
        CallableStatement cstm = null;
        Articulo art = null;


        String query = "exec ListarArticulosxId "+idArticulo;
        System.out.println("ListarArticulosxId: "+query);
        try{
            art = new Articulo();
            con = conexion.getConexion();
            cstm = con.prepareCall(query);
            rs = cstm.executeQuery();
            while(rs.next()){

                art = new Articulo();
                art.setIdProducto(rs.getInt("idProducto"));
                art.setIdUsuario(rs.getInt("idUsuario"));
                art.setNombre(rs.getString("nombre"));
                art.setCantidad(rs.getInt("cantidad"));
                art.setImagen(rs.getBytes("imagen"));
                art.setFacultad(rs.getString("facultad"));
                art.setDia(rs.getString("dia"));
                art.setHora(rs.getString("hora"));
                art.setLugar(rs.getString("lugar"));
                art.setDescripcion(rs.getString("descripcion"));

            }
        }catch(Exception e) {
            System.err.println("Tenemos una excepcion: "+e.getMessage());

        }finally {
            conexion.Cerrar2(cstm,rs); //Cerrar conexion
        }
        return(art);
    }






    public List<Articulo> dameSolicitudes(int idProducto){
        Connection con = null;
        ResultSet rs = null;
        CallableStatement cstm = null;
        List<Articulo> lista = null;


        String query = "exec nombresSolicitantes "+idProducto;
        System.out.println("dameSolicitudes: "+ query);

        try{
            lista = new ArrayList<>();
            con = conexion.getConexion();
            cstm = con.prepareCall(query);
            rs = cstm.executeQuery();
            Articulo articulo = null;
            while(rs.next()){

                articulo = new Articulo();
                articulo.setIdProducto(rs.getInt("idProducto"));
                articulo.setNombre(rs.getString("nombre"));
                articulo.setCantidad(rs.getInt("cantidad"));
                lista.add(articulo);

            }
        }catch(Exception e) {
            System.err.println("Tenemos una excepcion: "+e.getMessage());

        }finally {
            conexion.Cerrar2(cstm,rs); //Cerrar conexion
        }
        return(lista);
    }

    public int getLastSolicitud(){
        Connection con = null;
        ResultSet rs = null;
        CallableStatement cstm = null;
        String query = "exec listarSolicitud ";
        System.out.println("getLastSolicitud: "+ query);

        int cnt=0;
        try{
            con = conexion.getConexion();
            cstm = con.prepareCall(query);
            rs = cstm.executeQuery();
            while(rs.next()){
               cnt++;
            }
        }catch(Exception e) {
            System.err.println("Tenemos una excepcion: "+e.getMessage());
        }finally {
            conexion.Cerrar2(cstm,rs); //Cerrar conexion
        }
        return(cnt);
    }

    public List<Articulo> ListarArticulos(int idUsuario) {
        Connection con = null;
        CallableStatement cstm = null;
        ResultSet rs = null;
        String query ="exec ListarArticulos "+idUsuario;
        System.out.println("ListarArticulos: "+query);
        List<Articulo> lista = null;
        try {
            lista = new ArrayList<>();
            con = conexion.getConexion();
            cstm = con.prepareCall(query);
            rs = cstm.executeQuery();
            Articulo art = null;
            while (rs.next()) {
                art = new Articulo();
                art.setIdProducto(rs.getInt("idProducto"));
                art.setIdUsuario(rs.getInt("idUsuario"));
                art.setNombre(rs.getString("nombre"));
                art.setCantidad(rs.getInt("cantidad"));
                art.setImagen(rs.getBytes("imagen"));
                art.setFacultad(rs.getString("facultad"));
                art.setDia(rs.getString("dia"));
                art.setHora(rs.getString("hora"));
                art.setLugar(rs.getString("lugar"));
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
