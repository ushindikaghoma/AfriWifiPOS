package com.soft.afri_wifi.Ticket.data;

import com.google.gson.annotations.SerializedName;

public class MvtTicketReponse {
    @SerializedName("NumOperation")
    String numOperation;
    @SerializedName("CodeDepot")
    String codeDepot;
    @SerializedName("CatArticle")
    String catArticle;
    @SerializedName("UserName")
    String userName;
    @SerializedName("Password")
    String password;
    @SerializedName("Etat")
    int etat;
    @SerializedName("Prix")
    double prix;
    @SerializedName("Entree")
    double entree;
    @SerializedName("Sortie")
    double sortie;
    @SerializedName("IdTicket")
    int idTicket;

    public MvtTicketReponse() {
    }

    public String getNumOperation() {
        return numOperation;
    }

    public void setNumOperation(String numOperation) {
        this.numOperation = numOperation;
    }

    public String getCodeDepot() {
        return codeDepot;
    }

    public void setCodeDepot(String codeDepot) {
        this.codeDepot = codeDepot;
    }

    public String getCatArticle() {
        return catArticle;
    }

    public void setCatArticle(String catArticle) {
        this.catArticle = catArticle;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
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

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }
}
