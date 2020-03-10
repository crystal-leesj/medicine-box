package com.rxing.medicinebox.models;

import java.util.Date;

public class Medicine {
    private String drugName;
    private String doseAmount;
    private String doseScale;
    private String startDate;
    private String endDate;
    private String frequencyPeriod; //#
    private String frequencyByPeriod; // day

    public Medicine(String drugName, String doseAmount, String doseScale, String startDate, String endDate, String frequencyPeriod, String frequencyByPeriod) {
        this.drugName = drugName;
        this.doseAmount = doseAmount;
        this.doseScale = doseScale;
        this.startDate = startDate;
        this.endDate = endDate;
        this.frequencyPeriod = frequencyPeriod;
        this.frequencyByPeriod = frequencyByPeriod;
    }

    public Medicine() {
    }

    public String getDrugName() {
        return drugName;
    }

    public String getDoseAmount() {
        return doseAmount;
    }

    public String getDoseScale() {
        return doseScale;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getFrequencyPeriod() {
        return frequencyPeriod;
    }

    public String getFrequencyByPeriod() {
        return frequencyByPeriod;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public void setDoseAmount(String doseAmount) {
        this.doseAmount = doseAmount;
    }

    public void setDoseScale(String doseScale) {
        this.doseScale = doseScale;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setFrequencyPeriod(String frequencyPeriod) {
        this.frequencyPeriod = frequencyPeriod;
    }

    public void setFrequencyByPeriod(String frequencyByPeriod) {
        this.frequencyByPeriod = frequencyByPeriod;
    }
}
