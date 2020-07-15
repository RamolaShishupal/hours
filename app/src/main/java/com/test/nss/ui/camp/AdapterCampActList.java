package com.test.nss.ui.camp;

public class AdapterCampActList {
    private String campTitle;
    private String campDesc;
    private String campDay;

    public AdapterCampActList(String campTitle, String campDesc, String campDay) {
        this.campTitle = campTitle;
        this.campDesc = campDesc;
        this.campDay = campDay;
    }

    public String getCampTitle() {
        return campTitle;
    }

    public String getCampDesc() {
        return campDesc;
    }

    public String getCampDay() {
        return campDay;
    }
}