package com.example.shoppingcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegisterCompany extends AppCompatActivity {

    private Button back_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_company);

        getSupportActionBar().setTitle("Register Company");
        back_home=findViewById(R.id.back_home);


        back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotohomepage();
            }
        });

    }

    public void gotohomepage(){
        Intent homego = new Intent(this,userprofile.class);
        startActivity(homego);
    }


    public void onClick(View view)
    {
        Toast.makeText(this,"Registered Company Successfully",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(RegisterCompany.this,CompanyProfile.class);
        startActivity(intent);
    }

}
