package ads.com.mx.donacionesuabc.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ads.com.mx.donacionesuabc.R;

public class AcercadeActivity extends AppCompatActivity {
    byte flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acercade);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!= null)
        {
            switch ((String)bundle.get("name"))
            {
                case "DonadorActivity":
                        flag=1;
                    break;
                case "LoginActivity":
                        flag=0;
                    break;
            }
        }
    }

    public void onClickAceptar(View view){
        if(flag==0) {
            Intent intent = new Intent(AcercadeActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // <- Aquí :)
            startActivity(intent);
        } else if(flag ==1)
        {
            Intent intent = new Intent(AcercadeActivity.this, DonadorActivity.class);
            startActivity(intent);
        }
    }
}
