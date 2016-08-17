package com.opcclient.wolf.model;

public class ItemTags {

    private String nameTags;
    private String typeTags;
    private String tags;

    public ItemTags(String nameTags, String typeTags, String tags) {
        this.nameTags = nameTags;
        this.typeTags = typeTags;
        this.tags = tags;
    }

    public ItemTags(){}

    public String getNameTags() {
        return nameTags;
    }

    public void setNameTags(String nameTags) {
        this.nameTags = nameTags;
    }

    public String getTypeTags() {
        return typeTags;
    }

    public void setTypeTags(String typeTags) {
        this.typeTags = typeTags;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }


}
