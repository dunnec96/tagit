package com.example.christopherdunne.tagit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        OnClickLocation();




    }
    EditText name;
    EditText address;
    EditText number;
    EditText herdNumber;
    Button submit;
    String Pname;
    String Paddress;
    String Pnumber;
    String PherdNumber;
    public void OnClickLocation()
    {
        submit = (Button) findViewById(R.id.button4);
        // showing current location on map.
        submit.setOnClickListener(new View.OnClickListener()
                                  {

                                      public void onClick(View v)
                                      {
                                          name = (EditText)findViewById(R.id.name);
                                          address = (EditText)findViewById(R.id.address);
                                          number = (EditText)findViewById(R.id.number);
                                          herdNumber= (EditText)findViewById(R.id.herdNumber);

                                          Pname = name.getText().toString()+ ".";
                                          Paddress = address.getText().toString()+ ".";
                                          Pnumber =  number.getText().toString()+ ".";
                                          PherdNumber=herdNumber.getText().toString()+".";
                                          Toast.makeText(getApplicationContext(),Pname+  " "+Paddress + " "+Pnumber, Toast.LENGTH_LONG).show();
                                          //new SendUser().execute(Pname,Paddress,Pnumber);
                                          Intent intent = new Intent(UserActivity.this, LoginActivity.class);
                                          startActivity(intent);


                                      }


                                  }

        );
    }

}
