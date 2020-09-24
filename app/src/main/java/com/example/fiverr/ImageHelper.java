package com.example.fiverr;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

public class ImageHelper {
    public static Bitmap handleImageEffect(Bitmap bm, float hue, float saturation, float lum) {
        //Set the hue of the color
        ColorMatrix hueMatrix = new ColorMatrix();
        //The first parameter, the system uses 0, 1, 2 to represent the processing of Red, Green, Blue three colors; and the second parameter is the value that needs to be processed
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);

        //Set the saturation of the color
        ColorMatrix saturationMatrix = new ColorMatrix();
        //The saturation parameter represents the value of setting the saturation of the color. When the saturation is 0, the image becomes a grayscale image
        saturationMatrix.setSaturation(saturation);

        //Set the brightness of the color
        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        //Mix the effect of the matrix to superimpose the processing effect
        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);

        /**
         * After setting the color matrix for processing, by using the setColorFilter() method of the Paint class, it will be constructed by imageMatrix
         * Pass ColorMatrixColorFilter object into it, and use this brush to draw the original image, so as to apply the color matrix to the original image
         */
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        /**
         * Android system also does not allow direct modification of the original image, similar to the lock in Photoshop, you must create a Bitmap of the same size from the original image, and
         * The original image is drawn into the Bitmap, and the image is modified in the form of a copy.
         */
        Bitmap bitmap = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(bm, 0, 0 ,paint);
        return bitmap;
    }
}