package com.pm3;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URI;

/**
 * Created by Felix on 2017/10/7.
 */

public class ImageDownload extends AsyncTask<Uri, Integer, Bitmap> {

    private ImageView imageView;

    private ProgressDialog progressDialog;

    public ImageDownload(Context context, ImageView imageView) {
        this.imageView = imageView;

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("我會慢慢等、慢慢等、慢慢等～");
        progressDialog.show();
    }

    @Override
    protected Bitmap doInBackground(Uri... params) {

        Uri uri = params[0];
        Bitmap bitmap = null;

        try {
            URI uri1 = new URI(uri.toString());
            InputStream in = uri1.toURL().openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressDialog.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        progressDialog.dismiss();
        imageView.setImageBitmap(bitmap);
    }
}