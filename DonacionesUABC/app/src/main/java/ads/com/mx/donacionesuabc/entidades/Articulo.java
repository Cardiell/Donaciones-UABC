package ads.com.mx.donacionesuabc.entidades;

import java.io.Serializable;

public class Articulo implements Serializable {

    private int idProducto;
    private String nombre;
    private int cantidad;
    private String facultad;
    private String dia;
    private String hora;
    private String lugar;
    private byte[] imagen;
    private String descripcion;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Articulo(){}

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

    public String getNombre() {
        return nombre;
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
