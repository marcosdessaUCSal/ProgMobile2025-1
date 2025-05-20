package com.paginas.fragments;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.paginas.R;

public class SchwarzeneggerFragment extends Fragment {

    public SchwarzeneggerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schwarzenegger, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn = view.findViewById(R.id.btn_swz);
        btn.setOnClickListener(v -> mostrarSnackbar(v));
    }

    private void mostrarSnackbar(View view) {
        // Criando um snackbar
        Snackbar sb =  Snackbar.make(view, "Obrigado por clicar no Arnold Schwarzenegger!", Snackbar.LENGTH_SHORT);
        View sbView = sb.getView();
        Drawable background = sbView.getBackground();
        background.setTint(Color.BLACK);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) sbView.getLayoutParams();
        params.setMargins(32, 0, 32, 180);
        sbView.setLayoutParams(params);

        // Quanto ao texto
        TextView tviewSnackBar = sbView.findViewById(com.google.android.material.R.id.snackbar_text);
        tviewSnackBar.setTextColor(Color.WHITE);
        tviewSnackBar.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        tviewSnackBar.setTextAlignment(android.view.View.TEXT_ALIGNMENT_CENTER);

        sb.show();
    }
}