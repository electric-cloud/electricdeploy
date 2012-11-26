package com.ec.deploy.model.graph;

import com.ec.deploy.model.core.PersistentEntity;

public abstract class Element<E extends Element<E>> extends PersistentEntity<E>
{
    private Graph container;

    @Override
    public abstract E clone();
}
