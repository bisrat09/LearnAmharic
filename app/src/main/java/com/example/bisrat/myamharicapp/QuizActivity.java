package com.example.bisrat.myamharicapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private QuestionLibrary mQuestionLibrary = new QuestionLibrary();
    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;
    private Button mButtonQuit;
    private int lessonNum;
    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mScoreView = (TextView)findViewById(R.id.score);
        mQuestionView =(TextView)findViewById(R.id.question);
        mButtonChoice1 =(Button)findViewById(R.id.choice1);
        mButtonChoice2 =(Button)findViewById(R.id.choice2);
        mButtonChoice3 =(Button)findViewById(R.id.choice3);
        mButtonChoice4 =(Button)findViewById(R.id.choice4);
       // mButtonQuit= (Button)findViewById(R.id.quitButton);
        lessonNum = getIntent().getExtras().getInt("LESSON_NUMBER");
        String mactionBarTitle = getIntent().getStringExtra("actionBarTitle");
       // mQuestionLibrary.separateSentences(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(mactionBarTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        updateQuestion();

        // start button listener for button 1
        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonChoice1.getText() == mAnswer){
                    mScore = mScore + 1 ;
                    updateScore(mScore);

                        updateQuestion();

                    Toast.makeText(QuizActivity.this,"Correct",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizActivity.this,"Incorrect, try again",
                            Toast.LENGTH_SHORT).show();

                       updateQuestion();

                }
            }
        });
        // end of button listner for button1
        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonChoice2.getText() == mAnswer){
                    mScore++;
                    updateScore(mScore);
                    Toast.makeText(QuizActivity.this,"Correct",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizActivity.this,"Incorrect, try again",
                            Toast.LENGTH_SHORT).show();

                        updateQuestion();

                }
            }
        });
        // end of button listner for button2

        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonChoice3.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);

                      updateQuestion();

                    Toast.makeText(QuizActivity.this,"Correct",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizActivity.this,"Incorrect, try again",
                            Toast.LENGTH_SHORT).show();

                        updateQuestion();

                }
            }
        });

        // end of button listner for button3

        mButtonChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonChoice4.getText() == mAnswer){
                    mScore++;
                    updateScore(mScore);

                        updateQuestion();

                    Toast.makeText(QuizActivity.this,"Correct",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizActivity.this,"Incorrect, try Again" ,
                            Toast.LENGTH_SHORT).show();

                        updateQuestion();

                }
            }
        });


        // end of button listner for button4


    }
    private void updateQuestion(){
        if (mQuestionNumber < 15) {
            mQuestionLibrary.setLessonNum(this.lessonNum);
            mQuestionView.setText(mQuestionLibrary.getQuestion(this, mQuestionNumber));
            mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
            mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
            mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));
            mButtonChoice4.setText(mQuestionLibrary.getChoice4(mQuestionNumber));

            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
        }

    }

    private void updateScore(int point ){
        mScoreView.setText("" + mScore);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
