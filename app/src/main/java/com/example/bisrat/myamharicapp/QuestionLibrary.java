package com.example.bisrat.myamharicapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Button;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;



public class QuestionLibrary {

    private int lessonNum ;     // holds the lesson selected from mainActivity
    private ArrayList<Sentence> sentences = new ArrayList<Sentence>();
    boolean  parseAgain = true;


    private ArrayList<String> mQuestions =  new ArrayList<String>();
    private ArrayList<String> mCorrectAnswers =  new ArrayList<String>();
    private ArrayList<ArrayList<String>> mChoices = new ArrayList<ArrayList<String>>();


    ArrayList<String> englishOnly = new ArrayList<String>();
    ArrayList<String> amharicOnly = new ArrayList<String>();
    ArrayList<String> transliterationOnly = new ArrayList<String>();

      public String getQuestion( Activity activity, int questionNum){

        if ( parseAgain) {
            parseXML(activity);
            //Log.i("DEBUG", "sentencesSize =  " + String.valueOf(sentences.size()));
            for (Sentence sentence : sentences) {

                englishOnly.add(sentence.english);
                amharicOnly.add(sentence.amharic);
                transliterationOnly.add(sentence.transliteration);
            }
            //Log.i("DEBUG", "transliterationSize =  " + String.valueOf(transliterationOnly.size()));
        }
        parseAgain = false;


        int  listSize = englishOnly.size();
        Random rand  = new Random();
        int randomIndex;


       mQuestions.clear();
       mChoices.clear();
        mCorrectAnswers.clear();
        for (int i = 0 ; i < 15; i++ ) {

            int minIndex = 5;
            randomIndex = minIndex + rand.nextInt(listSize - minIndex);
            Log.i("DEBUG", " random index " + randomIndex + " listSize " + listSize);

            mQuestions.add(englishOnly.get(randomIndex));

            Log.i("DEBUG", "mQuestions =  " + String.valueOf(mQuestions.size()));
            mCorrectAnswers.add(transliterationOnly.get(randomIndex));
            Log.i("DEBUG", "mCorrectAnswers.size()=  " + String.valueOf(mCorrectAnswers.size()));
            ArrayList<String> choiceRow = new ArrayList<String>();
            //for (int j = 0; j < 4; j++ ) {


           // Log.i("DEBUG", "ch0 =  " + String.valueOf(choiceRow.get(0)));
            choiceRow.add(transliterationOnly.get(randomIndex));
            choiceRow.add(transliterationOnly.get(randomIndex - 1));
            choiceRow.add(transliterationOnly.get(randomIndex - 2));
            choiceRow.add(transliterationOnly.get(randomIndex - 3 ));
            //Log.i("DEBUG", "ch0 =  " + String.valueOf(choiceRow.get(0)));
            //}
            mChoices.add(choiceRow);


            //Log.i("DEBUG",  "mQuestion.size()=  "+ String.valueOf(mQuestions.size()));
            Collections.shuffle(choiceRow);
        }
            String question = "Translate " + "\n" + mQuestions.get(questionNum);
            Log.i("DEBUG", "question= " + question);


          return question;
    }

    public String getChoice1(int questionNum){

        String ch0 = mChoices.get(questionNum).get(0);
        Log.i("DEBUG", " mChoice0= " + ch0);
        return ch0;
    }
    public String getChoice2(int questionNum){
        String ch1 = mChoices.get(questionNum).get(1);
        Log.i("DEBUG", " mChoice1= " + ch1);
        return ch1;
    }
    public String getChoice3(int questionNum){
        String ch2 = mChoices.get(questionNum).get(2);
        Log.i("DEBUG", " mChoice2= " + ch2);
        return ch2;
    }
    public String getChoice4(int questionNum){
        String ch3= mChoices.get(questionNum).get(3);
        Log.i("DEBUG", " mChoice3= " + ch3);
        return ch3;
    }
    public String getCorrectAnswer(int questionNum){
        String answer = mCorrectAnswers.get(questionNum);
        Log.i("DEBUG", " answer= " + answer);
        return answer;
    }
    public void setLessonNum( int lessonNum){
        this.lessonNum = lessonNum;

    }




    private void parseXML(Activity activity) {
        XmlPullParserFactory parserFactory;
        try {
            parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            // get the data to parse in to inputStream
            InputStream is = activity.getAssets().open("data.xml");
            // not using namespaces
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);

            parser.setInput(is, null);
            processParsing(parser);
        } catch (XmlPullParserException e) {

        } catch (IOException e) {

        }

    }
    private List<Sentence> processParsing(XmlPullParser parser) throws IOException, XmlPullParserException {

        int eventType = parser.getEventType(); // returns the event type ex doc start,tag start etc
        Sentence currentSentence = null;
        boolean currentLesson = false;
        // keep parsing until we hit the end of file
        while (eventType != XmlPullParser.END_DOCUMENT) {
            String eltName = null;
            // if the event is a start tag, grab the element tag check if its lesson
            // compares the lesson number and loads the sentences for that lesson
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    eltName = parser.getName(); // returns tag name

                    if (eltName.equals("lesson")) {
                        currentLesson = parser.getAttributeValue(null,
                                "id").equals(Integer.toString(lessonNum));

                    } else if(currentLesson) {
                        if ("sentence".equals(eltName)) {
                            currentSentence = new Sentence();
                            sentences.add(currentSentence);
                        } else if (currentSentence != null) {
                            if ("english".equals(eltName)) {
                                currentSentence.english = parser.nextText();
                            } else if ("amharic".equals(eltName)) {
                                currentSentence.amharic = parser.nextText();
                            } else if ("transliteration".equals(eltName)) {
                                currentSentence.transliteration = parser.nextText();
                            }
                        }
                    }
                    break;
            }
            eventType = parser.next(); // advances parser

        }

        return sentences;
    }




}


