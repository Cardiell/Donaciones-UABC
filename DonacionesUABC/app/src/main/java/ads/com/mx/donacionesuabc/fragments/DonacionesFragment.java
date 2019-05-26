package ads.com.mx.donacionesuabc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ads.com.mx.donacionesuabc.Lista;
import ads.com.mx.donacionesuabc.R;
import ads.com.mx.donacionesuabc.activities.DonadorActivity;
import ads.com.mx.donacionesuabc.dao.ArticuloDAO;
import ads.com.mx.donacionesuabc.entidades.Articulo;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonacionesFragment extends Fragment {

    private int index;
    private Lista lista;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;

    private int resultado;
    private Toolbar toolbar;

    public DonacionesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_donaciones, container, false);

        // Inflate the layout for this fragment
        lista = new Lista(this);
        recyclerView = v.findViewById(R.id.rvLista);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(lista);
        cargarLista();
        return v;
    }

    public void cargarLista(){
        List<Articulo> arti =new ArticuloDAO().listarxUsuario(DonadorActivity.user.getIdUsuario());
        lista.addProducto(new Articulo());
        for (Articulo articulo : arti){
            lista.addProducto(articulo);
        }
    }



    public int getIndex(){
        return this.index;
    }

    public void setIndex(int i){
        this.index = i;
    }

}
