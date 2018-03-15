package com.example.barakamulungula.guessinggame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NameActivity extends AppCompatActivity {

    TextView textViewEnd;
    Button restartButton;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        textViewEnd = findViewById(R.id.winOrLose);
        restartButton = findViewById(R.id.restart_button);
        imageView = findViewById(R.id.emoji_image);

        String name = getIntent().getStringExtra("text_display");
        textViewEnd.setText(name);

        if(getIntent().getStringExtra("WINORLOSE").trim().equals("win")){
            imageView.setBackgroundResource(R.drawable.win);
            textViewEnd.setBackgroundColor(getResources().getColor(R.color.greenWinColor));
        }else{
            imageView.setBackgroundResource(R.drawable.lose);
            textViewEnd.setBackgroundColor(getResources().getColor(R.color.redColor));
        }

        restartApp();
    }

    private void restartApp() {
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
