package com.example.hp.assignment;

import android.widget.Button;
import android.widget.TextView;

public class Stores {

    public Button joinStore;
    public String nameOfstore;

    public Stores(Button button, String circleK) {
        this.joinStore = button;
        this.nameOfstore = circleK;
    }

    public Button getJoinStore() {
        return joinStore;
    }

    public void setJoinStore(Button joinStore) {
        this.joinStore = joinStore;
    }

    public String getNameOfstore() {
        return nameOfstore;
    }

    public void setNameOfstore(String nameOfstore) {
        this.nameOfstore = nameOfstore;
    }
}
