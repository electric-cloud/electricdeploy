package com.ec.deploy.model.auth;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.logging.LogFactory;

import com.ec.deploy.model.core.TenantRestrictedEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class User
    extends TenantRestrictedEntity
{
    @NotNull
    @Size(min = 2, max = 50)
    private String lastName;
    @NotNull
    @Size(min = 2, max = 50)
    private String firstName;
    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    private Set<Role> authorities;

    public User() {
        authorities = new HashSet<>();
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

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

    public Set<Role> getAuthorities() {
        return authorities;
    }

}
