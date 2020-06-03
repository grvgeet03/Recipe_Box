package com.example.recipebox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    EditText mFullName,mEmail,mPassword,mPhNumber;
    Button mSubmitBtn;
    FirebaseAuth fAurth;
    ProgressBar pBar;
    FirebaseFirestore fStore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.username2);
        mPhNumber = findViewById(R.id.phNumber);
        mPassword = findViewById(R.id.password2);
        mSubmitBtn = findViewById(R.id.submit);
        pBar = findViewById(R.id.progressBar);

        fAurth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        if(fAurth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(),Main2Activity.class));
            finish();
        }

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString().trim();
                String pass = mPassword.getText().toString().trim();
                final String name = mFullName.getText().toString().trim();
                final String number = mPhNumber.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    mFullName.setError("Name is Required");
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(pass)){
                    mPassword.setError("Password is Required");
                }

                if(TextUtils.isEmpty(number)){
                    mPhNumber.setError("Mobile Number is Required");
                }

                if (pass.length()<6){
                    mPassword.setError("Password should be atleast 6 Characters long");
                }

                if (number.length()<9){
                    mPhNumber.setError("Mobile no. is not correct");
                }

                pBar.setVisibility(View.VISIBLE);
                fAurth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignupActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
                            userId = fAurth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userId);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fName",name);
                            user.put("email",email);
                            user.put("phNumber",number);
                            documentReference.set(user);
                            startActivity(new Intent(getApplicationContext(),Main2Activity.class));
                            MainActivity.fa.finish();
                            finish();
                        }else {
                            Toast.makeText(SignupActivity.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            pBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }
}
