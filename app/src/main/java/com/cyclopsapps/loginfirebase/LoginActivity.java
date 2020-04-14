package com.cyclopsapps.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText emailId, password;
    Button btnSignIn;
    TextView tvSignUp;
    FirebaseAuth mfirebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mfirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignUp);
        tvSignUp = findViewById(R.id.tvSignIn);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = mfirebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    Toast.makeText(LoginActivity.this, "Has ingresado", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Por favor ingresar", Toast.LENGTH_SHORT).show();
                }
            }
        };

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if (email.isEmpty()){
                    emailId.setError("Por favor ingrese un corre electrónico");
                    emailId.requestFocus();
                }
                else if (pwd.isEmpty()){
                    password.setError("Por favor ingrese su contraseña");
                    password.requestFocus();
                }
                else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Los campos están vacíos", Toast.LENGTH_SHORT).show();

                }
                else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mfirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Error en el ingreso, por favor intente ingresar de nuevo", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent intentHome = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intentHome);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(LoginActivity.this, "Ocurrió un error!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intSignUp);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mfirebaseAuth.addAuthStateListener(authStateListener);
    }
}
