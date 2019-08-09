package com.example.shoppingcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class shoppingCart extends AppCompatActivity {

    private Button proc_checkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingcart);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        proc_checkout=findViewById(R.id.proceed_checkout);


        proc_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoCheckout();
            }
        });

    }


    public void gotoCheckout(){
        Intent checkout = new Intent (this, checkout.class);
        startActivity(checkout);
    }
}
