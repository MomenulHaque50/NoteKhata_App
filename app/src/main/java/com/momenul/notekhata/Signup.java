package com.momenul.notekhata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    EditText name,email,pass,cpass;
    FirebaseAuth auth;
    ProgressDialog pd ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name=findViewById(R.id.signup_full_name_edittext);
        email=findViewById(R.id.signup_email_edittext);
        pass=findViewById(R.id.signup_password_edittext);
        cpass=findViewById(R.id.signup_confirm_password);
        auth=FirebaseAuth.getInstance();
        pd=new ProgressDialog(Signup.this);
        pd.setMessage("Please wait...");
        pd.setCancelable(false);
        findViewById(R.id.signup_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().isEmpty()){
                    name.setError("Fill it");
                    return ;
                }
                if(email.getText().toString().isEmpty()){
                    email.setError("Fill it");
                    return ;
                }
                if(pass.getText().toString().isEmpty()){
                    pass.setError("Fill it");
                    return ;
                }
                if(cpass.getText().toString().isEmpty()){
                    cpass.setError("Fill it");
                    return ;
                }

                if(!pass.getText().toString().equals(cpass.getText().toString())){
                    pass.setError("Didn't match");
                    cpass.setError("Didn't match");
                    return;
                }
                pd.show();

                auth.createUserWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(Signup.this,Home.class));
                            finish();
                        }else{
                            Toast.makeText(Signup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        pd.dismiss();
                    }
                });



            }
        });
        findViewById(R.id.signup_signin_textview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup.this,Login.class));
                finish();
            }
        });
    }
}