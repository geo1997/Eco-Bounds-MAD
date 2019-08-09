package com.example.shoppingcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class deletaccount extends AppCompatActivity {

    private Button del_acnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Delete Account");
        setContentView(R.layout.activity_delete_account);

        del_acnt=findViewById(R.id.delete_accnt);

        del_acnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Account Deleted Successfully",Toast.LENGTH_SHORT).show();
                Intent logout = new Intent(getApplicationContext(),login.class);
                startActivity(logout);
            }
        });
    }
}
