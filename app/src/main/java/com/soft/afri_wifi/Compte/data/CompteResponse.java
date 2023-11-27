package com.soft.afri_wifi.Compte.data;

import com.google.gson.annotations.SerializedName;

public class CompteResponse {
    @SerializedName("NumCompte")
    private int numCompte;
    @SerializedName("DesignationCompte")
    private String designationCompte;
    @SerializedName("reference")
    private String libelle;
    @SerializedName("DateOp")
    String dateOperation;
    @SerializedName("Debit")
    private double debit;
    @SerializedName("Credit")
    private double credit;
    @SerializedName("Solde")
    private double solde;

    public CompteResponse(String libelle, String dateOperation, double debit, double credit, double solde) {
        this.libelle = libelle;
        this.dateOperation = dateOperation;
        this.debit = debit;
        this.credit = credit;
        this.solde = solde;
    }

    public CompteResponse(int numCompte, String designationCompte) {
        this.numCompte = numCompte;
        this.designationCompte = designationCompte;
    }

    public int getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(int numCompte) {
        this.numCompte = numCompte;
    }

    public String getDesignationCompte() {
        return designationCompte;
    }

    public void setDesignationCompte(String designationCompte) {
        this.designationCompte = designationCompte;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getDateOperation() {
        return dateOperation;
    }

    public double getDebit() {
        return debit;
    }

    public double getCredit() {
        return credit;
    }

    public double getSolde() {
        return solde;
    }
}
