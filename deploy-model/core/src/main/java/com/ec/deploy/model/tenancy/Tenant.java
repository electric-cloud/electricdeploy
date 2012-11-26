package com.ec.deploy.model.tenancy;

import javax.validation.constraints.NotNull;

import com.ec.deploy.model.core.PersistentEntity;

public class Tenant
    extends PersistentEntity<Tenant> {
    @Override
    public Tenant clone()
    {
        final Tenant tenant = new Tenant();
        tenant.setName(getName());
        tenant.setDescription(getDescription());
        return tenant;
    }
}
