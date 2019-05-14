package ads.com.mx.donacionesuabc.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import ads.com.mx.donacionesuabc.R;
import ads.com.mx.donacionesuabc.dao.PersonaDao;
import ads.com.mx.donacionesuabc.dao.UsuarioDAO;
import ads.com.mx.donacionesuabc.entidades.Persona;
import ads.com.mx.donacionesuabc.entidades.Usuario;
import ads.com.mx.donacionesuabc.fragments.AcercaDe;
import ads.com.mx.donacionesuabc.fragments.InicioReceptor;

public class ReceptorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static Usuario usuario;
    private static String PREFS_KEY = "XD";
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptor);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                usuario= null;
            } else {
                usuario= (Usuario)extras.get("user");
            }
        } else {
            usuario= (Usuario) savedInstanceState.getSerializable("user");
        }

        guardarValor("correo",usuario.getCorreo());
        guardarValor("pass",usuario.getPassword());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layouter);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_viewer);
        navigationView.setNavigationItemSelectedListener(this);

        txtCorreo = navigationView.getHeaderView(0).findViewById(R.id.menuCorreo);
        txtNombre = navigationView.getHeaderView(0).findViewById(R.id.textNombre);
        txtCorreo.setText(usuario.getCorreo());

        Persona per = new PersonaDao().DamePersona(usuario.getIdPersona());
        txtNombre.setText(per.getNombre()+" "+per.getApellidoP()+" "+per.getApellidoM());
        System.out.println("Aqui Receptor");

        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new InicioReceptor()).commit();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        usuario = new UsuarioDAO().consultaUsuario(usuario.getCorreo(),usuario.getPassword());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layouter);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.receptor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Ropa) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,
                    new InicioReceptor()).commit();
        } else if (id == R.id.nav_donaciones) {

        } else if (id == R.id.nav_change) {
            usuario.setRol(true);
            new UsuarioDAO().UpdateUsuario(usuario);
            Intent intent = new Intent(ReceptorActivity.this,DonadorActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("user", usuario);
            startActivity(intent);

        } else if (id == R.id.nav_Acercade) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,
                    new AcercaDe()).commit();

        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_close) {
            guardarValor("correo","...");
            Intent intent = new Intent(ReceptorActivity.this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // <- Aquí :)
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layouter);
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
