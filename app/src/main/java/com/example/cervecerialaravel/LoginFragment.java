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
import android.widget.TextView;

import com.example.cervecerialaravel.viewmodel.ViewModel;

public class LoginFragment extends Fragment {
    private EditText etNombre;
    private EditText etPass;
    private Button btLogin;
    private Button btRegistrarse;
    private NavController navController;
    private ViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        navController = Navigation.findNavController(view);
        btLogin = view.findViewById(R.id.btLogin);
        etNombre = view.findViewById(R.id.etName);
        etPass = view.findViewById(R.id.etPass);
        btRegistrarse = view.findViewById(R.id.btRegistrarse);

        btRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.registrarFragment);
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.login(etNombre.getText().toString(),etPass.getText().toString(), getView());

            }
        });








    }
}