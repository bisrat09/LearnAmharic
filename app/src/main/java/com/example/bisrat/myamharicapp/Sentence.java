package com.example.bisrat.myamharicapp;

// Defines the sentence model object for later use in the adapter
// and views to display
public class Sentence {

    // each sentence object has three fields

    public String english, amharic, transliteration;
    public String audioFile;
    private int id;

    // constructors

    public Sentence() {
    }

    public Sentence(String english, String amharic,String audioFile) {
        this.english = english;
        this.amharic = amharic;
       // this.transliteration = transliteration;
        this.audioFile = audioFile;
    }

    // returns sentence id

    // public int sentenceId;


    public String getEnglish() {
        return english;
    }

    public String getAmharic() {
        return amharic;
    }

   // public String getTransliteration() {
      //  return transliteration;
   // }
    public String getAudioFile() {
        return audioFile;
    }

    // returns sentence id
    public int getId() {
        return id;

    }


    // sets the sentences id

    public void setId(int id) {
        this.id = id;
    }

    // sets the fields of a sentence
    public void setEnglish(String english) {
        this.english = english;
    }

    public void setAmharic(String amharic) {
        this.amharic = amharic;
    }

    public void setTransliteration(String transliteration) {
        this.transliteration = transliteration;
    }


}
