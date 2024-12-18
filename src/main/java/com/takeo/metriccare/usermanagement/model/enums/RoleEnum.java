package com.takeo.metriccare.usermanagement.model.enums;

public enum RoleEnum {
    SUPER_ADMIN("ROLE_SUPER_ADMIN"),
    ORG_ADMIN("ROLE_ORG_ADMIN"),
    USER("ROLE_USER");

    RoleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private String name;
}
