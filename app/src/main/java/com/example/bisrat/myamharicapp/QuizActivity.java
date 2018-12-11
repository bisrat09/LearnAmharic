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
                    Toast.makeText(QuizActivity.this,"Correct",
                            Toast.LENGTH_SHORT).show();
                    mScore = mScore + 1 ;
                    updateScore(mScore);

                    updateQuestion();


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
                    Toast.makeText(QuizActivity.this,"Correct",
                            Toast.LENGTH_SHORT).show();
                    mScore++;
                    updateScore(mScore);
                    updateQuestion();

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
                    Toast.makeText(QuizActivity.this,"Correct",
                            Toast.LENGTH_SHORT).show();
                    mScore = mScore + 1;
                    updateScore(mScore);

                      updateQuestion();


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
                    Toast.makeText(QuizActivity.this,"Correct",
                            Toast.LENGTH_SHORT).show();
                    mScore++;
                    updateScore(mScore);
                    updateQuestion();
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
        //int listIndex = (mQuestionNumber % 15);

       // if (mQuestionNumber < 15) {
       // int correctIndex = mQuestionNumber;
            mQuestionLibrary.setLessonNum(this.lessonNum);
        int correctIndex = mQuestionLibrary.getRandomQuestionIndex(this);
            mQuestionView.setText(mQuestionLibrary.getQuestion(correctIndex));
            mButtonChoice1.setText(mQuestionLibrary.getChoice1(correctIndex));
            mButtonChoice2.setText(mQuestionLibrary.getChoice2(correctIndex));
            mButtonChoice3.setText(mQuestionLibrary.getChoice3(correctIndex));
            mButtonChoice4.setText(mQuestionLibrary.getChoice4(correctIndex));

            mAnswer = mQuestionLibrary.getCorrectAnswer(correctIndex);
            mQuestionNumber++;
      //  }

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
