package com.example.lab203_28.healthy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lab203_28.healthy.Weight.WeightFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


public class MenuFragment extends Fragment{
    ArrayList<String> _menu = new ArrayList<>();
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _menu.add("BMI");
        _menu.add("Weight");
        _menu.add("Logout");


        final ArrayAdapter<String> _menuAdapter = new ArrayAdapter<>(
         getActivity(),
         android.R.layout.simple_list_item_1,
         _menu
        );
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser muUser = mAuth.getCurrentUser();
        Log.d("MENU", muUser.getEmail());
        ListView _menuList = (ListView) getView().findViewById(R.id.menu_list);
        _menuList.setAdapter(_menuAdapter);
        _menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Log.d("text" , "Click on  " + _menu.get(i));

                _menuAdapter.notifyDataSetChanged();
                if(i == 0){

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new BMIFragment()).addToBackStack(null).commit();

                }
                else if(i == 1){

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new WeightFragment()).addToBackStack(null).commit();
                }
                else if(i == 2){
                    mAuth.signOut();
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new LoginFragment()).addToBackStack(null).commit();
                }
            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }
}
