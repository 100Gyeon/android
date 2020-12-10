package com.example.user.lab07_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView prevText;
    TextView prevInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        
        prevText = findViewById(R.id.prevText);
        prevInt = findViewById(R.id.prevInt);

        SharedPreferences sharedPref = getSharedPreferences("text", Context.MODE_PRIVATE);
        String res1 = sharedPref.getString("inputText", "");
        int res2 = sharedPref.getInt("inputInt", 0);
        prevText.setText(res1);
        prevInt.setText(String.valueOf(res2));
    }
}