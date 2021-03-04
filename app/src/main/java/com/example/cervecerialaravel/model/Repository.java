package com.example.cervecerialaravel.model;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.cervecerialaravel.FirstFragment;
import com.example.cervecerialaravel.R;
import com.example.cervecerialaravel.entity.Cerveza;
import com.example.cervecerialaravel.entity.Venta;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    MutableLiveData<List<Cerveza>> listMutableLiveDataCerveza = new MutableLiveData<>();
    MutableLiveData<List<Venta>> listMutableLiveDataVentas = new MutableLiveData<>();
    MutableLiveData<Long> idInsertBeer = new MutableLiveData<>();
    MutableLiveData<Cerveza> devolverBeer = new MutableLiveData<>();
    Cerveza cervezaConcreta;
    Cerveza savedBeer;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth firebaseAuth;


    public Repository() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void login(String user, String pass, View v){
        if(user.equalsIgnoreCase("") || pass.equalsIgnoreCase("")){
            Toast.makeText(v.getContext(), "Campos vacios", Toast.LENGTH_SHORT).show();
        }else{
            firebaseAuth.signInWithEmailAndPassword(user,pass)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        NavController navController = Navigation.findNavController(v);
                        navController.navigate(R.id.firstFragment);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(v.getContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
        }

    }

    public void Register(String user, String pass, View v){

        if(user.equalsIgnoreCase("") || pass.equalsIgnoreCase("")){
            Toast.makeText(v.getContext(), "Campos vacios", Toast.LENGTH_SHORT).show();
        }else{
            firebaseAuth.createUserWithEmailAndPassword(user,pass)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("zxc", e.getLocalizedMessage());
                        Toast.makeText(v.getContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
        }
    }

    public void getCervezaConcreta(long id){

        db.collection("user/"+firebaseAuth.getCurrentUser().getUid()+"/cervezas")
                .document(id+"")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            devolverBeer.postValue(task.getResult().toObject(Cerveza.class));
                        }
                    }
                });

    }       ///////////////////////////

    public MutableLiveData<Cerveza> getUrl() {
        return devolverBeer;
    }   //////////////??

    public void getAllVentas(){

        db.collection("user/"+firebaseAuth.getCurrentUser().getUid()+"/ventas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            listMutableLiveDataVentas.setValue(task.getResult().toObjects(Venta.class));
                        }else{
                            listMutableLiveDataVentas.setValue(null);
                        }
                    }
                });
    }       //////////////

    public void borrarVenta(long id){
        db.collection("user/"+firebaseAuth.getCurrentUser().getUid()+"/ventas")
                .document(id+"")
                .delete();
    }

    public void venderCerveza(long id){

    }   //////////////

    public void vender(long id){
       db.collection("user/"+firebaseAuth.getCurrentUser().getUid()+"/cervezas").document(id+"").get()
       .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
           @Override
           public void onComplete(@NonNull Task<DocumentSnapshot> task) {
               if(task.isSuccessful()){
                   Cerveza cerveza = task.getResult().toObject(Cerveza.class);
                   if(cerveza.getAmount()>0){
                       Venta venta = new Venta(cerveza.getId());
                       db.collection("user/"+firebaseAuth.getCurrentUser().getUid()+"/ventas")
                               .document(venta.getId()+"")
                                .set(venta);

                       cerveza.setAmount(cerveza.getAmount()-1);

                       updateCerveza(cerveza.getId(), cerveza);

                   }
               }
           }



       });

        //hay mas de 1?
        //inserta venta
        // si tiene exito resta una

    }       /////////////

    public Cerveza getSavedBeer() {
        return savedBeer;
    }   /////////////////

    public void setSavedBeer(Cerveza savedBeer) {
        this.savedBeer = savedBeer;
    }   ////////////////

    public void updateCerveza(long id, Cerveza cerveza){
        db.collection("user/"+firebaseAuth.getCurrentUser().getUid()+"/cervezas")
            .document(id+"")
            .set(cerveza, SetOptions.merge());

    }    ////////////////////////

    public void deleteCerveza(long id){
        db.collection("user/"+firebaseAuth.getCurrentUser().getUid()+"/cervezas").document(id+"").delete();

    }       /////////////////////////////////////////////

    public void getAllCervezas(){

        db.collection("user/"+firebaseAuth.getCurrentUser().getUid()+"/cervezas")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        listMutableLiveDataCerveza.setValue(task.getResult().toObjects(Cerveza.class));
                    }else{

                    }
                }
            });
    }           ///////////////////////////////////

    public void insertCerveza(Cerveza cerveza){
        db.collection("user/"+firebaseAuth.getCurrentUser().getUid()+"/cervezas")
                .document(cerveza.getId()+"")
                .set(cerveza)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.v("zxc", "INSERTAR BIEN");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("zxc", "INSERTAR ERROR");
                    }
                });
    }   ///////////////////////////////////////////////////

    public void existeBeer(long id, Cerveza cerveza){

        db.collection("user/"+firebaseAuth.getCurrentUser().getUid()+"/cervezas")
                .document(id+"")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(!task.getResult().exists()){
                            insertCerveza(cerveza);
                        }
                    }
                });
    }           //NO ENTIENDO

    public MutableLiveData<Long> getIdInsertBeer() {
        return idInsertBeer;
    }

    public void setIdInsertBeer(MutableLiveData<Long> idInsertBeer) {
        this.idInsertBeer = idInsertBeer;
    }

    public MutableLiveData<List<Venta>> getListMutableLiveDataVentas() {
        return listMutableLiveDataVentas;
    }

    public MutableLiveData<List<Cerveza>> getListMutableLiveDataCerveza() {
        return listMutableLiveDataCerveza;
    }

    public void setListMutableLiveDataCerveza(MutableLiveData<List<Cerveza>> listMutableLiveDataCerveza) {
        this.listMutableLiveDataCerveza = listMutableLiveDataCerveza;
    }


}
