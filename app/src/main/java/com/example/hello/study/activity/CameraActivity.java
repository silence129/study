package com.example.hello.study.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.hello.study.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;

    private Button button_take_photo;
    private Button button_open_album;
    private ImageView imageView_photo;

    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        button_open_album = (Button) findViewById(R.id.button_open_album);
        button_take_photo = (Button) findViewById(R.id.button_take_photo);
        imageView_photo = (ImageView) findViewById(R.id.image_photo);

        button_open_album.setOnClickListener(this);
        button_take_photo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_take_photo:

                File outputImage = new File(Environment.getExternalStorageDirectory(),
                        "tempImage.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(outputImage);
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO); // 启动相机程序

                break;

            case R.id.button_open_album:

                File outputImage2 = new File(Environment.
                        getExternalStorageDirectory(), "output_image.jpg");
                try {
                    if (outputImage2.exists()) {
                        outputImage2.delete();
                    }
                    outputImage2.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(outputImage2);
                Intent intent2 = new Intent("android.intent.action.GET_CONTENT");
                intent2.setType("image/*");
                intent2.putExtra("crop", true);
                intent2.putExtra("scale", true);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent2, CROP_PHOTO);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CROP_PHOTO); // 启动裁剪程序
                }
                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream
                                (getContentResolver()
                                        .openInputStream(imageUri));
                        imageView_photo.setImageBitmap(bitmap); // 将裁剪后的照片显示出来
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
