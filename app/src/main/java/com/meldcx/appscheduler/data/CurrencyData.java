
package com.meldcx.appscheduler.data;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "currency_base")
public class CurrencyData {
    @PrimaryKey(autoGenerate = false)
    public int id = 1;

    @SerializedName("disclaimer")
    private String disclaimer;
    @SerializedName("license")
    private String license;
    @SerializedName("timestamp")
    private Integer timestamp;
    @SerializedName("base")
    private String base;

    @Ignore
    @SerializedName("rates")
    public Rates rates;

    @Ignore
    public ArrayList<Rate> rateList = new ArrayList();

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

    public void setRateList(List<Rate> rates){
        rateList.clear();
        rateList.addAll(rates);
    }

    public List<Rate> getRateListFromAPI() {
        rateList.clear();
        rateList.add(new Rate("AED",rates.getAed()));
        rateList.add(new Rate("AFN",rates.getAfn()));
        rateList.add(new Rate("ALL",rates.getAll()));
        rateList.add(new Rate("AMD",rates.getAmd()));
        rateList.add(new Rate("ANG",rates.getAng()));
        rateList.add(new Rate("AOA",rates.getAoa()));
        rateList.add(new Rate("ARS",rates.getArs()));
        rateList.add(new Rate("AUD",rates.getAud()));
        rateList.add(new Rate("AWG",rates.getAwg()));
        rateList.add(new Rate("AZN",rates.getAzn()));
        rateList.add(new Rate("BAM",rates.getBam()));
        rateList.add(new Rate("BBD",rates.getBbd()));
        rateList.add(new Rate("BDT",rates.getBdt()));
        rateList.add(new Rate("BGN",rates.getBgn()));
        rateList.add(new Rate("BHD",rates.getBhd()));
        rateList.add(new Rate("BIF",rates.getBif()));
        rateList.add(new Rate("BMD",rates.getBmd()));
        rateList.add(new Rate("BND",rates.getBnd()));
        rateList.add(new Rate("BOB",rates.getBob()));
        rateList.add(new Rate("BRL",rates.getBrl()));
        rateList.add(new Rate("BSD",rates.getBsd()));
        rateList.add(new Rate("BTC",rates.getBtc()));
        rateList.add(new Rate("BTN",rates.getBtn()));
        rateList.add(new Rate("BWP",rates.getBwp()));
        rateList.add(new Rate("BYN",rates.getByn()));
        rateList.add(new Rate("BZD",rates.getBzd()));
        rateList.add(new Rate("CAD",rates.getCad()));
        rateList.add(new Rate("CDF",rates.getCdf()));
        rateList.add(new Rate("CHF",rates.getChf()));
        rateList.add(new Rate("CLF",rates.getClf()));
        rateList.add(new Rate("CLP",rates.getClp()));
        rateList.add(new Rate("CNH",rates.getCnh()));
        rateList.add(new Rate("CNY",rates.getCny()));
        rateList.add(new Rate("COP",rates.getCop()));
        rateList.add(new Rate("CRC",rates.getCrc()));
        rateList.add(new Rate("CUC",rates.getCuc()));
        rateList.add(new Rate("CUP",rates.getCup()));
        rateList.add(new Rate("CVE",rates.getCve()));
        rateList.add(new Rate("CZK",rates.getCzk()));
        rateList.add(new Rate("DJF",rates.getDjf()));
        rateList.add(new Rate("DKK",rates.getDkk()));
        rateList.add(new Rate("DOP",rates.getDop()));
        rateList.add(new Rate("DZD",rates.getDzd()));
        rateList.add(new Rate("EGP",rates.getEgp()));
        rateList.add(new Rate("ERN",rates.getErn()));
        rateList.add(new Rate("ETB",rates.getEtb()));
        rateList.add(new Rate("EUR",rates.getEur()));
        rateList.add(new Rate("FJD",rates.getFjd()));
        //list.add(new Rate("FKP",rates.getFkp()));
        //list.add(new Rate("GBP",rates.getGbp()));
        //list.add(new Rate("GEL",rates.getGel()));
        rateList.add(new Rate("GGP",rates.getGgp()));
        rateList.add(new Rate("GHS",rates.getGhs()));
        rateList.add(new Rate("GIP",rates.getGip()));
        rateList.add(new Rate("GMD",rates.getGmd()));
        rateList.add(new Rate("GNF",rates.getGnf()));
        rateList.add(new Rate("GTQ",rates.getGtq()));
        rateList.add(new Rate("GYD",rates.getGyd()));
        rateList.add(new Rate("HKD",rates.getHkd()));
        rateList.add(new Rate("HNL",rates.getHnl()));
        rateList.add(new Rate("HRK",rates.getHrk()));
        rateList.add(new Rate("HTG",rates.getHtg()));
        rateList.add(new Rate("HUF",rates.getHuf()));
        rateList.add(new Rate("IDR",rates.getIdr()));
        rateList.add(new Rate("ILS",rates.getIls()));
        rateList.add(new Rate("IMP",rates.getImp()));
        rateList.add(new Rate("INR",rates.getInr()));
        rateList.add(new Rate("IQD",rates.getIqd()));
        rateList.add(new Rate("IRR",rates.getIrr()));
        rateList.add(new Rate("ISK",rates.getIsk()));
        rateList.add(new Rate("JEP",rates.getJep()));
        rateList.add(new Rate("JMD",rates.getJmd()));
        rateList.add(new Rate("JOD",rates.getJod()));
        rateList.add(new Rate("JPY",rates.getJpy()));
        rateList.add(new Rate("KES",rates.getKes()));
        rateList.add(new Rate("KGS",rates.getKgs()));
        rateList.add(new Rate("KHR",rates.getKhr()));
        rateList.add(new Rate("KMF",rates.getKmf()));
        rateList.add(new Rate("KPW",rates.getKpw()));
        rateList.add(new Rate("KRW",rates.getKrw()));
        rateList.add(new Rate("KWD",rates.getKwd()));
        rateList.add(new Rate("KYD",rates.getKyd()));
        rateList.add(new Rate("KZT",rates.getKzt()));
        rateList.add(new Rate("LAK",rates.getLak()));
        rateList.add(new Rate("LBP",rates.getLbp()));
        rateList.add(new Rate("LKR",rates.getLkr()));
        rateList.add(new Rate("LRD",rates.getLrd()));
        rateList.add(new Rate("LSL",rates.getLsl()));
        rateList.add(new Rate("LYD",rates.getLyd()));
        rateList.add(new Rate("MAD",rates.getMad()));
        rateList.add(new Rate("MDL",rates.getMdl()));
        rateList.add(new Rate("MGA",rates.getMga()));
        rateList.add(new Rate("MKD",rates.getMkd()));
        rateList.add(new Rate("MMK",rates.getMmk()));
        rateList.add(new Rate("MNT",rates.getMnt()));
        rateList.add(new Rate("MOP",rates.getMop()));
        rateList.add(new Rate("MRU",rates.getMru()));
        rateList.add(new Rate("MUR",rates.getMur()));
        rateList.add(new Rate("MVR",rates.getMvr()));
        rateList.add(new Rate("MWK",rates.getMwk()));
        rateList.add(new Rate("MXN",rates.getMxn()));
        rateList.add(new Rate("MYR",rates.getMyr()));
        rateList.add(new Rate("MZN",rates.getMzn()));
        rateList.add(new Rate("NAD",rates.getNad()));
        rateList.add(new Rate("NGN",rates.getNgn()));
        rateList.add(new Rate("NIO",rates.getNio()));
        rateList.add(new Rate("NOK",rates.getNok()));
        rateList.add(new Rate("NPR",rates.getNpr()));
        rateList.add(new Rate("NZD",rates.getNzd()));
        rateList.add(new Rate("OMR",rates.getOmr()));
        rateList.add(new Rate("PAB",rates.getPab()));
        rateList.add(new Rate("PEN",rates.getPen()));
        rateList.add(new Rate("PGK",rates.getPgk()));
        rateList.add(new Rate("PHP",rates.getPhp()));
        rateList.add(new Rate("PKR",rates.getPkr()));
        rateList.add(new Rate("PLN",rates.getPln()));
        rateList.add(new Rate("PYG",rates.getPyg()));
        rateList.add(new Rate("QAR",rates.getQar()));
        rateList.add(new Rate("RON",rates.getRon()));
        rateList.add(new Rate("RSD",rates.getRsd()));
        rateList.add(new Rate("RUB",rates.getRub()));
        rateList.add(new Rate("RWF",rates.getRwf()));
        rateList.add(new Rate("SAR",rates.getSar()));
        rateList.add(new Rate("SBD",rates.getSbd()));
        rateList.add(new Rate("SCR",rates.getScr()));
        rateList.add(new Rate("SDG",rates.getSdg()));
        rateList.add(new Rate("SEK",rates.getSek()));
        rateList.add(new Rate("SGD",rates.getSgd()));
        rateList.add(new Rate("SHP",rates.getShp()));
        rateList.add(new Rate("SLL",rates.getSll()));
        rateList.add(new Rate("SOS",rates.getSos()));
        rateList.add(new Rate("SRD",rates.getSrd()));
        rateList.add(new Rate("SSP",rates.getSsp()));
        rateList.add(new Rate("STD",rates.getStd()));
        rateList.add(new Rate("ST",rates.getStn()));
        rateList.add(new Rate("SVC",rates.getSvc()));
        rateList.add(new Rate("SYP",rates.getSyp()));
        rateList.add(new Rate("SZL",rates.getSzl()));
        rateList.add(new Rate("THB",rates.getThb()));
        rateList.add(new Rate("TJS",rates.getTjs()));
        rateList.add(new Rate("TMT",rates.getTmt()));
        rateList.add(new Rate("TND",rates.getTnd()));
        rateList.add(new Rate("TOP",rates.getTop()));
        rateList.add(new Rate("TRY",rates.getTry()));
        rateList.add(new Rate("TD",rates.getTtd()));
        rateList.add(new Rate("TWD",rates.getTwd()));
        rateList.add(new Rate("TZS",rates.getTzs()));
        rateList.add(new Rate("UAH",rates.getUah()));
        rateList.add(new Rate("UGX",rates.getUgx()));
        rateList.add(new Rate("USD",rates.getUsd()));
        rateList.add(new Rate("UYU",rates.getUyu()));
        rateList.add(new Rate("UZS",rates.getUzs()));
        rateList.add(new Rate("VES",rates.getVes()));
        rateList.add(new Rate("VND",rates.getVnd()));
        rateList.add(new Rate("VUV",rates.getVuv()));
        rateList.add(new Rate("WST",rates.getWst()));
        rateList.add(new Rate("XAF",rates.getXaf()));
        rateList.add(new Rate("XAG",rates.getXag()));
        rateList.add(new Rate("XAU",rates.getXau()));
        rateList.add(new Rate("XCD",rates.getXcd()));
        rateList.add(new Rate("XDR",rates.getXdr()));
        rateList.add(new Rate("XOF",rates.getXof()));
        rateList.add(new Rate("XPD",rates.getXpd()));
        rateList.add(new Rate("XPF",rates.getXpf()));
        rateList.add(new Rate("XPT",rates.getXpt()));
        rateList.add(new Rate("YER",rates.getYer()));
        rateList.add(new Rate("ZAR",rates.getZar()));
        rateList.add(new Rate("ZMW",rates.getZmw()));
        rateList.add(new Rate("ZWL",rates.getZwl()));
        return rateList;
    }

}
