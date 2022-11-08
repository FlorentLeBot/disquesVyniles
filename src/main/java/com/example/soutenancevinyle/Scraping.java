package com.example.soutenancevinyle;

public class Scraping {
    // attributs de la classe Scraping
    private String title;
    private String price;
    private String description;
    private String type;
    private String url;

    private String date;

    // constructeur de la class Scraping

    public Scraping(String title, String price, String description, String url) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.url = url;
    }

    public Scraping(String title, String price, String description, String url, String type, String date) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.url = url;
        this.type = type;
        this.date = date;
    }

    public Scraping() {

    }

    //public Scraping(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // affichage
    @Override
    public String toString() {
        return "Titre de l'album : " + title + "\n" + "\n" +
                //"Prix : " + price + " €" + "\n" + "\n" +
                "Prix : " + price + "\n" + "\n" +
                "Description : " + description + "\n" + "\n" +
                "Site web : "+ url + "\n" +
                "--------------------------------------------------------" + "\n";
    }
}
