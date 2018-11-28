
/**
 * MainActivity.java is the point of entry to the app.
 * The first program to run when app is launched.
 * Parent class to all other activities
 * Extends the AppCompatActivity base class to
   use the action bar features of support library
 * written by bisrat belayneh
 * Date 11/26/2018

*/
package com.example.bisrat.myamharicapp;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    MainAdapter adapter;
    String[] title;
    int[] icon;
    ArrayList<Model> arrayList = new ArrayList<Model>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.Main_title);

        title = new String[]{"Greetings  |  ሰላምታ", "Expressions  |  አባባሎች", "Numbers  |  ቁጥሮች",
                "Directions  |  አቅጣጫ", "Travel  |  ጉዞ", "Time  |  ጊዜ", "Days & Months  |  ቀናት እና ወራት",
                "Clothing  |  አልባሳት", "Food  |  ምግብ", "Prepositions  |  መስተዋድድ","Verbs  |  ግሶች",
                "Family  |  ቤተሰብ","Weather  |  የአየር ሁኔታ"};

        icon = new int[]{
                R.drawable.greeting, R.drawable.chat, R.drawable.numbers,
                R.drawable.directions, R.drawable.travel, R.drawable.clock_yellow, R.drawable.calendar,
                R.drawable.tie,R.drawable.food_burger,R.drawable.pre_at, R.drawable.verb, R.drawable.family,
                R.drawable.weather
        };

        listView = findViewById(R.id.listView);

        for (int i =0; i < title.length; i++){
            Model model = new Model (title[i],icon[i]);
            // bind strings in an array
            arrayList.add(model);
        }

        //pass results to adapter class

        adapter = new MainAdapter(this,arrayList);
        // bind adapter to listview

        listView.setAdapter(adapter);
    }
    /**-----------------------------------------------------------------------------
     * onCreateOptionsMenu method
     * takes Menu object and returns boolean
     * inflates the menu , binds search action , takes string from user
     * and filters lesson title , returns true on success
     ----------------------------------------------------------------------------
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)){
                    adapter.filter("");
                    listView.clearTextFilter();
                }else{
                    adapter.filter(s);
                }
                return true;
            }
        });
        return true;
    }
 /**---------------------------------------------------------------------------------
 * onOptionsItemSelected method
 * takes MenuItem object and returns boolean
 * sets up the intent to launch an activity when Settings button is clicked
 * launches the SettingsPrefActivity class
 * ----------------------------------------------------------------------------------
 */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            Intent intent = new Intent(this, SettingsPrefActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

