package com.example.administrator.calling;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    EditText mDisplayName, mEmail, mPassword;
    Button mCreateBtn;

    private FirebaseAuth mAuth;

    private ProgressDialog mprogressDialog;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        anhxa();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Tạo tài khoản");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Display_name = mDisplayName.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                if (Display_name.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Bạn chưa nhập Tên", Toast.LENGTH_SHORT).show();
                }
                if (email.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Bạn chưa nhập Email", Toast.LENGTH_SHORT).show();
                }
                if (password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Bạn chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
                } else
                    mprogressDialog.setMessage("Vui lòng đợi ...");
                mprogressDialog.show();
                register_Auth(Display_name, email, password);
            }
        });
    }

    private void register_Auth(String display_name, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent MainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(MainIntent);
                    finish();
                } else
                    mprogressDialog.hide();
                    Toast.makeText(RegisterActivity.this, "Dang ky loi ban thu lai", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void anhxa() {
        mDisplayName = (EditText) findViewById(R.id.editTextName);
        mEmail = (EditText) findViewById(R.id.editTextEmail);
        mPassword = (EditText) findViewById(R.id.editTextPassword);
        mCreateBtn = (Button) findViewById(R.id.buttonRegister);
        mAuth = FirebaseAuth.getInstance();
        mprogressDialog = new ProgressDialog(this);
        mToolbar = (Toolbar)findViewById(R.id.register_toolbar);

    }


}