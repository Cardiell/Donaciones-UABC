package ads.com.mx.donacionesuabc.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import ads.com.mx.donacionesuabc.R;
import ads.com.mx.donacionesuabc.Utils;
import ads.com.mx.donacionesuabc.dao.ArticuloDAO;
import ads.com.mx.donacionesuabc.entidades.Articulo;

public class RegistroArticuloActivity extends AppCompatActivity {


    private static final int COD_PHOTO = 20;
    private Bitmap bitmap;

    byte[] imagen;
    String facultad,dia,hora,lugar,entrega;
    public boolean photo=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_articulo);
        initComponents();
    }

    private void initComponents(){
        spnFacultad = findViewById(R.id.facultad);
        spnDia= findViewById(R.id.dia);
        spnHora= findViewById(R.id.hora);
        spnLugar= findViewById(R.id.lugar);
        txtNombre = findViewById(R.id.edtNombre);
        edtDescripcion = findViewById(R.id.txtDescripcion);
        imgCamara = findViewById(R.id.imageCamara);
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

    public void onClickCamara(View view){//MediaStore.ACTION_IMAGE_CAPTURE
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,COD_PHOTO);

    }

    public boolean isEmptyTxt(EditText txt){
        return !(txt.getText().toString().isEmpty());
    }

    public void onClickGuardar(View view){
        int cantidad = Integer.valueOf(np.getValue());
        if(photo){
            if(isEmptyTxt(txtNombre)) {
                    System.out.println("idUsuario ->"+DonadorActivity.user.getIdUsuario());
                System.out.println("correo ->"+DonadorActivity.user.getCorreo());
                    new ArticuloDAO().AgregarArticulo(new Articulo(DonadorActivity.user.getIdUsuario(),String.valueOf(txtNombre.getText()), cantidad,imagen, facultad, dia, hora, lugar, String.valueOf(edtDescripcion.getText())));
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
        if(requestCode ==COD_PHOTO && resultCode == RESULT_OK){

            Bitmap bitmap = data.getParcelableExtra("data");
            //Bitmap bit = BitmapFactory.decodeResource(getResources(),"data");
            Bitmap scaled = Bitmap.createScaledBitmap(bitmap,250,300,true);
            imgCamara.setImageBitmap(scaled);
            imagen = Utils.getBytes(scaled);
            photo = true;
        }

    }


    private ImageView imgCamara;
    private NumberPicker np;
    private Spinner spnFacultad;
    private Spinner spnDia;
    private Spinner spnHora;
    private Spinner spnLugar;
    private EditText txtNombre;
    private EditText edtDescripcion;
}
