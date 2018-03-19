package com.example.barakamulungula.guessinggame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button_submit;
    private EditText editText_userInput;
    private TextView textView_userAttempts;
    private TextView textView_clueText;

    private int randomNumber = 20;
    private String display;
    private int maxGuess = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_submit = findViewById(R.id.submit_button);
        editText_userInput = findViewById(R.id.guess_editText);
        textView_userAttempts = findViewById(R.id.app_numOfAttempts);
        textView_clueText = findViewById(R.id.clueText);

        onSubmitButtonClick();
    }

    /**
     * This method displays a message to give the user hints about the number*/
    private void clueMessage(String message){
        textView_clueText.setText(message);
        textView_clueText.setVisibility(View.VISIBLE);
    }

    /**
     * This method is called when the submit button is clicked. The method checks if the users input is corrects*/
    private void onSubmitButtonClick() {
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    textView_clueText.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(MainActivity.this, NameActivity.class);
                    String input = editText_userInput.getText().toString();
                    int guess = Integer.parseInt(input);

                        if(guess > 0 && input.length() > 0){
                            if(guess <= 100) {
                                if(maxGuess > 1) {
                                    checkGuess(guess, intent);
                                    editText_userInput.setText("");
                                }else {
                                    intent.putExtra("text_display", "You ran out of attempts\nthe winning number was: "+ randomNumber +"\nYou Lose!");
                                    randomNumber = (int)(Math.random() * (100-1)) + 1;
                                    maxGuess = 5;
                                    display = "Attempts: "+ maxGuess;
                                    textView_userAttempts.setText(display);
                                    intent.putExtra("WINORLOSE","lose");
                                    startActivity(intent);
                                }
                            }else {
                                clueMessage("Enter a number less than hundred");
                            }
                        }else {
                            clueMessage("Enter a number greater than zero");
                            editText_userInput.setText("");

                        }

                }catch (Exception e){
                    clueMessage("Enter input");
                    editText_userInput.setText("");
                }
                editText_userInput.setText("");
            }
        });
    }

    private void checkGuess(int guess, Intent intent){
            if(guess == randomNumber){
                intent.putExtra("text_display", "YOU WON\nthe winning was number: "+ randomNumber);
                display =  "Attempts: "+ maxGuess;
                randomNumber = (int)(Math.random() * (100-1)) + 1;
                maxGuess = 5;
                display = "Attempts: "+ maxGuess;
                textView_userAttempts.setText(display);
                intent.putExtra("WINORLOSE","win");
                startActivity(intent);
            }else{
                maxGuess--;
                if(guess > randomNumber){
                    clueMessage("Enter a lower number.");
                    display =  "Attempts: "+ maxGuess;
                    textView_userAttempts.setText(display);
                }else {
                    clueMessage("Enter higher number");
                    display = "Attempts: "+ maxGuess;
                    textView_userAttempts.setText(display);
                }
            }
        }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        editText_userInput.setText("");
    }
}
