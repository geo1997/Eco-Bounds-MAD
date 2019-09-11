package com.example.shoppingcart;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import androidx.annotation.NonNull;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class login extends AppCompatActivity {

    private TextInputLayout textInputEmail;
    private TextInputLayout textInputPassword;

    private TextView newaccnt;
    private TextView admin_tv;

    private EditText emailtv,passtv;

    private Button log_user;

    private FirebaseAuth firebaseAuth;

    AwesomeValidation awesomeValidation;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Login");

        textInputEmail=findViewById(R.id.text_input_email);
        textInputPassword=findViewById((R.id.text_input_password));
        newaccnt=findViewById(R.id.new_accnt_tv);
        admin_tv=findViewById(R.id.admin_tv);
        emailtv=findViewById(R.id.emtv);
        passtv=findViewById(R.id.pwtv);
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);



        log_user=findViewById(R.id.log_btn);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        newaccnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTosignup();
            }
        });

        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() !=null){
            //home
        }


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
                validateEmail();
                validatePassword();
                confirmInput();
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


    public void confirmInput(){

        if(validateEmail() | validatePassword()){

            String email=emailtv.getText().toString().trim();
            final String pw=passtv.getText().toString().trim();

            progressBar.setVisibility(View.VISIBLE);
           firebaseAuth.signInWithEmailAndPassword(email,pw)
                   .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           progressBar.setVisibility(View.GONE);
                           if(!task.isSuccessful()){
                               if(pw.length()<6){
                                   textInputPassword.setError("Password is incorrect");
                               }else{
                                   Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                               }
                           }else{
                               //Toast.makeText(getApplicationContext(),"Login succesful",Toast.LENGTH_SHORT).show();
                               String input="Email :"+textInputEmail.getEditText().getText().toString();
                               input +="\n";
                               input +="Successfully logged In";

                               Toast.makeText(getApplicationContext(),input,Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
        }



    }


    public void goTosignup(){
        Intent gotoSignup = new Intent (this ,signup.class);
        startActivity(gotoSignup);
    }






}
