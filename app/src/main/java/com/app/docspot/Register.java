package com.app.docspot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    Button mbtn_reg;
    EditText mid_su;
    EditText mpw_su;
    EditText mname;
    EditText mphone;
    TextView mtv_redirect;

   //  ProgressBar PBar;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mbtn_reg = (Button) findViewById(R.id.btn_reg);
        mname = (EditText) findViewById(R.id.name);
        mid_su = (EditText) findViewById(R.id.id_su);
       mpw_su = (EditText) findViewById(R.id.pw_su);
        mphone = (EditText) findViewById(R.id.phone);
        mtv_redirect = (TextView) findViewById(R.id.tv_redirect);
        firebaseAuth = FirebaseAuth.getInstance();
        //PBar = findViewById(R.id.progressBar);

        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), Register.class));
            finish();
        }


        mbtn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mid_su.getText().toString().trim();
                String password = mpw_su.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mid_su.setError("Please enter email");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mpw_su.setError("Please enter a valid password");
                    return;
                }
                if (password.length() < 8) {
                    mpw_su.setError("Password should be minimum 8 characters");
                    return;
                }
              //  PBar.setVisibility(View.VISIBLE);

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(Register.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Dashboard.class));

                        } else {

                            Toast.makeText(Register.this, "Error! Please try again" + task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

        mtv_redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}




