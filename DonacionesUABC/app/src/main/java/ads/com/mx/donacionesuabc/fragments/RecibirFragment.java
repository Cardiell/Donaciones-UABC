package ads.com.mx.donacionesuabc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.List;

import ads.com.mx.donacionesuabc.ListaRecibir;
import ads.com.mx.donacionesuabc.R;
import ads.com.mx.donacionesuabc.activities.ReceptorActivity;
import ads.com.mx.donacionesuabc.dao.ArticuloDAO;
import ads.com.mx.donacionesuabc.entidades.Articulo;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecibirFragment extends Fragment {

    private int index;
    private ListaRecibir lista;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    public RecibirFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);




        View v = inflater.inflate(R.layout.fragment_inicio_receptor, container, false); //Cambiar a su respectivo fragmento

        // Inflate the layout for this fragment
        lista = new ListaRecibir(this,new ArticuloDAO().listarDetalleDonacion(ReceptorActivity.usuario.getIdUsuario()));
        recyclerView = v.findViewById(R.id.rvLista);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(lista);
        setHasOptionsMenu(true);
        return v;
    }



    public int getIndex(){
        return this.index;
    }

    public void setIndex(int i){
        this.index = i;
    }

}


