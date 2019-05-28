package ads.com.mx.donacionesuabc;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import ads.com.mx.donacionesuabc.activities.VerDonacionesActivity;
import ads.com.mx.donacionesuabc.dao.UsuarioDAO;
import ads.com.mx.donacionesuabc.entidades.Articulo;
import ads.com.mx.donacionesuabc.entidades.Persona;
import ads.com.mx.donacionesuabc.fragments.DonacionesFragment;


public class Lista extends RecyclerView.Adapter<Lista.MyViewHolder>{
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


    public void addProducto(Articulo articulo){
        articulos.add(articulo);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View itemView;
        final MyViewHolder vh;
        itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_text_view, viewGroup, false);

        vh = new MyViewHolder(itemView);
        vh.btnCantidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (!(temp.get(vh.getLayoutPosition()).isEmpty())) {
                        Intent intent = new Intent(donacion.getActivity(), VerDonacionesActivity.class);
                        persona = temp.get(vh.getLayoutPosition());
                        IDARTICULO = articulos.get(vh.getLayoutPosition()).getIdProducto();
                        donacion.startActivity(intent);
                    } else {
                        Toast.makeText(donacion.getContext(), "No hay solicitudes por el momento...", Toast.LENGTH_SHORT).show();
                }
            }

        });

        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i){
            viewHolder.txtNombreArt.setText(articulos.get(i).getNombre());
            temp.add(new UsuarioDAO().dameSolicitudes(articulos.get(i).getIdProducto()));
            viewHolder.btnCantidad.setText(String.valueOf(temp.get(i).size()));

    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtNombreArt;
        Button btnCantidad;
        public MyViewHolder(final View itemView){
            super(itemView);
            txtNombreArt = itemView.findViewById(R.id.txtNom);
            btnCantidad = itemView.findViewById(R.id.btnCantidad);
        }
    }
}
