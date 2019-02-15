package ads.com.mx.donacionesuabc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AcercadeActivity extends AppCompatActivity {
    byte flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acercade);
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
            startActivity(intent);
        } else if(flag ==1)
        {
            Intent intent = new Intent(AcercadeActivity.this, DonadorActivity.class);
            startActivity(intent);
        }
    }
}
