package tn.esprit.dari.entities;

public enum StatusType {
	pending("pending") , accepted("accepted") , refused("refused");
	
	private final String val;

	StatusType(String val) {
        this.val = val;
    }

    public String getStatusType() {
        return val;
    }

}
