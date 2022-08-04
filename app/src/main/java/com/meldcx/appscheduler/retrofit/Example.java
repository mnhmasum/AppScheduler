
package com.meldcx.appscheduler.retrofit;


import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("disclaimer")
    private String disclaimer;
    @SerializedName("license")
    private String license;
    @SerializedName("timestamp")
    private Integer timestamp;
    @SerializedName("base")
    private String base;
    @SerializedName("rates")
    private Rates rates;

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }

}
