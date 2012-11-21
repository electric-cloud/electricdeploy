package com.ec.deploy.model.tenancy;

import com.ec.deploy.model.core.PersistentEntityTestCase;

public class TenantTest extends PersistentEntityTestCase<Tenant>
{

    static final String defaultName;
    static final String defaultDescription;

    private Tenant tenant;
    static {
        defaultName = "Pepsi";
        defaultDescription =
            "Pepsi is a manufacturer of fine, " +
                "pancreas-ruining soft drinks";
    }


    @Override
    protected Tenant createValidPersistentEntity()
    {
        tenant = new Tenant();
        tenant.setName(defaultName);
        tenant.setDescription(defaultDescription);
        return tenant;
    }
}
