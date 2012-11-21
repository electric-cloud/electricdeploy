package com.ec.deploy.model.core;

import javax.validation.constraints.NotNull;

import com.ec.deploy.model.tenancy.Tenant;
import com.ec.deploy.model.tenancy.TenantRestricted;

public class TenantRestrictedEntity
    extends PersistentEntity
    implements TenantRestricted
{
    @NotNull
    private Tenant tenant;

    @Override
    public Tenant getTenant()
    {
        return tenant;
    }

    public void setTenant(Tenant tenant)
    {
        this.tenant = tenant;
    }
}
