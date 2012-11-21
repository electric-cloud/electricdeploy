package com.ec.deploy.model.core;

import org.junit.Test;

import com.ec.deploy.model.tenancy.Tenant;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public abstract class TenantRestrictedEntityTestCase<T extends TenantRestrictedEntity>
    extends PersistentEntityTestCase<T>
{

    protected static final String defaultTenantName;
    protected static final String defaultTenantDescription;

    static {
        defaultTenantName = "Homeskillet";
        defaultTenantDescription = "Just a friendly tenant!";
    }


    @Test
    public void ensureTenantHasExpectedName() {
        final Tenant tenant = resolveTenant();
        assertThat(tenant.getName(), is(equalTo(defaultTenantName)));
    }
    
    @Test
    public void ensureTenantHasExpectedDescription() {
        final Tenant tenant = resolveTenant();
        assertThat(tenant.getDescription(), is(equalTo(defaultTenantDescription)));
    }

    private Tenant resolveTenant() {
        final Tenant tenant = createTenant();

        validPersistentEntity = createValidPersistentEntity();
        validPersistentEntity.setTenant(tenant);
        entityManager.persist(tenant);
        entityManager.persist(validPersistentEntity);
        entityManager.flush();
        final T found = (T) entityManager.find(
            validPersistentEntity.getClass(), validPersistentEntity.getId());
        return found.getTenant();
    }

    private Tenant createTenant()
    {
        final Tenant tenant = new Tenant();
        tenant.setName(defaultTenantName);
        tenant.setDescription(defaultTenantDescription);
        return tenant;
    }
}
