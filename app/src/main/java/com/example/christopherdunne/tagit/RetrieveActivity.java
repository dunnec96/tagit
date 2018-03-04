package com.example.christopherdunne.tagit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RetrieveActivity extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList <String> list;
    ArrayAdapter<String> adapter;
    Animal animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_retrieve );

        animal = new Animal();
        listView = (ListView) findViewById(R.id.listView );
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Animal").child( FirebaseAuth.getInstance().getCurrentUser().getUid()).child( "Animal03" );
        list = new ArrayList <>();
        adapter = new ArrayAdapter <String>( this,R.layout.animal_info ,R.id.animalInfo, list);
        ref.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    Toast.makeText(RetrieveActivity.this, "Please Fill All the Fields"+ ds, Toast.LENGTH_LONG).show();
                    animal = ds.getValue(Animal.class);
                    if(animal != null) {
                        list.add( animal.getTagNo().toString()+ ""+animal.getDam().toString()+""+animal.getDateOfBirth().toString()+""+animal.getSex().toString()+""+animal.getSire().toString());
                    }
                }


                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );
    }
}
