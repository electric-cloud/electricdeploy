package com.ec.deploy.persist.dao.auth;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ec.deploy.model.auth.User;

@Transactional
@Repository("UserDao")
public class UserDao
{
    @PersistenceContext
    private EntityManager entityManager;

    public boolean save(User user)
    {
        try {
            entityManager.persist(user);
            return true;
        }
        catch (IllegalArgumentException | ConstraintViolationException ex) {
        return false;
    }
    }

    public long count()
    {
        return entityManager.createQuery("select count(u) from User u",
            Long.class).getSingleResult();
    }
}
