package tn.esprit.dari.entities;

public enum StatusAd {
	pending , accepted , refused ;

    public String getStatusAd() {
        return name().replace("_","-");
    }
	
}
