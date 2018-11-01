package com.example.internship.mypersonalcamera;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.List;

public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback {

    android.hardware.Camera camera;

    SurfaceHolder holder;

    public ShowCamera(Context context, android.hardware.Camera camera) {

        super(context);
        this.camera = camera;
        holder = getHolder();
        holder.addCallback(this);
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        android.hardware.Camera.Parameters parameters = camera.getParameters();


        List<android.hardware.Camera.Size> sizes = parameters.getSupportedPictureSizes();
        android.hardware.Camera.Size mSize = null;

        for (android.hardware.Camera.Size size : sizes) {
            mSize = size;
        }


        if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
            parameters.set("orientation", "portrait");
            camera.setDisplayOrientation(90);
            parameters.setRotation(90);
        } else {
            parameters.set("orientation", "landscape");
            camera.setDisplayOrientation(0);
            parameters.setRotation(0);
        }

        parameters.setPictureSize(mSize.width, mSize.width);


        camera.setParameters(parameters);
        try {
            camera.setPreviewDisplay(holder);

            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
