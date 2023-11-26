package com.soft.afri_wifi.Ticket.data;

import com.google.gson.annotations.SerializedName;

public class TicketResponse {
    @SerializedName("DesignationTicket")
    String designationTicket;
    @SerializedName("PrixTicket")
    double prixTicket;
    @SerializedName("ValiditeTicket")
    String validiteTicket;
    @SerializedName("CatArticle")
    String catArticle;
    @SerializedName("IdTicket")
    int idTicket;

    public TicketResponse(String designationTicket,
                          double prixTicket,
                          String validiteTicket, String catArticle) {
        this.designationTicket = designationTicket;
        this.prixTicket = prixTicket;
        this.validiteTicket = validiteTicket;
        this.catArticle = catArticle;
    }

    public TicketResponse() {
    }

    public String getDesignationTicket() {
        return designationTicket;
    }

    public void setDesignationTicket(String designationTicket) {
        this.designationTicket = designationTicket;
    }

    public double getPrixTicket() {
        return prixTicket;
    }

    public void setPrixTicket(double prixTicket) {
        this.prixTicket = prixTicket;
    }

    public String getValiditeTicket() {
        return validiteTicket;
    }

    public void setValiditeTicket(String validiteTicket) {
        this.validiteTicket = validiteTicket;
    }

    public String getCatArticle() {
        return catArticle;
    }

    public void setCatArticle(String catArticle) {
        this.catArticle = catArticle;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }
}
