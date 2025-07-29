package com.feirarural.api.shared.domain.model;

public enum Role {
    USUARIO("USUARIO"),  // Nome em português para consistência com o domínio
    ADMIN("ADMIN");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Role fromString(String text) {
        for (Role role : Role.values()) {
            if (role.value.equalsIgnoreCase(text)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No enum constant " + Role.class + " with value " + text);
    }
}