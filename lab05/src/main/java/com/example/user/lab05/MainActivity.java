package com.example.user.lab05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imageView = findViewById(R.id.img);

        // 애니메이션 획득
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.in);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.move);
                anim.setFillAfter(true); // 애니메이션이 끝난 상태로 유지된다.
                imageView.startAnimation(anim); // 애니메이션 in이 끝나면 move가 시작된다.
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        // 애니메이션 시작
        imageView.startAnimation(anim);
    }

}