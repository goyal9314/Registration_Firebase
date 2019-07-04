package com.example.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private TextView useremail;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth=FirebaseAuth.getInstance();
        //if the user is not logged in
        //means current user will return null
        if (firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login Activity
            startActivity(new Intent(this,LoginActivity.class));
        }
        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

    useremail=(TextView)findViewById(R.id.usermail);
    logout=(Button)findViewById(R.id.logout);

    logout.setOnClickListener(this);

useremail.setText("welcome " + user.getEmail() );
    }
    private void logooutfromprofile(){
            firebaseAuth.signOut();
             finish();
              startActivity(new Intent(this,LoginActivity.class));

    }


    @Override
    public void onClick(View v) {
        logooutfromprofile();
    }
}
