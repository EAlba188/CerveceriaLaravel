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

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cervecerialaravel.R;
import com.example.cervecerialaravel.entity.Cerveza;
import com.example.cervecerialaravel.viewmodel.ViewModel;

import java.util.List;

public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private List<Cerveza> lista;
    private Context context;
    private Application application;
    private ViewModel vm;



    public RecyclerViewAdapter(Context context, Application application){
        this.context=context;
        this.application = application;


    }

    public void setMainList(List<Cerveza> lista){
        this.lista = lista;
    }

    public List getList(){
        return lista;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new RecyclerViewAdapter.MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {


        holder.brand.setText(lista.get(position).getBrand());
        holder.price.setText(lista.get(position).getPrice()+"");
        holder.type.setText(lista.get(position).getType());
        holder.amount.setText(lista.get(position).getAmount()+"");
        holder.location.setText(lista.get(position).getLocation());

        //Log.v("asd", lista.get(position).toString());

        Glide.with(application)
                .load(lista.get(position).getUrl())
                .into(holder.pic);

        holder.lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("¿Qué deseas hacer?");
                ViewModel vm = new ViewModelProvider((ViewModelStoreOwner) context).get(ViewModel.class);
                NavController navController = Navigation.findNavController(v);

                builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        vm.setSavedBeer(lista.get(position));
                        navController.navigate(R.id.editFragment);
                    }
                });
                builder.setNeutralButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        vm.deleteCerveza(lista.get(position).getId());
                    }
                });

                builder.setNegativeButton("Cancelar",null);

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
        TextView brand;
        TextView price;
        TextView amount;
        TextView type;
        TextView location;
        ImageView pic;
        ConstraintLayout lout;

        public MyViewHolder(View view) {
            super(view);
            amount = view.findViewById(R.id.tvAmount);
            price = view.findViewById(R.id.tvPrice);
            brand = view.findViewById(R.id.tvBrand);
            type = view.findViewById(R.id.tvType);
            location = view.findViewById(R.id.tvLocation);
            pic = view.findViewById(R.id.ivPic);
            lout = view.findViewById(R.id.rowId);
        }

    }
}
