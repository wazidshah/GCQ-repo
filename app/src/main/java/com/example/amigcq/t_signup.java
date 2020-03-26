package com.example.amigcq;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class t_signup extends AppCompatActivity implements View.OnClickListener {

    ProgressBar progressBar;

    EditText editTextEmail, editTextPassword,tname,dept,tphn;

    private FirebaseAuth mAuth;
    DatabaseReference refff;
    teacher tea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachers_signup);

        editTextEmail = findViewById(R.id.editTextEmailTeach);
        editTextPassword = findViewById(R.id.editTextPasswordTeach);
        progressBar = (ProgressBar) findViewById(R.id.progressbarTeach); //

        tname = findViewById(R.id.editTextFullNameteach);
        dept= findViewById(R.id.editTextDept);
        tphn = findViewById(R.id.editTextNumberteach);



        mAuth = FirebaseAuth.getInstance();

        tea=new teacher();
        refff= FirebaseDatabase.getInstance().getReference().child("teacher");

        findViewById(R.id.buttonSignUpTeach).setOnClickListener(this);     //  findViewById(R.id.buttonSignUp).setOnClickListener(this);             findViewById(R.id.textViewSignup).setOnClickListener(this);
        findViewById(R.id.textViewLoginTeach).setOnClickListener(this);




    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        String phone = tphn.getText().toString().trim();
        String dep = dept.getText().toString().trim();
        String name = tname.getText().toString().trim();

        if (name.isEmpty()) {
            tname.setError("Name is required");
            tname.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            tphn.setError("Phone Number is required");
            tphn.requestFocus();
            return;
        }

        if (phone.length() < 10) {
            tphn.setError("Wrong Number");
            tphn.requestFocus();
            return;
        }
        if (phone.length() > 10) {
            tphn.setError("Wrong Number");
            tphn.requestFocus();
            return;
        }

        if (dep.isEmpty()) {
            dept.setError("Please enter Department");
            dept.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }


        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Minimum length of password should be 6");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()) {
                    finish();

                    String nm=tname.getText().toString().trim();
                    String ph=tphn.getText().toString().trim();
                    String cls=dept.getText().toString().trim();
                    String em=editTextEmail.getText().toString().trim();
                    tea.setName(nm);
                    tea.setNumber(ph);
                    tea.setDep(cls);
                    tea.setEmail(em);
                    refff.push().setValue(tea);
                    Toast.makeText(t_signup.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(t_signup.this,teaProfileActivity.class));

                }else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSignUpTeach:
                registerUser();

                break;

            case R.id.textViewLoginTeach:
                finish();
                startActivity(new Intent(this, t_login.class));
                break;


        }
    }
}
