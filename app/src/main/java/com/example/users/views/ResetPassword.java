package com.example.users.views;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.users.R;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {


    ProgressBar progressBar;
    EditText email ;
    Button btnResetPassword;
    Button btnBack ;


    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        progressBar = findViewById(R.id.progressBar_reset);
        email = findViewById(R.id.email_reset);
        btnResetPassword = findViewById(R.id.btn_reset_password);
        btnBack = findViewById(R.id.btn_back);



        firebaseAuth = FirebaseAuth.getInstance();

        btnBack.setOnClickListener(v -> this.finish());

        btnResetPassword.setOnClickListener(v -> {
            String userEmail = email.getText().toString().trim();

            if (TextUtils.isEmpty(userEmail)) {
                Toast.makeText(this, "Enter your register email id", Toast.LENGTH_SHORT).show();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            //reset password you will get a mail
            firebaseAuth.sendPasswordResetEmail(userEmail)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }

                        progressBar.setVisibility(View.GONE);
                    });


        });

    }
}
