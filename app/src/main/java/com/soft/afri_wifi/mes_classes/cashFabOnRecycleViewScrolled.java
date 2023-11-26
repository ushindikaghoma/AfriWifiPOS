package com.soft.afri_wifi.mes_classes;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class cashFabOnRecycleViewScrolled {
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;

    public cashFabOnRecycleViewScrolled(RecyclerView recyclerView, final FloatingActionButton floatingActionButton) {
        this.recyclerView = recyclerView;
        this.floatingActionButton = floatingActionButton;


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && floatingActionButton.getVisibility() == View.VISIBLE) {
                    floatingActionButton.hide();
                } else if (dy < 0 && floatingActionButton.getVisibility() != View.VISIBLE) {
                    floatingActionButton.show();
                }
            }
        });
    }



}
