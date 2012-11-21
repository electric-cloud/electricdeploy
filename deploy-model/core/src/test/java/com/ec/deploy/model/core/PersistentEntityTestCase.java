package com.ec.deploy.model.core;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    locations = {"classpath:/spring/model-context.xml"
    })
@Transactional
@TransactionConfiguration
public abstract class PersistentEntityTestCase<T extends PersistentEntity>
{

    protected final Logger logger;

    protected T validPersistentEntity;

    @PersistenceContext
    protected EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected PersistentEntityTestCase() {
        logger = Logger.getLogger(getClass().getName());
    }




    protected abstract T createValidPersistentEntity();

    @Test
    public void ensureValidPersistentEntityCanBeSaved() {
        validPersistentEntity = createValidPersistentEntity();
        assertThat("Invalid overridden createValidPersistentEntity" +
            "--valid persistent entity must not be null!",
            validPersistentEntity, is(not(nullValue())));
        try {
            entityManager.persist(validPersistentEntity);
            assertThat(entityManager.contains(validPersistentEntity), is(true));
            entityManager.remove(validPersistentEntity);
            assertThat(entityManager.contains(validPersistentEntity), is(false));
        } catch(ConstraintViolationException ex) {
            handleInvalidEntity(ex);
        }
    }

    private void handleInvalidEntity(ConstraintViolationException ex)
    {
        logger.log(Level.SEVERE, "The following constraint violations " +
            "were detected while validating the valid persistent entity: ");
        for(ConstraintViolation violation : ex.getConstraintViolations()) {
            logger.log(Level.SEVERE, "\tValue ''{0}'' at path " +
                "''{1}'' on type ''{2}''",
                    new Object[] {
                        violation.getInvalidValue(),
                        violation.getPropertyPath(),
                        validPersistentEntity.getClass().getName()
                    });

        }
        fail("Invalid overridden createValidPersistentEntity--" +
            "validators indicate it wasn't valid!");
    }

    @After
    public void tearDown() {  }
}
