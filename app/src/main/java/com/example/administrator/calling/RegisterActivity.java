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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private EditText mDisplayName, mEmail, mPassword;
    private Button mCreateBtn;

    private FirebaseAuth mAuth;
    private DatabaseReference mdatabase;

    private ProgressDialog mprogressDialog;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mToolbar = (Toolbar) findViewById(R.id.register_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Đăng ký");
        mDisplayName = (EditText) findViewById(R.id.editTextName);
        mEmail = (EditText) findViewById(R.id.editTextEmailLogin);
        mPassword = (EditText) findViewById(R.id.editTextPasswordLogin);
        mCreateBtn = (Button) findViewById(R.id.buttonRegister);
        mAuth = FirebaseAuth.getInstance();
        mprogressDialog = new ProgressDialog(this);


        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Display_name = mDisplayName.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(Display_name)){
                    Toast.makeText(RegisterActivity.this, "Cần nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }else
                register_Auth(Display_name, email, password);
            }
        });
    }

    private void register_Auth(final String display_name, String email, String password) {
        mprogressDialog.setMessage("Vui lòng đợi ...");
        mprogressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = current_user.getUid();
                    mdatabase = FirebaseDatabase.getInstance().getReference().child("User").child(uid);
                    HashMap<String, String> userMap = new HashMap<>();
                    userMap.put("name", display_name);
                    userMap.put("status" , "Yêu màu tím, thích màu hồng, ghét sự giả dối");
                    userMap.put("image", "default");
                    userMap.put("thumb_image", "default");
                    mdatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                mprogressDialog.dismiss();
                                Intent MainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                                MainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(MainIntent);
                                finish();
                            }
                        }
                    });

                } else mprogressDialog.hide();
//                Toast.makeText(RegisterActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();

            }
        });
    }



}