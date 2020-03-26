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

public class s_signup extends AppCompatActivity implements View.OnClickListener {

    ProgressBar progressBar;

    EditText editTextEmail, editTextPassword,sname,sclass,srollno,sphn;
    /*private Spinner sp;
    ArrayAdapter adp;*/

    private FirebaseAuth mAuth;
    DatabaseReference reff;
    student stu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        sname = findViewById(R.id.editTextFullName);
        sclass= findViewById(R.id.editTextClass);
        srollno = findViewById(R.id.editTextRollno);
        sphn = findViewById(R.id.editTextNumber);

        progressBar = (ProgressBar) findViewById(R.id.progressbar); //

        mAuth = FirebaseAuth.getInstance();


        stu=new student();
        reff= FirebaseDatabase.getInstance().getReference().child("student");

        findViewById(R.id.buttonSignUp).setOnClickListener(this);     //  findViewById(R.id.buttonSignUp).setOnClickListener(this);             findViewById(R.id.textViewSignup).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);



        //spinner

/*
        sp=findViewById(R.id.spinnerclass);
        sp.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        ArrayList<String> categories = new ArrayList<>();
        categories.add(0,"Choose Login");
        categories.add("FyBsc");
        categories.add("SyBsc");
        categories.add("TyBsc");
        categories.add("FyBA");
        categories.add("SyBA");
        categories.add("TyBA");
        categories.add("FyCOM");
        categories.add("SyCOM");
        categories.add("TyCOM");
        categories.add("Post Graduation");
        adp = new ArrayAdapter(this, simple_list_item_1,categories);
        adp.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp.setAdapter(adp);*/




    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        String phone = sphn.getText().toString().trim();
        String cla = sclass.getText().toString().trim();
        String name = sname.getText().toString().trim();
        String roll = srollno.getText().toString().trim();

        if (name.isEmpty()) {
            sname.setError("Name is required");
            sname.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            sphn.setError("Phone Number is required");
            sphn.requestFocus();
            return;
        }

        if (phone.length() < 10) {
            sphn.setError("Wrong Number");
            sphn.requestFocus();
            return;
        }
        if (phone.length() > 10) {
            sphn.setError("Wrong Number");
            sphn.requestFocus();
            return;
        }

        if (cla.isEmpty()) {
            sclass.setError("Please enter class");
            sclass.requestFocus();
            return;
        }

        if (roll.isEmpty()) {
            srollno.setError("Enter Roll no");
            srollno.requestFocus();
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

                    String nm=sname.getText().toString().trim();
                    String ph=sphn.getText().toString().trim();
                    String cls=sclass.getText().toString().trim();
                    String rl=srollno.getText().toString().trim();
                    String em=editTextEmail.getText().toString().trim();
                    stu.setName(nm);
                    stu.setNumber(ph);
                    stu.setClas(cls);
                    stu.setRoll(rl);
                    stu.setEmail(em);
                    reff.push().setValue(stu);
                    Toast.makeText(s_signup.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();


                    startActivity(new Intent(s_signup.this,ProfileActivity.class));

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
            case R.id.buttonSignUp:
                registerUser();

                break;

            case R.id.textViewLogin:
                finish();
                startActivity(new Intent(this, s_login.class));
                break;


        }
    }
}
