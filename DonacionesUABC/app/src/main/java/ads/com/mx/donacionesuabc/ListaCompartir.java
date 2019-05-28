package ads.com.mx.donacionesuabc;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ads.com.mx.donacionesuabc.entidades.Articulo;
import ads.com.mx.donacionesuabc.fragments.CompartirFragment;

public class ListaCompartir extends RecyclerView.Adapter<ListaCompartir.MyViewHolder>{

    private List<Articulo> articulos;
    private CompartirFragment compartir=null;
    private static ArrayList<Articulo> arrayList;
    CustomItemClickListener listener;

    public ListaCompartir(CompartirFragment u){
        compartir = u;
    }

    public ListaCompartir(CompartirFragment u , List<Articulo> lista) {
        this.compartir = u;
        this.articulos = lista;
        arrayList = new ArrayList<>();
        arrayList.addAll(lista);
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

    public void modify(int index, Articulo arti){
        articulos.set(index, arti);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View itemView;
        final MyViewHolder vh;
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_text_view_compartir, viewGroup, false);
            vh = new MyViewHolder(itemView);
        return vh;
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


    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i){
        viewHolder.nombreArticulo.setText("Nombre: "+articulos.get(i).getNombre());
        viewHolder.numeroArticulos.setText("Cant. No: "+ articulos.get(i).getCantidad());
        viewHolder.nombreLugar.setText("Lugar de entrega: "+articulos.get(i).getLugar());
        viewHolder.nombreDia.setText("Dia de entrega: "+articulos.get(i).getDia());
        viewHolder.horaArticulo.setText("Hora de entrega: "+articulos.get(i).getHora());
        viewHolder.imgArticulo.setImageBitmap(Utils.getImage(articulos.get(i).getImagen()));
    }
    // Return the size of your dataset (invoked by the layout manager)

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombreArticulo;
        TextView numeroArticulos;
        TextView nombreLugar;
        TextView nombreDia;
        TextView horaArticulo;
        ImageView imgArticulo;
        public MyViewHolder(final View itemView) {
            super(itemView);
            nombreArticulo = itemView.findViewById(R.id.nombreArticulo);
            numeroArticulos = itemView.findViewById(R.id.numeroArticulos);
            nombreLugar = itemView.findViewById(R.id.nombreLugar);
            nombreDia = itemView.findViewById(R.id.nombreDia);
            horaArticulo = itemView.findViewById(R.id.horaEntrega);
            imgArticulo =itemView.findViewById(R.id.imgArticulo);
        }
    }
}