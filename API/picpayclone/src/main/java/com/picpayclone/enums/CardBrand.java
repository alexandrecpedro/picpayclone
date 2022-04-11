package com.picpayclone.enums;

public enum CardBrand {
    // Brand options
    VISA("Visa"), MASTERCARD("MasterCard"), ELO("Elo");

    // Attribute
    private String description;

    // Constructor
    CardBrand(String description) {
        this.description = description;
    }

    // Getter
    public String getDescription() {
        return description;
    }
}
