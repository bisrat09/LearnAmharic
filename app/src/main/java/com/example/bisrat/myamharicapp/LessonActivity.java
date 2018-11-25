package com.example.bisrat.myamharicapp;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import android.media.MediaPlayer;
//  displays the details of the a particular lesson in a separate screen

public class LessonActivity extends AppCompatActivity {

    private int lessonNum = 0;
    private ListView listView;
    private LessonAdapter lAdapter;
    ArrayList<Sentence> sentences = new ArrayList<Sentence>();
    ArrayList<Sentence> sentenceList = new ArrayList<Sentence>();

// for audio purposes
    ArrayList<String> arrayList;
    MediaPlayer mPlayer ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);


        ActionBar actionBar = getSupportActionBar();
        ListView mDetailTV = (ListView) findViewById(R.id.sentences_list);

        // get data from prev activity when item of the
        // listview is clicked using intent
        Intent intent = getIntent();
        String mactionBarTitle =intent.getStringExtra("actionBarTitle");
        lessonNum = getIntent().getExtras().getInt("LESSON_NUMBER");
        arrayList = new ArrayList<String>();

        // set action bar title
        actionBar.setTitle(mactionBarTitle);

        // parse the data and load it to arrayList
        parseXML();
        // make a list for each type of sentence
        ArrayList<String> englishOnly = new ArrayList<>();
        ArrayList<String> amharicOnly = new ArrayList<>();
        ArrayList<String> translitrationOnly = new ArrayList<>();
        final ArrayList<String> audioOnly = new ArrayList<>();


        Field[] fields = R.raw.class.getFields();

        for ( int i = 0; i < fields.length; i++ ){
            String audioName = fields[i].getName();

            if (lessonNum == 1) {

                if ( audioName.startsWith("grt")){
                    audioOnly.add(audioName);
                }
            }else if (lessonNum == 2) {
                if ( audioName.startsWith("exp")){
                    audioOnly.add(audioName);
                }
            }else if (lessonNum == 3) {
                if ( audioName.startsWith("num")){
                    audioOnly.add(audioName);
                }
            }else if (lessonNum == 4) {
                if ( audioName.startsWith("dir")){
                    audioOnly.add(audioName);
                }
            }else if (lessonNum == 5) {
                if ( audioName.startsWith("tra")){
                    audioOnly.add(audioName);
                }
            }else if (lessonNum == 6) {
                if ( audioName.startsWith("tym")){
                    audioOnly.add(audioName);
                }
            }else if (lessonNum == 7) {
                if ( audioName.startsWith("day")){
                    audioOnly.add(audioName);
                }
            }else if (lessonNum == 8) {
                if ( audioName.startsWith("cloz")){
                    audioOnly.add(audioName);
                }
            }else if (lessonNum == 9) {
                if ( audioName.startsWith("fud")){
                    audioOnly.add(audioName);
                }
            }else if (lessonNum == 10) {
                if ( audioName.startsWith("prep")){
                    audioOnly.add(audioName);
                }
            }else if (lessonNum == 11) {
                if ( audioName.startsWith("verb")){
                    audioOnly.add(audioName);
                }
            }else if (lessonNum == 12) {
                if ( audioName.startsWith("fam")){
                    audioOnly.add(audioName);
                }
            }
             else if(lessonNum == 13) {
                if ( audioName.startsWith("wea")){
                    audioOnly.add(audioName);
                }
            }

        }


        // populate all english , amharic and audio lists
        for (Sentence sentence : sentences) {

            englishOnly.add(sentence.english);
            amharicOnly.add(sentence.amharic);
            //translitrationOnly.add(sentence.transliteration);
            //audioOnly.add(sentence.audioFile);
        }


        for (int i =0; i < englishOnly.size(); i++){
            Sentence sent = new Sentence(englishOnly.get(i), amharicOnly.get(i)
                    ,audioOnly.get(i));
           // audioOnly.add (fields[i].getName());

           // Log.i("DEBUG" ,"audioOnlySize"+ String.valueOf(audioOnly.size()));
            //bind all strings in an array
            sentenceList.add(sent);
        }


        // load the list to adapter
        lAdapter = new LessonAdapter(this,sentenceList);

        // sets the view to the adapter
        mDetailTV.setAdapter(lAdapter);


        // on click listener
        mDetailTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                // audio prefix according to lesson number
                String audioPrefix = "";
                if (lessonNum == 1){
                    audioPrefix = "grt";
                }else if ( lessonNum == 2){
                    audioPrefix = "exp";
                }else if ( lessonNum == 3){
                    audioPrefix = "num";
                }else if ( lessonNum == 4){
                    audioPrefix = "dir";
                }else if ( lessonNum == 5){
                    audioPrefix = "tra";
                }else if ( lessonNum == 6){
                    audioPrefix = "tym";
                }else if ( lessonNum == 7){
                    audioPrefix = "day";
                }else if ( lessonNum == 8){
                    audioPrefix = "cloz";
                }else if ( lessonNum == 9){
                    audioPrefix = "fud";
                }else if ( lessonNum == 10){
                    audioPrefix = "prep";
                }else if ( lessonNum == 11){
                    audioPrefix = "verb";
                }else if ( lessonNum == 12){
                    audioPrefix = "fam";
                }else if ( lessonNum == 13){
                    audioPrefix = "wea";
                }

                if (mPlayer != null){
                    mPlayer.release();
                    mPlayer = null;
                }
                Log.i("DEBUG" ,"audioOnlySize"+ String.valueOf(audioOnly.size()));
                Log.i("DEBUG" ,"audioOnly.get(i) "+ String.valueOf(audioOnly.get(i)));

               // String audioFile = getAudio(audioOnly,audioPrefix + 1) ;
                int resid = getResources().getIdentifier(getAudio(audioOnly,audioPrefix + 1+i),"raw",
                        getPackageName());
                //int resid = getResources().getIdentifier(audioOnly.get(i),"raw",
                      //  getPackageName());
                mPlayer = MediaPlayer.create(LessonActivity.this,resid);
                mPlayer.start();

            }
        });

    }

    public String getAudio( ArrayList<String> audioList ,String wantedFileName) {

        for ( int i = 0 ; i < audioList.size();i++){
            String audioName = audioList.get(i);
            int indexOfFile;
            //Log.i("DEBUG" ,"audioOnlySize"+ String.valueOf(audioOnly.size()));
            Log.i("DEBUG" ,"audioName "+ audioName);
            if ( audioName.indexOf(wantedFileName) >= 0 || audioName != null){
            return audioName;
        }
    }
        return audioList.get(0);
}


    // Instantiates an Xml pull parser, receives the data from file
    // and pass it to parser, calls process parser for further data
    // processing
    private void parseXML() {
        XmlPullParserFactory parserFactory;
        try {
            parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            // get the data to parse in to inputStream
            InputStream is = getAssets().open("data.xml");
            // not using namespaces
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);

            parser.setInput(is, null);
            processParsing(parser);
        } catch (XmlPullParserException e) {

        } catch (IOException e) {

        }

    }


    // Takes the input data from parser object and steps through the lessons and
    //  sentences , populates sentence list and returns it
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
        //Log.i("DEBUG", String.valueOf(sentences.size()));

        return sentences;
    }

}

