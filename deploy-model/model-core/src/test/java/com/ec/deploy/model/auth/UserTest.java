package com.ec.deploy.model.auth;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.validation.ConstraintViolationException;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.junit.Before;
import org.junit.Test;

import com.ec.deploy.model.core.PersistentEntityTestCase;
import com.ec.deploy.model.core.TenantRestrictedEntityTestCase;
import com.ec.deploy.model.decorators.TenantAwareEntityManagerFactory;
import com.ec.deploy.model.tenancy.TenancyContextHolder;
import com.ec.deploy.model.tenancy.Tenant;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class UserTest
    extends TenantRestrictedEntityTestCase<User>
{


    static Tenant tenant;
    private static final String defaultLastName;
    private static final String defaultFirstName;

    static {
        defaultFirstName = "Josiah";
        defaultLastName = "Haswell";

        tenant = new Tenant();
        tenant.setName(defaultTenantName);
        tenant.setDescription(defaultTenantDescription);
        TenancyContextHolder.getInstance().set(tenant);
    }


    @PersistenceUnit
    private EntityManagerFactory emf;
    private User user;


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
    public void ensureDifferentTenantsGetTheirData() {
        entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Tenant t1 = new Tenant();
        t1.setName("pepsi");
        t1.setDescription("pepsi");

        Tenant t2 = new Tenant();
        t2.setName("coke");
        t2.setDescription("coke");

        entityManager.persist(t1);
        entityManager.persist(t2);
        entityManager.flush();



        User pepsiAdmin = new User();
        pepsiAdmin.setFirstName("Hosia");
        pepsiAdmin.setLastName("Hashwell");
        pepsiAdmin.setDescription("Just another guy");
        pepsiAdmin.setUsername("Frapper");
        pepsiAdmin.setTenant(t1);

        User cokeAdmin = new User();
        cokeAdmin.setFirstName("Josiah");
        cokeAdmin.setLastName("Haskell");
        cokeAdmin.setDescription("Just a guy");
        cokeAdmin.setUsername("Josadfs");
        cokeAdmin.setTenant(t2);


        TenancyContextHolder.getInstance().set(t2);
        entityManager.setProperty(
            PersistenceUnitProperties.MULTITENANT_PROPERTY_DEFAULT, t2.getId()
                                                                      .toString());
        entityManager.persist(cokeAdmin);
        entityManager.flush();
        TenancyContextHolder.getInstance().set(t1);
        entityManager.setProperty(
            PersistenceUnitProperties.MULTITENANT_PROPERTY_DEFAULT, t1.getId()
                                                                      .toString());
        entityManager.persist(pepsiAdmin);
        entityManager.flush();
        entityManager.getTransaction().commit();

        entityManager = emf.createEntityManager();


        entityManager.setProperty(PersistenceUnitProperties.MULTITENANT_PROPERTY_DEFAULT, t1.getId().toString());
        assertThat(entityManager.find(User.class, cokeAdmin.getId()), is(nullValue()));
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

        entityManager.persist(tenant);
        user = new User();
        user.setUsername("joe");
        user.setFirstName(defaultFirstName);
        user.setLastName(defaultLastName);
        user.setTenant(tenant);
        return user;
    }
}
