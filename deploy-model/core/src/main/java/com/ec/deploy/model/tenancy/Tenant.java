package com.ec.deploy.model.tenancy;

import javax.validation.constraints.NotNull;

import com.ec.deploy.model.core.PersistentEntity;

public class Tenant
    extends PersistentEntity {
    @NotNull
    private String name;
    @NotNull
    private String description;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
