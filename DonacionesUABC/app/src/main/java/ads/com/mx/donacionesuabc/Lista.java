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

import ads.com.mx.donacionesuabc.activities.VerDonacionesActivity;
import ads.com.mx.donacionesuabc.entidades.Articulo;
import ads.com.mx.donacionesuabc.fragments.DonacionesFragment;
import ads.com.mx.donacionesuabc.fragments.InicioReceptor;



public class Lista extends RecyclerView.Adapter<Lista.MyViewHolder>{
    int cont=0;
    private ArrayList<Articulo> articulos = new ArrayList<Articulo>();
    private DonacionesFragment donacion=null;
    CustomItemClickListener listener;

    public Lista(DonacionesFragment u){
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

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View itemView;
        final MyViewHolder vh;
    if(cont==0) {
        itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header_text_view, viewGroup, false);
        vh = new MyViewHolder(itemView);
    }
    else {
        itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_text_view, viewGroup, false);

        vh = new MyViewHolder(itemView);
        vh.btnCantidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(donacion.getContext(), VerDonacionesActivity.class);
                    donacion.setIndex(vh.getLayoutPosition());
                    intent.putExtra("ART", articulos.get(vh.getLayoutPosition()));
                    donacion.startActivityForResult(intent, 2);
            }
        });
    }

        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i){
        System.out.println("cont: "+cont);
        if(cont!=0) {
            viewHolder.txtNombreArt.setText(articulos.get(i).getNombre());
            viewHolder.btnCantidad.setText(String.valueOf(articulos.get(i).getCantidad()));
        }
        cont++;


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
