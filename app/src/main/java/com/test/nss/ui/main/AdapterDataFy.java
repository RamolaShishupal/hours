package com.test.nss.ui.main;

public class AdapterDataFy {
    private String date;
    private String act;
    private String hours;

    public AdapterDataFy(String date, String act, String hours) {
        this.date = date;
        this.act = act;
        this.hours = hours;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
