package com.example.cervecerialaravel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cervecerialaravel.entity.Cerveza;
import com.example.cervecerialaravel.entity.Venta;
import com.example.cervecerialaravel.view.RecyclerViewAdapter;
import com.example.cervecerialaravel.view.VentasAdapter;
import com.example.cervecerialaravel.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class VentasFragment extends Fragment {
    public VentasAdapter adapter;
    private Button btAtras;
    private ViewModel viewModel;
    private List<Venta> lista2 = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ventas, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btAtras = view.findViewById(R.id.btAtras);

        NavController navController = Navigation.findNavController(view);


        btAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.firstFragment);

            }
        });


        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getAllVentas();
        viewModel.getListMutableLiveDataVentas().observe(getActivity(), new Observer<List<Venta>>() {
            @Override
            public void onChanged(List<Venta> ventas) {

                lista2.clear();
                lista2.addAll(ventas);
                initRecyclerView(view);

            }
        });


    }



    private void initRecyclerView(View v) {


        RecyclerView recyclerView = v.findViewById(R.id.recyclerVentas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter = new VentasAdapter(getActivity(), getActivity().getApplication());
        adapter.setMainList(lista2);
        recyclerView.setAdapter(adapter);


    }
}