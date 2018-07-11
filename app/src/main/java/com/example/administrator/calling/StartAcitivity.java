package com.example.administrator.calling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartAcitivity extends AppCompatActivity {
    private Button mButtonLogin, mButtonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_acitivity);

        mButtonLogin = (Button) findViewById(R.id.buttonLogin_start);
        mButtonRegister = (Button) findViewById(R.id.buttonregister_start);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == mButtonLogin){
                    Intent IntentLogin = new Intent(StartAcitivity.this, LoginActivity.class);
                    startActivity(IntentLogin);
                }
            }
        });
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == mButtonRegister){
                    Intent registerIntent = new Intent(StartAcitivity.this, RegisterActivity.class);
                    startActivity(registerIntent);
                }
            }
        });
    }
}
