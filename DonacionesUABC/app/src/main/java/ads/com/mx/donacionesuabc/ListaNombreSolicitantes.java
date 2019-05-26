package ads.com.mx.donacionesuabc;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import ads.com.mx.donacionesuabc.activities.VerDonacionesActivity;
import ads.com.mx.donacionesuabc.entidades.Persona;

public class ListaNombreSolicitantes extends RecyclerView.Adapter<ListaNombreSolicitantes.MyViewHolder>{
        int cont=0;
        private ArrayList<Persona> persona = new ArrayList<Persona>();
        private VerDonacionesActivity donacion=null;
        CustomItemClickListener listener;

        public ListaNombreSolicitantes(VerDonacionesActivity u){
            donacion = u;
        }

        @Override
        public int getItemCount() {
            return persona.size();
        }

        public Persona getArticulo(int i){
            return persona.get(i);
        }

        public void addPersona(Persona person){
            persona.add(person);
            notifyDataSetChanged();
        }

        public void removePersona(int index){
            persona.remove(index);
            notifyDataSetChanged();
        }

        public void modify(int index, Persona perso){
            persona.set(index, perso);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
            View itemView;
            final MyViewHolder vh;
            if(cont==0) {
                itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header_text_view_solicitud, viewGroup, false);
                vh = new MyViewHolder(itemView);
            }else {
                 itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_text_view_solicitud, viewGroup, false);
                 vh = new MyViewHolder(itemView);

               /* vh.btnCantidad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(donacion.getContext(), VerDonacionesActivity.class);
                        donacion.setIndex(vh.getLayoutPosition());
                        intent.putExtra("ART", persona.get(vh.getLayoutPosition()));
                        donacion.startActivityForResult(intent, 2);
                    }
                });*/
            }

            return vh;
        }


        @Override
        public void onBindViewHolder(MyViewHolder viewHolder, int i){
            if(cont!=0)
                viewHolder.txtNombreArt.setText(persona.get(i).getNombre() +" " + persona.get(i).getApellidoP());
            else
                cont++;
        }
        // Return the size of your dataset (invoked by the layout manager)

     class MyViewHolder extends RecyclerView.ViewHolder {
            TextView txtNombreArt;
            RadioButton btnAceptar;

            public MyViewHolder(final View itemView) {
                super(itemView);
                txtNombreArt = itemView.findViewById(R.id.txtNombreArt);
                btnAceptar = itemView.findViewById(R.id.btnAceptar);
            }
        }
}