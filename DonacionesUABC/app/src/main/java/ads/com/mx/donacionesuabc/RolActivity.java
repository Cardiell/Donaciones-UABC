package ads.com.mx.donacionesuabc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol);
    }

    public void onClickDonador(View view){
        Intent intent = new Intent(RolActivity.this,DonadorActivity.class);
        startActivity(intent);
    }

    public void onClickReceptor(View view){

    }
}
