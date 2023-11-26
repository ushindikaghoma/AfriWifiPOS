package com.soft.afri_wifi.Comptabilite.data;

import com.google.gson.annotations.SerializedName;

public class MvtResponse {

    @SerializedName("NumCompte")
    private int NumCompte;
    @SerializedName("LibeleDeCompte")
    private String LibeleDeCompte ;
    @SerializedName("Entree")
    private double Entree ;
    @SerializedName("Details")
    private String Details;
    @SerializedName("CodeLibele")
    private String CodeLibele ;
    @SerializedName("Sortie")
    private double Sortie;
    @SerializedName("Qte")
    private double Qte;
    @SerializedName("NumOperation")
    private String NumOperation ;
    @SerializedName("NumMvtCompte")
    private String NumMvtCompte ;

    public MvtResponse() {
    }

    public int getNumCompte() {
        return NumCompte;
    }

    public void setNumCompte(int numCompte) {
        NumCompte = numCompte;
    }

    public String getLibeleDeCompte() {
        return LibeleDeCompte;
    }

    public void setLibeleDeCompte(String libeleDeCompte) {
        LibeleDeCompte = libeleDeCompte;
    }

    public double getEntree() {
        return Entree;
    }

    public void setEntree(double entree) {
        Entree = entree;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getCodeLibele() {
        return CodeLibele;
    }

    public void setCodeLibele(String codeLibele) {
        CodeLibele = codeLibele;
    }

    public double getSortie() {
        return Sortie;
    }

    public void setSortie(double sortie) {
        Sortie = sortie;
    }

    public double getQte() {
        return Qte;
    }

    public void setQte(double qte) {
        Qte = qte;
    }

    public String getNumOperation() {
        return NumOperation;
    }

    public void setNumOperation(String numOperation) {
        NumOperation = numOperation;
    }

    public String getNumMvtCompte() {
        return NumMvtCompte;
    }

    public void setNumMvtCompte(String numMvtCompte) {
        NumMvtCompte = numMvtCompte;
    }
}

