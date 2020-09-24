package com.example.fiverr;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity  implements SeekBar.OnSeekBarChangeListener{
    private static final int MAX_VALUE = 255;
    private static final int MID_VALUE = 127;

    private ImageView mImageView;
    private float mHue, mSaturation, mLum;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.img);

        mImageView = (ImageView) findViewById(R.id.imageview);
        SeekBar seekBarHue = (SeekBar) findViewById(R.id.seekbarHue);
        SeekBar seekBarSaturation = (SeekBar) findViewById(R.id.seekbarSaturation);
        SeekBar seekBarLum = (SeekBar) findViewById(R.id.seekbarLum);

        seekBarHue.setOnSeekBarChangeListener(this);
        seekBarSaturation.setOnSeekBarChangeListener(this);
        seekBarLum.setOnSeekBarChangeListener(this);

        //Set the maximum value
        seekBarHue.setMax(MAX_VALUE);
        seekBarSaturation.setMax(MAX_VALUE);
        seekBarLum.setMax(MAX_VALUE);

        //Set progress
        seekBarHue.setProgress(MID_VALUE);
        seekBarSaturation.setProgress(MID_VALUE);
        seekBarLum.setProgress(MID_VALUE);

        mImageView.setImageBitmap(mBitmap);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seekbarHue://hue
                mHue = (progress - MID_VALUE) * 1.0f / MID_VALUE * 180;
                break;
            case R.id.seekbarSaturation://saturation
                mSaturation = progress * 1.0f / MID_VALUE;
                break;
            case R.id.seekbarLum://Brightness
                mLum = progress * 1.0f / MID_VALUE;
                break;
        }
        mImageView.setImageBitmap(ImageHelper.handleImageEffect(mBitmap, mHue, mSaturation, mLum));

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}