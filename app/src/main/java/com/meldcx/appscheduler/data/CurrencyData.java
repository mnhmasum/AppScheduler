
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
    public ArrayList<Rate> list = new ArrayList();

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
        list.clear();
        list.addAll(rates);
    }

    public List<Rate> getRateListFromAPI() {
        list.clear();
        list.add(new Rate("AED",rates.getAed()));
        list.add(new Rate("AFN",rates.getAfn()));
        list.add(new Rate("ALL",rates.getAll()));
        list.add(new Rate("AMD",rates.getAmd()));
        list.add(new Rate("ANG",rates.getAng()));
        list.add(new Rate("AOA",rates.getAoa()));
        list.add(new Rate("ARS",rates.getArs()));
        list.add(new Rate("AUD",rates.getAud()));
        list.add(new Rate("AWG",rates.getAwg()));
        list.add(new Rate("AZN",rates.getAzn()));
        list.add(new Rate("BAM",rates.getBam()));
        list.add(new Rate("BBD",rates.getBbd()));
        list.add(new Rate("BDT",rates.getBdt()));
        list.add(new Rate("BGN",rates.getBgn()));
        list.add(new Rate("BHD",rates.getBhd()));
        list.add(new Rate("BIF",rates.getBif()));
        list.add(new Rate("BMD",rates.getBmd()));
        list.add(new Rate("BND",rates.getBnd()));
        list.add(new Rate("BOB",rates.getBob()));
        list.add(new Rate("BRL",rates.getBrl()));
        list.add(new Rate("BSD",rates.getBsd()));
        list.add(new Rate("BTC",rates.getBtc()));
        list.add(new Rate("BTN",rates.getBtn()));
        list.add(new Rate("BWP",rates.getBwp()));
        list.add(new Rate("BYN",rates.getByn()));
        list.add(new Rate("BZD",rates.getBzd()));
        list.add(new Rate("CAD",rates.getCad()));
        list.add(new Rate("CDF",rates.getCdf()));
        list.add(new Rate("CHF",rates.getChf()));
        list.add(new Rate("CLF",rates.getClf()));
        list.add(new Rate("CLP",rates.getClp()));
        list.add(new Rate("CNH",rates.getCnh()));
        list.add(new Rate("CNY",rates.getCny()));
        list.add(new Rate("COP",rates.getCop()));
        list.add(new Rate("CRC",rates.getCrc()));
        list.add(new Rate("CUC",rates.getCuc()));
        list.add(new Rate("CUP",rates.getCup()));
        list.add(new Rate("CVE",rates.getCve()));
        list.add(new Rate("CZK",rates.getCzk()));
        list.add(new Rate("DJF",rates.getDjf()));
        list.add(new Rate("DKK",rates.getDkk()));
        list.add(new Rate("DOP",rates.getDop()));
        list.add(new Rate("DZD",rates.getDzd()));
        list.add(new Rate("EGP",rates.getEgp()));
        list.add(new Rate("ERN",rates.getErn()));
        list.add(new Rate("ETB",rates.getEtb()));
        list.add(new Rate("EUR",rates.getEur()));
        list.add(new Rate("FJD",rates.getFjd()));
        //list.add(new Rate("FKP",rates.getFkp()));
        //list.add(new Rate("GBP",rates.getGbp()));
        //list.add(new Rate("GEL",rates.getGel()));
        list.add(new Rate("GGP",rates.getGgp()));
        list.add(new Rate("GHS",rates.getGhs()));
        list.add(new Rate("GIP",rates.getGip()));
        list.add(new Rate("GMD",rates.getGmd()));
        list.add(new Rate("GNF",rates.getGnf()));
        list.add(new Rate("GTQ",rates.getGtq()));
        list.add(new Rate("GYD",rates.getGyd()));
        list.add(new Rate("HKD",rates.getHkd()));
        list.add(new Rate("HNL",rates.getHnl()));
        list.add(new Rate("HRK",rates.getHrk()));
        list.add(new Rate("HTG",rates.getHtg()));
        list.add(new Rate("HUF",rates.getHuf()));
        list.add(new Rate("IDR",rates.getIdr()));
        list.add(new Rate("ILS",rates.getIls()));
        list.add(new Rate("IMP",rates.getImp()));
        list.add(new Rate("INR",rates.getInr()));
        list.add(new Rate("IQD",rates.getIqd()));
        list.add(new Rate("IRR",rates.getIrr()));
        list.add(new Rate("ISK",rates.getIsk()));
        list.add(new Rate("JEP",rates.getJep()));
        list.add(new Rate("JMD",rates.getJmd()));
        list.add(new Rate("JOD",rates.getJod()));
        list.add(new Rate("JPY",rates.getJpy()));
        list.add(new Rate("KES",rates.getKes()));
        list.add(new Rate("KGS",rates.getKgs()));
        list.add(new Rate("KHR",rates.getKhr()));
        list.add(new Rate("KMF",rates.getKmf()));
        list.add(new Rate("KPW",rates.getKpw()));
        list.add(new Rate("KRW",rates.getKrw()));
        list.add(new Rate("KWD",rates.getKwd()));
        list.add(new Rate("KYD",rates.getKyd()));
        list.add(new Rate("KZT",rates.getKzt()));
        list.add(new Rate("LAK",rates.getLak()));
        list.add(new Rate("LBP",rates.getLbp()));
        list.add(new Rate("LKR",rates.getLkr()));
        list.add(new Rate("LRD",rates.getLrd()));
        list.add(new Rate("LSL",rates.getLsl()));
        list.add(new Rate("LYD",rates.getLyd()));
        list.add(new Rate("MAD",rates.getMad()));
        list.add(new Rate("MDL",rates.getMdl()));
        list.add(new Rate("MGA",rates.getMga()));
        list.add(new Rate("MKD",rates.getMkd()));
        list.add(new Rate("MMK",rates.getMmk()));
        list.add(new Rate("MNT",rates.getMnt()));
        list.add(new Rate("MOP",rates.getMop()));
        list.add(new Rate("MRU",rates.getMru()));
        list.add(new Rate("MUR",rates.getMur()));
        list.add(new Rate("MVR",rates.getMvr()));
        list.add(new Rate("MWK",rates.getMwk()));
        list.add(new Rate("MXN",rates.getMxn()));
        list.add(new Rate("MYR",rates.getMyr()));
        list.add(new Rate("MZN",rates.getMzn()));
        list.add(new Rate("NAD",rates.getNad()));
        list.add(new Rate("NGN",rates.getNgn()));
        list.add(new Rate("NIO",rates.getNio()));
        list.add(new Rate("NOK",rates.getNok()));
        list.add(new Rate("NPR",rates.getNpr()));
        list.add(new Rate("NZD",rates.getNzd()));
        list.add(new Rate("OMR",rates.getOmr()));
        list.add(new Rate("PAB",rates.getPab()));
        list.add(new Rate("PEN",rates.getPen()));
        list.add(new Rate("PGK",rates.getPgk()));
        list.add(new Rate("PHP",rates.getPhp()));
        list.add(new Rate("PKR",rates.getPkr()));
        list.add(new Rate("PLN",rates.getPln()));
        list.add(new Rate("PYG",rates.getPyg()));
        list.add(new Rate("QAR",rates.getQar()));
        list.add(new Rate("RON",rates.getRon()));
        list.add(new Rate("RSD",rates.getRsd()));
        list.add(new Rate("RUB",rates.getRub()));
        list.add(new Rate("RWF",rates.getRwf()));
        list.add(new Rate("SAR",rates.getSar()));
        list.add(new Rate("SBD",rates.getSbd()));
        list.add(new Rate("SCR",rates.getScr()));
        list.add(new Rate("SDG",rates.getSdg()));
        list.add(new Rate("SEK",rates.getSek()));
        list.add(new Rate("SGD",rates.getSgd()));
        list.add(new Rate("SHP",rates.getShp()));
        list.add(new Rate("SLL",rates.getSll()));
        list.add(new Rate("SOS",rates.getSos()));
        list.add(new Rate("SRD",rates.getSrd()));
        list.add(new Rate("SSP",rates.getSsp()));
        list.add(new Rate("STD",rates.getStd()));
        list.add(new Rate("ST",rates.getStn()));
        list.add(new Rate("SVC",rates.getSvc()));
        list.add(new Rate("SYP",rates.getSyp()));
        list.add(new Rate("SZL",rates.getSzl()));
        list.add(new Rate("THB",rates.getThb()));
        list.add(new Rate("TJS",rates.getTjs()));
        list.add(new Rate("TMT",rates.getTmt()));
        list.add(new Rate("TND",rates.getTnd()));
        list.add(new Rate("TOP",rates.getTop()));
        list.add(new Rate("TRY",rates.getTry()));
        list.add(new Rate("TD",rates.getTtd()));
        list.add(new Rate("TWD",rates.getTwd()));
        list.add(new Rate("TZS",rates.getTzs()));
        list.add(new Rate("UAH",rates.getUah()));
        list.add(new Rate("UGX",rates.getUgx()));
        list.add(new Rate("USD",rates.getUsd()));
        list.add(new Rate("UYU",rates.getUyu()));
        list.add(new Rate("UZS",rates.getUzs()));
        list.add(new Rate("VES",rates.getVes()));
        list.add(new Rate("VND",rates.getVnd()));
        list.add(new Rate("VUV",rates.getVuv()));
        list.add(new Rate("WST",rates.getWst()));
        list.add(new Rate("XAF",rates.getXaf()));
        list.add(new Rate("XAG",rates.getXag()));
        list.add(new Rate("XAU",rates.getXau()));
        list.add(new Rate("XCD",rates.getXcd()));
        list.add(new Rate("XDR",rates.getXdr()));
        list.add(new Rate("XOF",rates.getXof()));
        list.add(new Rate("XPD",rates.getXpd()));
        list.add(new Rate("XPF",rates.getXpf()));
        list.add(new Rate("XPT",rates.getXpt()));
        list.add(new Rate("YER",rates.getYer()));
        list.add(new Rate("ZAR",rates.getZar()));
        list.add(new Rate("ZMW",rates.getZmw()));
        list.add(new Rate("ZWL",rates.getZwl()));
        return list;
    }

}
