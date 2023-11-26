package com.soft.afri_wifi.Operation;

import com.google.gson.annotations.SerializedName;

public class OperationResponse {
    @SerializedName("NumOperation")
    private String numOperation;
    @SerializedName("CodeClient")
    private String codeClient;
    @SerializedName("Libelle")
    private String libelle;
    @SerializedName("DateOp")
    private String dateOperation;
    @SerializedName("NomUt")
    private String nomUtilisateur;
    @SerializedName("DateSysteme")
    private String dateSysteme;
    @SerializedName("CodeEtatdeBesoin")
    private String codeEtatdeBesoin;
    @SerializedName("IdTypeOperation")
    private int idTypeOperation;
    @SerializedName("Valider")
    private int valider;
    @SerializedName("totalMontantOp")
    private double montant;
    @SerializedName("QteEntree")
    private double qteEntree;
    @SerializedName("Etat")
    private int etat;
    public OperationResponse() {
    }

    public OperationResponse(String libelle, String dateOperation, double montant) {
        this.libelle = libelle;
        this.dateOperation = dateOperation;
        this.montant = montant;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getNumOperation() {
        return numOperation;
    }

    public void setNumOperation(String numOperation) {
        this.numOperation = numOperation;
    }

    public String getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(String dateOperation) {
        this.dateOperation = dateOperation;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getDateSysteme() {
        return dateSysteme;
    }

    public void setDateSysteme(String dateSysteme) {
        this.dateSysteme = dateSysteme;
    }

    public String getCodeEtatdeBesoin() {
        return codeEtatdeBesoin;
    }

    public void setCodeEtatdeBesoin(String codeEtatdeBesoin) {
        this.codeEtatdeBesoin = codeEtatdeBesoin;
    }

    public int getIdTypeOperation() {
        return idTypeOperation;
    }

    public void setIdTypeOperation(int idTypeOperation) {
        this.idTypeOperation = idTypeOperation;
    }

    public int getValider() {
        return valider;
    }

    public void setValider(int valider) {
        this.valider = valider;
    }

    public double getMontant()
    {
        return montant;
    }
    public double getQteEntree()
    {
        return qteEntree;
    }
}
