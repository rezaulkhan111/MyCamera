package com.example.internship.mypersonalcamera;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.internship.mypersonalcamera.model.Above;

import java.util.ArrayList;

import static com.example.internship.mypersonalcamera.R.layout.recycler_view_page;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder> {

    public ArrayList<Above> aboveArrayList;

    public AdapterClass(ArrayList<Above> aboveArrayList) {
        this.aboveArrayList = aboveArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_page, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder Holder, int position) {
        try {
            Holder.onBind(position);
        } catch (Exception ex) {

        }
    }

    @Override
    public int getItemCount() {
        return aboveArrayList.size();
    }

    public class ViewHolder extends AdapterViewHolder {

//        final ImageView imageView;

//        final TextView _sat_Id;
//        final TextView _sat_Name;
//        final TextView _ini_Designator;
//        final TextView _launch_Date;
//        final TextView _sat_lat;
//        final TextView _sat_Lng;
//        final TextView _sat_alt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            imageView = itemView.findViewById(R.id.iv_photo);
//            _sat_Id = itemView.findViewById(R.id.tv_sat_id);
//            _sat_Name = itemView.findViewById(R.id.tv_sat_name);
//            _ini_Designator = itemView.findViewById(R.id.tv_ini_designator);
//            _launch_Date = itemView.findViewById(R.id.tv_launch_date);
//            _sat_lat = itemView.findViewById(R.id.tv_sat_lat);
//            _sat_Lng = itemView.findViewById(R.id.tv_sat_lng);
//            _sat_alt = itemView.findViewById(R.id.tv_sat_alt);
        }

        public void onBind(int position) {
            try {
                final Above above = aboveArrayList.get(position);

                //  imageView.setAnimation(R.id.iv_animation);
                //  imageView.an
//                _sat_Id.setText(String.valueOf(above.getSatid()));
//                _sat_Name.setText(String.valueOf(above.getSatname()));
//                _ini_Designator.setText(String.valueOf(above.getIntDesignator()));
//                _launch_Date.setText(String.valueOf(above.getLaunchDate()));
//                _sat_lat.setText(String.valueOf(above.getSatlat()));
//                _sat_Lng.setText(String.valueOf(above.getSatlng()));
//                _sat_alt.setText(String.valueOf(above.getSatlat()));

            } catch (Exception e) {
            }
        }
    }
}
