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
import com.example.cervecerialaravel.view.RecyclerViewAdapter;
import com.example.cervecerialaravel.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {
    public RecyclerViewAdapter adapter;
    private static com.example.cervecerialaravel.viewmodel.ViewModel viewModel;
    private List<Cerveza> lista = new ArrayList<>();
    private Button insertarCerveza;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        insertarCerveza = view.findViewById(R.id.addBeer);

        navController = Navigation.findNavController(view);

        insertarCerveza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.insertFragment);
            }
        });


        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getAllCervezas();
        viewModel.getListMutableLiveDataCerveza().observe(getActivity(), new Observer<List<Cerveza>>() {
            @Override
            public void onChanged(List<Cerveza> cervezas) {

                lista = new ArrayList<>();
                lista.addAll(cervezas);
                initRecyclerView(view);

            }
        });



    }

    private void initRecyclerView(View v) {


        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter = new RecyclerViewAdapter(getActivity(), getActivity().getApplication());
        adapter.setMainList(lista);
        recyclerView.setAdapter(adapter);





    }








}