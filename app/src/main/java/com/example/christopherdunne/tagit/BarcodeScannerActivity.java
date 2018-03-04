package com.example.christopherdunne.tagit;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class BarcodeScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkPermission())
            {
                Toast.makeText(BarcodeScannerActivity.this,"Permission is granted",Toast.LENGTH_LONG).show();
            }
            else
            {
                requestPermission();
            }
        }

    }

    private boolean checkPermission()
    {
       return (ContextCompat.checkSelfPermission(BarcodeScannerActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this,new String[]{CAMERA_SERVICE},REQUEST_CAMERA);
    }

    public void onRequestPermissionResult(int requestCode, String permissions[],int grantResults[])
    {
        switch (requestCode)
        {
            case REQUEST_CAMERA:
                if (grantResults.length > 0)
                {
                    boolean cameraAccepted = grantResults[0]==PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted)
                    {
                        Toast.makeText(BarcodeScannerActivity.this,"Permission Granted",Toast.LENGTH_LONG).show();

                    }
                    else
                    {
                        Toast.makeText(BarcodeScannerActivity.this,"Permission Denied",Toast.LENGTH_LONG).show();
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        {
                            if(shouldShowRequestPermissionRationale(CAMERA_SERVICE))
                            {
                                displayAlertMessage("You need to allow acess for both permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA_SERVICE},REQUEST_CAMERA);
                                                }
                                            }
                                        });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }
    public void onResume()
    {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
          if (checkPermission())
          {
              if (scannerView == null)
              {
                  scannerView = new ZXingScannerView(this);
                  setContentView(scannerView);
              }
              scannerView.setResultHandler(this);
              scannerView.startCamera();
          }
          else
          {
            requestPermission();
          }
        }
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        scannerView.stopCamera();
    }


    public void displayAlertMessage(String message, DialogInterface.OnClickListener listener)
    {
        new AlertDialog.Builder(BarcodeScannerActivity.this)
                .setMessage(message)
                .setPositiveButton("ok",listener)
                .setNegativeButton("cancel",null)
                .create()
                .show();
    }

    @Override
    public void handleResult(final Result result) {
        final String scanResult = result.getText();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                scannerView.resumeCameraPreview(BarcodeScannerActivity.this);
            }
        });
        builder.setNeutralButton("Visit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(BarcodeScannerActivity.this,RegisterAnimalActivity.class);
                startActivity(intent);

            }
        });
        builder.setMessage(scanResult);
        AlertDialog alert =builder.create();
        alert.show();

    }
}
