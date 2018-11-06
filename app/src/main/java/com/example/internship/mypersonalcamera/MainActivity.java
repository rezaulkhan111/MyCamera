package com.example.internship.mypersonalcamera;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Path;
import android.hardware.camera2.CameraManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.animation.DynamicAnimation;
import android.support.animation.FlingAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.internship.mypersonalcamera.model.Above;
import com.example.internship.mypersonalcamera.model.Response;
import com.example.internship.mypersonalcamera.model.RetrofitClientInstance;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.jar.Attributes;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends Activity {
    String authorization_Key = "&apiKey=53VELF-92F346-8HGD75-3IPC";
    double observer_lat;
    double observer_lng;
    double observer_alt = 0;
    int search_radius90 = 90;
    int category_id = 15;
    private AdapterClass adapter;
    ArrayList<Above> aboveList = new ArrayList<>();
//    RecyclerView recyclerView;

    ObjectAnimator moveX;
    ObjectAnimator moveY;

    //    android.hardware.Camera camera;
//    FrameLayout frameLayout;
//    ShowCamera showCamera;
    ImageView imageView;

    Handler handler = new Handler();
    Button button3, button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button3 = findViewById(R.id.button3);
//        frameLayout = findViewById(R.id.frame_layout_camera);
        imageView = findViewById(R.id.iv_photo);
//
//        camera = android.hardware.Camera.open();
//        showCamera = new ShowCamera(this, camera);
//        frameLayout.addView(showCamera);

//        recyclerView = findViewById(R.id._rv_id);
//        recyclerView.setHasFixedSize(true);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSON_Response_Method();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                @SuppressLint("MissingPermission") Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                observer_lat = location.getLatitude();
                observer_lng = location.getLongitude();
            }
        });
    }
//    private void StartA() {
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation);
//        textView.startAnimation(animation);
//    }

    public void JSON_Response_Method() {
        final GetDataService date_service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Response> call = date_service.PostFromURLData(observer_lat, observer_lng, observer_alt, search_radius90, category_id, authorization_Key);
        call.enqueue(new Callback<Response>() {
            @SuppressLint("NewApi")
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        String C = response.body().getInfo().getCategory();
                        String T = String.valueOf(response.body().getInfo().getTransactionscount());
                        String S = String.valueOf(response.body().getInfo().getSatcount());

                        Toast.makeText(getApplicationContext(), C + "--" + T + "--" + S, Toast.LENGTH_SHORT).show();
                        if (response.body() != null) {
                            for (Above above : response.body().getAbove()) {
                                above.getSatid();
                                above.getSatname();
                                above.getIntDesignator();
                                above.getLaunchDate();
                                above.getSatlat();
                                above.getSatlng();
                                above.getSatalt();
                                Animation_Movement(above.getSatlat(), above.getSatlng());
                            }
                            // Get_Adapter_List(aboveList);
//                            for (int i = 0; i < response.body().getAbove().size(); i++) {
//
//                                ID[i] = response.body().getAbove().get(i).getSatid();
//                                NAME[i] = response.body().getAbove().get(i).getSatname();
//                                INT_DESIGNATOR[i] = response.body().getAbove().get(i).getIntDesignator();
//                                LAUNCH_DATE[i] = response.body().getAbove().get(i).getLaunchDate();
//                                SAT_LAT[i] = response.body().getAbove().get(i).getSatlat();
//                                SAT_LNG[i] = response.body().getAbove().get(i).getSatlng();
//                                SAT_ALT[i] = response.body().getAbove().get(i).getSatalt();
//                                if (SAT_LAT[i] == 1 && SAT_LNG[i] == 1) {
//                                    //  Animation_Movement(SAT_LAT[i], SAT_LNG[i]);
//
//                                    Toast.makeText(getApplicationContext(), SAT_LAT[i] + "--" + SAT_LNG[i], Toast.LENGTH_SHORT).show();
//                                    break;
//                                } else {
//                                    continue;
//                                }
//                            }
                            //  said(SAT_LAT[1], SAT_LNG[1]);
                        }
                    } else {
                        Log.e("dekha jak", "onResponse: " + response.body().getError());
                        Log.e("dekha jak", "onResponse: " + response.body().getInfo().getCategory());
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e("failure", "onFailure: " + t.getMessage());
            }
        });
    }

    private void Get_Adapter_List(ArrayList<Above> aboveList) {
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        adapter.getItemId(1);
//        adapter = new AdapterClass(aboveList);
//        recyclerView.setAdapter(adapter);
    }

    public void Animation_Movement(double lat, double lng) {
        moveX = ObjectAnimator.ofFloat(imageView, "translationX", (float) lat, (float) lng);
        moveY = ObjectAnimator.ofFloat(imageView, "translationY", (float) lat, (float) lng);
        AnimatorSet set = new AnimatorSet();
        set.play(moveX);
        // set.setDuration(180000);
        set.start();
    }

}
