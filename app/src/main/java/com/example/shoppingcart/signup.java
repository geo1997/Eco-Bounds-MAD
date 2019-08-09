package com.example.shoppingcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    private TextView tv1;
    private Button new_accnt_btn;


    String myString= "Account succesfully created";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().setTitle("Sign Up");

        tv1=findViewById(R.id.already_a_mem_tv);
        new_accnt_btn= findViewById(R.id.new_accnt_btn);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginPage();
            }
        });


        new_accnt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Toast.makeText(getApplicationContext(),myString,Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void openLoginPage(){
        Intent intent = new Intent(this,login.class);
        startActivity(intent);
    }
}
