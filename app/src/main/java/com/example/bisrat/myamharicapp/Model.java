package com.example.bisrat.myamharicapp;


// constructs model object with title and icon field
// for adapter and view to display
public class Model {


    String title;
    int icon;
    // constructor
    public Model(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    // returns title
    public String getTitle() {
        return this.title;
    }

    // returns icon
    public int getIcon() {
        return this.icon;
    }
}
