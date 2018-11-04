package com.example.internship.mypersonalcamera;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class AdapterViewHolder extends RecyclerView.ViewHolder {
    public AdapterViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void onBind(int position) {
    }
}
