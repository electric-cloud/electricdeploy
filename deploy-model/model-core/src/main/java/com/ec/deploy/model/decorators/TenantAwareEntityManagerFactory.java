package com.ec.deploy.model.decorators;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;

import org.hibernate.validator.internal.util.logging.LoggerFactory;

import org.eclipse.persistence.config.PersistenceUnitProperties;

import com.ec.deploy.model.tenancy.TenancyContextHolder;
import com.ec.deploy.model.tenancy.Tenant;

public class TenantAwareEntityManagerFactory implements EntityManagerFactory
{

    static final Logger logger =
        Logger.getLogger(TenantAwareEntityManagerFactory.class.getName());


    private EntityManagerFactory entityManagerFactory;
    private static final Map<UUID, Map> propertyCache;

    static {
        propertyCache = Collections.synchronizedMap(new HashMap<UUID, Map>());
    }


    @Override
    public EntityManager createEntityManager()
    {
        return createEntityManager(setTenant(entityManagerFactory.getProperties()));
    }


    private Tenant getCurrentTenant()
    {
        final Tenant currentTenant = TenancyContextHolder.getInstance().get();
        if(currentTenant == null) {
            throw new IllegalStateException("Error!  Attempting to access a " +
                "tenant-specific persistence unit without a tenant!");
        }

        logger.log(Level.INFO,
            "Located tenant ''{0}''", currentTenant.getName());
        return currentTenant;
    }

    @Override
    public EntityManager createEntityManager(Map map)
    {
        Map properties = setTenant(map);
        return entityManagerFactory.createEntityManager(properties);
    }

    private Map setTenant(Map map) {
        final UUID cacheKey = getCurrentTenant().getId();
        final Map properties = propertyCache.get(cacheKey);
        System.out
              .println("Called");
        if(properties != null) {
            return properties;
        }
        else {
            final Map copy = new HashMap(map);
            copy.put(PersistenceUnitProperties.MULTITENANT_PROPERTY_DEFAULT,
                getCurrentTenant().getId());
            propertyCache.put(cacheKey, copy);
            return copy;
        }
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder()
    {
        return entityManagerFactory.getCriteriaBuilder();
    }

    @Override
    public Metamodel getMetamodel()
    {
        return entityManagerFactory.getMetamodel();
    }

    @Override
    public boolean isOpen()
    {
        return entityManagerFactory.isOpen();
    }

    @Override
    public void close()
    {
        entityManagerFactory.close();
    }

    @Override
    public Map<String, Object> getProperties()
    {
        return setTenant(entityManagerFactory.getProperties());
    }

    @Override
    public Cache getCache()
    {
        return entityManagerFactory.getCache();
    }

    @Override
    public PersistenceUnitUtil getPersistenceUnitUtil()
    {
        return entityManagerFactory.getPersistenceUnitUtil();
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        if(entityManagerFactory == null) {
            logger.log(Level.SEVERE, "EntityManagerFactory must not be null");
            throw new IllegalStateException("Error: EntityManagerFactory must not be null!");
        }
        logger.log(Level.INFO, "Decorating raw persistence unit");
        this.entityManagerFactory = entityManagerFactory;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
