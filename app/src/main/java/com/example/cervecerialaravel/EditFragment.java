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

public class EditFragment extends Fragment {

    private TextView url;
    private TextView precio;
    private TextView tipo;
    private TextView cantidad;
    private TextView localizacion;
    private TextView marca;
    private Button atras;
    private Button guardar;
    private NavController navController;
    private ViewModel viewModel;
    private Cerveza cerveza;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        url = view.findViewById(R.id.etUrl3);
        precio = view.findViewById(R.id.etPrice3);
        tipo = view.findViewById(R.id.etType3);
        cantidad = view.findViewById(R.id.etAmount3);
        localizacion = view.findViewById(R.id.etLocation3);
        marca = view.findViewById(R.id.etMarca3);
        atras = view.findViewById(R.id.btBack3);
        guardar = view.findViewById(R.id.btSave3);

        navController = Navigation.findNavController(view);
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);

        cerveza = viewModel.getSavedBeer();

        url.setText(cerveza.getUrl());
        precio.setText(cerveza.getPrice()+"");
        tipo.setText(cerveza.getType());
        cantidad.setText(cerveza.getAmount()+"");
        localizacion.setText(cerveza.getLocation());
        marca.setText(cerveza.getBrand());


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cerveza cervezaUpdated = new Cerveza(0,
                        url.getText().toString(),
                        marca.getText().toString(),
                        Double.valueOf(precio.getText().toString()),
                        tipo.getText().toString(),
                        Integer.parseInt(cantidad.getText().toString()),
                        localizacion.getText().toString()
                );
                viewModel.updateCerveza(cerveza.getId(), cervezaUpdated);
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