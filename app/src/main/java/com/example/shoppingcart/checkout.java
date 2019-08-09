package com.example.shoppingcart;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class checkout extends AppCompatActivity {

    private Button confirm_pay;
    Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        myDialog = new Dialog(this);

    confirm_pay=findViewById(R.id.confirm_pay_btn);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    confirm_pay.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(),"Payment Done",Toast.LENGTH_SHORT).show();
            gotohomepage();
        }
    });

    }

    public void gotohomepage(){
        Intent homepage = new Intent ( this, userprofile.class);
        startActivity(homepage);
    }

    public void showPopup(View v){
        TextView txtClose;
        myDialog.setContentView(R.layout.custompopup_checkout);
        txtClose=myDialog.findViewById(R.id.closs_tv);

        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

}
