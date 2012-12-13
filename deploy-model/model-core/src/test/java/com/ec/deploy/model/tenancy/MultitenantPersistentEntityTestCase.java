package com.ec.deploy.model.tenancy;

import java.io.Serializable;

import com.ec.deploy.model.core.PersistentEntity;
import com.ec.deploy.model.core.PersistentEntityTestCase;

/**
 * Created with IntelliJ IDEA. User: jhaswell Date: 12/5/12 Time: 12:30 PM To
 * change this template use File | Settings | File Templates.
 */
public abstract class MultitenantPersistentEntityTestCase<
    E extends PersistentEntity<E,K>, K extends Serializable>
    extends PersistentEntityTestCase<E>
{

    static {
        Tenant tenant = new Tenant();
        tenant.setDescription("Default testing tenant");
        tenant.setName("Test Tenant");
        TenancyContextHolder.getInstance().set(tenant);
    }

}
