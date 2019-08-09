package com.example.shoppingcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddFinishedProducts extends AppCompatActivity {

    private Button regCen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_finished_products);




        getSupportActionBar().setTitle("Add Finished Products");


        regCen=findViewById(R.id.reg_cent);

        regCen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegCompany();
            }
        });
    }

    public void RegCompany(){
        Intent regcompany = new Intent(this,RegisterCompany.class);
        startActivity(regcompany);
    }

}
