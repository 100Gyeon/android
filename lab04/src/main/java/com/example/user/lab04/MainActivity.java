package com.example.user.lab04;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    TextView bellTextView;
    TextView labelTextView;
    CheckBox repeatCheckView;
    CheckBox vibrateCheckView;
    Switch switchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bellTextView = findViewById(R.id.bell_name);
        labelTextView = findViewById(R.id.label);
        repeatCheckView = findViewById(R.id.repeatCheck);
        vibrateCheckView = findViewById(R.id.vibrate);
        switchView = findViewById(R.id.onOff);

        // 리스너 등록
        bellTextView.setOnClickListener(this);
        labelTextView.setOnClickListener(this);
        repeatCheckView.setOnCheckedChangeListener(this);
        vibrateCheckView.setOnCheckedChangeListener(this);
        switchView.setOnCheckedChangeListener(this);
    }

    private void showToast(String message){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onClick(View view) {
        if (view == bellTextView) {
            showToast("bell text click event...");
        } else if (view == labelTextView) {
            showToast("label text click event...");
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == repeatCheckView) {
            showToast("repeat checkbox is " + isChecked);
        } else if (buttonView == vibrateCheckView) {
            showToast("vibrate checkbox is " + isChecked);
        } else if (buttonView == switchView) {
            showToast("switch is " + isChecked);
        }
    }
}
