package com.takeo.metriccare.usermanagement.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public class OrganizationRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public @NotBlank String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank String description) {
        this.description = description;
    }
}
