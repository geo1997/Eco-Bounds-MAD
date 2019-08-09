package com.example.shoppingcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class FinishedProducts extends AppCompatActivity {


    private ImageButton b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_products);


        getSupportActionBar().setTitle("Finished Products");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        b1=findViewById(R.id.clickImage);

                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gotoshoppincart();
                    }
                });

    }

    public void gotoshoppincart(){
        Intent shoppingcart = new Intent(this,shoppingCart.class);
        startActivity(shoppingcart);
    }


}
