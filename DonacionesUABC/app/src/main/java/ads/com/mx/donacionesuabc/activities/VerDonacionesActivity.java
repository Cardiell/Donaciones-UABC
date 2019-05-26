package ads.com.mx.donacionesuabc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ads.com.mx.donacionesuabc.Lista;
import ads.com.mx.donacionesuabc.ListaNombreSolicitantes;
import ads.com.mx.donacionesuabc.R;
import ads.com.mx.donacionesuabc.entidades.Persona;

import static ads.com.mx.donacionesuabc.activities.DonadorActivity.user;

public class VerDonacionesActivity extends AppCompatActivity {


    private int index;
    private ListaNombreSolicitantes lista;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Inicie................"+user.getCorreo());
        setContentView(R.layout.activity_ver_donaciones);
        System.out.println("HER: "+ Lista.persona.size());

        recyclerView = findViewById(R.id.rvListaSoli);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        lista = new ListaNombreSolicitantes(this);
        recyclerView.setAdapter(lista);
        cargarLista();
    }



    public void cargarLista(){
        System.out.println("HER: "+Lista.persona.get(0).getNombre());
        lista.addPersona(null);
        for (Persona articulo : Lista.persona){
            lista.addPersona(articulo);
        }
    }

    public void onClickAceptar(View view){
        onBackPressed();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    public int getIndex(){
        return this.index;
    }

    public void setIndex(int i){
        this.index = i;
    }
}
