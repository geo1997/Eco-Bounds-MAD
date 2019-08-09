package com.example.shoppingcart;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    private TextInputLayout textInputEmail;
    private TextInputLayout textInputPassword;

    private TextView newaccnt;
    private TextView admin_tv;

    private Button log_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Login");

        textInputEmail=findViewById(R.id.text_input_email);
        textInputPassword=findViewById((R.id.text_input_password));
        newaccnt=findViewById(R.id.new_accnt_tv);
        admin_tv=findViewById(R.id.admin_tv);

        log_user=findViewById(R.id.log_btn);

        newaccnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTosignup();
            }
        });



        admin_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Log in as Admin", Toast.LENGTH_SHORT).show();
                gotoaddproduct();
            }
        });

        log_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoFinishedProducts();
            }
        });




    }

    public void gotoFinishedProducts(){
        Intent finished_products = new Intent(this,FinishedProducts.class);
        startActivity(finished_products);
    }

    public void gotoaddproduct(){
        Intent admin_addproduct = new Intent(this,AddFinishedProducts.class);
        startActivity(admin_addproduct);
    }


     private boolean validateEmail(){
        String emailInput=textInputEmail.getEditText().getText().toString().trim();

        if(emailInput.isEmpty()){
            textInputEmail.setError("Field can't be empty");
            return false;
        }else{
            textInputEmail.setError(null);
            return true;
        }

    }

    private boolean validatePassword(){
        String passwordInput=textInputPassword.getEditText().getText().toString().trim();

        if(passwordInput.isEmpty()){
            textInputPassword.setError("Field can't be empty");
            return false;
        }else{
            textInputPassword.setError(null);
            return true;
        }

    }


    public void confirmInput(View v){

        if(!validateEmail() | !validatePassword()){
            return;
        }

        String input="Email :"+textInputEmail.getEditText().getText().toString();
        input +="\n";
        input +="Successfully logged In";

        Toast.makeText(this,input,Toast.LENGTH_SHORT).show();


    }


    public void goTosignup(){
        Intent gotoSignup = new Intent (this ,signup.class);
        startActivity(gotoSignup);
    }
}
