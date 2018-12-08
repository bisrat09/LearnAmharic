package com.example.bisrat.myamharicapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    private int lessonNum = 0; // holds the lesson selected from mainActivity
    private String actionBarTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.menu_title);
        lessonNum = getIntent().getExtras().getInt("LESSON_NUMBER");
        actionBarTitle = getIntent().getStringExtra("actionBarTitle");

    }
    public void launchQuizActivity(View view){

        Intent intent = new Intent (this,QuizActivity.class);
        intent.putExtra("LESSON_NUMBER", lessonNum);
        intent.putExtra("actionBarTitle",actionBarTitle);
        startActivity(intent);
    }
    public void launchLessonActivity(View view){
        Intent intent = new Intent (this,LessonActivity.class);
        intent.putExtra("LESSON_NUMBER", lessonNum);
        intent.putExtra("actionBarTitle",actionBarTitle);
        startActivity(intent);
    }
}
