package ads.com.mx.donacionesuabc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegistroExternoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_externo);
    }

    public void onClickAceptar(View view){
        Intent intent = new Intent(RegistroExternoActivity.this,DonadorActivity.class);
        startActivity(intent);
    }
}
