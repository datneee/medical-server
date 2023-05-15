package com.medical.constants;

public enum StatusOrderItem {

    Processing("Processing")  , Processed("Processed") ,

    Delivering("Delivering") , Complete("Complete");

    private String value;

    private StatusOrderItem(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static StatusOrderItem of(String value) {
        if (value == null) {
            return null;
        }
        for (StatusOrderItem status : StatusOrderItem.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return null;
    }
}

