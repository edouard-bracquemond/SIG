package com.example.maxime.sig.model;

public class Picture {


    private int id;
    private String location;
    private String saison;
    private String url;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSaison() {
        return saison;
    }

    public void setSaison(String saison) {
        this.saison = saison;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", saison='" + saison + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
