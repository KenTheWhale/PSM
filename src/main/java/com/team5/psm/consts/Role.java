package com.team5.psm.consts;

import lombok.Getter;

@Getter
public class Role {
    public static String USER = "User";
    public static String SHOP_OWNER = "Shop Owner";
    public static String ADMIN = "Admin";

//    private final String value;
//
//    ERoleEnum(String value) {
//        this.value = value;
//    }
//
//    public static ERoleEnum fromValue(String value) {
//        for (ERoleEnum role : ERoleEnum.values()) {
//            if (role.getValue().equals(value)) {
//                return role;
//            }
//        }
//        return null;
//    }
}
