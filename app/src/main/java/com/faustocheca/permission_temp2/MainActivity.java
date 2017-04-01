package com.faustocheca.permission_temp2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button takePictureButton;
    private ImageView imageView;

    private String[] permissions = {Manifest.permission.CAMERA};
    private static int REQUEST_CODE_CAMERA = 0;



    // App:
    // Activity1:
        // botón tomar foto y imageView
        // permisos: chequear si los hay y si no pedirlos
        // permisos OK -> Intent y AFR a app_camera y sacar una foto
        // results: presentar la foto





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // botón tomar foto y imageView
        takePictureButton = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.image_view);

        // permisos: chequear si los hay y si no pedirlos
        // si te los dan -> Intent y AFR a app_camera y sacar una foto

        ensurePermissions();

        // ya tenemos el button enabled
        // acción del botón : pedir que se cargue la app de fotos
        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 111);


            }
        });

    }



    private void ensurePermissions() {
        if (ContextCompat.checkSelfPermission(this, permissions[0]) != PackageManager.PERMISSION_GRANTED) {
            takePictureButton.setEnabled(false);
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_CAMERA);


        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 111) {
            if (resultCode == RESULT_OK) {

                Bundle extras = data.getExtras();
                Bitmap thePic = extras.getParcelable("data");

                imageView.setImageBitmap(thePic);


            }


        }

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_CAMERA ) {
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                takePictureButton.setEnabled(true);
            }


        }



    }

}
