package com.example.recipebox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView foodDescription;
    ImageView foodImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.appBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        foodDescription = (TextView)findViewById(R.id.detailProcedure);
        foodImage = (ImageView)findViewById(R.id.detailImg);

        Bundle mBundle = getIntent().getExtras();

        if(mBundle != null){

            foodImage.setImageResource(mBundle.getInt("Image"));
            foodDescription.setText(mBundle.getString("Instruction"));
            getSupportActionBar().setTitle(mBundle.getString("Name"));
        }
    }
}
