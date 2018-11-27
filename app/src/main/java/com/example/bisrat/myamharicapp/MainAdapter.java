
/** MainAdapter.java
 * custom adapter class created to describe the process of converting model object to a View
 * Extends BaseAdapter class to implement the specialized ListAdapter interface
 * come handy to easily manage the behaviour of the ListViews and their items
 * starts new activity up on click on main view
 * written by bisrat belayneh
 * Date 11/26/2018
 */

package com.example.bisrat.myamharicapp;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainAdapter extends BaseAdapter {

    Context mContext;    // the activity in which adapater will be used
    LayoutInflater inflater; // to inflate views per the model
    List<Model> modellist;  // list of model objects
    ArrayList<Model> arrayList; // the list that holds the populated data

    // constructors


    public MainAdapter(Context context, List<Model> modellist) {
        mContext = context;
        this.modellist = modellist;
        inflater =LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<Model>();
        this.arrayList.addAll(modellist);
    }
    // view holder to contain the text and image views
    public class ViewHolder{
        TextView mTitleTv;
        ImageView mIconIv;
    }
    @Override
    // returns number of items in the list
    public int getCount() {
        return modellist.size();
    }

    // returns an element from the list
    @Override
    public Object getItem(int i) {
        return modellist.get(i);
    }

    // returns an element id from the list
    @Override
    public long getItemId(int i) {
        return i;
    }
   //--------------------------------------------------------------------------------
    // getView() method
    // returns the completed view to render to the screen with
    // the lesson selected from screen
    // we don't call this method directly, just need to implement it
    // to tell the parent view how to generate the item's view.
    //---------------------------------------------------------------------------------
    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        ViewHolder holder;
        if (view == null){
            holder= new ViewHolder(); // view lookup cache stored in tag

            view = inflater.inflate(R.layout.row, null);

            // locate the views in row.xml
            holder.mTitleTv = view.findViewById(R.id.mainTitle);
            holder.mIconIv = view.findViewById(R.id.mainIcon);

            view.setTag(holder);
        }
        else{
            holder = (ViewHolder)view.getTag();
        }
        // set the results in to txt views
        holder.mTitleTv.setText(modellist.get(position).getTitle());
        holder.mIconIv.setImageResource(modellist.get(position).getIcon());

        //--------------------------------------------------------------------------
        // onClick() method
        //takes a view object and returns nothing
        // list view item clicks puts lesson titles on click listener
        // launches a second activity when title is clicked
        // populates the action bar with lesson title in second activity
        // associates a lesson number to the new activity
        //------------------------------------------------------------------------
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (modellist.get(position).getTitle().equals("Greetings  |  ሰላምታ")){
                    //start new activity with title for actionBar and text for text view
                    Intent intent = new Intent (mContext,LessonActivity.class);
                    intent.putExtra("actionBarTitle","Greetings");
                    intent.putExtra("LESSON_NUMBER",1);
                    mContext.startActivity(intent);
                }
                if (modellist.get(position).getTitle().equals("Expressions  |  አባባሎች")){
                    //start new activity with title for actionBar and text for text view
                    Intent intent = new Intent (mContext,LessonActivity.class);
                    intent.putExtra("actionBarTitle","Expressions");
                    intent.putExtra("LESSON_NUMBER",2);
                    mContext.startActivity(intent);
                }
                if (modellist.get(position).getTitle().equals("Numbers  |  ቁጥሮች")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(mContext, LessonActivity.class);
                    intent.putExtra("actionBarTitle", "Numbers");
                    intent.putExtra("LESSON_NUMBER",3);
                    mContext.startActivity(intent);
                }
                if (modellist.get(position).getTitle().equals("Directions  |  አቅጣጫ")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(mContext, LessonActivity.class);
                    intent.putExtra("actionBarTitle", "Directions");
                    intent.putExtra("LESSON_NUMBER",4);
                    mContext.startActivity(intent);
                }
                if (modellist.get(position).getTitle().equals("Travel  |  ጉዞ")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(mContext, LessonActivity.class);
                    intent.putExtra("actionBarTitle", "Travel");
                    intent.putExtra("LESSON_NUMBER",5);
                    mContext.startActivity(intent);
                }
                if (modellist.get(position).getTitle().equals("Time  |  ጊዜ")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(mContext, LessonActivity.class);
                    intent.putExtra("actionBarTitle", "Time");
                    intent.putExtra("LESSON_NUMBER",6);
                    mContext.startActivity(intent);
                }
                if (modellist.get(position).getTitle().equals("Days & Months  |  ቀናት እና ወራት")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(mContext, LessonActivity.class);
                    intent.putExtra("actionBarTitle", "Days and Months");
                    intent.putExtra("LESSON_NUMBER",7);
                    mContext.startActivity(intent);
                }
                if (modellist.get(position).getTitle().equals("Clothing  |  አልባሳት")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(mContext, LessonActivity.class);
                    intent.putExtra("LESSON_NUMBER",8);
                    intent.putExtra("actionBarTitle", "Clothing");
                    mContext.startActivity(intent);
                }
                if (modellist.get(position).getTitle().equals("Food  |  ምግብ")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(mContext, LessonActivity.class);
                    intent.putExtra("LESSON_NUMBER",9);
                    intent.putExtra("actionBarTitle", "Food");
                    mContext.startActivity(intent);
                }
                if (modellist.get(position).getTitle().equals("Prepositions  |  መስተዋድድ")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(mContext, LessonActivity.class);
                    intent.putExtra("LESSON_NUMBER",10);
                    intent.putExtra("actionBarTitle", "Prepositions");
                    mContext.startActivity(intent);
                }
                if (modellist.get(position).getTitle().equals("Verbs  |  ግሶች")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(mContext, LessonActivity.class);
                    intent.putExtra("actionBarTitle", "Verbs");
                    intent.putExtra("LESSON_NUMBER",11);
                    mContext.startActivity(intent);
                }
                if (modellist.get(position).getTitle().equals("Family  |  ቤተሰብ")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(mContext, LessonActivity.class);
                    intent.putExtra("actionBarTitle", "Family");
                    intent.putExtra("LESSON_NUMBER",12);
                    mContext.startActivity(intent);
                }
                if (modellist.get(position).getTitle().equals("Weather  |  የአየር ሁኔታ")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(mContext, LessonActivity.class);
                    intent.putExtra("actionBarTitle", "Weather");
                    intent.putExtra("LESSON_NUMBER",13);
                    mContext.startActivity(intent);
                }


            }
        });
        return view;
    }
   /**-------------------------------------------------------------------------
    *filter() method
    *takes a string and returns nothing
    * filters the list with the characters typed in the search bar
    ------------------------------------------------------------------------
    */

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        modellist.clear();
        // no characters typed so display the whole list
        if (charText.length()==0){
            modellist.addAll(arrayList);
            // user typed chars find a match from list and load it
        }else{
            for (Model model: arrayList){
                if(model.getTitle().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    modellist.add(model);


                }
            }
        }
        notifyDataSetChanged();
    }
}

