package ads.com.mx.donacionesuabc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.List;

import ads.com.mx.donacionesuabc.R;
import ads.com.mx.donacionesuabc.ListaReceptor;
import ads.com.mx.donacionesuabc.dao.ArticuloDAO;
import ads.com.mx.donacionesuabc.entidades.Articulo;

/**
 * A simple {@link Fragment} subclass.
 */
public class InicioReceptor extends Fragment {

    private int index;
    private ListaReceptor lista;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;

    private int resultado;
    private Toolbar toolbar;

    public InicioReceptor() {
        // Required empty public constructor
    }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        View v = inflater.inflate(R.layout.fragment_inicio_receptor, container, false);

            // Inflate the layout for this fragment
            lista = new ListaReceptor(this);
            recyclerView = v.findViewById(R.id.rvLista);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(lista);
            cargarLista();
            return v;
        }

    public void cargarLista(){
        List<Articulo> arti =new ArticuloDAO().ListarArticulos();
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


