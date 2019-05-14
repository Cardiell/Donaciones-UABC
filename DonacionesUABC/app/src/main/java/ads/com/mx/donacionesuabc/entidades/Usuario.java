package ads.com.mx.donacionesuabc.entidades;

import java.io.Serializable;

public class Usuario  extends Persona implements Serializable{
    private int idUsuario;
    private String correo;
    private String password;
    private int idPersona;
    private boolean acceso;
    private boolean rol;
    private boolean tipo;

    public Usuario(int idUsuario,String correo, String password, int idPersona, boolean acceso, boolean rol, boolean tipo) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.password = password;
        this.idPersona = idPersona;
        this.acceso = acceso;
        this.rol = rol;
        this.tipo = tipo;
    }

    public Usuario(String correo, String password, int idPersona, boolean acceso, boolean rol, boolean tipo) {
        this.correo = correo;
        this.password = password;
        this.idPersona = idPersona;
        this.acceso = acceso;
        this.rol = rol;
        this.tipo = tipo;
    }

    public Usuario(){}


    @Override
    public int getIdPersona() {
        return idPersona;
    }

    @Override
    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAcceso() {
        return acceso;
    }

    public void setAcceso(boolean acceso) {
        this.acceso = acceso;
    }

    public boolean isRol() {
        return rol;
    }

    public void setRol(boolean rol) {
        this.rol = rol;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

}
