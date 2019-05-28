package ads.com.mx.donacionesuabc.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ads.com.mx.donacionesuabc.R;
import ads.com.mx.donacionesuabc.dao.PersonaDao;
import ads.com.mx.donacionesuabc.dao.UsuarioDAO;
import ads.com.mx.donacionesuabc.entidades.Persona;
import ads.com.mx.donacionesuabc.entidades.Usuario;


public class RegistroExternoActivity extends AppCompatActivity {

    String espacio = " ";
    private String apellidoP = "";
    private String apellidoM = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_externo);
        getSupportActionBar().hide();
        initComponent();

    }

    private void initComponent(){
        txtNombre = findViewById(R.id.txtNombre);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtPassword = findViewById(R.id.txtPass1);
        txtPasswordConfirma = findViewById(R.id.txtPass2);
        txtTelefono = findViewById(R.id.txtTelefono);


    }

    public boolean isEmptyTxt(EditText txt){
        return !(txt.getText().toString().isEmpty());
    }

    public String toStringTxt(EditText txt){
        return txt.getText().toString();
    }

    public void onClickAceptar(View view){
        if(isEmptyTxt(txtNombre)&&isEmptyTxt(txtApellidos)&&isEmptyTxt(txtTelefono)&&isEmptyTxt(txtCorreo)&&isEmptyTxt(txtPassword)) {
            if (toStringTxt(txtPasswordConfirma).equals(toStringTxt(txtPassword))) {
                //filtrar apellidos.....
                apellidoP = toStringTxt(txtApellidos).substring(0,toStringTxt(txtApellidos).indexOf(espacio));
                apellidoM = toStringTxt(txtApellidos).substring(toStringTxt(txtApellidos).indexOf(espacio)+1);
                Persona persona = new Persona(toStringTxt(txtNombre), apellidoP,apellidoM, toStringTxt(txtTelefono));
                new PersonaDao().agregarPersona(persona);
                Persona temp = new PersonaDao().endPersona();
                Usuario user = new Usuario(toStringTxt(txtCorreo), toStringTxt(txtPassword), temp.getIdPersona(), true, true, true);
                new UsuarioDAO().agregarUsuario(user);
                Intent intent = new Intent(RegistroExternoActivity.this, DonadorActivity.class);
                intent.putExtra("user", user);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // <- Aquí :)
                startActivity(intent);
                finish();
            } else
                Toast.makeText(this, "Las contrase;as no son iguales", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show();
    }

    private EditText txtNombre;
    private EditText txtApellidos;
    private EditText txtCorreo;
    private EditText txtPassword;
    private EditText txtPasswordConfirma;
    private EditText txtTelefono;
}
