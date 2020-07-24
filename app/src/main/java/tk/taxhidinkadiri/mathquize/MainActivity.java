package tk.taxhidinkadiri.mathquize;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score = 0;
    int numberOfQuestions = 0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;
    int a, b, d;

    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");

        new CountDownTimer(600000,1000) {

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void chooseAnswer(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTextView.setText("Correct!");
            score++;
        } else {
            resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
        //Toast.makeText(this, "True Answer="+Integer.valueOf(a + b - d), Toast.LENGTH_SHORT).show();
    }

    public void start(View view) {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }

    public void newQuestion() {
        Random rand = new Random();

        a = rand.nextInt(6);
        b = rand.nextInt(6);
        d=rand.nextInt(6);


        while ( (a + b - d) < 0 ) {
            a = 1 + rand.nextInt(5);
            b = 1 + rand.nextInt(5);
            d = 1 + rand.nextInt(5);

        }

        sumTextView.setText(MessageFormat.format("{0} + {1} - {2}", Integer.toString(a), Integer.toString(b), Integer.toString(d)));

        locationOfCorrectAnswer = rand.nextInt(4);
        Log.i("index true vlue", "newQuestion: "+"true ans. index="+locationOfCorrectAnswer);

        answers.clear();



        for (int i=0; i<4; i++) {
            if (i == locationOfCorrectAnswer) {

//
//                while ( (a + b - d) < 0 ) {
//                    a = 1 + rand.nextInt(5);
//                    b = 1 + rand.nextInt(5);
//                    d = 1 + rand.nextInt(5);
//
//                }



//                sumTextView.setText(MessageFormat.format("{0} - {1} - {2}", Integer.toString(a), Integer.toString(b), Integer.toString(d)));
                answers.add(i, a + b - d);
                Log.i("index right", "newQuestion: "+i+" "+Integer.valueOf(a - b - d)+"  "+ Integer.valueOf(a)+ "  "+Integer.valueOf(b)+"   "+Integer.valueOf(d));
//                Toast.makeText(this, "True Answer="+Integer.valueOf(a + b - d), Toast.LENGTH_SHORT).show();

                // Toast.makeText(this, "5= "+Integer.toString(a) + " - " + Integer.toString(b) + " - " + Integer.toString(d), Toast.LENGTH_SHORT).show();
                // Log.i("a - b - d", "newQuestion: " + "5= " + Integer.toString(a) + " - " + Integer.toString(b) + " - " + Integer.toString(d));

            }

            else {
                int wrongAnswer = rand.nextInt(11);

                while (wrongAnswer == (a + b - d)) {
                    wrongAnswer = rand.nextInt(11);
                }
                Log.i("index wrong", "newQuestion: "+i+"  "+Integer.valueOf(a - b - d) +"  "+Integer.valueOf(wrongAnswer) +" "+Integer.valueOf(a)+"  "+Integer.valueOf(b)+"   "+Integer.valueOf(d) );
                answers.add(i,wrongAnswer);
            }



        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);
        goButton = findViewById(R.id.goButton);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
    }
}

