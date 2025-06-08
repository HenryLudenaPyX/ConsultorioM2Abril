package com.prototype.cm2a.models;

public class MedicalAttention {
    private String id;
    private String date;
    private String time;
    private String evolution;
    private String preinscription;
    private String medication;
    private String medic;

    public MedicalAttention() {}

    public MedicalAttention(String id, String date, String time, String evolution, String preinscription, String medication, String medic) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.evolution = evolution;
        this.preinscription = preinscription;
        this.medication = medication;
        this.medic = medic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEvolution() {
        return evolution;
    }

    public void setEvolution(String evolution) {
        this.evolution = evolution;
    }

    public String getPreinscription() {
        return preinscription;
    }

    public void setPreinscription(String preinscription) {
        this.preinscription = preinscription;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getMedic() {
        return medic;
    }

    public void setMedic(String medic) {
        this.medic = medic;
    }
}
