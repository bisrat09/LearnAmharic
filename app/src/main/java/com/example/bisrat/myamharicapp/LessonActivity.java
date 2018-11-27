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
           
        }


        for (int i =0; i < englishOnly.size(); i++){
            Sentence sent = new Sentence(englishOnly.get(i), amharicOnly.get(i)
                    ,audioOnly.get(i));

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
              int offset = 0;
                // audio prefix according to lesson number
                String audioPrefix = "";
                if (lessonNum == 1){
                    audioPrefix = "grt";
                    offset = 1;
                }else if ( lessonNum == 2){
                    audioPrefix = "exp";
                    offset = 52;
                }else if ( lessonNum == 3){
                    audioPrefix = "num";
                    offset = 93;
                }else if ( lessonNum == 4){
                    audioPrefix = "dir";
                    offset = 119;
                }else if ( lessonNum == 5){
                    audioPrefix = "tra";
                    offset = 165;
                }else if ( lessonNum == 6){
                    audioPrefix = "tym";
                    offset = 192;
                }else if ( lessonNum == 7){
                    audioPrefix = "day";
                    offset = 219;
                }else if ( lessonNum == 8){
                    audioPrefix = "cloz";
                    offset = 238;
                }else if ( lessonNum == 9){
                    audioPrefix = "fud";
                    offset = 290;
                }else if ( lessonNum == 10){
                    audioPrefix = "prep";
                    offset = 362;
                }else if ( lessonNum == 11){
                    audioPrefix = "verb";
                    offset = 436;
                }else if ( lessonNum == 12){
                    audioPrefix = "fam";
                    offset = 525;
                }else if ( lessonNum == 13){
                    audioPrefix = "wea";
                    offset =549;
                }

                if (mPlayer != null){
                    mPlayer.release();
                    mPlayer = null;
                }

                int resid = getResources().getIdentifier(getAudio(audioOnly,
                        audioPrefix + (offset + i) ),"raw",
                        getPackageName());
                Log.i("DEBUG", "resid " +  String.valueOf(resid));

                mPlayer = MediaPlayer.create(LessonActivity.this,resid);
                mPlayer.start();

            }
        });

    }

    public String getAudio( ArrayList<String> audioList ,String wantedFileName) {
        Log.i("DEBUG" ,"wantedName "+ wantedFileName);
        for ( int i = 0 ; i < audioList.size();i++){
            String audioName = audioList.get(i);

            if ( audioName != null && audioName.equalsIgnoreCase(wantedFileName) ){

            return audioName;
        }
    }
        return audioList.get(0);
}

     //---------------------------------------------------------------------------
    // ParseXML() method
    // Instantiates an Xml pull parser, receives the data from file
    // and pass it to parser, calls process parser for further data
    // processing
    //-------------------------------------------------------------------------------
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

    //---------------------------------------------------------------------------------
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

