package com.stefanodecillis.intcoverage;



/**
 * Created by stefanodecillis on 30/10/2017.
 */

public class ListItem {

    private String title;
    private String description;

    public ListItem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
