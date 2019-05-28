package ads.com.mx.donacionesuabc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ads.com.mx.donacionesuabc.activities.RecibosActivity;
import ads.com.mx.donacionesuabc.dao.ArticuloDAO;
import ads.com.mx.donacionesuabc.entidades.Articulo;
import ads.com.mx.donacionesuabc.fragments.RecibirFragment;

public class ListaRecibir extends RecyclerView.Adapter<ListaRecibir.MyViewHolder>{

    int cont=0;
    private RecibirFragment donacion=null;
    public static ArrayList<Articulo> arrayList;
    private ArrayList<ArrayList<Articulo>> temp =new ArrayList<>();
    CustomItemClickListener listener;
    private List<Articulo> articulos;

    public ListaRecibir(RecibirFragment u , List<Articulo> lista) {
        this.donacion = u;
        this.articulos = lista;
        arrayList = new ArrayList<>();
        arrayList.addAll(lista);
    }

    public ListaRecibir(RecibirFragment u){
        donacion = u;
    }

    @Override
    public int getItemCount() {
        return articulos.size();
    }

    public Articulo getArticulo(int i){
        return articulos.get(i);
    }

    public void addProducto(Articulo articulo){
        articulos.add(articulo);
        notifyDataSetChanged();
    }

    public void removeProducto(int index){
        articulos.remove(index);
        notifyDataSetChanged();
    }

    public void modify(int index, Articulo articulo){
        articulos.set(index, articulo);
        notifyDataSetChanged();
    }

    public void filter(String text){
        text = text.toLowerCase(Locale.getDefault());
        articulos.clear();
        if(text.length()==0)
            articulos.addAll(arrayList);
        else{
            for(Articulo arti:arrayList){
                if(arti.getNombre().toLowerCase(Locale.getDefault()).contains(text))
                    articulos.add(arti);
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View itemView;
        final MyViewHolder vh;
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_text_view_recibir, viewGroup, false);
            vh = new MyViewHolder(itemView);
            vh.btnCantidad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(donacion.getActivity(), RecibosActivity.class);
                        donacion.startActivity(intent);
                    }
            });

        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i){
        System.out.println(articulos.get(i).getIdProducto());
            Articulo te = new ArticuloDAO().listarArticuloxId(articulos.get(i).getIdProducto());
            viewHolder.txtNombreArt.setText(te.getNombre());
            //temp.add(new UsuarioDAO().dameSolicitudes(articulos.get(i).getIdProducto()));
            System.out.println("Imprimiendo "+te.getNombre());
            if(articulos.get(i).isAceptar())
                viewHolder.btnCantidad.setBackgroundResource(R.drawable.paloma);
            else
                viewHolder.btnCantidad.setBackgroundResource(R.drawable.nopaloma);
//            viewHolder.btnCantidad.setText(String.valueOf(temp.get(i-1).size()));



    }
    // Return the size of your dataset (invoked by the layout manager)

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtNombreArt;
        Button btnCantidad;
        public MyViewHolder(final View itemView){
            super(itemView);
            txtNombreArt = itemView.findViewById(R.id.txtNombreArt);
            btnCantidad = itemView.findViewById(R.id.btnCantidad);
        }
    }
}
