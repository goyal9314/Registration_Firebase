package com.example.registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotpasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText email;
    private Button emailsend;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = (EditText)findViewById(R.id.forgot_password_email);
        emailsend=(Button)findViewById(R.id.email_send);

        progressDialog =new ProgressDialog(this);

        firebaseAuth=FirebaseAuth.getInstance();

        emailsend.setOnClickListener(this);
}
private void emaillinksend(){
        String emailtext = email.getText().toString().trim();

        if (TextUtils.isEmpty(emailtext)){
            Toast.makeText(this,"Enter email id",Toast.LENGTH_LONG).show();
        }
        progressDialog.setMessage("Sending request...");
        progressDialog.show();

        firebaseAuth.sendPasswordResetEmail(emailtext).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotpasswordActivity.this,"Email sent",Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));

                }
                else {
                    Toast.makeText(ForgotpasswordActivity.this,"Request error",Toast.LENGTH_LONG).show();
                }
            }
        });


}

    @Override
    public void onClick(View v) {
        emaillinksend();

    }
}
