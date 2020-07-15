package com.test.nss.ui.camp;

public class AdapterCampDetails {
    private String clgName;
    private String campFrom;
    private String campTo;
    private String campVenue;
    private String campPost;
    private String campTaluka;
    private String campDistrict;

    public AdapterCampDetails(String clgName, String campFrom,
                              String campTo, String campVenue,
                              String campPost, String campTaluka, String campDistrict) {
        this.clgName = clgName;
        this.campFrom = campFrom;
        this.campTo = campTo;
        this.campVenue = campVenue;
        this.campPost = campPost;
        this.campTaluka = campTaluka;
        this.campDistrict = campDistrict;
    }

    public String getClgName() {
        return clgName;
    }

    public String getCampFrom() {
        return campFrom;
    }

    public String getCampTo() {
        return campTo;
    }

    public String getCampVenue() {
        return campVenue;
    }

    public String getCampPost() {
        return campPost;
    }

    public String getCampTaluka() {
        return campTaluka;
    }

    public String getCampDistrict() {
        return campDistrict;
    }
}