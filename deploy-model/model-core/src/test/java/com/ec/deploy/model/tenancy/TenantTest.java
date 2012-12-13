package com.ec.deploy.model.tenancy;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.ec.deploy.model.core.PersistentEntityTestCase;
import com.ec.deploy.model.decorators.TenantAwareEntityManagerFactory;

import static org.junit.Assert.assertEquals;

public class TenantTest extends MultitenantPersistentEntityTestCase<Tenant, UUID>
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
