package com.example.pinkcal.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pinkcal.Add_template;
import com.example.pinkcal.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

          binding = FragmentHomeBinding.inflate(inflater, container, false);
         View root = binding.getRoot();

        //  final TextView textView = binding.textHome;
        //  homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            //     @Override
            //      public void onChanged(@Nullable String s) {
                //         textView.setText(s);
                //      }
            //   });

        CardView add =(CardView) binding.addtemplate;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Add_template.class);
                startActivity(intent);
            }
        });

            return root;
         }

     @Override
     public void onDestroyView() {
             super.onDestroyView();
             binding = null;
          }
}