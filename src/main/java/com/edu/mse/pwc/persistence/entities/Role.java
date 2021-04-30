package com.edu.mse.pwc.persistence.entities;

public enum Role {
    ADMIN("ADMIN"),
    MODERATOR("MODERATOR"),
    USER("USER");

    private final String text;

    Role(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Role{" +
                "text='" + text + '\'' +
                '}';
    }
}
