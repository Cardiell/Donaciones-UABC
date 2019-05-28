package ads.com.mx.donacionesuabc;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ads.com.mx.donacionesuabc.activities.VerDonacionesActivity;
import ads.com.mx.donacionesuabc.dao.UsuarioDAO;
import ads.com.mx.donacionesuabc.entidades.Articulo;
import ads.com.mx.donacionesuabc.entidades.Persona;
import ads.com.mx.donacionesuabc.fragments.DonacionesFragment;


public class Lista extends RecyclerView.Adapter<Lista.MyViewHolder>{
    int cont=0;
    public static int IDARTICULO;
    private ArrayList<Articulo> articulos = new ArrayList<Articulo>();
    private DonacionesFragment donacion=null;
    public static ArrayList<Persona> persona;
    private ArrayList<ArrayList<Persona>> temp =new ArrayList<>();
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
                    if (!(temp.get(vh.getLayoutPosition() - 1).isEmpty())) {
                        Intent intent = new Intent(donacion.getActivity(), VerDonacionesActivity.class);
                        persona = temp.get(vh.getLayoutPosition()-1);
                        IDARTICULO = articulos.get(vh.getLayoutPosition()).getIdProducto();
                        donacion.startActivity(intent);
                    } else {
                        Toast.makeText(donacion.getContext(), "No hay solicitudes por el momento...", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i){
        if(cont!=0) {
            viewHolder.txtNombreArt.setText(articulos.get(i).getNombre());
            temp.add(new UsuarioDAO().dameSolicitudes(articulos.get(i).getIdProducto()));
            System.out.println("Imprimiendo "+temp.size());
            viewHolder.btnCantidad.setText(String.valueOf(temp.get(i-1).size()));
        }else
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
