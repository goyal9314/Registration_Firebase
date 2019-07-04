package com.example.registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText emailtext,passwordtext;
    private Button register;
    private TextView signin,forgot_password;
//defining firebase auth
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Initializing firebasr auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //if getcurrentuser does not returns null
        if (firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }
//initializing views
        emailtext=(EditText)findViewById(R.id.email);
        passwordtext = (EditText)findViewById(R.id.password);
        register = (Button)findViewById(R.id.register);
        signin=(TextView)findViewById(R.id.signin);
        forgot_password = (TextView)findViewById(R.id.password_forgot);
        progressDialog=new ProgressDialog(this);

       signin.setOnClickListener(this);
        register.setOnClickListener(this);
        forgot_password.setOnClickListener(this);

    }
    private void registeruser(){
        String email = emailtext.getText().toString().trim();
        String password = passwordtext.getText().toString().trim();

        //checking email and password are empty or not
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Enter email adderess",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Enter password",Toast.LENGTH_LONG).show();
            return;
        }
        //if email and password are not empty
        progressDialog.setMessage("sending request...");
        progressDialog.show();

        //creating a user
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       //checking if success
                       if (task.isSuccessful()){
                           finish();
                           startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                       }
                       else {
                           //display some message here
                           Toast.makeText(MainActivity.this,"Registration error",Toast.LENGTH_LONG).show();
                       }
                       progressDialog.dismiss();
                    }
                });
     }


    @Override
    public void onClick(View v) {
        if (v==register)
        registeruser();
        if (v==signin)
       startActivity(new Intent(this,LoginActivity.class));
        if(v==forgot_password){
            startActivity(new Intent(this,ForgotpasswordActivity.class));
        }
    }
}
