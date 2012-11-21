package com.ec.deploy.persist.dao.auth;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ec.deploy.model.auth.User;

@Transactional
@Repository(UserDao.REPOSITORY_NAME)
public class UserDao
{

    public static final String REPOSITORY_NAME = "UserDao";
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

    public List<User> list() {
        return entityManager.createQuery(
            "select user from User user", User.class).getResultList();
    }



}
