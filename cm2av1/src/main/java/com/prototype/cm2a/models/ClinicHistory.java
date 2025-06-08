package com.prototype.cm2a.models;

public class ClinicHistory {
    private String establishment;
    private String identityCard;
    private String names;
    private String surnames;
    private String sex;
    private int sheetNumber;
    private String codeHistoryClinic;
    private String consultMotive;
    private String personalAntecedents;
    private String familyAntecedents;
    private String physicExam;
    private String nameDoctor;

    public ClinicHistory() {}

    public ClinicHistory(String codeHistoryClinic, String identityCard, String names, String surnames, String nameDoctor){
        this.codeHistoryClinic = codeHistoryClinic;
        this.identityCard = identityCard;
        this.names = names;
        this.surnames = surnames;
        this.nameDoctor = nameDoctor;
    }

    public ClinicHistory(String establishment, String identityCard, String names, String surnames, String sex, int sheetNumber, String codeHistoryClinic, String consultMotive, String personalAntecedents, String familyAntecedents, String physicExam, String nameDoctor) {
        this.establishment = establishment;
        this.identityCard = identityCard;
        this.names = names;
        this.surnames = surnames;
        this.sex = sex;
        this.sheetNumber = sheetNumber;
        this.codeHistoryClinic = codeHistoryClinic;
        this.consultMotive = consultMotive;
        this.personalAntecedents = personalAntecedents;
        this.familyAntecedents = familyAntecedents;
        this.physicExam = physicExam;
        this.nameDoctor = nameDoctor;
    }

    public String getEstablishment() {
        return establishment;
    }

    public void setEstablishment(String establishment) {
        this.establishment = establishment;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getSheetNumber() {
        return sheetNumber;
    }

    public void setSheetNumber(int sheetNumber) {
        this.sheetNumber = sheetNumber;
    }

    public String getCodeHistoryClinic() {
        return codeHistoryClinic;
    }

    public void setCodeHistoryClinic(String codeHistoryClinic) {
        this.codeHistoryClinic = codeHistoryClinic;
    }

    public String getConsultMotive() {
        return consultMotive;
    }

    public void setConsultMotive(String consultMotive) {
        this.consultMotive = consultMotive;
    }

    public String getPersonalAntecedents() {
        return personalAntecedents;
    }

    public void setPersonalAntecedents(String personalAntecedents) {
        this.personalAntecedents = personalAntecedents;
    }

    public String getFamilyAntecedents() {
        return familyAntecedents;
    }

    public void setFamilyAntecedents(String familyAntecedents) {
        this.familyAntecedents = familyAntecedents;
    }

    public String getPhysicExam() {
        return physicExam;
    }

    public void setPhysicExam(String physicExam) {
        this.physicExam = physicExam;
    }

    public String getNameDoctor() {
        return nameDoctor;
    }

    public void setNameDoctor(String nameDoctor) {
        this.nameDoctor = nameDoctor;
    }
}
