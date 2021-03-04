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
import android.widget.EditText;

import com.example.cervecerialaravel.viewmodel.ViewModel;

public class RegistrarFragment extends Fragment {
    private EditText etNombre;
    private EditText etPass;
    private Button btRegistrarse;
    private NavController navController;
    private ViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registrar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        navController = Navigation.findNavController(view);
        etNombre = view.findViewById(R.id.etRegName);
        etPass = view.findViewById(R.id.etRegPass);
        btRegistrarse = view.findViewById(R.id.btConfirmarRegistro);


        btRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.Register(etNombre.getText().toString(), etPass.getText().toString(), view);
                navController.navigate(R.id.loginFragment);
            }
        });


    }
}