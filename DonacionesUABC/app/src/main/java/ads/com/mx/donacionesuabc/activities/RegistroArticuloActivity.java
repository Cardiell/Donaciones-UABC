package ads.com.mx.donacionesuabc.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import ads.com.mx.donacionesuabc.R;
import ads.com.mx.donacionesuabc.Utils;
import ads.com.mx.donacionesuabc.dao.ArticuloDAO;
import ads.com.mx.donacionesuabc.entidades.Articulo;

public class RegistroArticuloActivity extends AppCompatActivity {

    byte[] imagen;
    String facultad,dia,hora,lugar,entrega;
    public boolean photo=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_articulo);
        initComponents();
    }

    private void initComponents(){
        spnFacultad = (Spinner)findViewById(R.id.facultad);
        spnDia= (Spinner)findViewById(R.id.dia);
        spnHora= (Spinner)findViewById(R.id.hora);
        spnLugar= (Spinner)findViewById(R.id.lugar);
        txtNombre = (EditText)findViewById(R.id.edtNombre);
        edtDescripcion = (EditText)findViewById(R.id.txtDescripcion);
        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        btnCamara = (ImageButton)findViewById(R.id.btnCamara);
        np = findViewById(R.id.cantidad);
        np.setMinValue(1);
        np.setMaxValue(20);
        np.setWrapSelectorWheel(true);

        spnFacultad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
               facultad= (String) adapterView.getItemAtPosition(pos);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {    }
        });
        spnDia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                dia= (String) adapterView.getItemAtPosition(pos);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {    }
        });

        spnHora.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                hora= (String) adapterView.getItemAtPosition(pos);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {    }
        });

        spnLugar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                lugar= (String) adapterView.getItemAtPosition(pos);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {    }
        });
    }

    public void onClickCamara(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }

    public boolean isEmptyTxt(EditText txt){
        return !(txt.getText().toString().isEmpty());
    }

    public void onClickGuardar(View view){
        int cantidad = Integer.valueOf(np.getValue());
        if(photo){
            if(isEmptyTxt(txtNombre)) {
                new ArticuloDAO().AgregarArticulo(new Articulo(String.valueOf(txtNombre.getText()), cantidad, facultad, dia, hora, lugar, imagen, String.valueOf(edtDescripcion.getText())));
                finish();
                Toast.makeText(this,"Datos guardados satisfactoriamente",Toast.LENGTH_SHORT).show();
            }else
                Toast.makeText(this,"Favor dde ingresar un nombre",Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this,"Favor de tomar una fotografia del articulo",Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imagen = Utils.getBytes(bitmap);
        btnCamara.setImageBitmap(bitmap);
        photo = true;
    }

    private ImageButton btnCamara;
    private NumberPicker np;
    private Spinner spnFacultad;
    private Spinner spnDia;
    private Spinner spnHora;
    private Spinner spnLugar;
    private EditText txtNombre;
    private EditText edtDescripcion;
    private Button btnGuardar;
}
