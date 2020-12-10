package com.example.user.lab07_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadFileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_file);

        TextView textView = findViewById(R.id.fileResult);

        // 항상 Environment.getExternalStorageDirectory().getAbsolutePath() 써서 경로 획득
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/myApp/myfile.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuffer buffer = new StringBuffer();
            String line;
            while((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            textView.setText(buffer.toString());
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}