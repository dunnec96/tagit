package com.example.christopherdunne.tagit;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.test.mock.MockPackageManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class UserSelectionActivity extends AppCompatActivity {
    /*
        This is the main page when the program is executed giving choice for the Parent Login and the user Login.
     */
    private ImageView Parent;
    private ImageView Client;
    private Animation loadAnimation;
    private Animation loadAnimation2;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = android.Manifest.permission.ACCESS_FINE_LOCATION;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);
        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != MockPackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);

                // If any permission above not allowed by user, this condition will

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent = (ImageView) findViewById(R.id.Admin);
        Client = (ImageView) findViewById(R.id.Farmer);


        int permissionCheck = ContextCompat.checkSelfPermission(UserSelectionActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED) {
            // ask permissions here using below code
            ActivityCompat.requestPermissions(UserSelectionActivity.this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},3310);
        }


        this.loadAnimation = AnimationUtils.loadAnimation(this, R.anim.fromtop);
        this.loadAnimation.reset();

        this.loadAnimation2 = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        this.loadAnimation2.reset();


        this.Parent.clearAnimation();
        this.Parent.startAnimation(this.loadAnimation);
        this.Client.clearAnimation();
        this.Client.startAnimation(this.loadAnimation);


        OnClickParent();
        OnClickClient();
    }

    /*
    OnClickParent will bring the administrator to the Login page allowing them to sign up or login.
     */
    public void OnClickParent() {

        Parent.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {

                                          Intent intent = new Intent(UserSelectionActivity.this,LoginActivity.class);
                                          startActivity(intent);
                                      }

                                  }
        );
    }

    /*
    OnClickClient will bring the user to the clients Login page by an intent.
     */

    public void OnClickClient() {

        Client.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          Intent intent = new Intent(UserSelectionActivity.this, MainActivity.class);
                                          startActivity(intent);
                                      }

                                  }
        );
    }

}



