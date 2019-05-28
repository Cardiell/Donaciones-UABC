package ads.com.mx.donacionesuabc.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ads.com.mx.donacionesuabc.CamaraPermisos;
import ads.com.mx.donacionesuabc.R;
import ads.com.mx.donacionesuabc.dao.UsuarioDAO;
import ads.com.mx.donacionesuabc.entidades.Usuario;

public class LoginActivity extends AppCompatActivity {

    private static String PREFS_KEY = "mispreferencias";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        new CamaraPermisos().solicitarPermisos(this);
        Usuario user = new UsuarioDAO().consultaUsuario(leerValor("correo"),leerValor("pass"));
        if(user== null){
            setContentView(R.layout.activity_login);
            getSupportActionBar().hide();
            initComponents();
        }else {
            if(user.isRol()) {
                Intent intent = new Intent(this, DonadorActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // <- Aquí :)
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent(this, ReceptorActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // <- Aquí :)
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        }
    }

    private String leerValor(String keyPref){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return sharedPreferences.getString(keyPref, "temp") ;
    }

    private void initComponents(){
        txtPass = (EditText)findViewById(R.id.txtPass);
        txtUser = (EditText)findViewById(R.id.txtUser);
    }

    public void onClickAcercaDe(View view) {
        Intent intent = new Intent(LoginActivity.this, AcercadeActivity.class);
        intent.putExtra("name","LoginActivity");
        startActivity(intent);
    }

    public boolean isEmptyTxt(EditText txt){
        return  !(txt.getText().toString().isEmpty());
    }

    public void onClickAcceder(View view){
        UsuarioDAO consulta = new UsuarioDAO();
        if(isEmptyTxt(txtUser)&&isEmptyTxt(txtPass)){
            Usuario user = consulta.consultaUsuario(txtUser.getText().toString(),txtPass.getText().toString());

            if(user != null) {
                if(user.isAcceso()){
                    if(user.isRol()) {
                        Intent intent = new Intent(LoginActivity.this, DonadorActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // <- Aquí :)
                        intent.putExtra("user", user);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(LoginActivity.this, ReceptorActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // <- Aquí :)
                        intent.putExtra("user", user);
                        startActivity(intent);
                        finish();
                    }
                }else {
                    Intent intent = new Intent(LoginActivity.this, RolActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // <- Aquí :)
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                }
            }else{
                Toast.makeText(this,"Usuario invalido", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,"Campos vacios", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickRegistrar(View view) {
        Intent intent = new Intent(LoginActivity.this,RegistroExternoActivity.class);
        startActivity(intent);
    }

    private EditText txtUser;
    private EditText txtPass;
}
