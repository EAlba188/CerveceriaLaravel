package com.example.cervecerialaravel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cervecerialaravel.entity.Cerveza;
import com.example.cervecerialaravel.viewmodel.ViewModel;

public class InsertFragment extends Fragment {
    private TextView url;
    private TextView precio;
    private TextView tipo;
    private TextView cantidad;
    private TextView localizacion;
    private TextView marca;
    private TextView ID;
    private Button atras;
    private Button guardar;
    private NavController navController;
    private ViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_insert, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ID = view.findViewById(R.id.etID);
        url = view.findViewById(R.id.etUrl);
        precio = view.findViewById(R.id.etPrice);
        tipo = view.findViewById(R.id.etType);
        cantidad = view.findViewById(R.id.etAmount);
        localizacion = view.findViewById(R.id.etLocation);
        marca = view.findViewById(R.id.etMarca);
        atras = view.findViewById(R.id.btBack);
        guardar = view.findViewById(R.id.btSave);

        navController = Navigation.findNavController(view);
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cerveza cerveza = new Cerveza(Integer.parseInt(ID.getText().toString()),
                        url.getText().toString(),
                        marca.getText().toString(),
                        Double.valueOf(precio.getText().toString()),
                        tipo.getText().toString(),
                        Integer.parseInt(cantidad.getText().toString()),
                        localizacion.getText().toString()
                        );
                viewModel.existeBeer(Integer.parseInt(ID.getText().toString()),cerveza);
                navController.navigate(R.id.firstFragment);
            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.firstFragment);
            }
        });


    }
}