package ads.com.mx.donacionesuabc;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AlertDialogLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ads.com.mx.donacionesuabc.activities.DonadorActivity;
import ads.com.mx.donacionesuabc.activities.ReceptorActivity;
import ads.com.mx.donacionesuabc.activities.VerDonacionesActivity;
import ads.com.mx.donacionesuabc.dao.ArticuloDAO;
import ads.com.mx.donacionesuabc.entidades.Persona;

import static ads.com.mx.donacionesuabc.Lista.IDARTICULO;


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

        public void showAlertDialog(View v, Context c){
            AlertDialog.Builder alert = new AlertDialog.Builder(c);
            alert.setTitle("Android Studio");
            alert.setMessage("Seguro que quieres donar a este usuario?");
            alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Evento
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Salir
                }
            });
        }

    public static void alert_msg(Context context, String title, String message,final MyViewHolder vh) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);

        alertDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                vh.rdbtn.setEnabled(false);
            }
        });
        // Set OK Buttonv
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                vh.rdbtn.setChecked(false);
            }
        });

        // Show Alert Message
        alertDialog.show();
    }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
            final View itemView;
            final MyViewHolder vh;

            //CODIGO NECESARIO PARA HACER FUNCIONAR RADIO BUTTON

            if(cont==0) {
                itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header_text_view_solicitud, viewGroup, false);
                vh = new MyViewHolder(itemView);
            }else {
                 itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_text_view_solicitud, viewGroup, false);
                 vh = new MyViewHolder(itemView);
                vh.btnGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        String mensaje = "Se envio la solicitud a \n\t\t"+persona.get(vh.getLayoutPosition()).getNombre();
                        new ArticuloDAO().updateDetallesDonacion(IDARTICULO,persona.get(vh.getLayoutPosition()).getIdPersona(),true);
                        //alert_msg(donacion,"DonApps",mensaje,vh);
                        Toast.makeText(donacion,mensaje, Toast.LENGTH_SHORT).show();
                    }
                });
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
            RadioGroup btnGroup;
            RadioButton rdbtn;

            public MyViewHolder(final View itemView) {
                super(itemView);
                txtNombreArt = itemView.findViewById(R.id.txtNombreArt);
                btnGroup = itemView.findViewById(R.id.btnGroup);
                rdbtn = itemView.findViewById(R.id.btnSolicitud);
            }
        }
}