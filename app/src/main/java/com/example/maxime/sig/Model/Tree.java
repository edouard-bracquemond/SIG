package com.example.maxime.sig.Model;

import java.util.Collection;

public class Tree {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Collection<Picture> pictures) {
        this.pictures = pictures;
    }

    private int id;
    private Collection<Picture> pictures;

}
