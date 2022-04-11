package com.picpayclone.enums;

public enum PermissionType {
    // Permission type options
    USER("ROLE_USER"), ADMINISTRATOR("ROLE_ADMIN");

    // Attribute
    private String code;

    // Constructor
    PermissionType(String code) {
        this.code = code;
    }

    // Getter
    public String getCode() {
        return code;
    }
}
