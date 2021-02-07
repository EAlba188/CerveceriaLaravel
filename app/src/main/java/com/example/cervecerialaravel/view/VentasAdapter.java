package com.example.cervecerialaravel.view;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cervecerialaravel.R;
import com.example.cervecerialaravel.entity.Cerveza;
import com.example.cervecerialaravel.entity.Venta;
import com.example.cervecerialaravel.viewmodel.ViewModel;

import java.util.List;

public class VentasAdapter extends RecyclerView.Adapter<VentasAdapter.MyViewHolder> {


    private List<Venta> lista;
    private Context context;
    private Application application;



    public VentasAdapter(Context context, Application application){
        this.context=context;
        this.application = application;


    }

    public void setMainList(List<Venta> lista){
        this.lista = lista;
    }

    public List getList(){
        return lista;
    }


    @NonNull
    @Override
    public VentasAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowventas, parent, false);
        return new VentasAdapter.MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull VentasAdapter.MyViewHolder holder, int position) {
        holder.idVenta.setText("Id Venta: "+lista.get(position).getId());
        holder.idCerveza.setText("Id Cerveza: "+lista.get(position).getIdCerveza());

        ViewModel vm = new ViewModelProvider((ViewModelStoreOwner) context).get(ViewModel.class);
        vm.getCervezaConcreta(lista.get(position).getIdCerveza());



        vm.getUrl().observe((LifecycleOwner) context, new Observer<Cerveza>() {
            @Override
            public void onChanged(Cerveza cerveza) {
                if(cerveza.getId()==lista.get(position).getIdCerveza()){
                    Glide.with(application)
                            .load(cerveza.getUrl())
                            .into(holder.pic);
                }
            }
        });




        holder.lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("¿Qué deseas hacer?");
                ViewModel vm = new ViewModelProvider((ViewModelStoreOwner) context).get(ViewModel.class);
                NavController navController = Navigation.findNavController(v);


                builder.setNeutralButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        vm.borrarVenta(lista.get(position).getId());
                        navController.navigate(R.id.ventasFragment);
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alert = builder.create();
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView idVenta;
        TextView idCerveza;
        ImageView pic;
        ConstraintLayout lout;

        public MyViewHolder(View view) {
            super(view);
            idVenta = view.findViewById(R.id.tvIdVenta);
            idCerveza = view.findViewById(R.id.tvIdCerveza);
            pic = view.findViewById(R.id.ivVentas);
            lout = view.findViewById(R.id.ventasLayoutID);
        }

    }

}
