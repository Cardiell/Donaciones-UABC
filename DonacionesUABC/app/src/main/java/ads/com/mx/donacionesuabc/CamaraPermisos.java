package ads.com.mx.donacionesuabc;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;


public class CamaraPermisos{

    final private int REQUEST_CODE_ASK_PERMISSION = 1994;

    public void solicitarPermisos(AppCompatActivity activity) {
        int permisoCamara = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);

        if (permisoCamara != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // Agregar los permisos en el Manifest solo funciona con versiones menores a la M
                activity.requestPermissions(new String[]{Manifest.permission.CAMERA},REQUEST_CODE_ASK_PERMISSION);
            }
        }
    }
}