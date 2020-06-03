package com.example.recipebox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    public static MainActivity fa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fa = this;
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),Main2Activity.class));
            finish();
        }
    }
    public void openLog(View view){
        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
    }
    public void openSign(View view){
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
    }
}
