package com.example.shoppingcart;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class signup extends AppCompatActivity {

//    private TextView tv1;
//    private Button new_accnt_btn;


//    String myString= "Account succesfully created";

        EditText fname,lname,email,phone,pass,cnfpass;
        Button submit;
        AwesomeValidation awesomeValidation;
        DatabaseReference dbRef;
        UserRegistration ur;
        long maxid=0;
        private ProgressBar progressBar;
     FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

//        getSupportActionBar().setTitle("Sign Up");
//
//        tv1=findViewById(R.id.already_a_mem_tv);
//        new_accnt_btn= findViewById(R.id.new_accnt_btn);
//
//        tv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openLoginPage();
//            }
//        });
//
//
//        new_accnt_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//             Toast.makeText(getApplicationContext(),myString,Toast.LENGTH_SHORT).show();
//            }
//        });




        fname=findViewById(R.id.fnametv);
        lname=findViewById(R.id.lnametv);
        email=findViewById(R.id.emailtv);
        phone=findViewById(R.id.mobnumtv);
        pass=findViewById(R.id.passwordtv);
        cnfpass=findViewById(R.id.confpasstv);
       progressBar = findViewById(R.id.progressBar);
       progressBar.setVisibility(View.GONE);

        submit=findViewById(R.id.new_accnt_btn);




        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        updateUI();

        //ur = new UserRegistration();
        firebaseAuth=FirebaseAuth.getInstance();
//        dbRef= FirebaseDatabase.getInstance().getReference().child("UserRegistration");
//        dbRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists()){
//                    maxid=(dataSnapshot.getChildrenCount());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUI();
                registeruser();
            }
        });

    }

//    public void openLoginPage(){
//        Intent intent = new Intent(this,login.class);
//        startActivity(intent);
//    }


    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser() !=null){

        }
    }

    private void updateUI() {

//        fname=findViewById(R.id.fnametv);
//        lname=findViewById(R.id.lnametv);
//        email=findViewById(R.id.emailtv);
//        phone=findViewById(R.id.mobnumtv);
//        pass=findViewById(R.id.passwordtv);
//        cnfpass=findViewById(R.id.confpasstv);
//
//        submit=findViewById(R.id.new_accnt_btn);

        //String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";

        awesomeValidation.addValidation(signup.this, R.id.fnametv, "[a-zA-Z\\s]+", R.string.valfname);
        awesomeValidation.addValidation(signup.this, R.id.lnametv, "[a-zA-Z\\s]+", R.string.vallname);
        awesomeValidation.addValidation(signup.this, R.id.emailtv, android.util.Patterns.EMAIL_ADDRESS, R.string.valemail);
        awesomeValidation.addValidation(signup.this, R.id.mobnumtv, RegexTemplate.TELEPHONE, R.string.valphone);
        //awesomeValidation.addValidation(signup.this,R.id.passwordtv ,regexPassword,R.string.valpass);
        awesomeValidation.addValidation(signup.this, R.id.confpasstv, R.id.passwordtv, R.string.valconfpass);
    }

//        submit.setOnClickListener(new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View v) {
//
//                                                if (awesomeValidation.validate()) {
//                                                    //Toast.makeText(getApplicationContext(),"Data Recieved Succesfully",Toast.LENGTH_SHORT).show();
//
//                                                    String em = email.getText().toString().trim();
//                                                    String pw = pass.getText().toString().trim();
//
//                                                    //progressBar.setVisibility(View.VISIBLE);
//                                                    firebaseAuth.createUserWithEmailAndPassword(em, pw)
//                                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                                                @Override
//                                                                public void onComplete(@NonNull Task<AuthResult> task) {
//                                                                    if (task.isComplete()) {
//                                                                        //dbRef.child(String.valueOf(maxid+1)).setValue("userregistration");
//                                                                        ur.setFname(fname.getText().toString().trim());
//                                                                        ur.setLname(lname.getText().toString().trim());
//                                                                        ur.setEmail(email.getText().toString().trim());
//                                                                        ur.setPassword(pass.getText().toString().trim());
//                                                                        dbRef.child(String.valueOf(maxid + 1)).setValue(ur);
//
//
//                                                                        FirebaseDatabase.getInstance().getReference("UserRegistration")
//                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                                .setValue(ur).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                            @Override
//                                            public void onComplete(@NonNull Task<Void> task) {
//                                                //progressBar.setVisibility(View.GONE);
//                                                if (task.isSuccessful()) {
//                                                    Toast.makeText(signup.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
//                                                }
//                                            }
//                                        });
//                                    } else {
//                                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//
//
//                    //dbRef.push().setValue(ur);
//
//
//                    Toast.makeText(getApplicationContext(), "Data Recieved Succesfully", Toast.LENGTH_SHORT).show();
////                    Intent intent = new Intent(getApplicationContext(),login.class);
////                    startActivity(intent);
//
//
//                } else {
//                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


    public void registeruser(){
        final String Fname=fname.getText().toString().trim();
        final String Lname=lname.getText().toString().trim();
       final String em = email.getText().toString().trim();
         final String Mobnum=phone.getText().toString().trim();
        final String pw = pass.getText().toString().trim();

        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(em,pw)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            UserRegistration ur = new UserRegistration(
                                    Fname,
                                    Lname,
                                    em,
                                    Mobnum,
                                    pw
                            );

                            FirebaseDatabase.getInstance().getReference("UserRegistration")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(ur).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if(task.isSuccessful()){
                                        Toast.makeText(signup.this, "Registration Success", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),login.class);
                                        startActivity(intent);
                                        Toast.makeText(getApplicationContext(), "Please Login", Toast.LENGTH_SHORT).show();
                                    }
                                    else{

                                    }
                                }
                            });

                        } else {
                            Toast.makeText(signup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });




}


}
