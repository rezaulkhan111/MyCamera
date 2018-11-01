package com.example.internship.mypersonalcamera;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.internship.mypersonalcamera.model.Info;
import com.example.internship.mypersonalcamera.model.Response;
import com.example.internship.mypersonalcamera.model.RetrofitClientInstance;

import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends Activity {

    String authorization_Key = "&apiKey=53VELF-92F346-8HGD75-3IPC";
    double observer_lat = 41.702f;
    double observer_lng = -76.014f;
    double observer_alt = 0;
    int search_radius90 = 90;
    int category_id = 18;


    ObjectAnimator moveX;
    ObjectAnimator moveY;
    ObjectAnimator moveX1;

    ObjectAnimator moveY1;

    android.hardware.Camera camera;
    FrameLayout frameLayout;
    ShowCamera showCamera;
    ImageView imageView;
    int delay = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.frame_layout_camera);
        imageView = findViewById(R.id.iv_photo);

        camera = android.hardware.Camera.open();
        showCamera = new ShowCamera(this, camera);
        frameLayout.addView(showCamera);

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            public void run() {
                aaaaaaa();

            }
        }, delay);
    }


//    private void StartA() {
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation);
//        textView.startAnimation(animation);
//    }

    double Sat_Lat;
    double Sat_Lan;

    public void aaaaaaa() {
        final GetDataService date_service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Response> call = date_service.PostFromURLData(observer_lat, observer_lng, observer_alt, search_radius90, category_id, authorization_Key);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                Log.e("1", "onResponse: " + response.code());
                Log.e("2", "onResponse: " + response.isSuccessful());
                Log.e("3", "onResponse: " + response.body());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        String C = response.body().getInfo().getCategory();
                        String T = String.valueOf(response.body().getInfo().getTransactionscount());
                        String S = String.valueOf(response.body().getInfo().getSatcount());

                        Toast.makeText(getApplicationContext(), C + "--" + T + "--" + S, Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < response.body().getAbove().size(); i++) {

                            int Sat_ID = response.body().getAbove().get(i).getSatid();
                            String Sat_Name = response.body().getAbove().get(i).getSatname();
                            String int_Designator = response.body().getAbove().get(i).getIntDesignator();
                            Sat_Lat = response.body().getAbove().get(i).getSatlat();
                            Sat_Lan = response.body().getAbove().get(i).getSatlat();
                            double Sat_Alt = response.body().getAbove().get(i).getSatalt();

                            Toast.makeText(getApplicationContext(), Sat_ID + "--" + Sat_Name + "--" + int_Designator + "--" + Sat_Lat + "--" + Sat_Lan + "--" + Sat_Alt, Toast.LENGTH_SHORT).show();
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

    public void said(View view) {
        moveX = ObjectAnimator.ofFloat(imageView, "translationX", (float) Sat_Lat, (float) Sat_Lan);
        moveY = ObjectAnimator.ofFloat(imageView, "translationY", (float) Sat_Lat, (float) Sat_Lan);
        // moveX1 = ObjectAnimator.ofFloat(imageView, "translationX", (float) Sat_Lat, (float) -Sat_Lan);
        // moveY1 = ObjectAnimator.ofFloat(imageView, "translationY", (float) Sat_Lat, (float) -Sat_Lan);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(moveX, moveY);
//        downIt.setDuration(2500);
//        downIt.setRepeatCount(ValueAnimator.INFINITE);
//        downIt.setRepeatMode(ValueAnimator.RESTART);
        set.start();
    }
}
