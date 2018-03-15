package com.example.barakamulungula.guessinggame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button submitButton;
    private EditText editText;
    private TextView textView;
    private TextView clueText;

    private int lower = 1;
    private int higher = 100;
    private int random = 21;
    private String display;
    private int tries = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = findViewById(R.id.submit_button);
        editText = findViewById(R.id.guess_editText);
        textView = findViewById(R.id.app_numOfAttempts);
        clueText = findViewById(R.id.clueText);

        onSubmitButtonClick();
    }

    private void clueMessage(String message){
        clueText.setText(message);
        clueText.setVisibility(View.VISIBLE);
    }


    private void onSubmitButtonClick() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    clueText.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(MainActivity.this, NameActivity.class);
                    String input = editText.getText().toString();
                    int guess = Integer.parseInt(input);


                        if(guess > 0 && input.length() > 0){
                            if(guess <= 100) {
                                if(tries > 1) {
                                    checkGuess(guess, intent, input);
                                    editText.setText("");
                                }else {
                                    intent.putExtra("text_display", "You ran out of attempts\nThe correct number was: "+ random+"\nYou Lose!");
                                    random = (int)(Math.random() * (higher-lower)) + lower;
                                    tries = 5;
                                    display = "Attempts: "+tries;
                                    textView.setText(display);
                                    intent.putExtra("WINORLOSE","lose");
                                    startActivity(intent);
                                }
                            }else {
                                clueMessage("Enter a number less than hundred");
                            }
                        }else {
                            clueMessage("Enter a number greater than zero");
                            editText.setText("");

                        }

                }catch (Exception e){
                    clueMessage("Enter input");
                    editText.setText("");
                }
            }
        });
    }

    private void checkGuess(int guess, Intent intent, String input){
            if(guess == random){
                intent.putExtra("text_display", "YOU WON\ncorrect number: "+random);
                display =  "Attempts: "+tries;
                textView.setText(display);
                intent.putExtra("WINORLOSE","win");
                startActivity(intent);
            }else{
                tries--;
                if(guess > random){
                    clueMessage("Enter a lower number");
                    display =  "Attempts: "+tries;
                    textView.setText(display);
                }else {
                    clueMessage("Enter higher number");
                    display = "Attempts: "+tries;
                    textView.setText(display);
                }
            }
        }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        editText.setText("");
    }
}
