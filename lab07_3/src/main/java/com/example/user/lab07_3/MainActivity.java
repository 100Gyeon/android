package com.example.user.lab07_3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
    }

    public void clickSet(View view) {
        // set 버튼 클릭 시 SharedPreferences에 값 저장
        SharedPreferences sharedPref = getSharedPreferences("text", Context.MODE_PRIVATE);
        // 데이터를 저장하려면 Editor 클래스의 함수를 이용
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("inputText", editText.getText().toString()); // key, value 형식으로 저장
        editor.putInt("inputInt", 100);
        editor.commit(); // 저장한 데이터를 최종 반영하기 위해 호출
        Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    public void clickGet(View view) {
        // get 버튼 클릭 시 SharedPreferences에서 값 불러오기
        SharedPreferences sharedPref = getSharedPreferences("text", Context.MODE_PRIVATE);
        String inputText = sharedPref.getString("inputText", ""); // 두 번째는 default value
        int inputInt = sharedPref.getInt("inputInt", 0); // inputInt라는 key 값이 없다면 0을 리턴하라
        textView.setText(inputText + " " + inputInt);
    }
}
