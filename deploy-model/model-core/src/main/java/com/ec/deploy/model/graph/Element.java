package com.ec.deploy.model.graph;

import com.ec.deploy.model.core.PersistentEntity;

public abstract class Element<E extends Element<E>> extends PersistentEntity<E, Long>
{
    private Graph container;

    public Element(final Graph container) {
        this.container = container;
    }

    public Element() {

    }

    public Graph getContainer() {
        return container;
    }

    public void setContainer(Graph container) {
        assert container != null : "Container must not be null!";
        this.container = container;
    }
    @Override
    public abstract E clone();
}
