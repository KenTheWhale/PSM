package com.team5.psm.consts;

import lombok.Getter;

@Getter
public enum ERoleEnum {
    ROLE_USER("User"),
    ROLE_SHOP_OWNER("Shop Owner"),
    ROLE_ADMIN("Admin");

    private final String value;

    ERoleEnum(String value) {
        this.value = value;
    }

    public static ERoleEnum fromValue(String value) {
        for (ERoleEnum role : ERoleEnum.values()) {
            if (role.getValue().equals(value)) {
                return role;
            }
        }
        return null;
    }
}
