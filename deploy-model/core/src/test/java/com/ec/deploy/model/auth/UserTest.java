package com.ec.deploy.model.auth;

import java.util.Collections;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;

import com.ec.deploy.model.core.PersistentEntityTestCase;
import com.ec.deploy.model.core.TenantRestrictedEntityTestCase;
import com.ec.deploy.model.tenancy.Tenant;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void ensureUserHasNoRolesUponDefaultCreation() {
        assertTrue(user.getAuthorities().isEmpty());
    }

    @Test
    public void ensureUserHasNoRolesUponPersistAndRetrieval() {
        entityManager.persist(user);
        entityManager.flush();
        assertTrue(entityManager.find(User.class, user.getId())
                                .getAuthorities()
                                .isEmpty());
    }

    @Test
    public void ensureAddingRoleToUserResultsInRoleBeingAvailableUponRetrieval() {
        user.addAuthority(Role.ADMINISTRATOR);
        entityManager.persist(user);
        entityManager.flush();
        assertThat(entityManager.find(User.class, user.getId())
                                .getAuthorities(),
            is(equalTo(Collections.singleton(Role.ADMINISTRATOR))));
    }

    @Override
    protected User createValidPersistentEntity()
    {
        tenant = new Tenant();
        tenant.setName(defaultTenantName);
        tenant.setDescription(defaultTenantDescription);

        entityManager.persist(tenant);
        user = new User();
        user.setUsername("joe");
        user.setFirstName(defaultFirstName);
        user.setLastName(defaultLastName);
        user.setTenant(tenant);
        return user;
    }
}
