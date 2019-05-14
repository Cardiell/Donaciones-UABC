package ads.com.mx.donacionesuabc.dao;
import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* ****************************************************
 *                                                    *
 *   CLASE PARA REALIZAR LA CONEXION A NUESTRA BD     *
 *                                                    *
 ******************************************************/
public class Conexion{
    private String IP = "uabc.database.windows.net";
    private String DB = "ADS";
    private String DBUser = "cardiell";
    private String DBPWD = "Sqlserver123";

    @SuppressLint("NewApi")

    public Connection getConexion(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conexion = null;
        String urlconexion;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            urlconexion = "jdbc:jtds:sqlserver://"+IP+"/"+DB+";user="+DBUser+";password="+DBPWD+";";
            conexion = DriverManager.getConnection(urlconexion);
        }catch(SQLException se){
            Log.e("Error here 1 : ", se.getMessage());
        }catch(ClassNotFoundException e){
            Log.e("Erron #2",e.getMessage());
        }catch (Exception e){
            Log.e("Error #3",e.getMessage());
        }
        return conexion;
    }

    public void Cerrar1(Connection con, CallableStatement cstm) {
        try {
            if (con != null) {
                con.close();
            }
            if (cstm != null) {
                cstm.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Cerrar2(CallableStatement cstm, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (cstm != null) {
                cstm.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Cerrar3(PreparedStatement cstm, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (cstm != null) {
                cstm.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Cerrar4(Statement st)
    {
        try
        {
            if(st!=null)
                st.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
