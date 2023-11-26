package com.soft.afri_wifi.Depot.data;

import com.google.gson.annotations.SerializedName;

public class DepotResponse {
    @SerializedName("CodeDepot")
    private String codeDepot;
    @SerializedName("DesignationDepot")
    private String designationDepot;
    @SerializedName("AffecteCompte")
    private String affecteCompte;

    public DepotResponse(String codeDepot, String designationDepot, String affecteCompte) {
        this.codeDepot = codeDepot;
        this.designationDepot = designationDepot;
        this.affecteCompte = affecteCompte;
    }

    public String getCodeDepot() {
        return codeDepot;
    }

    public void setCodeDepot(String codeDepot) {
        this.codeDepot = codeDepot;
    }

    public String getDesignationDepot() {
        return designationDepot;
    }

    public void setDesignationDepot(String designationDepot) {
        this.designationDepot = designationDepot;
    }

    public String getAffecteCompte() {
        return affecteCompte;
    }

    public void setAffecteCompte(String affecteCompte) {
        this.affecteCompte = affecteCompte;
    }
}
