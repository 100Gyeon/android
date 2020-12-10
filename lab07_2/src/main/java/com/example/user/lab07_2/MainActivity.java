package com.example.user.lab07_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText contentView;
    Button btn;

    // 퍼미션 부여 여부
    boolean fileReadPermission;
    boolean fileWritePermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentView = findViewById(R.id.content);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);

        // permission 체크
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            fileReadPermission = true;
        }
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            fileWritePermission = true;
        }

        // permission 부여 안 될 경우 permission 요청
        // 실행 시 onCreate에서 요청
        // 요청에 대해서 임의로 번호 할당하고, 이름 붙인 것이 200
        if(!fileReadPermission || !fileWritePermission) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
        }
    }

    // permission 부여 요청 결과 확인
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 200 && grantResults.length > 0) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fileReadPermission = true;
            }
            if(grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                fileWritePermission = true;
            }
        }
    }

    @Override
    public void onClick(View view) {
        String content = contentView.getText().toString();

        // permission이 부여되어 있다면
        if(fileReadPermission && fileWritePermission) {
            FileWriter writer;
            try {
                // 외부 저장 공간 root 하위에 myApp이라는 폴더 경로 획득
                String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myApp";
                File dir = new File(dirPath);
                if(!dir.exists()) { // 폴더가 없다면 새로 만들어 준다.
                    dir.mkdir();
                }

                // myApp 폴더 밑에 myfile.txt 파일 지정
                File file = new File(dir + "/myfile.txt");
                if(!file.exists()) { // 파일이 없다면 새로 만들어 준다.
                    file.createNewFile();
                }

                // file write
                writer = new FileWriter(file, true);
                writer.write(content);
                writer.flush();
                writer.close();

                // 결과 확인을 위한 Activity 실행
                Intent intent = new Intent(this, ReadFileActivity.class);
                startActivity(intent);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showToast("permission이 부여되지 않아 실행이 안 됩니다.");
        }
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
