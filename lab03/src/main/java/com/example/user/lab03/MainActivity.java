package com.example.user.lab03;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button vibrationBtn;
    Button systemBeepBtn;
    Button customBeepBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vibrationBtn = findViewById(R.id.btn_vibration);
        systemBeepBtn = findViewById(R.id.btn_system_beep);
        customBeepBtn = findViewById(R.id.btn_custom_sound);

        // 버튼 3개가 하나의 리스너 공유하고 있음
        vibrationBtn.setOnClickListener(this);
        systemBeepBtn.setOnClickListener(this);
        customBeepBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == vibrationBtn) {
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(1000);
        } else if (view == systemBeepBtn) {
            // 스마트폰에 내장된 기본 효과음을 이용하는 방법
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION); // Uri는 Url의 상위 개념
            // Uri 값으로 식별되는 효과음을 재생할 수 있는 Ringtone을 얻어 play() 함수로 재생
            Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
            ringtone.play();
        } else if (view == customBeepBtn) {
            MediaPlayer player = MediaPlayer.create(this, R.raw.fallbackring); // 직접 녹음한 효과음
            player.start();
        }
    }
}