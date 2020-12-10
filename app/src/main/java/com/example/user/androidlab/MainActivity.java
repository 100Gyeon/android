package com.example.user.androidlab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    MyHandler myHandler = new MyHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);

        // 스레드를 만들어 실행하는 리스너
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundThread thread = new BackgroundThread();
                thread.start();
            }
        });
    }

    class BackgroundThread extends Thread {
        boolean running = false;
        int value = 0;

        @Override
        public void run() {
            running = true;
            while (running) {
                value += 1;

                Bundle bundle = new Bundle();
                bundle.putInt("value", value);

                Message message = myHandler.obtainMessage();
                message.setData(bundle); // setData로 넣는다

                // BackgroundThread에서 MyHandler에게 bundle을 던져줌
                myHandler.sendMessage(message);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData(); // getData로 뺀다
            int value = bundle.getInt("value");

            // 핸들러 내에서 UI 변경 가능
            textView.setText("현재 값 : " + value);
        }
    }
}
