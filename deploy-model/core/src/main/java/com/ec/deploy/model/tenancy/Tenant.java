package com.ec.deploy.model.tenancy;

import javax.validation.constraints.NotNull;

import com.ec.deploy.model.core.PersistentEntity;
import com.ec.deploy.model.core.UniquePersistentEntity;

public class Tenant
    extends UniquePersistentEntity<Tenant>
{
    @Override
    public Tenant clone()
    {
        final Tenant tenant = new Tenant();
        tenant.setName(getName());
        tenant.setDescription(getDescription());
        return tenant;
    }
}
