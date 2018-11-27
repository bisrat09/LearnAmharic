package com.example.bisrat.myamharicapp;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;


public class LessonAdapter extends ArrayAdapter<Sentence> {
    // defines text views in a view holder to speed up population of the views
    private static class ViewHolder {
        TextView english;
        TextView amharic;
        //TextView transliteration;
        ImageView speaker;
    }


    public LessonAdapter(Context context, ArrayList<Sentence> sents) {
        super(context,R.layout.lesson_row, sents);
    }

//-------------------------------------------------------------------------------
// getView() Method
// uses a viewHolder object to cache and reuse a view when user scrolls up  or down
// views that are no longer on the current screen of the user are recycled
// takes the index, view and viewGroup and returns
// Populates english, amharic and audio data from the data object via the viewHolder object
//-----------------------------------------------------------------------------

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        // Get the data item for this position
        Sentence sentence = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lesson_row, parent, false);
            viewHolder.english = (TextView) convertView.findViewById(R.id.englishTv);
            viewHolder.amharic= (TextView) convertView.findViewById(R.id.amharicTv);
           // viewHolder.transliteration = (TextView) convertView.findViewById(R.id.transliterationTv);
            viewHolder.speaker = (ImageView) convertView.findViewById(R.id.speakerIv);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {

            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Populate the data from the data object via the viewHolder object
        // into the template view.
        viewHolder.english.setText(sentence.english);
        viewHolder.amharic.setText(sentence.amharic);
        //viewHolder.transliteration.setText(sentence.transliteration);
        viewHolder.speaker.setImageResource(R.drawable.speaker_greyyellow);
        // Return the completed view to render on screen
        return convertView;
    }

}

