package ads.com.mx.donacionesuabc;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ads.com.mx.donacionesuabc.activities.ReceptorActivity;
import ads.com.mx.donacionesuabc.dao.ArticuloDAO;
import ads.com.mx.donacionesuabc.entidades.Articulo;
import ads.com.mx.donacionesuabc.fragments.DonacionesFragment;
import ads.com.mx.donacionesuabc.fragments.InicioReceptor;



interface CustomItemClickListener{
    public void onItemClick(View v, int position);
}

public class ListaReceptor extends RecyclerView.Adapter<ListaReceptor.MyViewHolder>{

    public static List<Articulo> articulos;
   private static ArrayList<Articulo> arrayList;
    private InicioReceptor receptor =null;

    public ListaReceptor(InicioReceptor u,List<Articulo> temp){
        receptor = u;
        this.articulos = temp;
        this.arrayList = new ArrayList<>();
        this.arrayList.addAll(temp);
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
        System.out.println("Tamano:" +articulos.size());
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
                    int color = Color.parseColor("#AE6118");
                    vh.btnDeseo.setBackgroundColor(color);
                    Toast.makeText(receptor.getActivity(),"Solicitud enviada :)",Toast.LENGTH_SHORT).show();
                    new ArticuloDAO().agregarSolicitud(ReceptorActivity.usuario.getIdUsuario()); //idUsuario
                    new ArticuloDAO().agregarDetallesDonacion(articulos.get(vh.getLayoutPosition()).getIdProducto(),new ArticuloDAO().getLastSolicitud());

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
