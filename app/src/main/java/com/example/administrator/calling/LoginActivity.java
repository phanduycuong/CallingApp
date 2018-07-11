package com.example.administrator.calling;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private EditText mLoginEmail, mLoginPassword;
    private Button mLogin_Btn;

    private FirebaseAuth mAuth;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginEmail = (EditText) findViewById(R.id.editTextEmailLogin);
        mLoginPassword = (EditText) findViewById(R.id.editTextPasswordLogin);
        mLogin_Btn = (Button) findViewById(R.id.buttonLogin);
        mToolbar = (Toolbar) findViewById(R.id.login_toolbar);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Đăng nhập");

        mLogin_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mLoginEmail.getText().toString().trim();
                String password = mLoginPassword.getText().toString().trim();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Vui lòng điều Email và Mật khẩu", Toast.LENGTH_SHORT).show();
                } else
                loginUser(email, password);

            }
        });
    }

    private void loginUser(String email, String password) {
        progressDialog.setMessage("Đang đăng nhập ...");
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intentmain = new Intent(LoginActivity.this, MainActivity.class);
                    intentmain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentmain);
                    finish();
                }else progressDialog.hide();
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
