package com.ec.deploy.model.auth;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ec.deploy.model.core.TenantRestrictedEntity;

public class User
    extends TenantRestrictedEntity
{
    @NotNull
    @Size(min = 2, max = 50)
    private String lastName;
    @NotNull
    @Size(min = 2, max = 50)
    private String firstName;

    public String getLastName()
    {
        return lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
}
