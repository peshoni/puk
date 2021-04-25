package com.edu.mse.pwc.persistence.entities;

public enum RoleEnum {
    ADMIN("ADMIN"),
    MODERATOR("MODERATOR"),
    USER("USER");
    private final String text;

    RoleEnum(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "RoleEnum{" +
                "text='" + text + '\'' +
                '}';
    }
}
