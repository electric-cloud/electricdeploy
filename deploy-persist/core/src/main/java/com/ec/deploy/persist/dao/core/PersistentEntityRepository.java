package com.ec.deploy.persist.dao.core;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.criteria.CriteriaQuery;

import com.ec.deploy.model.core.PersistentEntity;
import com.ec.deploy.persist.dao.query.Page;

public interface PersistentEntityRepository<
    I extends Serializable, T extends PersistentEntity>
{
    /**
     * @return the number of entities persisted
     */
    long count();

    /**
     *
     * @param id the id to search for
     * @return the entity identified by id
     */
    @Nullable
    T find(@Nonnull I id);

    /**
     *
     * @param id the id to retrieve
     * @return the entity identified by id
     * @throws java.util.NoSuchElementException if no entity
     * with the given identifier exists
     */
    @Nonnull
    T get(@Nonnull I id);

    /**
     * @return an iterator over the entire set of entities.  Implementations
     * may decide how to implement this (may be lazy or eager)
     */
    @Nonnull
    Iterable<T> list();

    /**
     * @param page the page descriptor to use
     * @return an iterable over this set that batches in the specified size
     */
    @Nonnull
    Iterable<T> list(@Nonnull Page page);

    /**
     * @param item
     * @return
     */
    boolean save(@Nonnull T item);

    /**
     * @param item
     */
    void persist(@Nonnull T item);

    /**
     * @param item
     * @return
     */
    boolean update(@Nonnull T item);

    /**
     * @param query
     * @param <U>
     * @return
     */
    @Nullable
    <U> U executeQuery(@Nonnull CriteriaQuery<U> query);

    /**
     * @param query
     * @param <U>
     * @return
     */
    @Nullable
    <U> U executeQuery(@Nonnull String query);
}
