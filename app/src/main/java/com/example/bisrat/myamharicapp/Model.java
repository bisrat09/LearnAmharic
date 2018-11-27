/** Model.java
 * constructs model object with title and icon field
   for adapter and view to display categories of lessons
 * written by bisrat belayneh
 * Date 11/26/2018
 */
package com.example.bisrat.myamharicapp;

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
