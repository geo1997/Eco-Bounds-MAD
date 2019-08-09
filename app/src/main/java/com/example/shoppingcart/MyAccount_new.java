package com.example.shoppingcart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyAccount_new extends AppCompatActivity {

    private Button updated_accnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Account");

        setContentView(R.layout.activity_my_account_new);


       updated_accnt= findViewById(R.id.update_accnt);

       updated_accnt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(getApplicationContext(),"Account Details Updated Succesfully",Toast.LENGTH_SHORT).show();
           }
       });
    }



}
