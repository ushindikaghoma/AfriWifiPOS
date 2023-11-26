package com.soft.afri_wifi.Comptabilite.data;

import com.google.gson.annotations.SerializedName;

public class ComptabiliteResponse {
    @SerializedName("NumOperation")
    private String numOperation;
    @SerializedName("Libelle")
    private String libelle;
    @SerializedName("NumCompteDebitEntree")
    private int numCompteDebitEntree;
    @SerializedName("NumCompteCreditSortie")
    private int numCompteCreditSortie;
    @SerializedName("Qte")
    private int qte;
    @SerializedName("Montant")
    private double montant;
    @SerializedName("Entree")
    private double entree;
    @SerializedName("Sortie")
    private double sortie;
    @SerializedName("DesignationCompteDebitEntree")
    private String designationCompteDebit;
    @SerializedName("DesignationCompteCreditSortie")
    private String designationCreditSortie;
    @SerializedName("NumMvtCompte")
    private String numMvtCompte;


    public ComptabiliteResponse() {

    }

    public String getNumOperation() {
        return numOperation;
    }

    public String getNumMvtCompte() {
        return numMvtCompte;
    }

    public void setNumMvtCompte(String numMvtCompte) {
        this.numMvtCompte = numMvtCompte;
    }

    public void setNumOperation(String numOperation) {
        this.numOperation = numOperation;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getNumCompteDebitEntree() {
        return numCompteDebitEntree;
    }

    public void setNumCompteDebitEntree(int numCompteDebitEntree) {
        this.numCompteDebitEntree = numCompteDebitEntree;
    }

    public int getNumCompteCreditSortie() {
        return numCompteCreditSortie;
    }

    public void setNumCompteCreditSortie(int numCompteCreditSortie) {
        this.numCompteCreditSortie = numCompteCreditSortie;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getEntree() {
        return entree;
    }

    public void setEntree(double entree) {
        this.entree = entree;
    }

    public double getSortie() {
        return sortie;
    }

    public void setSortie(double sortie) {
        this.sortie = sortie;
    }

    public String getDesignationCompteDebit() {
        return designationCompteDebit;
    }

    public void setDesignationCompteDebit(String designationCompteDebit) {
        this.designationCompteDebit = designationCompteDebit;
    }

    public String getDesignationCreditSortie() {
        return designationCreditSortie;
    }

    public void setDesignationCreditSortie(String designationCreditSortie) {
        this.designationCreditSortie = designationCreditSortie;
    }
}
