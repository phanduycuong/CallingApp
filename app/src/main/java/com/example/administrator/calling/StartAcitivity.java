package com.example.administrator.calling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_acitivity);
        Intent registerIntent = new Intent(StartAcitivity.this, RegisterActivity.class);
        startActivity(registerIntent);
        finish();
    }
}
