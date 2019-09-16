package com.example.shoppingcart;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingcart.Prevalent.Prevalent;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class Settings extends AppCompatActivity {

    private CircleImageView profileImageView;
    private TextView closeAcc,updateAcc,profilePic;
    private EditText etname,etphone,etemail,etaddress;

    private Uri imageUri;
    private String myUrl = "";
    private StorageTask uploadTask;
    private StorageReference storageProfilePictureRef;
    private String checker = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        storageProfilePictureRef = FirebaseStorage.getInstance().getReference().child("Profile picture");

        profileImageView = (CircleImageView) findViewById(R.id.user_profile_image);
        profilePic = findViewById(R.id.profile_pic);
        closeAcc = findViewById((R.id.close_settings));
        updateAcc = findViewById(R.id.update_settings);
        etname = findViewById(R.id.acc_name);
        etphone = findViewById(R.id.acc_phone);
        etaddress = findViewById(R.id.acc_address);
        etemail = findViewById(R.id.acc_email);

        userInfoDisplay(profileImageView,etname,etphone,etemail,etaddress);

        closeAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        updateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checker.equals("clicked"))
                {
                    userInfoSaved();
                }
                else
                {
                    updateOnlyUserInfo();
                }
            }
        });

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checker = "clicked";
                CropImage.activity(imageUri)
                        .setAspectRatio(1,1)
                        .start(Settings.this);
            }
        });

    }

    private void updateOnlyUserInfo()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");

        HashMap<String,Object> userMAp = new HashMap<>();
        userMAp.put("name",etname.getText().toString());
        userMAp.put("phone",etphone.getText().toString());
        userMAp.put("email",etemail.getText().toString());
        userMAp.put("address",etaddress.getText().toString());
        ref.child(Prevalent.currentonlineUser.getPhone()).updateChildren(userMAp);

        startActivity(new Intent(Settings.this,userprofile.class));
        Toast.makeText(Settings.this,"Profile Information updated successfully",Toast.LENGTH_LONG).show();
        finish();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && requestCode==RESULT_OK && data!=null)
        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();
            profileImageView.setImageURI(imageUri);
        }
        else
        {
            Toast.makeText(this,"Error,try again",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Settings.this,Settings.class));
            finish();
        }
    }

    private void userInfoSaved()
    {
        if (TextUtils.isEmpty((etname.getText().toString())))
        {
            Toast.makeText(this,"Name is mandatory",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty((etphone.getText().toString())))
        {
            Toast.makeText(this,"Phone number is mandatory",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty((etemail.getText().toString())))
        {
            Toast.makeText(this,"Email is mandatory",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty((etaddress.getText().toString())))
        {
            Toast.makeText(this,"Address is mandatory",Toast.LENGTH_SHORT).show();
        }
        else if (checker.equals("clicked"))
        {
            uploadImage();
        }
    }

    private void uploadImage() {
        if (imageUri != null)
        {
            final StorageReference fileRef = storageProfilePictureRef.child(Prevalent.currentonlineUser.getPhone()+".jpg");

            uploadTask = fileRef.putFile(imageUri);

            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {

                    if (!task.isSuccessful())
                    {
                        throw task.getException();
                    }

                    return fileRef.getDownloadUrl();
                }
            })
                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task <Uri>task) {
                            if (task.isSuccessful())
                            {
                                Uri downloadUrl = task.getResult();
                                myUrl = downloadUrl.toString();

                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");

                                HashMap<String,Object> userMAp = new HashMap<>();
                                userMAp.put("name",etname.getText().toString());
                                userMAp.put("phone",etphone.getText().toString());
                                userMAp.put("email",etemail.getText().toString());
                                userMAp.put("address",etaddress.getText().toString());
                                userMAp.put("image",myUrl);
                                ref.child(Prevalent.currentonlineUser.getPhone()).updateChildren(userMAp);

                                startActivity(new Intent(Settings.this,userprofile.class));
                                Toast.makeText(Settings.this,"Profile Information updated successfully",Toast.LENGTH_LONG).show();
                                finish();
                            }
                            else
                            {
                                Toast.makeText(Settings.this,"Error",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
        else
        {
            Toast.makeText(this,"Image not selected",Toast.LENGTH_SHORT).show();
        }
    }

    private void userInfoDisplay(final CircleImageView profileImageView, final EditText etname, final EditText etphone, final EditText etemail, final EditText etaddress) {

        DatabaseReference UserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.currentonlineUser.getPhone());

        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                     if (dataSnapshot.child("image").exists())
                     {
                         String image = dataSnapshot.child("image").getValue().toString();
                         String name = dataSnapshot.child("name").getValue().toString();
                         String phone = dataSnapshot.child("phone").getValue().toString();
                         String email = dataSnapshot.child("email").getValue().toString();
                         String address = dataSnapshot.child("image").getValue().toString();




                         Picasso.get().load(image).into(profileImageView);
                         etname.setText(name);
                         etphone.setText(phone);
                         etemail.setText(email);
                         etaddress.setText(address);
                     }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
