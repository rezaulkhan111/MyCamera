package com.example.internship.mypersonalcamera;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Path;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.animation.DynamicAnimation;
import android.support.animation.FlingAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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

public class MainActivity extends Activity implements View.OnClickListener {
    String authorization_Key = "&apiKey=53VELF-92F346-8HGD75-3IPC";
    double observer_lat = 41.702f;
    double observer_lng = -76.014f;
    double observer_alt = 0;
    int search_radius90 = 90;
    int category_id = 15;
    private AdapterClass adapter;
    ArrayList<Above> aboveList = new ArrayList<>();
    RecyclerView recyclerView;

    int[] ID;
    String[] NAME;
    String[] INT_DESIGNATOR;
    String[] LAUNCH_DATE;
    Double[] SAT_LAT;
    Double[] SAT_LNG;
    Double[] SAT_ALT;
    ObjectAnimator moveX;
    ObjectAnimator moveY;
//    ObjectAnimator moveX1;
//
//    ObjectAnimator moveY1;

    //    android.hardware.Camera camera;
//    FrameLayout frameLayout;
//    ShowCamera showCamera;
    ImageView imageView;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
//        frameLayout = findViewById(R.id.frame_layout_camera);
        imageView = findViewById(R.id.iv_photo);
//
//        camera = android.hardware.Camera.open();
//        showCamera = new ShowCamera(this, camera);
//        frameLayout.addView(showCamera);

        // recyclerView = findViewById(R.id._rv_id);
        //  recyclerView.setHasFixedSize(true);
        //  recyclerView.setLayoutManager(new LinearLayoutManager(this));

        button.setOnClickListener(this);
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

                                if (above.getSatid() == 25077) {
                                    Animation_Movement(above.getSatlat(), above.getSatlng());

                                    Toast.makeText(getApplicationContext(), above.getSatlat() + "--" + above.getSatlng(), Toast.LENGTH_SHORT).show();
                                    break;
                                } else {
                                    continue;
                                }
                            }

                            //  adapter = new AdapterClass(aboveList);
                            // recyclerView.setAdapter(adapter);
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


    public void Animation_Movement(double lat, double lng) {


        SpringAnimation springAnimation
                = new SpringAnimation(imageView, DynamicAnimation.X);


//        float x = (float) Sat_Lan;
//        float y = (float) Sat_Lan;
//        android.graphics.Path path = new android.graphics.Path();
//        path.moveTo(x + 0, y + 0);
//        path.lineTo(x + 100, y + 150);
//        path.lineTo(x + 400, y + 150);
//        path.lineTo(x + 0, y + 0);
//
//
//            @SuppressLint({"NewApi", "LocalSuppress"}) ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, View.X, View.Y, path);
//            objectAnimator.setDuration(3000);
//            objectAnimator.start();


        moveX = ObjectAnimator.ofFloat(imageView, "translationX", (float) lat, (float) lng);
        moveY = ObjectAnimator.ofFloat(imageView, "translationY", (float) lng, (float) lat);
        // moveX1 = ObjectAnimator.ofFloat(imageView, "translationX", (float) Sat_Lat, (float) -Sat_Lan);
        // moveY1 = ObjectAnimator.ofFloat(imageView, "translationY", (float) Sat_Lat, (float) -Sat_Lan);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(moveX, moveY);
        //set.playTogether(moveY);
        set.setDuration(1000000);
        set.start();
    }

    @Override
    public void onClick(View v) {
        JSON_Response_Method();
    }
}
