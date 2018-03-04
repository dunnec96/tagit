package com.example.christopherdunne.tagit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterAnimalActivity extends AppCompatActivity {

    EditText tagNo,dam,sire,date,sex;
    Button register;
    FirebaseDatabase database;
    DatabaseReference ref;
    Animal animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_animal);

        tagNo =(EditText) findViewById(R.id.tagNo);
        dam = (EditText) findViewById(R.id.dam);
        sire = (EditText) findViewById(R.id.sire);
        date = (EditText) findViewById(R.id.dateOfBirth);
        sex = (EditText) findViewById(R.id.sex);
        register =(Button) findViewById(R.id.btnClick);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Animal").child( FirebaseAuth.getInstance().getCurrentUser().getUid()).child( "Animal03" );
        animal = new Animal();

    }


private void getValues()
{
    animal.setTagNo(tagNo.getText().toString());
    animal.setDam(dam.getText().toString());
    animal.setSire(sire.getText().toString());
    animal.setDate(date.getText().toString());
    animal.setSex(sex.getText().toString());
}

    public void register(View view) {


                getValues();
                ref.child("Animal03").setValue(animal);
                Toast.makeText(RegisterAnimalActivity.this,"Data inserted",Toast.LENGTH_LONG).show();
            }




    }



