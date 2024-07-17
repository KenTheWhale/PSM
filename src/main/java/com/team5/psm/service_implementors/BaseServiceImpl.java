package com.team5.psm.service_implementors;

public abstract class BaseServiceImpl {
    protected boolean checkIfStringIsValid(String input) {
        return input != null && !input.isEmpty();
    }
}
