package ads.com.mx.donacionesuabc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.List;

import ads.com.mx.donacionesuabc.R;
import ads.com.mx.donacionesuabc.ListaReceptor;
import ads.com.mx.donacionesuabc.activities.ReceptorActivity;
import ads.com.mx.donacionesuabc.dao.ArticuloDAO;
import ads.com.mx.donacionesuabc.entidades.Articulo;

/**
 * A simple {@link Fragment} subclass.
 */
public class InicioReceptor extends Fragment implements MenuItem.OnActionExpandListener {

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
            System.out.println("InicioRec"+ReceptorActivity.usuario.getCorreo());
            lista = new ListaReceptor(this,new ArticuloDAO().ListarArticulos(ReceptorActivity.usuario.getIdUsuario()));
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

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return false;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
       final MenuItem searchItem = menu.findItem(R.id.buscarArticulo);
       final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String s) {
               return false;
           }

           @Override
           public boolean onQueryTextChange(String s) {
                if(TextUtils.isEmpty(s))
                    lista.filter("");
                else
                    lista.filter(s);

               return true;
           }
       });
       super.onCreateOptionsMenu(menu,inflater);
    }
}


