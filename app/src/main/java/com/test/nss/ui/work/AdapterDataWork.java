package com.test.nss.ui.work;

public class AdapterDataWork {
    private String nature;
    private String totalHours;
    private String compHours;
    private String remHours;

    public AdapterDataWork(String nature, String totalHours, String compHours, String remHours) {
        this.nature = nature;
        this.totalHours = totalHours;
        this.compHours = compHours;
        this.remHours = remHours;
    }

    public String getNature() {
        return nature;
    }

    public String getTotalHours() {
        return totalHours;
    }

    public String getCompHours() {
        return compHours;
    }

    public String getRemHours() {
        return remHours;
    }
}
