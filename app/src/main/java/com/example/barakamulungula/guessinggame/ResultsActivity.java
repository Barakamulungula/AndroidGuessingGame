package com.example.barakamulungula.guessinggame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    TextView resultsTextView;
    Button restartButton;
    ImageView resultImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        resultsTextView = findViewById(R.id.winOrLose);
        restartButton = findViewById(R.id.restart_button);
        resultImageView = findViewById(R.id.emoji_image);

        String name = getIntent().getStringExtra("text_display");
        resultsTextView.setText(name);

        if(getIntent().getStringExtra("WINORLOSE").trim().equals("win")){
            resultImageView.setBackgroundResource(R.drawable.win);
            resultsTextView.setBackgroundColor(getResources().getColor(R.color.greenWinColor));
        }else{
            resultImageView.setBackgroundResource(R.drawable.lose);
            resultsTextView.setBackgroundColor(getResources().getColor(R.color.redColor));
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
