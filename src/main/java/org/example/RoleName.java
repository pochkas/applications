package org.example;


import org.springframework.security.core.GrantedAuthority;

public enum RoleName implements GrantedAuthority {

    USER("user"),
    MODERATOR("moderator"),
    ADMIN("admin");

    @Override
    public String getAuthority() {
        return name();
    }

    private String text;

    RoleName(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static RoleName fromString(String text) {
        for (RoleName roleName : RoleName.values()) {
            if (roleName.text.equalsIgnoreCase(text)) {
                return roleName;
            }
        }
        return null;
    }
}
