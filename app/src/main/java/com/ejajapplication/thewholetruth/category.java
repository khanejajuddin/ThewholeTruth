package com.ejajapplication.thewholetruth;

public class category {
    public String ID;
    public String Name;
    public String image;

    public category() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public category(String ID, String name, String image) {
        this.ID = ID;
        Name = name;
        this.image = image;
    }

}
