package ads.com.mx.donacionesuabc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ads.com.mx.donacionesuabc.activities.VerDonacionesActivity;
import ads.com.mx.donacionesuabc.entidades.Articulo;
import ads.com.mx.donacionesuabc.fragments.DonacionesFragment;
import ads.com.mx.donacionesuabc.fragments.InicioReceptor;



interface CustomItemClickListener{
    public void onItemClick(View v, int position);
}

public class ListaReceptor extends RecyclerView.Adapter<ListaReceptor.MyViewHolder>{

    private ArrayList<Articulo> articulos = new ArrayList<Articulo>();
    private DonacionesFragment donacion=null;
    private InicioReceptor receptor =null;

    public ListaReceptor(InicioReceptor u){receptor = u;}

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
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_text_view_receptor, viewGroup, false);

            vh = new MyViewHolder(itemView);
            vh.btnDeseo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(receptor.getContext(), VerDonacionesActivity.class);
                        receptor.setIndex(vh.getLayoutPosition());
                        intent.putExtra("ART", articulos.get(vh.getLayoutPosition()));
                        receptor.startActivityForResult(intent, 2);

                }
            });


        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i){
            viewHolder.nombreArticulo.setText(articulos.get(i).getNombre());
            viewHolder.nombreFacultad.setText("Facultad: "+articulos.get(i).getFacultad());
            viewHolder.numeroArticulos.setText("Articulos No: "+String.valueOf(articulos.get(i).getCantidad()));
            viewHolder.nombreLugar.setText("Lugar: "+articulos.get(i).getLugar());
            viewHolder.nombreDia.setText("Dia: "+articulos.get(i).getDia());
            viewHolder.nombreDescripcion.setText("Descripcion: "+articulos.get(i).getDescripcion());
            viewHolder.imgArticulo.setImageBitmap(Utils.getImage(articulos.get(i).getImagen()));
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nombreArticulo;
        TextView nombreFacultad;
        TextView numeroArticulos;
        TextView nombreLugar;
        TextView nombreDia;
        TextView nombreDescripcion;
        ImageView imgArticulo;
        Button btnDeseo;
        public MyViewHolder(final View itemView){
            super(itemView);
            nombreArticulo = itemView.findViewById(R.id.nombreArticulo);
            nombreFacultad = itemView.findViewById(R.id.nombreFacultad);
            numeroArticulos = itemView.findViewById(R.id.numeroArticulos);
            nombreLugar = itemView.findViewById(R.id.nombreLugar);
            nombreDia = itemView.findViewById(R.id.nombreDia);
            nombreDescripcion = itemView.findViewById(R.id.nombreDescripcion);
            btnDeseo = itemView.findViewById(R.id.btnDeseo);
            imgArticulo =itemView.findViewById(R.id.imgArticulo);
        }
    }
}
