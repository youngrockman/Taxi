package com.example.taxi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.taxi.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = getActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE);


        String savedFamilia = sharedPreferences.getString("familia", "");
        String savedName = sharedPreferences.getString("name", "");
        String savedPhone = sharedPreferences.getString("phone", "");

        if (!savedFamilia.isEmpty() && !savedName.isEmpty() && !savedPhone.isEmpty()) {
            binding.buttonFirst.setText("Log in");
        }

        binding.buttonFirst.setOnClickListener(v -> {
            String familia = binding.familiaField4.getText().toString();
            String name = binding.nameField4.getText().toString();
            String phone = binding.phoneField4.getText().toString();


            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("familia", familia);
            editor.putString("name", name);
            editor.putString("phone", phone);
            editor.apply();


            Bundle bundle = new Bundle();
            bundle.putString("familia", familia);
            bundle.putString("name", name);
            bundle.putString("phone", phone);

            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Lifecycle", "FirstFragment: onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Lifecycle", "FirstFragment: onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Lifecycle", "FirstFragment: onDestroy");
    }
}