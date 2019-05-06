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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_externo);
        getSupportActionBar().hide();
        initComponent();

    }

    private void initComponent(){
        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtApellidoP = (EditText)findViewById(R.id.txtApellidoP);
        txtApellidoM = (EditText)findViewById(R.id.txtApellidoM);
        txtCorreo = (EditText)findViewById(R.id.txtCorreo);
        txtPassword = (EditText)findViewById(R.id.txtPass1);
        txtPasswordConfirma = (EditText)findViewById(R.id.txtPass2);
        txtTelefono = (EditText)findViewById(R.id.txtTelefono);


    }

    public boolean isEmptyTxt(EditText txt){
        return !(txt.getText().toString().isEmpty());
    }

    public String toStringTxt(EditText txt){
        return txt.getText().toString();
    }

    public void onClickAceptar(View view){
        if(isEmptyTxt(txtNombre)&&isEmptyTxt(txtApellidoP)&&isEmptyTxt(txtApellidoM)&&isEmptyTxt(txtTelefono)&&isEmptyTxt(txtCorreo)&&isEmptyTxt(txtPassword)) {
            if (toStringTxt(txtPasswordConfirma).equals(toStringTxt(txtPassword))) {
                Persona persona = new Persona(toStringTxt(txtNombre), toStringTxt(txtApellidoP), toStringTxt(txtApellidoM), toStringTxt(txtTelefono));
                new PersonaDao().agregarPersona(persona);
                Persona temp = new PersonaDao().endPersona();
                Usuario user = new Usuario(txtCorreo.getText().toString(), txtPassword.getText().toString(), true, true, true, temp.getIdPersona());
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
    private EditText txtApellidoP;
    private EditText txtApellidoM;
    private EditText txtCorreo;
    private EditText txtPassword;
    private EditText txtPasswordConfirma;
    private EditText txtTelefono;
}
