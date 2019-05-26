package ads.com.mx.donacionesuabc.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import ads.com.mx.donacionesuabc.dao.PersonaDao;
import ads.com.mx.donacionesuabc.dao.UsuarioDAO;
import ads.com.mx.donacionesuabc.entidades.Persona;
import ads.com.mx.donacionesuabc.entidades.Usuario;
import ads.com.mx.donacionesuabc.fragments.AcercaDe;
import ads.com.mx.donacionesuabc.fragments.CompartirFragment;
import ads.com.mx.donacionesuabc.fragments.DonacionesFragment;
import ads.com.mx.donacionesuabc.fragments.InicioDonador;
import ads.com.mx.donacionesuabc.R;

public class DonadorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences.Editor mEditor;
    public static Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donador);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                user= null;
            } else {
                user= (Usuario)extras.get("user");
            }
        } else {
            user= (Usuario) savedInstanceState.getSerializable("user");
        }

        guardarValor("correo",user.getCorreo());
        guardarValor("pass",user.getPassword());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        txtCorreo = navigationView.getHeaderView(0).findViewById(R.id.menuCorreo);
        txtNombre = navigationView.getHeaderView(0).findViewById(R.id.textNombre);
        txtCorreo.setText(user.getCorreo());
        Persona per = new PersonaDao().DamePersona(user.getIdPersona());
        txtNombre.setText(per.getNombre()+" "+per.getApellidoP()+" "+per.getApellidoM());
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new InicioDonador()).commit();
        navigationView.getMenu().getItem(0).setChecked(true);
        user = new UsuarioDAO().consultaUsuario(user.getCorreo(),user.getPassword());

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.donador, menu);
        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,
                    new InicioDonador()).commit();
        } else if (id == R.id.nav_donaciones) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,
                    new DonacionesFragment()).commit();

        } else if (id == R.id.nav_change) {
            user.setRol(false);
            new UsuarioDAO().UpdateUsuario(user);
            Intent intent = new Intent(DonadorActivity.this,ReceptorActivity.class);
            intent.putExtra("user", user);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,
                    new CompartirFragment()).commit();
        } else if (id == R.id.nav_Acercade) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,
                    new AcercaDe()).commit();
        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_close)
        {
            guardarValor("correo","...");
            Intent intent = new Intent(DonadorActivity.this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // <- Aquí :)
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void guardarValor(String key, String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private TextView txtNombre;
    private TextView txtCorreo;

}
