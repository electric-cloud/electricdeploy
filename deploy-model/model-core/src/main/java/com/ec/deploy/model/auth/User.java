package com.ec.deploy.model.auth;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ec.deploy.model.core.TenantRestrictedEntity;

public class User
    extends TenantRestrictedEntity<User>
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
        onNameUpdate();
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
        onNameUpdate();
    }

    public Set<Role> getAuthorities() {
        return Collections.unmodifiableSet(authorities);
    }

    public void addAuthority(Role role) {
        if (role == null) {
            throw new IllegalArgumentException(
                "Error:  Role must not be null!");
        }
        authorities.add(role);
    }

    public void addAuthorities(Collection<? extends Role> authorities) {
        if(authorities == null) {
            throw new IllegalArgumentException("Error:  Roles must not be null!");
        }
        this.authorities.addAll(authorities);

    }

    @Override
    public User clone()
    {
        final User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.addAuthorities(getAuthorities());
        user.setName(String.format("%s %s", firstName, lastName));
        return user;
    }

    private void onNameUpdate() {
        setName(String.format("%s %s", firstName, lastName));
        setDescription("<none>");
    }
}
