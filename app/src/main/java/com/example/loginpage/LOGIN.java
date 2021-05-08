package com.example.loginpage;

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

public class LOGIN extends AppCompatActivity {
    EditText mfn,me,mpw,mp;
    Button ml;
    TextView mc;
    FirebaseAuth fAuth;
    ProgressBar mpb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_o_g_i_n);

        me = findViewById(R.id.email);
        mpw = findViewById(R.id.password);
        ml = findViewById(R.id.login);
        mc= findViewById(R.id.createtext);
        fAuth = FirebaseAuth.getInstance();
        mpb = findViewById(R.id.progressbar);

        ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = me.getText().toString().trim();
                String password = mpw.getText().toString().trim();


                if (TextUtils.isEmpty(email)) {
                    me.setError("E-MAIL IS REQUIRED");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mpw.setError("PASSWORD IS REQUIRED");
                    return;
                }
                if (password.length() < 6) {
                    mpw.setError("PASSWORD LENGTH SHOULD BE ATLEAST 6");
                    return;
                }

                mpb.setVisibility(ProgressBar.VISIBLE);

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(LOGIN.this,"LOGGED IN SUCCESSFULLY!!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(LOGIN.this,"ERROR!!!!!!!!!!!!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });


        mc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),REGISTER.class));
            }
        });



    }
}