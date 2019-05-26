package ads.com.mx.donacionesuabc.entidades;

import java.io.Serializable;

public class Articulo implements Serializable {

    private int idProducto;
    private int idUsuario;
    private String nombre;
    private int cantidad;
    private byte[] imagen;
    private String facultad;
    private String dia;
    private String hora;
    private String lugar;
    private String descripcion;
    private int solicitudes;

    public int getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(int solicitudes) {
        this.solicitudes = solicitudes;
    }

    public Articulo(){}

    public Articulo(int idUsuario, String nombre, int cantidad, byte[] imagen, String facultad, String dia, String hora, String lugar, String descripcion) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.imagen = imagen;
        this.facultad = facultad;
        this.dia = dia;
        this.hora = hora;
        this.lugar = lugar;
        this.descripcion = descripcion;
    }

    public Articulo(String nombre, int cantidad, String facultad, String dia, String hora, String lugar, byte[] imagen, String descripcion) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.facultad = facultad;
        this.dia = dia;
        this.hora = hora;
        this.lugar = lugar;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

    public Articulo(int idProducto, String nombre, int cantidad, String facultad, String dia, String hora, String lugar, byte[] imagen, String descripcion) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.facultad = facultad;
        this.dia = dia;
        this.hora = hora;
        this.lugar = lugar;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
