package com.ec.deploy.model.auth;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;

import com.ec.deploy.model.core.PersistentEntityTestCase;
import com.ec.deploy.model.core.TenantRestrictedEntityTestCase;
import com.ec.deploy.model.tenancy.Tenant;

public class UserTest
    extends TenantRestrictedEntityTestCase<User>
{

    private static final String defaultLastName;
    private static final String defaultFirstName;

    static {
        defaultFirstName = "Josiah";
        defaultLastName = "Haswell";
    }


    @PersistenceContext
    private EntityManager entityManager;
    private User user;
    private Tenant tenant;


    @Before
    public void setUp()
    {
        user = createValidPersistentEntity();
    }

    @Test
    public void ensureValidUserCanBeSaved()
    {
        entityManager.persist(user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void ensureUserWithoutTenantCannotBeSaved()
    {
        user.setTenant(null);
        entityManager.persist(user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void ensureUserCannotBeSavedWithoutFirstName()
    {
        user.setFirstName(null);
        entityManager.persist(user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void ensureUserCannotBeSavedWithTooShortAFirstName()
    {
        user.setFirstName("a");
        entityManager.persist(user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void ensureUserWithoutLastNameCannotBeSaved()
    {
        user.setLastName(null);
        entityManager.persist(user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void ensureUserWithTooShortALastNameCannotBeSaved()
    {
        user.setLastName("a");
        entityManager.persist(user);
    }

    @Override
    protected User createValidPersistentEntity()
    {
        tenant = new Tenant();
        tenant.setName(defaultTenantName);
        tenant.setDescription(defaultTenantDescription);

        entityManager.persist(tenant);
        user = new User();
        user.setFirstName(defaultFirstName);
        user.setLastName(defaultLastName);
        user.setTenant(tenant);
        return user;
    }
}
