package ads.com.mx.donacionesuabc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClickAcercaDe(View view) {
        Intent intent = new Intent(LoginActivity.this, AcercadeActivity.class);
        intent.putExtra("name","LoginActivity");
        startActivity(intent);
    }

    public void onClickAcceder(View view){
        Intent intent = new Intent(LoginActivity.this,RolActivity.class);
        startActivity(intent);
    }

    public void onClickRegistrar(View view) {
        Intent intent = new Intent(LoginActivity.this,RegistroExternoActivity.class);
        startActivity(intent);
    }
}
