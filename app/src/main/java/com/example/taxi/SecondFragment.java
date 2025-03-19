package com.example.taxi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.taxi.databinding.FragmentSecondBinding;
import com.google.android.material.snackbar.Snackbar;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private static final int SET_PATH_REQUEST_CODE = 1;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String familia = bundle.getString("familia");
            String name = bundle.getString("name");
            String phone = bundle.getString("phone");

            TextView textView = binding.textviewSecond;
            String userInfo = "Фамилия: " + familia + "\nИмя: " + name + "\nТелефон: " + phone;
            textView.setText(userInfo);
        }

        binding.SetPath.setOnClickListener(v -> {
            Intent intent = new Intent("com.example.taxi.SET_PATH");
            startActivityForResult(intent, SET_PATH_REQUEST_CODE);
        });

        binding.callingTaxi.setOnClickListener(v -> {
            Snackbar.make(v, "Такси успешно вызвано!", Snackbar.LENGTH_LONG).show();
        });

        binding.buttonSecond.setOnClickListener(v ->
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment)
        );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SET_PATH_REQUEST_CODE && resultCode == getActivity().RESULT_OK) {
            if (data != null) {
                String startPoint = data.getStringExtra("startPoint");
                String endPoint = data.getStringExtra("endPoint");
                String distance = data.getStringExtra("distance");
                String time = data.getStringExtra("time");
                String cost = data.getStringExtra("cost");
                String comment = data.getStringExtra("comment");

                String routeInfo = "Маршрут:\n" +
                        "Начальная точка: " + startPoint + "\n" +
                        "Конечная точка: " + endPoint + "\n" +
                        "Расстояние: " + distance + " км\n" +
                        "Время: " + time + " мин\n" +
                        "Стоимость: " + cost + " руб\n" +
                        "Комментарий: " + comment;

                binding.emptyField.setText(routeInfo);
                binding.callingTaxi.setEnabled(true);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Lifecycle", "SecondFragment: onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Lifecycle", "SecondFragment: onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Lifecycle", "SecondFragment: onDestroy");
    }
}