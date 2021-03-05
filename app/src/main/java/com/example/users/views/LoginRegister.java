package com.example.users.views;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.users.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginRegister extends AppCompatActivity {
    EditText Email;
    EditText Password;
    Button login;
    Button register;
    Button reset;

    FirebaseAuth firebaseAuth;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginregister);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            Intent i = new Intent(this, UserListActivity.class);
            startActivity(i);
            finish();
        }



        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        reset = findViewById(R.id.reset);

        reset.setOnClickListener(v ->{
            Intent i = new Intent(this, ResetPassword.class);
            startActivity(i);
        });

        register.setOnClickListener(v -> {
            String email = Email.getText().toString();
            String password = Password.getText().toString();
            if(email.length()>0 && password.length()>0){
                //loginRegisterViewModel.register(email,password);
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if(task.isSuccessful()) {
                                Toast.makeText(this,"User Registered",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(this, UserListActivity.class);
                                startActivity(i);
                                finish();
                            }else{
                                Toast.makeText(this,"Registration failed"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
            }else{
                Toast.makeText(getApplicationContext(),"invalid Credentials",Toast.LENGTH_SHORT).show();
            }
        });
        login.setOnClickListener(v -> {
            String email = Email.getText().toString();
            String password = Password.getText().toString();
            if(email.length()>0 && password.length()>0){
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if(task.isSuccessful()){
                                Toast.makeText(this,"User Registered",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(this, UserListActivity.class);
                                startActivity(i);
                                finish();
                            }else{
                                Toast.makeText(this,"Login failed"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
            }else{
                Toast.makeText(getApplicationContext(),"invalid Credentials",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
